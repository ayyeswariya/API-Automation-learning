public class Post {

    private int id;        // ✅ Add this
    private String title;
    private String body;
    private int userId;

    public Post() {
    }

    public Post(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public int getUserId() { return userId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
    public void setUserId(int userId) { this.userId = userId; }
}