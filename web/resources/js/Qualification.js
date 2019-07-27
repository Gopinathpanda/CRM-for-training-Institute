 $(document).ready(function(){
            
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxQualification();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addQualification();
                  ClearData();
                  Display();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateQualification();
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
                             $("#btnsubmit").show();
                             $("#btnupdate").hide();
                             }
                             $("#myModal").modal("hide");
                            });
                });
                function GetMaxQualification()
                {
                $.get("/CRM_Student/getqualificationid",function(data){
                 $("#qi").val(data);
                });                   
                }
                
                
               function Display(){
                $.getJSON("/CRM_Student/displayqualification",function(data){
                $("#tbldata").empty();
                   $.ajax({
                    url:'/CRM_Student/displayqualification',
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                    $("#tbldata").empty();
                    $.each(data,function(i,d){
                    $("#tbldata").append("<tr><td>"+d.qualification_id+"</td><td>"+d.qualification+"</td><td>\n\
                    <input type='button' value='View' onclick='View("+d.qualification_id+")'></td></tr>"); });
                    }
                    });
                   });
                   }
                function addQualification(){
                var i=$("#qi").val();
                var n=$("#qn").val();
                var data={"qualification_id":i,"qualification":n};
                $.ajax({
                    url:'/CRM_Student/addqualification',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateQualification(){
                var i=$("#qi").val();
                var n=$("#qn").val();
                var data={"qualification_id":i,"qualification":n};
                $.ajax({
                    url:'/CRM_Student/updatequalification',
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
                    url:'/CRM_Student/displayqualification/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#qi").val(d.qualification_id);
                     $("#qn").val(d.qualification);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#qi").val();
                    $.ajax({
                    url:'/CRM_Student/deletequalification/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#qi").val("");
                 $("#qn").val("");
            }


