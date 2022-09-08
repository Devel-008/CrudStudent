package crud.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ReadStudent {
    public void select(Connection connect, Scanner sc){
        try {
            System.out.println("Press 1. to see all data \nPress 2. to see random data :=");
            int i = sc.nextInt();
            if (i == 2) {
                randomSelect(connect, sc);
            } else if (i == 1) {
                selectAll(connect);
            } else {
                System.out.println("Incorrect choice");
                System.exit(0);
            }

        }catch (Exception e){
            System.out.println(""+e);
            System.exit(0);
        }

    }
    public void randomSelect(Connection connect, Scanner sc){
        System.out.println("Enter the admission number whose data ou want to see :=");
        int rollno = sc.nextInt();
        int count = 0;
        try{
            String sql = "select * from student where rollno = " +rollno;
            PreparedStatement s1 = connect.prepareStatement(sql);
            ResultSet rs = s1.executeQuery();
            while(rs.next()){
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
                System.exit(0);
            }
            rs.close();
            s1.close();
            connect.close();
        }catch (Exception e){
            System.out.println(""+e);
            System.exit(0);
        }
    }

    public void selectAll(Connection connect) {
        int count = 0;
        try {
            Statement s1 = connect.createStatement();
            String sql = "select * from student";
            ResultSet rs = s1.executeQuery(sql);
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
                System.exit(0);
            }
            rs.close();
            s1.close();
           connect.close();
        } catch (Exception e) {
            System.out.println("ERROR:=" + e);
            System.exit(0);
        }
    }
}



