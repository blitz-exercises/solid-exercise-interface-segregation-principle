package com.example.smarthome;

/**
 * Represents a smart thermostat device.
 * Currently implements all methods from SmartHomeService, but only needs temperature methods.
 */
public class Thermostat extends Device {
    private double currentTemperature;
    private double targetTemperature;
    
    public Thermostat(String id, String name) {
        super(id, name, "THERMOSTAT");
        this.currentTemperature = 20.0;
        this.targetTemperature = 20.0;
    }
    
    // Temperature methods (needed)
    public void setTemperature(double temperature) {
        this.targetTemperature = temperature;
        System.out.println("Thermostat " + getId() + " target temperature set to " + temperature + "°C");
    }
    
    public double getCurrentTemperature() {
        return currentTemperature;
    }
    
    public double getTargetTemperature() {
        return targetTemperature;
    }
    
    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
    
    // Lighting methods (not needed - violates ISP)
    public void turnOn() {
        throw new UnsupportedOperationException("Thermostats do not support lighting control");
    }
    
    public void turnOff() {
        throw new UnsupportedOperationException("Thermostats do not support lighting control");
    }
    
    public void setBrightness(int brightness) {
        throw new UnsupportedOperationException("Thermostats do not support brightness control");
    }
    
    public void setColor(String color) {
        throw new UnsupportedOperationException("Thermostats do not support color control");
    }
    
    // Security camera methods (not needed - violates ISP)
    public void startRecording() {
        throw new UnsupportedOperationException("Thermostats do not support recording");
    }
    
    public void stopRecording() {
        throw new UnsupportedOperationException("Thermostats do not support recording");
    }
    
    public String takeSnapshot() {
        throw new UnsupportedOperationException("Thermostats do not support snapshots");
    }
}

