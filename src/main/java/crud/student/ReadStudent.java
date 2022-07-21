package crud.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ReadStudent {
    String url = "jdbc:postgresql://localhost:5432/reportcard";
    String username = "postgres";
    String password = "isha@123";
    Scanner sc = new Scanner(System.in);

    public void select() throws Exception {
        int count = 0;
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        } else {
            Statement s1 = connect.createStatement();
            String se = "select * from student";
            ResultSet rs = s1.executeQuery(se);


            while (rs.next()) {
                System.out.print("Roll-No:" + rs.getInt("rollno") + " ");
                System.out.print(", Name: " + rs.getString("fullname") + " ");
                System.out.print(", Father's name: " + rs.getString("fathername"));
                System.out.println(", Address: " + rs.getString("address") + " ");
                System.out.print(", Date-of-Birth: " + rs.getString("dob") + " ");
                System.out.print(", English: " + rs.getFloat("english") + " ");
                System.out.print(", Maths: " + rs.getFloat("maths") + " ");
                System.out.print(", Science: " + rs.getFloat("science") + " ");
                System.out.println(", Social Science: " + rs.getFloat("social") + " ");
                System.out.print(", Percentage: " + rs.getFloat("percentage") + " ");
                System.out.println(" ");
                count++;

            }
            if (count <= 0) {
                System.out.println("No data available");
            }


        }
    }


}
