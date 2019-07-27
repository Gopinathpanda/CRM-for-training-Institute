<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
            $("#cnc").css("color", "red");
            $("#cnc").html("**please fill City name");
            $("#snc").css("color", "red");
            $("#snc").html("**please fill State name");
            var cn_err = true;
            var sn_err = true;
            $("#cn").keyup(function () {
            ci_check();
            });
            function ci_check()
            {
                var cn = $("#cn").val();
                if (cn.length == "") {
                    $("#cnc").show();
                    $("#cnc").html("**please fill  City name");
                    $("#cnc").focus();
                    $("#cnc").css("color", "red");
                    cn_err = false;
                    return cn_err;
                }
                else {
                    $("#cnc").hide();
                     }
            }

            $("#ddstate").click(function () {
                 sn_check();

            });
            function sn_check()
            {
                var sn = $("#ddstate");
                if (sn.val()=== "") {
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
            DisplayStates();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxCity();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addCity();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateCity();
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
                function GetMaxCity()
                {
                $.get("/CRM_Student/getcityid",function(data){
                 $("#cid").val(data);
                });                   
                }
               function DisplayStates(){
                $.ajax({
                url:'/CRM_Student/display',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddstate").empty();
$.each(data,function(i,d){
    $("#ddstate").append("<option  value="+d.state_id+">"+d.state_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaycity',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.city_id+"</td><td>"+d.city_name+"</td><td>"+d.state_name+"</td><td><input type='button' value='View' onclick='View("+d.city_id+")'></td></tr>");
});
    }
    });
     }
                function addCity(){
                var sid=$("#ddstate").val();
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"city_id":i,"city_name":n,"state_id":sid};
                $.ajax({
                    url:'/CRM_Student/addcity',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("City name is already exists");
                    
                    }
                });
            }
            function updateCity(){
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"city_id":i,"city_name":n};
                $.ajax({
                    url:'/CRM_Student/updatecity',
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
            $("#cnc").hide();
            $("#btnsubmit").hide();
            $("#btnupdate").show();
            $("#btndelete").show();
            $.ajax({
                    url:'/CRM_Student/displaycity/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#cid").val(d.city_id);
                     $("#cn").val(d.city_name);
                     $("#ddstate").val(d.state_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#cid").val();
                    $.ajax({
                    url:'/CRM_Student/deletecity/'+i,
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
                  $("#ddstate").val("");
            }



</script> 
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add City</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>City-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >City ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">City Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">State Name:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text"  id="cid" class="form-control" placeholder="CITY ID"></div>
                           <div class="col-md-4 img-rounded"><input type="text"  id="cn" class="form-control"  placeholder="CITY NAME"></div>
                           <div class="col-md-4 img-rounded"> 
                               <select id="ddstate" class="form-control">
                                   <option selected="selected" disabled="disabled">Select State</option>
                               </select>
                           </div>    
                           <div class="col-md-4"><h5></h5></div> <div class="col-md-4"><h5 id="cnc"></h5></div> <div class="col-md-4"><h5 id="snc"></h5></div>
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
                          <th class="text-center">City ID</th>
                          <th class="text-center">City Name</th>
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