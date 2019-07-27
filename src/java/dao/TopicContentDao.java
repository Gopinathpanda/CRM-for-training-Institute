
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TopicContent;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class TopicContentDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addTopicContent(TopicContent s)
    {
      template.execute("insert into tbltopiccontents values("+s.getContent_id()+",'"+s.getContent_name()+"',"+s.getTopic_id()+",0)");
    }
    public void updateTopicContent(TopicContent s)
    {
     template.execute("update tbltopiccontents set content_name='"+s.getContent_name()+"' where content_id="+s.getContent_id());
    }
    public void deleteTopicContent(int id)
    {
     template.execute("update tbltopiccontents set flag=1 where content_id="+id);
    }
    public List<TopicContent> getAllTopicContent()
    {
     List<TopicContent> lst=template.query("select c.content_id as content_id,content_name,topic_name,c.topic_id as topic_id,c.flag from tbltopiccontents c join tbltrainingtopics s on c.topic_id=s.topic_id where c.flag=0 order by content_id",new RowMapper<TopicContent>() {
         @Override
         public TopicContent mapRow(ResultSet rs, int i) throws SQLException {
             TopicContent s=new TopicContent(rs.getInt("content_id"),rs.getString("content_name"),rs.getInt("topic_id"),rs.getString("topic_name"));
             return s;
         }
     });
     return lst;
    }
    public TopicContent getTopicContentById(int id)
    {
    List<TopicContent> lst=template.query("select c.content_id as content_id,content_name,topic_name,c.topic_id as topic_id,c.flag from tbltopiccontents c join tbltrainingtopics s on c.topic_id=s.topic_id where content_id="+id, new RowMapper<TopicContent>() {
        @Override
        public TopicContent mapRow(ResultSet rs, int i) throws SQLException {
             TopicContent s=new TopicContent(rs.getInt("content_id"),rs.getString("content_name"),rs.getInt("topic_id"),rs.getString("topic_name"));
             return s;
        }
    });
    TopicContent st=lst.get(0);
    return st;
    }
     public int getNextTopicContentId()
    {
     List<Integer>lst=template.query("select (max(content_id)+1) content_id from tbltopiccontents",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("content_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("content_id");
            }
        });
        return lst.get(0);
     }
}
