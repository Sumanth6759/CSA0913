# TASK 8 – Apply Polymorphism

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Polymorphism is an important concept in Java Object-Oriented Programming. The word polymorphism means "many forms". It allows a single method or object reference to perform different behaviours depending on the situation.

In the Campus Smart-Parking System, different vehicles such as TwoWheelers, Cars and ElectricCars have different parking fees. Polymorphism allows the system to calculate the correct parking fee without writing separate conditions for every vehicle type.

Java supports two main types of polymorphism:

1. Compile-time polymorphism.
2. Runtime polymorphism.

## 2. Compile-Time Polymorphism (Method Overloading)

Compile-time polymorphism is achieved through method overloading.

Method overloading means defining multiple methods with the same name but different parameter lists.

Example:

```
class ParkingFee {

    double calculateFee(int minutes) {
        int hours = (minutes + 59) / 60;
        return hours * 30;
    }

    double calculateFee(int hours, int rate) {
        return hours * rate;
    }

    public static void main(String[] args) {

        ParkingFee p = new ParkingFee();

        System.out.println(
            "Fee for 130 minutes: Rs. " +
            p.calculateFee(130)
        );

        System.out.println(
            "Fee for 3 hours: Rs. " +
            p.calculateFee(3, 30)
        );
    }
}
```

Output:

```
Fee for 130 minutes: Rs. 90.0
Fee for 3 hours: Rs. 90.0
```

Explanation:

- `calculateFee(int minutes)` calculates the fee based on parking duration in minutes.
- `calculateFee(int hours, int rate)` calculates the fee using hours and hourly rate.
- The compiler selects the correct method based on the arguments passed.

## 3. Runtime Polymorphism (Method Overriding)

Runtime polymorphism is achieved through method overriding.

A parent class reference can refer to child class objects. When an overridden method is called, Java executes the method belonging to the actual object.

Example:

```
class Vehicle {

    double calculateFee() {
        return 0;
    }
}

class TwoWheeler extends Vehicle {

    @Override
    double calculateFee() {
        return 10;
    }
}

class Car extends Vehicle {

    @Override
    double calculateFee() {
        return 30;
    }
}
```

Here, the `calculateFee()` method behaves differently depending on whether the actual object is a TwoWheeler or Car.

## 4. Upcasting

Upcasting means assigning a child class object to a parent class reference.

Example:

```
Vehicle v = new Car();
```

Here:

- `Vehicle` is the declared reference type.
- `Car` is the actual object type.
- The Car class inherits the properties and methods of Vehicle.

A single array of Vehicle references can store different vehicle objects.

Example:

```
Vehicle[] vehicles = {
    new TwoWheeler(),
    new Car()
};
```

This makes the parking system easier to manage because different vehicles can be stored in the same array.

## 5. Dynamic Method Dispatch

Dynamic method dispatch is the mechanism through which Java selects the overridden method at runtime.

Example:

```
Vehicle v = new Car();

v.calculateFee();
```

Execution process:

1. The reference type is Vehicle.
2. The actual object type is Car.
3. Java identifies the overridden `calculateFee()` method in Car.
4. The Car class implementation is executed.

Method Dispatch Trace:

| Description             | Value              |
| ----------------------- | ------------------ |
| Declared reference type | Vehicle            |
| Actual object type      | Car                |
| Method called           | calculateFee()     |
| Method executed         | Car.calculateFee() |
| Result                  | Rs. 30             |

## 6. Why Polymorphism Avoids Long If-Else Chains

Without polymorphism, the system may require multiple if-else statements to identify the vehicle type and calculate its parking fee.

For example:

```
if (type.equals("Car")) {
    fee = 30;
}
else if (type.equals("TwoWheeler")) {
    fee = 10;
}
else if (type.equals("ElectricCar")) {
    fee = 30;
}
```

With polymorphism, the system directly calls:

```
vehicle.calculateFee();
```

Java automatically executes the appropriate subclass method.

Advantages:

- Reduces repeated conditional statements.
- Improves code readability.
- Supports code reusability.
- Makes adding new vehicle categories easier.
- Improves maintainability.

## 7. Complete the Code

The following program completes all three TODO sections from your assignment.

File name: FeeDemo.java

