import java.io.*;

public class ManualReport {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("sales.txt"));
            String line;
            double total = 0;
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
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File closed.");
                } catch (IOException e) {
                    System.out.println("Could not close file: " + e.getMessage());
                }
            }
        }
    }
}
