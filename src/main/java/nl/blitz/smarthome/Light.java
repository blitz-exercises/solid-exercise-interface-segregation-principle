package com.example.smarthome;

/**
 * Represents a smart light device.
 * Currently implements all methods from SmartHomeService, but only needs lighting methods.
 */
public class Light extends Device {
    private boolean isOn;
    private int brightness;
    private String color;
    
    public Light(String id, String name) {
        super(id, name, "LIGHT");
        this.isOn = false;
        this.brightness = 50;
        this.color = "white";
    }
    
    // Lighting methods (needed)
    public void turnOn() {
        this.isOn = true;
        System.out.println("Light " + getId() + " turned on");
    }
    
    public void turnOff() {
        this.isOn = false;
        System.out.println("Light " + getId() + " turned off");
    }
    
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        }
        this.brightness = brightness;
        System.out.println("Light " + getId() + " brightness set to " + brightness);
    }
    
    public void setColor(String color) {
        this.color = color;
        System.out.println("Light " + getId() + " color set to " + color);
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public int getBrightness() {
        return brightness;
    }
    
    public String getColor() {
        return color;
    }
    
    // Temperature methods (not needed - violates ISP)
    public void setTemperature(double temperature) {
        throw new UnsupportedOperationException("Lights do not support temperature control");
    }
    
    public double getCurrentTemperature() {
        throw new UnsupportedOperationException("Lights do not support temperature reading");
    }
    
    // Security camera methods (not needed - violates ISP)
    public void startRecording() {
        throw new UnsupportedOperationException("Lights do not support recording");
    }
    
    public void stopRecording() {
        throw new UnsupportedOperationException("Lights do not support recording");
    }
    
    public String takeSnapshot() {
        throw new UnsupportedOperationException("Lights do not support snapshots");
    }
}

