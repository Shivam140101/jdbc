package myjdbc;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.Properties;

public class TestOracleDriver3 {

    public static void main(String[] args) {
        System.out.println("2nd way to Load the Driver Class ");

        try{
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            System.out.println("Step 1................");
            Properties prop = new Properties();
            prop.put("user","system");
            prop.put("password","Oracle");
            Connection conn = driver.connect("jdbc:oracle:thin:@localhost:1521:xe",prop);

           // Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
            System.out.println("Step 2..............");

            Statement stmt = conn.createStatement();
            System.out.println("Step 3 Get the Statement Object ");

            ResultSet rs =  stmt.executeQuery("select * from employee");
            System.out.println("Step4 get rs object    ");
            System.out.println("Name\tId\tDesgination\tsalary ");
            while(rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("\t"+rs.getString(2));
                System.out.print("\t"+rs.getString(3));
                System.out.println("\t\t"+rs.getString(4));
            }



        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("End of main");

    }
}
