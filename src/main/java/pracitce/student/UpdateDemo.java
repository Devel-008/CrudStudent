package pracitce.student;

import crud.student.StudentCheck;

import java.sql.*;
import java.util.Scanner;

public class UpdateDemo {
    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData rsm = null;

    public static void main(String[] args) {


        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert connect != null;
            if (connect.isClosed()) {
                System.out.println("Not Connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UpdateDemo up = new UpdateDemo();
        Scanner sc = new Scanner(System.in);
        StudentCheck stu = new StudentCheck();

        up.updateRecord(connect, sc);
    }

    public void updateRecord(Connection connect, Scanner sc) {
        try {
            String select = "select * from student";
            Statement stmt = connect.createStatement();
            rs = stmt.executeQuery(select);
            rs.next();
            rsm = rs.getMetaData();

            System.out.println("Enter Admission number of student whose record you want to update:=");
            int id = sc.nextInt();

            System.out.println("What you want to update Press:=\n 2]fullname 3]fathername 4]address\n 5]dob 6]english 7]hindi\n 8]maths 9]science 10]social ");
            int i = sc.nextInt();

            String column = rsm.getColumnName(i);
            String query = "update student  set " + column + " = (?) where rollno = (?)";

            if (i == 2 || i==3 || i==4 || i==5) {
                if(i==2) {
                     String choice = "Name";
                    System.out.print("Update " + choice + ":=");
                }else if(i==3){
                    String choice = "Father-Name";
                    System.out.print("Update " + choice + ":=");
                }
                else if(i==4){
                    String choice = "Address";
                    System.out.print("Update " + choice + ":=");
                }
                else if(i==5){
                    String choice = "DOB";
                    System.out.print("Update " + choice + ":=");
                }
                String name=sc.next();
                ps = connect.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, id);
                ps.executeUpdate();
                System.out.println("updated");
            }
            else if (i == 6 || i==7 || i==8 || i==9 || i==10) {
                if(i==6) {
                    String choice = "English";
                    System.out.println("Update " + choice + " marks:=");
                }else if(i==7){
                    String choice = "Hindi";
                    System.out.println("Update " + choice + " marks:=");
                }
                else if(i==8){
                    String choice = "Maths";
                    System.out.println("Update " + choice + " marks:=");
                }
                else if(i==9){
                    String choice = "Science";
                    System.out.println("Update " + choice + " marks:=");
                }else if(i==10){
                    String choice = "Social";
                    System.out.println("Update " + choice + " marks:=");
                }
                float name = sc.nextFloat();

                ps = connect.prepareStatement(query);
                ps.setFloat(1, name);
                ps.setInt(2, id);
                ps.executeUpdate();
                System.out.println("updated");
                updatePercentage(connect, sc, id);
            }
            else {
                System.out.println("Enter Correct Option");
                System.exit(0);
            }

        } catch (Exception e) {
            System.out.println("ERROR:= " + e);
        }
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

            preparedStatement2.close();
            rs.close();
            psmt.close();
        } catch (Exception e) {
            System.out.println("ERROR:= " + e);
        } finally {
            try {
                rs.close();
                ps.close();
                connect.close();
            } catch (Exception e) {
                System.out.println("ERROR:= " + e);
            }
        }
        return false;
    }
}
