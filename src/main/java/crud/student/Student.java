package crud.student;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
    private static int rollno;
    private static String name;
    private static String fathername;
    private static String address;
    private static String dob;
    private static float english;
    private static float hindi;
    private static float maths;
    private static float science;
    private static float social;
    private static float percentage;

   /* public Student(int rollno, String name, String fathername, String address, String dob, float english, float hindi, float maths, float science, float social, float percentage) {
        this.rollno = rollno;
        this.name = name;
        this.fathername = fathername;
        this.address = address;
        this.dob = dob;
        this.english = english;
        this.hindi = hindi;
        this.maths = maths;
        this.science = science;
        this.social = social;
        this.percentage = percentage;

    }*/

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        Student.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Student.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        Student.fathername = fathername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Student.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        Student.dob = dob;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        Student.english = english;
    }

    public float getHindi() {
        return hindi;
    }

    public void setHindi(float hindi) {
        Student.hindi = hindi;
    }

    public float getMaths() {
        return maths;
    }

    public void setMaths(float maths) {
        Student.maths = maths;
    }

    public float getScience() {
        return science;
    }

    public void setScience(float science) {
        Student.science = science;
    }

    public float getSocial() {
        return social;
    }

    public void setSocial(float social) {
        Student.social = social;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        Student.percentage = percentage;
    }

    public static void readData(Connection connect) {
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
                saveData(connect);
                //System.out.println(empName + "\t" + empSalary + "\t" + empDeptId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void saveData(Connection connect) {
        try {

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
                connect.close();

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
