<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>$(document).ready(function(){
            
            DisplayTopics();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxContent();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addContent();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateContent();
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
                function GetMaxContent()
                {
                $.get("/CRM_Student/getcontentid",function(data){
                 $("#cid").val(data);
                });                   
                }
               function DisplayTopics(){
                $.ajax({
                url:'/CRM_Student/displaytopics',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddtopic").empty();
$.each(data,function(i,d){
    $("#ddtopic").append("<option  value="+d.topic_id+">"+d.topic_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaycontent',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.content_id+"</td><td>"+d.content_name+"</td><td>"+d.topic_name+"</td><td><input type='button' value='View' onclick='View("+d.content_id+")'></td></tr>");
});
    }
    });
     }
                function addContent(){
                var sid=$("#ddtopic").val();
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"content_id":i,"content_name":n,"topic_id":sid};
                $.ajax({
                    url:'/CRM_Student/addcontent',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateContent(){
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"content_id":i,"content_name":n};
                $.ajax({
                    url:'/CRM_Student/updatecontent',
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
                    url:'/CRM_Student/displaycontent/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#cid").val(d.content_id);
                     $("#cn").val(d.content_name);
                     $("#ddtopic").val(d.topic_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#cid").val();
                    $.ajax({
                    url:'/CRM_Student/deletecontent/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#cid").val("");
                 $("#cn").val("");
                  $("#ddtopic").val("");
            }



</script> 
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Contents</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Contents-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >Content ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Content Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Topic ID:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="cid" placeholder="CONTENT ID"></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="cn"  placeholder="CONTENT NAME"></div>
                          <div class="col-md-4 img-rounded"> 
                               <select id="ddtopic" class="form-control">
                                   <option selected="selected" disabled="disabled">Select Topic</option>
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
                          <th class="text-center">Content ID</th>
                          <th class="text-center">Content Name</th>
                          <th class="text-center">Topic Name</th>
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