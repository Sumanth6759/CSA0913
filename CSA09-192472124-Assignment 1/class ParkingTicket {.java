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