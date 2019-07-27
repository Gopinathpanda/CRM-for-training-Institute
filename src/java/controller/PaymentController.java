package controller;
import model.TrainingFees;import model.FeeMode;
import dao.TrainingFeesDao;import dao.FeeModeDao;
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
public class PaymentController {
 @Autowired
 TrainingFeesDao tfs;
 @Autowired
 FeeModeDao fmd;
     @RequestMapping("newpayment")  
  public ModelAndView newPayment(){
      return new ModelAndView("Payment/NewPayment");
  }
  @RequestMapping("trainingfees")  
  public ModelAndView trainFees(){
      return new ModelAndView("Payment/TrainingFees");
  }
   @RequestMapping(value="displaytrainingfees",method=RequestMethod.GET)
  @ResponseBody()
  public List<TrainingFees> getAllTrainingFees()
  {
      return tfs.getAllTrainingFees();
  }
    @RequestMapping(value="gettrainingfeesid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxTrainingFees()
  {
      return tfs.getNextTrainingFeesId();
  }

  @RequestMapping(value="addtrainingfees",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addTrainingFees(@RequestBody TrainingFees s)
  {
   tfs.addTrainingFees(s);
   return "success";
  }
  @RequestMapping(value="updatetrainingfees",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateTrainingFees(@RequestBody TrainingFees s)
  {
   tfs.updateTrainingFees(s);
   return "success";
  }
  @RequestMapping(value="displaytrainingfees/{fee_id}",method=RequestMethod.GET)
  @ResponseBody
  public TrainingFees getTrainingFeesByID(@PathVariable("fee_id") int fee_id)
  {
   return tfs.getTrainingFeesById(fee_id);
  }  
  @RequestMapping(value="deletetrainingfees/{fee_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteTrainingFees(@PathVariable("fee_id") int fee_id)
  {
   tfs.deleteTrainingFees(fee_id);
   return "success";
  }
 @RequestMapping("feemode")  
  public ModelAndView Feemode(){
      return new ModelAndView("Payment/FeeMode");
  }
   @RequestMapping(value="displayfeemode",method=RequestMethod.GET)
  @ResponseBody()
  public List<FeeMode> getAllFeeMode()
  {
      return fmd.getAllFeeMode();
  }
    @RequestMapping(value="getfeemodeid",method=RequestMethod.GET)
  @ResponseBody()
  public int getMaxFeeMode()
  {
      return fmd.getNextFeeModeId();
  }

  @RequestMapping(value="addfeemode",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String addFeeMode(@RequestBody FeeMode s)
  {
   fmd.addFeeMode(s);
   return "success";
  }
  @RequestMapping(value="updatefeemode",method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateFeeMode(@RequestBody FeeMode s)
  {
   fmd.updateFeeMode(s);
   return "success";
  }
  @RequestMapping(value="displayfeemode/{fee_mode_id}",method=RequestMethod.GET)
  @ResponseBody
  public FeeMode getFeeModeByID(@PathVariable("fee_mode_id") int fee_mode_id)
  {
   return fmd.getFeeModeById(fee_mode_id);
  }  
  @RequestMapping(value="deletefeemode/{fee_mode_id}",method=RequestMethod.GET)
  @ResponseBody
  public String deleteFeeMode(@PathVariable("fee_mode_id") int fee_mode_id)
  {
   fmd.deleteFeeMode(fee_mode_id);
   return "success";
  }
 }
