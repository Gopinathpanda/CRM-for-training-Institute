
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Specialization;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class SpecializationDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addSpecialization(Specialization s)
    {
      template.execute("insert into tblspecialization values("+s.getSpecialization_id()+",'"+s.getSpecialization()+"',"+s.getQualification_id()+",0)");
    }
    public void updateSpecialization(Specialization s)
    {
     template.execute("update tblspecialization set specialization='"+s.getSpecialization()+"' where specialization_id="+s.getSpecialization_id());
    }
    public void deleteSpecialization(int id)
    {
     template.execute("update tblspecialization set flag=1 where specialization_id="+id);
    }
    public List<Specialization> getAllSpecialization()
    {
     List<Specialization> lst=template.query("select s.specialization_id as specialization_id,specialization,qualification,s.qualification_id as qualification_id,s.flag from tblspecialization s join tblqualification q on s.qualification_id=q.qualification_id where s.flag=0",new RowMapper<Specialization>() {
         @Override
         public Specialization mapRow(ResultSet rs, int i) throws SQLException {
             Specialization s=new Specialization(rs.getInt("specialization_id"),rs.getString("specialization"),rs.getInt("qualification_id"),rs.getString("qualification"));
             return s;
         }
     });
     return lst;
    }
    public Specialization getSpecializationById(int id)
    {
    List<Specialization> lst=template.query("select s.specialization_id as specialization_id,specialization,qualification,s.qualification_id as qualification_id,s.flag from tblspecialization s join tblqualification q on s.qualification_id=q.qualification_id where specialization_id="+id, new RowMapper<Specialization>() {
        @Override
        public Specialization mapRow(ResultSet rs, int i) throws SQLException {
             Specialization s=new Specialization(rs.getInt("specialization_id"),rs.getString("specialization"),rs.getInt("qualification_id"),rs.getString("qualification"));
             return s;
        }
    });
    Specialization st=lst.get(0);
    return st;
    }
    
    
     public int getNextSpecializationId()
    {
     List<Integer>lst=template.query("select (max(specialization_id)+1) specialization_id from tblspecialization",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("specialization_id");
                if(id==0)
                {
                    return 1;
                }
                
                  return rs.getInt("specialization_id");
            }
        });
        return lst.get(0);
     }
        public List<Specialization> getSpecializationByQId(int id)
    {
    List<Specialization> lst=template.query("select * from tblspecialization where qualification_id="+id, new RowMapper<Specialization>() {
        @Override
        public Specialization mapRow(ResultSet rs, int i) throws SQLException {
             Specialization s=new Specialization(rs.getInt("specialization_id"),rs.getString("specialization"),rs.getInt("qualification_id"),"");
             return s;
        }
    });
     
    return lst;
    }
    
}
