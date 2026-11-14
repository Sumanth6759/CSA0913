import java.io.*;

public class ResourceReport {
    public static void main(String[] args) {
        double total = 0;

        // try-with-resources closes the reader automatically.
        try (BufferedReader reader =
                 new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    total += Double.parseDouble(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid amount: " + line);
                }
            }
            System.out.printf("Total sales: %.2f%n", total);
        } catch (IOException e) {
            System.out.println("Unable to read sales.txt: " + e.getMessage());
        }
    }
}
