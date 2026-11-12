# TASK 2 – Design an Object-Oriented Model for the Campus Smart-Parking System

Scenario: Campus Smart-Parking System

## 1. Introduction

The Campus Smart-Parking System is designed using Object-Oriented Programming (OOP) principles in Java. The system manages multiple parking floors, different vehicle categories, parking slots, tickets, monthly passes and parking fee calculations.

The design uses classes, objects, inheritance, encapsulation, abstraction and polymorphism to ensure that the system is easy to maintain and extend. It also follows SOLID design principles so that new vehicle categories and pricing rules can be introduced without rewriting existing classes.

## 2. Identify the Classes and Their Attributes and Methods

The following classes are proposed for the parking system.

| Class           | Attributes                                 | Methods                                          |
| --------------- | ------------------------------------------ | ------------------------------------------------ |
| Vehicle         | registrationNumber, vehicleType            | getRegistrationNumber(), getVehicleType()        |
| TwoWheeler      | registrationNumber                         | getVehicleType()                                 |
| Car             | registrationNumber                         | getVehicleType()                                 |
| ElectricVehicle | registrationNumber, chargingRequired       | getVehicleType(), requiresCharging()             |
| ParkingSlot     | slotNumber, slotType, occupied             | assignVehicle(), releaseVehicle(), isAvailable() |
| ParkingFloor    | floorNumber, slots                         | findAvailableSlot(), addSlot()                   |
| ParkingLot      | floors, parkingName                        | addFloor(), findSlot(), getAvailableSlots()      |
| ParkingTicket   | ticketNumber, vehicle, entryTime, exitTime | generateTicket(), closeTicket()                  |
| MonthlyPass     | passNumber, userType, expiryDate           | validatePass()                                   |
| FeeCalculator   | pricingStrategy                            | calculateFee()                                   |
| ParkingManager  | parkingLot, activeTickets                  | vehicleEntry(), vehicleExit()                    |

## 3. Class Relationships

The system uses the following object-oriented relationships.

1\. Inheritance

The Vehicle class acts as the parent class. TwoWheeler, Car and ElectricVehicle inherit common vehicle properties from it.

2\. Encapsulation

Attributes such as registration number, slot status and ticket number are declared private. Public getter and setter methods control access to these attributes.

3\. Abstraction

The Vehicle class can be designed as an abstract class. It defines common vehicle properties and requires subclasses to implement vehicle-specific behaviour.

4\. Polymorphism

Different vehicle subclasses implement the same `getVehicleType()` method according to their vehicle categories.

5\. Association

A ParkingTicket is associated with a Vehicle and a ParkingSlot because each ticket records which vehicle occupies which slot.

6\. Composition

A ParkingFloor contains multiple ParkingSlot objects. A ParkingLot contains multiple ParkingFloor objects.

## 4. UML Class Diagram

The following diagram represents the proposed object-oriented architecture.

[System Design for Parking lot](https://images.openai.com/static-rsc-4/zVl3b2Ju8RFsJvD4tvT1DD0f_8pFs9JSXlJsGN5P5EDgO90-iS1ju8ggo7nDewzivQ0zxXHm8033_4YCaoVL-qPD9hBzAynrUQCAhILYdT3Fl9z_e9TShIwnVpvHLtf92nUFTVV-I5mUCKq-yV5nRkz76KbQ_XCH-9M4njQPV0Y?purpose=inline)

[iq.opengenus.org](https://iq.opengenus.org/system-design-for-parking-lot/?utm_source=chatgpt.com)

### UML Relationship Representation

```
                    Vehicle (Abstract)
                           |
              +------------+------------+
              |            |            |
         TwoWheeler       Car     ElectricVehicle
                                      |
                               Charging Required

ParkingLot
    |
    | contains
    v
ParkingFloor
    |
    | contains
    v
ParkingSlot

ParkingTicket ------ Vehicle
      |
      +------------- ParkingSlot

ParkingManager ----- ParkingLot
      |
      +------------- ParkingTicket
      |
      +------------- MonthlyPass

FeeCalculator ------ PricingStrategy
                           |
                +----------+----------+
                |                     |
          StandardPricing       DiscountPricing
```

## 5. Object-Oriented Design Principles Applied

| OOP Principle | Implementation in the System                     | Advantage                                 |
| ------------- | ------------------------------------------------ | ----------------------------------------- |
| Encapsulation | Private vehicle and parking attributes           | Protects data from unauthorized access    |
| Inheritance   | Car, TwoWheeler and EV extend Vehicle            | Reduces code duplication                  |
| Polymorphism  | Different vehicle types implement common methods | Supports multiple vehicle categories      |
| Abstraction   | Abstract Vehicle and PricingStrategy interface   | Hides unnecessary implementation details  |
| Composition   | ParkingLot contains ParkingFloor objects         | Organizes the parking facility            |
| Association   | Ticket connects vehicle and parking slot         | Maintains parking transaction information |

## 6. Design for Future Extension

The system must support new vehicle categories and pricing rules without modifying existing working classes.

The following improvements are included:

1\. New Vehicle Categories

New vehicle types such as Electric Bicycle and Hybrid Vehicle can be introduced by extending the Vehicle class.

2\. Flexible Pricing Rules

A PricingStrategy interface can be used to implement different pricing methods, such as standard pricing, weekend pricing and premium parking pricing.

3\. Flexible Parking Slots

Different slot categories can be introduced for two-wheelers, cars, EVs and charging facilities.

4\. Monthly Pass Management

Separate validation logic can be implemented for students and staff to determine free parking eligibility.

## 7. Design Patterns Used

| Design Pattern               | Application                                           |
| ---------------------------- | ----------------------------------------------------- |
| Strategy Pattern             | Implements different parking fee calculation methods. |
| Factory Pattern              | Creates vehicle objects based on vehicle category.    |
| Singleton Pattern (optional) | Maintains a single ParkingManager instance.           |

Strategy Pattern Example:

```
interface PricingStrategy {
    double calculateFee(int hours);
}

class StandardPricing implements PricingStrategy {
    public double calculateFee(int hours) {
        return hours * 20;
    }
}

class WeekendPricing implements PricingStrategy {
    public double calculateFee(int hours) {
        return hours * 15;
    }
}
```

Here, new pricing strategies can be introduced by implementing the PricingStrategy interface without changing the existing pricing classes.

## 8. Design Validation

The proposed design must satisfy the following conditions:

1. A parking slot must not be allocated to multiple vehicles.
2. Each parking ticket must have a unique ticket number.
3. A vehicle must be allocated only to a compatible parking slot.
4. EVs requiring charging must receive charging-compatible slots.
5. Valid student and staff monthly passes must provide free parking.
6. Visitors must pay the applicable parking charges.
7. The parking slot must be released after successful vehicle exit.
8. New vehicle categories and pricing strategies must be supported without modifying existing classes.

## 9. Conclusion

1. The proposed object-oriented design divides the parking system into independent classes with clearly defined responsibilities.
2. Inheritance, encapsulation, abstraction and polymorphism improve code reusability and maintainability.
3. The Strategy Pattern supports flexible parking fee calculations, while inheritance allows new vehicle categories to be introduced.
4. The design is scalable and can support different facility configurations, including 1 floor with 20 slots, 3 floors with 150 slots and 5 floors with 500 slots.

T