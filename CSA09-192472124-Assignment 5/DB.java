import java.sql.*;
public class DB {
    public static Connection get() throws SQLException {
        String url="jdbc:mysql://localhost:3306/transport_db";
        String user="root", password="CHANGE_ME";
        return DriverManager.getConnection(url,user,password);
    }
}
