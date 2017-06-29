package io.egensolutions.alerts;

import io.egensolutions.entities.readings.Reading;
import io.egensolutions.entities.vehicle.Vehicle;

public class CheckChecksForAlerts {
    Reading checkReading = new Reading();
    Vehicle checkVehicle = new Vehicle();
    public boolean checkRpm(){
        if(checkReading.getEngineRpm() > checkVehicle.getRedlineRpm()){
            return true;
        }
        else
            return false;
    }
    public boolean checkFuelVolume(){
        if(checkReading.getFuelVolume() < 0.1* checkVehicle.getMaxFuelVolume()){
            return true;
        }
        else
            return false;
    }
    public boolean checkTirePressure(){
        if((checkReading.getTyre().getFrontLeft() < 32 || checkReading.getTyre().getFrontLeft() > 36) ||
                (checkReading.getTyre().getFrontRight() < 32 || checkReading.getTyre().getFrontRight() > 36) ||
                (checkReading.getTyre().getRearLeft() < 32 || checkReading.getTyre().getRearLeft() > 36) ||
                (checkReading.getTyre().getRearRight() < 32 || checkReading.getTyre().getRearRight() > 36)){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkEngineStats(){
        if(checkReading.isEngineCoolantLow() || checkReading.isCheckEngineLightOn()){
            return true;
        }else{
            return false;
        }
    }
}
