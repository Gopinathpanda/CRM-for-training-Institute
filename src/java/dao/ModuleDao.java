
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Module;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class ModuleDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addModule(Module s)
    {
     template.execute("insert into tbltrainingmodules values("+s.getModule_id()+",'"+s.getModule_name()+"',"+s.getDuration_module()+","+s.getProgram_id()+",0)");
    }
    public void updateModule(Module s)
    {
     template.execute("update tbltrainingmodules set module_name='"+s.getModule_name()+"',duration_module="+s.getDuration_module()+" where module_id="+s.getModule_id());
    }
    public void deleteModule(int id)
    {
     template.execute("update tbltrainingmodules set flag=1 where module_id="+id);
    }
    public List<Module> getAllModule()
    {
     List<Module> lst=template.query("select l.module_id as module_id,module_name,duration_module,course_name,l.program_id as program_id,l.flag from tbltrainingmodules l join tbltraining_programs s on l.program_id=s.program_id where l.flag=0",new RowMapper<Module>() {
         @Override
         public Module mapRow(ResultSet rs, int i) throws SQLException {
             Module s=new Module(rs.getInt("module_id"),rs.getString("module_name"),rs.getInt("duration_module"),rs.getInt("program_id"),rs.getString("course_name"));
             return s;
         }
     });
     return lst;
    }
    public Module getModuleById(int id)
    {
    List<Module> lst=template.query("select l.module_id as module_id,module_name,duration_module,course_name,l.program_id as program_id,l.flag from tbltrainingmodules l join tbltraining_programs s on l.program_id=s.program_id where module_id="+id, new RowMapper<Module>() {
        @Override
       public Module mapRow(ResultSet rs, int i) throws SQLException {
             Module s=new Module(rs.getInt("module_id"),rs.getString("module_name"),rs.getInt("duration_module"),rs.getInt("program_id"),rs.getString("course_name"));
             return s;
        }
    });
    Module st=lst.get(0);
    return st;
    }
     public int getNextModuleId()
    {
     List<Integer>lst=template.query("select (max(module_id)+1) module_id from tbltrainingmodules",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("module_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("module_id");
            }
        });
        return lst.get(0);
     }
     
}
