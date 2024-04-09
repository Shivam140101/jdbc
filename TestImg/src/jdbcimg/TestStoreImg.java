package jdbcimg;

import java.io.FileInputStream;
import java.sql.*;

public class TestStoreImg {

    public static void main(String[] args) {
        try {

            System.out.println("Start in try Block");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb4pm?useSSL=false","root","MySQL");
            System.out.println("Step 2 Get the Connection");

            Statement stmt = conn.createStatement();
            System.out.println("Step 3.........");

           // int x1 = stmt.executeUpdate("create table studentImg2(id int , name varchar(255) , logo BLOB) ");
           // System.out.println("Table is created successfully"+x1);

                              // Inserting Values

            String query = "insert into studentImg2 (id,name,logo) values(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,101);
            pstmt.setString(2,"Shivam");

            FileInputStream fis = new FileInputStream("E:\\Notepad\\Image\\Shivam.jpeg");
            System.out.println("Image is read successfully");

            pstmt.setBinaryStream(3,fis);
            pstmt.execute();

            System.out.println("Data Inserted");
            ResultSet rs = stmt.executeQuery("select * from studentImg2");
            while (rs.next()){
                System.out.print("Id: "+rs.getInt("id")+", ");
                System.out.print("name: "+rs.getString("name")+", ");
                System.out.print("logo: "+rs.getBlob("logo"));
                System.out.println();
            }
            System.out.println("End of try block");

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End of main method");
    }
}
