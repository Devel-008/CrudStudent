package PracticecrudStudent;

import java.sql.*;
import java.util.Scanner;

public class UpdateStudent {
    public void update() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        Scanner sc = new Scanner(System.in);
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);
        if (connect.isClosed()) {
            System.out.println("Not Connected");
        }
        System.out.println("What you want to update :=\n 1]n for Name\n 2]f for Father-Name\n 3]a for Address\n4]dob for Date of Birth\n5]e for English-marks\n6]h for Hindi-marks\n7]m for Maths-marks\n8]s for Science-marks\n9]ss for Social-marks\n");
        String update = sc.nextLine();
        //name
        if (update.equals("n")) {
            System.out.println("Update Name :=");
            String name = sc.nextLine();

            System.out.println("Enter admission number student whose name you want to update :=");
            int n = sc.nextInt();

            String u = "update student set fullname=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);
            statement.setString(1, name);
            statement.setInt(2, n);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //father-name
        else if (update.equals("f")) {
            System.out.println("Update Father-Name :=");
            String name = sc.nextLine();

            System.out.println("Enter admission number student whose Father-name you want to update :=");
            int n = sc.nextInt();

            String u = "update student set fathername=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);
            statement.setString(1, name);
            statement.setInt(2, n);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }

        //address
        else if (update.equals("a")) {
            System.out.println("Update Address :=");
            String name = sc.nextLine();

            System.out.println("Enter admission number student whose address you want to update :=");
            int n = sc.nextInt();

            String u = "update student set address=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);
            statement.setString(1, name);
            statement.setInt(2, n);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("UPDATED");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //dob
       else if (update.equals("dob")) {
            System.out.println("Update Date of Birth :=");
            String name = sc.nextLine();

            System.out.println("Enter admission number student whose date of birth you want to update :=");
            int n = sc.nextInt();

            String u = "update student set dob=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);
            statement.setString(1, name);
            statement.setInt(2, n);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("UPDATED");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //English-marks
       else if (update.equals("e")) {

            System.out.println("Update English-Marks:=");
            float english = sc.nextFloat();

            System.out.println("Enter admission number student whose english number you want to update :=");
            int n = sc.nextInt();


            String u = "update student set english=?,percentage=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);

            String select = "select hindi,maths,science,social from student where rollno=" + n;
            Statement s1 = connect.createStatement();
            ResultSet rs = s1.executeQuery(select);
            rs.next();


            float hindi = rs.getFloat("hindi");
            float maths = rs.getFloat("maths");
            float science = rs.getFloat("science");
            float social = rs.getFloat("social");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            statement.setFloat(1, english);
            statement.setInt(3, n);
            statement.setFloat(2, percent);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
//Hindi-Marks
       else if (update.equals("h")) {

            System.out.println("Update Hindi-Marks:=");
            float hindi = sc.nextFloat();

            System.out.println("Enter admission number student whose hindi number you want to update :=");
            int n = sc.nextInt();


            String u = "update student set hindi=?,percentage=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);

            String select = "select english,maths,science,social from student where rollno=" + n;
            Statement s1 = connect.createStatement();
            ResultSet rs = s1.executeQuery(select);
            rs.next();


            float english = rs.getFloat("english");
            float maths = rs.getFloat("maths");
            float science = rs.getFloat("science");
            float social = rs.getFloat("social");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            statement.setFloat(1, hindi);
            statement.setInt(3, n);
            statement.setFloat(2, percent);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //Maths-Marks
       else if (update.equals("m")) {

            System.out.println("Update Maths-Marks:=");
            float maths = sc.nextFloat();

            System.out.println("Enter admission number student whose maths number you want to update :=");
            int n = sc.nextInt();


            String u = "update student set maths=?,percentage=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);

            String select = "select hindi,english,science,social from student where rollno=" + n;
            Statement s1 = connect.createStatement();
            ResultSet rs = s1.executeQuery(select);
            rs.next();


            float hindi = rs.getFloat("hindi");
            float english = rs.getFloat("english");
            float science = rs.getFloat("science");
            float social = rs.getFloat("social");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            statement.setFloat(1, maths);
            statement.setInt(3, n);
            statement.setFloat(2, percent);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //Science-Marks
       else if (update.equals("s")) {

            System.out.println("Update Science-Marks:=");
            float science = sc.nextFloat();

            System.out.println("Enter admission number student whose science number you want to update :=");
            int n = sc.nextInt();


            String u = "update student set science=?,percentage=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);

            String select = "select hindi,maths,english,social from student where rollno=" + n;
            Statement s1 = connect.createStatement();
            ResultSet rs = s1.executeQuery(select);
            rs.next();


            float hindi = rs.getFloat("hindi");
            float maths = rs.getFloat("maths");
            float english = rs.getFloat("english");
            float social = rs.getFloat("social");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            statement.setFloat(1, science);
            statement.setInt(3, n);
            statement.setFloat(2, percent);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
        //Social Marks
       else if (update.equals("ss")) {

            System.out.println("Update Social-Marks:=");
            float social = sc.nextFloat();

            System.out.println("Enter admission number student whose social number you want to update :=");
            int n = sc.nextInt();


            String u = "update student set social=?,percentage=? where rollno=?";
            PreparedStatement statement = connect.prepareStatement(u);

            String select = "select hindi,maths,science,english from student where rollno=" + n;
            Statement s1 = connect.createStatement();
            ResultSet rs = s1.executeQuery(select);
            rs.next();


            float hindi = rs.getFloat("hindi");
            float maths = rs.getFloat("maths");
            float science = rs.getFloat("science");
            float english = rs.getFloat("english");
            float total = english + hindi + maths + science + social;
            float percent = (total * 100) / 500;

            statement.setFloat(1, social);
            statement.setInt(3, n);
            statement.setFloat(2, percent);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("updated");

            } else {
                System.out.println("Failed!!!");
            }
        }
       else {
            System.out.println("Enter correct choice");
        }

    }

}
