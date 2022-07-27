package crud.student;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteStudent {
int rows;
    public boolean delete(Connection connect, int rollno) {
        try {
            PreparedStatement ps = connect.prepareStatement("delete from student where rollno=?");
            ps.setInt(1, rollno);
             rows = ps.executeUpdate();
            return rows > 0;
        }catch (Exception e){
            System.out.println("ERROR:-"+e);
        }
        return true;
    }

}


