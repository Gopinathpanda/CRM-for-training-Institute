package model;
public class MentorDetails {
    private int mentor_id;
    private String full_name;
    private String contact_no;
    private String email;

    public MentorDetails() {
    }

    public MentorDetails(int mentor_id, String full_name, String contact_no, String email) {
        this.mentor_id = mentor_id;
        this.full_name = full_name;
        this.contact_no = contact_no;
        this.email = email;
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

    public void setFullname(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
