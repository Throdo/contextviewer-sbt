package models;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 27/05/13
 * Time: 08:39
 */
public class OrangeDocument {
    private String id;
    private String jsonValue;

    public OrangeDocument(String id, String jsonValue) {
        this.id = id;
        this.jsonValue = jsonValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
    }
}
