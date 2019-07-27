package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Technology;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class TechnologyDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addTechnology(Technology t)
    {
      template.execute("insert into tbltechnologies values("+t.getTechnology_id()+",'"+t.getTechnology_name()+"',0)");
    }
    public void updateTechnology(Technology t)
    {
     template.execute("update tbltechnologies set technology_name='"+t.getTechnology_name()+"' where technology_id="+t.getTechnology_id());
    }
    public void deleteTechnology(int id)
    {
     template.execute("update tbltechnologies set flag=1 where technology_id="+id);
    }
    public List<Technology> getAllTechnology()
    {
     List<Technology> lst=template.query("select * from tbltechnologies where flag=0",new RowMapper<Technology>() {
         @Override
         public Technology mapRow(ResultSet rs, int i) throws SQLException {
             Technology s=new Technology(rs.getInt("technology_id"),rs.getString("technology_name"));
             return s;
         }
     });
     return lst;
    }
    public Technology getTechnologyById(int id)
    {
    List<Technology> lst=template.query("select * from tbltechnologies where technology_id="+id, new RowMapper<Technology>() {
        @Override
        public Technology mapRow(ResultSet rs, int i) throws SQLException {
        Technology s=new Technology(rs.getInt("technology_id"),rs.getString("technology_name"));
        return s;
        }
    });
    Technology st=lst.get(0);
    return st;
    }
    public int getNextTechnologyid()
    {
     List<Integer>lst=template.query("select (max(technology_id)+1) technology_id from tbltechnologies",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                 int id=rs.getInt("technology_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("technology_id");
            }
        });
        return lst.get(0);
     }
}
