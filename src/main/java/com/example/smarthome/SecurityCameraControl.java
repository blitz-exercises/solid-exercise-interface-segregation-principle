package com.example.smarthome;

public interface SecurityCameraControl {
    void startRecording();

    void stopRecording();

    String takeSnapshot();
}
