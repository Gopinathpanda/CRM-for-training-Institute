<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
            DisplayQualification();
            DisplayEnquiry();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxEnquiryQualification();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });
              $("#ddqualification").change(function(){
             FetchSpecializations();});
                  $("#btnsubmit").click(function(){
                  addEnqQualification();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateEnqQualification();
                       ClearData();
                       $("#myModal").modal("hide");
                       Display();
                       });
                             $("#btndelete").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             Delete();
                             ClearData();
                              $("#myModal").modal("hide");
                             Display();
                             $("#myModal").modal("hide");
                             $("#btnsubmit").show();
                             $("#btnupdate").hide();
                             }
                              $("#myModal").modal("hide");
                            });
                });
                function GetMaxEnquiryQualification()
                {
                $.get("/CRM_Student/getenquiryqualificationid",function(data){
                 $("#eqid").val(data);
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
            function DisplayEnquiry(){
                $.ajax({
                url:'/CRM_Student/displayenquiry',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddemail").empty();
               $("#ddemail").append("<option  selected disabled>Select Lead Source</option>");
$.each(data,function(i,d){
    $("#ddemail").append("<option  value="+d.enquiry_id+">"+d.email+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayenqqualification',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.enquiry_qualification_id+"</td><td>"+d.email+"</td><td>"+d.specialization+"</td><td>"+d.university+"</td><td>"+d.percentage+"</td><td>"+d.passing_year+"</td><td><input type='button' value='View' onclick='View("+d.enquiry_qualification_id+")'></td></tr>");
});
    }
    });
     }
               function addEnqQualification(){
                var ei=$("#eqid").val();
                var email=$("#ddemail").val();
                var sp=$("#ddspecialization").val();
                var un=$("#un").val();
                var p=$("#per").val();
                var s=$("#py").val();
                var data={"enquiry_qualification_id":ei,"enquiry_id":email,"specialization_id":sp,"university":un,"percentage":p,"passing_year":s};
                $.ajax({
                    url:'/CRM_Student/addenquiryqualification',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateEnqQualification(){
                 var ei=$("#eqid").val();
                var email=$("#ddemail").val();
                var sp=$("#ddspecialization").val();
                var un=$("#un").val();
                var p=$("#per").val();
                var s=$("#py").val();
                var data={"enquiry_qualification_id":ei,"enquiry_id":email,"specialization_id":sp,"university":un,"percentage":p,"passing_year":s};
                $.ajax({
                    url:'/CRM_Student/updateenquiryqualification',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
                });
              }
            function View(i){
            $("#btnsubmit").hide();
            $("#btnupdate").show();
            $("#btndelete").show();
            $.ajax({
                    url:'/CRM_Student/displayenquiryqualification/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
               $("#eqid").val(d.enquiry_qualification_id);
                $("#ddemail").append("<option selected value="+d.enquiry_id+">"+d.email+"</option>");
                $("#ddqualification").val(d.qualification_id);
                 $("#ddspecialization").append("<option selected value="+d.specialization_id+">"+d.specialization+"</option>");
                $("#un").val(d.university);
                $("#per").val(d.percentage);
                $("#py").val(d.passing_year);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#eqid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteenquiryqualification/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
               $("#eqid").val("");
               $("#ddemail").val("");
               $("#ddspecialization").val("");
               $("#un").val("");
               $("#per").val("");
               $("#py").val("");
            }

</script> 
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/CounselorMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Enquiry Qualification</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Enquiry Qualification-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary">Enquiry Qualification ID:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Email:</label></small></div>
                            <div class="col-md-6 img-rounded"><input type="text"  id="eqid" class="form-control" placeholder="ENQUIRY QUALIFICATION ID"></div>
                          <div class="col-md-6 img-rounded"> 
                               <select id="ddemail" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Email</option>
                               </select>
                           </div> 
                        </div>
                        <div class="form-group">
                             <div class="col-md-6"><small><label class="text-primary">Qualification</label></small></div>
                             <div class="col-md-6"><small><label class="text-primary">Specialization:</label></small></div>
                             <div class="col-md-6 img-rounded"> 
                               <select id="ddqualification" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Qualification</option>
                               </select>
                           </div> 
                             <div class="col-md-6 img-rounded"> 
                               <select id="ddspecialization" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Specialization</option>
                               </select>
                           </div> 
                        </div>
                        <div class="form group">
                            <div class="col-md-4"><small><label class="text-primary">University:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Percentage:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Passing Year:</label></small></div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="un" class="form-control"  placeholder="UNIVERSITY"></div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="per" class="form-control"  placeholder="PERCENTAGE"></div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="py" class="form-control"  placeholder="PASSING YEAR"></div>
                        </div>
                    </form>
                   </div>
                <div class="modal-footer">
                <button type="button" id="btndelete" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdate" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
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
                          <th class="text-center">Enquiry Qualification ID</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Specialization</th>
                          <th class="text-center">University</th>
                          <th class="text-center">Percentage</th> 
                          <th class="text-center">Passing Year</th>
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