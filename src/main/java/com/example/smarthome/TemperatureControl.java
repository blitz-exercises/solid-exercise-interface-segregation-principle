package com.example.smarthome;

public interface TemperatureControl  extends Switchable {
    void setTemperature(double temperature);

    double getCurrentTemperature();
}
