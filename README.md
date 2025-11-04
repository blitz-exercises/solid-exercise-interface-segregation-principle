# SOLID Exercise: Interface Segregation Principle (ISP)

## Overview

This repository contains educational exercises for learning the **Interface Segregation Principle (ISP)**, one of the five SOLID principles of object-oriented design.

## What is the Interface Segregation Principle?

The Interface Segregation Principle states:

> **Clients should not be forced to depend on interfaces they don't use.**

In other words, it's better to have many small, focused interfaces than one large, monolithic interface. When a class implements an interface, it should not be forced to implement methods it doesn't need.

### Why ISP Matters

- **Reduces coupling**: Classes only depend on methods they actually use
- **Improves maintainability**: Changes to one interface don't affect unrelated classes
- **Prevents interface pollution**: Avoids forcing implementers to provide empty/throwaway implementations
- **Better code clarity**: Smaller interfaces are easier to understand and document
- **Facilitates testing**: Mocking smaller interfaces is simpler

### Common Violations

- A large interface with many unrelated methods
- Classes implementing methods they don't need (often with empty implementations or throwing exceptions)
- Clients depending on interfaces with methods they never call
- Difficulty extending functionality without affecting existing implementations

## Learning Objectives

By completing these exercises, you will:

1. Identify ISP violations in code
2. Understand when and why to split large interfaces
3. Refactor code to follow ISP while maintaining functionality
4. Recognize the benefits of smaller, focused interfaces
5. Learn to design interfaces that are cohesive and purposeful

## Exercises

### [Smart Home Devices Exercise](./smart-home-exercise.md)

A smart home controller interface that forces all devices to implement methods for lighting, temperature control, and security cameras, even when they only need some of these capabilities.

**Domain**: Smart Home Automation  
**Violation**: Lights don't need camera methods, thermostats don't need lighting methods

---

### Suggested Additional Exercises

#### Multi-Media Player Exercise
A media player interface combining audio playback, video playback, streaming, and downloading. A simple MP3 player shouldn't need video or streaming methods.

**Domain**: Media Players  
**Violation**: Audio players forced to implement video/streaming methods

#### Employee Management System Exercise
An employee interface with methods for hourly workers, salaried employees, managers, and contractors. Different employee types don't need all methods.

**Domain**: HR/Employee Management  
**Violation**: Contractors don't need salary management, interns don't need management methods

#### Office Equipment Exercise
A device interface combining printer, scanner, and fax capabilities. A simple printer shouldn't need scanning or faxing methods.

**Domain**: Office Equipment  
**Violation**: Printers forced to implement scanning/faxing methods

#### Social Media Platform Exercise
A social media interface with posting, commenting, messaging, sharing, and live streaming. Different user types (readers, creators, moderators) don't need all features.

**Domain**: Social Media  
**Violation**: Read-only users forced to implement posting/streaming methods

## Project Structure

```
project-root/
├── README.md                    # This file
├── pom.xml                      # Maven configuration
├── {exercise-name}-exercise.md  # Exercise-specific instructions
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── {exercise-package}/
    │                   ├── {MainClass}.java        # Violates ISP (intentionally)
    │                   ├── {MainClass}Service.java # Interface (UNTOUCHED contract)
    │                   └── {DataClasses}.java      # Data classes (unchanged)
    └── test/
        └── java/
            └── com/
                └── example/
                    └── {exercise-package}/
                        └── {MainClass}IntegrationTest.java  # Tests (UNTOUCHED)
```

## How to Run Tests

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Running All Tests

```bash
mvn test
```

### Running Tests for a Specific Exercise

```bash
mvn test -Dtest={MainClass}IntegrationTest
```

### Compiling the Project

```bash
mvn compile
```

## Key Design Principles

### Contract Immutability

The service interface (`{MainClass}Service.java`) and integration tests (`{MainClass}IntegrationTest.java`) must **never change** during refactoring exercises. They define the public API contract that must be maintained.

### Intentional Violations

The main class (`{MainClass}.java`) intentionally violates ISP to demonstrate the problem. Your task is to refactor it while maintaining the same functionality.

### Working Code

All code works correctly before refactoring. The violation is a design issue, not a functional bug.

## Getting Started

1. Choose an exercise from the list above
2. Read the exercise-specific documentation (`{exercise-name}-exercise.md`)
3. Study the code to understand the ISP violation
4. Refactor the code following ISP while keeping tests passing
5. Verify your solution maintains the public API contract

## Success Criteria

- All tests pass (`mvn test`)
- The public API contract remains unchanged
- Code follows Interface Segregation Principle
- No empty method implementations or "not supported" exceptions
- Smaller, focused interfaces replace the large interface
- Clients only depend on interfaces they actually use

## Additional Resources

- [SOLID Principles](https://en.wikipedia.org/wiki/SOLID)
- [Interface Segregation Principle](https://en.wikipedia.org/wiki/Interface_segregation_principle)
- [Clean Code by Robert C. Martin](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)

---

**Happy Learning!** 🎓

