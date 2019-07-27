<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include  file="../common/files.jsp" %>
        <script>
             $(document).ready(function(){
             DisplayCity();
             Display();
             DisplayEmail();
             DisplayEmailID();
             EmailShow();
             DisplayCourses();
             DisplayMode();
             DisplayDetails();
             DisplayQualification();
             DisplayRegQualification();
             DisplayPayment();
             $("#ddcity").change(function(){
             FetchLocation();
             });
              $("#ddqualification").change(function(){
             FetchSpecializations();
             });
                     $("#btnupdate").click(function(){
                       updateProfile();
                       ClearData();
                       Display();
                       alert("Profile Updated");
                       });
                             $("#btndelete").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             Delete();
                             ClearData();
                             Display();
                             }
                            });
                            
                        $("#btnupdatedetails").click(function(){
                        updateDetails();
                        ClearDataDetails();
                        DisplayDetails();
                        alert("Registration Details Updated");
                        });
                             $("#btndeletedetails").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             DeleteDetails();
                             ClearDataDetails();
                             DisplayDetails();
                             }
                             });
                       $("#btnupdatequalification").click(function(){
                        updateQualification();
                        ClearDataQualification();
                        DisplayRegQualification();
                        alert("Registration Qualification Updated");
                        });
                             $("#btndeletequalification").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             DeleteQualification();
                             ClearDataDetails();
                             DisplayRegQualification();
                             }
                           });     
                         $("#btnupdatepayment").click(function(){
                        updatePayment();
                        ClearDataPayment();
                        DisplayPayment();
                        alert("Student Payment Updated");
                        });
                             $("#btndeletepayment").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             DeletePayment();
                             ClearDataPayment();
                             DisplayPayment();
                             }
                           });     
                      });
        
        
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
             function DisplayQualification(){
                $.ajax({
                url:'/CRM_Student/displayqualification',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddqualification").empty();
                  $("#ddqualification").append("<option  selected disabled>Select Qualification</option>");
                    $.each(data,function(i,d){
                        $("#ddqualification").append("<option  value="+d.qualification_id+">"+d.qualification+"</option>");
                    });
                }
                });
                 }

       function FetchSpecializations(){
           var qid=$("#ddqualification").val();
               $.ajax({
                    url:'/CRM_Student/displayspecializationByQid/'+qid,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                     $("#ddspecialization").empty();
                        $("#ddspecialization").append("<option selected disabled>Select Specialization</option>");

                    $.each(data,function(i,d){
                      
                     $("#ddspecialization").append("<option  value="+d.specialization_id+">"+d.specialization+"</option>");
                   });
                    
                    }
                });
            }
             
             function updateProfile(){
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
                    "permanent_address":pa,"local_address":la};
                 
                $.ajax({
                    url:'/CRM_Student/updateprofile',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
                });
              }
              
              
              function updateDetails(){
                 var rdid=$("#rdid").val();
                var rid=$("#ddemail").val();
                var pid=$("#ddcourse").val();
                var mode=$("#ddmode").val();
                var status=$("#status").val();
                var data={"registration_details_id":rdid,"registration_id":rid,"program_id":pid,"fee_mode_id":mode,"status":status}; 
                $.ajax({
                    url:'/CRM_Student/updateregdetails',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    DisplayDetails();
                    }
                });
              }
              

               function updateQualification(){
                var rqid=$("#rqid").val();
                var rid=$("#ddemailid").val();
                var sp=$("#ddspecialization").val();
                var un=$("#un").val();
                var pe=$("#pe").val();
                var py=$("#py").val();
                var data={"registration_qualification_id":rqid,"registration_id":rid,"specialization_id":sp,"university":un,"percentage":pe,"passing_year":py};
                 
                $.ajax({
                    url:'/CRM_Student/updateregqualification',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    DisplayRegQualification();
                    }
                });
              }
               function updatePayment(){
                var pid=$("#pid").val();
                var rid=$("#emailid").val();
                var am=$("#amount").val();
                var pd=$("#pd").val();
                var pu=$("#purpose").val();
                var pm=$("#pm").val();
                var data={"payment_id":pid,"registration_id":rid,"amount":am,"payment_date":pd,"purpose":pu,"payment_mode":pm};
                $.ajax({
                    url:'/CRM_Student/updatestdpayment',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    DisplayPayment();
                    }
                });
              }
              function DisplayEmail(){
                $.ajax({
                url:'/CRM_Student/displayprofile',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddemail").empty();
               $("#ddemail").append("<option  selected disabled>Select Email</option>");
                $.each(data,function(i,d){
               $("#ddemail").append("<option  value="+d.registration_id+">"+d.email+"</option>");
                });
                }
                });
                }  
                 function DisplayEmailID(){
                $.ajax({
                url:'/CRM_Student/displayprofile',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddemailid").empty();
               $("#ddemailid").append("<option  selected disabled>Select Email</option>");
                $.each(data,function(i,d){
               $("#ddemailid").append("<option  value="+d.registration_id+">"+d.email+"</option>");
                });
                }
                });
                }  
        function EmailShow(){
                $.ajax({
                url:'/CRM_Student/displayprofile',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#emailid").empty();
               $("#emailid").append("<option  selected disabled>Select Email</option>");
                $.each(data,function(i,d){
               $("#emailid").append("<option  value="+d.registration_id+">"+d.email+"</option>");
                });
                }
                });
                }  
        function DisplayCourses(){
                $.ajax({
                url:'/CRM_Student/displayprogram',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcourse").empty();
                 $("#ddcourse").append("<option  selected disabled>Select Course</option>");
$.each(data,function(i,d){
    $("#ddcourse").append("<option  value="+d.program_id+">"+d.course_name+"</option>");
});
    }
    });
     }
     function DisplayMode(){
                $.ajax({
                url:'/CRM_Student/displayfeemode',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddmode").empty();
                 $("#ddmode").append("<option  selected disabled>Select Mode</option>");
$.each(data,function(i,d){
    $("#ddmode").append("<option  value="+d.fee_mode_id+">"+d.mode_name+"</option>");
});
    }
    });
     }
            
     function DisplayDetails(){
            var id1=${regid}
                    $.ajax({
                    url:'/CRM_Student/displaydetails/'+id1,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                $("#rdid").val(d.registration_details_id);
                $("#ddemail").append("<option selected value="+d.registration_id+">"+d.email+"</option>");
                 $("#ddcourse").append("<option selected value="+d.program_id+">"+d.course_name+"</option>");
                $("#ddmode").append("<option selected value="+d.fee_mode_id+">"+d.mode_name+"</option>");
                $("#status").val(d.status);
                    }
                });
            }
      function DisplayRegQualification(){
            var id2=${regid}
                    $.ajax({
                    url:'/CRM_Student/displayregqualification/'+id2,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                $("#rqid").val(d.registration_qualification_id);
                $("#ddemailid").append("<option selected value="+d.registration_id+">"+d.email+"</option>");
                $("#ddqualification").val(d.qualification_id);
                $("#ddspecialization").append("<option selected value="+d.specialization_id+">"+d.specialization+"</option>");
                $("#un").val(d.university);
                $("#pe").val(d.percentage);
                $("#py").val(d.passing_year);
                    }
                });
            }
       function DisplayPayment(){
            var id1=${regid}
                    $.ajax({
                    url:'/CRM_Student/displaystdpayment/'+id1,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                $("#pid").val(d.payment_id);
                $("#emailid").append("<option selected value="+d.registration_id+">"+d.email+"</option>");
                $("#amount").val(d.amount);
                $("#pd").val(d.payment_date);
                $("#purpose").val(d.purpose);
                $("#pm").val(d.payment_mode);
                    }
                });
            }
            function Display(){
            var id=${regid}
                    $.ajax({
                    url:'/CRM_Student/displayprofile/'+id,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                  $("#rid").val(d.registration_id);
                $("#fn").val(d.first_name);
                 $("#mn").val(d.middle_name);
                 $("#ln").val(d.last_name);
                 $("#db").val(d.dob); 
                 $("#cno").val(d.contact_no);
                $("#email").val(d.email);
                $("#pno").val(d.parent_number);
                 $("#rdate").val(d.registration_date);
                 $("#ddcity").val(d.city_id);
                   $("#ddlocation").append("<option selected value="+d.location_id+">"+d.location_name+"</option>");
                        if(d.gender=="male")
                        {
                            $("#gmale").attr("checked" ,"checked");
                        }
                         if(d.gender=="female")
                        {
                            $("#gfemale").attr("checked" ,"checked");
                        }
                         if(d.gender=="other")
                        {
                            $("#gother").attr("checked" ,"checked");
                        }

        $("#paddress").val(d.permanent_address);
                      $("#laddress").val(d.local_address);
                       
                    }
                });
            }
            function Delete(){
            var i=$("#rid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteprofile/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    ClearData();
                    pageredirect();
                    }
               });
            }
             function DeleteDetails(){
            var i=$("#rdid").val();
                    $.ajax({
                    url:'/CRM_Student/deletedetails/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    ClearDataDetails();
                    pageredirect();
                    }
               });
            }
             function DeleteQualification(){
            var i=$("#rqid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteregqualification/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    ClearDataQualification();
                    pageredirect();
                    }
               });
            }
            function DeletePayment(){
            var i=$("#pid").val();
                    $.ajax({
                    url:'/CRM_Student/deletestdpayment/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    ClearDataPayment();
                    pageredirect();
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
             function ClearDataDetails()
       {
           $("#rdid").val("");
           $("#ddemail").val("");
           $("#ddcourse").val("");
           $("#ddmode").val("");
           $("#status").val("");
           
       }
        function ClearDataQualification()
       {
           $("#rqid").val("");
           $("#ddemailid").val("");
           $("#ddspecialization").val("");
           $("#un").val("");
           $("ddqualification").val("");
           $("#pe").val("");
           $("#py").val("");
        }
         function ClearDataPayment()
       {
           $("#pid").val("");
           $("#emailid").val("");
           $("#amount").val("");
           $("#pd").val("");
           $("#purpose").val("");
           $("#pm").val("");
        }
     
            function pageredirect()
            {
                window.location.href="http://localhost:8085/CRM_Student/admission"
            }
         </script>
    </head>
   <body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
      <div id="page-wrapper"> 
       <ul class="nav nav-tabs">
    <li class="active"><a href="#tab1" data-toggle="tab">Student Profile</a></li>
    <li><a href="#tab2" data-toggle="tab">Registration Details</a></li>
    <li><a href="#tab3" data-toggle="tab">Qualification</a></li>
    <li><a href="#tab4" data-toggle="tab">Student Payment</a></li>
    </ul>
          <div class="tab-content">
   <div class="tab-pane active" id="tab1">
<div class="container-fluid">
     <div style="overflow-y: scroll;height: 450px">
        <div class="row">
             <h3 class="title1"><center>Student Profile-Form</center></h3>
		<div class="form-three widget-shadow">   
                    <form class="form-horizontal">
                        <div class="form-group">
                             <div class="col-md-3"><small><label class="text-primary">Registration ID:</label></small></div>
                             <div class="col-md-3" ><small><label class="text-primary"> First Name:</label></small></div>
                             <div class="col-md-3"><small><label class="text-primary">  Middle Name:</label></small></div>
                             <div class="col-md-3"><small><label class="text-primary"> Last Name:</label></small></div>
                            <div class="col-md-3 img-rounded"><input type="text" class="form-control" id="rid" placeholder="REGISTRATION ID"></div> 
                            <div class="col-md-3 img-rounded"><input type="text" class="form-control" id="fn" placeholder="FIRST NAME"></div>
                            <div class="col-md-3 img-rounded"> <input type="text" class="form-control" id="mn" placeholder="MIDDLE NAME"></div>
                            <div class="col-md-3 img-rounded"> <input type="text" class="form-control" id="ln" placeholder="LAST NAME"></div>
                        </div>  
                        <div class="form-group">
                            <div class="col-md-3"><small><label class="text-primary">City Name:</label></small></div>
                            <div class="col-md-3"><small><label class="text-primary">Location Name</label></small></div> 
                            <div class="col-md-3"><small><label class="text-primary"> Date Of Birth:</label></small></div>
                            <div class="col-md-3"><small><label class="text-primary">Registration Date:</label></small></div>
                            <div class=" col-md-3 img-rounded"> 
                               <select type="text" id="ddcity" class="form-control">
                                    <option selected="selected" disabled="disabled">Select City</option>
                               </select>
                            </div>
                            <div class=" col-md-3 img-rounded">
                                <select type="text" id="ddlocation" class="form-control">
                                    <option selected="selected" disabled="disabled">Select Location</option>
                                </select>
                            </div>
                           <div class="col-md-3 img-rounded"> <input type="date" class="form-control" id="db"></div>
                           <div class="col-md-3 img-rounded"> <input type="date" class="form-control" id="rdate"></div>
                        </div>
                          <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> Contact Number:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Email:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Parent Number:</label></small></div>
                                
                            <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="cno" placeholder="MOBILE NUMBER"></div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="email" placeholder="EMAIL"></div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="pno" placeholder="PARENT NUMBER"></div>
                            
                        </div>
                        <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> Gender:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Local Address:</label></small></div>
                           <div class="col-md-4"><small><label class="text-primary">  Permanent Address:</label></small></div>
                           
                                <div class="col-md-4"><input type="radio" value="male" id="gmale" name="d"> <small>Male</small>
                                                  <input type="radio" value="female" id="gfemale" name="d"><small> Female</small>
                                                  <input type="radio" value="others" id="gother" name="d"><small> Others</small>
                                </div>
                            <div class="col-md-4 img-rounded"> <textarea rows="3" cols="31" id="laddress"></textarea></div>
                            <div class="col-md-4 img-rounded"><textarea rows="3" cols="31" id="paddress"></textarea></div>
                               
                        </div>
                        <div class="form-group">
                           
                        </div>
                        
                    </form>
                      
                    
                <p>
                <button type="button" id="btndelete" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdate" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
               
                </p>
            </div>
            
           </div>
      </div>
</div>
   </div>
 <div class="tab-pane" id="tab2">
   <div class="container-fluid">
        <div class="row">
             <h3 class="title1"><center>Registration Details-Form</center></h3>
		<div class="form-three widget-shadow">   
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-6"><small><label class="text-primary">Registration Details ID:</label></small></div>
                                <div class="col-md-6"><small><label class="text-primary">Student Email:</label></small></div>
                                <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="rdid" placeholder="REGISTRATION DETAILS ID"></div>
                                <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddemail" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Email</option>
                                    </select>
                                </div>   
                             
                            </div>
                            <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> Course Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Payment Mode Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Status:</label></small></div>
                                <div class=" col-md-4 img-rounded"> 
                                    <select type="text" id="ddcourse" class="form-control">
                                       <option selected="selected" disabled="disabled">Select Course Name</option>
                                    </select>
                                </div>
                                <div class=" col-md-4 img-rounded"> 
                                    <select type="text" id="ddmode" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Mode</option>
                                    </select>
                              </div>
                            <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="status" placeholder="STATUS"></div>
                            </div>    
                        </form>                    
                <p>
                <button type="button" id="btndeletedetails" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdatedetails" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
               
                </p>
            </div>
            </div>
       </div>
 </div>
 <div class="tab-pane" id="tab3"> 
   <div class="container-fluid">
        <div class="row">
             <h3 class="title1"><center>Student Qualification-Form</center></h3>
		<div class="form-three widget-shadow">   
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-6"><small><label class="text-primary">Registration Qualification ID:</label></small></div>
                                <div class="col-md-6"><small><label class="text-primary">Student Email:</label></small></div>
                                 
                                <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="rqid" placeholder=" QUALIFICATION ID"></div>
                               <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddemailid" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Email</option>
                                    </select>
                                </div>   
                            </div>
                            <div class="form-group">
                                <div class="col-md-6"><small><label class="text-primary">Qualification:</label></small></div>
                                <div class="col-md-6"><small><label class="text-primary">Specialization:</label></small></div>
                                 <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddqualification" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Qualification</option>
                                    </select>
                                </div>
                                 <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddspecialization" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Specialization</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> University Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Percentage:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Passing Year:</label></small></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="un" placeholder="UNIVERSITY"></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="pe" placeholder="PERCENTAGE"></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="py" placeholder="PASSING YEAR"></div>
                            </div>       
                        </form>                    
                <p>
                <button type="button" id="btndeletequalification" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdatequalification" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
                </p>
            </div>
        </div>
     </div>
</div>
<div class="tab-pane" id="tab4"> 
   <div class="container-fluid">
        <div class="row">
             <h3 class="title1"><center>Student Payment-Form</center></h3>
		<div class="form-three widget-shadow">   
                        <form class="form-horizontal">
                             <div class="form-group">
                                <div class="col-md-4"><small><label class="text-primary">Payment ID:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Student Email:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Amount:</label></small></div> 
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="pid" placeholder=" PAYMENT ID"></div>
                              <div class="col-md-4 img-rounded"> 
                                    <select type="text" id="emailid" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Email</option>
                                    </select>
                                </div>   
                                 <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="amount" placeholder=" AMOUNT"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4"><small><label class="text-primary">Payment Date:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Purpose:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Payment Mode:</label></small></div>
                                <div class="col-md-4 img-rounded"><input type="date" class="form-control" id="pd" placeholder=" PAYMENT DATE"></div>
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="purpose" placeholder=" PURPOSE"></div>
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="pm" placeholder=" PAYMENT MODE"></div>
                            </div>
                       </form>                    
                <p>
                <button type="button" id="btndeletepayment" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdatepayment" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
               
                </p>
            </div>
          </div>
       </div>
     </div>
    </div>
  </div> 
      <%@include file="../common/footer.jsp" %>
    </body>
     
</html>