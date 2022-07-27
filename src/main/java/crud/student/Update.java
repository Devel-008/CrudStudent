package crud.student;

import java.sql.*;
import java.util.Scanner;

public class Update {

    public void updateRecord(Connection connect, Scanner sc) {

        System.out.println("Enter Admission number of student whose record you want to update:=");
        int id = sc.nextInt();

        System.out.println("What you want to update:=\n 1]fullname 2]fathername 3]address\n 4]dob 5]english 6]hindi\n 7]maths 8]science 9]social ");
        String choice = sc.next();

        if (choice.equals("fullname") || choice.equals("fathername") || choice.equals("address") || choice.equals("dob")) {

            updateString(connect, sc, id, choice);

        } else if (choice.equals("english") || choice.equals("hindi") || choice.equals("maths") || choice.equals("science") || choice.equals("social")) {

            updateFloat(connect, sc, id, choice);
            updatePercentage(connect, sc, id);

        } else {
            System.out.println("Process Terminated!!!! ");
            System.exit(0);
        }

    }

    private boolean updateString(Connection connect, Scanner sc, int id, String choice) {
        System.out.print("Update " + choice + ":=");
        String update = sc.next();

        String query = "update student set $columnName = ? where rollno = ? ";
        String query4 = query.replace("$columnName", choice);
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query4);
            preparedStatement.setString(1, update);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated");
            preparedStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean updateFloat(Connection connect, Scanner sc, int id, String choice) {
        System.out.println("Update " + choice + " marks:=");
        float update = sc.nextFloat();

        String query = "update student set $columnName = ? where rollno = ? ";
        String query4 = query.replace("$columnName", choice);
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query4);
            preparedStatement.setFloat(1, update);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated"+ choice + " marks");
            preparedStatement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean updatePercentage(Connection connect, Scanner sc, int id) {

        try {
            String query = "select english,hindi, science, maths, social from student where rollno = " + id;
            Statement psmt = connect.createStatement();
            ResultSet rs = psmt.executeQuery(query);
            rs.next();

            float english = rs.getFloat("english");
            float hindi = rs.getFloat("hindi");
            float maths = rs.getFloat("maths");
            float science = rs.getFloat("science");
            float social = rs.getFloat("social");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            String query2 = "update student set percentage = ? where rollno = ?";
            PreparedStatement preparedStatement2 = connect.prepareStatement(query2);
            preparedStatement2.setInt(2, id);
            preparedStatement2.setFloat(1, percent);
            preparedStatement2.executeUpdate();
            System.out.println("Percentage Updated!!!");

            rs.close();
            preparedStatement2.close();
            connect.close();

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return true;
    }

}