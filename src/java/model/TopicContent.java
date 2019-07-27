package model;
public class TopicContent {
    private int content_id;
    private String content_name;
    private int topic_id;
    private String topic_name;

    public TopicContent() {
    }

    public TopicContent(int content_id, String content_name, int topic_id, String topic_name) {
        this.content_id = content_id;
        this.content_name = content_name;
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
    
    
}
