import java.sql.*; import java.util.*;

public class StudentDao implements Dao<Student, String> {
    private final Connection con;
    public StudentDao(Connection con) { this.con = con; }
    public void insert(Student s) throws SQLException {
        String q="INSERT INTO student(register_no,name,email,department) VALUES(?,?,?,?)";
        try(PreparedStatement p=con.prepareStatement(q)){ bind(p,s); p.executeUpdate(); }
    }
    private void bind(PreparedStatement p, Student s) throws SQLException {
        p.setString(1,s.getRegisterNo()); p.setString(2,s.getName()); p.setString(3,s.getEmail()); p.setString(4,s.getDepartment());
    }
    private Student map(ResultSet r) throws SQLException { return new Student(r.getString("register_no"),r.getString("name"),r.getString("email"),r.getString("department")); }
    public Optional<Student> findById(String id) throws SQLException {
        try(PreparedStatement p=con.prepareStatement("SELECT * FROM student WHERE register_no=?")){p.setString(1,id);try(ResultSet r=p.executeQuery()){return r.next()?Optional.of(map(r)):Optional.empty();}}
    }
    public List<Student> findAll() throws SQLException {
        List<Student> out=new ArrayList<>(); try(Statement s=con.createStatement();ResultSet r=s.executeQuery("SELECT * FROM student")){while(r.next())out.add(map(r));} return out;
    }
    public boolean update(Student s) throws SQLException {
        String q="UPDATE student SET name=?,email=?,department=? WHERE register_no=?";
        try(PreparedStatement p=con.prepareStatement(q)){p.setString(1,s.getName());p.setString(2,s.getEmail());p.setString(3,s.getDepartment());p.setString(4,s.getRegisterNo());return p.executeUpdate()==1;}
    }
    public boolean delete(String id) throws SQLException {try(PreparedStatement p=con.prepareStatement("DELETE FROM student WHERE register_no=?")){p.setString(1,id);return p.executeUpdate()==1;}}
}
