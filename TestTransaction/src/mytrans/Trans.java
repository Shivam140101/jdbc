package mytrans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Trans {
    public static void main(String[] args) {
        System.out.println("Test");
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Step 1.......");

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
            System.out.println("Step 2...........");

            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.executeUpdate("insert into employee4pm values('Ritika','06TCS','Soft-Er',30000)");
            stmt.executeUpdate("update employee4pm set DESIGNATION='PM' where id='01TCS'");
            stmt.executeUpdate("delete from employee4pm where id='03IBM'");

            ResultSet rs = stmt.executeQuery("select * from employee4pm");
            System.out.println("Before RollBack");
            System.out.println("Name\tId\tDesignation\tSalary");
            while (rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("\t"+rs.getString(2));
                System.out.print("\t"+rs.getString(3));
                System.out.println("\t\t"+rs.getString(4));
            }
            conn.rollback();
            ResultSet rs1 = stmt.executeQuery("select * from employee4pm");
            System.out.println("After RollBack");
            System.out.println("Name\tId\tDesignation\tSalary");
            while (rs1.next()){
                System.out.print(rs1.getString(1));
                System.out.print("\t"+rs1.getString(2));
                System.out.print("\t"+rs1.getString(3));
                System.out.println("\t\t"+rs1.getString(4));
            }
            //conn.close();



        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
