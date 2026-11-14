public class ThreadDemo {
    static class NumberThread extends Thread {
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(getName() + " : " + i);
                try { Thread.sleep(200); }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberThread t1 = new NumberThread();
        NumberThread t2 = new NumberThread();
        t1.setName("Thread-1");
        t2.setName("Thread-2");

        System.out.println("State before start: " + t1.getState());
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Both threads completed.");
        System.out.println("State after completion: " + t1.getState());
    }
}
