package com.example.smarthome;

/**
 * Represents a smart security camera device.
 * Currently implements all methods from SmartHomeService, but only needs camera methods.
 */
public class Camera extends Device implements SecurityCameraControl {
    private boolean isRecording;
    private int snapshotCount;
    
    public Camera(String id, String name) {
        super(id, name, "CAMERA");
        this.isRecording = false;
        this.snapshotCount = 0;
    }
    
    // Security camera methods (needed)
    public void startRecording(String deviceId) {
        this.isRecording = true;
        System.out.println("Camera " + getId() + " started recording");
    }
    
    public void stopRecording(String deviceId) {
        this.isRecording = false;
        System.out.println("Camera " + getId() + " stopped recording");
    }
    
    public String takeSnapshot(String deviceId) {
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
}