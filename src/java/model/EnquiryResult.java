package model;
public class EnquiryResult {
 private int enquiry_result_id;
 private int enquiry_id;
 private String email;
 private String enquiry_by;
 private String description;
 private String status;

    public EnquiryResult() {
    }

    public EnquiryResult(int enquiry_result_id, int enquiry_id, String email, String enquiry_by, String description, String status) {
        this.enquiry_result_id = enquiry_result_id;
        this.enquiry_id = enquiry_id;
        this.email = email;
        this.enquiry_by = enquiry_by;
        this.description = description;
        this.status = status;
    }

    public int getEnquiry_result_id() {
        return enquiry_result_id;
    }

    public void setEnquiry_result_id(int enquiry_result_id) {
        this.enquiry_result_id = enquiry_result_id;
    }

    public int getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(int enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnquiry_by() {
        return enquiry_by;
    }

    public void setEnquiry_by(String enquiry_by) {
        this.enquiry_by = enquiry_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
