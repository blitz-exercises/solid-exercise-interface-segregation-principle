package com.example.smarthome;

public interface SecurityCameraControl {
       
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
