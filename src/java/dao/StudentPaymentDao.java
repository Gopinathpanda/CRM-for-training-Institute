
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.StudentPayment;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class StudentPaymentDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addStudentPayment(StudentPayment s)
    {
      template.execute("insert into tblstudentpayment values("+s.getPayment_id()+","+s.getRegistration_id()+","+s.getAmount()+",'"+s.getPayment_date()+"','"+s.getPurpose()+"','"+s.getPayment_mode()+"',0)");
    }
    public void updateStudentPayment(StudentPayment s)
    {
     template.execute("update tblstudentpayment set registration_id="+s.getRegistration_id()+",amount="+s.getAmount()+",payment_date='"+s.getPayment_date()+"',purpose='"+s.getPurpose()+"',payment_mode='"+s.getPayment_mode()+"' where payment_id="+s.getPayment_id());
    }
    public void deleteStudentPayment(int id)
    {
     template.execute("update tblstudentpayment set flag=1 where payment_id="+id);
    }
    public List<StudentPayment> getAllStudentPayment()
    {
     List<StudentPayment> lst=template.query("select l.payment_id as payment_id,amount,payment_date,purpose,payment_mode,email,l.registration_id as registration_id,l.flag from tblstudentpayment l join tblstudentprofile sp on l.registration_id=sp.registration_id where l.flag=0 order by payment_id",new RowMapper<StudentPayment>() {
         @Override
         public StudentPayment mapRow(ResultSet rs, int i) throws SQLException {
             StudentPayment s=new StudentPayment(rs.getInt("payment_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("amount"),rs.getString("payment_date"),rs.getString("purpose"),rs.getString("payment_mode"));
             return s;
         }
     });
     return lst;
    }
    public StudentPayment getStudentPaymentById(int id)
    {
    List<StudentPayment> lst=template.query("select l.payment_id as payment_id,amount,payment_date,purpose,payment_mode,email,l.registration_id as registration_id,l.flag from tblstudentpayment l join tblstudentprofile sp on l.registration_id=sp.registration_id  where l.flag=0 and l.registration_id="+id, new RowMapper<StudentPayment>() {
        @Override
         public StudentPayment mapRow(ResultSet rs, int i) throws SQLException {
             StudentPayment s=new StudentPayment(rs.getInt("payment_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("amount"),rs.getString("payment_date"),rs.getString("purpose"),rs.getString("payment_mode"));
             return s;
        }
    });
    StudentPayment st=lst.get(0);
    return st;
    }
     public int getNextStudentPaymentId()
    {
     List<Integer>lst=template.query("select (max(payment_id)+1) payment_id from tblstudentpayment",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("payment_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("payment_id");
            }
        });
        return lst.get(0);
     }
}
