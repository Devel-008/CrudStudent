package crud.student;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class ResultSetConverter {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/reportcard";
        String username = "postgres";
        String password = "isha@123";

        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);
        if (connect.isClosed()) {
            System.out.println("Not Connected");
        }
        String select = "select * from student";
        PreparedStatement preparedStatement = connect.prepareStatement(select);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Student> arrayList = new ArrayList<>();

        while(rs.next()){
            int rollno = rs.getInt("rollno");
            String name = rs.getString("fullname");
            String fatherName = rs.getString("fathername");
            String address = rs.getString("address");
            String dob = rs.getString("dob");
            float english = rs.getFloat("english");
            float hindi = rs.getFloat("hindi");
            float maths = rs.getFloat("maths");
            float science = rs.getFloat("science");
            float social = rs.getFloat("social");
            float percentage = rs.getFloat("percentage");
            Student student = new Student();

            student.setRollno(rollno);
            student.setName(name);
            student.setFathername(fatherName);
            student.setAddress(address);
            student.setDob(dob);
            student.setEnglish(english);
            student.setHindi(hindi);
            student.setMaths(maths);
            student.setScience(science);
            student.setSocial(social);
            student.setPercentage(percentage);

            arrayList.add(student);
        }
        for(int i=0; i<arrayList.size();i++) {
            File jsonFile = new File("StudentData"+i+".json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile,arrayList.get(i) );
            connect.close();
            System.out.println("Successful!!");
        }
    }
}