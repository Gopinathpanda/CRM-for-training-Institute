
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TrainingFees;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class TrainingFeesDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addTrainingFees(TrainingFees s)
    {
      template.execute("insert into tbltrainingfees values("+s.getFee_id()+","+s.getProgram_id()+","+s.getLumsum()+","+s.getFee_mode_id()+","+s.getRegistration_amount()+",0)");
    }
    public void updateTrainingFees(TrainingFees s)
    {
     template.execute("update tbltrainingfees set program_id="+s.getProgram_id()+",lumsum="+s.getLumsum()+",fee_mode_id="+s.getFee_mode_id()+",registration_amount="+s.getRegistration_amount()+" where fee_id="+s.getFee_id());
    }
    public void deleteTrainingFees(int id)
    {
     template.execute("update tbltrainingfees set flag=1 where fee_id="+id);
    }
    public List<TrainingFees> getAllTrainingFees()
    {
     List<TrainingFees> lst=template.query("select c.fee_id as fee_id,lumsum,registration_amount,course_name,mode_name,c.program_id as program_id,c.fee_mode_id as fee_mode_id,c.flag from tbltrainingfees c join tbltraining_programs s on c.program_id=s.program_id join tblFeesModes f on c.fee_mode_id=f.fee_mode_id where c.flag=0",new RowMapper<TrainingFees>() {
         @Override
         public TrainingFees mapRow(ResultSet rs, int i) throws SQLException {
             TrainingFees s=new TrainingFees(rs.getInt("fee_id"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("lumsum"),rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getInt("registration_amount"));
             return s;
         }
     });
     return lst;
    }
    public TrainingFees getTrainingFeesById(int id)
    {
    List<TrainingFees> lst=template.query("select c.fee_id as fee_id,lumsum,registration_amount,course_name,mode_name,c.program_id as program_id,c.fee_mode_id as fee_mode_id,c.flag from tbltrainingfees c join tbltraining_programs s on c.program_id=s.program_id join tblFeesModes f on c.fee_mode_id=f.fee_mode_id where fee_id="+id, new RowMapper<TrainingFees>() {
        @Override
        public TrainingFees mapRow(ResultSet rs, int i) throws SQLException {
             TrainingFees s=new TrainingFees(rs.getInt("fee_id"),rs.getInt("program_id"),rs.getString("course_name"),rs.getInt("lumsum"),rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getInt("registration_amount"));
             return s;
        }
    });
    TrainingFees st=lst.get(0);
    return st;
    }
     public int getNextTrainingFeesId()
    {
     List<Integer>lst=template.query("select (max(fee_id)+1) fee_id from tbltrainingfees",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("fee_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("fee_id");
            }
        });
        return lst.get(0);
     }
}
