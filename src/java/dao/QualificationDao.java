package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Qualification;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class QualificationDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addQualification(Qualification s)
    {
      template.execute("insert into tblqualification values("+s.getQualification_id()+",'"+s.getQualification()+"',0)");
    }
    public void updateQualification(Qualification s)
    {
     template.execute("update tblqualification set qualification='"+s.getQualification()+"' where qualification_id="+s.getQualification_id());
    }
    public void deleteQualification(int id)
    {
     template.execute("update tblqualification set flag=1 where qualification_id="+id);
    }
    public List<Qualification> getAllQualification()
    {
     List<Qualification> lst=template.query("select * from tblqualification where flag=0",new RowMapper<Qualification>() {
         @Override
         public Qualification mapRow(ResultSet rs, int i) throws SQLException {
             Qualification s=new Qualification(rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("flag"));
             return s;
         }
     });
     return lst;
    }
    public Qualification getQualificationById(int id)
    {
    List<Qualification> lst=template.query("select * from tblqualification where qualification_id="+id, new RowMapper<Qualification>() {
        @Override
        public Qualification mapRow(ResultSet rs, int i) throws SQLException {
        Qualification s=new Qualification(rs.getInt("qualification_id"),rs.getString("qualification"),rs.getInt("flag"));
        return s;
        }
    });
    Qualification st=lst.get(0);
    return st;
    }
    public int getNextQualificationId()
    {
     List<Integer>lst=template.query("select (max(qualification_id)+1) qualification_id from tblqualification",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                  int id=rs.getInt("qualification_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("qualification_id");
            }
        });
        return lst.get(0);
     }
}
