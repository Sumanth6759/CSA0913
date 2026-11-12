import java.time.LocalDateTime;

// Interface for electric vehicles
interface Chargeable {
    double chargingFee(double units);
}

// Abstract parent class
abstract class Vehicle {
    private String regNo;

    public Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Invalid registration number"
            );
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double ratePerHour();

    public double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Duration cannot be negative"
            );
        }

        if (minutes == 0) {
            return 0;
        }

        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }
}

// Two-wheeler class
class TwoWheeler extends Vehicle {
    public TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    public double ratePerHour() {
        return 10;
    }
}

// Car class
class Car extends Vehicle {
    public Car(String regNo) {
        super(regNo);
    }

    @Override
    public double ratePerHour() {
        return 30;
    }
}

// Electric car class
class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(double units) {
        if (units < 0) {
            throw new IllegalArgumentException(
                "Charging units cannot be negative"
            );
        }
        return units * 12;
    }
}

// Parking ticket class
class ParkingTicket {
    private static int counter = 1000;

    private final int ticketNo;
    private final Vehicle vehicle;
    private final int floor;
    private final int slot;
    private final String userType;
    private final boolean validMonthlyPass;
    private final LocalDateTime entryTime;

    public ParkingTicket(
        Vehicle vehicle,
        int floor,
        int slot,
        String userType,
        boolean validMonthlyPass
    ) {
        this.ticketNo = ++counter;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.userType = userType;
        this.validMonthlyPass = validMonthlyPass;
        this.entryTime = LocalDateTime.now();
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public String getUserType() {
        return userType;
    }

    public boolean hasValidMonthlyPass() {
        return validMonthlyPass;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}

// Main parking facility class
class ParkingFacility {

    private final Vehicle[][] slots;
    private final ParkingTicket[] activeTickets;

    private int ticketCount;
    private double totalCollection;

    public ParkingFacility(int[] slotsPerFloor, int maxVehicles) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0
                || maxVehicles <= 0) {
            throw new IllegalArgumentException(
                "Invalid parking facility configuration"
            );
        }

        slots = new Vehicle[slotsPerFloor.length][];

        for (int i = 0; i < slotsPerFloor.length; i++) {
            if (slotsPerFloor[i] <= 0) {
                throw new IllegalArgumentException(
                    "Each floor must have at least one slot"
                );
            }
            slots[i] = new Vehicle[slotsPerFloor[i]];
        }

        activeTickets = new ParkingTicket[maxVehicles];
        ticketCount = 0;
        totalCollection = 0;
    }

    // Check whether a vehicle is already parked
    private boolean isDuplicate(String regNo) {
        for (int i = 0; i < ticketCount; i++) {
            if (activeTickets[i].getVehicle()
                    .getRegNo().equalsIgnoreCase(regNo)) {
                return true;
            }
        }
        return false;
    }

    // Park a vehicle and issue a ticket
    public ParkingTicket parkVehicle(
        Vehicle vehicle,
        String userType,
        boolean validMonthlyPass
    ) {
        if (vehicle == null || userType == null) {
            throw new IllegalArgumentException(
                "Vehicle and user type are required"
            );
        }

        if (!userType.equalsIgnoreCase("STUDENT")
                && !userType.equalsIgnoreCase("STAFF")
                && !userType.equalsIgnoreCase("VISITOR")) {
            throw new IllegalArgumentException(
                "Unknown user type"
            );
        }

        if (isDuplicate(vehicle.getRegNo())) {
            System.out.println(
                "Rejected: Duplicate registration number "
                + vehicle.getRegNo()
            );
            return null;
        }

        if (ticketCount >= activeTickets.length) {
            System.out.println("Ticket storage is full");
            return null;
        }

        // Find the first available slot
        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {

                if (slots[f][s] == null) {
                    slots[f][s] = vehicle;

                    ParkingTicket ticket = new ParkingTicket(
                        vehicle, f, s, userType.toUpperCase(),
                        validMonthlyPass
                    );

                    activeTickets[ticketCount++] = ticket;

                    System.out.println(
                        vehicle.getRegNo()
                        + " parked at Floor " + f
                        + ", Slot " + s
                        + " | Ticket: " + ticket.getTicketNo()
                    );

                    return ticket;
                }
            }
        }

