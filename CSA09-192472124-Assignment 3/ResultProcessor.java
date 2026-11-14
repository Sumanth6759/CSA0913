import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ResultProcessor {
    private static final int STUDENT_COUNT = 10000;
    private static final int THREAD_COUNT = 4;

    static class ResultTask implements Callable<Integer> {
        private final int start;
        private final int end;

        ResultTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Integer call() throws Exception {
            int total = 0;
            for (int i = start; i <= end; i++) {
                File file = new File("student" + i + ".txt");
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine();
                    if (line != null) {
                        total += Integer.parseInt(line.trim());
                    }
                }
            }
            return total;
        }
    }

    public static void main(String[] args) throws Exception {
        // Create deterministic input files if they are not already present.
        for (int i = 1; i <= STUDENT_COUNT; i++) {
            File file = new File("student" + i + ".txt");
            if (!file.exists()) {
                try (PrintWriter out = new PrintWriter(file)) {
                    out.println(i % 101);
                }
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<Integer>> results = new ArrayList<>();

        int chunk = STUDENT_COUNT / THREAD_COUNT;
        for (int t = 0; t < THREAD_COUNT; t++) {
            int start = t * chunk + 1;
            int end = (t == THREAD_COUNT - 1)
                    ? STUDENT_COUNT : (t + 1) * chunk;
            results.add(pool.submit(new ResultTask(start, end)));
        }

        int grandTotal = 0;
        try {
            for (Future<Integer> result : results) {
                grandTotal += result.get();
            }
            System.out.println("Students processed: " + STUDENT_COUNT);
            System.out.println("Total marks: " + grandTotal);
            System.out.println("Processing completed successfully.");
        } catch (ExecutionException e) {
            System.out.println("Processing failed: " + e.getCause());
        } finally {
            pool.shutdown();
        }
    }
}
