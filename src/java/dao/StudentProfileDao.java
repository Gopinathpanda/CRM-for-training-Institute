
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.StudentProfile;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class StudentProfileDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addProfile(StudentProfile s)
    {
        
        template.execute("insert into tblstudentprofile values("+s.getRegistration_id()+",'"+s.getFirst_name()+"','"+s.getMiddle_name()+"','"+s.getLast_name()+"','"+s.getDob()+"','"+s.getContact_no()+"','"+s.getEmail()+"','"+s.getParent_number()+"','"+s.getGender()+"','"+s.getRegistration_date()+"',"+s.getLocation_id()+",'"+s.getPermanent_address()+"','"+s.getLocal_address()+"',0)");
    }
    public void updateProfile(StudentProfile s)
    {
     template.execute("update tblstudentprofile set first_name='"+s.getFirst_name()+"',middle_name='"+s.getMiddle_name()+"',last_name='"+s.getLast_name()+"',dob='"+s.getDob()+"',contact_no='"+s.getContact_no()+"',email='"+s.getEmail()+"',parent_number='"+s.getParent_number()+"',gender='"+s.getGender()+"',registration_date='"+s.getRegistration_date()+"',location_id="+s.getLocation_id()+",permanent_address='"+s.getPermanent_address()+"',local_address='"+s.getLocal_address()+"' where registration_id="+s.getRegistration_id());
    }
    public void deleteProfile(int id)
    {
     template.execute("update tblstudentprofile set flag=1 where registration_id="+id);
    }
     public List<StudentProfile> getAllStudentProfile()
    {
     List<StudentProfile> lst=template.query("select l.registration_id as registration_id,first_name,middle_name,last_name,dob,contact_no,email,parent_number,gender,registration_date,location_name,l.location_id as location_id,permanent_address,local_address,tc.city_id as city_id,city_name,l.flag from tblstudentprofile l join tbllocation lc on l.location_id=lc.location_id join tblcity tc on tc.city_id=lc.city_id where l.flag=0",new RowMapper<StudentProfile>() {
         @Override
         public StudentProfile mapRow(ResultSet rs, int i) throws SQLException {
             StudentProfile s=new StudentProfile(rs.getInt("registration_id"),rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),rs.getString("dob"),rs.getString("contact_no"),rs.getString("email"),rs.getString("parent_number"),rs.getString("gender"),rs.getString("registration_date"),rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("location_id"),rs.getString("location_name"),rs.getString("permanent_address"),rs.getString("local_address"));
             return s;
         }
     });
     return lst;
    }
    public StudentProfile getStudentProfileById(int id)
    {
    List<StudentProfile> lst=template.query("select l.registration_id as registration_id,first_name,middle_name,last_name,dob,contact_no,email,parent_number,gender,registration_date,location_name,l.location_id as location_id,permanent_address,local_address,tc.city_id as city_id,city_name,l.flag from tblstudentprofile l join tbllocation lc on l.location_id=lc.location_id join tblcity tc on tc.city_id=lc.city_id where l.flag=0 and registration_id="+id, new RowMapper<StudentProfile>() {
        @Override
        public StudentProfile mapRow(ResultSet rs, int i) throws SQLException {
             StudentProfile s=new StudentProfile(rs.getInt("registration_id"),rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),rs.getString("dob"),rs.getString("contact_no"),rs.getString("email"),rs.getString("parent_number"),rs.getString("gender"),rs.getString("registration_date"),rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("location_id"),rs.getString("location_name"),rs.getString("permanent_address"),rs.getString("local_address"));
             return s;
        }
    });
    StudentProfile st=lst.get(0);
    return st;
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
