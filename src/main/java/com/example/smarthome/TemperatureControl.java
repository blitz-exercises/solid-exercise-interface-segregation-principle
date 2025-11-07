package com.example.smarthome;

public interface TemperatureControl {
        // Temperature control methods
    /**
     * Sets the target temperature for a thermostat device.
     * @param deviceId The unique identifier of the thermostat device
     * @param temperature Target temperature in Celsius
     */
    void setTemperature(String deviceId, double temperature);
    
    /**
     * Gets the current temperature from a thermostat device.
     * @param deviceId The unique identifier of the thermostat device
     * @return Current temperature in Celsius
     */
    double getCurrentTemperature(String deviceId);
}
