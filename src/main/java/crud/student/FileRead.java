package crud.student;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FileRead {
    static int rollno;
    static String name;
    static String fathername;
    static String address;
    static String dob;
    static float english;
    static float hindi;
    static float maths;
    static float science;
    static float social;
    static float percentage;

    //File Insert
    public static void readData(Connection connect, Scanner sc) {
        System.out.println("Enter the Address of file:=");
        String filePath = sc.nextLine();

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                name = "";
                fathername = "";
                address = "";
                dob = "";

                String line;
                line = input.nextLine();

                try (Scanner data = new Scanner(line)) {

                    if (data.hasNextInt()) {
                        rollno = data.nextInt();
                    }
                    while (!data.hasNextInt()) {
                        name += data.next();
                        fathername += data.next();
                        address += data.next();
                        dob += data.next();
                    }
                    name = name.trim();
                    fathername = fathername.trim();
                    address = address.trim();
                    dob = dob.trim();

                    if (data.hasNextInt()) {
                        english = data.nextFloat();
                    }

                    if (data.hasNextInt()) {
                        hindi = data.nextFloat();
                    }
                    if (data.hasNextInt()) {
                        maths = data.nextFloat();
                    }
                    if (data.hasNextInt()) {
                        science = data.nextFloat();
                    }
                    if (data.hasNextInt()) {
                        social = data.nextFloat();
                    }
                    float total = english + hindi + maths + science + social;
                    percentage = (total * 100) / 500;
                }
                saveData(connect);
                //System.out.println(empName + "\t" + empSalary + "\t" + empDeptId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveData(Connection connect) {
        try (PreparedStatement preparedStatement = connect.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, rollno);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fathername);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, dob);
            preparedStatement.setFloat(6, english);
            preparedStatement.setFloat(7, hindi);
            preparedStatement.setFloat(8, maths);
            preparedStatement.setFloat(9, science);
            preparedStatement.setFloat(10, social);
            preparedStatement.setFloat(11, percentage);

            preparedStatement.executeUpdate();
            System.out.println("Data Saved!!!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
