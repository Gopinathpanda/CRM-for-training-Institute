package controller;
import model.Location;import model.Qualification;import model.LeadSource;import model.Specialization;import model.Technology;
import model.Programs;import model.Module;import model.TrainingTopics;import model.TopicContent;
import dao.CityDao;import dao.LocationDao;import dao.QualificationDao;import dao.LeadSourceDao;import dao.SpecializationDao;
import dao.TechnologyDao;import dao.ProgramDao;import dao.ModuleDao;import dao.TrainingTopicsDao;
import dao.TopicContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import model.State;
import dao.StateDao;
import java.util.*;
import model.City;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
 @Controller
public class MasterController {
     @Autowired
     StateDao st;
     @Autowired
     CityDao ct;
      @Autowired
      LocationDao lt;
      @Autowired
      LeadSourceDao ldt;
      @Autowired
      QualificationDao qt;
       @Autowired
      SpecializationDao spt;
      @Autowired
      TechnologyDao tt;
      @Autowired
      ProgramDao pt;
      @Autowired
      ModuleDao mt;
       @Autowired
     TrainingTopicsDao ttd;
       @Autowired
     TopicContentDao tcd;
     @RequestMapping("state")  
  public ModelAndView newState(){
     return new ModelAndView("Master/State");
  }
  @RequestMapping(value="display",method=RequestMethod.GET)
  @ResponseBody()
  public List<State> getAllState()
  {
      return st.getAll();
  }
    @RequestMapping(value="getstateid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxState()
  {
      return st.getNextStateId();
  }

  @RequestMapping(value="add",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addState(@RequestBody State s)
  {
      
      String res=null;
      List<State> stateDataList=getAllState();
      List<String> stateList=new ArrayList<>();
      for(State state:stateDataList){
          stateList.add(state.getState_name());
      }try{
       if(stateList.contains(s)){
         res="State Is already Present" ;
         }     
      else{
   st.addState(s);
   res="Sucess";
      } }
catch(Exception e){
    System.out.println(e.getMessage());} 
   return res;
  }
  @RequestMapping(value="update",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateState(@RequestBody State s)
  {
   st.updateState(s);
   return "success";
  }
  @RequestMapping(value="display/{state_id}",method=RequestMethod.GET)
  @ResponseBody
  public State getStateByID(@PathVariable("state_id") int state_id)
  {
   return st.getStateById(state_id);
  }  
  @RequestMapping(value="delete/{state_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteState(@PathVariable("state_id") int state_id)
  {
   st.deleteState(state_id);
   return "success";
  }
  
      @RequestMapping("city")  
  public ModelAndView newCity(){
      return new ModelAndView("Master/City");
  }
  @RequestMapping(value="getcityid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxCity()
  {
      return ct.getNextCityId();
  }
  @RequestMapping(value="displaycity",method=RequestMethod.GET)
  @ResponseBody()
  public List<City> getAllCity()
  {
      return ct.getAllCities();
  }
  @RequestMapping(value="addcity",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addCity(@RequestBody City c)
  {
   String res=null;
      List<City> citydata=getAllCity();
      List<String> cityList=new ArrayList<>();
      for(City city:citydata){
          cityList.add(city.getCity_name());
      }try{
       if(cityList.contains(c)){
         res="City Is already Present" ;
         }     
      else{
   ct.addCity(c);
   res="Sucess";
      } }
catch(Exception e){
    System.out.println(e.getMessage());} 
   return res;
  }
  @RequestMapping(value="updatecity",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateCity(@RequestBody City c)
  {
   ct.updateCity(c);
   return "success";
  }
  @RequestMapping(value="displaycity/{city_id}",method=RequestMethod.GET)
  @ResponseBody
  public City getCityByID(@PathVariable("city_id") int city_id)
  {
   return ct.getCityById(city_id);
  }  
  @RequestMapping(value="deletecity/{city_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteCity(@PathVariable("city_id") int city_id)
  {
   ct.deleteCity(city_id);
   return "success";
  }
   @RequestMapping("location")  
  public ModelAndView newLocation(){
      return new ModelAndView("Master/Location");
  }
  @RequestMapping(value="getlocationid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxLocation()
  {
      return lt.getNextLocationId();
  }
  @RequestMapping(value="displaylocation",method=RequestMethod.GET)
  @ResponseBody()
  public List<Location> getAllLocation()
  {
      return lt.getAllLocation();
  }
  @RequestMapping(value="addlocation",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addLocation(@RequestBody Location l)
  {
   lt.addLocation(l);
   return "success";
  }
  @RequestMapping(value="updatelocation",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateLocation(@RequestBody Location l)
  {
      
  lt.updateLocation(l);
   return "success";
  }
  @RequestMapping(value="displaylocation/{location_id}",method=RequestMethod.GET)
  @ResponseBody
  public Location getLocationByID(@PathVariable("location_id") int location_id)
  {
   return lt.getLocationById(location_id);
  }  
   @RequestMapping(value="displaylocationbycity/{c_id}",method=RequestMethod.GET)
  @ResponseBody
  public List<Location> getLocationByCity(@PathVariable("c_id") int location_id)
  {
      
   return lt.getLocationByCity(location_id);
  }  
  @RequestMapping(value="deletelocation/{location_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteLocation(@PathVariable("location_id") int location_id)
  {
   lt.deleteLocation(location_id);
   return "success";
  }
  @RequestMapping("leadsource")  
  public ModelAndView newLeadSource(){
      return new ModelAndView("Master/LeadSource");
  }
  @RequestMapping(value="displayleadsource",method=RequestMethod.GET)
  @ResponseBody()
  public List<LeadSource> getAllLeadSource()
  {
      return ldt.getAllLeadSource();
  }
    @RequestMapping(value="getleadsourceid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxLeadSource()
  {
      return ldt.getNextLeadSourceId();
  }

  @RequestMapping(value="addleadsource",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addLeadSource(@RequestBody LeadSource s)
  {
   ldt.addLeadSource(s);
   return "success";
  }
  @RequestMapping(value="updateleadsource",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateLeadSource(@RequestBody LeadSource s)
  {
   ldt.updateLeadSource(s);
   return "success";
  }
  @RequestMapping(value="displayleadsource/{source_id}",method=RequestMethod.GET)
  @ResponseBody
  public LeadSource getLeadSourceByID(@PathVariable("source_id") int source_id)
  {
   return ldt.getLeadSourceById(source_id);
  }  
  @RequestMapping(value="deleteleadsource/{source_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteLeadSource(@PathVariable("source_id") int source_id)
  {
   ldt.deleteLeadSource(source_id);
   return "success";
  }
   @RequestMapping("qualification")  
  public ModelAndView newQualification(){
      return new ModelAndView("Master/Qualification");
  }
   @RequestMapping(value="displayqualification",method=RequestMethod.GET)
  @ResponseBody()
  public List<Qualification> getAllQualification()
  {
      return qt.getAllQualification();
  }
    @RequestMapping(value="getqualificationid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxQualification()
  {
      return qt.getNextQualificationId();
  }

  @RequestMapping(value="addqualification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addQualification(@RequestBody Qualification s)
  {
   qt.addQualification(s);
   return "success";
  }
  @RequestMapping(value="updatequalification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateQualification(@RequestBody Qualification s)
  {
   qt.updateQualification(s);
   return "success";
  }
  @RequestMapping(value="displayqualification/{qualification_id}",method=RequestMethod.GET)
  @ResponseBody
  public Qualification getQualificationByID(@PathVariable("qualification_id") int qualification_id)
  {
   return qt.getQualificationById(qualification_id);
  }  
  @RequestMapping(value="deletequalification/{qualification_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteQualification(@PathVariable("qualification_id") int qualification_id)
  {
   qt.deleteQualification(qualification_id);
   return "success";
  }
   @RequestMapping("specialization")  
  public ModelAndView newSpecialization(){
      return new ModelAndView("Master/Specialization");
  }
  @RequestMapping(value="getspecializationid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxSpecialization()
  {
      return spt.getNextSpecializationId();
  }
  @RequestMapping(value="displayspecialization",method=RequestMethod.GET)
  @ResponseBody()
  public List<Specialization> getAllSpecialization()
  {
      return spt.getAllSpecialization();
  }
  
  
    
  @RequestMapping(value="addspecialization",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addSpecialization(@RequestBody Specialization c)
  {
   spt.addSpecialization(c);
   return "success";
  }
  @RequestMapping(value="updatespecialization",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateSpecialization(@RequestBody Specialization c)
  {
     
  spt.updateSpecialization(c);
   return "success";
  }
   
  @RequestMapping(value="displayspecialization/{specialization_id}",method=RequestMethod.GET)
  @ResponseBody
  public Specialization getSpecializationByID(@PathVariable("specialization_id") int specialization_id)
  {
   return spt.getSpecializationById(specialization_id);
  }  
  
  @RequestMapping(value="displayspecializationByQid/{q_id}",method=RequestMethod.GET)
  @ResponseBody
  public List<Specialization> getSpecializationByqID(@PathVariable("q_id") int specialization_id)
  {
      
   return spt.getSpecializationByQId(specialization_id);
  }  
  
  
  @RequestMapping(value="deletespecialization/{specialization_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteSpecialization(@PathVariable("specialization_id") int specialization_id)
  {
   spt.deleteSpecialization(specialization_id);
   return "success";
  }
   @RequestMapping("technologies")  
  public ModelAndView newTechnology(){
      return new ModelAndView("Master/Technologies");
  }
   @RequestMapping(value="displaytechnology",method=RequestMethod.GET)
  @ResponseBody()
  public List<Technology> getAllTechnology()
  {
      return tt.getAllTechnology();
  }
    @RequestMapping(value="gettechnologyid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxTechnology()
  {
      return tt.getNextTechnologyid();
  }

  @RequestMapping(value="addtechnology",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addTechnology(@RequestBody Technology s)
  {
   tt.addTechnology(s);
   return "success";
  }
  @RequestMapping(value="updatetechnology",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateTechnology(@RequestBody Technology s)
  {
   tt.updateTechnology(s);
   return "success";
  }
  @RequestMapping(value="displaytechnology/{technology_id}",method=RequestMethod.GET)
  @ResponseBody
  public Technology getTechnologyID(@PathVariable("technology_id") int technology_id)
  {
   return tt.getTechnologyById(technology_id);
  }  
  @RequestMapping(value="deletetechnology/{technology_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteTechnology(@PathVariable("technology_id") int technology_id)
  {
   tt.deleteTechnology(technology_id);
   return "success";
  }
  @RequestMapping("programs")  
  public ModelAndView newPrograms(){
      return new ModelAndView("Master/Programs");
  }
  @RequestMapping(value="displayprogram",method=RequestMethod.GET)
  @ResponseBody()
  public List<Programs> getAllPrograms()
  {
      return pt.getAllPrograms();
  }
    @RequestMapping(value="getprogramid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxPrograms()
  {
      return pt.getNextProgramsId();
  }

  @RequestMapping(value="addprogram",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addPrograms(@RequestBody Programs s)
  {
   pt.addPrograms(s);
   return "success";
  }
  @RequestMapping(value="updateprogram",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updatePrograms(@RequestBody Programs s)
  {
   pt.updatePrograms(s);
   return "success";
  }
  @RequestMapping(value="displayprogram/{program_id}",method=RequestMethod.GET)
  @ResponseBody
  public Programs getProgramID(@PathVariable("program_id") int program_id)
  {
   return pt.getProgramsById(program_id);
  }  
  @RequestMapping(value="deleteprogram/{program_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteProgram(@PathVariable("program_id") int program_id)
  {
   pt.deletePrograms(program_id);
   return "success";
  }
   @RequestMapping("modules")  
  public ModelAndView newModules(){
      return new ModelAndView("Master/Modules");
  }
  @RequestMapping(value="displaymodule",method=RequestMethod.GET)
  @ResponseBody()
  public List<Module> getAllModule()
  {
      return mt.getAllModule();
  }
    @RequestMapping(value="getmoduleid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxModule()
  {
      return mt.getNextModuleId();
  }

  @RequestMapping(value="addmodule",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addModule(@RequestBody Module s)
  {
   mt.addModule(s);
   return "success";
  }
  @RequestMapping(value="updatemodule",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateModule(@RequestBody Module s)
  {
   mt.updateModule(s);
   return "success";
  }
  @RequestMapping(value="displaymodule/{module_id}",method=RequestMethod.GET)
  @ResponseBody
  public Module getModuleID(@PathVariable("module_id") int module_id)
  {
   return mt.getModuleById(module_id);
  }  
  @RequestMapping(value="deletemodule/{module_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteModule(@PathVariable("module_id") int module_id)
  {
   mt.deleteModule(module_id);
   return "success";
  }
   @RequestMapping("topics")  
  public ModelAndView newTopics(){
      return new ModelAndView("Master/Topics");
  }
  @RequestMapping(value="displaytopics",method=RequestMethod.GET)
  @ResponseBody()
  public List<TrainingTopics> getAllTopics()
  {
      return ttd.getAllTrainingTopics();
  }
    @RequestMapping(value="gettopicid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxTopic()
  {
      return ttd.getNextTopicId();
  }

  @RequestMapping(value="addtopic",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addTopic(@RequestBody TrainingTopics s)
  {
   ttd.addTrainingTopics(s);
   return "success";
  }
  @RequestMapping(value="updatetopic",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateTopic(@RequestBody TrainingTopics s)
  {
   ttd.updateTopics(s);
   return "success";
  }
  @RequestMapping(value="displaytopics/{topic_id}",method=RequestMethod.GET)
  @ResponseBody
  public TrainingTopics getTopicID(@PathVariable("topic_id") int topic_id)
  {
   return ttd.getTrainingTopicsById(topic_id);
  }  
  @RequestMapping(value="deletetopic/{topic_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteTopic(@PathVariable("topic_id") int topic_id)
  {
   ttd.deleteTopics(topic_id);
   return "success";
  }
   @RequestMapping("contents")  
  public ModelAndView newContents(){
      return new ModelAndView("Master/TopicContent");
  }
  @RequestMapping(value="displaycontent",method=RequestMethod.GET)
  @ResponseBody()
  public List<TopicContent> getAllContents()
  {
      return tcd.getAllTopicContent();
  }
    @RequestMapping(value="getcontentid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxContent()
  {
      return tcd.getNextTopicContentId();
  }

  @RequestMapping(value="addcontent",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addContent(@RequestBody TopicContent s)
  {
   tcd.addTopicContent(s);
   return "success";
  }
  @RequestMapping(value="updatecontent",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateContent(@RequestBody TopicContent s)
  {
   tcd.updateTopicContent(s);
   return "success";
  }
  @RequestMapping(value="displaycontent/{content_id}",method=RequestMethod.GET)
  @ResponseBody
  public TopicContent getContentID(@PathVariable("content_id") int content_id)
  {
   return tcd.getTopicContentById(content_id);
  }  
  @RequestMapping(value="deletecontent/{content_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteContent(@PathVariable("content_id") int content_id)
  {
   tcd.deleteTopicContent(content_id);
   return "success";
  }
}
