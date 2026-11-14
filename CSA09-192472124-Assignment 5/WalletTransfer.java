import java.sql.*;

public class WalletTransfer {
    public static void transfer(Connection con, long fromId, long toId, double amount) throws SQLException {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        boolean old = con.getAutoCommit(); con.setAutoCommit(false);
        try {
            try (PreparedStatement debit=con.prepareStatement("UPDATE wallet SET balance=balance-? WHERE wallet_id=? AND balance>=?")) {
                debit.setDouble(1,amount); debit.setLong(2,fromId); debit.setDouble(3,amount);
                if (debit.executeUpdate()!=1) throw new SQLException("Debit failed or insufficient balance");
            }
            try (PreparedStatement credit=con.prepareStatement("UPDATE wallet SET balance=balance+? WHERE wallet_id=?")) {
                credit.setDouble(1,amount); credit.setLong(2,toId);
                if (credit.executeUpdate()!=1) throw new SQLException("Credit failed");
            }
            con.commit();
        } catch (SQLException | RuntimeException e) {
            con.rollback(); throw e;
        } finally { con.setAutoCommit(old); }
    }
}
