package com.bhavya.businesslogic;

/**
 * This class holds the values of sensor data
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class SensorDetails {
    private int id;
    private String timestamp;
    private int sensorId;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}