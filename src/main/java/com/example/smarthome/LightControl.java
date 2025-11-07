package com.example.smarthome;

public interface LightControl extends Switchable {
    void setBrightness(int brightness);

    void setColor(String color);
}
