package com.example.smarthome;

/**
 * Base class representing a smart home device.
 * This is a simple data class that remains unchanged during refactoring.
 */
public class Device {
    private final String id;
    private final String name;
    private final String type;
    
    public Device(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("Device{id='%s', name='%s', type='%s'}", id, name, type);
    }
}

