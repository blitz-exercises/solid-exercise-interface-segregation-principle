package com.example.smarthome;

/**
 * Represents a smart security camera device. Currently implements all methods from
 * SmartHomeService, but only needs camera methods.
 */
public class Camera extends Device implements SecurityCameraControl {
    private boolean isRecording;
    private int snapshotCount;

    public Camera(String id, String name) {
        super(id, name, "CAMERA");
        this.isRecording = false;
        this.snapshotCount = 0;
    }

    @Override
    public void startRecording() {
        this.isRecording = true;
        System.out.println("Camera " + getId() + " started recording");
    }

    @Override
    public void stopRecording() {
        this.isRecording = false;
        System.out.println("Camera " + getId() + " stopped recording");
    }

    @Override
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

}

