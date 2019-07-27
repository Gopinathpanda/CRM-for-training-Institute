<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
</head>
<body>	
<div class="main-content">
    <div id="page-wrapper">
        <div class="main-page login-page ">
	    <h2 class="title1">Login</h2>
	        <div class="widget-shadow">
		    <div class="login-body">
                        <form:form action="/CRM_Student/dashboardb" method="post">
                            <form:input type="email" class="user" path="login" placeholder="Enter Your Email" required="" value="" autocomplete="off"></form:input>
                                <form:input type="password"  path="password" class="lock" placeholder="Password" required="" value="" autocomplete="off"></form:input>
                                <form:input type="text" path="role"  placeholder="Enter Role(Admin/Accountant/Counselor)" autocomplete="off" required="" value=""style="width:530px; height:50px"></form:input>
				    <div class="forgot-grid">
					<label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>Remember me</label>
					    <div class="forgot">
                                                <a href="/CRM_Student/setpwd"><p style="text-decoration: #cd201f underline;color:red">Forgot password?</p></a>
					    </div>
					    <div class="clearfix"> </div>
				    </div>
				    <input type="submit" name="Sign In" value="Sign In">
				    <div class="registration">
                                        <h2 style="color:red">${msg}</h2>
                                    </div>
			</form:form>
		    </div>
		</div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>


                      
						
