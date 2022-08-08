package test.crud;

import org.testng.Assert;
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

        String nameH2,name,fnameH2,fname,add,addH2,dob,dobH2;
        int id, idH2;
        float englishH2,english,hindiH2,hindi,maths,mathsH2,science,scienceH2,socialH2,social,percentageH2,percentage;
        try {
            resultSet.next() ;
            idH2 = resultSet.getInt("rollno");
            nameH2 = resultSet.getString("fullname");
             fnameH2 = resultSet.getString("fathername");
            addH2 = resultSet.getString("address");
             dobH2 = resultSet.getString("dob");
             englishH2 = resultSet.getFloat("english");
             hindiH2 = resultSet.getFloat("hindi");
             mathsH2 = resultSet.getFloat("maths");
             scienceH2 = resultSet.getFloat("science");
             socialH2 = resultSet.getFloat("social");
             percentageH2 = resultSet.getFloat("percentage");

            resultSet1.next();
            id = resultSet1.getInt("rollno");
            name = resultSet1.getString("fullname");
            fname = resultSet1.getString("fathername");
            add = resultSet1.getString("address");
            dob = resultSet1.getString("dob");
            english = resultSet1.getFloat("english");
             hindi = resultSet1.getFloat("hindi");
             maths = resultSet1.getFloat("maths");
             science = resultSet1.getFloat("science");
             social = resultSet1.getFloat("social");
             percentage = resultSet1.getFloat("percentage");

            softAssert.assertEquals(addH2,add,"failed");
            softAssert.assertEquals(idH2,id,"failed");
            softAssert.assertEquals(nameH2,name,"failed");
            softAssert.assertEquals(fnameH2,fname);
            softAssert.assertEquals(dobH2,dob);
            softAssert.assertEquals(englishH2,english);
            softAssert.assertEquals(hindiH2,hindi);
            softAssert.assertEquals(mathsH2,maths);
            softAssert.assertEquals(scienceH2,science);
            softAssert.assertEquals(percentageH2,percentage);

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
