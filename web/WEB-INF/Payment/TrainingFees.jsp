<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRM Student</title>
        <%@include  file="../common/files.jsp" %>
        <script>
            $(document).ready(function(){
            
            DisplayCourses();
            DisplayMode();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxFees();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addTrainingFees();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateTrainingFees();
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
                function GetMaxFees()
                {
                $.get("/CRM_Student/gettrainingfeesid",function(data){
                 $("#fid").val(data);
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
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaytrainingfees',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.fee_id+"</td><td>"+d.course_name+"</td><td>"+d.lumsum+"</td><td>"+d.mode_name+"</td><td>"+d.registration_amount+"</td><td><input type='button' value='View' onclick='View("+d.fee_id+")'></td></tr>");
});
    }
    });
     }
                function addTrainingFees(){
                var cid=$("#ddcourse").val();
                var i=$("#fid").val();
                var l=$("#ls").val();
                var ins=$("#ddmode").val();
                var r=$("#rm").val();
                var data={"fee_id":i,"lumsum":l,"fee_mode_id":ins,"registration_amount":r,"program_id":cid};
                $.ajax({
                    url:'/CRM_Student/addtrainingfees',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateTrainingFees(){
               var cid=$("#ddcourse").val();
                var i=$("#fid").val();
                var l=$("#ls").val();
                var ins=$("#ddmode").val();
                var r=$("#rm").val();
                var data={"fee_id":i,"lumsum":l,"fee_mode_id":ins,"registration_amount":r,"program_id":cid};
                $.ajax({
                    url:'/CRM_Student/updatetrainingfees',
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
                    url:'/CRM_Student/displaytrainingfees/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#fid").val(d.fee_id);
                     $("#ls").val(d.lumsum);
                     $("#ddmode").val(d.fee_mode_id);
                     $("#rm").val(d.registration_amount);
                     $("#ddcourse").val(d.program_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#fid").val();
                    $.ajax({
                    url:'/CRM_Student/deletetrainingfees/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#fid").val("");
                 $("#ls").val("");
                 $("#ddmode").val("");
                 $("#rm").val("");
                  $("#ddcourse").val("");
            }




        </script> 
    </head>
    <body class="cbp-spmenu-push">
    <%@include file="../common/AccountantMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Training Fees</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Training Fees-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >Fee ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Course Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Lumsum:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text"  id="fid" class="form-control" placeholder="FEE ID"></div>
                            <div class="col-md-4 img-rounded"> 
                               <select id="ddcourse" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Course</option>
                               </select>
                           </div> 
                           <div class="col-md-4 img-rounded"><input type="text"  id="ls" class="form-control"  placeholder="LUMSUM AMOUNT"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary" >Mode_name:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Registration Amount:</label></small></div>
                             <div class="col-md-6 img-rounded"> 
                               <select id="ddmode" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Mode</option>
                               </select>
                           </div> 
                              <div class="col-md-6 img-rounded"><input type="text"  id="rm" class="form-control" placeholder="REGISTRATION AMOUNT"></div>
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
                          <th class="text-center">Fee ID</th>
                          <th class="text-center">Course Name</th>
                          <th class="text-center">Lumsum</th>
                          <th class="text-center">Mode</th>
                          <th class="text-center">Registration Amount</th>
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
