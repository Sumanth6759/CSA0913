import java.sql.*;
public class UnsafeAndSafeLogin {
    static boolean unsafe(Connection c,String id,String pwd)throws SQLException{
        String sql="SELECT * FROM users WHERE id='"+id+"' AND pwd='"+pwd+"'";
        try(Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){return r.next();}
    }
    static boolean safe(Connection c,String id,String pwd)throws SQLException{
        try(PreparedStatement p=c.prepareStatement("SELECT * FROM users WHERE id=? AND pwd=?")){
            p.setString(1,id);p.setString(2,pwd);try(ResultSet r=p.executeQuery()){return r.next();}
        }
    }
    // Test in a disposable local database only. Example crafted ID: admin' -- 
}
