
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.RegistrationDetails;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class RegistrationDetailsDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addDetails(RegistrationDetails s)
    {
      template.execute("insert into tblregistrationdetails values("+s.getRegistration_details_id()+","+s.getRegistration_id()+","+s.getProgram_id()+","+s.getFee_mode_id()+",'"+s.getStatus()+"',0)");
    }
    public void updateDetails(RegistrationDetails s)
    {
     template.execute("update tblregistrationdetails set registration_id="+s.getRegistration_id()+",program_id="+s.getProgram_id()+",fee_mode_id="+s.getFee_mode_id()+",status='"+s.getStatus()+"' where registration_details_id="+s.getRegistration_details_id());
    }
    public void deleteDetails(int id)
    {
     template.execute("update tblregistrationdetails set flag=1 where registration_details_id="+id);
    }
    public List<RegistrationDetails> getAllDetails()
    {
     List<RegistrationDetails> lst=template.query("select l.registration_details_id as registration_details_id,status,email,l.registration_id as registration_id,course_name,l.program_id as program_id,mode_name,l.fee_mode_id as fee_mode_id,l.flag from tblregistrationdetails l join tblstudentprofile sp on l.registration_id=sp.registration_id join tbltraining_programs ld on l.program_id=ld.program_id join tblFeesModes tq on l.fee_mode_id=tq.fee_mode_id where l.flag=0 order by registration_details_id",new RowMapper<RegistrationDetails>() {
         @Override
         public RegistrationDetails mapRow(ResultSet rs, int i) throws SQLException {
             RegistrationDetails s=new RegistrationDetails(rs.getInt("registration_details_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getString("status"));
             return s;
         }
     });
     return lst;
    }
    public RegistrationDetails getDetailsById(int id)
    {
    List<RegistrationDetails> lst=template.query("select l.registration_details_id as registration_details_id,status,email,l.registration_id as registration_id,course_name,l.program_id as program_id,mode_name,l.fee_mode_id as fee_mode_id,l.flag from tblregistrationdetails l join tblstudentprofile sp on l.registration_id=sp.registration_id join tbltraining_programs ld on l.program_id=ld.program_id join tblFeesModes tq on l.fee_mode_id=tq.fee_mode_id where l.flag=0 and l.registration_id="+id, new RowMapper<RegistrationDetails>() {
        @Override
        public RegistrationDetails mapRow(ResultSet rs, int i) throws SQLException {
             RegistrationDetails s=new RegistrationDetails(rs.getInt("registration_details_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getString("status"));
             return s;
        }
    });
    RegistrationDetails st=lst.get(0);
    return st;
    }
     public int getNextDetailsId()
    {
     List<Integer>lst=template.query("select (max(registration_details_id)+1) registration_details_id from tblregistrationdetails",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("registration_details_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("registration_details_id");
            }
        });
        return lst.get(0);
     }
}
