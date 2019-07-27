package model;
public class RegistrationQualification {
  private int  registration_qualification_id;
  private int registration_id;
  private String email;
  private int qualification_id;
  private String qualification;
  private int specialization_id;
  private String specialization;
  private String university;
  private float percentage;
  private String passing_year;

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

    public RegistrationQualification() {
    }

    public RegistrationQualification(int registration_qualification_id, int registration_id, String email,int qualification_id, String qualification, int specialization_id, String specialization, String university, float percentage, String passing_year) {
        this.registration_qualification_id = registration_qualification_id;
        this.registration_id = registration_id;
        this.email = email;
        this.qualification_id=qualification_id;
        this.qualification=qualification;
        this.specialization_id = specialization_id;
        this.specialization = specialization;
        this.university = university;
        this.percentage = percentage;
        this.passing_year = passing_year;
    }

    public int getRegistration_qualification_id() {
        return registration_qualification_id;
    }

    public void setRegistration_qualification_id(int registration_qualification_id) {
        this.registration_qualification_id = registration_qualification_id;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }
}
