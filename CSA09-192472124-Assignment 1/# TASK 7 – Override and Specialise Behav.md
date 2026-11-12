# TASK 7 – Override and Specialise Behaviour

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Method overriding is an important concept in Java Object-Oriented Programming. It allows a subclass to provide its own implementation of a method already defined in its parent class.

In the Campus Smart-Parking System, different vehicle categories have different parking charges. Therefore, the `calculateFee()` method is overridden in subclasses such as `TwoWheeler`, `Car` and `ElectricCar`.

This helps implement different parking fee calculations without writing multiple conditional statements.

## 2. Methods That Are Overridden

The following methods can be overridden in the parking system:

| Method         | Purpose                                                        |
| -------------- | -------------------------------------------------------------- |
| calculateFee() | Calculates parking fees based on vehicle type.                 |
| toString()     | Returns vehicle details as a string.                           |
| equals()       | Checks whether two vehicles have the same registration number. |
| hashCode()     | Generates a hash code based on registration number.            |

Example:

- TwoWheeler charges Rs. 10 per hour.
- Car charges Rs. 30 per hour.
- ElectricCar charges Rs. 30 per hour, with an additional charging fee.

Each subclass provides its own implementation of the required behaviour.

## 3. Rules of Method Overriding

1. The method name must be the same as the parent class method.
2. The method parameters must be the same.
3. The return type must be the same or compatible through covariant returns.
4. The access level cannot be more restrictive than the overridden method.
5. Static methods cannot be overridden; they are hidden.
6. The `@Override` annotation is used to identify overridden methods.

## 4. Use of @Override Annotation

The `@Override` annotation informs the compiler that a method is intended to override a parent class method.

Example:

```
class Vehicle {

    double calculateFee() {
        return 0;
    }
}

class Car extends Vehicle {

    @Override
    double calculateFee() {
        return 30;
    }
}
```

Here, the Car class overrides the `calculateFee()` method of the Vehicle class.

## 5. Use of final Keyword

The `final` keyword prevents unwanted modifications in Java.

It can be used in three ways.

A. Final Variable

A final variable cannot be reassigned after initialization.

```
final double CAR_RATE = 30;
```

B. Final Method

A final method cannot be overridden by subclasses.

```
class Vehicle {

    final void displayRules() {
        System.out.println("Parking rules applied");
    }
}
```

C. Final Class

A final class cannot be inherited by another class.

```
final class ParkingRules {

    void display() {
        System.out.println("Parking rules");
    }
}
```

In the parking system, final variables are useful for maintaining fixed parking rates or configuration values.

## 6. Overriding Object Class Methods

Java provides the `Object` class as the superclass of all classes. The methods `toString()`, `equals()` and `hashCode()` can be overridden to define meaningful vehicle comparisons.

### A. toString() Method

The `toString()` method returns the vehicle details in string format.

```
@Override
public String toString() {
    return "Vehicle Registration: " + regNo;
}
```

### B. equals() Method

The `equals()` method checks whether two vehicle objects have the same registration number.

```
@Override
public boolean equals(Object obj) {

    if (this == obj)
        return true;

    if (!(obj instanceof Vehicle))
        return false;

    Vehicle other = (Vehicle) obj;

    return regNo.equals(other.regNo);
}
```

### C. hashCode() Method

The `hashCode()` method generates a hash code based on the registration number.

```
@Override
public int hashCode() {
    return regNo.hashCode();
}
```

If two vehicle objects have the same registration number, their hash codes must also be equal.

## 7. Complete Java Program for Method Overriding

File name: VehicleOverriding.java

