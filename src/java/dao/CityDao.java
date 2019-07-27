
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.City;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class CityDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addCity(City s)throws Exception
    {
      template.execute("insert into tblcity values("+s.getCity_id()+",'"+s.getCity_name()+"',"+s.getState_id()+",0)");
    }
    public void updateCity(City s)
    {
     template.execute("update tblcity set city_name='"+s.getCity_name()+"' where city_id="+s.getCity_id());
    }
    public void deleteCity(int id)
    {
     template.execute("update tblcity set flag=1 where city_id="+id);
    }
    public List<City> getAllCities()
    {
     List<City> lst=template.query("select c.city_id as city_id,city_name,state_name,c.state_id as state_id,c.flag from tblcity c join tblstate s on c.state_id=s.state_id where c.flag=0 order by city_id",new RowMapper<City>() {
         @Override
         public City mapRow(ResultSet rs, int i) throws SQLException {
             City s=new City(rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("state_id"),rs.getString("state_name"));
             return s;
         }
     });
     return lst;
    }
    public City getCityById(int id)
    {
    List<City> lst=template.query("select c.city_id as city_id,city_name,state_name,c.state_id as state_id,c.flag from tblcity c join tblstate s on c.state_id=s.state_id where city_id="+id, new RowMapper<City>() {
        @Override
        public City mapRow(ResultSet rs, int i) throws SQLException {
             City s=new City(rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("state_id"),rs.getString("state_name"));
             return s;
        }
    });
    City st=lst.get(0);
    return st;
    }
     public int getNextCityId()
    {
     List<Integer>lst=template.query("select (max(city_id)+1) city_id from tblcity",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("city_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("city_id");
            }
        });
        return lst.get(0);
     }
}
