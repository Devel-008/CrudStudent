package PracticecrudStudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MainCrud {
    public static void main(String[] args) throws Exception {

        InsertStudent in =  new InsertStudent();
        ReadStudent read = new ReadStudent();
        DeleteStudent de = new DeleteStudent();
        UpdateStudent up = new UpdateStudent();
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        if (connect.isClosed()) {
            System.out.println("Not Connected");
        }
        do {
            System.out.println("1] Press i to INSERT \n2] Press d to DELETE \n3]Press s to READ \n4]Press u to UPDATE \n5]Press any other key to exit");
            String c = sc.nextLine();
            switch (c) {
                case "i" -> in.insert();
                case "d" -> de.delete();
                case "s" -> read.select();
                case "u" -> up.update();
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

}
