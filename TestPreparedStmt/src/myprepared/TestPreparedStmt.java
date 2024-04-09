package myprepared;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class TestPreparedStmt {

    public static void main(String[] args) {
        System.out.println("Prepared Stmt");

        try {
           // FileInputStream fis = new FileInputStream("src/resource/mysql.properties");
           // FileInputStream fis = new FileInputStream("src/resource/oracle.properties");
            FileInputStream fis = new FileInputStream("src/resource/postgres.properties");


            System.out.println("Read File properties");
            Properties prop = new Properties();
            prop.load(fis);
            String drivername = (String) prop.get("driverclassname");
            String url = (String) prop.get("url");
            String user = (String) prop.get("user");
            String password = (String) prop.get("pass");
            Class.forName(drivername);
            System.out.println("Step 1 Load the Driver Class");

            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Step 2 Get Connection");

            Statement stmt = conn.createStatement();
            System.out.println("Get the stmt object");

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            // int x = stmt.executeUpdate("create table employee4pm(name varchar(255) , id varchar(255) , Designation varchar(255) , Salary int)");
            // System.out.println("Table is created successfully "+x);

            System.out.println("====================== Insert Into DB =========================");


            System.out.println("Enter a Name of Employee ");
            String name = br.readLine();

            System.out.println("Enter Id of Employee ");
            String id = br.readLine();

            System.out.println("Enter Designation of Employee");
            String desig = br.readLine();

            System.out.println("Enter Salary of Employee ");
            int sal = Integer.parseInt(br.readLine());

            PreparedStatement ps = conn.prepareStatement("insert into employee4pm values(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, desig);
            ps.setInt(4, sal);
            int x6 = ps.executeUpdate();
            System.out.println("Insert Successfully...."+x6);
            ps.close();


            System.out.println("=========== Update into DB ======================");
/*

            System.out.println("Enter a Name of Employee ");
            String name = br.readLine();

            System.out.println("Enter Id of Employee ");
            String id = br.readLine();

            System.out.println("Enter Designation of Employee");
            String desig = br.readLine();

            System.out.println("Enter Salary of Employee ");
            int sal = Integer.parseInt(br.readLine());

            PreparedStatement ps1 = conn.prepareStatement("update employee4pm set name=?, Designation=?, Salary=? where id=?");
            ps1.setString(1, name);
            ps1.setString(2, desig);
            ps1.setInt(3, sal);
            ps1.setString(4, id);
            int x1 = ps1.executeUpdate();
            System.out.println("Updated Successfully...."+x1);
            ps1.close();

 */

            System.out.println("===================== Delete Database ==========================  ");


            PreparedStatement ps2 = conn.prepareStatement("delete from employee4pm where id=?");

            ps2.setString(1, "04HCL");
            int x2 = ps2.executeUpdate();
            System.out.println("Updated Successfully...."+ x2);
            ps2.close();

            PreparedStatement ps3 = conn.prepareStatement("select * from employee4pm where salary=?");
            ps3.setInt(1,25000);
            System.out.println("Get Particular Record");
            ResultSet rs  = ps3.executeQuery();
            System.out.println("Name\tId\tDesignation\tSalary");
            while (rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("\t"+rs.getString(2));
                System.out.print("\t"+rs.getString(3));
                System.out.println("\t\t"+rs.getString(4));
            }
            ps3.close();


            System.out.println("========Get All Record=========");
            PreparedStatement ps4 = conn.prepareStatement("select * from employee4pm");
            ResultSet rs1  = ps4.executeQuery();
            System.out.println("Name\tId\tDesignation\tSalary");
            while (rs1.next()){
                System.out.print(rs1.getString(1));
                System.out.print("\t"+rs1.getString(2));
                System.out.print("\t"+rs1.getString(3));
                System.out.println("\t\t"+rs1.getString(4));
            }
            ps4.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
