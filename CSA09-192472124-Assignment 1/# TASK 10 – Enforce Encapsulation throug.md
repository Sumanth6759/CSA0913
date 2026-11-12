# TASK 10 – Enforce Encapsulation through Packages and Access Modifiers

3 Marks

## 1. Introduction

Encapsulation is an important principle of Object-Oriented Programming (OOP). It means wrapping data and methods together inside a class and restricting direct access to the data.

In the Campus Smart-Parking System, encapsulation protects important information such as vehicle registration numbers, parking fees, ticket numbers, entry times and slot numbers.

We use private fields and public getter and setter methods to access and modify the data safely. Packages are used to organise related classes.

## 2. Package Structure

The project is divided into three main packages:

```
CampusSmartParking/
│
└── src/
    └── parking/
        ├── model/
        │   ├── Vehicle.java
        │   ├── ParkingTicket.java
        │   └── ParkingSlot.java
        │
        ├── service/
        │   └── ParkingService.java
        │
        └── app/
            └── Main.java
```

Explanation:

- `parking.model`: Contains classes that represent vehicles, tickets and parking slots.
- `parking.service`: Contains parking operations such as allocation and release.
- `parking.app`: Contains the main method to execute the application.

## 3. Access Modifiers

Java provides four main access modifiers.

| Modifier    | Accessibility               | Usage                        |
| ----------- | --------------------------- | ---------------------------- |
| `private`   | Within the same class       | Protect sensitive fields     |
| Default     | Within the same package     | Package-level helper methods |
| `protected` | Same package and subclasses | Inheritance-related methods  |
| `public`    | Accessible from anywhere    | Required application methods |

Access modifier selection in the parking system:

| Class member               | Access modifier | Reason                        |
| -------------------------- | --------------- | ----------------------------- |
| Registration number        | private         | Prevent direct modification   |
| Ticket number              | private         | Protect unique ticket details |
| Parking fee                | private         | Prevent invalid fee changes   |
| Getters and setters        | public          | Controlled access             |
| Internal validation method | private         | Used only inside the class    |
| Parking allocation method  | public          | Accessible by the application |

## 4. Java Implementation

File 1: Vehicle.java

Save inside `parking/model/Vehicle.java`.

```
package parking.model;

public class Vehicle {

    private String regNo;
    private String vehicleType;

    public Vehicle(String regNo, String vehicleType) {
        setRegNo(regNo);
        setVehicleType(vehicleType);
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Invalid registration number"
            );
        }
        this.regNo = regNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null ||
            !(vehicleType.equalsIgnoreCase("Car") ||
              vehicleType.equalsIgnoreCase("TwoWheeler") ||
              vehicleType.equalsIgnoreCase("EV"))) {
            throw new IllegalArgumentException(
                "Invalid vehicle type"
            );
        }
        this.vehicleType = vehicleType;
    }
}
```

File 2: ParkingTicket.java

Save inside `parking/model/ParkingTicket.java`.

```
package parking.model;

public class ParkingTicket {

    private int ticketNo;
    private int slotNo;
    private long entryTime;

    public ParkingTicket(int ticketNo, int slotNo,
                         long entryTime) {

        if (ticketNo <= 0)
            throw new IllegalArgumentException(
                "Invalid ticket number"
            );

        setSlotNo(slotNo);

        if (entryTime <= 0)
            throw new IllegalArgumentException(
                "Invalid entry time"
            );

        this.ticketNo = ticketNo;
        this.entryTime = entryTime;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        if (slotNo < 0)
            throw new IllegalArgumentException(
                "Invalid slot number"
            );

        this.slotNo = slotNo;
    }

    public long getEntryTime() {
        return entryTime;
    }
}
```

File 3: ParkingService.java

Save inside `parking/service/ParkingService.java`.

```
package parking.service;

import parking.model.Vehicle;
import parking.model.ParkingTicket;

public class ParkingService {

    public void parkVehicle(Vehicle v,
                            ParkingTicket t) {

        System.out.println("Vehicle Parked Successfully");
        System.out.println("Registration: " + v.getRegNo());
        System.out.println("Vehicle Type: " + v.getVehicleType());
        System.out.println("Ticket Number: " + t.getTicketNo());
        System.out.println("Slot Number: " + t.getSlotNo());
    }
}
```

