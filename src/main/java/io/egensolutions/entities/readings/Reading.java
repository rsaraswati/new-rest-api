package io.egensolutions.entities.readings;

import io.egensolutions.alerts.Alerts;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(schema = "readings_db")
@NamedQueries({
        @NamedQuery(name = "Reading.findAllReadings",
                query = "SELECT readings FROM Reading readings ORDER BY readings.timestamp ASC"),
        @NamedQuery(name = "Reading.findByTimestamp",
                query = "SELECT readings FROM Reading readings WHERE readings.timestamp=:timestampParameter"),
})
public class Reading {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String vin;

    @EmbeddedId
    @Column(unique = true)
    LatiLongi latiLongi;

    @Column(nullable = false)
    private Timestamp timestamp;

    private float fuelVolume;
    private int speed;
    private int engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRpm;

    @OneToOne
    private Tyre tyre;
    @OneToOne
    private Alerts alerts;

    public Reading() {
        this.vin = UUID.randomUUID().toString();
        System.out.println("1 " + vin);
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LatiLongi getLatiLongi() {
        return latiLongi;
    }

    public void setLatiLongi(LatiLongi latiLongi) {
        this.latiLongi = latiLongi;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(float fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(int engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public int getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(int engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }

    @Override
    public String toString() {
        return "Reading{" + "vin='" + vin + '\'' + ", latiLongi=" + latiLongi + ", timestamp=" + timestamp + ", fuelVolume=" + fuelVolume + ", speed=" + speed + ", engineHp=" + engineHp + ", checkEngineLightOn=" + checkEngineLightOn + ", engineCoolantLow=" + engineCoolantLow + ", cruiseControlOn=" + cruiseControlOn + ", engineRpm=" + engineRpm + ", tyre=" + tyre + ", alerts=" + alerts + '}';
    }
}
