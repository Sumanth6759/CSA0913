# TASK 1 – Understand the Problem and Identify the Objects

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

## 1. Problem Analysis

The Campus Smart-Parking System is designed to manage vehicle parking in a university campus. The parking facility contains multiple floors, and each floor has a fixed number of parking slots.

The system supports three types of vehicles: two-wheelers, cars and electric vehicles (EVs). When a vehicle enters the parking facility, the system checks for a suitable free slot and allocates it. It generates a unique parking ticket and records the entry time.

When the vehicle leaves, the system releases the parking slot and calculates the parking fee based on the vehicle type and parking duration. Students and staff with valid monthly passes can park free of charge, whereas visitors must pay the applicable parking fee.

The system should be designed using Java Object-Oriented Programming concepts such as classes, objects, inheritance, encapsulation and polymorphism.

## 2. Inputs and Outputs

Inputs:

1. Vehicle registration number.
2. Vehicle type (Two-wheeler, Car or Electric Vehicle).
3. Parking floor and slot details.
4. Vehicle entry time and exit time.
5. User category (Student, Staff or Visitor).
6. Monthly pass validity.
7. Charging slot requirement for electric vehicles.

Outputs:

1. Allocated parking floor and slot number.
2. Unique parking ticket number.
3. Vehicle entry and exit details.
4. Parking duration.
5. Calculated parking fee.
6. Updated parking slot availability.
7. Error message when no suitable parking slot is available.

## 3. Identification of Candidate Classes

The following classes are identified from the problem description.

| Candidate Class | Responsibility                                            |
| --------------- | --------------------------------------------------------- |
| ParkingFacility | Manages floors, parking slots and availability.           |
| ParkingSlot     | Stores slot number, floor number and occupancy status.    |
| Vehicle         | Represents common vehicle details.                        |
| TwoWheeler      | Represents two-wheeler vehicles.                          |
| Car             | Represents car vehicles.                                  |
| ElectricVehicle | Represents electric vehicles and charging requirements.   |
| ParkingTicket   | Stores ticket number, registration number and entry time. |
| User            | Represents parking users.                                 |
| Student         | Represents student users.                                 |
| Staff           | Represents staff users.                                   |
| Visitor         | Represents visitor users.                                 |
| Payment         | Calculates and records parking payments.                  |

## 4. Responsibilities of Each Class

1\. ParkingFacility: Manages the complete parking facility. It allocates available slots, releases occupied slots and checks parking availability.

2\. ParkingSlot: Maintains individual parking slot information. It ensures that one slot cannot be occupied by multiple vehicles at the same time.

3\. Vehicle: Acts as the parent class for different vehicle categories. It stores common information such as registration number and vehicle type.

4\. TwoWheeler: Represents two-wheeler vehicles and defines their parking fee.

5\. Car: Represents cars and provides the applicable parking fee calculation.

6\. ElectricVehicle: Represents electric vehicles and handles charging-related requirements and additional charges.

7\. ParkingTicket: Generates and maintains a unique ticket number, vehicle registration number and entry time.

8\. User: Stores common user information and pass details.

9\. Student and Staff: Represent students and staff who can receive free parking when they hold valid monthly passes.

10\. Visitor: Represents visitors who must pay the applicable parking fee.

11\. Payment: Calculates the parking fee based on vehicle type, parking duration and applicable parking rules.

## 5. Nouns and Verbs Identification

| Nouns (Candidate Classes) | Verbs (Methods)     |
| ------------------------- | ------------------- |
| Vehicle                   | registerVehicle()   |
| ParkingFacility           | allocateSlot()      |
| ParkingSlot               | checkAvailability() |
| ParkingTicket             | generateTicket()    |
| User                      | validatePass()      |
| Payment                   | calculateFee()      |
| Floor                     | addFloor()          |
| ElectricVehicle           | chargeVehicle()     |

Nouns represent the objects and entities in the system, whereas verbs represent the operations performed by those objects.

## 6. Business Rules

The system must follow these business rules:

1. A parking slot can contain only one vehicle at a time.
2. Every vehicle must have a valid registration number.
3. Each parking ticket must have a unique ticket number.
4. A vehicle cannot be allocated to an already occupied slot.
5. Electric vehicles should be assigned suitable charging slots when charging is requested.
6. A parking slot must be released when the vehicle exits.
7. Parking fees must be calculated based on vehicle type and parking duration.
8. Students and staff with valid monthly passes are eligible for free parking.
9. Visitors must pay the applicable parking fee.
10. Invalid parking operations must be rejected by the system.

## 7. Assumptions

1. Each parking floor contains a fixed number of parking slots.
2. Every vehicle has a unique registration number.
3. The parking system operates continuously.
4. Parking charges are calculated according to predefined fee rates.
5. A valid monthly pass is required for free parking.
6. Electric vehicles require designated charging slots when charging is needed.
7. The system records entry and exit times accurately.

## 8. Alternative Concept and Complexity Analysis

Alternative: Using Plain Strings in Parallel Arrays

Instead of creating individual Vehicle objects, the system can store vehicle information using parallel arrays.

Example:

```
String regNo[] = new String[100];
String type[] = new String[100];
String entryTime[] = new String[100];
```

Here:

- `regNo[]` stores vehicle registration numbers.
- `type[]` stores vehicle types.
- `entryTime[]` stores vehicle entry times.

All three arrays use the same index to represent one vehicle.

Example Java Code:

```
public class ParkingArray {
    public static void main(String[] args) {

        String regNo[] = {"TN01AB1111", "TN02CD2222"};
        String type[] = {"Car", "TwoWheeler"};
        String entryTime[] = {"09:00", "10:00"};

        String search = "TN01AB1111";

        for (int i = 0; i < regNo.length; i++) {
            if (regNo[i].equals(search)) {
                System.out.println("Vehicle Found");
                System.out.println("Registration: " + regNo[i]);
                System.out.println("Type: " + type[i]);
                System.out.println("Entry Time: " + entryTime[i]);
                break;
            }
        }
    }
}
```

### Comparison of Both Approaches

| Comparison        | Vehicle Objects                  | Parallel Arrays                 |
| ----------------- | -------------------------------- | ------------------------------- |
| Data storage      | Stores data and methods together | Stores data separately          |
| Searching         | Linear search                    | Linear search                   |
| Search complexity | O(n)                             | O(n)                            |
| Adding attributes | Easier by adding a field         | Requires another parallel array |
| Maintenance       | Easier                           | More difficult                  |
| Data organization | Object-oriented                  | Index-based                     |

### Time and Space Complexity

1\. Searching for a vehicle

- Vehicle Objects: O(n), because the system may check each vehicle object.
- Parallel Arrays: O(n), because the registration array is searched sequentially.

2\. Space complexity

For n vehicles and three attributes:

- Vehicle Objects: O(n), with one object per vehicle and its associated fields.
- Parallel Arrays: O(n), with three arrays containing n entries each.

Both approaches have linear space complexity, but parallel arrays require maintaining corresponding indexes correctly.

### Conclusion

The Vehicle Object approach is more suitable for the Campus Smart-Parking System because it combines vehicle data and related operations into a single object. It supports encapsulation, inheritance and polymorphism, making the system easier to maintain and extend when new vehicle categories or parking rules are introduced.