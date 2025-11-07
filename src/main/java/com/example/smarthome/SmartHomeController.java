package com.example.smarthome;

import java.util.HashMap;
import java.util.Map;

/**
 * Smart home controller that manages various smart devices.
 * 
 * This implementation violates the Interface Segregation Principle by: 1. Forcing all devices to
 * implement methods they don't need 2. Using type checking and exception handling to work around
 * the design flaw 3. Creating tight coupling between different device capabilities
 * 
 * Your task: Refactor this to use segregated interfaces while maintaining the SmartHomeService
 * contract.
 */
public class SmartHomeController implements SmartHomeService {

    private final Map<String, Device> devices;

    public SmartHomeController() {
        this.devices = new HashMap<>();
    }

    /**
     * Registers a device with the controller.
     * 
     * @param device The device to register
     */
    public void registerDevice(Device device) {
        devices.put(device.getId(), device);
        System.out.println("Registered device: " + device);
    }

    // Lighting control methods
    @Override
    public void turnOnLight(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Light) {
            ((Light) device).turnOn();
        } else if (device instanceof SmartSwitch) {
            ((SmartSwitch) device).turnOn();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support lighting control");
        }
    }

    @Override
    public void turnOffLight(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Light) {
            ((Light) device).turnOff();
        } else if (device instanceof SmartSwitch) {
            ((SmartSwitch) device).turnOff();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support lighting control");
        }
    }

    @Override
    public void setBrightness(String deviceId, int brightness) {
        Device device = getDevice(deviceId);
        if (device instanceof Light) {
            ((Light) device).setBrightness(brightness);
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support brightness control");
        }
    }

    @Override
    public void setColor(String deviceId, String color) {
        Device device = getDevice(deviceId);
        if (device instanceof Light) {
            ((Light) device).setColor(color);
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support color control");
        }
    }

    // Temperature control methods
    @Override
    public void setTemperature(String deviceId, double temperature) {
        Device device = getDevice(deviceId);
        if (device instanceof Thermostat) {
            ((Thermostat) device).setTemperature(temperature);
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support temperature control");
        }
    }

    @Override
    public double getCurrentTemperature(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Thermostat) {
            return ((Thermostat) device).getCurrentTemperature();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support temperature reading");
        }
    }

    // Security camera control methods
    @Override
    public void startRecording(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Camera) {
            ((Camera) device).startRecording();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support recording");
        }
    }

    @Override
    public void stopRecording(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Camera) {
            ((Camera) device).stopRecording();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support recording");
        }
    }

    @Override
    public String takeSnapshot(String deviceId) {
        Device device = getDevice(deviceId);
        if (device instanceof Camera) {
            return ((Camera) device).takeSnapshot();
        } else {
            throw new IllegalArgumentException(
                    "Device " + deviceId + " does not support snapshots");
        }
    }

    private Device getDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }
        return device;
    }

    public Map<String, Device> getDevices() {
        return new HashMap<>(devices);
    }
}

