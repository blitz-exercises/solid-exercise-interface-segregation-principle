# Smart Home Devices Exercise

## Overview

This exercise demonstrates a **Smart Home Controller** system that violates the Interface Segregation Principle. The current implementation forces all devices to implement methods they don't need (e.g., a simple switch must implement camera methods, a thermostat must implement lighting methods). You will first experience the problem firsthand by adding a new device, then refactor the code to follow ISP, and finally add a multi-capability device to see the difference.

## The Problem

The `SmartHomeService` interface violates ISP in several ways:

1. **Monolithic Interface**: A single interface combines methods for three different device capabilities (lighting, temperature, security cameras)

2. **Forced Implementations**: Devices are forced to implement methods they don't need (often throwing `UnsupportedOperationException`)

3. **Interface Pollution**: A simple device like a switch must have camera and temperature methods

4. **Tight Coupling**: Unrelated device capabilities are bundled together

### Example Violation

```java
public class Light extends Device {
    // Lighting methods (needed)
    public void turnOn() { ... }
    public void turnOff() { ... }
    
    // Temperature methods (not needed - violates ISP)
    public void setTemperature(double temperature) {
        throw new UnsupportedOperationException("Lights do not support temperature control");
    }
    
    // Security camera methods (not needed - violates ISP)
    public void startRecording() {
        throw new UnsupportedOperationException("Lights do not support recording");
    }
}
```

## Why This Is a Problem

1. **Interface Pollution**: Devices are forced to implement methods they don't need
2. **Maintenance Burden**: Changes to one capability affect all device types
3. **Code Clarity**: It's unclear which methods a device actually supports
4. **Extensibility**: Adding new device types requires implementing unnecessary methods
5. **Testing Complexity**: Need to mock/stub methods that aren't relevant
6. **Type Safety**: Can't distinguish between devices that support different capabilities

## Your Task

This exercise is divided into three phases to help you understand the Interface Segregation Principle:

### Phase 1: Experience the Problem (Before Refactoring)

**Add Smart Switch Device Support**

Add support for a new `SmartSwitch` device that only needs basic on/off functionality (no brightness, color, temperature, or camera features). This will help you experience firsthand what it takes to add a device when the system violates ISP.

**What you need to do:**

1. Create a new `SmartSwitch` class extending `Device`
2. Implement only `turnOn()` and `turnOff()` methods (the methods it actually needs)
3. Add all other methods from `SmartHomeService` with "not supported" exceptions:
   - `setBrightness()` - throw `UnsupportedOperationException`
   - `setColor()` - throw `UnsupportedOperationException`
   - `setTemperature()` - throw `UnsupportedOperationException`
   - `getCurrentTemperature()` - throw `UnsupportedOperationException`
   - `startRecording()` - throw `UnsupportedOperationException`
   - `stopRecording()` - throw `UnsupportedOperationException`
   - `takeSnapshot()` - throw `UnsupportedOperationException`
4. Update `SmartHomeController` to handle `SmartSwitch` in the `turnOnLight()` and `turnOffLight()` methods (add instanceof check)
5. Write a test to verify the switch can be turned on and off

**Take note of:**

- How many methods you added to `SmartSwitch` (should be 9 total - 2 needed, 7 unnecessary)
- How many methods throw exceptions (7 out of 9!)
- Whether you're touching existing device classes
- The frustration of implementing methods you know will never be used
- How unclear it is what capabilities `SmartSwitch` actually supports

**Expected Behavior:**

- `SmartSwitch` should be able to turn on and off
- Calling `setBrightness()`, `setColor()`, `setTemperature()`, etc. should throw `UnsupportedOperationException`
- The switch should be registered and controlled through `SmartHomeController`

**Note:** You'll need to modify `Light.java`, `Thermostat.java`, and `Camera.java` if they don't already have all these methods. However, for this learning exercise, we're showing what happens when you need to implement unnecessary methods.

### Phase 2: Refactor to Follow ISP

Now that you've experienced the problem, refactor the code to follow the Interface Segregation Principle:

1. **Create Segregated Interfaces**: Split the functionality into focused interfaces:
   - `Switchable` - basic on/off functionality (`turnOn()`, `turnOff()`)
   - `LightControl` - extends `Switchable` and adds lighting-specific methods (`setBrightness()`, `setColor()`)
   - `TemperatureControl` - temperature methods (`setTemperature()`, `getCurrentTemperature()`)
   - `SecurityCameraControl` - camera methods (`startRecording()`, `stopRecording()`, `takeSnapshot()`)

