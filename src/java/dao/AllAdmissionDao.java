
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AllAdmission;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class AllAdmissionDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
     public void addProfile(AllAdmission s)
    {
        
        template.execute("insert into tblstudentprofile values("+s.getRegistration_id()+",'"+s.getFirst_name()+"','"+s.getMiddle_name()+"','"+s.getLast_name()+"','"+s.getDob()+"','"+s.getContact_no()+"','"+s.getEmail()+"','"+s.getParent_number()+"','"+s.getGender()+"','"+s.getRegistration_date()+"',"+s.getLocation_id()+",'"+s.getPermanent_address()+"','"+s.getLocal_address()+"',0)");
    }
      public List<AllAdmission> getAllAdmission()
    {
    List<AllAdmission> lst=template.query("select * from tblstudentprofile where flag=0", new RowMapper<AllAdmission>() {
        @Override
        public AllAdmission mapRow(ResultSet rs, int i) throws SQLException {
             AllAdmission s=new AllAdmission(rs.getInt("registration_id"),rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),rs.getString("dob"),rs.getString("email"));
             return s;
        }
    });
    
    return lst;
    }
        public int getNextStudentProfileId()
    {
     List<Integer>lst=template.query("select (max(registration_id)+1) registration_id from tblstudentprofile",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("registration_id");
                if(id==0)
                {
                    return 1;
                }
                
                  return rs.getInt("registration_id");
            }
        });
        return lst.get(0);
     }
     }
       

