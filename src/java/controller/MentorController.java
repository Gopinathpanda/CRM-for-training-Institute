package controller;
import model.MentorDetails;import model.MentorAssign;
import dao.MentorDetailsDao;import dao.MentorAssignDao;
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
public class MentorController {
     @Autowired
     MentorDetailsDao mdd;
     @Autowired
     MentorAssignDao mad;
    @RequestMapping("mentordetails")  
  public ModelAndView mentordetails(){
      return new ModelAndView("Mentor/MentorDetails");
  }
  @RequestMapping("mentor")  
  public ModelAndView trainFees(){
      return new ModelAndView("Mentor/AllMentors");
  }
  @RequestMapping(value="displaymentor",method=RequestMethod.GET)
  @ResponseBody()
  public List<MentorDetails> getAllState()
  {
      return mdd.getAllMentorDetails();
  }
    @RequestMapping(value="getmentorid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxMentor()
 {
     return mdd.getNextMentorDetailsId();
 }
  

 @RequestMapping(value="addmentor",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
 public String addMentor(@RequestBody MentorDetails s)
 {
  mdd.addMentorDetails(s);
  return "success";
  }
  @RequestMapping(value="updatementor",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateState(@RequestBody MentorDetails s)
  {
  mdd.updateMentorDetails(s);
   return "success";
  }
  @RequestMapping(value="displaymentor/{mentor_id}",method=RequestMethod.GET)
  @ResponseBody
  public MentorDetails getMentorByID(@PathVariable("mentor_id") int mentor_id)
  {
   return mdd.getMentorDetailsById(mentor_id);
  } 
 
  @RequestMapping(value="deletementor/{mentor_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteState(@PathVariable("mentor_id") int mentor_id)
  {
   mdd.deleteMentorDetails(mentor_id);
   return "success";
  }
  @RequestMapping(value="displaymentorassign",method=RequestMethod.GET)
  @ResponseBody()
  public List<MentorAssign> getAllmentorAssign()
  {
      return mad.getAllMentorAssign();
  }
    @RequestMapping(value="getmentorassignid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxMentorAssign()
 {
     return mad.getNextMentorAssignId();
 }
  

 @RequestMapping(value="addmentorassign",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
 public String addMentorAssign(@RequestBody MentorAssign s)
 {
  mad.addMentorAssign(s);
  return "success";
  }
  @RequestMapping(value="updatementorassign",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateMentorAssign(@RequestBody MentorAssign s)
  {
  mad.updateMentorAssign(s);
   return "success";
  }
  @RequestMapping(value="displaymentorassign/{assigned_mentor_id}",method=RequestMethod.GET)
  @ResponseBody
  public MentorAssign getMentorAssignByID(@PathVariable("assigned_mentor_id") int assigned_mentor_id)
  {
   return mad.getMentorAssignById(assigned_mentor_id);
  }  
  @RequestMapping(value="deletementorassign/{assigned_mentor_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteMentorassign(@PathVariable("assigned_mentor_id") int assigned_mentor_id)
  {
   mad.deleteMentorAssign(assigned_mentor_id);
   return "success";
  }
  
 }
