package controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import model.Login;
import dao.AdminLoginDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
 @Controller
public class LoginController {
     @Autowired
     AdminLoginDao ald;
     @RequestMapping("login")  
  public ModelAndView login(HttpServletRequest req,HttpServletResponse res){
          HttpSession session=req.getSession();
          res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
          session.setAttribute("user_name", null);

      return new ModelAndView("Login/Login","command",new Login());
  }
  @RequestMapping("setpwd")  
  public ModelAndView newPassword(){
     return new ModelAndView("Login/SetPassword","command",new Login());
  }  
  @RequestMapping(value ="dashboardb",method = RequestMethod.POST)  
  public ModelAndView check(@ModelAttribute("l")Login l,HttpServletRequest req){
  Login login=null; 
  try{
 login=ald.CheckLogin(l.getLogin(),l.getPassword(),l.getRole());
  }
  catch(Exception ex){}
 if(login==null)
  {
       ModelAndView m=new ModelAndView("Login/Login","command",new Login());
           m.addObject("msg","invalid Email id or password or Role");
           return m;
  }

  if(login!=null && l.getRole().equalsIgnoreCase("admin"))
      {
          HttpSession session=req.getSession();
          session.setAttribute("user_name", l.getLogin());
          ModelAndView m=new ModelAndView("DashBoard/Dashboard");
          return m;    
       }
  else if(login!=null && l.getRole().equalsIgnoreCase("accountant"))
          {
         ModelAndView m=new ModelAndView("DashBoard/DashboardAccountant");
          return m;    
      }
  else if(login!=null && l.getRole().equalsIgnoreCase("counselor"))
  {
      ModelAndView m=new ModelAndView("DashBoard/DashboardCounselor");
          return m;  
  }
       ModelAndView m=new ModelAndView("Login/Login","command",new Login());
           m.addObject("msg","invalid Email id or password or Role");
           return m;
  
  }
 
 @RequestMapping(value = "login",method = RequestMethod.POST)  
  public ModelAndView update(@ModelAttribute("l")Login l){
      if(l.getLogin().equals("gopi@gmail.com") || l.getLogin().equals("gnp@gmail.com")||l.getLogin().equals("g@gmail.com"))
      {
          ald.updateLogin(l);
     Login it=new Login("", "", "");
     ModelAndView m=new ModelAndView("Login/Login","command",it);
     return m;
       }
      else{
          ModelAndView m=new ModelAndView("Login/SetPassword","command",new Login());
           m.addObject("msg1","invalid Email");
           return m;
      }
    }  
  }
 