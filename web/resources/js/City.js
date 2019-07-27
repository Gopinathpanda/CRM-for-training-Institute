$(document).ready(function(){
            $("#cnc").css("color", "red");
            $("#cnc").html("**please fill City name");
            $("#snc").css("color", "red");
            $("#snc").html("**please fill State name");
            var cn_err = true;
            var sn_err = true;
            $("#cn").keyup(function () {
            ci_check();
            });
            function ci_check()
            {
                var cn = $("#cn").val();
                if (cn.length == "") {
                    $("#cnc").show();
                    $("#cnc").html("**please fill  City name");
                    $("#cnc").focus();
                    $("#cnc").css("color", "red");
                    cn_err = false;
                    return cn_err;
                }
                else {
                    $("#cnc").hide();
                     }
            }

            $("#ddstate").keyup(function () {
                 sn_check();

            });
            function sn_check()
            {
                var sn = $("#ddstate").val();
                if (sn.length == "") {
                    $("#snc").show();
                    $("#snc").html("**please fill State name");
                    $("#snc").focus();
                    $("#snc").css("color", "red");
                    sn_err = false;
                    return false;
                }
                else {
                    $("#snc").hide();
                }
            }
            DisplayStates();
            Display();
            $("#add").click(function(){
            ClearData();
            $("#myModal").modal("show");
            GetMaxCity();
            $("#btnsubmit").show();
            $("#btnupdate").hide();
            $("#btndelete").hide();
             });      
                  $("#btnsubmit").click(function(){
                  addCity();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
                       $("#btnupdate").click(function(){
                       updateCity();
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
                function GetMaxCity()
                {
                $.get("/CRM_Student/getcityid",function(data){
                 $("#cid").val(data);
                });                   
                }
               function DisplayStates(){
                $.ajax({
                url:'/CRM_Student/display',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddstate").empty();
$.each(data,function(i,d){
    $("#ddstate").append("<option  value="+d.state_id+">"+d.state_name+"</option>");
});
    }
    });
     }
     function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaycity',
        method:'get',
        contentType:'application/json',
        success:function(data){
$("#tbldata").empty();
$.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.city_id+"</td><td>"+d.city_name+"</td><td>"+d.state_name+"</td><td><input type='button' value='View' onclick='View("+d.city_id+")'></td></tr>");
});
    }
    });
     }
                function addCity(){
                var sid=$("#ddstate").val();
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"city_id":i,"city_name":n,"state_id":sid};
                $.ajax({
                    url:'/CRM_Student/addcity',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            function updateCity(){
                var i=$("#cid").val();
                var n=$("#cn").val();
                var data={"city_id":i,"city_name":n};
                $.ajax({
                    url:'/CRM_Student/updatecity',
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
                    url:'/CRM_Student/displaycity/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                     $("#cid").val(d.city_id);
                     $("#cn").val(d.city_name);
                     $("#ddstate").val(d.state_id);
                     $("#myModal").modal("show");
                    }
                });
            }
            function Delete(){
            var i=$("#cid").val();
                    $.ajax({
                    url:'/CRM_Student/deletecity/'+i,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(d){
                    Display();
                    }
               });
            }
            function ClearData(){
                 $("#cid").val("");
                 $("#cn").val("");
                  $("#ddstate").val("");
            }


