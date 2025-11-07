package com.example.smarthome;

/**
 * Contract for smart home device operations.
 * 
 * IMPORTANT: This interface should remain UNTOUCHED during refactoring exercises.
 * It defines the public API that SmartHomeController must implement.
 * 
 * This interface violates the Interface Segregation Principle by combining
 * methods for different device capabilities (lighting, temperature, security).
 */
public interface SmartHomeService {
    
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
    
    // Security camera control methods
    /**
     * Starts recording on a security camera device.
     * @param deviceId The unique identifier of the camera device
     */
    void startRecording(String deviceId);
    
    /**
     * Stops recording on a security camera device.
     * @param deviceId The unique identifier of the camera device
     */
    void stopRecording(String deviceId);
    
    /**
     * Takes a snapshot from a security camera device.
     * @param deviceId The unique identifier of the camera device
     * @return The snapshot image data as a string
     */
    String takeSnapshot(String deviceId);
}