        System.out.println("Parking facility is full");
        return null;
    }

    // Check whether a floor and slot position is valid
    public boolean isValidSlot(int floor, int slot) {
        return floor >= 0 && floor < slots.length
            && slot >= 0 && slot < slots[floor].length;
    }

    // Vehicle exit and fee calculation
    public double exitVehicle(
        String regNo,
        int durationMinutes,
        double chargingUnits
    ) {
        if (durationMinutes < 0 || chargingUnits < 0) {
            throw new IllegalArgumentException(
                "Duration and charging units cannot be negative"
            );
        }

        for (int i = 0; i < ticketCount; i++) {

            ParkingTicket ticket = activeTickets[i];

            if (ticket.getVehicle().getRegNo()
                    .equalsIgnoreCase(regNo)) {

                Vehicle vehicle = ticket.getVehicle();

                double fee = 0;

                boolean freePass =
                    ticket.hasValidMonthlyPass()
                    && (ticket.getUserType().equals("STUDENT")
                    || ticket.getUserType().equals("STAFF"));

                if (!freePass) {
                    fee = vehicle.calculateFee(durationMinutes);

                    if (vehicle instanceof Chargeable) {
                        Chargeable ev = (Chargeable) vehicle;
                        fee += ev.chargingFee(chargingUnits);
                    }
                }

                slots[ticket.getFloor()][ticket.getSlot()] = null;

                // Remove the active ticket from the array
                for (int j = i; j < ticketCount - 1; j++) {
                    activeTickets[j] = activeTickets[j + 1];
                }

                activeTickets[--ticketCount] = null;

                totalCollection += fee;

                System.out.println(
                    regNo + " exited successfully."
                );
                System.out.println("Parking Fee: Rs. " + fee);

                return fee;
            }
        }

        System.out.println("No active ticket found for " + regNo);
        return -1;
    }

    // Test invalid slot positions
    public void testInvalidSlot(int floor, int slot) {
        if (!isValidSlot(floor, slot)) {
            System.out.println(
                "Invalid slot: Floor " + floor
                + ", Slot " + slot
            );
        } else {
            System.out.println("Valid slot position");
        }
    }

    public double getTotalCollection() {
        return totalCollection;
    }
}

// Main class
public class ParkingSystem {

    public static void main(String[] args) {

        // Two floors with two slots each
        ParkingFacility parking =
            new ParkingFacility(new int[]{2, 2}, 10);

        System.out.println("===== VEHICLE ENTRY =====");

        // Normal vehicle entries
        parking.parkVehicle(
            new TwoWheeler("TN01AB1111"),
            "VISITOR", false
        );

        parking.parkVehicle(
            new Car("TN02CD2222"),
            "STUDENT", true
        );

        parking.parkVehicle(
            new ElectricCar("TN03EF3333"),
            "VISITOR", false
        );

        // Last free slot
        parking.parkVehicle(
            new Car("TN04GH4444"),
            "VISITOR", false
        );

        // Parking full boundary case
        parking.parkVehicle(
            new TwoWheeler("TN05IJ5555"),
            "VISITOR", false
        );

        System.out.println("\n===== INVALID CASES =====");

        // Duplicate registration
        parking.parkVehicle(
            new Car("TN01AB1111"),
            "VISITOR", false
        );

        // Unknown vehicle type
        try {
            Vehicle unknown =
                createVehicle("TN06KL6666", "BUS");

            parking.parkVehicle(
                unknown, "VISITOR", false
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Invalid slot number
        parking.testInvalidSlot(5, 10);

        System.out.println("\n===== VEHICLE EXIT =====");

        // Two-wheeler: 120 minutes = 2 hours x Rs.10
        parking.exitVehicle("TN01AB1111", 120, 0);

        // Student with valid monthly pass: free
        parking.exitVehicle("TN02CD2222", 60, 0);

        // EV: 60 minutes parking + 10 charging units
        // Rs.30 parking + Rs.120 charging = Rs.150
        parking.exitVehicle("TN03EF3333", 60, 10);

        // Zero-minute parking
        parking.exitVehicle("TN04GH4444", 0, 0);

        System.out.println("\n===== FINAL COLLECTION =====");
        System.out.println(
            "Total Collection: Rs. "
            + parking.getTotalCollection()
        );
    }

    // Factory method for vehicle creation
    public static Vehicle createVehicle(
        String regNo,
        String type
    ) {
        if (type.equalsIgnoreCase("CAR")) {
            return new Car(regNo);
        } else if (type.equalsIgnoreCase("TWOWHEELER")) {
            return new TwoWheeler(regNo);
        } else if (type.equalsIgnoreCase("EV")) {
            return new ElectricCar(regNo);
        } else {
            throw new IllegalArgumentException(
                "Unknown vehicle type: " + type
            );
        }
    }
}
