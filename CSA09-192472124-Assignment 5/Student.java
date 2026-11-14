public class Student {
    private final String registerNo, name, email, department;
    public Student(String registerNo, String name, String email, String department) {
        this.registerNo=registerNo; this.name=name; this.email=email; this.department=department;
    }
    public String getRegisterNo(){return registerNo;} public String getName(){return name;}
    public String getEmail(){return email;} public String getDepartment(){return department;}
    public String toString(){return registerNo+" | "+name+" | "+email+" | "+department;}
}
