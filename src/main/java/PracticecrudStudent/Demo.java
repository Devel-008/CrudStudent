package PracticecrudStudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {
    private int rollno;
    private String name;
    private String fathername;
    private String address;
    private String dob;
    private float english;
    private float hindi;
    private float maths;
    private float science;
    private float social;
    private float percentage;

    public void readData() {

        Scanner sc = new Scanner(System.in);
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
                saveData();
                //System.out.println(empName + "\t" + empSalary + "\t" + empDeptId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    private void saveData() {
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement preparedStatement = conn.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)")) {
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
                conn.close();

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.readData();

    }
}

