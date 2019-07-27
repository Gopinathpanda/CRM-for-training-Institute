
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AllEnquiry;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class AllEnquiryDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addAllEnquiry(AllEnquiry s)
    {
      template.execute("insert into tblenquiry values("+s.getEnquiry_id()+",'"+s.getCandidate_name()+"','"+s.getEmail()+"','"+s.getMobile_no()+"','"+s.getDob()+"','"+s.getAddress()+"',"+s.getProgram_id()+","+s.getSource_id()+",'"+s.getEnquiry_date()+"',0)");
    }
    public void updateAllEnquiry(AllEnquiry s)
    {
     template.execute("update tblenquiry set candidate_name='"+s.getCandidate_name()+"',email='"+s.getEmail()+"',mobile_no='"+s.getMobile_no()+"',dob='"+s.getDob()+"',address='"+s.getAddress()+"',program_id="+s.getProgram_id()+",source_id="+s.getSource_id()+",enquiry_date='"+s.getEnquiry_date()+"' where enquiry_id="+s.getEnquiry_id());
    }
    public void deleteAllEnquiry(int id)
    {
     template.execute("update tblenquiry set flag=1 where enquiry_id="+id);
    }
    public List<AllEnquiry> getAllEnquiry()
    {
     List<AllEnquiry> lst=template.query("select c.enquiry_id as enquiry_id,candidate_name,email,mobile_no,dob,address,enquiry_date,course_name,c.program_id as program_id,source_name,c.source_id as source_id,c.flag from tblenquiry c join tbltraining_programs s on c.program_id=s.program_id join tblleadsources l on c.source_id=l.source_id where c.flag=0 order by enquiry_id",new RowMapper<AllEnquiry>() {
         @Override
         public AllEnquiry mapRow(ResultSet rs, int i) throws SQLException {
             AllEnquiry s=new AllEnquiry(rs.getInt("enquiry_id"),rs.getString("candidate_name"),rs.getString("email"),rs.getString("mobile_no"),rs.getString("dob"),rs.getString("address"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("source_id"),rs.getString("source_name"),rs.getString("enquiry_date"));
             return s;
         }
     });
     return lst;
    }
    public AllEnquiry getAllEnquiryById(int id)
    {
   List<AllEnquiry> lst=template.query("select c.enquiry_id as enquiry_id,candidate_name,email,mobile_no,dob,address,enquiry_date,course_name,c.program_id as program_id,source_name,c.source_id as source_id,c.flag from tblenquiry c join tbltraining_programs s on c.program_id=s.program_id join tblleadsources l on c.source_id=l.source_id where enquiry_id="+id,new RowMapper<AllEnquiry>() {
         @Override
         public AllEnquiry mapRow(ResultSet rs, int i) throws SQLException {
             AllEnquiry s=new AllEnquiry(rs.getInt("enquiry_id"),rs.getString("candidate_name"),rs.getString("email"),rs.getString("mobile_no"),rs.getString("dob"),rs.getString("address"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("source_id"),rs.getString("source_name"),rs.getString("enquiry_date"));
             return s;
        }
    });
    AllEnquiry st=lst.get(0);
    return st;
    }
     public int getNextAllEnquiryId()
    {
     List<Integer>lst=template.query("select (max(enquiry_id)+1) enquiry_id from tblenquiry",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("enquiry_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("enquiry_id");
            }
        });
        return lst.get(0);
     }
}
