package com.example.smarthome;

public interface SecurityCameraControl  extends Switchable {
    void startRecording();

    void stopRecording();

    String takeSnapshot();
}
