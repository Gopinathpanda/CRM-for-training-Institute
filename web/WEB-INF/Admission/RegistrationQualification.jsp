<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
    $(document).ready(function(){
        Display();
        DisplayQualification();
        DisplayEmailID();
        $("#addqualification").click(function(){
            ClearDataQualification();
        $("#myModalqualification").modal("show");
            GetMaxQualification();
         });
         $("#ddqualification").change(function(){
             FetchSpecializations();
        });
         $("#btnsubmitqualification").click(function(){
                  addQualification();
                  ClearDataQualification();
                  $("#myModalqualification").modal("hide");
                  Display();
                  }); 
                        });
           function GetMaxQualification()
                {
                $.get("/CRM_Student/getregdqualificationid",function(data){
                 $("#rqid").val(data);
                });                   
                }
                 function DisplayEmailID(){
                $.ajax({
                url:'/CRM_Student/displayprofile',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddemailid").empty();
               $("#ddemailid").append("<option  selected disabled>Select Email</option>");
                $.each(data,function(i,d){
               $("#ddemailid").append("<option  value="+d.registration_id+">"+d.email+"</option>");
                });
                }
                });
                }  
                 function DisplayQualification(){
                $.ajax({
                url:'/CRM_Student/displayqualification',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddqualification").empty();
                  $("#ddqualification").append("<option  selected disabled>Select Qualification</option>");
                    $.each(data,function(i,d){
                        $("#ddqualification").append("<option  value="+d.qualification_id+">"+d.qualification+"</option>");
                    });
                }
                });
                 }

       function FetchSpecializations(){
         
          var qid=$("#ddqualification").val();
      
       
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
            function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayregqualification',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.registration_qualification_id+"</td><td>"+d.email+"</td><td>"+d.specialization+"</td><td>"+d.university+"</td><td>"+d.percentage+"</td><td>"+d.passing_year+"</td><td>");
});
    }
    });
     }
     function addQualification(){
                var rqid=$("#rqid").val();
                var rid=$("#ddemailid").val();
                var sp=$("#ddspecialization").val();
                var un=$("#un").val();
                var pe=$("#pe").val();
                var py=$("#py").val();
                var data={"registration_qualification_id":rqid,"registration_id":rid,"specialization_id":sp,"university":un,"percentage":pe,"passing_year":py};
                   $.ajax({
                    url:'/CRM_Student/addregdqualification',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function ClearDataQualification()
       {
           $("#rqid").val("");
           $("#ddemailid").val("");
           $("#ddspecialization").val("");
           $("#un").val("");
           $("ddqualification").val("");
           $("#pe").val("");
           $("#py").val("");
        }

</script>
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
 <div class="container">
         <a id="addqualification" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Registration Qualification</a>
         
           <div class="row">
           <div id="myModalqualification" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Registration qualification-Form</center></h3>
                   </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-6"><small><label class="text-primary">Registration Qualification ID:</label></small></div>
                                <div class="col-md-6"><small><label class="text-primary">Student Email:</label></small></div>
                                 
                                <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="rqid" placeholder=" QUALIFICATION ID"></div>
                                <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddemailid" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Email</option>
                                    </select>
                                </div>
                                
                            </div>
                            <div class="form-group">
                                <div class="col-md-6"><small><label class="text-primary">Qualification:</label></small></div>
                                <div class="col-md-6"><small><label class="text-primary">Specialization:</label></small></div>
                                 <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddqualification" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Qualification</option>
                                    </select>
                                </div>
                                 <div class="col-md-6 img-rounded"> 
                                    <select type="text" id="ddspecialization" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Specialization</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4" ><small><label class="text-primary"> University Name:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">  Percentage:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary"> Passing Year:</label></small></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="un" placeholder="UNIVERSITY"></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="pe" placeholder="PERCENTAGE"></div>
                                <div class="col-md-4 img-rounded"> <input type="text" class="form-control" id="py" placeholder="PASSING YEAR"></div>
                            </div>    
                        </form>
                    </div>
                <div class="modal-footer">
                <button type="button" id="btnsubmitqualification" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Submit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Close</button>
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
                          <th class="text-center">Registration Qualification ID</th>
                          <th class="text-center">Student Email</th>
                          <th class="text-center">Specialization</th>
                          <th class="text-center">University</th>  
                          <th class="text-center">Percentage</th>  
                          <th class="text-center">Passing Year</th>  
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