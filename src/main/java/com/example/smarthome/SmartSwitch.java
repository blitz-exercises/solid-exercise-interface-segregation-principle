package com.example.smarthome;

public interface SmartSwitch extends Switchable {
    // Lighting control methods
    /**
     * Turns on a light device.
     * @param deviceId The unique identifier of the light device
     */
    void turnOnLight(String deviceId);
    
    /**
     * Turns off a light device.
     * @param deviceId The unique identifier of the light device
     */
    void turnOffLight(String deviceId);

}
