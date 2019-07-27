<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>$(document).ready(function(){
            
            DisplayName();
            DisplayContent();            
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxAssign();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addAssign();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateAssign();
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
                function GetMaxAssign()
                {
                $.get("/CRM_Student/getmentorassignid",function(data){
                 $("#aid").val(data);
                });                   
                }
               function DisplayName(){
                $.ajax({
                url:'/CRM_Student/displaymentor',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddname").empty();
$.each(data,function(i,d){
    $("#ddname").append("<option  value="+d.mentor_id+">"+d.full_name+"</option>");
});
    }
    });
     }
      function DisplayContent(){
                $.ajax({
                url:'/CRM_Student/displaycontent',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcontent").empty();
$.each(data,function(i,d){
    $("#ddcontent").append("<option  value="+d.content_id+">"+d.content_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaymentorassign',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.assigned_mentor_id+"</td><td>"+d.full_name+"</td><td>"+d.content_name+"</td><td><input type='button' value='View' onclick='View("+d.assigned_mentor_id+")'></td></tr>");
});
    }
    });
     }
                function addAssign(){
                var cid=$("#ddcontent").val();
                var i=$("#aid").val();
                var n=$("#ddname").val();
                var data={"assigned_mentor_id":i,"mentor_id":n,"content_id":cid};
                $.ajax({
                    url:'/CRM_Student/addmentorassign',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateAssign(){
                var cid=$("#ddcontent").val();
                var i=$("#aid").val();
                var n=$("#ddname").val();
                var data={"assigned_mentor_id":i,"mentor_id":n,"content_id":cid};
                $.ajax({
                    url:'/CRM_Student/updatementorassign',
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
                    url:'/CRM_Student/displaymentorassign/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#aid").val(d.assigned_mentor_id);
                     $("#ddname").append("<option  value="+d.mentor_id+">"+d.full_name+"</option>");
                     $("#ddcontent").append("<option  value="+d.content_id+">"+d.content_name+"</option>");
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#aid").val();
                    $.ajax({
                    url:'/CRM_Student/deletementorassign/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#aid").val("");
                 $("#ddname").val("");
                  $("#ddcontent").val("");
            }



</script> 
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Assigned Mentors</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Assigned Mentors-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >Assigned Mentor ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary"> Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Content Name:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="aid" placeholder="ASSIGNED MENTOR ID"></div>
                            <div class="col-md-4 img-rounded"> 
                               <select id="ddname" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Name</option>
                               </select>
                           </div>  
                          <div class="col-md-4 img-rounded"> 
                               <select id="ddcontent" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Content</option>
                               </select>
                           </div>  
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
                          <th class="text-center">Assigned Mentor ID</th>
                          <th class="text-center"> Name</th>
                          <th class="text-center">Content Name</th>
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