# TASK 6 – Design the Inheritance Hierarchy

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Inheritance is an important concept in Java Object-Oriented Programming (OOP). It allows one class to acquire the properties and methods of another class.

In the Campus Smart-Parking System, different types of vehicles share common properties such as registration number and vehicle details. Instead of writing separate code for each vehicle, a common parent class called `Vehicle` is created.

The subclasses `TwoWheeler`, `Car` and `ElectricCar` inherit the common properties and methods from the `Vehicle` class.

This reduces code duplication and improves code reusability.

## 2. Inheritance Hierarchy

The following inheritance hierarchy is designed for the parking system.

Vehicle

Registration number, vehicle details

TwoWheeler

Two-wheeler rate

Car

Car rate

ElectricCar

Charging capability

ElectricCar extends Car and implements Chargeable

### Types of Inheritance Used

1\. Single Inheritance

A subclass inherits from one superclass.

Example: `Car` inherits from `Vehicle`.

2\. Hierarchical Inheritance

Multiple subclasses inherit from the same superclass.

Example: `TwoWheeler` and `Car` inherit from `Vehicle`.

3\. Multilevel Inheritance

One class inherits from another subclass.

Example: `ElectricCar` inherits from `Car`, and `Car` inherits from `Vehicle`.

## 3. Superclass and Subclasses

| Class       | Inherited Properties       | Additional Properties    |
| ----------- | -------------------------- | ------------------------ |
| Vehicle     | Base class                 | Registration number      |
| TwoWheeler  | Vehicle properties         | Two-wheeler parking rate |
| Car         | Vehicle properties         | Car parking rate         |
| ElectricCar | Vehicle and Car properties | Charging facility        |

### Superclass – Vehicle

The `Vehicle` class acts as the parent class. It contains common vehicle information such as registration number and methods for calculating parking fees.

### Subclass – TwoWheeler

The `TwoWheeler` class inherits from Vehicle and provides the parking rate for two-wheelers.

### Subclass – Car

The `Car` class inherits from Vehicle and provides the parking rate for cars.

### Subclass – ElectricCar

The `ElectricCar` class inherits from Car and implements the charging functionality using an interface.

## 4. Use of super Keyword

The `super` keyword is used to refer to the immediate parent class.

It is mainly used for three purposes:

1. Calling Parent Constructor: `super()` calls the constructor of the parent class.
2. Accessing Parent Methods: `super.methodName()` calls the parent class method.
3. Accessing Parent Variables: `super.variableName` accesses an accessible parent class variable.

Example:

```
class Vehicle {

    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }
}
```

Here, `super(regNo)` calls the constructor of the Vehicle class and initializes the registration number.

## 5. Constructor Execution Order

When an ElectricCar object is created, the constructors execute from the topmost parent class to the child class.

Example:

```
class Vehicle {

    Vehicle(String regNo) {
        System.out.println("Vehicle Constructor");
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
        System.out.println("Car Constructor");
    }
}

class ElectricCar extends Car {

    ElectricCar(String regNo) {
        super(regNo);
        System.out.println("ElectricCar Constructor");
    }

    public static void main(String[] args) {

        ElectricCar e =
            new ElectricCar("TN01AB1111");
    }
}
```

Output:

```
Vehicle Constructor
Car Constructor
ElectricCar Constructor
```

Explanation:

1. First, the Vehicle constructor executes.
2. Next, the Car constructor executes.
3. Finally, the ElectricCar constructor executes.

This demonstrates the order of constructor execution in multilevel inheritance.

## 6. Complete Java Program for Inheritance

File name: VehicleInheritance.java

```

class Vehicle {

    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    void display() {
        System.out.println("Registration Number: " + regNo);
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    void showRate() {
        System.out.println("Parking Rate: Rs. 10 per hour");
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    void showRate() {
        System.out.println("Parking Rate: Rs. 30 per hour");
    }
}

class ElectricCar extends Car {

    ElectricCar(String regNo) {
        super(regNo);
    }

    void chargingFacility() {
        System.out.println("Charging Facility Available");
    }
}

public class VehicleInheritance {

    public static void main(String[] args) {

        TwoWheeler bike =
            new TwoWheeler("TN01AB1111");

        Car car =
            new Car("TN02CD2222");

        ElectricCar ev =
            new ElectricCar("TN03EF3333");

        System.out.println("Two Wheeler Details:");
        bike.display();
        bike.showRate();

        System.out.println("\nCar Details:");
        car.display();
        car.showRate();

        System.out.println("\nElectric Car Details:");
        ev.display();
        ev.showRate();
        ev.chargingFacility();
    }
}

```

