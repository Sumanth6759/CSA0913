public class RunnableDemo {
    static class PrintTask implements Runnable {
        private final String label;
        PrintTask(String label) { this.label = label; }

        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(label + " : " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintTask("Runnable-A"));
        Thread t2 = new Thread(new PrintTask("Runnable-B"));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Runnable tasks completed.");
    }
}
