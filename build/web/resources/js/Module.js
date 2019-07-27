$(document).ready(function(){
            DisplayProgram();
            Display();
            $("#add").click(function(){
            $("#myModal").modal("show");
            GetMaxModule();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addModule();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateModule();
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
                function GetMaxModule()
                {
                $.get("/CRM_Student/getmoduleid",function(data){
                 $("#mi").val(data);
                });                   
                }
               function DisplayProgram(){
                $.ajax({
                url:'/CRM_Student/displayprogram',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddprogram").empty();
               $.each(data,function(i,d){
               $("#ddprogram").append("<option  value="+d.program_id+">"+d.course_name+"</option>");
               });
               }
               });
               }
            function Display(){
         $.ajax({
        url:'/CRM_Student/displaymodule',
        method:'get',
        contentType:'application/json',
        success:function(data){
       $("#tbldata").empty();
       $.each(data,function(i,d){
       $("#tbldata").append("<tr><td>"+d.module_id+"</td><td>"+d.module_name+"</td><td>"+d.duration_module+"</td><td>"+d.course_name+"</td><td><input type='button' value='View' onclick='View("+d.module_id+")'></td></tr>");
        });
        }
        });
        }
                function addModule(){
                var pid=$("#ddprogram").val();
                var i=$("#mi").val();
                var n=$("#mn").val();
                var d=$("#du").val();
                var data={"module_id":i,"module_name":n,"duration_module":d,"program_id":pid};
                $.ajax({
                    url:'/CRM_Student/addmodule',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                    alert("success");
                    }
                });
            }
            function updateModule(){
                 var pid=$("#ddprogram").val();
                var i=$("#mi").val();
                var n=$("#mn").val();
                var d=$("#du").val();
                var data={"module_id":i,"module_name":n,"duration_module":d,"program_id":pid};
                $.ajax({
                    url:'/CRM_Student/updatemodule',
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
                    url:'/CRM_Student/displaymodule/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#mi").val(d.module_id);
                     $("#mn").val(d.module_name);
                      $("#du").val(d.duration_module);
                     $("#ddprogram").val(d.program_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#mi").val();
                    $.ajax({
                    url:'/CRM_Student/deletemodule/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#mi").val("");
                 $("#mn").val("");
                  $("#du").val("");
                  $("#ddprogram").val("");
            }

