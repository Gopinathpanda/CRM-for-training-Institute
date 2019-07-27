package controller;
import model.StudentProfile;import model.AllAdmission;import model.RegistrationDetails;
import model.RegistrationQualification;import model.StudentPayment;
import dao.StudentProfileDao;import dao.AllAdmissionDao;import dao.RegistrationDetailsDao;
import dao.RegistrationQualificationDao;import dao.StudentPaymentDao;
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
public class AdmissionController {
       @Autowired
       StudentProfileDao stdt;
       @Autowired
      AllAdmissionDao aad;
       @Autowired
      RegistrationDetailsDao rdd;
       @Autowired
      RegistrationQualificationDao rqd;
        @Autowired
      StudentPaymentDao spd;
       @RequestMapping("studentdetails/{regid}")  
  public ModelAndView studentAdmission(@PathVariable("regid")int id){

      
      return new ModelAndView("Admission/ShowStudentDetails","regid",id);
  }
   @RequestMapping("all")  
  public ModelAndView newAdmission(){
      return new ModelAndView("Admission/StudentProfile");
  }
       @RequestMapping("admission")  
  public ModelAndView allAdmission(){
      return new ModelAndView("Admission/AllAdmission");
  }
   @RequestMapping("details")  
  public ModelAndView AdmissionDetails(){
      return new ModelAndView("Admission/RegistrationDetails");
  }
  @RequestMapping("registrationqualification")  
  public ModelAndView AdmissionQualification(){
      return new ModelAndView("Admission/RegistrationQualification");
  }
  @RequestMapping("studentpayment")  
  public ModelAndView StudentPayment(){
      return new ModelAndView("Admission/StudentPayment");
  } 
   @RequestMapping(value="updateprofile",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateProfile(@RequestBody StudentProfile s)
  {
   stdt.updateProfile(s);
   return "success";
  }
  @RequestMapping(value="displayprofile/{registration_id}",method=RequestMethod.GET)
  @ResponseBody
  public StudentProfile getProfileByID(@PathVariable("registration_id") int registration_id)
  {
   return stdt.getStudentProfileById(registration_id);
  }  
  @RequestMapping(value="deleteprofile/{registration_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteProfile(@PathVariable("registration_id") int registration_id)
  {
   stdt.deleteProfile(registration_id);
   return "success";
  }
  @RequestMapping(value="displayregdetails",method=RequestMethod.GET)
  @ResponseBody()
  public List<RegistrationDetails> getAllDetails()
  {

      return rdd.getAllDetails();
  }
   @RequestMapping(value="updateregdetails",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateRegDetails(@RequestBody RegistrationDetails s)
  {
      
   rdd.updateDetails(s);
   return "success";
  }
  @RequestMapping(value="displaydetails/{registration_id}",method=RequestMethod.GET)
  @ResponseBody
  public RegistrationDetails getDetailsByID(@PathVariable("registration_id") int registration_id)
  {
   return rdd.getDetailsById(registration_id);
  }  
  @RequestMapping(value="deletedetails/{registration_details_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteDetails(@PathVariable("registration_details_id") int registration_details_id)
  {
   rdd.deleteDetails(registration_details_id);
   return "success";
  }
  @RequestMapping(value="displayregqualification",method=RequestMethod.GET)
  @ResponseBody()
  public List<RegistrationQualification> getAllQualification()
  {

      return rqd.getAllRegdQualification();
  }
   @RequestMapping(value="updateregqualification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateQualification(@RequestBody RegistrationQualification s)
  {
   rqd.updateRegdQualification(s);
   return "success";
  }
  @RequestMapping(value="displayregqualification/{registration_id}",method=RequestMethod.GET)
  @ResponseBody
  public RegistrationQualification getQualificationByID(@PathVariable("registration_id") int registration_id)
  {
   return rqd.getregQualificationById(registration_id);
  }  
  @RequestMapping(value="deleteregqualification/{registration_qualification_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteQualification(@PathVariable("registration_qualification_id") int registration_qualification_id)
  {
   rqd.deleteRegdQualification(registration_qualification_id);
   return "success";
  }
   @RequestMapping(value="displaystdpayment",method=RequestMethod.GET)
  @ResponseBody()
  public List<StudentPayment> getAllStdPayment()
  {

      return spd.getAllStudentPayment();
  }
   @RequestMapping(value="updatestdpayment",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updatePayment(@RequestBody StudentPayment s)
  {
   spd.updateStudentPayment(s);
   return "success";
  }
  @RequestMapping(value="displaystdpayment/{registration_id}",method=RequestMethod.GET)
  @ResponseBody
  public StudentPayment getPaymentByID(@PathVariable("registration_id") int registration_id)
  {
   return spd.getStudentPaymentById(registration_id);
  }  
  @RequestMapping(value="deletestdpayment/{payment_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deletePayment(@PathVariable("payment_id") int payment_id)
  {
   spd.deleteStudentPayment(payment_id);
   return "success";
  }
   @RequestMapping(value="displayprofile",method=RequestMethod.GET)
  @ResponseBody()
  public List<StudentProfile> getAllStudents()
  {

      return  stdt.getAllStudentProfile();
  }
 
   @RequestMapping(value="addprofile",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addStudent(@RequestBody AllAdmission s)
  {
   aad.addProfile(s);
   return "success";
  }
   @RequestMapping(value="getprofileid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxStudent()
  {
      return aad.getNextStudentProfileId();
  }
    @RequestMapping(value="adddetails",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addDetails(@RequestBody RegistrationDetails s)
  {
   rdd.addDetails(s);
   return "success";
  }
   @RequestMapping(value="getdetailsid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxDetails()
  {
      return rdd.getNextDetailsId();
  }
   @RequestMapping(value="addregdqualification",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addQualification(@RequestBody RegistrationQualification s)
  {
   rqd.addRegdQualification(s);
   return "success";
  }
   @RequestMapping(value="getregdqualificationid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxStdQualification()
  {
      return rqd.getNextRegdQualificationId();
  }
  @RequestMapping(value="addstudentpayment",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
   public String addStdPayment(@RequestBody StudentPayment s)
  {
   spd.addStudentPayment(s);
   return "success";
  }
   @RequestMapping(value="getstudentpaymentid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxStdpayment()
  {
      return spd.getNextStudentPaymentId();
  }
}
