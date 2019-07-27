package model;
public class MentorAssign {
private int assigned_mentor_id;
private int mentor_id;
private String full_name;
private int content_id;
private String content_name;

    public MentorAssign() {
    }

    public MentorAssign(int assigned_mentor_id, int mentor_id, String full_name, int content_id, String content_name) {
        this.assigned_mentor_id = assigned_mentor_id;
        this.mentor_id = mentor_id;
        this.full_name = full_name;
        this.content_id = content_id;
        this.content_name = content_name;
    }

    public int getAssigned_mentor_id() {
        return assigned_mentor_id;
    }

    public void setAssigned_mentor_id(int assigned_mentor_id) {
        this.assigned_mentor_id = assigned_mentor_id;
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
}
