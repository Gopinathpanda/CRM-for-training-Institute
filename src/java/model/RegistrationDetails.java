package model;
public class RegistrationDetails {
  private int registration_details_id;
  private int registration_id;
  private String email;
  private int program_id;
  private String course_name;
  private int fee_mode_id;
  private String mode_name;
  private String status;

    public RegistrationDetails() {
    }

    public RegistrationDetails(int registration_details_id, int registration_id, String email, int program_id, String course_name, int fee_mode_id, String mode_name, String status) {
        this.registration_details_id = registration_details_id;
        this.registration_id = registration_id;
        this.email = email;
        this.program_id = program_id;
        this.course_name = course_name;
        this.fee_mode_id = fee_mode_id;
        this.mode_name = mode_name;
        this.status = status;
    }

    public int getRegistration_details_id() {
        return registration_details_id;
    }

    public void setRegistration_details_id(int registration_details_id) {
        this.registration_details_id = registration_details_id;
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getFee_mode_id() {
        return fee_mode_id;
    }

    public void setFee_mode_id(int fee_mode_id) {
        this.fee_mode_id = fee_mode_id;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
