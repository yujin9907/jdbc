package boardService;

import boardService.DB.DBConnection;
import boardService.board.Board;
import boardService.board.BoardDao;
import boardService.comment.Comment;
import boardService.comment.CommentDao;
import boardService.dto.CommedResDto;
import boardService.member.Member;
import boardService.member.MemberDao;

import java.sql.Connection;
import java.util.ArrayList;

public class BoardApp {

    public static void main(String[] args) {
        Connection conn = DBConnection.connection();

        MemberDao memberDao = new MemberDao(conn);
        CommentDao commentDao = new CommentDao(conn);
        BoardDao boardDao = new BoardDao(conn);

        BoardService boardService = new BoardService(conn, boardDao, commentDao, memberDao);

        ArrayList<CommedResDto> commedResDtoArrayList = boardService.게시글목록보기();

        for(int i = 0; i<commedResDtoArrayList.size();i++){
            System.out.println(commedResDtoArrayList.get(i).getId());
            System.out.println(commedResDtoArrayList.get(i).getTitle());
            System.out.println(commedResDtoArrayList.get(i).getComments());
            System.out.println(commedResDtoArrayList.get(i).getUsername());
        }
        // 그냥 db에서 커밋을 안 해서 테스트케이스가 없어서 발생한 오류; 덕분에 디버깅 존나 열심히 해봤따

    }
}
