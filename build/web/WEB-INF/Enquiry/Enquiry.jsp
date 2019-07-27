<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
            DisplayCourses();
            DisplayLeadSource();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxEnquiry();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addEnquiry();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateEnquiry();
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
                function GetMaxEnquiry()
                {
                $.get("/CRM_Student/getenquiryid",function(data){
                 $("#eid").val(data);
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
      function DisplayLeadSource(){
                $.ajax({
                url:'/CRM_Student/displayleadsource',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddsource").empty();
               $("#ddsource").append("<option  selected disabled>Select Lead Source</option>");
$.each(data,function(i,d){
    $("#ddsource").append("<option  value="+d.source_id+">"+d.source_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayenquiry',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.enquiry_id+"</td><td>"+d.candidate_name+"</td><td>"+d.email+"</td><td>"+d.mobile_no+"</td><td>"+d.dob+"</td><td>"+d.address+"</td><td>"+d.course_name+"</td><td>"+d.source_name+"</td><td>"+d.enquiry_date+"</td><td><input type='button' value='View' onclick='View("+d.enquiry_id+")'></td></tr>");
});
    }
    });
     }
               function addEnquiry(){
                var ei=$("#eid").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
                var mobile=$("#mobile").val();
                var dob=$("#dob").val();
                var c=$("#ddcourse").val();
                var s=$("#ddsource").val();
                var ed=$("#ed").val();
                var ad=$("#address").val();
                var data={"enquiry_id":ei,"candidate_name":cn,"email":email,"mobile_no":mobile,"dob":dob,"program_id":c,"source_id":s,"enquiry_date":ed,"address":ad};
                $.ajax({
                    url:'/CRM_Student/addenquiry',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateEnquiry(){
                 var ei=$("#eid").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
                var mobile=$("#mobile").val();
                var dob=$("#dob").val();
                var c=$("#ddcourse").val();
                var s=$("#ddsource").val();
                var ed=$("#ed").val();
                var ad=$("#address").val();
                var data={"enquiry_id":ei,"candidate_name":cn,"email":email,"mobile_no":mobile,"dob":dob,"program_id":c,"source_id":s,"enquiry_date":ed,"address":ad};
                $.ajax({
                    url:'/CRM_Student/updateenquiry',
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
                    url:'/CRM_Student/displayenquiry/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                $("#eid").val(d.enquiry_id);
                $("#cn").val(d.candidate_name);
                $("#email").val(d.email);
                $("#mobile").val(d.mobile_no);
                $("#dob").val(d.dob);
                $("#ddcourse").val(d.program_id);
                $("#ddsource").val(d.source_id);
                $("#ed").val(d.enquiry_date);
                $("#address").val(d.address);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#eid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteenquiry/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                $("#eid").val("");
                $("#cn").val("");
                $("#email").val("");
                $("#mobile").val("");
                $("#dob").val("");
                $("#ddcourse").val("");
                $("#ddsource").val("");
                $("#ed").val("");
                $("#address").val("");
            }

</script> 
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/CounselorMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Enquiry</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Enquiry-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary">Enquiry ID:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Candidate Name:</label></small></div>
                            <div class="col-md-6 img-rounded"><input type="text"  id="eid" class="form-control" placeholder="ENQUIRY ID"></div>
                           <div class="col-md-6 img-rounded"><input type="text"  id="cn" class="form-control"  placeholder="CANDIDATE NAME"></div>
                        </div>
                        <div class="form group">
                            <div class="col-md-4"><small><label class="text-primary">Email ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Mobile:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Date Of Birth:</label></small></div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="email" class="form-control"  placeholder="EMAIL"></div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="mobile" class="form-control"  placeholder="MOBILE"></div>
                            <div class="col-md-4 img-rounded"><input type="date"  id="dob" class="form-control"  placeholder="DATE OF BIRTH"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary">Course Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Source Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Enquiry Date:</label></small></div>
                            <div class="col-md-4 img-rounded"> 
                               <select id="ddcourse" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Course</option>
                               </select>
                           </div> 
                            <div class="col-md-4 img-rounded"> 
                               <select id="ddsource" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Source</option>
                               </select>
                           </div> 
                            <div class="col-md-4 img-rounded"><input type="date"  id="ed" class="form-control"  placeholder="ENQUIRY DATE"></div>
                        </div>
                         <div class="form-group">
                             <div class="col-md-12"><small><label class="text-primary">Address:</label></small></div>
                            <div class="col-md-12 img-rounded"><textarea rows="3" cols="73" id="address"></textarea></div>
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
                          <th class="text-center">Enquiry ID</th>
                          <th class="text-center">Candidate Name</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Mobile</th>
                          <th class="text-center">Date Of Birth</th>
                          <th class="text-center">Address</th> 
                          <th class="text-center">Course Name</th>
                          <th class="text-center">Source name</th>
                          <th class="text-center">Enquiry Date</th>
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