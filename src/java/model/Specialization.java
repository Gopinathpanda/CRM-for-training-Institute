
package model;
public class Specialization {
  private int specialization_id;
private String specialization;  
private int qualification_id;
private String qualification;

    public Specialization() {
    }

    public Specialization(int specialization_id, String specialization, int qualification_id, String qualification) {
        this.specialization_id = specialization_id;
        this.specialization = specialization;
        this.qualification_id = qualification_id;
        this.qualification = qualification;
    }

    public int getSpecialization_id() {
        return specialization_id;
    }

    public void setSpecialization_id(int specialization_id) {
        this.specialization_id = specialization_id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getQualification_id() {
        return qualification_id;
    }

    public void setQualification_id(int qualification_id) {
        this.qualification_id = qualification_id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
