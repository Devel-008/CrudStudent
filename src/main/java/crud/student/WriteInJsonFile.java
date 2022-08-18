package crud.student;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
public class WriteInJsonFile {

    public void writeJson(Scanner sc, StudentCheck studentCheck) {
        try {
            //Creating a JSONObject object
            System.out.print("Enter Roll-no:=");
            studentCheck.setRollno(sc.nextInt());
            System.out.print("Enter Name:=");
            studentCheck.setName(sc.next());
            System.out.print("Enter Father-Name:=");
            studentCheck.setFathername(sc.next());
            System.out.print("Enter Address:=");
            studentCheck.setAddress(sc.next());
            System.out.print("Enter DOB:=");
            studentCheck.setDob(sc.next());
            System.out.print("Enter English-Marks:=");
            studentCheck.setEnglish(sc.nextFloat());
            System.out.print("Enter Hindi-Marks:=");
            studentCheck.setHindi(sc.nextFloat());
            System.out.print("Enter Maths-Marks:=");
            studentCheck.setMaths(sc.nextFloat());
            System.out.print("Enter Science-Marks:=");
            studentCheck.setScience(sc.nextFloat());
            System.out.print("Enter Social-Marks:=");
            studentCheck.setSocial(sc.nextFloat());

            JSONObject jsonObject = new JSONObject();

            float total = studentCheck.getEnglish()+ studentCheck.getHindi()+ studentCheck.getMaths()+ studentCheck.getScience()+ studentCheck.getSocial();
            studentCheck.setPercentage((total*100)/500);
            //Inserting key-value pairs into the json object
            jsonObject.put("ID", studentCheck.getRollno());
            jsonObject.put("Name", studentCheck.getName());
            jsonObject.put("FatherName", studentCheck.getFathername());
            jsonObject.put("Address", studentCheck.getAddress());
            jsonObject.put("Date_Of_Birth", studentCheck.getDob());
            jsonObject.put("English-Marks",studentCheck.getEnglish());
            jsonObject.put("Hindi-Marks", studentCheck.getHindi());
            jsonObject.put("Maths-Marks", studentCheck.getMaths());
            jsonObject.put("Science-Marks", studentCheck.getScience());
            jsonObject.put("Social-Marks", studentCheck.getSocial());
            jsonObject.put("Percentage", studentCheck.getPercentage());
            try {
                FileWriter file = new FileWriter("/Users/ishasethia/Desktop/java.json");
                file.write(jsonObject.toJSONString());
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("JSON file created: " + jsonObject);
        }catch (Exception e){
            System.out.println(""+e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentCheck studentCheck = new StudentCheck();
        WriteInJsonFile write = new WriteInJsonFile();
        write.writeJson(sc,studentCheck);
    }
}
