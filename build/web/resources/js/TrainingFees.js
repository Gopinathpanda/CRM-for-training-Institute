$(document).ready(function(){
            
            DisplayCourses();
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
$.each(data,function(i,d){
    $("#ddcourse").append("<option  value="+d.program_id+">"+d.course_name+"</option>");
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
    $("#tbldata").append("<tr><td>"+d.fee_id+"</td><td>"+d.course_name+"</td><td>"+d.lumsum+"</td><td>"+d.installment+"</td><td>"+d.registration_amount+"</td><td><input type='button' value='View' onclick='View("+d.city_id+")'></td></tr>");
});
    }
    });
     }
                function addTrainingFees(){
                var cid=$("#ddcourse").val();
                var i=$("#fid").val();
                var l=$("#ls").val();
                var ins=$("#install").val();
                var r=$("#rm").val();
                var data={"fee_id":i,"lumsum":l,"installment":ins,"registration_amount":rm,"program_id":cid};
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
                var ins=$("#install").val();
                var r=$("#rm").val();
                var data={"fee_id":i,"lumsum":l,"installment":ins,"registration_amount":rm,"program_id":cid};
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
                     $("#install").val(d.installment);
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
                 $("#install").val("");
                 $("#rm").val("");
                  $("#ddcourse").val("");
            }



