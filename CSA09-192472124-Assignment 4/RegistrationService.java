import java.sql.*;

public class RegistrationService {
    public String register(String studentId, String eventName) {
        if (studentId == null || studentId.trim().isEmpty() || eventName == null || eventName.trim().isEmpty())
            return "Enter student ID and select an event.";
        String sql = "INSERT INTO registrations(student_id, event_name) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentId.trim());
            ps.setString(2, eventName);
            ps.executeUpdate();
            return "Registration successful.";
        } catch (SQLIntegrityConstraintViolationException ex) {
            return "Duplicate registration: this student is already registered for this event.";
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) return "Duplicate registration: this student is already registered for this event.";
            return "Database error: " + ex.getMessage();
        }
    }

    public ResultSetPage loadPage(String event, int limit, int offset) throws SQLException {
        String sql = "SELECT id, student_id, event_name, registered_at FROM registrations " +
                     "WHERE (? = 'All' OR event_name = ?) ORDER BY id LIMIT ? OFFSET ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, event); ps.setString(2, event);
            ps.setInt(3, limit); ps.setInt(4, offset);
            try (ResultSet rs = ps.executeQuery()) {
                java.util.List<String[]> rows = new java.util.ArrayList<>();
                while (rs.next()) rows.add(new String[]{
                    String.valueOf(rs.getInt("id")), rs.getString("student_id"),
                    rs.getString("event_name"), String.valueOf(rs.getTimestamp("registered_at"))
                });
                return new ResultSetPage(rows);
            }
        }
    }
    public static class ResultSetPage {
        public final java.util.List<String[]> rows;
        public ResultSetPage(java.util.List<String[]> rows) { this.rows = rows; }
    }
}
