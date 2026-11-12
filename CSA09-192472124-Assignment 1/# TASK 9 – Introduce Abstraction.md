# TASK 9 – Introduce Abstraction

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Abstraction is an important concept in Java Object-Oriented Programming. It is used to hide implementation details and display only essential features to the user.

In the Campus Smart-Parking System, abstraction helps manage different types of vehicles without exposing their internal fee calculation methods.

Java supports abstraction using:

1. Abstract classes.
2. Interfaces.

In this system, `Vehicle` is designed as an abstract class, and `Chargeable` is designed as an interface for electric vehicles.

## 2. Abstract Class – Vehicle

An abstract class is a class declared using the `abstract` keyword. It cannot be instantiated directly.

The `Vehicle` class is designed as an abstract class because all vehicles share common properties, such as registration number, but their parking rates may differ.

Responsibilities:

- Store vehicle registration number.
- Provide a common constructor.
- Define an abstract method for parking rate calculation.
- Provide a common parking fee calculation method.

Example:

```
abstract class Vehicle {

    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    abstract double ratePerHour();

    double calculateFee(int hours) {
        return hours * ratePerHour();
    }
}
```

Here, `ratePerHour()` is an abstract method that must be implemented by subclasses.

## 3. Interface – Chargeable

An interface defines a capability that a class can implement.

In the parking system, electric vehicles require charging facilities. Therefore, the `Chargeable` interface is used to represent the charging capability.

Example:

```
interface Chargeable {

    double chargingFee(int kWh);
}
```

The ElectricCar class implements this interface and provides its own charging fee calculation.

Example:

```
class ElectricCar extends Vehicle implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    double ratePerHour() {
        return 30;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12;
    }
}
```

## 4. Difference Between Abstract Class and Interface

| Abstract Class                            | Interface                                        |
| ----------------------------------------- | ------------------------------------------------ |
| Declared using `abstract` keyword         | Declared using `interface` keyword               |
| Can have constructors                     | Cannot have constructors                         |
| Can contain instance variables            | Fields are public, static and final by default   |
| Can contain abstract and concrete methods | Can contain abstract, default and static methods |
| A class can extend only one class         | A class can implement multiple interfaces        |
| Used for common identity and shared state | Used to define common capabilities               |

Why is an abstract class used for Vehicle?

All vehicles share common information such as registration number. An abstract class allows this shared data and common implementation to be stored in one place.

Why is an interface used for Chargeable?

Not all vehicles require charging facilities. An interface allows only vehicles that support charging to implement the charging functionality.

## 5. Multiple Behaviours Through Interfaces

Java does not support multiple inheritance through classes. However, a class can implement multiple interfaces.

For example, an electric vehicle may support both charging and digital payment.

```
interface Chargeable {
    double chargingFee(int kWh);
}

interface Payable {
    void makePayment(double amount);
}

class ElectricCar implements Chargeable, Payable {

    public double chargingFee(int kWh) {
        return kWh * 12;
    }

    public void makePayment(double amount) {
        System.out.println("Payment: Rs. " + amount);
    }
}
```

This allows ElectricCar to support multiple capabilities.

## 6. Default and Static Methods in Interfaces

Java interfaces can contain default and static methods.

A. Default Method

A default method provides a common implementation that implementing classes can inherit or override.

```
interface Chargeable {

    double chargingFee(int kWh);

    default void chargingStatus() {
        System.out.println("Charging facility available");
    }
}
```

B. Static Method

A static method belongs to the interface itself and can be called using the interface name.

```
interface Chargeable {

    static void displayChargingRules() {
        System.out.println("Charging rate: Rs. 12 per kWh");
    }
}
```

It can be called as:

```
Chargeable.displayChargingRules();
```

These methods help provide reusable functionality.

## 7. Complete Java Program for Abstraction

File name: AbstractionDemo.java

