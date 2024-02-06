# Camera Battery Model

This Java project models a removable and rechargeable camera battery, simulating its behavior in terms of charging, discharging, and external charging.

## Overview

The `CameraBattery` class represents a camera battery with the following features:
- **Camera Charge**: Stores the total charge in the camera.
- **Battery Charge**: Stores the total charge in the battery.
- **Battery Capacity**: Maximum capacity of the battery.
- **Charging Rate**: Constant rate of change for charging.
- **Camera Power Consumption**: Consumption rate of the camera.
- **Charger Setting**: External charger's setting.
- **Connection Status**: Indicates whether the battery is connected to the camera or external charger.

## Functionality

The `CameraBattery` class provides the following functionality:
- Charging the camera.
- Draining the battery.
- External charging of the battery.
- Switching between camera and external charger connections.
- Adjusting camera power consumption.
- Monitoring total battery drain.

## Usage

To use the `CameraBattery` class in your Java project, follow these steps:

1. Import the `CameraBattery.java` file into your project.
2. Create an instance of the `CameraBattery` class with the initial battery charge and capacity.
3. Use the provided methods to charge, drain, and monitor the battery.

```java
// Example usage
CameraBattery battery = new CameraBattery(50.0, 100.0);
battery.cameraCharge(10.0);
battery.drain(5.0);
double totalDrain = battery.getTotalDrain();
