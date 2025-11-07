package com.example.smarthome;

import java.util.HashMap;
import java.util.Map;

public class SmartHomeController {

    private final Map<String, Device> allDevices = new HashMap<>();
    private final Map<String, Switchable> switchables = new HashMap<>();
    private final Map<String, LightControl> lights = new HashMap<>();
    private final Map<String, TemperatureControl> thermostats = new HashMap<>();
    private final Map<String, SecurityCameraControl> cameras = new HashMap<>();

    public SmartHomeController() {
    }

    public void registerDevice(Device device) {
        allDevices.put(device.getId(), device);

        if (device instanceof Switchable) {
            switchables.put(device.getId(), (Switchable) device);
        }
        if (device instanceof LightControl) {
            lights.put(device.getId(), (LightControl) device);
        }
        if (device instanceof TemperatureControl) {
            thermostats.put(device.getId(), (TemperatureControl) device);
        }
        if (device instanceof SecurityCameraControl) {
            cameras.put(device.getId(), (SecurityCameraControl) device);
        }

        System.out.println("Registered device: " + device);
    }

    public Map<String, Device> getAllDevices() {
        return new HashMap<>(allDevices);
    }

    // --- Switchable Devices ---
    public void turnOnAll() {
        for (Switchable device : switchables.values()) {
            device.turnOn();
        }
    }

    public void turnOffAll() {
        for (Switchable device : switchables.values()) {
            device.turnOff();
        }
    }

    public void turnOn(String deviceId) {
        Switchable device = switchables.get(deviceId);
        if (device != null) {
            device.turnOn();
        } else if (allDevices.containsKey(deviceId)) {
            throw new IllegalArgumentException("Device is not switchable: " + deviceId);
        } else {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }
    }

    public void turnOff(String deviceId) {
        Switchable device = switchables.get(deviceId);
        if (device != null) {
            device.turnOff();
        } else {
            System.out.println("Device is not switchable: " + deviceId);
        }
    }

    // --- Lights ---
    public void setAllLightsBrightness(String deviceId, int brightness) {
        for (LightControl light : lights.values()) {
            light.setBrightness(deviceId, brightness);
        }
    }

    public void setAllLightsColor(String deviceId, String color) {
        for (LightControl light : lights.values()) {
            light.setColor(deviceId, color);
        }
    }

    public void setLightBrightness(String deviceId, int brightness) {
        LightControl light = lights.get(deviceId);
        if (light != null) {
            light.setBrightness(deviceId ,brightness);
        } else {
            System.out.println("Device is not a light: " + deviceId);
        }
    }

    public void setLightColor(String deviceId, String color) {
        LightControl light = lights.get(deviceId);
        if (light != null) {
            light.setColor(deviceId ,color);
        } else {
            System.out.println("Device is not a light: " + deviceId);
        }
    }

    // --- Thermostats ---
    public void setAllTemperatures(String deviceId, double temperature) {
        for (TemperatureControl thermostat : thermostats.values()) {
            thermostat.setTemperature(deviceId, temperature);
        }
    }

    public void setTemperature(String deviceId, double temperature) {
        TemperatureControl thermostat = thermostats.get(deviceId);
        if (thermostat != null) {
            thermostat.setTemperature(deviceId, temperature);
        } else if (allDevices.containsKey(deviceId)) {
            throw new IllegalArgumentException("Device is not a thermostat: " + deviceId);
        } else {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }
    }


    public void printCurrentTemperatures(String deviceId) {
        for (Map.Entry<String, TemperatureControl> entry : thermostats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getCurrentTemperature(deviceId) + "°C");
        }
    }

    // --- Cameras ---
    public void startAllCameras(String deviceId) {
        for (SecurityCameraControl camera : cameras.values()) {
            camera.startRecording(deviceId);
        }
    }

    public void stopAllCameras(String deviceId) {
        for (SecurityCameraControl camera : cameras.values()) {
            camera.stopRecording(deviceId);
        }
    }

    public void snapshotAllCameras(String deviceId) {
        for (SecurityCameraControl camera : cameras.values()) {
            camera.takeSnapshot(deviceId);
        }
    }

    public void startCamera(String deviceId) {
        SecurityCameraControl camera = cameras.get(deviceId);
        if (camera != null) {
            camera.startRecording(deviceId);
        } else if (allDevices.containsKey(deviceId)) {
            throw new IllegalArgumentException("Device is not a camera: " + deviceId);
        } else {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }
    }


    public void stopCamera(String deviceId) {
        SecurityCameraControl camera = cameras.get(deviceId);
        if (camera != null) {
            camera.stopRecording(deviceId);
        } else {
            System.out.println("Device is not a camera: " + deviceId);
        }
    }

    public void takeSnapshot(String deviceId) {
        SecurityCameraControl camera = cameras.get(deviceId);
        if (camera != null) {
            camera.takeSnapshot(deviceId);
        } else {
            System.out.println("Device is not a camera: " + deviceId);
        }
    }
}
