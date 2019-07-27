<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script src="<c:url value="/resources/js/Specialization.js"></c:url>"></script>
</head> 
<body class="cbp-spmenu-push">
   <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
   <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Specialization</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Specialization-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-4"><small><label class="text-primary" >Specialization ID:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Specialization Name:</label></small></div>
                            <div class="col-md-4"><small><label class="text-primary">Qualification ID:</label></small></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="spid" placeholder="SPECIALIZATION ID"></div>
                           <div class="col-md-4 img-rounded"><input type="text" class="form-control" id="spn"  placeholder="SPECIALIZATION NAME"></div>
                           <div class="col-md-4 img-rounded"> 
                                <select id="ddqualification" class="form-control"></select>
                           </div>    
                        </div>
                    </form>
                    </div>
                <div class="modal-footer">
                <button type="button" id="btndelete" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete</button>
                <button type="button" id="btnupdate" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span>Update</button>
                <button type="button" id="btnsubmit" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Submit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Close</button>
                </div>
                </div>
           </div>
        </div>
     </div>
    </div>
        <div class="col-md-12">
            <div style="overflow-y: scroll;height: 470px">
                <table class="table table-bordered table-striped table-hover table-responsive table-condensed">
                <thead style="background:blue;color:white">
                       <tr>
                          <th class="text-center">Specialization ID</th>
                          <th class="text-center">Specialization Name</th>
                          <th class="text-center">Qualification Name</th>
                          <th class="text-center">Action</th>
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