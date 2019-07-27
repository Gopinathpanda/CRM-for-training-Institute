
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Location;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class LocationDao {
    JdbcTemplate template; 

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addLocation(Location s)
    {
      template.execute("insert into tbllocation values("+s.getLocation_id()+",'"+s.getLocation_name()+"',"+s.getCity_id()+",0)");
    }
    public void updateLocation(Location s)
    {
     template.execute("update tbllocation set location_name='"+s.getLocation_name()+"' where location_id="+s.getLocation_id());
    }
    public void deleteLocation(int id)
    {
     template.execute("update tbllocation set flag=1 where location_id="+id);
    }
    public List<Location> getAllLocation()
    {
     List<Location> lst=template.query("select l.location_id as location_id,location_name,city_name,l.city_id as city_id,l.flag from tbllocation l join tblcity s on l.city_id=s.city_id where l.flag=0 order by location_id",new RowMapper<Location>() {
         @Override
         public Location mapRow(ResultSet rs, int i) throws SQLException {
             Location s=new Location(rs.getInt("location_id"),rs.getString("location_name"),rs.getInt("city_id"),rs.getString("city_name"));
             return s;
         }
     });
     return lst;
    }
    public Location getLocationById(int id)
    {
    List<Location> lst=template.query("select l.location_id as location_id,location_name,city_name,l.city_id as city_id,l.flag from tbllocation l join tblcity s on l.city_id=s.city_id where location_id="+id, new RowMapper<Location>() {
        @Override
       public Location mapRow(ResultSet rs, int i) throws SQLException {
             Location s=new Location(rs.getInt("location_id"),rs.getString("location_name"),rs.getInt("city_id"),rs.getString("city_name"));
             return s;
        }
    });
    Location st=lst.get(0);
    return st;
    }
     public int getNextLocationId()
    {
     List<Integer>lst=template.query("select (max(location_id)+1) location_id from tbllocation",new RowMapper<Integer>() {
          @Override
            public Integer mapRow(ResultSet rs, int i) throws SQLException {
                int id=rs.getInt("location_id");
                 if(id==0)
                 {
                     return 1;
                 }
                  return rs.getInt("location_id");
            }
        });
        return lst.get(0);
     }
      public List<Location> getLocationByCity(int id)
    {
    List<Location> lst=template.query("select * from tbllocation where city_id="+id, new RowMapper<Location>() {
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
             Location s=new Location(rs.getInt("location_id"),rs.getString("location_name"),rs.getInt("city_id"),"");
             return s;
        }
    });
     
    return lst;
    }
}
