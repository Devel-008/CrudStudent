package crud.student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class FileReadAndWrite {

    public void readData(Scanner sc, Connection connect) {
        String query = "insert into student value(?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Enter the address of file:-");
        String filePath = sc.next();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;

            int id = Integer.parseInt((String) jsonObject.get("ID"));
            String name = (String) jsonObject.get("Name");
            String fname = (String) jsonObject.get("FatherName");
            String address = (String) jsonObject.get("Address");
            String dob = (String) jsonObject.get("Dob");
            float english = Float.parseFloat((String) jsonObject.get("English"));
            float hindi = Float.parseFloat((String) jsonObject.get("Hindi"));
            float maths = Float.parseFloat((String) jsonObject.get("Maths"));
            float science = Float.parseFloat((String) jsonObject.get("Science"));
            float social = Float.parseFloat((String) jsonObject.get("Social"));
            float percentage = Float.parseFloat((String) jsonObject.get("Percentage"));
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fname);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, dob);
            preparedStatement.setFloat(6, english);
            preparedStatement.setFloat(7, hindi);
            preparedStatement.setFloat(8, maths);
            preparedStatement.setFloat(9, science);
            preparedStatement.setFloat(10, social);
            preparedStatement.setFloat(11, percentage);
            preparedStatement.executeUpdate();
            System.out.println("Data Saved!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeData(Scanner sc, StudentCheck studentCheck) {

        try {
            System.out.println("Enter Admission:=");
            studentCheck.setRollno(sc.nextInt());

            System.out.print("Enter Name:-");
            studentCheck.setName(sc.next());
            System.out.print("Enter Father-Name:-");
            studentCheck.setFathername(sc.next());
            System.out.print("Enter Address:-");
            studentCheck.setAddress(sc.next());
            System.out.print("Enter DOB:-");
            studentCheck.setDob(sc.next());
            System.out.print("Enter English-Marks:-");
            studentCheck.setEnglish(sc.nextFloat());
            System.out.print("Enter Hindi-Marks:-");
            studentCheck.setHindi(sc.nextFloat());
            System.out.print("Enter Maths-Marks:- ");
            studentCheck.setMaths(sc.nextFloat());
            System.out.print("Enter Science-Marks:-");
            studentCheck.setScience(sc.nextFloat());
            System.out.print("Enter Social-Marks:-");
            studentCheck.setSocial(sc.nextFloat());
            float total = studentCheck.getEnglish() + studentCheck.getHindi() + studentCheck.getMaths() + studentCheck.getScience() + studentCheck.getSocial();
            studentCheck.setPercentage((total * 100) / 500);
        } catch (Exception e) {
            System.out.println(e);
        }
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("Id:", studentCheck.getRollno());
        jsonObject.put("Name:", studentCheck.getName());
        jsonObject.put("FatherName:", studentCheck.getFathername());
        jsonObject.put("Address:", studentCheck.getAddress());
        jsonObject.put("Dob:", studentCheck.getDob());
        jsonObject.put("English:", studentCheck.getEnglish());
        jsonObject.put("Hindi:", studentCheck.getHindi());
        jsonObject.put("Maths:", studentCheck.getMaths());
        jsonObject.put("Science:", studentCheck.getScience());
        jsonObject.put("Social:", studentCheck.getSocial());
        jsonObject.put("Percentage:", studentCheck.getPercentage());
        //Creating a json array
        try {
            FileWriter file = new FileWriter("/Users/ishasethia/Desktop/java.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
