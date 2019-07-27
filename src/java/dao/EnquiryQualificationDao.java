
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.EnquiryQualification;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class EnquiryQualificationDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addEnquiryQualification(EnquiryQualification s)
    {
      template.execute("insert into tblenquiryqualification values("+s.getEnquiry_qualification_id()+","+s.getEnquiry_id()+","+s.getSpecialization_id()+",'"+s.getUniversity()+"',"+s.getPercentage()+",'"+s.getPassing_year()+"',0)");
    }
    public void updateEnquiryQualification(EnquiryQualification s)
    {
     template.execute("update tblenquiryqualification set enquiry_id="+s.getEnquiry_id()+",specialization_id="+s.getSpecialization_id()+",university='"+s.getUniversity()+"',percentage="+s.getPercentage()+",passing_year='"+s.getPassing_year()+"' where enquiry_qualification_id="+s.getEnquiry_qualification_id());
    }
    public void deleteEnquiryQualification(int id)
    {
     template.execute("update tblenquiryqualification set flag=1 where enquiry_qualification_id="+id);
    }
    public List<EnquiryQualification> getAllEnquiryQualification()
    {
     List<EnquiryQualification> lst=template.query("select l.enquiry_qualification_id as enquiry_qualification_id,university,percentage,passing_year,email,l.enquiry_id as enquiry_id,specialization,l.specialization_id as specialization_id,tq.qualification_id as qualification_id,qualification,l.flag from tblenquiryqualification l join tblspecialization sp on l.specialization_id=sp.specialization_id join tblqualification tq on tq.qualification_id=sp.qualification_id join tblenquiry lc on l.enquiry_id=lc.enquiry_id where l.flag=0 order by enquiry_qualification_id",new RowMapper<EnquiryQualification>() {
         @Override
         public EnquiryQualification mapRow(ResultSet rs, int i) throws SQLException {
             EnquiryQualification s=new EnquiryQualification(rs.getInt("enquiry_qualification_id"),rs.getInt("enquiry_id"),rs.getString("email"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getString("university"),rs.getFloat("percentage"),rs.getString("passing_year"));
             return s;
         }
     });
     return lst;
    }
    public EnquiryQualification getEnquiryQualificationById(int id)
    {
    List<EnquiryQualification> lst=template.query("select l.enquiry_qualification_id as enquiry_qualification_id,university,percentage,passing_year,email,l.enquiry_id as enquiry_id,specialization,l.specialization_id as specialization_id,tq.qualification_id as qualification_id,qualification,l.flag from tblenquiryqualification l join tblspecialization sp on l.specialization_id=sp.specialization_id join tblqualification tq on tq.qualification_id=sp.qualification_id join tblenquiry lc on l.enquiry_id=lc.enquiry_id  where enquiry_qualification_id="+id, new RowMapper<EnquiryQualification>() {
        @Override
         public EnquiryQualification mapRow(ResultSet rs, int i) throws SQLException {
             EnquiryQualification s=new EnquiryQualification(rs.getInt("enquiry_qualification_id"),rs.getInt("enquiry_id"),rs.getString("email"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getString("university"),rs.getFloat("percentage"),rs.getString("passing_year"));
             return s;
        }
    });
    EnquiryQualification st=lst.get(0);
    return st;
    }
     public int getNextEnquiryQualificationId()
    {
     List<Integer>lst=template.query("select (max(enquiry_qualification_id)+1) enquiry_qualification_id from tblenquiryqualification",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("enquiry_qualification_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("enquiry_qualification_id");
            }
        });
        return lst.get(0);
     }
}
