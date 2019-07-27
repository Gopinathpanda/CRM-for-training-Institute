<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
</head>
<body>	
    <form:form action="/CRM_Student/login" method="post">
<div class="main-content">
    <div id="page-wrapper">
        <div class="main-page login-page ">
	    <h2 class="title1">Login</h2>
	        <div class="widget-shadow">
		    <div class="login-body">
                        <form:input type="email" class="user" path="login" id="email" placeholder="Enter Your Email" required="" value="" autocomplete="off"></form:input>
                           <form:input type="password" class="lock" path="password" id="pwd" placeholder="Enter New Password" required="" value="" autocomplete="off"></form:input>
				<input type="submit" id="add" value="Submit">
			<div class="registration">
                                        <h2 style="color:red">${msg1}</h2>
                                    </div>	   
		    </div>
		</div>
        </div>
    </div>
</div>
    </form:form>
<%@include file="../common/footer.jsp" %>
</body>
</html>


                      
						
