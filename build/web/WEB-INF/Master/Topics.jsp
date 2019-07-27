<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>$(document).ready(function(){
            
            DisplayModules();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxTopics();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addTopics();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateTopics();
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
                function GetMaxTopics()
                {
                $.get("/CRM_Student/gettopicid",function(data){
                 $("#tid").val(data);
                });                   
                }
               function DisplayModules(){
                $.ajax({
                url:'/CRM_Student/displaymodule',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddmodule").empty();
$.each(data,function(i,d){
    $("#ddmodule").append("<option  value="+d.module_id+">"+d.module_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaytopics',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.topic_id+"</td><td>"+d.topic_name+"</td><td>"+d.module_name+"</td><td><input type='button' value='View' onclick='View("+d.topic_id+")'></td></tr>");
});
    }
    });
     }
                function addTopics(){
                var sid=$("#ddmodule").val();
                var i=$("#tid").val();
                var n=$("#tn").val();
                var data={"topic_id":i,"topic_name":n,"module_id":sid};
                $.ajax({
                    url:'/CRM_Student/addtopic',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateTopics(){
                var i=$("#tid").val();
                var n=$("#tn").val();
                var data={"topic_id":i,"topic_name":n};
                $.ajax({
                    url:'/CRM_Student/updatetopic',
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
                    url:'/CRM_Student/displaytopics/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#tid").val(d.topic_id);
                     $("#tn").val(d.topic_name);
                     $("#ddmodule").val(d.module_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#tid").val();
                    $.ajax({
                    url:'/CRM_Student/deletetopic/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#tid").val("");
                 $("#tn").val("");
                  $("#ddmodule").val("");
            }



</script> 
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Topics</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Topic-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >Topic ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Topic Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Module ID:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="tid" placeholder="TOPIC ID"></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="tn"  placeholder="TOPIC NAME"></div>
                          <div class="col-md-4 img-rounded"> 
                               <select id="ddmodule" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Module</option>
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
                          <th class="text-center">Topic ID</th>
                          <th class="text-center">Topic Name</th>
                          <th class="text-center">Module Name</th>
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