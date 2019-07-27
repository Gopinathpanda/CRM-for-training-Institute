$(document).ready(function(){
            
            DisplayCity();
            Display();
            $("#add").click(function(){
            $("#myModal").modal("show");
            GetMaxLocation();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addLocation();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateLocation();
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
                function GetMaxLocation()
                {
                $.get("/CRM_Student/getlocationid",function(data){
                 $("#lid").val(data);
                });                   
                }
               function DisplayCity(){
                $.ajax({
                url:'/CRM_Student/displaycity',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcity").empty();
$.each(data,function(i,d){
    $("#ddcity").append("<option  value="+d.city_id+">"+d.city_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaylocation',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.location_id+"</td><td>"+d.location_name+"</td><td>"+d.city_name+"</td><td><input type='button' value='View' onclick='View("+d.location_id+")'></td></tr>");
});
    }
    });
     }
                function addLocation(){
                var cid=$("#ddcity").val();
                var i=$("#lid").val();
                var n=$("#ln").val();
                var data={"location_id":i,"location_name":n,"city_id":cid};
                $.ajax({
                    url:'/CRM_Student/addlocation',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateLocation(){
                var i=$("#lid").val();
                var n=$("#ln").val();
                var data={"location_id":i,"location_name":n};
                $.ajax({
                    url:'/CRM_Student/updatelocation',
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
                    url:'/CRM_Student/displaylocation/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#lid").val(d.location_id);
                     $("#ln").val(d.location_name);
                     $("#ddcity").val(d.city_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#lid").val();
                    $.ajax({
                    url:'/CRM_Student/deletelocation/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#lid").val("");
                 $("#ln").val("");
                  $("#ddcity").val("");
            }



