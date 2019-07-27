$(document).ready(function(){
            DisplayCourses();
            DisplayLeadSource();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxEnquiry();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addEnquiry();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateEnquiry();
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
                function GetMaxEnquiry()
                {
                $.get("/CRM_Student/getenquiryid",function(data){
                 $("#eid").val(data);
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
      function Display(){
    ajax({
        url:'/CRM_Student/displayenquiry',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.enquiry_id+"</td><td>"+d.candidate_name+"</td><td>"+d.email+"</td><td>"+d.mobile_no+"</td><td>"+d.dob+"</td><td>"+d.address+""+d.course_name+"</td><td>"+d.source_name+"</td><td>"+d.enquiry_date+"</td><td></td><td><input type='button' value='View' onclick='View("+d.enquiry_id+")'></td></tr>");
});
    }
    });
     }
                function addEnquiry(){
                var ei=$("#eid").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
                var mobile=$("#mobile").val();
                var dob=$("#dob").val();
                var c=$("#ddcourse").val();
                var s=$("#ddsource").val();
                var ed=$("#ed").val();
                var ad=$("#address").val();
                var data={"enquiry_id":ei,"candidate_name":cn,"email":email,"mobile_no":mobile,"dob":dob,"program_id":c,"source_id":s,"enquiry_date":ed,"address":ad};
                $.ajax({
                    url:'/CRM_Student/addenquiry',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateEnquiry(){
                 var ei=$("#eid").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
                var mobile=$("#mobile").val();
                var dob=$("#dob").val();
                var c=$("#ddcourse").val();
                var s=$("#ddsource").val();
                var ed=$("#ed").val();
                var ad=$("#address").val();
                var data={"enquiry_id":ei,"candidate_name":cn,"email":email,"mobile_no":mobile,"dob":dob,"program_id":c,"source_id":s,"enquiry_date":ed,"address":ad};
                $.ajax({
                    url:'/CRM_Student/updateenquiry',
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
                    url:'/CRM_Student/displayenquiry/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                $("#eid").val(d.enquiry_id);
                $("#cn").val(d.candidate_name);
                $("#email").val(d.email);
                $("#mobile").val(d.email);
                $("#dob").val(d.dob);
                $("#ddcourse").val(d.program_id);
                $("#ddsource").val(d.source_id);
                $("#ed").val(d.enquiry_date);
                $("#address").val(d.address);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#eid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteenquiry/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                $("#eid").val("");
                $("#cn").val("");
                $("#email").val("");
                $("#mobile").val("");
                $("#dob").val("");
                $("#ddcourse").val("");
                $("#ddsource").val("");
                $("#ed").val("");
                $("#address").val("");
            }



