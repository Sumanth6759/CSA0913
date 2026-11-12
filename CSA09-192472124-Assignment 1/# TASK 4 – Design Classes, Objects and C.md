# TASK 4 – Design Classes, Objects and Constructors

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Classes, objects and constructors are important concepts in Java Object-Oriented Programming. In the Campus Smart-Parking System, classes are used to represent vehicles, parking slots, parking tickets and users.

An object is an instance of a class. Constructors are used to initialize objects when they are created.

The system uses default, parameterized and overloaded constructors to initialize parking-related objects with appropriate values.

## 2. Fields and Methods of Each Class

| Class           | Fields                            | Methods                              |
| --------------- | --------------------------------- | ------------------------------------ |
| Vehicle         | regNo, vehicleType                | getDetails(), calculateFee()         |
| ParkingFacility | floors, slots                     | allocateSlot(), releaseSlot()        |
| ParkingSlot     | slotNumber, floorNumber, occupied | occupy(), release()                  |
| ParkingTicket   | ticketNumber, regNo, entryTime    | generateTicket(), getTicketDetails() |
| User            | userId, userName, userType        | validatePass()                       |
| Payment         | amount, paymentStatus             | calculateFee(), makePayment()        |

## 3. Types of Constructors

Java supports different types of constructors to initialize objects.

A. Default Constructor

A default constructor does not accept any parameters. It initializes an object with default values.

Example:

```
class ParkingSlot {

    int slotNumber;
    boolean occupied;

    ParkingSlot() {
        slotNumber = 0;
        occupied = false;
    }
}
```

Usage: It can be used when creating an empty parking slot before assigning its details.

B. Parameterized Constructor

A parameterized constructor accepts arguments to initialize an object with specific values.

Example:

```
class Vehicle {

    String regNo;
    String vehicleType;

    Vehicle(String regNo, String vehicleType) {
        this.regNo = regNo;
        this.vehicleType = vehicleType;
    }
}
```

Usage: It is used when a vehicle enters the parking facility and its registration number and type are available.

C. Overloaded Constructor

Constructor overloading means defining multiple constructors in the same class with different parameter lists.

Example:

```
class ParkingTicket {

    String regNo;
    int floor;
    int slot;

    ParkingTicket(String regNo) {
        this.regNo = regNo;
        this.floor = 0;
        this.slot = 0;
    }

    ParkingTicket(String regNo, int floor, int slot) {
        this.regNo = regNo;
        this.floor = floor;
        this.slot = slot;
    }
}
```

Usage: It allows tickets to be created with only a registration number or with complete parking details.

## 4. Use of this Keyword and Constructor Chaining

The `this` keyword refers to the current object. It is used to differentiate instance variables from constructor parameters.

Constructor chaining means calling one constructor from another constructor using `this()`.

Example Java Program:

```
class ParkingTicket {

    String regNo;
    int floor;
    int slot;

    ParkingTicket(String regNo) {
        this(regNo, 0, 0);
    }

    ParkingTicket(String regNo, int floor, int slot) {
        this.regNo = regNo;
        this.floor = floor;
        this.slot = slot;
    }

    void display() {
        System.out.println("Registration: " + regNo);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
    }

    public static void main(String[] args) {

        ParkingTicket t1 =
            new ParkingTicket("TN01AB1111");

        ParkingTicket t2 =
            new ParkingTicket("TN02CD2222", 1, 5);

        t1.display();
        t2.display();
    }
}
```

Output:

```
Registration: TN01AB1111
Floor: 0
Slot: 0
Registration: TN02CD2222
Floor: 1
Slot: 5
```

Explanation:

- `this(regNo, 0, 0)` calls another constructor.
- `this.regNo` refers to the current object's registration number.
- Constructor chaining avoids repeated initialization code.
- It improves code reusability and readability.

## 5. Copy Constructor

A copy constructor creates a new object using the values of an existing object.

In the parking system, it can be used when reissuing a parking ticket with the same vehicle and parking details.

However, the new ticket must receive a new unique ticket number rather than copying the old ticket number.

Example:

```
class ParkingTicket {

    static int counter = 1000;

    int ticketNumber;
    String regNo;

    ParkingTicket(String regNo) {
        this.ticketNumber = ++counter;
        this.regNo = regNo;
    }

    // Copy constructor
    ParkingTicket(ParkingTicket oldTicket) {
        this.ticketNumber = ++counter;
        this.regNo = oldTicket.regNo;
    }

    void display() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Registration: " + regNo);
    }

    public static void main(String[] args) {

        ParkingTicket t1 =
            new ParkingTicket("TN01AB1111");

        ParkingTicket t2 =
            new ParkingTicket(t1);

        t1.display();
        t2.display();
    }
}
```