```

interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {

    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    /** Every started hour is charged in full. */
    public double calculateFee(int minutes) {

        // TODO 1: Calculate billable hours
        int hours = (minutes + 59) / 60;

        return hours * ratePerHour();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vehicle &&
            regNo.equals(((Vehicle) o).regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {

    // TODO 2: Constructor and rate
    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

class ElectricCar extends Car implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    // TODO 3: Charging fee at Rs. 12 per kWh
    @Override
    public double chargingFee(int kWh) {
        return kWh * 12;
    }
}

public class FeeDemo {

    public static void main(String[] args) {

        Vehicle[] parked = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        for (Vehicle v : parked) {

            double fee = v.calculateFee(130);

            if (v instanceof Chargeable c) {
                fee += c.chargingFee(10);
            }

            System.out.println(
                v.getClass().getSimpleName() + " " +
                v.getRegNo() + " -> Rs " + fee
            );
        }

        System.out.println(
            new Car("TN02B2").equals(
                new ElectricCar("TN02B2")
            )
        );
    }
}

```

## 8. Expected Output

Expected execution result

```
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

### Output Explanation

1. TwoWheeler: 130 minutes is rounded up to 3 hours. Fee = 3 × 10 = Rs. 30.
2. Car: 130 minutes is rounded up to 3 hours. Fee = 3 × 30 = Rs. 90.
3. ElectricCar: Parking fee = Rs. 90 and charging fee = 10 × 12 = Rs. 120. Total = Rs. 210.
4. The last line prints `true` because both objects have the same registration number, `TN02B2`.

Is the last line correct?

Yes, according to the given assignment code, it is correct because the `equals()` method compares only the registration number. However, in a real parking system, the registration number should be globally unique and duplicate active registrations should be rejected.

## 9. Alternative Concept and Complexity Analysis

Alternative: Using instanceof Checks and Type Casting

Instead of using dynamic method dispatch, the program can identify each vehicle using `instanceof` and type casting.

Example:

```
class VehicleFee {

    static double calculateFee(Vehicle v, int minutes) {

        int hours = (minutes + 59) / 60;

        if (v instanceof TwoWheeler) {
            return hours * 10;
        }
        else if (v instanceof ElectricCar) {
            return hours * 30;
        }
        else if (v instanceof Car) {
            return hours * 30;
        }

        return 0;
    }
}
```

In this approach, the system checks the actual vehicle type and calculates the fee using conditional statements.

### Comparison

| Feature            | Dynamic Method Dispatch | instanceof and Casting     |
| ------------------ | ----------------------- | -------------------------- |
| Method selection   | Automatic at runtime    | Manual type checking       |
| Implementation     | Overridden methods      | Conditional statements     |
| Code maintenance   | Easier                  | More difficult             |
| Adding new vehicle | Add a subclass          | Modify existing conditions |
| Flexibility        | High                    | Lower                      |

### Time Complexity Analysis

Let k represent the number of vehicle types.

Dynamic Method Dispatch:

- Selects the appropriate overridden method.
- Method selection is treated as O(1).
- Fee calculation takes O(1).

instanceof and Casting:

- May check multiple vehicle types.
- Worst-case checking time is O(k).
- Fee calculation takes O(1) after identifying the type.

| Operation        | Dynamic Dispatch | instanceof                 |
| ---------------- | ---------------- | -------------------------- |
| Method selection | O(1)             | O(k) worst case            |
| Fee calculation  | O(1)             | O(1)                       |
| Maintenance      | Easier           | Requires condition updates |

### Effect on Code Maintenance

Dynamic method dispatch is better when the university introduces additional vehicle categories because new subclasses can provide their own fee calculations.

Using `instanceof` requires modifying the existing conditional logic whenever a new vehicle type is introduced.

## 10. Conclusion

Polymorphism allows the Campus Smart-Parking System to handle different vehicle types using a common parent class reference. Compile-time polymorphism is achieved through method overloading, while runtime polymorphism is achieved through method overriding.

Upcasting and dynamic method dispatch allow the system to execute the correct parking fee calculation based on the actual vehicle object. The Chargeable interface also provides additional charging functionality for electric vehicles.

Therefore, polymorphism improves flexibility, code reusability and maintainability in the parking management system.