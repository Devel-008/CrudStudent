import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class EmployesRecord {

    private String empName;
    private int empSalary;
    private int empDeptId;

    public void readData() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Address of file:=");
        String filePath = sc.nextLine();

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                empName = "";
                String line;
                line = input.nextLine();
                try (Scanner data = new Scanner(line)) {
                    while (!data.hasNextInt()) {
                        empName += data.next();
                    }
                    empName = empName.trim();

                    if (data.hasNextInt()) {
                        empSalary = data.nextInt();
                    }

                    if (data.hasNextInt()) {
                        empDeptId = data.nextInt();
                    }
                }
                saveData();
                //System.out.println(empName + "\t" + empSalary + "\t" + empDeptId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void saveData(){
        try(Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into file values(?,?,?)")){
            preparedStatement.setInt(1,empDeptId);
            preparedStatement.setString(2,empName);
            preparedStatement.setInt(3,empSalary);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e);
        }
    }
    private Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url,username,password);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
}

public class Employes {
    public static void main(String[] args) {
        EmployesRecord emp = new EmployesRecord();
        try {
            emp.readData();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

