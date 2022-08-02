package test.crud;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class ConnectToDataBase extends Assert {
    private static final String url = "jdbc:postgresql://localhost:5432/reportcard";
    private static final String username = "postgres";
    private static final String password = "isha@123";
    public static Connection connect = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    @BeforeClass
    public void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url, username, password);
            statement = connect.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCaseOne() {
        System.out.println("TestNG");
    }

    @Test
    public void createTable() {
        String query = "create table student (rollno int primary key not null, " +
                "fullname varchar(50) not null," +
                "fathername varchar(50) not null," +
                "address varchar(50) not null," +
                "dob varchar(50) not null, " +
                "english float not null, " +
                "hindi float not null, maths float not null, " +
                "science float not null, social float not null, " +
                "percentage float not null)  ";
        try {
            assertEquals(0, statement.executeUpdate(query));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(dependsOnMethods = {"createTable"})
    public void insertData() {
        String query = "insert into student values(10,'heena','jack','bvc colony','13/10/1999',33,44,55,77,88,59.4)";
        try {
            assertEquals(1, statement.executeUpdate(query));
            System.out.println("inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(dependsOnMethods = {"insertData"})
    public void fetchData() {
        String query = "select * from student";
        try {
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(dependsOnMethods = {"fetchData"})
    public void getData() {
        try {
            while (resultSet.next()) {
                System.out.println("Admission-no:=" + resultSet.getInt("rollno")
                        + ", Name:=" + resultSet.getString("fullname")
                        + ", Father-Name:=" + resultSet.getString("fathername")
                        + ", Address:=" + resultSet.getString("address")
                        + ", DOB:=" + resultSet.getString("dob")
                        + ", English-marks:=" + resultSet.getFloat("english")
                        + ", Hindi-marks:=" + resultSet.getFloat("hindi")
                        + ", Maths-marks:=" + resultSet.getFloat("maths")
                        + ", Science-marks:=" + resultSet.getFloat("science")
                        + ", SST-marks:=" + resultSet.getFloat("social")
                        + "Percentage:=" + resultSet.getFloat("percentage"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
