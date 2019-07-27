package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MentorAssign;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class MentorAssignDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addMentorAssign(MentorAssign s)
    {
      template.execute("insert into tblassignedmentors values("+s.getAssigned_mentor_id()+","+s.getMentor_id()+","+s.getContent_id()+",0)");
    }
    public void updateMentorAssign(MentorAssign s)
    {
     template.execute("update tblassignedmentors set mentor_id="+s.getMentor_id()+",content_id="+s.getContent_id()+" where assigned_mentor_id="+s.getAssigned_mentor_id());
    }
    public void deleteMentorAssign(int id)
    {
     template.execute("update tblassignedmentors set flag=1 where assigned_mentor_id="+id);
    }
    public List<MentorAssign> getAllMentorAssign()
    {
     List<MentorAssign> lst=template.query("select c.assigned_mentor_id as assigned_mentor_id,full_name,c.mentor_id as mentor_id,content_name,c.content_id as content_id,c.flag from tblassignedmentors c join tblmentordetails s on c.mentor_id=s.mentor_id join tbltopiccontents t on c.content_id=t.content_id where c.flag=0 order by assigned_mentor_id",new RowMapper<MentorAssign>() {
         @Override
         public MentorAssign mapRow(ResultSet rs, int i) throws SQLException {
             MentorAssign s=new MentorAssign(rs.getInt("assigned_mentor_id"),rs.getInt("mentor_id"),rs.getString("full_name"),rs.getInt("content_id"),rs.getString("content_name"));
             return s;
         }
     });
     return lst;
    }
    public MentorAssign getMentorAssignById(int id)
    {
    List<MentorAssign> lst=template.query("select c.assigned_mentor_id as assigned_mentor_id,full_name,c.mentor_id as mentor_id,content_name,c.content_id as content_id,c.flag from tblassignedmentors c join tblmentordetails s on c.mentor_id=s.mentor_id join tbltopiccontents t on c.content_id=t.content_id  where assigned_mentor_id="+id, new RowMapper<MentorAssign>() {
        @Override
        public MentorAssign mapRow(ResultSet rs, int i) throws SQLException {
              MentorAssign s=new MentorAssign(rs.getInt("assigned_mentor_id"),rs.getInt("mentor_id"),rs.getString("full_name"),rs.getInt("content_id"),rs.getString("content_name"));
             return s;
        }
    });
    MentorAssign st=lst.get(0);
    return st;
    }
     public int getNextMentorAssignId()
    {
     List<Integer>lst=template.query("select (max(assigned_mentor_id)+1) assigned_mentor_id from tblassignedmentors",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("assigned_mentor_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("assigned_mentor_id");
            }
        });
        return lst.get(0);
     }
}
