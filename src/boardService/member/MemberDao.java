package boardService.member;

import boardService.comment.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDao {

    private Connection conn;

    public MemberDao(Connection conn) {
        this.conn = conn;
    }

    //    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Member> findAll(){
        ArrayList<Member> member = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM member ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member members = new Member();
                members.setId(rs.getInt("id"));
                members.setUsername(rs.getString("getUsername"));
                members.setPassword(rs.getString("getPassword"));

                member.add(members);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return member;
    }


    public Member findById(int id){
        Member members = new Member();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM member WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                members.setId(rs.getInt("id"));
                members.setUsername(rs.getString("getUsername"));
                members.setPassword(rs.getString("getPassword"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return members;
    }


    public int insert(Member member){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO member ");
            sql.append("VALUES(?, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, member.getId());
            ps.setString(2, member.getUsername());
            ps.setString(3, member.getPassword());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 컬럼을 다 적어줄 수 없으니 웨어절에 걸리는 id 빼고 나머지는 커스터머로
    public int updateById(int id, Member member){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE member username = ?, password = ?");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getPassword());
            ps.setInt(4, member.getId());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(int id){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM member WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
