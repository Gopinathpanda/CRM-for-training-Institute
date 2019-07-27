
package model;
public class FeeMode {
  private int fee_mode_id;
private String mode_name;
private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public FeeMode() {
    }

    public FeeMode(int fee_mode_id, String mode_name,int flag) {
        this.fee_mode_id = fee_mode_id;
        this.mode_name = mode_name;
        this.flag=flag;
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
}
