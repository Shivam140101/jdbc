package myjdbc;

import java.sql.*;
import java.util.Scanner;

public class TestOracle {

    public static void main(String[] args) {
        System.out.println("Test .....");

        int id = 0;
        String name;
        String Address;
        int marks = 0;

        try {

            System.out.println("Start in try block.....");
            Class.forName("oracle.jdbc.driver.OracleDriver");

            System.out.println("Step 1 Load the Driver Class....");

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
            System.out.println("Step 2 Get the Connection"+conn);

            Statement stmt = conn.createStatement();
            System.out.println("Step 3 Get the stmt object");
           // int x = stmt.executeUpdate("create table student4pm(id number primary key , name varchar2(255) , Address varchar2(255) , marks number)");
           // System.out.println("The table is created successfully "+x);

            // int x1 = stmt.executeUpdate("insert into student4pm values(101,'Shiv','PKL',100)");
            // System.out.println("One record is inserted Successfully.... "+x1);

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Student id :- ");
            id = sc.nextInt();

            System.out.println("Enter Student Name :- ");
            name = sc.next();

            System.out.println("Enter Student Address :- ");
            Address = sc.next();

            System.out.println("Enter Student Marks :- ");
            marks = sc.nextInt();

            String insertqry = "insert into student4pm values("+id+",'"+name+"','"+Address+"',"+marks+")";
            System.out.println("Insert query :=> "+insertqry);

            int x1 = stmt.executeUpdate(insertqry);
            System.out.println("Record is inserted Successfully "+x1);

            ResultSet rs = stmt.executeQuery("select * from student4pm");
            System.out.println("Step 4 Get the ResultSet");
            System.out.println("Id\tName\tAddress\tMarks");
            while (rs.next()){
                System.out.print(rs.getInt("id"));
                System.out.print("\t"+rs.getString("name"));
                System.out.print("\t"+rs.getString("Address"));
                System.out.println("\t"+rs.getInt("marks"));
            }

            conn.close();
            System.out.println("Step 5 Close Connection");

            System.out.println("End of try block......");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
