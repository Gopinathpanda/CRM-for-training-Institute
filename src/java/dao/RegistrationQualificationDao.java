
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.RegistrationQualification;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class RegistrationQualificationDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addRegdQualification(RegistrationQualification s)
    {
      template.execute("insert into tblregistrationqualification values("+s.getRegistration_qualification_id()+","+s.getRegistration_id()+","+s.getSpecialization_id()+",'"+s.getUniversity()+"',"+s.getPercentage()+",'"+s.getPassing_year()+"',0)");
    }
    public void updateRegdQualification(RegistrationQualification s)
    {
     template.execute("update tblregistrationqualification set registration_id="+s.getRegistration_id()+",specialization_id="+s.getSpecialization_id()+",university='"+s.getUniversity()+"',percentage="+s.getPercentage()+",passing_year='"+s.getPassing_year()+"' where registration_qualification_id="+s.getRegistration_qualification_id());
    }
    public void deleteRegdQualification(int id)
    {
     template.execute("update tblregistrationqualification set flag=1 where registration_qualification_id="+id);
    }
    public List<RegistrationQualification> getAllRegdQualification()
    {
     List<RegistrationQualification> lst=template.query("select l.registration_qualification_id as registration_qualification_id,university,percentage,passing_year,email,l.registration_id as registration_id,specialization,l.specialization_id as specialization_id,qualification,tq.qualification_id as qualification_id,l.flag from tblregistrationqualification l join tblstudentprofile sp on l.registration_id=sp.registration_id join tblspecialization ld on l.specialization_id=ld.specialization_id join tblqualification tq on tq.qualification_id=ld.qualification_id  where l.flag=0 order by registration_qualification_id",new RowMapper<RegistrationQualification>() {
         @Override
         public RegistrationQualification mapRow(ResultSet rs, int i) throws SQLException {
             RegistrationQualification s=new RegistrationQualification(rs.getInt("registration_qualification_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getString("university"),rs.getFloat("percentage"),rs.getString("passing_year"));
             return s;
         }
     });
     return lst;
    }
    public RegistrationQualification getregQualificationById(int id)
    {
   List<RegistrationQualification> lst=template.query("select l.registration_qualification_id as registration_qualification_id,university,percentage,passing_year,email,l.registration_id as registration_id,specialization,l.specialization_id as specialization_id,qualification,tq.qualification_id as qualification_id,l.flag from tblregistrationqualification l join tblstudentprofile sp on l.registration_id=sp.registration_id join tblspecialization ld on l.specialization_id=ld.specialization_id join tblqualification tq on tq.qualification_id=ld.qualification_id  where l.flag=0 and l.registration_id="+id,new RowMapper<RegistrationQualification>() {
         @Override
         public RegistrationQualification mapRow(ResultSet rs, int i) throws SQLException {
              RegistrationQualification s=new RegistrationQualification(rs.getInt("registration_qualification_id"),rs.getInt("registration_id"),rs.getString("email"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getString("university"),rs.getFloat("percentage"),rs.getString("passing_year"));
             return s;
        }
    });
    RegistrationQualification st=lst.get(0);
    return st;
    }
     public int getNextRegdQualificationId()
    {
     List<Integer>lst=template.query("select (max(registration_qualification_id)+1) registration_qualification_id from tblregistrationqualification",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("registration_qualification_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("registration_qualification_id");
            }
        });
        return lst.get(0);
     }
}
