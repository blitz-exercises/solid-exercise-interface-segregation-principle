package com.example.smarthome;

/**
 * Phase 1: Smart switch that was added to the old API style. It extends Device and exposes
 * turnOn/turnOff, but because the system expects all device methods, we include the other methods
 * as UnsupportedOperationException to mimic the current problem.
 */
public class SmartSwitch extends Device implements Switchable {
    private boolean isOn;

    public SmartSwitch(String id, String name) {
        super(id, name, "SWITCH");
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        System.out.println("Switch " + getId() + " turned on");
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        System.out.println("Switch " + getId() + " turned off");
    }

    public boolean isOn() {
        return isOn;
    }
}
