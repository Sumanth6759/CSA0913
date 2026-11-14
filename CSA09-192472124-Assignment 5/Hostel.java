import java.io.*; import java.util.*;

class Resident implements Serializable {
    private static final long serialVersionUID = 1L;
    String name, registerNo, phoneNumber;
    transient String password; // do not persist credentials
    Resident(String n,String r,String phone){name=n;registerNo=r;phoneNumber=phone;}
}
class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    String roomNo; List<Resident> residents=new ArrayList<>();
    Room(String no){roomNo=no;}
}
public class Hostel implements Serializable {
    private static final long serialVersionUID = 1L;
    List<Room> rooms=new ArrayList<>();
    transient Object liveDatabaseConnection; // replace with java.sql.Connection in real application
    public static void main(String[] args) throws Exception {
        Hostel h=new Hostel(); Room r=new Room("A-101"); r.residents.add(new Resident("Arun","192311001","9000000000")); h.rooms.add(r);
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("hostel.dat"))){out.writeObject(h);}
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("hostel.dat"))){Hostel copy=(Hostel)in.readObject();System.out.println("Rooms loaded: "+copy.rooms.size());System.out.println("Resident: "+copy.rooms.get(0).residents.get(0).name);}
    }
}