Output:

```
Ticket Number: 1001
Registration: TN01AB1111
Ticket Number: 1002
Registration: TN01AB1111
```

Why is a copy constructor needed?

- It helps create a new ticket using existing ticket information.
- It avoids entering the same vehicle details again.
- It ensures that each newly issued ticket has a unique ticket number.

## 6. Object Creation and Memory Allocation

When an object is created in Java using the `new` keyword, memory is allocated for that object.

Example:

```
ParkingTicket ticket =
    new ParkingTicket("TN01AB1111");
```

The memory allocation process involves:

1. Stack Memory: Stores local variables and object reference variables within method calls.
2. Heap Memory: Stores objects created using the `new` keyword.
3. Reference Variable: Stores a reference to the object.

[Understanding Stack and Heap Memory in Java: Detailed Explanation with Examples and Use Cases | by Vinotech | Medium](https://images.openai.com/static-rsc-4/7fP7jQJGXI5MqC_Ceaf-xjLb8yCHkueUhqdeRVLZJ3CEF_eRqIxWwPRaBC1AJsQmgMTKey4B8oNeodJ3Ur2TltAK_R41FLF63vvN8Q-ysBnLxO8l6nYm-BsutI8zoGC2GsrDp6L8L6L5Wm1Uo9kDcezSgOuGo8G6ITHBSReF-As?purpose=inline)

[medium.com](https://medium.com/%40vino7tech/understanding-stack-and-heap-memory-in-java-detailed-explanation-with-examples-and-use-cases-135afe9bca37?utm_source=chatgpt.com)

Explanation:

- `ticket` is a reference variable.
- The actual ParkingTicket object is stored in heap memory.
- The reference variable is used to access the object's fields and methods.
- When an object is no longer reachable, it becomes eligible for garbage collection.

## 7. Alternative Concept and Complexity Analysis

Alternative: Creating Objects Using Default Constructors and Setter Methods

Instead of using overloaded constructors and constructor chaining, objects can be created using a default constructor followed by setter methods.

Example:

```
class ParkingTicket {

    String regNo;
    int floor;
    int slot;

    ParkingTicket() {
    }

    void setDetails(String regNo, int floor, int slot) {

        this.regNo = regNo;
        this.floor = floor;
        this.slot = slot;
    }

    void display() {
        System.out.println(regNo);
        System.out.println(floor);
        System.out.println(slot);
    }

    public static void main(String[] args) {

        ParkingTicket t = new ParkingTicket();

        t.setDetails("TN01AB1111", 1, 5);

        t.display();
    }
}
```

### Comparison of Object Creation Approaches

| Feature          | Overloaded Constructors        | Setter Methods                | Static Factory Methods         |
| ---------------- | ------------------------------ | ----------------------------- | ------------------------------ |
| Object creation  | Using constructors             | Default constructor           | Using static methods           |
| Initialization   | During creation                | After creation                | Through factory method         |
| Validation       | During construction            | During setter calls           | Inside factory method          |
| Object validity  | Can ensure valid initial state | May temporarily be incomplete | Can ensure valid initial state |
| Code maintenance | Easy                           | Requires careful validation   | Flexible                       |

Static Factory Method Example:

```
class ParkingTicket {

    String regNo;

    private ParkingTicket(String regNo) {
        this.regNo = regNo;
    }

    static ParkingTicket createTicket(String regNo) {
        return new ParkingTicket(regNo);
    }
}
```

### Time and Space Complexity Analysis

Assume that n ParkingTicket objects are created.

| Approach                         | Time Complexity | Space Complexity |
| -------------------------------- | --------------- | ---------------- |
| Overloaded constructors          | O(n)            | O(n)             |
| Default constructor with setters | O(n)            | O(n)             |
| Static factory methods           | O(n)            | O(n)             |

Explanation:

- Creating one ticket takes O(1) time when initialization involves a fixed number of fields.
- Creating n tickets takes O(n) time.
- Each ticket requires O(1) space for a fixed number of fields.
- Therefore, n ticket objects require O(n) total space.

Overloaded constructors provide better initialization because required values can be supplied during object creation. Setter methods may allow incomplete or invalid objects unless validation is applied carefully. Static factory methods provide additional flexibility and can control object creation.

## 8. Conclusion

The Campus Smart-Parking System uses classes, objects and constructors to organize parking information efficiently. Default, parameterized and overloaded constructors help initialize objects in different ways. The `this` keyword and constructor chaining reduce code duplication, while copy constructors support ticket reissuance with unique ticket numbers.

Objects are stored in heap memory, and reference variables provide access to them. Overall, overloaded constructors and constructor chaining are suitable for this system because they improve code reusability, readability and object initialization.
