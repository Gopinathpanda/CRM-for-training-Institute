
     $(document).ready(function(){
        DisplayCity();
        Display();
             $("#ddcity").change(function(){
             FetchLocation();
            
        });
        $("#add").click(function(){
            ClearData();
        $("#myModal").modal("show");
            GetMaxProfile();
            $("#btnsubmit").show();
         });
          $("#btnsubmit").click(function(){
                  addProfile();
                  ClearData();
                  $("#myModal").modal("hide");
                  Display();
                  });
    });
       function GetMaxProfile()
                {
                $.get("/CRM_Student/getprofileid",function(data){
                 $("#rid").val(data);
                });                   
                }          
        
        function DisplayCity(){
                $.ajax({
                url:'/CRM_Student/displaycity',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddcity").empty();
               $("#ddcity").append("<option  selected disabled>Select City</option>");
$.each(data,function(i,d){
    $("#ddcity").append("<option  value="+d.city_id+">"+d.city_name+"</option>");
});
    }
    });
     }
    function FetchLocation(){
         
          var cid=$("#ddcity").val();
      
       
            $.ajax({
                    url:'/CRM_Student/displaylocationbycity/'+cid,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                     $("#ddlocation").empty();
                        $("#ddlocation").append("<option selected disabled>Select Location</option>");

                    $.each(data,function(i,d){
                      
                     $("#ddlocation").append("<option  value="+d.location_id+">"+d.location_name+"</option>");
});
                    
                    }
                });
            }
             
            
            function Display(){
                
    $.ajax({
        url:'/CRM_Student/displayprofile',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.registration_id+"</td><td>"+d.first_name+"</td><td>"+d.middle_name+"</td><td>"+d.last_name+"</td><td>"+d.dob+"</td><td>"+d.contact_no+"</td><td>"+d.email+"</td><td>"+d.parent_number+"</td><td>"+d.gender+"</td><td>"+d.registration_date+"</td><td>"+d.location_name+"</td><td>"+d.local_address+"</td><td>"
    +d.permanent_address+"</td></tr>");
});
    }
    });
     }
      function addProfile(){
                var rid=$("#rid").val();
                var fn=$("#fn").val();
                 var mn=$("#mn").val();
                  var ln=$("#ln").val();
                   var dob=$("#db").val(); 
                   var cn=$("#cno").val();
                var email=$("#email").val();
                var pn=$("#pno").val();
                 var rd=$("#rdate").val();
                    var l=$("#ddlocation").val();
                     var g=$("input[name='d']:checked").val();
                      var pa=$("#paddress").val();
                       var la=$("#laddress").val();
                    
                var data={"registration_id":rid,"first_name":fn,"middle_name":mn,"last_name":ln,"dob":dob,"contact_no":cn,"email":email,"parent_number":pn,"gender":g,"registration_date":rd,"location_id":l,
                    "local_address":la,"permanent_address":pa};
                 
                $.ajax({
                    url:'/CRM_Student/addprofile',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
            
     
            function ClearData(){
                 $("#rid").val("");
                $("#fn").val("");
                 $("#mn").val("");
                 $("#ln").val("");
                   $("#db").val(""); 
                  $("#cno").val("");
                   $("#pno").val("");
                $("#email").val("");
                 $("#rdate").val("");
                  $("#ddcity").val("");
                    $("#ddlocation").val("");
                     $("#gender").val("");
                      $("#paddress").val("");
                      $("#laddress").val("");
            }


