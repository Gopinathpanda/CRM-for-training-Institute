package model;
public class TrainingFees {
   private int fee_id;
   private int program_id;
   private String course_name;
   private int lumsum;
   private int fee_mode_id;
   private String mode_name;

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
   private int registration_amount;

    public TrainingFees() {
    }

    public TrainingFees(int fee_id, int program_id,String course_name, int lumsum, int fee_mode_id,String mode_name, int registration_amount) {
        this.fee_id = fee_id;
        this.program_id = program_id;
        this.course_name=course_name;
        this.lumsum = lumsum;
        this.fee_mode_id = fee_mode_id;
        this.mode_name=mode_name;
        this.registration_amount = registration_amount;
    }
 public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public int getFee_id() {
        return fee_id;
    }

    public void setFee_id(int fee_id) {
        this.fee_id = fee_id;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getLumsum() {
        return lumsum;
    }

    public void setLumsum(int lumsum) {
        this.lumsum = lumsum;
    }
    public int getRegistration_amount() {
        return registration_amount;
    }

    public void setRegistration_amount(int registration_amount) {
        this.registration_amount = registration_amount;
    }
   
}
