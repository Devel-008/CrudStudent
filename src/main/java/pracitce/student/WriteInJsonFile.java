package pracitce.student;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import crud.student.StudentCheck;
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
            jsonObject.put("rollno", studentCheck.getRollno());
            jsonObject.put("name", studentCheck.getName());
            jsonObject.put("fathername", studentCheck.getFathername());
            jsonObject.put("address", studentCheck.getAddress());
            jsonObject.put("dob", studentCheck.getDob());
            jsonObject.put("english",studentCheck.getEnglish());
            jsonObject.put("hindi", studentCheck.getHindi());
            jsonObject.put("maths", studentCheck.getMaths());
            jsonObject.put("science", studentCheck.getScience());
            jsonObject.put("social", studentCheck.getSocial());
            jsonObject.put("percentage", studentCheck.getPercentage());
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
