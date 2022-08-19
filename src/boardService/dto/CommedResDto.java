package boardService.dto;

public class CommedResDto {
    private int id;
    private String title;
    private String username;
    private int comments;

    public CommedResDto() {
    }

    public CommedResDto(int id, String title, String username, int comments) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
