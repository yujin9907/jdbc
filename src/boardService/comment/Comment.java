package boardService.comment;

public class Comment {

    private int id;
    private String comment;
    private int boardId;
    private int memberId;

    public Comment(int id, String comment, int boardId, int memberId) {
        this.id = id;
        this.comment = comment;
        this.boardId = boardId;
        this.memberId = memberId;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

}
