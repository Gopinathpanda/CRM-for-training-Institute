
package model;
public class Qualification {
 private int qualification_id;
 private String qualification;
 private int flag;

    public Qualification() {
    }

    public Qualification(int qualification_id, String qualification, int flag) {
        this.qualification_id = qualification_id;
        this.qualification = qualification;
        this.flag = flag;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
