package myprop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TestDriverProp {
    public static void main(String[] args) {
        System.out.println("Load the Driver Class using Properties file");
        try {
            System.out.println("Start in try block");
           // FileInputStream fis = new FileInputStream("dbinfo.properties");
           // FileInputStream fis = new FileInputStream("src/resource/oracledbinfo.properties");
            FileInputStream fis = new FileInputStream("src/resource/mysqldbinfo.properties");
            System.out.println("Read the properties file");
            Properties prop = new Properties();
            prop.load(fis);
            String dname = (String) prop.get("driverclassname");
            String url = (String) prop.get("url");
            String uname = (String) prop.get("user");
            String password = (String) prop.get("pass");
            System.out.println(dname+"\n"+url+"\n"+uname+"\n"+password);
            Class.forName(dname);
            System.out.println("Step 1..............");
            Connection conn = DriverManager.getConnection(url,uname,password);
            System.out.println("Step 2............");

            Statement stmt = conn.createStatement();
            System.out.println("Step 3 Get the Stmt object");

            int x = stmt.executeUpdate("create table employee4pm(name varchar(255) , id varchar(255) , Designation varchar(255) , Salary int)");
            System.out.println("Table is created successfully "+x);



            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Enter a Name of Employee ");
            String name = br.readLine();

            System.out.println("Enter Id of Employee ");
            String id = br.readLine();

            System.out.println("Enter Designation of Employee");
            String desig = br.readLine();

            System.out.println("Enter Salary of Employee ");
            int sal = Integer.parseInt(br.readLine());

            int x1 = stmt.executeUpdate("insert into employee4pm values('"+name+"','"+id+"','"+desig+"',"+sal+")");
            System.out.println("One Record inserted successfully.. "+x1);

            ResultSet rs = stmt.executeQuery("select * from employee4pm");
            System.out.println("Step 4 Get the rs object");
            System.out.println("Name\tId\tDesignation\tSalary");
            while (rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("\t"+rs.getString(2));
                System.out.print("\t"+rs.getString(3));
                System.out.println("\t\t"+rs.getString(4));
            }
            conn.close();
            System.out.println("Step 5 Close Connection");

            System.out.println("End of try block");

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("End of main");
    }
}
