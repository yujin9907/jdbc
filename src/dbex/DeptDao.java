package dbex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeptDao {

    public int 부서생성(int no, String name, String loc){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO dept VALUES(?,?,?)");
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, no);
            pstmt.setString(2, name);
            pstmt.setString(3, loc);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int 부서삭제(int no){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM dept WHERE deptno = ?");
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, no);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<Dept> 부서목록보기() {
        ArrayList<Dept> depts = new ArrayList<>();

        try {
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dept");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDetpno(rs.getInt("dempno"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                depts.add(dept);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return depts;
    }
}