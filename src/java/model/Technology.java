package model;
public class Technology {
  private int technology_id;
private String Technology_name;  

    public Technology() {
    }

    public Technology(int technology_id, String Technology_name) {
        this.technology_id = technology_id;
        this.Technology_name = Technology_name;
    }

    public int getTechnology_id() {
        return technology_id;
    }

    public void setTechnology_id(int technology_id) {
        this.technology_id = technology_id;
    }

    public String getTechnology_name() {
        return Technology_name;
    }

    public void setTechnology_name(String Technology_name) {
        this.Technology_name = Technology_name;
    }
}
