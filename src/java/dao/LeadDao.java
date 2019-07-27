
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Lead;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class LeadDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addLead(Lead s)
    {
        template.execute("insert into leads values("+s.getLead_id()+",'"+s.getCandidate_name()+"','"+s.getEmail()+"',"+s.getMobile()+","+s.getLocation_id()+",'"+s.getAddress()+"',"+s.getSpecialization_id()+","+s.getSource_id()+",0)");
    }
    public void updateLeads(Lead s)
    {
     template.execute("update leads set candidate_name='"+s.getCandidate_name()+"',email='"+s.getEmail()+"',mobile="+s.getMobile()+",location_id="+s.getLocation_id()+",address='"+s.getAddress()+"',specialization_id="+s.getSpecialization_id()+",source_id="+s.getSource_id()+" where lead_id="+s.getLead_id());
    }
    public void deleteLead(int id)
    {
     template.execute("update leads set flag=1 where lead_id="+id);
    }
     public List<Lead> getAllLead()
    {
     List<Lead> lst=template.query("select l.lead_id as lead_id,candidate_name,email,mobile,address,location_name,l.location_id as location_id,tc.city_id as city_id,city_name,specialization,l.specialization_id as specialization_id,tq.qualification_id as qualification_id,qualification,source_name,l.source_id as source_id,l.flag from leads l join tblspecialization sp on l.specialization_id=sp.specialization_id join tblleadsources ld on l.source_id=ld.source_id join tblqualification tq on tq.qualification_id=sp.qualification_id join tbllocation lc on l.location_id=lc.location_id join tblcity tc on tc.city_id=lc.city_id where l.flag=0",new RowMapper<Lead>() {
         @Override
         public Lead mapRow(ResultSet rs, int i) throws SQLException {
             Lead s=new Lead(rs.getInt("lead_id"),rs.getString("candidate_name"),rs.getString("email"),rs.getInt("mobile"),rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("location_id"),rs.getString("location_name"),rs.getString("address"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getInt("source_id"),rs.getString("source_name"));
             return s;
         }
     });
     return lst;
    }
    public Lead getLeadById(int id)
    {
    List<Lead> lst=template.query("select l.lead_id as lead_id,candidate_name,email,mobile,address,location_name,l.location_id as location_id,tc.city_id as city_id,city_name,specialization,l.specialization_id as specialization_id,tq.qualification_id as qualification_id,qualification,source_name,l.source_id as source_id,l.flag from leads l join tblspecialization sp on l.specialization_id=sp.specialization_id join tblleadsources ld on l.source_id=ld.source_id join tblqualification tq on tq.qualification_id=sp.qualification_id join tbllocation lc on l.location_id=lc.location_id join tblcity tc on tc.city_id=lc.city_id where lead_id="+id, new RowMapper<Lead>() {
        @Override
       public Lead mapRow(ResultSet rs, int i) throws SQLException {
      Lead s=new Lead(rs.getInt("lead_id"),rs.getString("candidate_name"),rs.getString("email"),rs.getInt("mobile"),rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("location_id"),rs.getString("location_name"),rs.getString("address"),rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("specialization_id"),rs.getString("specialization"),rs.getInt("source_id"),rs.getString("source_name"));
       return s;
        }
    });
    Lead st=lst.get(0);
    return st;
    }
        public int getNextLeadId()
    {
     List<Integer>lst=template.query("select (max(lead_id)+1) lead_id from leads",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("lead_id");
                if(id==0)
                {
                    return 1;
                }
                
                  return rs.getInt("lead_id");
            }
        });
        return lst.get(0);
     }
}
