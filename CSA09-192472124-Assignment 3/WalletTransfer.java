public class WalletTransfer {
    static class Wallet {
        final int id;
        int balance;
        Wallet(int id, int balance) { this.id = id; this.balance = balance; }
    }

    static void transfer(Wallet from, Wallet to, int amount) {
        // Lock in a consistent order to avoid deadlock.
        Wallet first = from.id < to.id ? from : to;
        Wallet second = from.id < to.id ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (amount <= 0 || from.balance < amount) {
                    System.out.println("Transfer rejected.");
                    return;
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("Transferred Rs. " + amount);
            }
        }
    }

    public static void main(String[] args) {
        Wallet a = new Wallet(1, 1000);
        Wallet b = new Wallet(2, 1000);

        Thread t1 = new Thread(() -> transfer(a, b, 200));
        Thread t2 = new Thread(() -> transfer(b, a, 300));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Wallet A: " + a.balance);
        System.out.println("Wallet B: " + b.balance);
        System.out.println("Total: " + (a.balance + b.balance));
    }
}