2. **Update Device Classes**: Make each device implement only the interfaces it needs:
   - `SmartSwitch` implements `Switchable`
   - `Light` implements `LightControl`
   - `Thermostat` implements `TemperatureControl`
   - `Camera` implements `SecurityCameraControl`

3. **Refactor `SmartHomeController`**:
   - Store devices by their capability interfaces (use a map or separate collections)
   - Use interfaces instead of `instanceof` checks where possible
   - Remove exception-throwing methods from device classes
   - Maintain the `SmartHomeService` contract

4. **Remove Unnecessary Methods**: Delete all the "not supported" exception methods from device classes

**Refactoring Steps:**

1. **Create `Switchable` Interface**

   ```java
   public interface Switchable {
       void turnOn();
       void turnOff();
   }
   ```

2. **Create `LightControl` Interface**

   ```java
   public interface LightControl extends Switchable {
       void setBrightness(int brightness);
       void setColor(String color);
   }
   ```

3. **Create `TemperatureControl` Interface**

   ```java
   public interface TemperatureControl {
       void setTemperature(double temperature);
       double getCurrentTemperature();
   }
   ```

4. **Create `SecurityCameraControl` Interface**

   ```java
   public interface SecurityCameraControl {
       void startRecording();
       void stopRecording();
       String takeSnapshot();
   }
   ```

5. **Update Device Classes**

   - Remove all exception-throwing methods
   - Implement only the interfaces each device needs
   - Keep only the methods that make sense for each device

6. **Refactor `SmartHomeController`**

   - Store devices by their interfaces (consider using separate maps or a composite approach)
   - Delegate to the appropriate interface based on the method being called
   - Remove `instanceof` checks where possible (use interface-based lookups instead)

### Phase 3: Experience the Solution (After Refactoring)

**Add Smart Display Device Support**

Now add support for a `SmartDisplay` device that combines lighting and camera capabilities. This time, you should be able to add the new device **without implementing any unnecessary methods** and clearly see what capabilities it supports.

**What you need to do:**

1. Create a new `SmartDisplay` class extending `Device`
2. Make it implement both `LightControl` and `SecurityCameraControl` interfaces
3. Implement only the methods from those interfaces (no exception methods needed!)
4. Update `SmartHomeController` to handle `SmartDisplay` for both lighting and camera operations
5. Write tests to verify the display supports both lighting and camera capabilities

**Take note of:**

- How many methods you added to `SmartDisplay` (should be exactly what it needs - no exceptions!)
- Whether you touched any existing device classes (should be NO!)
- How clear it is that `SmartDisplay` supports lighting AND camera (via interfaces)
- How easy it is to add a multi-capability device
- The reduced risk of breaking existing functionality

**Expected Behavior:**

- `SmartDisplay` should support all lighting methods (`turnOn()`, `turnOff()`, `setBrightness()`, `setColor()`)
- `SmartDisplay` should support all camera methods (`startRecording()`, `stopRecording()`, `takeSnapshot()`)
- No exception methods needed - only implement what it supports!
- Clear through interfaces what capabilities the device has

## What Must Remain Unchanged

The following files define the public API contract and must **NOT** be modified during Phase 2 and Phase 3:

- `SmartHomeService.java` - Interface defining the public API (must remain unchanged)
- `SmartHomeControllerIntegrationTest.java` - Integration tests verifying the contract
- `Device.java` - Base data class structure

**Note:** During Phase 1, you may need to add methods to existing device classes if they don't already have all the exception methods. In Phase 2, you should remove those exception methods and ensure your refactored solution maintains backward compatibility through the `SmartHomeService` interface.

## Expected Structure After Refactoring

```
src/main/java/com/example/smarthome/
├── SmartHomeService.java           # Interface (UNTOUCHED)
├── SmartHomeController.java        # Refactored - uses interfaces
├── Switchable.java                  # NEW - Basic on/off interface
├── LightControl.java                # NEW - Extends Switchable
├── TemperatureControl.java         # NEW - Temperature interface
├── SecurityCameraControl.java      # NEW - Camera interface
├── Device.java                      # Base class (UNCHANGED)
├── SmartSwitch.java                 # Implements Switchable (from Phase 1)
├── Light.java                       # Implements LightControl
├── Thermostat.java                 # Implements TemperatureControl
├── Camera.java                      # Implements SecurityCameraControl
└── SmartDisplay.java               # NEW - Implements LightControl + SecurityCameraControl (from Phase 3)
```

## Success Criteria

Your refactoring is successful when:

1. ✅ All existing tests pass without modification
2. ✅ `SmartSwitch` only has `turnOn()` and `turnOff()` methods (no exceptions!)
3. ✅ `SmartDisplay` implements multiple interfaces cleanly (demonstrated in Phase 3)
4. ✅ No "not supported" exception methods in any device class
5. ✅ Each device class implements only the interfaces it needs
6. ✅ The `SmartHomeService` interface contract is preserved
7. ✅ Code follows clean code principles (single responsibility, meaningful names)

