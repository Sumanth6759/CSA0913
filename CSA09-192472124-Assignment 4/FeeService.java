public class FeeService {
    public String pay(String regNo, String amountText) {
        if (regNo == null || regNo.trim().isEmpty())
            return "Enter registration number.";
        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (Exception ex) {
            return "Enter a valid amount.";
        }
        if (amount <= 0) return "Amount must be greater than zero.";
        // Payment processing belongs here; this demo simulates bank confirmation.
        return String.format("Payment successful: Rs. %.2f", amount);
    }
}
