<%-- 
    Document   : FeeMode
    Created on : May 8, 2019, 12:28:24 PM
    Author     : panda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include  file="../common/files.jsp" %>
    <script>
      $(document).ready(function(){
            Display();
            $("#btnadd").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxMode();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addMode();
                  ClearData();
                  Display();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateMode();
                       ClearData();
                       $("#myModal").modal("hide");
                       Display();
                       });
                             $("#btndelete").click(function(){
                             if(confirm("Do you wants to Delete?"))
                             {
                             Delete();
                             ClearData();
                             Display();
                             $("#btnsubmit").show();
                             $("#btnupdate").hide();
                             }
                             $("#myModal").modal("hide");
                            });
                });
                function GetMaxMode()
                {
                $.get("/CRM_Student/getfeemodeid",function(data){
                 $("#mi").val(data);
                });                   
                }
                
                
               function Display(){
                $.getJSON("/CRM_Student/displayfeemode",function(data){
                $("#tbldata").empty();
                   $.ajax({
                    url:'/CRM_Student/displayfeemode',
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                    $("#tbldata").empty();
                    $.each(data,function(i,d){
                    $("#tbldata").append("<tr><td>"+d.fee_mode_id+"</td><td>"+d.mode_name+"</td><td>\n\
                    <input type='button' value='View' onclick='View("+d.fee_mode_id+")'></td></tr>"); });
                    }
                    });
                   });
                   }
                function addMode(){
                var i=$("#mi").val();
                var n=$("#mn").val();
                var data={"fee_mode_id":i,"mode_name":n};
                $.ajax({
                    url:'/CRM_Student/addfeemode',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateMode(){
                var i=$("#mi").val();
                var n=$("#mn").val();
               var data={"fee_mode_id":i,"mode_name":n};
                $.ajax({
                    url:'/CRM_Student/updatefeemode',
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
                    url:'/CRM_Student/displayfeemode/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#mi").val(d.fee_mode_id);
                     $("#mn").val(d.mode_name);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#mi").val();
                    $.ajax({
                    url:'/CRM_Student/deletefeemode/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
        
        $("#mi").val("");
                 $("#mn").val("");
            }


     </script>
</head> 
<body class="cbp-spmenu-push">
<%@include file="../common/AccountantMenu.jsp" %>
<div id="page-wrapper">
   
    <div class="container">

        <button id="btnadd" type="button" class="btn btn-large btn-primary" >Add Fee Mode</button>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Fee Mode-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary" >Mode ID:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Mode Name:</label></small></div>
                           <div class="col-md-6 img-rounded"><input type="text"  id="mi" class="form-control" placeholder="MODE ID"></div>
                           <div class="col-md-6 img-rounded"><input type="text" id="mn" class="form-control"  placeholder="MODE NAME"></div>
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
                           <th class="text-center">Mode ID</th>
                          <th class="text-center">Mode Name</th>
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
