$(document).ready(function(){
            
            DisplayQualification();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxSpecialization();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addSpecialization();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateSpecialization();
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
                function GetMaxSpecialization()
                {
                $.get("/CRM_Student/getspecializationid",function(data){
                 $("#spid").val(data);
                });                   
                }
               function DisplayQualification(){
                $.ajax({
                url:'/CRM_Student/displayqualification',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddqualification").empty();
$.each(data,function(i,d){
    $("#ddqualification").append("<option  value="+d.qualification_id+">"+d.qualification+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayspecialization',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.specialization_id+"</td><td>"+d.specialization+"</td><td>"+d.qualification+"</td><td><input type='button' value='View' onclick='View("+d.specialization_id+")'></td></tr>");
});
    }
    });
     }
                function addSpecialization(){
                var qid=$("#ddqualification").val();
                var i=$("#spid").val();
                var n=$("#spn").val();
                var data={"specialization_id":i,"specialization":n,"qualification_id":qid};
                $.ajax({
                    url:'/CRM_Student/addspecialization',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateSpecialization(){
                var i=$("#spid").val();
                var n=$("#spn").val();
                var data={"specialization_id":i,"specialization":n};
                $.ajax({
                    url:'/CRM_Student/updatespecialization',
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
                    url:'/CRM_Student/displayspecialization/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#spid").val(d.specialization_id);
                     $("#spn").val(d.specialization);
                     $("#ddqualification").val(d.qualification_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#spid").val();
                    $.ajax({
                    url:'/CRM_Student/deletespecialization/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#spid").val("");
                 $("#spn").val("");
                  $("#ddqualification").val("");
            }



