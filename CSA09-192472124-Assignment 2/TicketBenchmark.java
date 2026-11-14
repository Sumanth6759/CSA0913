import java.util.*;

public class TicketBenchmark {
    static final int RUNS = 5;

    static String usingString(int fields) {
        String ticket = "";
        for (int i = 1; i <= fields; i++) ticket += "FIELD" + i + "=VALUE" + i + ";";
        return ticket;
    }

    static String usingBuilder(int fields) {
        StringBuilder ticket = new StringBuilder();
        for (int i = 1; i <= fields; i++) ticket.append("FIELD").append(i).append("=VALUE").append(i).append(';');
        return ticket.toString();
    }

    static String usingBuffer(int fields) {
        StringBuffer ticket = new StringBuffer();
        for (int i = 1; i <= fields; i++) ticket.append("FIELD").append(i).append("=VALUE").append(i).append(';');
        return ticket.toString();
    }

    static long measure(String method, int tickets, int fields) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int t = 0; t < tickets; t++) {
            String result;
            if (method.equals("String")) result = usingString(fields);
            else if (method.equals("StringBuilder")) result = usingBuilder(fields);
            else result = usingBuffer(fields);
            checksum += result.length();
        }
        long elapsed = System.nanoTime() - start;
        if (checksum == 0) System.out.println(" ");
        return elapsed / 1_000_000;
    }

    public static void main(String[] args) {
        int[][] workloads = {{1, 6}, {1000, 20}, {10000, 50}};
        String[] methods = {"String", "StringBuilder", "StringBuffer"};
        System.out.println("Q1 - Railway e-Ticket Benchmark (time in milliseconds)");
        for (int[] w : workloads) {
            System.out.println("\nTickets: " + w[0] + ", Fields per ticket: " + w[1]);
            for (String method : methods) {
                long total = 0;
                for (int r = 0; r < RUNS; r++) total += measure(method, w[0], w[1]);
                System.out.println(method + " average: " + (total / (double) RUNS) + " ms");
            }
        }
        System.out.println("\nRecommendation: StringBuilder for a single ticket; use a synchronized/thread-safe design for shared logs.");
    }
}
