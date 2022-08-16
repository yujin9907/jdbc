package dbex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//dao = data, access, object
public class EmpDao {
    public int 직원수정(int empno, int sal, int comm){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE emp SET sal = ?, comm = ? WHERE empno=?;");
            //인젝션 공격에 대비하여, ?로 굳이 써줌
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, sal);
            pstmt.setInt(2, comm);
            pstmt.setInt(3, empno);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int 직원삭제(int empno){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM emp WHERE empno = ?");
            //인젝션 공격에 대비하여, ?로 굳이 써줌
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, empno);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int 직원추가(Emp emp){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO Emp ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");
            Connection conn = DBConnection.connection();

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setString(3, emp.getJob());
            pstmt.setInt(4, emp.getMgr());
            pstmt.setTimestamp(5, emp.getHiredate());
            pstmt.setInt(6, emp.getSal());
            pstmt.setInt(7, emp.getComm());
            pstmt.setInt(8, emp.getDeptno());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // 이 메서드이 책임 = emp 데이터의 모든 데이터를 리턴
    public ArrayList<Emp> 직원목록보기() {
        ArrayList<Emp> emps = new ArrayList<>();

        try {
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM emp");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("JOB"));
                emp.setMgr(rs.getInt("mgr"));
                emp.setHiredate(rs.getTimestamp("hiredate"));
                emp.setSal(rs.getInt("sal"));
                emp.setComm(rs.getInt("comm"));
                emp.setDeptno(rs.getInt("deptno"));
                emps.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emps;
    }

    public Emp 직원한건보기(int e) {
        Emp emp = new Emp();
        try {
            Connection conn = DBConnection.connection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM emp WHERE empno="+e);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("JOB"));
                emp.setMgr(rs.getInt("mgr"));
                emp.setHiredate(rs.getTimestamp("hiredate"));
                emp.setSal(rs.getInt("sal"));
                emp.setComm(rs.getInt("comm"));
                emp.setDeptno(rs.getInt("deptno"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return emp;
    }
}
