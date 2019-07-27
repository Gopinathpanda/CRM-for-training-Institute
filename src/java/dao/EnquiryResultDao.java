
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.EnquiryResult;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class EnquiryResultDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addEnquiryResult(EnquiryResult s)
    {
      template.execute("insert into tblenquiryresult values("+s.getEnquiry_result_id()+","+s.getEnquiry_id()+",'"+s.getEnquiry_by()+"','"+s.getDescription()+"','"+s.getStatus()+"',0)");
    }
    public void updateEnquiryResult(EnquiryResult s)
    {
     template.execute("update tblenquiryresult set enquiry_id="+s.getEnquiry_id()+",enquiry_by='"+s.getEnquiry_by()+"',description='"+s.getDescription()+"',status='"+s.getStatus()+"' where enquiry_result_id="+s.getEnquiry_result_id());
    }
    public void deleteEnquiryResult(int id)
    {
     template.execute("update tblenquiryresult set flag=1 where enquiry_result_id="+id);
    }
    public List<EnquiryResult> getAllEnquiryResult()
    {
     List<EnquiryResult> lst=template.query("select c.enquiry_result_id as enquiry_result_id,enquiry_by,description,status,email,c.enquiry_id as enquiry_id,c.flag from tblenquiryresult c join tblenquiry s on c.enquiry_id=s.enquiry_id where c.flag=0 order by enquiry_result_id",new RowMapper<EnquiryResult>() {
         @Override
         public EnquiryResult mapRow(ResultSet rs, int i) throws SQLException {
             EnquiryResult s=new EnquiryResult(rs.getInt("enquiry_result_id"),rs.getInt("enquiry_id"),rs.getString("email"),rs.getString("enquiry_by"),rs.getString("description"),rs.getString("status"));
             return s;
         }
     });
     return lst;
    }
    public EnquiryResult getEnquiryResultById(int id)
    {
    List<EnquiryResult> lst=template.query("select c.enquiry_result_id as enquiry_result_id,enquiry_by,description,status,email,c.enquiry_id as enquiry_id,c.flag from tblenquiryresult c join tblenquiry s on c.enquiry_id=s.enquiry_id  where enquiry_result_id="+id, new RowMapper<EnquiryResult>() {
        @Override
        public EnquiryResult mapRow(ResultSet rs, int i) throws SQLException {
             EnquiryResult s=new EnquiryResult(rs.getInt("enquiry_result_id"),rs.getInt("enquiry_id"),rs.getString("email"),rs.getString("enquiry_by"),rs.getString("description"),rs.getString("status"));
             return s;
        }
    });
    EnquiryResult st=lst.get(0);
    return st;
    }
     public int getNextEnquiryResultId()
    {
     List<Integer>lst=template.query("select (max(enquiry_result_id)+1) enquiry_result_id from tblenquiryresult",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("enquiry_result_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("enquiry_result_id");
            }
        });
        return lst.get(0);
     }
}
