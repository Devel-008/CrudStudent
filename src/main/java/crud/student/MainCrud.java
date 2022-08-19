package crud.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainCrud {

    public static void main(String[] args) {

        InsertCheck in1 = new InsertCheck();
        StudentCheck stu = new StudentCheck();
        ReadStudent read = new ReadStudent();
        DeleteStudent de = new DeleteStudent();
        FileReadAndWrite file = new FileReadAndWrite();
        Update up = new Update();
        Scanner sc = new Scanner(System.in);

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
        do {
            System.out.println("1] Press i to INSERT 2] Press d to DELETE 3]Press s to READ \n4]Press u to UPDATE 5]Press f to insert data from any JSON-File you want in database \n6]Press j to insert data in JSON File you want \n7]Press any other key to exit");
            String c = sc.nextLine();
            switch (c) {
                case "i" -> {
                    try {
                        in1.insert(sc, stu);
                        in1.insertData(connect, stu);
                    }catch (Exception e){
                        System.out.println("ERROR:="+e);
                    }
                }
                case "d" -> {
                    System.out.println("Enter the roll no to delete: ");
                    int roll = sc.nextInt();
                    try {
                        if (de.delete(connect, roll)) {
                            System.out.println("Entries deleted \n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "s" -> {
                    try {
                        read.select(connect,sc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "u" -> {
                    try {
                        up.updateRecord(connect, sc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "f" -> {
                    try {
                        file.readData(sc,connect);
                    } catch (Exception e) {
                        System.out.println("ERROR");
                    }
                }
                case "j" -> {
                    try {
                        file.writeData(sc,stu);
                    } catch (Exception e) {
                        System.out.println("ERROR");
                   }
                }
                default -> {
                    System.out.println("\nEnter Correct Option!!!!");

                    System.out.println("\nDo you want to continue:= Press any key or else Press n to exit!!");
                    String n = sc.nextLine();

                    if (n.startsWith("n")) {
                        System.out.println("\nProcess Successful");
                        try {
                            connect.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                }
            }
        } while (true);
    }
}