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
			<spring:url value="/foci/editEntity/{ident}/" var="editUrl">
           		<spring:param name="ident" value="${foco.ident}" />
            </spring:url>
            <spring:url value="/foci/disableEntity/{ident}/" var="disableUrl">
                 	<spring:param name="ident" value="${foco.ident}" />
            </spring:url>
            <spring:url value="/foci/enableEntity/{ident}/" var="enableUrl">
              	<spring:param name="ident" value="${foco.ident}" />
            </spring:url>
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
			            <div class="card mb-4" id="viewfoco-element">
			              <h6 class="card-header with-elements">
			                <div class="card-header-title"><i class="ion ion-md-cloud-outline"></i>&nbsp;<strong><c:out value="${foco.name}" /></strong></div>
			              </h6>
			              <div class="col-md-12 col-lg-12 col-xl-12">
			                <div class="card-body demo-vertical-spacing">
			                	<div class="row">
									<div class="col-md-4">
										<div>
											<label class="control-label"><spring:message code="ident" />:</label>
											<div>
												<p class="form-control-static">
													 <strong><c:out value="${foco.ident}" /></strong>
												</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div>
											<label class="control-label"><spring:message code="code" />:</label>
											<div>
												<p class="form-control-static">
													 <strong><c:out value="${foco.code}" /></strong>
												</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label"><spring:message code="enabled" />:</label>
											<div>
												<p class="form-control-static">
													<c:choose>
														<c:when test="${foco.pasive=='0'.charAt(0)}">
															<strong><spring:message code="CAT_SINO_SI" /></strong>
														</c:when>
														<c:otherwise>
															<strong><spring:message code="CAT_SINO_NO" /></strong>
														</c:otherwise>
													</c:choose>
												</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
			                 	<div class="row">
								<!--/span-->
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label"><spring:message code="createdBy" />:</label>
										<div>
											<p class="form-control-static">
												 <strong><c:out value="${foco.recordUser}" /></strong>
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label"><spring:message code="dateCreated" />:</label>
										<div>
											<p class="form-control-static">
												<strong><c:out value="${foco.recordDate}" /></strong>
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							
			                </div>
			                
			               
			              </div>
			              
			              <div>
	          				<div class="row float-right mr-4" >
	          					<button id="edit_entity" onclick="location.href='${fn:escapeXml(editUrl)}'" type="button" class="btn btn-outline-primary"><i class="fa fa-pencil-alt"></i>&nbsp; <spring:message code="edit" /></button>
	          					<c:choose>
									<c:when test="${foco.pasive=='0'.charAt(0)}">
										<button id="disable_entity" onclick="location.href='${fn:escapeXml(disableUrl)}'" type="button" class="btn btn-outline-danger"><i class="fa fa-window-close"></i>&nbsp; <spring:message code="disable" /></button>
									</c:when>
									<c:otherwise>
										<button id="enable_entity" onclick="location.href='${fn:escapeXml(enableUrl)}'" type="button" class="btn btn-outline-primary"><i class="fa fa-check"></i>&nbsp; <spring:message code="enable" /></button>
									</c:otherwise>
							 	</c:choose>
	          					<button id="back_button" onclick="location.href='<spring:url value="/foci/" htmlEscape="true "/>'" type="button" class="btn btn-outline-primary"><i class="fa fa-undo"></i>&nbsp; <spring:message code="cancel" /></button>
	          				</div>
	            		  </div>
			              
			
			            </div>
			            
			            <div class="card mb-4" id="viewuser-element-2">
			              <h6 class="card-header with-elements">
			                <div class="card-header-title"><i class="fa fa-map"></i>&nbsp;<strong><spring:message code="local.samp" /></strong></div>
			              </h6>
			              <div class="col-md-12 col-lg-12 col-xl-12">
			                <div class="card-body">
			                	<table id="lista_localidades" class="table table-striped table-bordered datatable" width="100%">
					                <thead>
					                	<tr>
						                    <th><spring:message code="local.samp" /></th>
											<th><spring:message code="correg.samp" /></th>
											<th><spring:message code="region.samp" /></th>
											<th></th>
					                	</tr>
					                </thead>
					                <tbody>
					                	<c:forEach items="${localidadesSeleccionadas}" var="localidadfoco">
					                		<spring:url value="/foci/disableLoc/{ident}/{locality}/" var="disableLocalUrl">
								              	<spring:param name="ident" value="${foco.ident}" />
								              	<spring:param name="locality" value="${localidadfoco.ident}" />
								            </spring:url>
											<tr>
												<td><c:out value="${localidadfoco.name}" /></td>
												<td><c:out value="${localidadfoco.corregimiento.name}" /></td>
												<td><c:out value="${localidadfoco.corregimiento.distrito.region.name}" /></td>
												<td><button id="disable_local" onclick="location.href='${fn:escapeXml(disableLocalUrl)}'" type="button" class="btn btn-outline-danger"><i class="fa fa-window-close"></i></button></td>
											</tr>
										</c:forEach>
					                </tbody>
					            </table>
			                </div>
			                <h6 class="card-header with-elements">
				                <div class="card-title-elements ml-md-auto">
				                	<spring:url value="/foci/addLocalidad/{foco}/" var="addLocalidadUrl"><spring:param name="foco" value="${foco.ident}" /></spring:url>
			            			<button type="button" class="btn btn-primary" id="addLocalidad" data-toggle="modal" data-whatever="${fn:escapeXml(addLocalidadUrl)}"><i class="fa fa-plus"></i>&nbsp;<spring:message code="add" /></button>
		                      	</div>
				              </h6>
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
    
   	  <!-- Modal -->
 	  <div class="modal fade" id="localidadesForm" tabindex="-1" data-role="localidadesForm" data-backdrop="static" data-aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title"><spring:message code="add" /> <spring:message code="local.samp" /></h2>
				</div>
				<div class="modal-body">
					<input type="hidden" id="inputAddLocalidadUrl"/>
					<div id="cuerpo" class="select2-primary">
	                  	<select id="localidades" name="localidades" class="select2-filtro form-control" style="width: 100%">
	                  	</select>
					</div>
				</div>	
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
					<button type="button" id="buttonAgregarLoalidad" class="btn btn-info" onclick="ejecutarAgregarLocalidad()"><spring:message code="ok" /></button>
				</div>
			</div>
			<!-- /.modal-content -->
	    </div>
	  <!-- /.modal-dialog -->
 	  </div>
    
	
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
	
	<spring:url value="/resources/js/views/foco.js" var="Foco" />
  	<script src="${Foco}" type="text/javascript"></script>
  	
  	<spring:url value="/resources/vendor/libs/datatables/label_{language}.json" var="dataTablesLang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>	
  	
  	<spring:url value="/resources/vendor/libs/select2/select2_{language}.js" var="select2Lang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
  	<script src="${select2Lang}"></script>
  	
  	<spring:url value="/api/localidades/" var="localidadesUrl"/>

    <c:set var="entityEnabledLabel"><spring:message code="enabled" /></c:set>
    <c:set var="entityDisabledLabel"><spring:message code="disabled" /></c:set>
    <c:set var="seleccionar"><spring:message code="select" /></c:set>
    <c:set var="ourequerida"><spring:message code="ou.required" /></c:set>

	<script>
		jQuery(document).ready(function() {
			var parametros = {seleccionar: "${seleccionar}",lenguaje: "${lenguaje}",dataTablesLang: "${dataTablesLang}",localidadesUrl: "${localidadesUrl}"};
			ProcessLocalidad.init(parametros);
			
			if ($('html').attr('dir') === 'rtl') {
				$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
			}
	    	$("li.foci").addClass("active");
			
	    });
		
		
		
		if ("${entidadHabilitada}"){
			toastr.info("${entityEnabledLabel}", "${nombreEntidad}", {
			    closeButton: true,
			    progressBar: true,
			  } );
		}

		if ("${entidadDeshabilitada}"){
			toastr.error("${entityDisabledLabel}", "${nombreEntidad}" , {
			    closeButton: true,
			    progressBar: true,
			  });
		}
		
	  function ejecutarAgregarLocalidad() {
		  if($('#localidades').select2('data')[0]===undefined){
			  alert("${ourequerida}");
		  }
		  else{
			window.location.href = $('#inputAddLocalidadUrl').val()+$('#localidades').val()+'/';
		  }
		}
		
	</script>
	
	
</body>
</html>
