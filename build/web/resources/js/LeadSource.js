 $(document).ready(function(){
            
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxLeadSource();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addLeadSource();
                  ClearData();
                  Display();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateLeadSource();
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
                function GetMaxLeadSource()
                {
                $.get("/CRM_Student/getleadsourceid",function(data){
                 $("#sid").val(data);
                });                   
                }
                
                
               function Display(){
                $.getJSON("/CRM_Student/displayleadsource",function(data){
                $("#tbldata").empty();
                   $.ajax({
                    url:'/CRM_Student/displayleadsource',
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                    $("#tbldata").empty();
                    $.each(data,function(i,d){
                    $("#tbldata").append("<tr><td>"+d.source_id+"</td><td>"+d.source_name+"</td><td>\n\
                    <input type='button' value='View' onclick='View("+d.source_id+")'></td></tr>"); });
                    }
                    });
                   });
                   }
                function addLeadSource(){
                var i=$("#sid").val();
                var n=$("#son").val();
                var data={"source_id":i,"source_name":n};
                $.ajax({
                    url:'/CRM_Student/addleadsource',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateLeadSource(){
                var i=$("#sid").val();
                var n=$("#son").val();
                var data={"source_id":i,"source_name":n};
                $.ajax({
                    url:'/CRM_Student/updateleadsource',
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
                    url:'/CRM_Student/displayleadsource/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#sid").val(d.source_id);
                     $("#son").val(d.source_name);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#sid").val();
                    $.ajax({
                    url:'/CRM_Student/deleteleadsource/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#sid").val("");
                 $("#sn").val("");
            }


