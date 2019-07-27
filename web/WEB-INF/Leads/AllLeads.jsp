<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
        DisplayQualification();
        DisplayCity();
        DisplayLeadSource();
        Display();
        $("#btnnewlead").click(function(){
            ClearData();
        $("#myModal").modal("show");
            GetMaxLead();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
            
        });
            $("#ddqualifications").change(function(){
             FetchSpecializations();
            
        }); 
           $("#ddcity").change(function(){
             FetchLocation();
            
        });
        $("#btnsubmit").click(function(){
                  addLead();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                  $("#btnupdate").click(function(){
                       updateLead();
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
                             $("#myModal").modal("hide");
                             $("#btnsubmit").show();
                             $("#btnupdate").hide();
                             }
                             $("#myModal").modal("hide");
                            });
        });
        
        function GetMaxLead()
                {
                $.get("/CRM_Student/getleadid",function(data){
                 $("#txtleadid").val(data);
                });                   
                }
        
         function DisplayQualification(){
                $.ajax({
                url:'/CRM_Student/displayqualification',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddqualifications").empty();
                  $("#ddqualifications").append("<option  selected disabled>Select Qualification</option>");
                    $.each(data,function(i,d){
                        $("#ddqualifications").append("<option  value="+d.qualification_id+">"+d.qualification+"</option>");
                    });
                }
                });
                 }

       function FetchSpecializations(){
         
          var qid=$("#ddqualifications").val();
      
       
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
           function DisplayCity(){
                $.ajax({
                url:'/CRM_Student/displaycity',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcity").empty();
               $("#ddcity").append("<option  selected disabled>Select City</option>");
$.each(data,function(i,d){
    $("#ddcity").append("<option  value="+d.city_id+">"+d.city_name+"</option>");
});
    }
    });
     }
    function FetchLocation(){
         
          var cid=$("#ddcity").val();
      
       
            $.ajax({
                    url:'/CRM_Student/displaylocationbycity/'+cid,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                     $("#ddlocations").empty();
                        $("#ddlocations").append("<option selected disabled>Select Location</option>");

                    $.each(data,function(i,d){
                      
                     $("#ddlocations").append("<option  value="+d.location_id+">"+d.location_name+"</option>");
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
     function addLead(){
                var lid=$("#txtleadid").val();
                var cn=$("#txtname").val();
                var email=$("#txtemail").val();
                 var mobile=$("#txtmobile").val();
                  var s=$("#ddspecialization").val();
                   var ls=$("#ddsource").val();
                    var l=$("#ddlocations").val();
                     var add=$("#txtaddress").val();
                     
                var data={"lead_id":lid,"candidate_name":cn,"email":email,"address":add,"specialization_id":s,"source_id":ls,
                "mobile":mobile,"location_id":l};
                $.ajax({
                    url:'/CRM_Student/addlead',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateLead(){
                var lid=$("#txtleadid").val();
                var cn=$("#txtname").val();
                var email=$("#txtemail").val();
                 var mobile=$("#txtmobile").val();
                  var s=$("#ddspecialization").val();
                   var ls=$("#ddsource").val();
                    var l=$("#ddlocations").val();
                     var add=$("#txtaddress").val();
                var data={"lead_id":lid,"candidate_name":cn,"email":email,"address":add,"specialization_id":s,"source_id":ls,
                "mobile":mobile,"location_id":l};
                $.ajax({
                    url:'/CRM_Student/updatelead',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
                });
              }
            function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaylead',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.lead_id+"</td><td>"+d.candidate_name+"</td><td>"+d.email+"</td><td>"+d.mobile+"</td><td>"+d.location_name+"</td><td>"+d.address+"</td><td>"
    +d.specialization+"</td><td>"+d.source_name+"</td><td><input type='button' value='View' onclick='View("+d.lead_id+")'></td></tr>");
});
    }
    });
     }
     function View(i){
            $("#btnsubmit").hide();
            $("#btnupdate").show();
            $("#btndelete").show();
            $.ajax({
                    url:'/CRM_Student/displaylead/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                 $("#txtleadid").val(d.lead_id);
                 $("#txtname").val(d.candidate_name);
                 $("#txtemail").val(d.email);
                 $("#txtmobile").val(d.mobile);
                 $("#ddcity").val(d.city_id);
                 $("#ddqualifications").val(d.qualification_id);
                 $("#ddspecialization").append("<option selected value="+d.specialization_id+">"+d.specialization+"</option>");
                 $("#ddsource").val(d.source_id);
                 $("#ddlocations").append("<option selected value="+d.location_id+">"+d.location_name+"</option>");


        $("#txtaddress").val(d.address);
                 $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#txtleadid").val();
                    $.ajax({
                    url:'/CRM_Student/deletelead/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#txtleadid").val("");
                 $("#txtname").val("");
                 $("#txtemail").val("");
                 $("#txtmobile").val("");
                 $("#ddspecialization").val("");
                 $("#ddsource").val("");
                 $("#ddlocations").val("");
                 $("#txtaddress").val("");
                  $("#ddqualifications").val("");
                  $("#ddcity").append("<option selected disabled>Select City</option>");
            }

</script>
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/CounselorMenu.jsp" %>
<div id="page-wrapper">
 <div class="container">
         <a id="btnnewlead" role="button" class="btn btn-large btn-primary" data-toggle="modal">New Lead</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Lead-Form</center></h3>
                   </div>
                    <div>
                    <div class="modal-body">
                         <form class="form-horizontal">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Lead ID</label>
                                <div class="img-rounded"><input type="text" id="txtleadid" class="form-control"></div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Candidate Name</label>
                                <div class="img-rounded"><input type="text" id="txtname" class="form-control"></div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Email</label>
                                <div class="img-rounded"><input type="text" id="txtemail" class="form-control"></div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Mobile Number</label>
                                <div class="img-rounded"><input type="text" id="txtmobile" class="form-control"></div>
                            </div>
                        </div>
                        
                           <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Qualification</label>
                            <div class="img-rounded"><select type="text" id="ddqualifications" class="form-control">
                   <option selected="selected" disabled="disabled">Select Qualification</option>

                                </select>
                            </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Specialization</label>
                             <div class="img-rounded"> <select type="text" id="ddspecialization" class="form-control">
                   <option selected="selected" disabled="disabled">Select Specialization</option>
                                </select>
                             </div>
                            </div>
                        </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Lead Source</label>
                             <div class="img-rounded"> <select type="text" id="ddsource" class="form-control">
                   <option selected="selected" disabled="disabled">Select Source</option>
                                </select>
                             </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">City Name</label>
                                <div class="img-rounded"><select type="text" id="ddcity" class="form-control">
                                    <option selected="selected" disabled="disabled">Select City</option>
                                </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="text-primary">Location</label>
                                <div class="img-rounded"><select type="text" id="ddlocations" class="form-control">
                   <option selected="selected" disabled="disabled">Select Location</option>
                                </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="text-primary">Address</label>
                                <div class="img-rounded"><textarea type="text" id="txtaddress" class="form-control"></textarea></div>
                            </div>
                        </div>
                       </form>
                     </div>
                    </div>
                <div>
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
 </div>
        <div class="col-md-12">
        <div class="container-fluid">
            <div style="overflow-y: scroll;overflow-x:scroll;height: 470px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                    <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Lead ID</th>
                          <th class="text-center">Candidate Name</th>
                          <th class="text-center">Email</th>
                          <th class="text-center">Mobile</th>
                          <th class="text-center">Location Name</th>
                          <th class="text-center">Address</th>
                          <th class="text-center">Specialization Name</th>
                          <th class="text-center">Source Name</th>
                          <th class="text-center">Action</th>
                          
                       </tr>
                   </thead>
                   <tbody id="tbldata" class="text-center text-capitalize text-primary">
                   </tbody>
                </table>
            
            </div>
      </div>
        </div>
    </div>
        <%@include file="../common/footer.jsp" %>

</body>
</html>