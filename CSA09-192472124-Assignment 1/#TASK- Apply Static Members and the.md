# TASK 5 – Apply Static Members and the Object Life Cycle

3 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Static members in Java belong to the class rather than individual objects. They are shared among all objects of a class.

In the Campus Smart-Parking System, static variables and methods are used to maintain common information such as the total number of parking tickets generated and the total number of occupied parking slots.

The object life cycle explains how objects are created, used and destroyed during program execution.

## 2. Static Variables in the Parking System

Static variables are used when data must be shared among all objects.

The following static variables are required:

1\. Ticket Counter

- Maintains the total number of parking tickets generated.
- Every new ticket receives a unique ticket number.
- It should be static because all parking tickets share the same counter.

2\. Total Occupancy

- Maintains the total number of occupied parking slots.
- It is shared by all parking facility objects.
- It is updated whenever a vehicle enters or leaves.

Example:

```
static int ticketCounter = 1000;
static int totalOccupancy = 0;
```

If these variables were instance variables, each object would maintain a separate counter, which could cause duplicate ticket numbers or incorrect overall occupancy.

## 3. Static Methods and Static Blocks

A. Static Method

A static method belongs to the class and can be called without creating an object.

Example:

```
static int getTotalOccupancy() {
    return totalOccupancy;
}
```

It can be accessed using:

```
System.out.println(ParkingFacility.getTotalOccupancy());
```

B. Static Block

A static block is executed only once when the class is loaded.

Example:

```
static {
    ticketCounter = 1000;
    totalOccupancy = 0;
}
```

It initializes the common parking system values before objects are created.

## 4. Object Life Cycle

The object life cycle describes the different stages of an object.

