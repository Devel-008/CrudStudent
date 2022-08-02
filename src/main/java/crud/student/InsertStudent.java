package crud.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertStudent {
    public static String sql = "insert into student (rollNo, FullName,FatherName, Address, dob, english, hindi, maths, science,  social,  percentage) values( ?,?,?,?,?,?,?,?,?,?,?)";

    public boolean insert(Connection connect, Scanner sc, int rollNo, String name,String Fathername, String add, String dob, float eng, float hindi, float maths, float sci, float sst, float per) {
        try {
            PreparedStatement inset = connect.prepareStatement(sql);
            inset.setInt(1, rollNo);
            inset.setString(2, name);
            inset.setString(3, Fathername);
            inset.setString(4, add);
            inset.setString(5, dob);
            inset.setFloat(6, eng);
            inset.setFloat(7, hindi);
            inset.setFloat(8, maths);
            inset.setFloat(9, sci);
            inset.setFloat(10, sst);
            inset.setFloat(11, per);

            int input = inset.executeUpdate();
            if (input > 0) {
                System.out.println("Successfully inserted");
                inset.close();
            } else {
                System.out.println("Unsuccessful insertion ");
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("ERROR:=" + e);
        }  return false;
    }
}