## 7. Expected Output

```
Two Wheeler Details:
Registration Number: TN01AB1111
Parking Rate: Rs. 10 per hour

Car Details:
Registration Number: TN02CD2222
Parking Rate: Rs. 30 per hour

Electric Car Details:
Registration Number: TN03EF3333
Parking Rate: Rs. 30 per hour
Charging Facility Available
```

## 8. Alternative Concept and Complexity Analysis

Alternative: Using Composition Instead of Inheritance

Instead of creating separate subclasses for different vehicle types, the system can use composition.

Composition means creating a class that contains an object of another class.

For example, a single `Vehicle` class can contain a `VehicleType` object that stores the vehicle category and parking rate.

### Example Java Program

```
class VehicleType {

    String type;
    double rate;

    VehicleType(String type, double rate) {
        this.type = type;
        this.rate = rate;
    }
}

class Vehicle {

    String regNo;
    VehicleType vehicleType;

    Vehicle(String regNo, VehicleType vehicleType) {
        this.regNo = regNo;
        this.vehicleType = vehicleType;
    }

    double calculateFee(int hours) {
        return hours * vehicleType.rate;
    }

    void display() {
        System.out.println("Registration: " + regNo);
        System.out.println("Vehicle Type: " + vehicleType.type);
        System.out.println("Parking Fee: Rs. " +
            calculateFee(2));
    }
}

public class CompositionDemo {

    public static void main(String[] args) {

        VehicleType bikeType =
            new VehicleType("TwoWheeler", 10);

        VehicleType carType =
            new VehicleType("Car", 30);

        Vehicle bike =
            new Vehicle("TN01AB1111", bikeType);

        Vehicle car =
            new Vehicle("TN02CD2222", carType);

        bike.display();
        car.display();
    }
}
```

Output:

```
Registration: TN01AB1111
Vehicle Type: TwoWheeler
Parking Fee: Rs. 20.0
Registration: TN02CD2222
Vehicle Type: Car
Parking Fee: Rs. 60.0
```

### Comparison Between Inheritance and Composition

| Feature             | Inheritance                      | Composition                             |
| ------------------- | -------------------------------- | --------------------------------------- |
| Relationship        | Is-a relationship                | Has-a relationship                      |
| Structure           | Parent and child classes         | Objects contained within another object |
| Adding vehicle type | Create a new subclass            | Create a new VehicleType object         |
| Code reusability    | Through inheritance              | Through object composition              |
| Flexibility         | Less flexible for changing types | More flexible                           |
| Maintenance         | May require subclass changes     | Centralized type configuration          |

### Time and Space Complexity

Let k represent the number of vehicle types.

| Operation             | Inheritance                                         | Composition                                  |
| --------------------- | --------------------------------------------------- | -------------------------------------------- |
| Fee calculation       | O(1)                                                | O(1)                                         |
| Adding a vehicle type | O(1) development-time configuration with a subclass | O(1) configuration using a new type object   |
| Vehicle storage       | O(1) per vehicle, excluding shared data             | O(1) per vehicle, excluding shared type data |

Both approaches calculate the parking fee in constant time because accessing a rate and multiplying it by the parking duration requires a fixed number of operations.

### Advantages of Composition

1. New vehicle categories can be introduced without creating many subclasses.
2. Vehicle types and rates can be changed dynamically.
3. It reduces dependency on a fixed inheritance hierarchy.
4. It improves flexibility when parking rules change.

## 9. Conclusion

Inheritance helps organize different vehicle categories in the Campus Smart-Parking System. The Vehicle class provides common properties, while TwoWheeler, Car and ElectricCar provide specialized behaviour.

The `super` keyword allows subclasses to access parent constructors and methods. Constructor execution follows a top-to-bottom order from the parent class to the child class.

Composition is an alternative approach that provides flexibility when adding new vehicle types. Both approaches have constant-time fee calculation, but composition can make future vehicle category changes easier.