## Evaluation

### Phase 1: Before Refactoring

- Adding `SmartSwitch`: Requires implementing 9 methods (only 2 needed), 7 throw exceptions
- Code clarity: Unclear what capabilities `SmartSwitch` supports
- Testing: Must handle exception cases for methods that shouldn't exist
- Risk: High - confusion about what methods are actually supported
- Lines of code: Many unnecessary exception methods

### Phase 3: After Refactoring

- Adding `SmartDisplay`: Implement `LightControl` + `SecurityCameraControl` - only methods it needs!
- Code clarity: Interfaces make capabilities explicit - clearly supports lighting AND camera
- Testing: Only test methods that actually exist
- Risk: Low - clear contract through interfaces
- Lines of code: Only implement what's needed, no exceptions

## Benefits of Following ISP

After refactoring, you'll achieve:

1. **Focused Interfaces**: Each interface has a single, clear purpose
2. **Better Encapsulation**: Devices only expose methods they support
3. **Easier Testing**: Mock only the interfaces you need
4. **Improved Readability**: Clear which capabilities each device has through interfaces
5. **Flexible Design**: Easy to add new device types with any combination of capabilities
6. **Reduced Coupling**: Changes to one capability don't affect others

## Getting Started

### Phase 1: Add Smart Switch (Experience the Problem)

1. **Review the Current Code**

   - Read `SmartHomeService.java` and understand all the methods
   - Examine `Light.java`, `Thermostat.java`, and `Camera.java` to see the exception methods
   - Run tests: `mvn test -Dtest=SmartHomeControllerIntegrationTest`
   - Notice how many methods each device has vs. what it actually needs

2. **Add Smart Switch Support**

   - Create `SmartSwitch.java` extending `Device`
   - Implement `turnOn()` and `turnOff()` (the methods it needs)
   - Add all other methods throwing `UnsupportedOperationException`
   - Update `SmartHomeController` to handle switches in lighting methods
   - Write a test for the switch

3. **Reflect on the Experience**

   - Count: How many methods did you add? How many are actually needed?
   - Notice: How frustrating it is to implement methods you know won't be used
   - Question: Why does a simple switch need camera methods?

### Phase 2: Refactor to Follow ISP

1. **Plan Your Refactoring**

   - Identify all device capabilities (switchable, lighting, temperature, camera)
   - Design the interface hierarchy (`Switchable` as base, `LightControl` extends it)
   - Plan how to store devices by their interfaces in the controller

2. **Implement Step by Step**

   - Create interfaces first (`Switchable`, `LightControl`, `TemperatureControl`, `SecurityCameraControl`)
   - Update one device class at a time (start with `SmartSwitch` - it's simplest!)
   - Refactor `SmartHomeController` gradually
   - Run tests frequently to ensure nothing breaks
   - Remove exception methods as you go

3. **Verify Success**

   - All tests pass
   - No exception methods remain
   - Code is cleaner and more maintainable

### Phase 3: Add Smart Display (Experience the Solution)

1. **Add Smart Display Support**

   - Create `SmartDisplay.java` extending `Device`
   - Make it implement both `LightControl` and `SecurityCameraControl`
   - Implement only the methods from those interfaces (no exceptions!)
   - Update `SmartHomeController` to handle display for both capabilities
   - Write tests for both lighting and camera features

2. **Compare with Phase 1**

   - Did you add any exception methods? (Should be NO!)
   - How many methods does `SmartDisplay` have vs. `SmartSwitch` in Phase 1?
   - How clear is it that the display supports lighting AND camera?
   - How much easier was it to add this multi-capability device?

## Running Tests

```bash
# Run all tests
mvn test

# Run only Smart Home tests
mvn test -Dtest=SmartHomeControllerIntegrationTest

# Run with verbose output
mvn test -Dtest=SmartHomeControllerIntegrationTest -X
```

## Notes

- The initial code works correctly - your goal is to improve design, not fix bugs
- Focus on ISP, but maintain good coding practices (naming, structure, etc.)
- Consider edge cases (devices not found, invalid operations, etc.)
- Use logging appropriately (avoid System.out.println)
- In Phase 1, you'll add exception methods - this is intentional to show the problem
- In Phase 2, remove all exception methods and use interfaces instead
- The `SmartHomeService` interface must remain unchanged - it's the public contract
- Interfaces can extend other interfaces - use this for `LightControl extends Switchable`

---

**Good luck with your refactoring!** 🏠💡
