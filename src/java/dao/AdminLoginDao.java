package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Login;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class AdminLoginDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public Login CheckLogin(String uid,String pass,String role)
    {
   List<Login> lst=template.query("select * from tbllogin where login='"+uid+"' and password='"+pass+"' and role='"+role+"'",new RowMapper<Login>() {
         @Override
         public Login mapRow(ResultSet rs, int i) throws SQLException {
             Login s=new Login(rs.getString("login"),rs.getString("password"),rs.getString("role"));
             return s;
        }
    });
    Login st=lst.get(0);
    return st;
}
   public void updateLogin(Login s)
    {
     template.execute("update tbllogin set password='"+s.getPassword()+"' where login='"+s.getLogin()+"'");
    } 
    public Login OnlyLogin(String uid)
    {
   List<Login> lst=template.query("select * from tbllogin where login='"+uid+"'",new RowMapper<Login>() {
         @Override
         public Login mapRow(ResultSet rs, int i) throws SQLException {
             Login s=new Login(rs.getString("login"));
             return s;
        }
    });
    Login st=lst.get(0);
    return st;
}
   
}