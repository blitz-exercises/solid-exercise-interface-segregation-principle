package com.example.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the complete smart home flow.
 * 
 * IMPORTANT: This test class should remain UNTOUCHED during refactoring exercises. The
 * SmartHomeService interface defines the public API contract that must be maintained.
 */
public class SmartHomeControllerIntegrationTest {

    private SmartHomeController controller;
    private Light livingRoomLight;
    private Thermostat bedroomThermostat;
    private Camera frontDoorCamera;
    private SmartSwitch hallwaySwitch;

    @BeforeEach
    void setUp() {
        controller = new SmartHomeController();

        livingRoomLight = new Light("light-001", "Living Room Light");
        bedroomThermostat = new Thermostat("thermo-001", "Bedroom Thermostat");
        frontDoorCamera = new Camera("camera-001", "Front Door Camera");
        hallwaySwitch = new SmartSwitch("switch-001", "Hallway Switch");

        controller.registerDevice(livingRoomLight);
        controller.registerDevice(bedroomThermostat);
        controller.registerDevice(frontDoorCamera);
        controller.registerDevice(hallwaySwitch);
    }

    @Test
    void testLightControl() {
        // Test turning light on
        controller.turnOnLight("light-001");
        assertTrue(livingRoomLight.isOn(), "Light should be on");

        // Test setting brightness
        controller.setBrightness("light-001", 75);
        assertEquals(75, livingRoomLight.getBrightness(), "Brightness should be 75");

        // Test setting color
        controller.setColor("light-001", "warm white");
        assertEquals("warm white", livingRoomLight.getColor(), "Color should be warm white");

        // Test turning light off
        controller.turnOffLight("light-001");
        assertFalse(livingRoomLight.isOn(), "Light should be off");
    }

    @Test
    void testTemperatureControl() {
        // Test setting temperature
        controller.setTemperature("thermo-001", 22.5);
        assertEquals(22.5, bedroomThermostat.getTargetTemperature(), 0.01,
                "Target temperature should be 22.5°C");

        // Test getting current temperature
        double currentTemp = controller.getCurrentTemperature("thermo-001");
        assertEquals(20.0, currentTemp, 0.01, "Current temperature should be 20.0°C");

        // Update current temperature
        bedroomThermostat.setCurrentTemperature(21.5);
        currentTemp = controller.getCurrentTemperature("thermo-001");
        assertEquals(21.5, currentTemp, 0.01, "Current temperature should be 21.5°C");
    }

    @Test
    void testSecurityCameraControl() {
        // Test starting recording
        assertFalse(frontDoorCamera.isRecording(), "Camera should not be recording initially");
        controller.startRecording("camera-001");
        assertTrue(frontDoorCamera.isRecording(), "Camera should be recording");

        // Test taking snapshot
        String snapshot1 = controller.takeSnapshot("camera-001");
        assertNotNull(snapshot1, "Snapshot should not be null");
        assertTrue(snapshot1.contains("camera-001"), "Snapshot should contain device ID");
        assertEquals(1, frontDoorCamera.getSnapshotCount(), "Snapshot count should be 1");

        String snapshot2 = controller.takeSnapshot("camera-001");
        assertEquals(2, frontDoorCamera.getSnapshotCount(), "Snapshot count should be 2");

        // Test stopping recording
        controller.stopRecording("camera-001");
        assertFalse(frontDoorCamera.isRecording(), "Camera should not be recording");
    }

    @Test
    void testInvalidDeviceOperations() {
        // Try to use light methods on thermostat
        assertThrows(IllegalArgumentException.class, () -> controller.turnOnLight("thermo-001"),
                "Should throw exception when using light method on thermostat");

        // Try to use temperature methods on light
        assertThrows(IllegalArgumentException.class,
                () -> controller.setTemperature("light-001", 25.0),
                "Should throw exception when using temperature method on light");

        // Try to use camera methods on thermostat
        assertThrows(IllegalArgumentException.class, () -> controller.startRecording("thermo-001"),
                "Should throw exception when using camera method on thermostat");
    }

    @Test
    void testNonExistentDevice() {
        assertThrows(IllegalArgumentException.class, () -> controller.turnOnLight("non-existent"),
                "Should throw exception for non-existent device");
    }

    @Test
    void testMultipleDevices() {
        // Add another light
        Light kitchenLight = new Light("light-002", "Kitchen Light");
        controller.registerDevice(kitchenLight);

        // Control both lights independently
        controller.turnOnLight("light-001");
        controller.turnOnLight("light-002");

        assertTrue(livingRoomLight.isOn(), "Living room light should be on");
        assertTrue(kitchenLight.isOn(), "Kitchen light should be on");

        // Set different brightness levels
        controller.setBrightness("light-001", 50);
        controller.setBrightness("light-002", 80);

        assertEquals(50, livingRoomLight.getBrightness(), "Living room brightness should be 50");
        assertEquals(80, kitchenLight.getBrightness(), "Kitchen brightness should be 80");
    }

    @Test
    void testCompleteSmartHomeScenario() {
        // Morning routine: turn on lights, adjust temperature, check camera
        controller.turnOnLight("light-001");
        controller.setBrightness("light-001", 60);
        controller.setTemperature("thermo-001", 21.0);
        String morningSnapshot = controller.takeSnapshot("camera-001");

        assertTrue(livingRoomLight.isOn(), "Light should be on");
        assertEquals(60, livingRoomLight.getBrightness(), "Brightness should be 60");
        assertEquals(21.0, bedroomThermostat.getTargetTemperature(), 0.01,
                "Temperature should be set to 21.0°C");
        assertNotNull(morningSnapshot, "Snapshot should be taken");

        // Evening routine: dim lights, lower temperature, start camera recording
        controller.setBrightness("light-001", 30);
        controller.setColor("light-001", "warm white");
        controller.setTemperature("thermo-001", 19.0);
        controller.startRecording("camera-001");

        assertEquals(30, livingRoomLight.getBrightness(), "Brightness should be dimmed to 30");
        assertEquals("warm white", livingRoomLight.getColor(), "Color should be warm white");
        assertEquals(19.0, bedroomThermostat.getTargetTemperature(), 0.01,
                "Temperature should be lowered to 19.0°C");
        assertTrue(frontDoorCamera.isRecording(), "Camera should be recording");
    }
}

