package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MentorDetails;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class MentorDetailsDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addMentorDetails(MentorDetails s)
    {
      template.execute("insert into tblmentordetails values("+s.getMentor_id()+",'"+s.getFull_name()+"','"+s.getContact_no()+"','"+s.getEmail()+"',0)");
    }
    public void updateMentorDetails(MentorDetails s)
    {
     template.execute("update tblmentordetails set full_name='"+s.getFull_name()+"',contact_no='"+s.getContact_no()+"',email='"+s.getEmail()+"' where mentor_id="+s.getMentor_id());
    }
    public void deleteMentorDetails(int id)
    {
     template.execute("update tblmentordetails set flag=1 where mentor_id="+id);
    }
    public List<MentorDetails> getAllMentorDetails()
    {
     List<MentorDetails> lst=template.query("select * from tblmentordetails where flag=0 order by mentor_id",new RowMapper<MentorDetails>() {
         @Override
         public MentorDetails mapRow(ResultSet rs, int i) throws SQLException {
             MentorDetails s=new MentorDetails(rs.getInt("mentor_id"),rs.getString("Full_name"),rs.getString("contact_no"),rs.getString("email"));
             return s;
         }
     });
     return lst;
    }
    public MentorDetails getMentorDetailsById(int id)
    {
    List<MentorDetails> lst=template.query("select * from tblmentordetails where mentor_id="+id, new RowMapper<MentorDetails>() {
        @Override
        public MentorDetails mapRow(ResultSet rs, int i) throws SQLException {
             MentorDetails s=new MentorDetails(rs.getInt("mentor_id"),rs.getString("Full_name"),rs.getString("contact_no"),rs.getString("email"));
             return s;
        }
    });
    MentorDetails st=lst.get(0);
    return st;
    }
     public int getNextMentorDetailsId()
    {
     List<Integer>lst=template.query("select (max(mentor_id)+1) mentor_id from tblmentordetails",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("mentor_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("mentor_id");
            }
        });
        return lst.get(0);
     }
}
