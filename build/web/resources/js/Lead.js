$(document).ready(function(){
        DisplayQualification()
        $("#btnnewlead").click(function(){
            $("#myModal").modal("show");
            
        })
            $("#ddqualifications").change(function(){
             FetchSpecializations();
            
        }) 
        });
        
         function DisplayQualification(){
                $.ajax({
                url:'/CRM_Student/displayqualification',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#ddqualifications").empty();
                  $("#ddqualifications").append("<option  selected disabled>Select Qualification</option>");
$.each(data,function(i,d){
    $("#ddqualifications").append("<option  value="+d.qualification_id+">"+d.qualification+"</option>");
});
    }
    });
     }
     
       function FetchSpecializations(){
         
          var qid=$("#ddqualifications").val();
      
       
            $.ajax({
                    url:'/CRM_Student/displayspecializationByQid/'+qid,
                    method:'get',                   
                    contentType:'application/json',
                    success:function(data){
                     $("#ddspecialization").empty();
                    $.each(data,function(i,d){
                        alert(data)
    $("#ddspecialization").append("<option  value="+d.specialization_id+">"+d.specialization+"</option>");
});
                    
                    }
                });
            }