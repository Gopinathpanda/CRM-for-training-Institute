package model;
public class Location {
 private int location_id;
 private String location_name;
  private int city_id;
 private String city_name;

    public Location() {
    }

    public Location(int location_id, String location_name, int city_id, String city_name) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