[Understanding Java Memory Management & GC | Saket Verma posted on the topic | LinkedIn](https://images.openai.com/static-rsc-4/khW5Vj3d3qjl1Y65mTHpbtegSdjJL-p2X0mHBDDwQDqI1KFikx4tlEcCAJ42THZjzqDJlF07TZYYBZH3CmHoZ0qoPGeLIYTpwvbxX3hJa81JqLaMulvAFhqkqFLNRQVIdXo2jkeRGWo8k7O0yntI_qaMkCB4Ld-8mlYjC9Xwewc?purpose=inline)

[linkedin.com](https://www.linkedin.com/posts/saket-verma37_java-memorymanagement-garbagecollection-activity-7322198522814939136-ICFX?utm_source=chatgpt.com)

The stages are:

1. Object Creation: An object is created using the `new` keyword.
2. Object Initialization: The constructor initializes the object's fields.
3. Object Usage: The object performs operations such as parking allocation and fee calculation.
4. Object Becomes Unreachable: When the vehicle leaves, the parking ticket may no longer be needed by the active parking system.
5. Garbage Collection: The object becomes eligible for garbage collection when no reachable references point to it.

Example:

```
ParkingTicket ticket =
    new ParkingTicket("TN01AB1111");

ticket = null;
```

After assigning `null`, if no other references point to that ticket, it becomes eligible for garbage collection.

Note: Garbage collection is managed automatically by the JVM and does not happen immediately when an object becomes unreachable.

## 5. Passing and Returning Objects in Methods

In Java, objects can be passed as arguments to methods and returned from methods.

Example:

```
class ParkingTicket {

    String regNo;

    ParkingTicket(String regNo) {
        this.regNo = regNo;
    }
}

class ParkingDemo {

    static void displayTicket(ParkingTicket ticket) {
        System.out.println("Vehicle: " + ticket.regNo);
    }

    static ParkingTicket createTicket(String regNo) {
        return new ParkingTicket(regNo);
    }

    public static void main(String[] args) {

        ParkingTicket t =
            createTicket("TN01AB1111");

        displayTicket(t);
    }
}
```

Output:

```
Vehicle: TN01AB1111
```

Explanation:

- `createTicket()` creates and returns a ParkingTicket object.
- `displayTicket()` receives the object as an argument.
- The object reference is passed by value in Java.

## 6. Complete Java Program Using Static Members

File name: ParkingStaticDemo.java

```

class ParkingTicket {

    static int ticketCounter = 1000;

    int ticketNumber;
    String regNo;

    ParkingTicket(String regNo) {
        this.ticketNumber = ++ticketCounter;
        this.regNo = regNo;
    }

    void display() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Vehicle Number: " + regNo);
    }
}

class ParkingFacility {

    static int totalOccupancy = 0;

    void vehicleEntry() {
        totalOccupancy++;
        System.out.println("Vehicle entered.");
    }

    void vehicleExit() {
        if (totalOccupancy > 0) {
            totalOccupancy--;
            System.out.println("Vehicle exited.");
        }
    }

    static void displayOccupancy() {
        System.out.println(
            "Total Occupancy: " + totalOccupancy
        );
    }
}

public class ParkingStaticDemo {

    public static void main(String[] args) {

        ParkingFacility p1 = new ParkingFacility();
        ParkingFacility p2 = new ParkingFacility();

        ParkingTicket t1 =
            new ParkingTicket("TN01AB1111");

        ParkingTicket t2 =
            new ParkingTicket("TN02CD2222");

        t1.display();
        t2.display();

        p1.vehicleEntry();
        p2.vehicleEntry();
        p1.vehicleExit();

        ParkingFacility.displayOccupancy();
    }
}

```

## 7. Expected Output

```
Ticket Number: 1001
Vehicle Number: TN01AB1111
Ticket Number: 1002
Vehicle Number: TN02CD2222
Vehicle entered.
Vehicle entered.
Vehicle exited.
Total Occupancy: 1
```

Output Explanation:

1. The first ticket receives ticket number 1001.
2. The second ticket receives ticket number 1002.
3. Two vehicles enter the parking facility.
4. One vehicle exits the facility.
5. The final total occupancy is 1.

## 8. Alternative Concept and Complexity Analysis

Alternative: Using an Instance Variable Instead of a Static Variable

In the original design, the ticket counter is declared as a static variable.

If it is changed into an instance variable, each ParkingFacility object maintains its own ticket counter.

Example:

```
class ParkingFacility {

    int ticketCounter = 1000;

    int generateTicket() {
        return ++ticketCounter;
    }

    public static void main(String[] args) {

        ParkingFacility north = new ParkingFacility();
        ParkingFacility south = new ParkingFacility();

        System.out.println(north.generateTicket());
        System.out.println(south.generateTicket());
        System.out.println(north.generateTicket());
    }
}
```

Output:

```
1001
1001
1002
```

The output shows that both facilities generate the same ticket number because each facility maintains an independent counter.

### Comparison Between Static and Instance Variables

| Feature           | Static Variable      | Instance Variable        |
| ----------------- | -------------------- | ------------------------ |
| Belongs to        | Class                | Object                   |
| Memory allocation | Once per class       | Once per object          |
| Sharing           | Shared among objects | Separate for each object |
| Ticket numbering  | Common counter       | Separate counters        |
| Memory usage      | O(1) per counter     | O(k) for k facilities    |

### Proposed Solution

For a university with north and south parking facilities, there are two possible approaches:

1. Single common ticket counter: Use a static counter if ticket numbers must be unique across all facilities.
2. Separate facility counters: Use instance variables if each facility maintains its own ticket numbering system. In this case, include a facility identifier in the ticket number to prevent duplication.

### Time and Space Complexity

Let:

- k = Number of parking facility objects.
- n = Number of tickets generated.

| Operation             | Static Counter | Instance Counter |
| --------------------- | -------------- | ---------------- |
| Generate one ticket   | O(1)           | O(1)             |
| Generate n tickets    | O(n)           | O(n)             |
| Counter storage       | O(1)           | O(k)             |
| Ticket object storage | O(n)           | O(n)             |

Static variables save memory when information is shared among all objects. Instance variables are useful when each facility requires independent information.

## 9. Conclusion

Static members are useful in the Campus Smart-Parking System for maintaining common information such as ticket numbers and total parking occupancy. Static variables ensure that all objects share the same data, while static methods provide access to class-level information.

The object life cycle explains how parking ticket objects are created, used and eventually become eligible for garbage collection. Objects can also be passed to and returned from methods.

Therefore, static members and proper object lifecycle management improve memory usage, data sharing and overall system organization.
