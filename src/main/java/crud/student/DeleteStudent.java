package crud.student;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteStudent {

    public boolean delete(Connection connect, int rollno) throws Exception {
        PreparedStatement ps = connect.prepareStatement("delete from student where rollno=?");
        ps.setInt(1, rollno);
        int rows = ps.executeUpdate();
        return rows > 0;
    }

}


