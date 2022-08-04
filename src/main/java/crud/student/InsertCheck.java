package crud.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCheck {

    public void insert(Scanner sc, StudentCheck studentCheck) {
        try {
            System.out.println("Enter Full name:= ");
            studentCheck.setName(sc.nextLine());
            System.out.println("Enter Father name:= ");
            studentCheck.setFathername(sc.nextLine());
            System.out.println("Enter Address:= ");
            studentCheck.setAddress(sc.nextLine());
            System.out.println("Enter Date of Birth := ");
            studentCheck.setDob(sc.nextLine());

            System.out.println("Enter English marks out 100 := ");
            studentCheck.setEnglish(sc.nextFloat());
            System.out.println("Enter Hindi marks out 100:= ");
            studentCheck.setHindi(sc.nextFloat());
            System.out.println("Enter Maths Marks out 100 := ");
            studentCheck.setMaths(sc.nextFloat());
            System.out.println("Enter Science Marks out 100:= ");
            studentCheck.setScience(sc.nextFloat());
            System.out.println("Enter Social marks out 100 := ");
            studentCheck.setSocial(sc.nextFloat());

            float total = studentCheck.getEnglish() + studentCheck.getHindi() + studentCheck.getMaths() + studentCheck.getScience() + studentCheck.getSocial();
            studentCheck.setPercentage((total * 100) / 500);
            System.out.println("Enter Roll Number:= ");
            studentCheck.setRollno(sc.nextInt());
        }catch (Exception e){
            System.out.println("ERROR:="+e);
        }
    }

    private static final String sql = "insert into student (rollNo, FullName,FatherName, Address, dob, english, hindi, maths, science,  social,  percentage) values( ?,?,?,?,?,?,?,?,?,?,?)";

    public boolean insertData(Connection connect, StudentCheck studentCheck) {

        try {
            PreparedStatement inset = connect.prepareStatement(sql);
            inset.setInt(1, studentCheck.getRollno());
            inset.setString(2, studentCheck.getName());
            inset.setString(3, studentCheck.getFathername());
            inset.setString(4, studentCheck.getAddress());
            inset.setString(5, studentCheck.getDob());
            inset.setFloat(6, studentCheck.getEnglish());
            inset.setFloat(7, studentCheck.getHindi());
            inset.setFloat(8, studentCheck.getMaths());
            inset.setFloat(9, studentCheck.getScience());
            inset.setFloat(10, studentCheck.getSocial());
            inset.setFloat(11, studentCheck.getPercentage());

            int input = inset.executeUpdate();
            if (input > 0) {
                System.out.println("Successfully inserted");
                inset.close();
            } else {
                System.out.println("Unsuccessful insertion ");
            }

        } catch (Exception e) {
            System.out.println("ERROR:=" + e);
        }
        finally {
            try {
                connect.close();
            } catch (SQLException e) {
                System.out.println("ERROR:=" + e);
            }
        }
        return false;
    }
}
