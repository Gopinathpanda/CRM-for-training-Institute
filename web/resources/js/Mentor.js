$(document).ready(function(){
            
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxMentor();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addMentor();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateMentor();
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
                function GetMaxMentor()
                {
                $.get("/CRM_Student/getmentorid",function(data){
                 $("#mid").val(data);
                });                   
                }
              function Display(){
        $.ajax({
        url:'/CRM_Student/displaymentor',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.mentor_id+"</td><td>"+d.full_name+"</td><td>"+d.contact_no+"</td><td>"+d.email+"</td><td><input type='button' value='View' onclick='View("+d.mentor_id+")'></td></tr>");
});
    }
    });
     }
                function addMentor(){
                var mid=$("#mid").val();
                var fn=$("#name").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
               var data={"mentor_id":mid,"full_name":fn,"contact_no":cn,"email":email};
                $.ajax({
                    url:'/CRM_Student/addmentor',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateMentor(){
                var mid=$("#mid").val();
                var fn=$("#name").val();
                var cn=$("#cn").val();
                var email=$("#email").val();
               var data={"mentor_id":mid,"full_name":fn,"contact_no":cn,"email":email};
                $.ajax({
                    url:'/CRM_Student/updatementor',
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
                    url:'/CRM_Student/displaymentor/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#mid").val(d.mentor_id);
                     $("#name").val(d.full_name);
                     $("#cn").val(d.contact_no);
                     $("#email").val(d.email);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#mid").val();
                    $.ajax({
                    url:'/CRM_Student/deletementor/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#mid").val("");
                 $("#name").val("");
                  $("#cn").val("");
                 $("#email").val("");
                                }



