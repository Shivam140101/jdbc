package myjdbc;

import java.sql.*;
import java.util.Scanner;

public class TestMysql {

    public static void main(String[] args) {

        System.out.println("Start in Main..");
        int Id = 0;
        String Name=" ";
        String Address = " ";
        int Marks = 0;


        try {
            System.out.println("Start in try....");
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Step 1 Load the Driver Class");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4pm?useSSL=false","root","MySQL");
            System.out.println("Step 2 Get the Connection "+conn);

            Statement stmt = conn.createStatement();
            System.out.println("Step 3 Get the Statement Object ");

           // int x = stmt.executeUpdate("create table student(id int primary key , name varchar(255) ,  Address varchar(255) , marks int)");
           // System.out.println("The table is created Successfully "+x);


            Scanner sc= new Scanner(System.in);
            System.out.print("Enter Student Id :- ");
            Id = sc.nextInt();

            System.out.print("Enter Student Name :- ");
            Name = sc.next();

            System.out.print("Enter Student Address :- ");
            Address = sc.next();

            System.out.print("Enter Student Marks :- ");
            Marks = sc.nextInt();

            String insertqry = "insert into student values("+Id+",'"+Name+"','"+Address+"',"+Marks+")";
            System.out.println("Insert query :=> "+insertqry);
            int x2 = stmt.executeUpdate(insertqry);
            System.out.println("Step 3 Record is Updated Successfully "+x2);

            ResultSet rs = stmt.executeQuery("select * from student");
            System.out.println("Step 4 Get the ResultSet ");
            System.out.println("Id\tName\tAddress\tMarks");
            while (rs.next()){
                System.out.print(rs.getInt("Id"));
                System.out.print("\t"+rs.getString("Name"));
                System.out.print("\t"+rs.getString("Address"));
                System.out.println("\t"+rs.getInt("Marks"));
            }
            conn.close();
            System.out.println("Step 5 Close Connection ");


            System.out.println("End of try block..........");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
