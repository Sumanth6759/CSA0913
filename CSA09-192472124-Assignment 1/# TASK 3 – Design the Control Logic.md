# TASK 3 – Design the Control Logic

4 Marks

Assignment Title: Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System.

Bloom's Level: K4 – Analyse

## 1. Introduction

Control logic is used to manage the decision-making and looping operations in the Campus Smart-Parking System. It helps the system allocate available parking slots, release occupied slots and calculate parking fees based on vehicle type and parking duration.

The system uses conditional statements such as `if-else` and looping statements such as `for` and `while` to perform parking operations efficiently.

## 2. Slot Allocation Logic

Slot allocation is the process of finding and assigning an available parking slot to a vehicle.

Working procedure:

1. Receive the vehicle registration number.
2. Check each parking floor one by one.
3. Search every slot on the selected floor.
4. If a free slot is found, allocate it to the vehicle.
5. Return the floor number and slot number.
6. Stop searching after successful allocation.
7. If no free slot is available, return null.

Pseudocode – Slot Allocation

```
ALGORITHM AllocateSlot(vehicleNo)

BEGIN
    FOR each floor f in parking facility DO
        FOR each slot s in floor f DO

            IF slots[f][s] IS NULL THEN
                slots[f][s] = vehicleNo

                RETURN (f, s)
            END IF

        END FOR
    END FOR

    RETURN NULL

END
```

## 3. Parking Fee Calculation Logic

The parking fee is calculated based on the vehicle type and parking duration.

The system follows these sample fee rules:

| Vehicle Type     | First Hour | Each Additional Hour |
| ---------------- | ---------- | -------------------- |
| Two-Wheeler      | Rs. 10     | Rs. 10               |
| Car              | Rs. 30     | Rs. 30               |
| Electric Vehicle | Rs. 30     | Rs. 30               |

Fee calculation rules:

- The first hour is charged at the applicable vehicle rate.
- Every started additional hour is charged in full.
- A valid monthly pass for students and staff provides free parking.
- Visitors must pay the full parking fee.
- Electric vehicle charging fees can be added separately.

Pseudocode – Parking Fee Calculation

```
ALGORITHM CalculateFee(vehicleType, duration, userType, monthlyPass)

BEGIN

    IF userType IS Student OR Staff THEN
        IF monthlyPass IS VALID THEN
            RETURN 0
        END IF
    END IF

    IF duration <= 0 THEN
        RETURN 0
    END IF

    hours = duration / 60

    IF duration MOD 60 != 0 THEN
        hours = hours + 1
    END IF

    IF vehicleType = "TwoWheeler" THEN
        rate = 10

    ELSE IF vehicleType = "Car" THEN
        rate = 30

    ELSE IF vehicleType = "ElectricVehicle" THEN
        rate = 30

    ELSE
        RETURN INVALID VEHICLE TYPE
    END IF

    fee = hours * rate

    RETURN fee

END
```

## 4. Java Control Statements Used

| Control Statement | Purpose                                       |
| ----------------- | --------------------------------------------- |
| if                | Checks whether a parking slot is free.        |
| if-else           | Selects the applicable parking fee.           |
| for loop          | Searches floors and parking slots.            |
| break             | Stops searching when a free slot is found.    |
| continue          | Skips an occupied parking slot.               |
| return            | Returns the allocated slot or calculated fee. |

## 5. Complete the Code

The following Java program completes the two TODO sections given in the assignment.

FILE: ParkingFacility.java

```

public class ParkingFacility {

    // Slots hold vehicle numbers or null
    private final String[][] slots;

    public ParkingFacility(int[] slotsPerFloor) {
        slots = new String[slotsPerFloor.length][];

        for (int f = 0; f < slotsPerFloor.length; f++) {
            slots[f] = new String[slotsPerFloor[f]];
        }
    }

    /** Allocates the first available slot */
    public int[] allocate(String vehicleNo) {

        // TODO 1: Search floor by floor and slot by slot
        for (int f = 0; f < slots.length; f++) {

            for (int s = 0; s < slots[f].length; s++) {

                if (slots[f][s] == null) {

                    slots[f][s] = vehicleNo;

                    return new int[]{f, s};
                }
            }
        }

        return null;
    }

    /** Releases an occupied slot */
    public boolean release(int floor, int slot) {

        // TODO 2: Validate floor and slot
        if (floor < 0 || floor >= slots.length) {
            return false;
        }

        if (slot < 0 || slot >= slots[floor].length) {
            return false;
        }

        if (slots[floor][slot] == null) {
            return false;
        }

        slots[floor][slot] = null;

        return true;
    }

    public static void main(String[] args) {

        // Floor 0 has 2 slots, floor 1 has 1 slot
        ParkingFacility p =
            new ParkingFacility(new int[]{2, 1});

        System.out.println(java.util.Arrays.toString(
            p.allocate("TN01AB1111")));

        System.out.println(java.util.Arrays.toString(
            p.allocate("TN02CD2222")));

        System.out.println(java.util.Arrays.toString(
            p.allocate("TN03EF3333")));

        System.out.println(java.util.Arrays.toString(
            p.allocate("TN04GH4444")));

        System.out.println(
            p.release(0, 1) + " " +
            p.release(0, 1) + " " +
            p.release(5, 0));

        System.out.println(java.util.Arrays.toString(
            p.allocate("TN04GH4444")));
    }
}

```

## 6. Expected Output

Expected execution result

```
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

Output Explanation:

1. The first vehicle is allocated to floor 0, slot 0.
2. The second vehicle is allocated to floor 0, slot 1.
3. The third vehicle is allocated to floor 1, slot 0.
4. The fourth vehicle cannot be allocated because all slots are occupied.
5. The first release operation returns `true` because the slot is occupied.
6. The second release returns `false` because the slot is already free.
7. The third release returns `false` because floor 5 does not exist.
8. The fourth vehicle is successfully allocated to the released slot.

## 7. Alternative Concept and Complexity Analysis

Alternative: Using Free Slot Counters and a Stack

Instead of searching every floor and slot, the system can maintain:

- A counter to track the number of free slots on each floor.
- A stack to store the available slot numbers.

When a vehicle enters, the system takes an available slot from the stack. When a vehicle exits, the released slot is pushed back into the stack.

### Comparison

| Operation      | Current Approach           | Stack-Based Approach                 |
| -------------- | -------------------------- | ------------------------------------ |
| Allocation     | O(F × S) worst case        | O(1)                                 |
| Release        | O(1)                       | O(1)                                 |
| Extra space    | O(F × S) for parking slots | O(F × S) for stacks                  |
| Searching      | Sequential search          | No sequential search                 |
| Implementation | Simple                     | Requires additional stack management |

Where:

- F = Number of floors.
- S = Maximum number of slots per floor.

Advantages of the Current Approach:

- Simple to implement.
- Easy to understand.
- Does not require additional stack structures.

Advantages of the Stack-Based Approach:

- Faster allocation.
- Avoids searching all parking slots.
- Suitable for large parking facilities.

## 8. Conclusion

The control logic of the Campus Smart-Parking System successfully manages vehicle allocation, slot release and parking operations. The program uses loops, conditional statements, `break`, `continue` and `return` to perform operations efficiently. The completed Java program produces the expected output and correctly handles occupied slots, unavailable slots and invalid release operations.

The sequential searching approach is suitable for smaller parking facilities, whereas the stack-based approach provides better allocation performance for larger parking facilities.
