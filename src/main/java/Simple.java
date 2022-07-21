<<<<<<< HEAD
import crudStudent.InsertStudent;
import crudStudent.ReadStudent;
=======
import crud.student.InsertStudent;
import crud.student.ReadStudent;
>>>>>>> 6f291f7 (Initial commit)

import java.sql.*;
import java.util.Scanner;

public class Simple {

    String url = "jdbc:postgresql://localhost:5432/reportcard";
    String username = "postgres";
    String password = "isha@123";
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Simple simple = new Simple();
        InsertStudent in =  new InsertStudent();
        ReadStudent read = new ReadStudent();
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        } else {
            do {
                System.out.println("1] Press i to INSERT \n2] Press d to DELETE \n3]Press s to READ \n4]Press u to UPDATE \n5]Press any other key to exit");
                String c = sc.nextLine();
                switch (c) {
                    case "i" -> in.insert();
                    case "d" -> simple.delete();
                    case "s" -> read.select();
                    case "u" -> simple.update();
                    default -> {
                        System.out.println("Enter Correct Option!!!!");
                        System.out.println("Do you want to continue:=y/n");
                        String n = sc.nextLine();
                        if (n.startsWith("n")) {
                            System.out.println("Process Successful");
                            connect.close();
                            System.exit(0);
                        }
                    }
                }
            } while (true);
        }
        System.out.println("Process Successfull");
        connect.close();
    }

        //used to display record!!
    public void select() throws Exception {
        int count=0;
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        } else {
            Statement s1 = connect.createStatement();
            String se = "select * from student";
            ResultSet rs = s1.executeQuery(se);


            while (rs.next()) {
                System.out.print("Roll-No:" + rs.getInt("rollno") + " ");
                System.out.print(", Name: " + rs.getString("fullname") + " ");
                System.out.print(", Father's name: " + rs.getString("fathername"));
                System.out.println(", Address: " + rs.getString("address") + " ");
                System.out.print(", Date-of-Birth: " + rs.getString("dob") + " ");
                System.out.print(", English: " + rs.getFloat("english") + " ");
                System.out.print(", Maths: " + rs.getFloat("maths") + " ");
                System.out.print(", Science: " + rs.getFloat("science") + " ");
                System.out.println(", Social Science: " + rs.getFloat("social") + " ");
                System.out.print(", Percentage: " + rs.getFloat("percentage") + " ");
                System.out.println(" ");
                count++;

            }
            if(count<=0){
                System.out.println("No data available");
            }


        }
    }

    //Delete the records
    public void delete() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        }
        System.out.println("Enter the Admission number you want to delete:=");
        int id = sc.nextInt();
        PreparedStatement ps = connect.prepareStatement("delete from student where rollno=?");
        ps.setInt(1,id);
        int rows =ps.executeUpdate();
        if(rows>0){
            System.out.println("DELETED SUCCESSFULLY");
        }
        else{
            System.out.println("NOT DELETED");
        }
        }




    public void update() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        } else {
            System.out.println("What you want to update :=\n1]r for Roll-Number\n 2]n for Name\n 3]f for Father-Name\n 4]a for Address\n5]dob for Date of Birth\n6]e for English-marks\n7]h for Hindi-marks\n8]m for Maths-marks\n9]s for Science-marks\n10]ss for Social-marks\n");
            String update = sc.nextLine();
            if(update.equals("r")){
                System.out.println("Roll-Number you want to update :=");
                int s = sc.nextInt();
                System.out.println("Roll-Number update :=");
                int r = sc.nextInt();
                String sql = "update student set rollno="+r+" where rollno="+s+":";
                PreparedStatement st = connect.prepareStatement(sql);
                st.setInt(1,r);
                int i = st.executeUpdate();
                if(i>0){
                    System.out.println("Roll number updated");
                    }else {
                    System.out.println("Not updated");
                }

            }
        }
    }
}
