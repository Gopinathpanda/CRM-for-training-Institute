 $(document).ready(function(){
            
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxTechnology();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addTechnology();
                  ClearData();
                  Display();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateTechnology();
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
                function GetMaxTechnology()
                {
                $.get("/CRM_Student/gettechnologyid",function(data){
                 $("#tei").val(data);
                });                   
                }
                
                
               function Display(){
                $.getJSON("/CRM_Student/displaytechnology",function(data){
                $("#tbldata").empty();
                   $.ajax({
                    url:'/CRM_Student/displaytechnology',
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                    $("#tbldata").empty();
                    $.each(data,function(i,d){
                    $("#tbldata").append("<tr><td>"+d.technology_id+"</td><td>"+d.technology_name+"</td><td>\n\
                    <input type='button' value='View' onclick='View("+d.technology_id+")'></td></tr>"); });
                    }
                    });
                   });
                   }
                function addTechnology(){
                var i=$("#tei").val();
                var n=$("#ten").val();
                var data={"technology_id":i,"technology_name":n};
                $.ajax({
                    url:'/CRM_Student/addtechnology',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateTechnology(){
                var i=$("#tei").val();
                var n=$("#ten").val();
                var data={"technology_id":i,"technology_name":n};
                $.ajax({
                    url:'/CRM_Student/updatetechnology',
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
                    url:'/CRM_Student/displaytechnology/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#tei").val(d.technology_id);
                     $("#ten").val(d.technology_name);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#tei").val();
                    $.ajax({
                    url:'/CRM_Student/deletetechnology/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#tei").val("");
                 $("#ten").val("");
            }



