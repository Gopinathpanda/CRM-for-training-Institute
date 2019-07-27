package model;
public class AllEnquiry {
 private int  enquiry_id;
 private String candidate_name;
 private String email;
 private String mobile_no;
 private String dob;
 private String address;
 private int program_id;
private String course_name; 
 private int source_id;
 private String source_name;
private String enquiry_date;
    public AllEnquiry() {
    }

    public AllEnquiry(int enquiry_id, String candidate_name, String email, String mobile_no, String dob, String address, int program_id, String course_name, int source_id, String source_name, String enquiry_date) {
        this.enquiry_id = enquiry_id;
        this.candidate_name = candidate_name;
        this.email = email;
        this.mobile_no = mobile_no;
        this.dob = dob;
        this.address = address;
        this.program_id = program_id;
        this.course_name = course_name;
        this.source_id = source_id;
        this.source_name = source_name;
        this.enquiry_date = enquiry_date;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public int getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(int enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public String getEnquiry_date() {
        return enquiry_date;
    }

    public void setEnquiry_date(String enquiry_date) {
        this.enquiry_date = enquiry_date;
    }
}
