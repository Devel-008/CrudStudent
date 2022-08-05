package test.crud;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class ConnectTest {

    private static final String url = "jdbc:postgresql://localhost:5432/reportcard";
    private static final String username = "postgres";
    private static final String password = "isha@123";
    public static Connection connect = null;
    public static Statement statement = null;
    public static Statement statement1 = null;
    public static ResultSet resultSet = null;
    public static ResultSet resultSet1 = null;
    public static Connection connection = null;

    @BeforeClass
    public void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url, username, password);
            statement1 = connect.createStatement();
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }

    @BeforeClass
    public void setUpH2() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    @Test
    public void insertH2TestCase() {
        String query = "select * from ninth";
        try {
            resultSet = statement.executeQuery(query);
            System.out.println("hey");
        } catch (Exception e) {
            System.out.println(""+e);
        }
        String query1 = "select * from student";
        try {
            resultSet1 = statement1.executeQuery(query1);
            System.out.println("Hello");
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }

    @Test(dependsOnMethods = {"insertH2TestCase"})
    public void getData() {
        int id=1,id1=1;
        String add1;
        String add ;
        try {
            resultSet.next() ;
            //  id = resultSet.getInt("rollno");
            String name = resultSet.getString("fullname");
            String fname = resultSet.getString("fathername");
            add = resultSet.getString("address");
            String dob = resultSet.getString("dob");
            float english = resultSet.getFloat("english");
            float hindi = resultSet.getFloat("hindi");
            float maths = resultSet.getFloat("maths");
            float science = resultSet.getFloat("science");
            float social = resultSet.getFloat("social");
            float percentage = resultSet.getFloat("percentage");

            resultSet1.next();
            //id1 = resultSet1.getInt("rollno");
            String name1 = resultSet1.getString("fullname");
            String fname1 = resultSet1.getString("fathername");
            add1 = resultSet1.getString("address");
            String dob1 = resultSet1.getString("dob");
            float english1 = resultSet1.getFloat("english");
            float hindi1 = resultSet1.getFloat("hindi");
            float maths1 = resultSet1.getFloat("maths");
            float science1 = resultSet1.getFloat("science");
            float social1 = resultSet1.getFloat("social");
            float percentage1 = resultSet1.getFloat("percentage");

            Assert.assertEquals(add,add1);
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println(""+e);
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

    @AfterClass
    public void tearDownH2() {
        if (connection != null) {
            try {
                connection.close();
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
