package model;
public class TrainingTopics {
    private int topic_id;
    private String topic_name;
    private int module_id;
    private String module_name;

    public TrainingTopics() {
    }

    public TrainingTopics(int topic_id, String topic_name, int module_id, String module_name) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.module_id = module_id;
        this.module_name = module_name;
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

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
}
