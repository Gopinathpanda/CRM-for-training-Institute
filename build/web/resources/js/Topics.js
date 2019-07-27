$(document).ready(function(){
            
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



