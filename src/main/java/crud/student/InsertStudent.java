package crud.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertStudent {

    String url = "jdbc:postgresql://localhost:5432/reportcard";
    String username = "postgres";
    String password = "isha@123";

    Scanner sc = new Scanner(System.in);

    String sql = "insert into student (rollNo, FullName,FatherName, Address, dob, english, hindi, maths, science,  social,  percentage) values( ?,?,?,?,?,?,?,?,?,?,?)";

    public void insert() throws Exception {

        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        }
        System.out.println("Enter Full name:= ");
        String name = sc.nextLine();
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("Name is required.");
        }
        System.out.println("Enter Father name:= ");
        String Fathername = sc.nextLine();
        System.out.println("Enter Address:= ");
        String add = sc.nextLine();
        System.out.println("Enter Date of Birth := ");
        String dob = sc.nextLine();
        System.out.println("Enter English marks out 100 := ");
        float eng = sc.nextFloat();
        System.out.println("Enter Hindi marks out 100:= ");
        float hindi = sc.nextFloat();
        System.out.println("Enter Maths Marks out 100 := ");
        float maths = sc.nextFloat();
        System.out.println("Enter Science Marks out 100:= ");
        float sci = sc.nextFloat();
        System.out.println("Enter Social marks out 100 := ");
        float sst = sc.nextFloat();
        float total = eng + sci + maths + hindi + sst;
        float per = (total * 100) / 500;
        System.out.println("Enter Roll Number:= ");
        int rollNo = sc.nextInt();

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
            System.exit(0);
        } else {
            System.out.println("Unsuccessful insertion ");
        }

        connect.close();
    }

    public boolean insertStudentInfo(Connection connection, Student student){

        return true;
    }
}


