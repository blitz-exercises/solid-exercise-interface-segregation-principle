package com.example.smarthome;

/**
 * Represents a smart thermostat device. Currently implements all methods from SmartHomeService, but
 * only needs temperature methods later.
 */
public class Thermostat extends Device implements TemperatureControl {
    private double currentTemperature;
    private double targetTemperature;

    public Thermostat(String id, String name) {
        super(id, name, "THERMOSTAT");
        this.currentTemperature = 20.0;
        this.targetTemperature = 20.0;
    }

    @Override
    public void setTemperature(double temperature) {
        this.targetTemperature = temperature;
        System.out.println(
                "Thermostat " + getId() + " target temperature set to " + temperature + "°C");
    }

    @Override
    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}

