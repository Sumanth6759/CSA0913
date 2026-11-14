import java.io.*;
import java.util.Scanner;

public class ATM {
    static class InvalidPinException extends Exception {
        InvalidPinException(String message) { super(message); }
    }
    static class InsufficientBalanceException extends Exception {
        InsufficientBalanceException(String message) { super(message); }
    }

    static final String CARD_FILE = "card.txt";
    static double balance = 5000.0;
    static final String CORRECT_PIN = "1234";

    static void validatePin(String pin) throws InvalidPinException {
        if (!CORRECT_PIN.equals(pin)) {
            throw new InvalidPinException("Invalid PIN. Access denied.");
        }
    }

    static void withdraw(double amount)
            throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        balance -= amount;
        System.out.printf("Please collect your cash: Rs. %.2f%n", amount);
        System.out.printf("Remaining balance: Rs. %.2f%n", balance);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            File card = new File(CARD_FILE);
            if (!card.exists()) {
                try (PrintWriter out = new PrintWriter(card)) {
                    out.println("ATM test card file");
                }
            }

            System.out.println("===== ATM SYSTEM =====");
            System.out.print("Enter PIN (test PIN: 1234): ");
            String pin = sc.nextLine().trim();
            validatePin(pin);

            System.out.printf("Available balance: Rs. %.2f%n", balance);
            System.out.print("Enter withdrawal amount: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            withdraw(amount);
        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IOException e) {
            System.out.println("Card file error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }
}
