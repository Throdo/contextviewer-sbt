package models;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 27/05/13
 * Time: 08:39
 */
public class OrangeDocument {
    private String id;
    private String content;

    public OrangeDocument(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
