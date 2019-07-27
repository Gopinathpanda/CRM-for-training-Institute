<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script>
     $(document).ready(function(){
          EmailShow();
          Display();
         $("#addpayment").click(function(){
            ClearDataPayment();
        $("#myModalpayment").modal("show");
            GetMaxPayment();
         });
             $("#ddcity").change(function(){
             FetchLocation();
         });
           $("#btnsubmitpayment").click(function(){
                  addPayment();
                  ClearDataPayment();
                  $("#myModalpayment").modal("hide");
                  Display();
                  });  
        });
        
       
        
         function GetMaxPayment()
                {
                $.get("/CRM_Student/getstudentpaymentid",function(data){
                 $("#pid").val(data);
                });                   
                }
        function EmailShow(){
                $.ajax({
                url:'/CRM_Student/displayprofile',
                method:'get',
                contentType:'application/json',
                success:function(data){
                $("#emailid").empty();
               $("#emailid").append("<option  selected disabled>Select Email</option>");
                $.each(data,function(i,d){
               $("#emailid").append("<option  value="+d.registration_id+">"+d.email+"</option>");
                });
                }
                });
                }  
        function Display(){
                
    $.ajax({
        url:'/CRM_Student/displaystdpayment',
        method:'get',
        contentType:'application/json',
        success:function(data){
        $("#tbldata").empty();
        $.each(data,function(i,d){
    $("#tbldata").append("<tr><td>"+d.payment_id+"</td><td>"+d.email+"</td><td>"+d.amount+"</td><td>"+d.payment_date+"</td><td>"+d.purpose+"</td><td>"+d.payment_mode+"</td><td>");
});
    }
    });
     }    
           function addPayment(){
                var pid=$("#pid").val();
                var rid=$("#emailid").val();
                var am=$("#amount").val();
                var pd=$("#pd").val();
                var pu=$("#purpose").val();
                var pm=$("#pm").val();
                var data={"payment_id":pid,"registration_id":rid,"amount":am,"payment_date":pd,"purpose":pu,"payment_mode":pm};
                   $.ajax({
                    url:'/CRM_Student/addstudentpayment',
                    method:'post',
                    data:JSON.stringify(data),
                    contentType:'application/json',
                    success:function(d){
                     alert("success");
                    
                    }
                });
            }
        function ClearDataPayment()
       {
           $("#pid").val("");
           $("#emailid").val("");
           $("#amount").val("");
           $("#pd").val("");
           $("#purpose").val("");
           $("#pm").val("");
        }
     
</script>
</head> 
<body class="cbp-spmenu-push">
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
        <a id="addpayment" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Student Payment</a>
          <div class="row">
           <div id="myModalpayment" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Student Payment-Form</center></h3>
                   </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-md-4"><small><label class="text-primary">Payment ID:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Student Email:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Amount:</label></small></div> 
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="pid" placeholder=" PAYMENT ID"></div>
                                <div class="col-md-4 img-rounded"> 
                                    <select type="text" id="emailid" class="form-control">
                                        <option selected="selected" disabled="disabled">Select Email</option>
                                    </select>
                                </div>
                                 <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="amount" placeholder=" AMOUNT"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4"><small><label class="text-primary">Payment Date:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Purpose:</label></small></div>
                                <div class="col-md-4"><small><label class="text-primary">Payment Mode:</label></small></div>
                                <div class="col-md-4 img-rounded"><input type="date" class="form-control" id="pd" placeholder=" PAYMENT DATE"></div>
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="purpose" placeholder=" PURPOSE"></div>
                                <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="pm" placeholder=" PAYMENT MODE"></div>
                            </div>
                           
                        </form>
                    </div>
                <div class="modal-footer">
                <button type="button" id="btnsubmitpayment" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Submit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Close</button>
                </div>
                </div>
           </div>
        </div>
     </div>
    </div>
         <div class="col-md-12">
            <div style="overflow-y: scroll;height: 430px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Payment ID</th>
                          <th class="text-center">Student Email</th>
                          <th class="text-center">Amount</th>
                          <th class="text-center">Payment Date</th>
                          <th class="text-center">Purpose</th>
                          <th class="text-center">Payment Mode</th>
                        </tr>
                   </thead>
                   <tbody id="tbldata" class="text-center text-capitalize text-primary">
                   </tbody>
                </table>
            
            </div>
      </div>
    </div>
         <%@include file="../common/footer.jsp" %>
    </body>
</html>