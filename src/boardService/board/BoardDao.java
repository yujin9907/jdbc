package boardService.board;

import product.domain.customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {

    private Connection conn;

    public BoardDao(Connection conn) {
        this.conn = conn;
    }

    //    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Board> findAll(){
        ArrayList<Board> board = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM board ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Board boards = new Board();
                boards.setId(rs.getInt("id"));
                boards.setTitle(rs.getString("getTitle"));
                boards.setContent(rs.getString("getContent"));
                boards.setMemberId(rs.getInt("getMemberId"));

                board.add(boards);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }


    public Board findById(int id){
        Board boards = new Board();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM board WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                boards.setId(rs.getInt("id"));
                boards.setTitle(rs.getString("getTitle"));
                boards.setContent(rs.getString("getContent"));
                boards.setMemberId(rs.getInt("getMemberId"));

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return boards;
    }


    public int insert(Board board){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO board ");
            sql.append("VALUES(?, ?, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, board.getId());
            ps.setString(2, board.getTitle());
            ps.setString(3, board.getContent());
            ps.setInt(3, board.getMemberId());


            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 컬럼을 다 적어줄 수 없으니 웨어절에 걸리는 id 빼고 나머지는 커스터머로
    public int updateById(int id, Board board){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE board title = ?, content = ?, memberId = ?");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getMemberId());
            ps.setInt(4, board.getId());

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
            sql.append("DELETE FROM board WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
