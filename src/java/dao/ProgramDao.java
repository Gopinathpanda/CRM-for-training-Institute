
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Programs;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class ProgramDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addPrograms(Programs s)
    {
      template.execute("insert into tbltraining_programs values("+s.getProgram_id()+",'"+s.getCourse_id()+"','"+s.getCourse_name()+"','"+s.getDuration_course()+"',"+s.getTechnology_id()+",0)");
    }
    public void updatePrograms(Programs s)
    {
     template.execute("update tbltraining_programs set course_id='"+s.getCourse_id()+"',course_name='"+s.getCourse_name()+"',duration_course='"+s.getDuration_course()+"' where program_id="+s.getProgram_id());
    }
    public void deletePrograms(int id)
    {
     template.execute("update tbltraining_programs set flag=1 where program_id="+id);
    }
    public List<Programs> getAllPrograms()
    {
     List<Programs> lst=template.query("select s.program_id as program_id,course_id,course_name,duration_course,s.technology_id as technology_id,technology_name,s.flag from tbltraining_programs s join tbltechnologies q on s.technology_id=q.technology_id where s.flag=0",new RowMapper<Programs>() {
         @Override
         public Programs mapRow(ResultSet rs, int i) throws SQLException {
             Programs s=new Programs(rs.getInt("program_id"),rs.getString("course_id"),rs.getString("course_name"),rs.getString("duration_course"),rs.getInt("technology_id"),rs.getString("technology_name"));
             return s;
         }
     });
     return lst;
    }
    public Programs getProgramsById(int id)
    {
    List<Programs> lst=template.query("select s.program_id as program_id,course_id,course_name,duration_course,technology_name,s.technology_id as technology_id,s.flag from tbltraining_programs s join tbltechnologies q on s.technology_id=q.technology_id  where program_id="+id, new RowMapper<Programs>() {
        @Override
        public Programs mapRow(ResultSet rs, int i) throws SQLException {
             Programs s=new Programs(rs.getInt("program_id"),rs.getString("course_id"),rs.getString("course_name"),rs.getString("duration_course"),rs.getInt("technology_id"),rs.getString("technology_name"));
             return s;
        }
    });
    Programs st=lst.get(0);
    return st;
    }
     public int getNextProgramsId()
    {
     List<Integer>lst=template.query("select (max(program_id)+1) program_id from tbltraining_programs",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("program_id");
                if(id==0)
                {
                    return 1;
                }
                
                  return rs.getInt("program_id");
            }
        });
        return lst.get(0);
     }
}
