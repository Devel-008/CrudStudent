package crud.student;

import java.sql.*;
import java.util.Scanner;

public class Update {
    public void updateRecord(Connection connect, Scanner sc) {

        System.out.println("Enter Admission number of student whose record you want to update:=");
        int id = sc.nextInt();

        System.out.println("What you want to update:=\n 1]name\n 2]fathername\n 3]address\n 4]dob\n 5]english\n 6]hindi\n 7]maths\n 8]science\n 9]social");
        String choice = sc.next();

        if (choice.equals("name") || choice.equals("fathername") || choice.equals("address") || choice.equals("dob")) {

            System.out.println("Update " +choice+ ":=");
            String update = sc.nextLine();

            String query = "update student set" + choice +"= ? where rollno = "+id;
            try {
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, update);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("english") || choice.equals("hindi") || choice.equals("maths") || choice.equals("science") || choice.equals("social")) {
            System.out.println("Update " + choice + ":=");
            float update = sc.nextFloat();

            String query = "update student set"+ choice +"= ? where rollno = ?";

            try {
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setFloat(1, update);
                preparedStatement.setInt(2, id);

                preparedStatement.executeUpdate();
                System.out.println("Updated");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String query2 = "update student set percentage = ? where rollno = ?";
            String query3 = "select english,hindi, science, maths, social from student where rollno =" + id;

            try {
                PreparedStatement preparedStatement = connect.prepareStatement(query2);
                Statement psmt = connect.createStatement();
                ResultSet rs = psmt.executeQuery(query3);
                rs.next();

                float english = rs.getFloat("english");
                float hindi = rs.getFloat("hindi");
                float maths = rs.getFloat("maths");
                float science = rs.getFloat("science");
                float social = rs.getFloat("social");
                float total = english + hindi + maths + science + social;
                float percent = (total * 100) / 500;

                preparedStatement.setInt(2, id);
                preparedStatement.setFloat(1, percent);
                System.out.println("Percentage Updated!!!");

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }

    }

}