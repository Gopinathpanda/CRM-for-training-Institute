 

package controller;
import model.Lead;
import dao.LeadDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 @Controller
public class LeadController {
     @Autowired
     LeadDao ldtt;
  @RequestMapping("allleads")  
  public ModelAndView allAdmission(){
   return new ModelAndView("Leads/AllLeads");
  }
  @RequestMapping(value="displaylead",method=RequestMethod.GET)
  @ResponseBody()
  public List<Lead> getAllLead()
  {

      return ldtt.getAllLead();
  }
    @RequestMapping(value="getleadid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxLead()
  {
      return ldtt.getNextLeadId();
  }

  @RequestMapping(value="addlead",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addLead(@RequestBody Lead s)
  {
   ldtt.addLead(s);
   return "success";
  }
  @RequestMapping(value="updatelead",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateLead(@RequestBody Lead s)
  {
   ldtt.updateLeads(s);
   return "success";
  }
  @RequestMapping(value="displaylead/{lead_id}",method=RequestMethod.GET)
  @ResponseBody
  public Lead getLeadByID(@PathVariable("lead_id") int lead_id)
  {
   return ldtt.getLeadById(lead_id);
  }  
  @RequestMapping(value="deletelead/{lead_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteLead(@PathVariable("lead_id") int lead_id)
  {
   ldtt.deleteLead(lead_id);
   return "success";
  }
  
}
