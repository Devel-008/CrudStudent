package crud.student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JsonToDatabase {

    public void convertJson(Connection connect, Scanner sc) {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/ishasethia/Desktop/student.json"));
            //Retrieving the array
            JSONArray jsonArray = (JSONArray) jsonObject.get("Student_data");
            //Insert a row into the MyPlayers table
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO student values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for(Object object : jsonArray) {
                JSONObject record = (JSONObject) object;
                int id = Integer.parseInt((String) record.get("Rollno"));
                String name = (String) record.get("Name");
                String Fathername = (String) record.get("FatherName");
                String address = (String) record.get("Address");
                String date = (String) record.get("DOB");
                float english = Float.parseFloat((String) record.get("EnglishMarks"));
                float hindi = Float.parseFloat((String) record.get("HindiMarks"));
                float maths = Float.parseFloat((String) record.get("MathsMarks"));
                float science = Float.parseFloat((String) record.get("ScienceMarks"));
                float social = Float.parseFloat((String) record.get("SocialMarks"));
                float percentage = Float.parseFloat((String) record.get("percentage"));
                float total = english + hindi + maths + science + social ;
                 percentage = (total*100)/500;

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, Fathername);
                pstmt.setString(4, address);
                pstmt.setString(5, date);
                pstmt.setFloat(6, english);
                pstmt.setFloat(7, hindi);
                pstmt.setFloat(8, maths);
                pstmt.setFloat(9, science);
                pstmt.setFloat(10, social);
                pstmt.setFloat(11, percentage);
                pstmt.executeUpdate();
            }
            System.out.println("Records inserted.....");
            System.exit(0);
        } catch (Exception e) {
            System.out.println( e);
            System.exit(0);
        }

    }
}
