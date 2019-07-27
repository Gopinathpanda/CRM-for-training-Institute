
package model;
public class Lead {
 private int lead_id;
 private String candidate_name;
 private String email;
 private int mobile;
 private int city_id;
 private String city_name;
 private int location_id;
 private String location_name;
 private String address;
 private int qualification_id;
 private String qualification;
 private int specialization_id;
 private String specialization;
 private int source_id;
 private String source_name;

    public Lead() {
    }

    public Lead(int lead_id, String candidate_name, String email, int mobile, int city_id, String city_name, int location_id, String location_name, String address, int qualification_id, String qualification, int specialization_id, String specialization, int source_id, String source_name) {
        this.lead_id = lead_id;
        this.candidate_name = candidate_name;
        this.email = email;
        this.mobile = mobile;
        this.city_id = city_id;
        this.city_name = city_name;
        this.location_id = location_id;
        this.location_name = location_name;
        this.address = address;
        this.qualification_id = qualification_id;
        this.qualification = qualification;
        this.specialization_id = specialization_id;
        this.specialization = specialization;
        this.source_id = source_id;
        this.source_name = source_name;
    }

    public int getLead_id() {
        return lead_id;
    }

    public void setLead_id(int lead_id) {
        this.lead_id = lead_id;
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

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

}