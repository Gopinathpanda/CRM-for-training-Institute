<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
     $(document).ready(function(){
            Display();
         
             });   
    function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayprofile',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.registration_id+"</td><td>"+d.first_name+"</td><td>"+d.middle_name+"</td><td>"+d.last_name+"</td><td>"+d.dob+"</td><td>"+d.email+"</td><td><a  href='/CRM_Student/studentdetails/"+d.registration_id+"' >Show</td></tr>");
});
    }
    });
     }
     </script>
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
            <div class="col-md-12">
            <div style="overflow-y: scroll;height: 430px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Registration ID</th>
                          <th class="text-center">First Name</th>
                          <th class="text-center">Middle Name</th>
                          <th class="text-center">Last Name</th>
                          <th class="text-center">Date Of Birth</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Action</th>
                       </tr>
                   </thead>
                   <tbody id="tbldata" class="text-center text-capitalize text-primary">
                   </tbody>
                </table>
            
            </div>
      </div>
    </div>
         <%@include file="../common/footer.jsp" %>
    </body>
</html>