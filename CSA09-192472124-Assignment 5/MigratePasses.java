import java.io.*;import java.nio.charset.StandardCharsets;import java.nio.file.*;import java.sql.*;import java.util.*;
public class MigratePasses {
    public static void main(String[] args)throws Exception{
        Path input=Paths.get(args.length>0?args[0]:"passes.csv");List<String[]> valid=new ArrayList<>();
        try(BufferedReader r=Files.newBufferedReader(input,StandardCharsets.UTF_8)){
            String line;int lineNo=0;while((line=r.readLine())!=null){lineNo++;String[] f=line.split(",",-1);
                try{if(f.length!=5)throw new IllegalArgumentException("expected 5 fields");Integer.parseInt(f[3]);double amount=Double.parseDouble(f[4]);if(amount<0)throw new IllegalArgumentException("negative amount");valid.add(f);}
                catch(RuntimeException ex){System.out.println("Rejected line "+lineNo+": "+ex.getMessage()+" -> "+line);}
            }
        }
        try(Connection c=DB.get()){System.out.println("Migrated "+PassLoader.loadAll(c,valid)+" valid rows");}
    }
}
