import java.io.*;
import java.nio.file.*;

public class LineCounter {
    static long countBuffered(Path p) throws IOException {
        long lines = 0;
        try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(p), 64 * 1024)) {
            byte[] buffer = new byte[64 * 1024]; int n;
            while ((n = in.read(buffer)) != -1)
                for (int i = 0; i < n; i++) if (buffer[i] == '\n') lines++;
        }
        return lines;
    }
    static long countUnbuffered(Path p) throws IOException {
        long lines = 0;
        try (InputStream in = Files.newInputStream(p)) {
            int b; while ((b = in.read()) != -1) if (b == '\n') lines++;
        }
        return lines;
    }
    public static void main(String[] args) throws Exception {
        if (args.length == 0) { System.out.println("Usage: java LineCounter <file>"); return; }
        Path p = Paths.get(args[0]);
        long t = System.nanoTime(); long a = countUnbuffered(p); long u = System.nanoTime() - t;
        t = System.nanoTime(); long b = countBuffered(p); long v = System.nanoTime() - t;
        System.out.println("Unbuffered lines=" + a + " time_ms=" + u / 1_000_000.0);
        System.out.println("Buffered   lines=" + b + " time_ms=" + v / 1_000_000.0);
    }
}
