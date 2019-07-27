
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.FeeMode;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class FeeModeDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addFeeMode(FeeMode s)
    {
      template.execute("insert into tblFeesModes values("+s.getFee_mode_id()+",'"+s.getMode_name()+"',0)");
    }
    public void updateFeeMode(FeeMode s)
    {
     template.execute("update tblFeesModes set mode_name='"+s.getMode_name()+"'where fee_mode_id="+s.getFee_mode_id());
    }
    public void deleteFeeMode(int id)
    {
     template.execute("update tblFeesModes set flag=1 where fee_mode_id="+id);
    }
    public List<FeeMode> getAllFeeMode()
    {
     List<FeeMode> lst=template.query("select * from tblFeesModes where flag=0",new RowMapper<FeeMode>() {
         @Override
         public FeeMode mapRow(ResultSet rs, int i) throws SQLException {
             FeeMode s=new FeeMode(rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getInt("flag"));
             return s;
         }
     });
     return lst;
    }
    public FeeMode getFeeModeById(int id)
    {
    List<FeeMode> lst=template.query("select * from tblFeesModes where fee_mode_id="+id, new RowMapper<FeeMode>() {
        @Override
        public FeeMode mapRow(ResultSet rs, int i) throws SQLException {
        FeeMode s=new FeeMode(rs.getInt("fee_mode_id"),rs.getString("mode_name"),rs.getInt("flag"));
        return s;
        }
    });
    FeeMode st=lst.get(0);
    return st;
    }
    public int getNextFeeModeId()
    {
     List<Integer>lst=template.query("select (max(fee_mode_id)+1) fee_mode_id from tblFeesModes",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("fee_mode_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("fee_mode_id");
            }
        });
        return lst.get(0);
     }
}
