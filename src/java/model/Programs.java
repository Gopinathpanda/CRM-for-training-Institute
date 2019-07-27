 package model;
public class Programs {
private int program_id;    
private String course_id;
private String course_name;
private String duration_course;
private int technology_id;    
private String technology_name;

    public Programs() {
    }

    public Programs(int program_id, String course_id, String course_name, String duration_course, int technology_id, String technology_name) {
        this.program_id = program_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.duration_course = duration_course;
        this.technology_id = technology_id;
        this.technology_name = technology_name;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDuration_course() {
        return duration_course;
    }

    public void setDuration_course(String duration_course) {
        this.duration_course = duration_course;
    }

    public int getTechnology_id() {
        return technology_id;
    }

    public void setTechnology_id(int technology_id) {
        this.technology_id = technology_id;
    }

    public String getTechnology_name() {
        return technology_name;
    }

    public void setTechnology_name(String technology_name) {
        this.technology_name = technology_name;
    }
}