```

import java.util.Objects;

class Vehicle {

    protected final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    double calculateFee() {
        return 0;
    }

    @Override
    public String toString() {
        return "Vehicle Registration: " + regNo;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Vehicle))
            return false;

        Vehicle other = (Vehicle) obj;

        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee() {
        return 10;
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee() {
        return 30;
    }
}

class ElectricCar extends Car {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee() {
        return 30 + 12 * 10;
    }
}

public class VehicleOverriding {

    public static void main(String[] args) {

        Vehicle v1 = new TwoWheeler("TN01AB1111");
        Vehicle v2 = new Car("TN02CD2222");
        Vehicle v3 = new ElectricCar("TN03EF3333");

        System.out.println(v1);
        System.out.println("Fee: Rs. " + v1.calculateFee());

        System.out.println(v2);
        System.out.println("Fee: Rs. " + v2.calculateFee());

        System.out.println(v3);
        System.out.println("Fee: Rs. " + v3.calculateFee());

        Vehicle v4 = new Car("TN02CD2222");

        System.out.println("Are vehicles equal? " +
            v2.equals(v4));

        System.out.println("Hash codes equal? " +
            (v2.hashCode() == v4.hashCode()));
    }
}

```

## 8. Expected Output

```
Vehicle Registration: TN01AB1111
Fee: Rs. 10.0
Vehicle Registration: TN02CD2222
Fee: Rs. 30.0
Vehicle Registration: TN03EF3333
Fee: Rs. 150.0
Are vehicles equal? true
Hash codes equal? true
```

Output Explanation:

1. TwoWheeler calculates its parking fee as Rs. 10.
2. Car calculates its parking fee as Rs. 30.
3. ElectricCar calculates its parking and charging fee as Rs. 150 (Rs. 30 + Rs. 120).
4. The `equals()` method returns true because both Car objects have the same registration number.
5. The `hashCode()` method produces equal hash codes for both objects.

## 9. Alternative Concept and Complexity Analysis

Alternative: Using a Single calculateFee() Method with switch

Instead of overriding the `calculateFee()` method in different subclasses, a single method can be implemented using a switch statement.

Example:

```
class ParkingPayment {

    double calculateFee(String vehicleType, int hours) {

        double rate;

        switch (vehicleType) {

            case "TwoWheeler":
                rate = 10;
                break;

            case "Car":
                rate = 30;
                break;

            case "ElectricCar":
                rate = 30;
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid vehicle type"
                );
        }

        return rate * hours;
    }
}
```

In this approach, the vehicle type is checked using the switch statement, and the appropriate parking rate is selected.

### Comparison Between Method Overriding and Switch

| Feature            | Method Overriding           | Switch Statement                 |
| ------------------ | --------------------------- | -------------------------------- |
| Implementation     | Separate subclass methods   | Single method                    |
| Code organization  | Object-oriented             | Conditional-based                |
| Fee calculation    | Based on actual object type | Based on vehicle type            |
| Adding new vehicle | Create a new subclass       | Modify switch statement          |
| Maintenance        | Easier to extend            | More difficult as types increase |
| Polymorphism       | Supported                   | Not required                     |

### Time Complexity Analysis

Let k represent the number of vehicle types.

Method Overriding:

- Method dispatch selects the appropriate implementation.
- Fee calculation requires a constant number of operations.
- Time complexity: O(1).

Switch Statement:

- The vehicle type is checked against available cases.
- With k cases, the worst-case number of checks is O(k).
- Time complexity: O(k) in the general case.

### Effect on Code Maintenance

When a new vehicle category is introduced:

- In method overriding, a new subclass can be created without changing existing vehicle subclasses.
- In a switch-based approach, the existing `calculateFee()` method must be modified to include the new category.

Therefore, method overriding is more suitable for a parking system that may support additional vehicle categories in the future.

## 10. Conclusion

Method overriding allows different vehicle subclasses to implement their own parking fee calculations. The `@Override` annotation ensures that methods correctly override parent class methods.

The `final` keyword protects important variables, methods and classes from unwanted modifications. Overriding `toString()`, `equals()` and `hashCode()` provides meaningful vehicle information and comparison based on registration numbers.

Compared with a switch-based approach, method overriding provides better flexibility, code reusability and maintainability. Therefore, method overriding is a suitable approach for the Campus Smart-Parking System.
