package com.example.smarthome;

public interface LightControl extends Switchable {
        
    /**
     * Sets the brightness level of a light device (0-100).
     * @param deviceId The unique identifier of the light device
     * @param brightness Brightness level from 0 to 100
     */
    void setBrightness(String deviceId, int brightness);
    
    /**
     * Sets the color of a light device.
     * @param deviceId The unique identifier of the light device
     * @param color The color name (e.g., "red", "blue", "warm white")
     */
    void setColor(String deviceId, String color);
    
    
}
