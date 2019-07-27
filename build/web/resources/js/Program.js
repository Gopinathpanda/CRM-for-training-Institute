$(document).ready(function(){
            
            DisplayTechnology();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxProgram();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addProgram();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateProgram();
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
                function GetMaxProgram()
                {
                $.get("/CRM_Student/getprogramid",function(data){
                 $("#pi").val(data);
                });                   
                }
               function DisplayTechnology(){
                $.ajax({
                url:'/CRM_Student/displaytechnology',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddtechnology").empty();
$.each(data,function(i,d){
    $("#ddtechnology").append("<option  value="+d.technology_id+">"+d.technology_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayprogram',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.program_id+"</td><td>"+d.course_id+"</td><td>"+d.course_name+"</td><td>"+d.duration_course+"</td><td>"+d.technology_name+"</td><td><input type='button' value='View' onclick='View("+d.program_id+")'></td></tr>");
});
    }
    });
     }
                function addProgram(){
                var tid=$("#ddtechnology").val();
                var pi=$("#pi").val();
                var ci=$("#ci").val();
                var cn=$("#cn").val();
                var d=$("#du").val();
                var data={"program_id":pi,"course_id":ci,"course_name":cn,"duration_course":d,"technology_id":tid};
                $.ajax({
                    url:'/CRM_Student/addprogram',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateProgram(){
                 var tid=$("#ddtechnology").val();
                var pi=$("#pi").val();
                var ci=$("#ci").val();
                var cn=$("#cn").val();
                var d=$("#du").val();
                var data={"program_id":pi,"course_id":ci,"course_name":cn,"duration_course":d,"technology_id":tid};
                $.ajax({
                    url:'/CRM_Student/updateprogram',
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
                    url:'/CRM_Student/displayprogram/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#pi").val(d.program_id);
                     $("#ci").val(d.course_id);
                     $("#cn").val(d.course_name);
                     $("#du").val(d.duration_course);
                     $("#ddtechnology").val(d.technology_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#pi").val();
                    $.ajax({
                    url:'/CRM_Student/deleteprogram/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#pi").val("");
                 $("#ci").val("");
                 $("#cn").val("");
                 $("#du").val("");
                  $("#ddtechnology").val("");
            }




