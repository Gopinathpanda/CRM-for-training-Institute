
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.LeadSource;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class LeadSourceDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addLeadSource(LeadSource s)
    {
        
      template.execute("insert into tblleadsources values("+s.getSource_id()+",'"+s.getSource_name()+"',0)");
    }
    public void updateLeadSource(LeadSource s)
    {
     template.execute("update tblleadsources set source_name='"+s.getSource_name()+"'where source_id="+s.getSource_id());
    }
    public void deleteLeadSource(int id)
    {
     template.execute("update tblleadsources set flag=1 where source_id="+id);
    }
    public List<LeadSource> getAllLeadSource()
    {
     List<LeadSource> lst=template.query("select * from tblleadsources where flag=0",new RowMapper<LeadSource>() {
         @Override
         public LeadSource mapRow(ResultSet rs, int i) throws SQLException {
             LeadSource s=new LeadSource(rs.getInt("source_id"),rs.getString("source_name"));
             return s;
         }
     });
     return lst;
    }
    public LeadSource getLeadSourceById(int id)
    {
    List<LeadSource> lst=template.query("select * from tblleadsources where source_id="+id, new RowMapper<LeadSource>() {
        @Override
        public LeadSource mapRow(ResultSet rs, int i) throws SQLException {
        LeadSource s=new LeadSource(rs.getInt("source_id"),rs.getString("source_name"));
        return s;
        }
    });
    LeadSource st=lst.get(0);
    return st;
    }
    public int getNextLeadSourceId()
    {
     List<Integer>lst=template.query("select (max(source_id)+1) source_id from tblleadsources",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("source_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("source_id");
            }
        });
        return lst.get(0);
     }
}
