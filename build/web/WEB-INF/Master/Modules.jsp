<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CRM Student</title>
<%@include  file="../common/files.jsp" %>
<script src="<c:url value="/resources/js/Module.js"></c:url>"></script> 
</head> 
<body class="cbp-spmenu-push">
    
    <%@include file="../common/headersandMenu.jsp" %>
<div id="page-wrapper">
    <div class="container">
         <a id="add" role="button" class="btn btn-large btn-primary" data-toggle="modal">Add Module</a>
         <div class="row">
           <div id="myModal" class="modal fade">
               <div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h3 class="title1"><center>Module-Form</center></h3>
                   </div>
                    <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary" >Module ID:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">Module Name:</label></small></div>
                           <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="mi" placeholder="MODULE ID"></div>
                           <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="mn" placeholder="MODULE NAME"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6"><small><label class="text-primary" >Duration:</label></small></div>
                            <div class="col-md-6"><small><label class="text-primary">course Name:</label></small></div>
                           <div class="col-md-6 img-rounded"><input type="text" class="form-control" id="du" placeholder="DURATION"></div>
                                <div class="col-md-6 img-rounded"> 
                                <select id="ddprogram" class="form-control"></select>
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
                          <th class="text-center">Module ID</th>
                          <th class="text-center">Module Name</th>
                          <th class="text-center">Duration Module(In Days)</th>
                          <th class="text-center">Course Name</th>
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