package crud.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadStudent {

    public void select(Connection connect) {
        int count = 0;
        try {
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
            rs.close();
            s1.close();
           connect.close();
        } catch (Exception e) {
            System.out.println("ERROR:=" + e);
        }
    }
}