```

interface Chargeable {

    double chargingFee(int kWh);

    default void chargingStatus() {
        System.out.println("Charging facility available");
    }

    static void displayRules() {
        System.out.println("Charging Rate: Rs. 12 per kWh");
    }
}

abstract class Vehicle {

    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    abstract double ratePerHour();

    double calculateFee(int hours) {
        return hours * ratePerHour();
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    double ratePerHour() {
        return 30;
    }
}

class ElectricCar extends Vehicle implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    double ratePerHour() {
        return 30;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12;
    }
}

public class AbstractionDemo {

    public static void main(String[] args) {

        Vehicle bike = new TwoWheeler("TN01AB1111");
        Vehicle car = new Car("TN02CD2222");

        ElectricCar ev =
            new ElectricCar("TN03EF3333");

        System.out.println("Two Wheeler Fee: Rs. " +
            bike.calculateFee(2));

        System.out.println("Car Fee: Rs. " +
            car.calculateFee(2));

        System.out.println("Electric Car Parking Fee: Rs. " +
            ev.calculateFee(2));

        System.out.println("Electric Car Charging Fee: Rs. " +
            ev.chargingFee(10));

        ev.chargingStatus();

        Chargeable.displayRules();
    }
}

```

## 8. Expected Output

```
Two Wheeler Fee: Rs. 20.0
Car Fee: Rs. 60.0
Electric Car Parking Fee: Rs. 60.0
Electric Car Charging Fee: Rs. 120.0
Charging facility available
Charging Rate: Rs. 12 per kWh
```

Output Explanation:

1. TwoWheeler calculates its parking fee at Rs. 10 per hour.
2. Car calculates its parking fee at Rs. 30 per hour.
3. ElectricCar calculates its parking fee at Rs. 30 per hour.
4. The charging fee is calculated at Rs. 12 per kWh.
5. The default interface method displays the charging status.
6. The static interface method displays the charging rate.

## 9. Alternative Concept and Complexity Analysis

Alternative: Changing Vehicle into an Interface and Chargeable into an Abstract Class

In the original design:

- Vehicle is an abstract class.
- Chargeable is an interface.

In the alternative design:

- Vehicle becomes an interface.
- Chargeable becomes an abstract class.

### Example Alternative Code

```
interface Vehicle {

    double ratePerHour();

    default double calculateFee(int hours) {
        return hours * ratePerHour();
    }
}

abstract class Chargeable {

    protected int chargingCapacity;

    Chargeable(int chargingCapacity) {
        this.chargingCapacity = chargingCapacity;
    }

    abstract double chargingFee(int kWh);
}

class ElectricCar extends Chargeable implements Vehicle {

    ElectricCar(int capacity) {
        super(capacity);
    }

    public double ratePerHour() {
        return 30;
    }

    public double chargingFee(int kWh) {
        return kWh * 12;
    }
}
```

### Comparison

| Feature               | Original Design                        | Alternative Design                                      |
| --------------------- | -------------------------------------- | ------------------------------------------------------- |
| Vehicle               | Abstract class                         | Interface                                               |
| Chargeable            | Interface                              | Abstract class                                          |
| Constructors          | Available in Vehicle                   | Available in Chargeable                                 |
| Common instance state | Stored in Vehicle                      | Cannot be stored in Vehicle interface                   |
| Multiple inheritance  | Multiple interfaces can be implemented | ElectricCar can implement Vehicle and extend Chargeable |
| Flexibility           | Supports shared vehicle state          | Supports multiple vehicle contracts                     |

### What Can No Longer Be Expressed?

When Vehicle becomes an interface:

1. It cannot have normal instance variables.
2. It cannot have constructors.
3. It cannot directly initialize common object state.
4. Common implementation must be provided through default or static methods.
5. A class cannot extend multiple abstract classes.

Therefore, the common registration number must be stored separately in each implementing class or in another superclass.

### Time and Space Complexity Analysis

| Operation                | Original Design | Alternative Design |
| ------------------------ | --------------- | ------------------ |
| Parking fee calculation  | O(1)            | O(1)               |
| Charging fee calculation | O(1)            | O(1)               |
| Object creation          | O(1)            | O(1)               |
| Space per vehicle        | O(1)            | O(1)               |

Both approaches have constant-time method execution for these operations. The main difference is the organization of data and class relationships, not execution time.

## 10. Conclusion

Abstraction helps simplify the Campus Smart-Parking System by hiding internal implementation details. The Vehicle abstract class stores common vehicle information and defines methods that subclasses must implement.

The Chargeable interface provides charging functionality for electric vehicles. Default and static interface methods improve code reusability.

The alternative design using Vehicle as an interface and Chargeable as an abstract class is possible, but it makes common vehicle state management more complicated. Therefore, the original design is more suitable because it provides shared data through an abstract class and additional capabilities through interfaces.