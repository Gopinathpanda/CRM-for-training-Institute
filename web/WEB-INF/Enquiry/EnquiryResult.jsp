<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
            DisplayEnquiry();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxEnquiryResult();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });
              
                  $("#btnsubmit").click(function(){
                  addEnqResult();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateEnqResult();
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
                function GetMaxEnquiryResult()
                {
                $.get("/CRM_Student/getenquiryresultid",function(data){
                 $("#erid").val(data);
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
        url:'/CRM_Student/displayenquiryresult',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.enquiry_result_id+"</td><td>"+d.email+"</td><td>"+d.enquiry_by+"</td><td>"+d.description+"</td><td>"+d.status+"</td><td><input type='button' value='View' onclick='View("+d.enquiry_result_id+")'></td></tr>");
});
    }
    });
     }
               function addEnqResult(){
                var ei=$("#erid").val();
                var email=$("#ddemail").val();
                var eb=$("#eb").val();
                var d=$("#des").val();
                var s=$("#sts").val();
                var data={"enquiry_result_id":ei,"enquiry_id":email,"enquiry_by":eb,"description":d,"status":s};
                $.ajax({
                    url:'/CRM_Student/addenquiryresult',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateEnqResult(){
                  var ei=$("#erid").val();
                var email=$("#ddemail").val();
                var eb=$("#eb").val();
                var d=$("#des").val();
                var s=$("#sts").val();
                var data={"enquiry_result_id":ei,"enquiry_id":email,"enquiry_by":eb,"description":d,"status":s};
                $.ajax({
                    url:'/CRM_Student/updateenquiryresult',
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
                    url:'/CRM_Student/displayenquiryresult/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
               $("#erid").val(d.enquiry_result_id);
                $("#ddemail").append("<option selected value="+d.enquiry_id+">"+d.email+"</option>");
                $("#eb").val(d.enquiry_by);
                $("#des").val(d.description);
                $("#sts").val(d.status);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#erid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteenquiryresult/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
               $("#erid").val("");
               $("#ddemail").val("");
               $("#eb").val("");
               $("#des").val("");
               $("#sts").val("");
            }

</script> 
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/CounselorMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Enquiry Result</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Enquiry Result-Form</center></h3>
                   </div>
                   <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary">Enquiry Result ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Email:</label></small></div>
                           <div class="col-md-4"><small><label class="text-primary">Enquiry By:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text"  id="erid" class="form-control" placeholder="ENQUIRY RESULT ID"></div>
                          <div class="col-md-4 img-rounded"> 
                               <select id="ddemail" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Email</option>
                               </select>
                           </div>
                            <div class="col-md-4 img-rounded"><input type="text"  id="eb" class="form-control"  placeholder="ENQUIRY BY"></div>
                        </div>
                        <div class="form group">
                            <div class="col-md-6"><small><label class="text-primary">Status:</label></small></div>
                           <div class="col-md-6"><small><label class="text-primary">Description:</label></small></div>
                           <div class="col-md-6 img-rounded"><input type="text"  id="sts" class="form-control"  placeholder="STATUS"></div>
                           <div class="col-md-6 img-rounded"><textarea rows="3" cols="31" id="des"></textarea></div>
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
        <div class="col-md-12">
            <div style="overflow-y: scroll;height: 470px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Enquiry Result ID</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Enquiry By</th>
                          <th class="text-center">Description</th>
                          <th class="text-center">Status</th> 
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