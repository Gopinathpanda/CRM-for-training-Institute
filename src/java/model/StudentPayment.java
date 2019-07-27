package model;
public class StudentPayment {
 private int payment_id;
 private int registration_id;
 private String email;
 private int amount;
 private String payment_date;
 private String purpose;
 private String payment_mode;

    public StudentPayment() {
    }

    public StudentPayment(int payment_id, int registration_id, String email, int amount, String payment_date, String purpose, String payment_mode) {
        this.payment_id = payment_id;
        this.registration_id = registration_id;
        this.email = email;
        this.amount = amount;
        this.payment_date = payment_date;
        this.purpose = purpose;
        this.payment_mode = payment_mode;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }
}
