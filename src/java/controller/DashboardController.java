package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 @Controller
public class DashboardController {
 
    @RequestMapping("dashboardb")  
  public ModelAndView newDash(HttpServletRequest req,HttpServletResponse res){
        HttpSession session=req.getSession();
       res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if(session.getAttribute("user_name")==null)
        {
                 return new ModelAndView("Login/Login","command",new Login());
 
        }
     return new ModelAndView("DashBoard/Dashboard");
  }
   @RequestMapping("accdashboard")  
  public ModelAndView accountantDashboard(){
      return new ModelAndView("DashBoard/DashboardAccountant");
  }
   @RequestMapping("counsdashboard")  
  public ModelAndView counselorDashboard(){
      return new ModelAndView("DashBoard/DashboardCounselor");
  }
 }
