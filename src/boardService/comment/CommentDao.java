package boardService.comment;

import boardService.dto.CommedResDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDao {

    private Connection conn;

    public CommentDao(Connection conn) {
        this.conn = conn;
    }

    //    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Comment> findAll(){
        ArrayList<Comment> comment = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM commet_table ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comments = new Comment();
                comments.setId(rs.getInt("id"));
                comments.setComment(rs.getString("comment"));
                comments.setMemberId(rs.getInt("memberId"));
                comments.setBoardId(rs.getInt("boardId"));

                comment.add(comments);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }


    public Comment findById(int id){
        Comment comments = new Comment();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM board WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comments.setId(rs.getInt("id"));
                comments.setComment(rs.getString("getComment"));
                comments.setMemberId(rs.getInt("getMemberId"));
                comments.setBoardId(rs.getInt("getBoardId"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return comments;
    }


    public int insert(Comment comment){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO comment ");
            sql.append("VALUES(?, ?, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, comment.getId());
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getBoardId());
            ps.setInt(3, comment.getMemberId());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 컬럼을 다 적어줄 수 없으니 웨어절에 걸리는 id 빼고 나머지는 커스터머로
    public int updateById(int id, Comment comment){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE comment comment = ?, boardId = ?, memberId = ?");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getBoardId());
            ps.setInt(3, comment.getMemberId());
            ps.setInt(4, comment.getId());

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
            sql.append("DELETE FROM comment WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<CommedResDto> 게시글목록보기() {
        ArrayList<CommedResDto> comment = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            //sql.append("select * from comment_table");
            sql.append("SELECT b1.id id, b1.title title, ");
            sql.append("m1.username username, ");
            sql.append("(SELECT count(*) ");
            sql.append("FROM comment_table ");
            sql.append("WHERE boardId = b1.id) count ");
            sql.append("FROM board b1 INNER JOIN ");
            sql.append("member m1 ");
            sql.append("ON b1.memberId = m1.id");
//            sql.append("RIGHT OUTER JOIN comment_table ");
//            sql.append("ON board.id = comment_table.boardId ");
//            sql.append("group by boardId) b ");
//            sql.append("on b.boardId=a.id");

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CommedResDto commentlist = new CommedResDto();
                commentlist.setId(rs.getInt("id"));
                commentlist.setTitle(rs.getString("title"));
                commentlist.setUsername(rs.getString("username"));
                commentlist.setComments(rs.getInt("count"));
                comment.add(commentlist);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return comment;
    }
}
