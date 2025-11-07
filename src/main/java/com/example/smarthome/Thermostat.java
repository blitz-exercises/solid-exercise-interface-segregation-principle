package com.example.smarthome;

/**
 * Represents a smart thermostat device.
 * Currently implements all methods from SmartHomeService, but only needs temperature methods.
 */
public class Thermostat extends Device implements TemperatureControl {
    private double currentTemperature;
    private double targetTemperature;
    
    public Thermostat(String id, String name) {
        super(id, name, "THERMOSTAT");
        this.currentTemperature = 20.0;
        this.targetTemperature = 20.0;
    }
    
    // Temperature methods (needed)
    public void setTemperature(String deviceId, double temperature) {
        this.targetTemperature = temperature;
        System.out.println("Thermostat " + getId() + " target temperature set to " + temperature + "°C");
    }
    
    public double getCurrentTemperature(String deviceId) {
        return currentTemperature;
    }
    
    public double getTargetTemperature(String deviceId) {
        return targetTemperature;
    }
    
    public void setCurrentTemperature(String deviceId, double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}