File 4: Main.java

Save inside `parking/app/Main.java`.

```
package parking.app;

import parking.model.Vehicle;
import parking.model.ParkingTicket;
import parking.service.ParkingService;

public class Main {

    public static void main(String[] args) {

        Vehicle v = new Vehicle("TN01AB1234", "Car");

        ParkingTicket t =
            new ParkingTicket(1001, 5,
                              System.currentTimeMillis());

        ParkingService service = new ParkingService();

        service.parkVehicle(v, t);

        System.out.println("Encapsulation Successfully Implemented");
    }
}
```

## 5. Expected Output

```
Vehicle Parked Successfully
Registration: TN01AB1234
Vehicle Type: Car
Ticket Number: 1001
Slot Number: 5
Encapsulation Successfully Implemented
```

Note: The entry time is stored internally as a timestamp and is not printed.

## 6. Getters and Setters Validation

Getters and setters protect the data from invalid values.

Examples:

- Registration number cannot be null or empty.
- Vehicle type must be Car, TwoWheeler or EV.
- Slot number cannot be negative.
- Ticket number must be greater than zero.
- Entry time must be a positive value.

If invalid data is entered, the program throws an `IllegalArgumentException`.

## 7. Wrapper Classes and Autoboxing

Wrapper classes convert primitive data types into objects.

| Primitive type | Wrapper class |
| -------------- | ------------- |
| `int`          | `Integer`     |
| `double`       | `Double`      |
| `long`         | `Long`        |
| `boolean`      | `Boolean`     |

Example:

```
public class WrapperDemo {

    public static void main(String[] args) {

        int slot = 5;

        // Autoboxing
        Integer slotObject = slot;

        // Unboxing
        int newSlot = slotObject;

        System.out.println("Slot Object: " + slotObject);
        System.out.println("Slot Number: " + newSlot);

        Integer a = 1000;
        Integer b = 1000;

        System.out.println(a.equals(b));
        System.out.println(a == b);
    }
}
```

Output:

```
Slot Object: 5
Slot Number: 5
true
false
```

Explanation:

- Autoboxing automatically converts `int` into `Integer`.
- Unboxing converts `Integer` into `int`.
- `equals()` compares the values of wrapper objects.
- `==` compares object references, so it should not be used to compare wrapper values.

## 8. Alternative Concept and Complexity Analysis

Alternative: Public fields instead of private fields

If the fields were declared public, any class could directly modify the data without validation.

Example:

```
class Vehicle {
    public String regNo;
    public int slotNo;
}

public class Main {
    public static void main(String[] args) {

        Vehicle v = new Vehicle();

        v.regNo = "TN01AB1234";
        v.slotNo = -10;

        System.out.println(v.regNo);
        System.out.println(v.slotNo);
    }
}
```

Output:

```
TN01AB1234
-10
```

Here, an invalid slot number is accepted because no validation is performed.

### Comparison

| Private fields with setters     | Public fields                  |
| ------------------------------- | ------------------------------ |
| Data is protected               | Data is directly accessible    |
| Validation is centralised       | Validation must be repeated    |
| Better security and reliability | Greater chance of invalid data |
| Easier maintenance              | Difficult to maintain          |
| Supports encapsulation          | Violates encapsulation         |

### Time and Space Complexity

| Operation             | Private fields with validation | Public fields           |
| --------------------- | ------------------------------ | ----------------------- |
| Reading a field       | O(1)                           | O(1)                    |
| Updating a field      | O(1)                           | O(1)                    |
| Validation per update | O(1)                           | No automatic validation |
| Space per object      | O(1)                           | O(1)                    |

For `n` vehicles, storing the vehicle information requires O(n) space in both approaches.

If validation is repeated in `m` different calling locations, the total validation work can be O(m) per update. With encapsulation, the validation logic is maintained in one place, reducing duplication and maintenance effort.

## 9. Conclusion

Encapsulation through packages and access modifiers improves the security, reliability and maintainability of the Campus Smart-Parking System. Private fields prevent direct modification, while getters and setters provide controlled access and validation. Packages organise the classes into separate modules. Wrapper classes support object-based operations, but methods such as `equals()` should be used instead of `==` for comparing wrapper values.
