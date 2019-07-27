package controller;
import model.AllEnquiry;import model.EnquiryQualification;import model.EnquiryResult;
import dao.AllEnquiryDao;import dao.EnquiryQualificationDao;import dao.EnquiryResultDao;
import java.util.*;
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
public class EnquiryController {
    @Autowired
    AllEnquiryDao aed; 
     @Autowired
    EnquiryQualificationDao eqd; 
     @Autowired
    EnquiryResultDao erd; 
     
     @RequestMapping("newenquiry")  
  public ModelAndView newEnquiry(){
      return new ModelAndView("Enquiry/Enquiry");
  }
   @RequestMapping(value="displayenquiry",method=RequestMethod.GET)
  @ResponseBody()
  public List<AllEnquiry> getAllState()
  {
      return aed.getAllEnquiry();
  }
    @RequestMapping(value="getenquiryid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxEnquiry()
  {
      return aed.getNextAllEnquiryId();
  }

  @RequestMapping(value="addenquiry",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addAllEnquiry(@RequestBody AllEnquiry s)
  {
   aed.addAllEnquiry(s);
   return "success";
  }
  @RequestMapping(value="updateenquiry",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateEnquiry(@RequestBody AllEnquiry s)
  {
   aed.updateAllEnquiry(s);
   return "success";
  }
  @RequestMapping(value="displayenquiry/{enquiry_id}",method=RequestMethod.GET)
  @ResponseBody
  public AllEnquiry getEnquiryByID(@PathVariable("enquiry_id") int enquiry_id)
  {
   return aed.getAllEnquiryById(enquiry_id);
  }  
  @RequestMapping(value="deleteenquiry/{enquiry_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteState(@PathVariable("enquiry_id") int enquiry_id)
  {
   aed.deleteAllEnquiry(enquiry_id);
   return "success";
  }
 
   @RequestMapping("enquiryresult")  
  public ModelAndView EnquiryQualification(){
      return new ModelAndView("Enquiry/EnquiryResult");
  }
  @RequestMapping(value="displayenquiryresult",method=RequestMethod.GET)
  @ResponseBody()
  public List<EnquiryResult> getAllEnqResult()
  {
      return erd.getAllEnquiryResult();
  }
    @RequestMapping(value="getenquiryresultid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxEnquiryResult()
  {
      return erd.getNextEnquiryResultId();
  }

  @RequestMapping(value="addenquiryresult",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addAllEnquiryEnquiryResult(@RequestBody EnquiryResult s)
  {
   erd.addEnquiryResult(s);
   return "success";
  }
  @RequestMapping(value="updateenquiryresult",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateEnquiryResult(@RequestBody EnquiryResult s)
  {
   erd.updateEnquiryResult(s);
   return "success";
  }
  @RequestMapping(value="displayenquiryresult/{enquiry_result_id}",method=RequestMethod.GET)
  @ResponseBody
  public EnquiryResult getEnquiryResultByID(@PathVariable("enquiry_result_id") int enquiry_result_id)
  {
   return erd.getEnquiryResultById(enquiry_result_id);
  }  
  @RequestMapping(value="deleteenquiryresult/{enquiry_result_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteEnquiryResult(@PathVariable("enquiry_result_id") int enquiry_result_id)
  {
   erd.deleteEnquiryResult(enquiry_result_id);
   return "success";
  }


      @RequestMapping("allenquiry")  
  public ModelAndView allAdmission(){
      return new ModelAndView("Enquiry/EnquiryQualification");
  }
  @RequestMapping(value="displayenqqualification",method=RequestMethod.GET)
  @ResponseBody()
  public List<EnquiryQualification> getAllenquiryqualification()
  {
      return eqd.getAllEnquiryQualification();
  }
    @RequestMapping(value="getenquiryqualificationid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxEnquiryQualification()
  {
      return eqd.getNextEnquiryQualificationId();
  }

  @RequestMapping(value="addenquiryqualification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addAllEnquiryQualification(@RequestBody EnquiryQualification s)
  {
   eqd.addEnquiryQualification(s);
   return "success";
  }
  @RequestMapping(value="updateenquiryqualification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateEnquiryQualification(@RequestBody EnquiryQualification s)
  {
   eqd.updateEnquiryQualification(s);
   return "success";
  }
  @RequestMapping(value="displayenquiryqualification/{enquiry_qualification_id}",method=RequestMethod.GET)
  @ResponseBody
  public EnquiryQualification getEnquiryQualificationByID(@PathVariable("enquiry_qualification_id") int enquiry_qualification_id)
  {
   return eqd.getEnquiryQualificationById(enquiry_qualification_id);
  }  
  @RequestMapping(value="deleteenquiryqualification/{enquiry_qualification_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteEnquiryQualification(@PathVariable("enquiry_qualification_id") int enquiry_qualification_id)
  {
   eqd.deleteEnquiryQualification(enquiry_qualification_id);
   return "success";
  }
 }
