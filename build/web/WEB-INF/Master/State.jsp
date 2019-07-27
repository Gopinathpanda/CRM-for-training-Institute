<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>

<script>
      $(document).ready(function(){
            $("#snc").css("color", "red");
            $("#snc").html("**please fill State name");
            var sn_err = true;
            $("#sn").keyup(function () {
            sn_check();
            });
            function sn_check()
            {
                var sn = $("#sn").val();
                if (sn.length =="") {
                    $("#snc").show();
                    $("#snc").html("**please fill State name");
                    $("#snc").focus();
                    $("#snc").css("color", "red");
                    sn_err = false;
                    return false;
                }
                else {
                    $("#snc").hide();
                }
            }
            Display();
            Duplicate();
            $("#btnadd").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxState();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addState();
                  ClearData();
                  Display();
                 $("#myModal").modal("hide");
                 Display();
                   });
                       $("#btnupdate").click(function(){
                       if (sn_err == true) {
                       updateState();
                       ClearData();
                       $("#myModal").modal("hide");
                       Display();
                       return true;
                       }
                       else {
                       return false;
                       }
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
                function GetMaxState()
                {
                $.get("/CRM_Student/getstateid",function(data){
                 $("#si").val(data);
                });                   
                }
                function Display(){
                $.getJSON("/CRM_Student/display",function(data){
                $("#tbldata").empty();
                   $.ajax({
                    url:'/CRM_Student/display',
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                    $("#tbldata").empty();
                    $.each(data,function(i,d){
                    $("#tbldata").append("<tr><td>"+d.state_id+"</td><td>"+d.state_name+"</td><td>\n\
                    <input type='button' value='View' onclick='View("+d.state_id+")'></td></tr>"); });
                    }
                    });
                   });
                   }
                function addState(){
                var i=$("#si").val();
                var n=$("#sn").val();
                var data={"state_id":i,"state_name":n};
                $.ajax({
                    url:'/CRM_Student/add',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("State name already exists");
                   }
                });
            }
            function updateState(){
                var i=$("#si").val();
                var n=$("#sn").val();
                var data={"state_id":i,"state_name":n};
                $.ajax({
                    url:'/CRM_Student/update',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     Display();
                    }
                });
              }
            function View(i){
            $("#snc").hide();
            $("#btnsubmit").hide();
            $("#btnupdate").show();
            $("#btndelete").show();
            $.ajax({
                    url:'/CRM_Student/display/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#si").val(d.state_id);
                     $("#sn").val(d.state_name);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#si").val();
                    $.ajax({
                    url:'/CRM_Student/delete/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
        
        $("#si").val("");
                 $("#sn").val("");
            }
            function Duplicate(){
                $("#sn").on('keyup',function(){
                    var sname=$(this);
                });
            }
             
     </script>
</head> 
<body class="cbp-spmenu-push">
<%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
        <button id="btnadd" type="button" class="btn btn-large btn-primary" >Add State</button>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>State-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary" >State ID:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">State Name:</label></small></div>
                            <div class="col-md-6 img-rounded"><input type="text"  id="si" class="form-control" placeholder="STATE ID" required></div>
                            <div class="col-md-6 img-rounded"><input type="text" id="sn" class="form-control"  placeholder="STATE NAME" required></div>
                            <div class="col-md-6"><h5 id="sic"></h5></div><div class="col-md-6"><h5 id="snc"></h5></div>
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
                           <th class="text-center">State ID</th>
                          <th class="text-center">State Name</th>
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