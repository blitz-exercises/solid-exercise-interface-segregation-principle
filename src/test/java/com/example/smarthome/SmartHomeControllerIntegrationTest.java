package com.example.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartHomeControllerIntegrationTest {

    private SmartHomeController controller;
    private Light livingRoomLight;
    private Thermostat bedroomThermostat;
    private Camera frontDoorCamera;

    @BeforeEach
    void setUp() {
        controller = new SmartHomeController();

        livingRoomLight = new Light("light-001", "Living Room Light");
        bedroomThermostat = new Thermostat("thermo-001", "Bedroom Thermostat");
        frontDoorCamera = new Camera("camera-001", "Front Door Camera");

        controller.registerDevice(livingRoomLight);
        controller.registerDevice(bedroomThermostat);
        controller.registerDevice(frontDoorCamera);
    }

    @Test
    void testLightControl() {
        controller.turnOn("light-001");
        assertTrue(livingRoomLight.isOn());

        controller.setLightBrightness("light-001", 75);
        assertEquals(75, livingRoomLight.getBrightness());

        controller.setLightColor("light-001", "warm white");
        assertEquals("warm white", livingRoomLight.getColor());

        controller.turnOff("light-001");
        assertFalse(livingRoomLight.isOn());
    }

    @Test
    void testTemperatureControl() {
        // Set target temperature
        controller.setTemperature("thermo-001", 22.5);
        assertEquals(22.5, bedroomThermostat.getTargetTemperature("thermo-001"), 0.01);

        // Get current temperature
        double currentTemp = bedroomThermostat.getCurrentTemperature("thermo-001");
        assertEquals(20.0, currentTemp, 0.01);

        // Update current temperature
        bedroomThermostat.setCurrentTemperature("thermo-001", 21.5);
        currentTemp = bedroomThermostat.getCurrentTemperature("thermo-001");
        assertEquals(21.5, currentTemp, 0.01);
    }

    @Test
    void testSecurityCameraControl() {
        assertFalse(frontDoorCamera.isRecording());

        controller.startCamera("camera-001");
        assertTrue(frontDoorCamera.isRecording());

        controller.takeSnapshot("camera-001");
        assertEquals(1, frontDoorCamera.getSnapshotCount());

        controller.takeSnapshot("camera-001");
        assertEquals(2, frontDoorCamera.getSnapshotCount());

        controller.stopCamera("camera-001");
        assertFalse(frontDoorCamera.isRecording());
    }

    @Test
    void testInvalidDeviceOperations() {
        assertThrows(IllegalArgumentException.class, () -> controller.turnOn("thermo-001"));
        assertThrows(IllegalArgumentException.class, () -> controller.setTemperature("light-001", 25.0));
        assertThrows(IllegalArgumentException.class, () -> controller.startCamera("thermo-001"));
    }

    @Test
    void testNonExistentDevice() {
        assertThrows(IllegalArgumentException.class, () -> controller.turnOn("non-existent"));
    }

    @Test
    void testMultipleDevices() {
        Light kitchenLight = new Light("light-002", "Kitchen Light");
        controller.registerDevice(kitchenLight);

        controller.turnOn("light-001");
        controller.turnOn("light-002");

        assertTrue(livingRoomLight.isOn());
        assertTrue(kitchenLight.isOn());

        controller.setLightBrightness("light-001", 50);
        controller.setLightBrightness("light-002", 80);

        assertEquals(50, livingRoomLight.getBrightness());
        assertEquals(80, kitchenLight.getBrightness());
    }

    @Test
    void testCompleteSmartHomeScenario() {
        controller.turnOn("light-001");
        controller.setLightBrightness("light-001", 60);
        controller.setTemperature("thermo-001", 21.0);
        controller.takeSnapshot("camera-001");

        assertTrue(livingRoomLight.isOn());
        assertEquals(60, livingRoomLight.getBrightness());
        assertEquals(21.0, bedroomThermostat.getTargetTemperature("thermo-001"), 0.01);

        controller.setLightBrightness("light-001", 30);
        controller.setLightColor("light-001", "warm white");
        controller.setTemperature("thermo-001", 19.0);
        controller.startCamera("camera-001");

        assertEquals(30, livingRoomLight.getBrightness());
        assertEquals("warm white", livingRoomLight.getColor());
        assertEquals(19.0, bedroomThermostat.getTargetTemperature("thermo-001"), 0.01);
        assertTrue(frontDoorCamera.isRecording());
    }
}
