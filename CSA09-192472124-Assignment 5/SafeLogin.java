import java.sql.*;
public class SafeLogin {
    public static boolean check(Connection c,String id,String password) throws SQLException {
        String sql="SELECT 1 FROM users WHERE id=? AND pwd=?";
        try(PreparedStatement p=c.prepareStatement(sql)){p.setString(1,id);p.setString(2,password);try(ResultSet r=p.executeQuery()){return r.next();}}
    }
}
