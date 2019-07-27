
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.State;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class StateDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addState(State s)throws Exception
    {
    template.execute("insert into tblstate values("+s.getState_id()+",'"+s.getState_name()+"',0)");
    }
    public void updateState(State s)
    {
     template.execute("update tblstate set state_name='"+s.getState_name()+"'where state_id="+s.getState_id());
    }
    public void deleteState(int id)
    {
     template.execute("update tblstate set flag=1 where state_id="+id);
    }
    public List<State> getAll()
    {
     List<State> lst=template.query("select * from tblstate where flag=0 order by state_id",new RowMapper<State>() {
         @Override
         public State mapRow(ResultSet rs, int i) throws SQLException {
             State s=new State(rs.getInt("state_id"),rs.getString("state_name"));
             return s;
         }
     });
     return lst;
    }
    public State getStateById(int id)
    {
    List<State> lst=template.query("select * from tblstate where state_id="+id, new RowMapper<State>() {
        @Override
        public State mapRow(ResultSet rs, int i) throws SQLException {
        State s=new State(rs.getInt("state_id"),rs.getString("state_name"));
        return s;
        }
    });
    State st=lst.get(0);
    return st;
    }
    public int getNextStateId()
    {
     List<Integer>lst=template.query("select (max(state_id)+1) state_id from tblstate",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("state_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("state_id");
            }
        });
        return lst.get(0);
     }
   
}
