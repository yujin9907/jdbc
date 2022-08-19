package boardService;

import boardService.board.BoardDao;
import boardService.comment.CommentDao;
import boardService.dto.CommedResDto;
import boardService.member.MemberDao;

import java.sql.Connection;
import java.util.ArrayList;

public class BoardService {

    private Connection connection;
    private BoardDao boardDao;
    private CommentDao commentDao;
    private MemberDao memberDao;

    public BoardService() {
    }

    public BoardService(Connection connection, BoardDao board, CommentDao comment, MemberDao member) {
        this.boardDao = board;
        this.commentDao = comment;
        this.memberDao = member;
        this.connection = connection;
    }

    public ArrayList<CommedResDto> 게시글목록보기(){
        return commentDao.게시글목록보기();
    }
}