package io.egensolutions.service;

import io.egensolutions.alerts.Alerts;
import io.egensolutions.alerts.CheckChecksForAlerts;
import io.egensolutions.entities.readings.Reading;
import io.egensolutions.exception.ResourceNotFoundException;
import io.egensolutions.repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingsServiceImplementation implements ReadingsService{
    @Autowired
    ReadingsRepository readingsRepository;
    Alerts alerts = new Alerts();
    ReadingsServiceImplementation(){
        System.out.println("Reading service");
    }

    @Transactional(readOnly = true)
    public List<Reading> findAllReadings() {
        return readingsRepository.findAllReadings();
    }

    @Transactional(readOnly = true)
    public Reading findOneReading(String vin) {
        Reading reading = readingsRepository.findOneReading(vin);
        if(reading == null){
            //Throw ResourceNotFoundException Exception
            throw new ResourceNotFoundException("Reading with vin " + vin + " not found in database");
        }
        return reading;
    }
    @Transactional
    public Reading createReading(Reading reading) {
       /* Reading existingReading = readingsRepository.findByTimestamp(reading.getTimestamp());
        if (existingReading==null){
            throw new BadRequestException("Reading with timestamp" + reading.getTimestamp() + "already exists");
        } */
        return readingsRepository.createReading(reading);
    }
    @Transactional
    public Reading updateReading(String vin, Reading reading) {
        Reading existingReading = readingsRepository.findOneReading(vin);
        if(existingReading == null){
            throw new ResourceNotFoundException("Reading with vin " + vin + " not found in database");
        }
        if(existingReading!=null){
            Boolean checkRpm = new CheckChecksForAlerts().checkRpm();
            Boolean checkFuelVolume = new CheckChecksForAlerts().checkFuelVolume();
            Boolean checkTirePressure = new CheckChecksForAlerts().checkTirePressure();
            Boolean checkEngineStats = new CheckChecksForAlerts().checkEngineStats();
            if(checkRpm){
                alerts.setId(1);
                alerts.setMessage("Engine RPM is greater than read line RPM! Check your car!");
                alerts.setPriority("HIGH");
            }
            if(checkFuelVolume){
                alerts.setId(2);
                alerts.setMessage("Fuel volume is less than 10% of max volume! Get a refill!");
                alerts.setPriority("MEDIUM");
            }
            if(checkTirePressure){
                alerts.setId(3);
                alerts.setMessage("Tire pressure problem! Check your tires!");
                alerts.setPriority("LOW");
            }
            if(checkEngineStats){
                alerts.setId(4);
                alerts.setMessage("Either engine coolant is low or engine light is on");
                alerts.setPriority("LOW");
            }
        }

        return readingsRepository.updateReading(existingReading);
    }
    @Transactional
    public void deleteReading(String vin) {

        Reading existingReading = readingsRepository.findOneReading(vin);

        if(existingReading == null){
            throw new ResourceNotFoundException("Reading with vin " + vin + " not found in database");
        }
        readingsRepository.deleteReading(vin);
    }
}
