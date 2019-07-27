<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
     $(document).ready(function(){
        $("#ri").hide();
            $("#cn").hide();
            var ri_err = true;
            var cn_err = true;

            $("#rid").keyup(function () {
                ri_check();
            });

            function ri_check()
            {
                var ri = $("#rid").val();
                if (ri.length == "") {
                    $("#ri").show();
                    $("#ri").html("**please fill  Registration id");
                    $("#ri").focus();
                    $("#ri").css("color", "red");
                    ri_err = false;
                    return false;
                }
                else {
                    $("#ri").hide();
                }
            }

            $("#ddcity").keyup(function () {
                 cn_check();

            });
            function cn_check()
            {
                var c = $("#ddcity").val();
                if (c.length == "") {
                    $("#cn").show();
                    $("#cn").html("**please fill City name");
                    $("#cn").focus();
                    $("#cn").css("color", "red");
                    cn_err = false;
                    return false;
                }
                else {
                    $("#cn").hide();
                }
            }
        DisplayCity();
        Display();
             $("#ddcity").change(function(){
             FetchLocation();
            
        });
        $("#add").click(function(){
            ClearData();
        $("#myModal").modal("show");
            GetMaxProfile();
            $("#btnsubmit").show();
         });
          $("#btnsubmit").click(function(){
                  addProfile();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
    });
       function GetMaxProfile()
                {
                $.get("/CRM_Student/getprofileid",function(data){
                 $("#rid").val(data);
                });                   
                }          
        
        function DisplayCity(){
                $.ajax({
                url:'/CRM_Student/displaycity',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcity").empty();
               $("#ddcity").append("<option  selected disabled>Select City</option>");
$.each(data,function(i,d){
    $("#ddcity").append("<option  value="+d.city_id+">"+d.city_name+"</option>");
});
    }
    });
     }
    function FetchLocation(){
         
          var cid=$("#ddcity").val();
      
       
            $.ajax({
                    url:'/CRM_Student/displaylocationbycity/'+cid,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                     $("#ddlocation").empty();
                        $("#ddlocation").append("<option selected disabled>Select Location</option>");

                    $.each(data,function(i,d){
                      
                     $("#ddlocation").append("<option  value="+d.location_id+">"+d.location_name+"</option>");
});
                    
                    }
                });
            }
             
            
            function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayprofile',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.registration_id+"</td><td>"+d.first_name+"</td><td>"+d.middle_name+"</td><td>"+d.last_name+"</td><td>"+d.dob+"</td><td>"+d.contact_no+"</td><td>"+d.email+"</td><td>"+d.parent_number+"</td><td>"+d.gender+"</td><td>"+d.registration_date+"</td><td>"+d.location_name+"</td><td>"+d.local_address+"</td><td>"
    +d.permanent_address+"</td></tr>");
});
    }
    });
     }
      function addProfile(){
                var rid=$("#rid").val();
                var fn=$("#fn").val();
                 var mn=$("#mn").val();
                  var ln=$("#ln").val();
                   var dob=$("#db").val(); 
                   var cn=$("#cno").val();
                var email=$("#email").val();
                var pn=$("#pno").val();
                 var rd=$("#rdate").val();
                    var l=$("#ddlocation").val();
                     var g=$("input[name='d']:checked").val();
                      var pa=$("#paddress").val();
                       var la=$("#laddress").val();
                    
                var data={"registration_id":rid,"first_name":fn,"middle_name":mn,"last_name":ln,"dob":dob,"contact_no":cn,"email":email,"parent_number":pn,"gender":g,"registration_date":rd,"location_id":l,
                    "local_address":la,"permanent_address":pa};
                 
                $.ajax({
                    url:'/CRM_Student/addprofile',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            
     
            function ClearData(){
                 $("#rid").val("");
                $("#fn").val("");
                 $("#mn").val("");
                 $("#ln").val("");
                   $("#db").val(""); 
                  $("#cno").val("");
                   $("#pno").val("");
                $("#email").val("");
                 $("#rdate").val("");
                  $("#ddcity").val("");
                    $("#ddlocation").val("");
                     $("#gender").val("");
                      $("#paddress").val("");
                      $("#laddress").val("");
            }


</script>
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
  <div class="container">
               <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Student Profile</a>
          <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Student Profile-Form</center></h3>
                   </div>
                    <div class="modal-body">
                       
                    <form class="form-horizontal">
                 
                        <div class="form-group">
                           
                            <div class="col-md-4"><small><label class="text-primary">Registration ID:</label></small></div>
                             <div class="col-md-4"><small><label class="text-primary">City Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Location Name</label></small></div> 
                                
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="rid" placeholder="REGISTRATION ID"></div>
                               <div class=" col-md-4 img-rounded"> 
                                <select type="text" id="ddcity" class="form-control">
                                  <option selected="selected" disabled="disabled">Select City</option>
                                </select>
                              </div>
                               <div class=" col-md-4 img-rounded">
                                   <select type="text" id="ddlocation" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Location</option>
                                </select>
                             </div>
                             <div class="col-md-4"><h5 id="ri"></h5></div>
                             <div class="col-md-4"><h5 id="cn"></h5></div>
                             <div class="col-md-4"><h5 id="ln"></h5></div>
                        </div>
                        <div class="form-group">
                               <div class="col-md-4" ><small><label class="text-primary"> First Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Middle Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Last Name:</label></small></div>
                                
                            <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="fn" placeholder="FIRST NAME"></div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="mn" placeholder="MIDDLE NAME"></div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="ln" placeholder="LAST NAME"></div>
                           
                         </div>    
                        <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> Contact Number:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Email:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Parent Number:</label></small></div>
                                
                            <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="cno" placeholder="MOBILE NUMBER"></div>
                            <div class="col-md-4 img-rounded"> <input type="email" class="form-control" id="email" placeholder="EMAIL"></div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="pno" placeholder="PARENT NUMBER"></div>
                            
                        </div>
                        <div class="form-group">
                                <div class="col-md-6" ><small><label class="text-primary"> Gender:</label></small></div>
                               <div class="col-md-6" ><small><label class="text-primary"> Photo:</label></small></div>
                                <div class="col-md-6"><input type="radio" value="male" id="gmale" name="d"> <small>Male</small>
                                                  <input type="radio" value="female" id="gfemale" name="d"><small> Female</small>
                                                  <input type="radio" value="others" id="gother" name="d"><small> Others</small>
                                </div>
                               <div class="col-md-6"><small><input type="file" class="text-primary" id="photo"></small></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary"> Date Of Birth:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Registration Date:</label></small></div>
                            <div class="col-md-6 img-rounded"> <input type="date" class="form-control" id="db"></div>
                            <div class="col-md-6 img-rounded"> <input type="date" class="form-control" id="rdate"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary"> Local Address:</label></small></div>
                           <div class="col-md-6"><small><label class="text-primary">  Permanent Address:</label></small></div>
                            <div class="col-md-6 img-rounded"> <textarea rows="3" cols="31" id="laddress"></textarea></div>
                            <div class="col-md-6 img-rounded"><textarea rows="3" cols="31" id="paddress"></textarea></div>
                        </div>
                        
                    </form>
                      
                    </div>
                <div class="modal-footer">
                <button type="button" id="btnsubmit" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Submit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Close</button>
                </div>
                </div>
           </div>
        </div>
     </div>
     </div>
        <div class="col-md-12">
            <div style="overflow-y: scroll;height: 470px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Registration ID</th>
                          <th class="text-center">First Name</th>
                          <th class="text-center">Middle Name</th>
                          <th class="text-center">Last Name</th>
                          <th class="text-center">Date Of Birth</th>
                          <th class="text-center">Contact Number</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Parent Number</th>
                          <th class="text-center">Gender</th>
                          <th class="text-center">Registration Date</th>
                          <th class="text-center">Location Name</th>
                          <th class="text-center">Local Address</th>
                          <th class="text-center">Permanent Address</th>
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