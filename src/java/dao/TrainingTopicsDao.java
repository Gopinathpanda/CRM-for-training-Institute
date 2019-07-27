
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TrainingTopics;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class TrainingTopicsDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addTrainingTopics(TrainingTopics s)
    {
      template.execute("insert into tbltrainingtopics values("+s.getTopic_id()+",'"+s.getTopic_name()+"',"+s.getModule_id()+",0)");
    }
    public void updateTopics(TrainingTopics s)
    {
     template.execute("update tbltrainingtopics set topic_name='"+s.getTopic_name()+"' where topic_id="+s.getTopic_id());
    }
    public void deleteTopics(int id)
    {
     template.execute("update tbltrainingtopics set flag=1 where topic_id="+id);
    }
    public List<TrainingTopics> getAllTrainingTopics()
    {
     List<TrainingTopics> lst=template.query("select c.topic_id as topic_id,topic_name,module_name,c.module_id as module_id,c.flag from tbltrainingtopics c join tbltrainingmodules s on c.module_id=s.module_id where c.flag=0",new RowMapper<TrainingTopics>() {
         @Override
         public TrainingTopics mapRow(ResultSet rs, int i) throws SQLException {
             TrainingTopics s=new TrainingTopics(rs.getInt("topic_id"),rs.getString("topic_name"),rs.getInt("module_id"),rs.getString("module_name"));
             return s;
         }
     });
     return lst;
    }
    public TrainingTopics getTrainingTopicsById(int id)
    {
    List<TrainingTopics> lst=template.query("select c.topic_id as topic_id,topic_name,module_name,c.module_id as module_id,c.flag from tbltrainingtopics c join tbltrainingmodules s on c.module_id=s.module_id where topic_id="+id, new RowMapper<TrainingTopics>() {
        @Override
        public TrainingTopics mapRow(ResultSet rs, int i) throws SQLException {
             TrainingTopics s=new TrainingTopics(rs.getInt("topic_id"),rs.getString("topic_name"),rs.getInt("module_id"),rs.getString("module_name"));
             return s;
        }
    });
    TrainingTopics st=lst.get(0);
    return st;
    }
     public int getNextTopicId()
    {
     List<Integer>lst=template.query("select (max(topic_id)+1) topic_id from tbltrainingtopics",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("topic_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("topic_id");
            }
        });
        return lst.get(0);
     }
}
