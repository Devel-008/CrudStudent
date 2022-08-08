package PracticecrudStudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteStudent {

    String url = "jdbc:postgresql://localhost:5432/reportcard";
    String username = "postgres";
    String password = "isha@123";
    Scanner sc =new Scanner(System.in);

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

    }


