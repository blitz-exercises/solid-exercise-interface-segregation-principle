package com.example.smarthome;

/**
 * Represents a smart security camera device.
 * Currently implements all methods from SmartHomeService, but only needs camera methods.
 */
public class Camera extends Device {
    private boolean isRecording;
    private int snapshotCount;
    
    public Camera(String id, String name) {
        super(id, name, "CAMERA");
        this.isRecording = false;
        this.snapshotCount = 0;
    }
    
    // Security camera methods (needed)
    public void startRecording() {
        this.isRecording = true;
        System.out.println("Camera " + getId() + " started recording");
    }
    
    public void stopRecording() {
        this.isRecording = false;
        System.out.println("Camera " + getId() + " stopped recording");
    }
    
    public String takeSnapshot() {
        snapshotCount++;
        String snapshot = "snapshot_" + getId() + "_" + snapshotCount + ".jpg";
        System.out.println("Camera " + getId() + " took snapshot: " + snapshot);
        return snapshot;
    }
    
    public boolean isRecording() {
        return isRecording;
    }
    
    public int getSnapshotCount() {
        return snapshotCount;
    }
    
    // Lighting methods (not needed - violates ISP)
    public void turnOn() {
        throw new UnsupportedOperationException("Cameras do not support lighting control");
    }
    
    public void turnOff() {
        throw new UnsupportedOperationException("Cameras do not support lighting control");
    }
    
    public void setBrightness(int brightness) {
        throw new UnsupportedOperationException("Cameras do not support brightness control");
    }
    
    public void setColor(String color) {
        throw new UnsupportedOperationException("Cameras do not support color control");
    }
    
    // Temperature methods (not needed - violates ISP)
    public void setTemperature(double temperature) {
        throw new UnsupportedOperationException("Cameras do not support temperature control");
    }
    
    public double getCurrentTemperature() {
        throw new UnsupportedOperationException("Cameras do not support temperature reading");
    }
}

