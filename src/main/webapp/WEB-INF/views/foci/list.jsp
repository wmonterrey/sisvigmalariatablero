<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html class="default-style">
<head>
  <jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<div class="page-loader">
    	<div class="bg-primary"></div>
  	</div>
  	
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-2">
		<div class="layout-inner">
			<jsp:include page="../fragments/sideNav.jsp" />
			<!-- Layout container -->
			<div class="layout-container">
				<!-- Layout navbar -->
				<nav class="layout-navbar navbar navbar-expand-lg align-items-lg-center bg-white container-p-x" id="layout-navbar">
					<jsp:include page="../fragments/navHeader.jsp" />
					<jsp:include page="../fragments/navHeader2.jsp" />
				</nav>
				<!-- / Layout navbar -->
				<!-- Layout content -->
        		<div class="layout-content">
        			<!-- Content -->
         			<div class="container-fluid flex-grow-1 container-p-y">
         				<jsp:useBean id="now" class="java.util.Date"/>    
         				<h4 class="font-weight-bold py-3 mb-4">
			              <spring:message code="heading" />
			              <div class="text-muted text-tiny mt-1"><small class="font-weight-normal"><fmt:formatDate value="${now}" dateStyle="long"/></small></div>
			            </h4>
			            <spring:url value="/foci/newEntity/" var="newFoci"/>	
	              		<button id="lista_focos_new" onclick="location.href='${fn:escapeXml(newFoci)}'" type="button" class="btn btn-outline-primary"><i class="fa fa-plus"></i>&nbsp; <spring:message code="add" /></button><br><br>
			            <div class="card mb-4" id="foci-element">
			              <h6 class="card-header with-elements">
			              	<div class="card-title-elements">
			              	</div>
			                <div class="card-header-title"><spring:message code="foci" /></div>
			              </h6>
			              <div class="row no-gutters row-bordered">
			                <div class="col-md-12 col-lg-12 col-xl-12">
			                  <div class="card-body">
			                  	<table id="lista_focos" class="table table-striped table-bordered datatable" width="100%">
				                <thead>
				                	<tr>
					                    <th><spring:message code="code" /></th>
					                    <th class="hidden-xs"><spring:message code="name" /></th>
					                    <th><spring:message code="enabled" /></th>
					                    <th><spring:message code="createdBy" /></th>
					                    <th><spring:message code="dateCreated" /></th>
					                    <th></th>
				                	</tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${focos}" var="foco">
				                		<tr>
				                			<spring:url value="/foci/{id}/"
				                                        var="idUrl">
				                                <spring:param name="id" value="${foco.ident}" />
				                            </spring:url>
				                            <td><a href="${fn:escapeXml(idUrl)}"><c:out value="${foco.code}" /></a></td>
				                            <td class="hidden-xs"><c:out value="${foco.name}" /></td>
				                            
				                            <c:choose>
				                                <c:when test="${foco.pasive eq '0'.charAt(0)}">
				                                    <td><span class="badge badge-success"><spring:message code="CAT_SINO_SI" /></span></td>
				                                </c:when>
				                                <c:otherwise>
				                                    <td><span class="badge badge-danger"><spring:message code="CAT_SINO_NO" /></span></td>
				                                </c:otherwise>
				                            </c:choose>
				                            <td><c:out value="${foco.recordUser}" /></td>
				                            <td><c:out value="${foco.recordDate}" /></td>
				                            <td><a href="${fn:escapeXml(idUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a></td>
				                		</tr>
				                	</c:forEach>
				                </tbody>
				                </table>
			                  
			                  </div>
			                </div>
			
			              </div>
			            </div>
         			</div>
         			<!-- / Content -->
         			<jsp:include page="../fragments/navFooter.jsp" />
        		</div>
        		<!-- Layout content -->
			</div>
			<!-- / Layout container -->
    	</div>
    </div>  	
    <!-- / Layout wrapper -->
	
  	<!-- Bootstrap and necessary plugins -->
  	<jsp:include page="../fragments/corePlugins.jsp" />
  	<jsp:include page="../fragments/bodyUtils.jsp" />
  	
  	<!-- Lenguaje -->
	<c:choose>
	<c:when test="${cookie.esisvigLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.esisvigLang.value}"/>
	</c:otherwise>
	</c:choose>
  	
  	<spring:url value="/resources/vendor/libs/datatables/label_{language}.json" var="dataTablesLang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
 


	<script>
		jQuery(document).ready(function() {
	    	$("li.foci").addClass("active");
	    	$('.datatable').DataTable({
				  dom: 'lBfrtip',
		          "oLanguage": {
		              "sUrl": "${dataTablesLang}"
		          },
		          "bFilter": true, 
		          "bInfo": true, 
		          "bPaginate": true, 
		          "bDestroy": true,
		          "responsive": true,
		          "pageLength": 10,
		          "bLengthChange": true,
		          "responsive": true,
		          "buttons": [
		              {
		                  extend: 'excel'
		              },
		              {
		                  extend: 'pdfHtml5',
		                  orientation: 'portrait',
		                  pageSize: 'LETTER'
		              }
		          ]
		      });
	    });
	</script>
</body>
</html>
