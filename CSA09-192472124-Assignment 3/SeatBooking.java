public class SeatBooking {
    static class Cinema {
        private int availableSeats = 5;

        synchronized void book(String customer, int seats) {
            if (seats <= availableSeats) {
                System.out.println(customer + " booked " + seats + " seat(s).");
                availableSeats -= seats;
            } else {
                System.out.println(customer + ": booking failed. Only "
                        + availableSeats + " seat(s) left.");
            }
        }

        synchronized int getAvailableSeats() {
            return availableSeats;
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        Thread a = new Thread(() -> cinema.book("Customer A", 3));
        Thread b = new Thread(() -> cinema.book("Customer B", 3));
        Thread c = new Thread(() -> cinema.book("Customer C", 1));

        a.start();
        b.start();
        c.start();

        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Available seats at end: "
                + cinema.getAvailableSeats());
    }
}
