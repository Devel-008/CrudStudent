package test.crud;

import crud.student.StudentCheck;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    SoftAssert softAssert = new SoftAssert();

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
        String query = "select * from ninth where rollno = 1";
        try {
            resultSet = statement.executeQuery(query);
            System.out.println("hey");
        } catch (Exception e) {
            System.out.println(""+e);
        }
        String query1 = "select * from student where rollno = 1";
        try {
            resultSet1 = statement1.executeQuery(query1);
            System.out.println("passed");
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }

    @Test(dependsOnMethods = {"insertH2TestCase"})
    public void getData() {
        StudentCheck studentCheck = new StudentCheck();
        StudentCheck studentCheck1 = new StudentCheck();

        try {
            resultSet.next() ;
            studentCheck.setRollno(resultSet.getInt("rollno"));
            studentCheck.setName(resultSet.getString("fullname"));
            studentCheck.setFathername(resultSet.getString("fathername"));
            studentCheck.setAddress(resultSet.getString("address"));
            studentCheck.setDob(resultSet.getString("dob"));
            studentCheck.setEnglish(resultSet.getFloat("english"));
            studentCheck.setHindi(resultSet.getFloat("hindi"));
            studentCheck.setMaths(resultSet.getFloat("maths"));
            studentCheck.setScience(resultSet.getFloat("science"));
            studentCheck.setSocial(resultSet.getFloat("social"));
            studentCheck.setPercentage(resultSet.getFloat("percentage"));


            resultSet1.next();
            studentCheck1.setRollno(resultSet1.getInt("rollno"));
            studentCheck1.setName(resultSet1.getString("fullname"));
            studentCheck1.setFathername(resultSet1.getString("fathername"));
            studentCheck1.setAddress(resultSet1.getString("address"));
            studentCheck1.setDob(resultSet1.getString("dob"));
            studentCheck1.setEnglish(resultSet1.getFloat("english"));
            studentCheck1.setHindi(resultSet1.getFloat("hindi"));
            studentCheck1.setMaths(resultSet1.getFloat("maths"));
            studentCheck1.setScience(resultSet1.getFloat("science"));
            studentCheck1.setSocial(resultSet1.getFloat("social"));
            studentCheck1.setPercentage(resultSet1.getFloat("percentage"));


            softAssert.assertEquals(studentCheck.getRollno(),studentCheck1.getRollno(),"failed");
            softAssert.assertEquals(studentCheck.getName(),studentCheck1.getName(),"failed");
            softAssert.assertEquals(studentCheck.getFathername(),studentCheck1.getFathername(),"failed");
            softAssert.assertEquals(studentCheck.getAddress(),studentCheck1.getAddress(),"failed");
            softAssert.assertEquals(studentCheck.getDob(),studentCheck1.getDob(),"failed");
            softAssert.assertEquals(studentCheck.getEnglish(),studentCheck1.getEnglish(),"failed");
            softAssert.assertEquals(studentCheck.getHindi(),studentCheck1.getHindi(),"failed");
            softAssert.assertEquals(studentCheck.getMaths(),studentCheck1.getMaths(),"failed");
            softAssert.assertEquals(studentCheck.getScience(),studentCheck1.getScience(),"failed");
            softAssert.assertEquals(studentCheck.getSocial(),studentCheck1.getSocial(),"failed");
            softAssert.assertEquals(studentCheck.getPercentage(),studentCheck1.getPercentage(),"failed");
           softAssert.assertAll();
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
