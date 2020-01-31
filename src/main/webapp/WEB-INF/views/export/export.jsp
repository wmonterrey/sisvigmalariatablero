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
			            <!-- Filtros -->
			            <div class="row">
			              <div class="col-sm-12 col-md-12 col-xl-12">
			                <div class="card mb-4">
			                  <div class="card mb-4">
									<div class="card-body">
										<form id="filters-form">
											
											<div class="form-row">
												<div class="form-group col-md-12">
													<div class="form-row">
														<div class="form-group col-md-1.5">
															<label id="labelFechaInicio" class="form-label"></label>
															<input type="hidden" id="fechaInicio" name="fechaInicio" class="form-control"></input>
														</div>
														<div class="form-group col-md-10">
															<div id="slider-date"></div>
														</div>
														<div class="form-group col-md-1.5">
															<label id="labelFechaFin" class="form-label col-sm-12 text-right"></label>
															<input type="hidden" id="fechaFin" name="fechaFin" class="form-control"></input>
														</div>
													</div>
												</div>
											</div>
											
											<div class="form-row">
						                    <div class="form-group col-md-3">
						                    	<label class="form-label"><spring:message code="level" /></label>
						                    	<div class="select2-primary">
						                  			<select id="oulevel" name="oulevel" class="select2-filtro form-control" style="width: 100%">
						                  				<option value="ALL"><spring:message code="all" /></option>
						                  				<option value="province.samp"><spring:message code="province.samp" /></option>
						                  				<option value="region.samp"><spring:message code="region.samp" /></option>
						                  				<option value="district.samp"><spring:message code="district.samp" /></option>
						                  				<option value="correg.samp"><spring:message code="correg.samp" /></option>
						                  				<option value="local.samp"><spring:message code="local.samp" /></option>
						                  			</select>
						               			</div>
						                    </div>
						                    <div id="divouname" class="form-group col-md-4">
						                    	<label class="form-label">&nbsp;</label>
						                    	<div class="select2-primary">
						                  			<select id="ouname" name="ouname" class="select2-filtro form-control" style="width: 100%">
						                  			</select>
						               			</div>
						                    </div>
											<div class="form-group col-md-2">
												<label class="form-label">&nbsp;</label>
												<div>
						               				<button id="actualizar" type="button" class="btn btn-primary"><spring:message code="update" /></button>
						               			</div>
						               		</div>
						               		</div>
										</form>
									</div>
								</div>
			                </div>
			
			              </div>
			            </div>
			            
			            
			            <div class="card mb-4" id="export-element">
			              <h6 class="card-header with-elements">
			              	<div class="card-title-elements">
			              	</div>
			                <div class="card-header-title"><label id="labelTableTitle"></label></div>
			              </h6>
			              <div class="row no-gutters row-bordered">
			                <div class="col-md-12 col-lg-12 col-xl-12">
			                  <div class="card-body">
			                  	<div class="card-datatable table-responsive">
                				  <table id="resultados"class="datatables-demo table table-striped table-bordered">
					                <thead>
					                	<tr>
					                		<th>Id Caso</th>
					                		<th>Id Not</th>
					                		<th>Fecha Nac</th>
					                		<th>Sexo</th>
					                		<th>Estado</th>
					                		<th>Fecha Not</th>
					                		<th>Unidad Not</th>
					                		<th>FIS</th>
					                		<th>Fecha Muestra</th>
					                		<th>Semana</th>
					                		<th>Año</th>
					                		<th>Tipo busq</th>
					                		<th>PDR</th>
					                		<th>PDR Res</th>
					                		<th>PDR Esp</th>
					                		<th>Fecha GG</th>
					                		<th>Res GG</th>
					                		<th>Esp GG</th>
					                		<th>Región Mx</th>
					                		<th>Distrito Mx</th>
					                		<th>Corregimiento Mx</th>
					                		<th>Localidad Mx</th>
					                		<th>Pais res</th>
					                		<th>Región res</th>
					                		<th>Distrito res</th>
					                		<th>Corregimiento res</th>
					                		<th>Localidad res</th>
					                		<th>Funcionario</th>
					                		<th>Clave</th>
					                		<th>Investigado</th>
					                		<th>Clasificado</th>
					                		<th>Tipo Caso</th>
					                		<th>Origen Inf</th>
					                		<th>Tx iniciado</th>
					                		<th>fecha inicio tx</th>
					                		<th>tx completo</th>
					                		<th>causa susp</th>
					                	</tr>
					                </thead>
					                <tbody>
					                </tbody>
					              </table>
			                  	</div>
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
  	<spring:url value="/resources/vendor/libs/select2/select2_{language}.js" var="select2Lang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
  	<script src="${select2Lang}"></script>
  	
  	<spring:url value="/resources/vendor/libs/datatables/label_{language}.json" var="dataTablesLang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
  	
  	<!-- Urls -->
  	<spring:url value="/resources/js/views/export.js" var="Export" />
  	<script src="${Export}" type="text/javascript"></script>
  	
  	<spring:url value="/export/casos/" var="casosUrl"/>

  	
  	<spring:url value="/api/regiones" var="opcRegUrl"/>
  	<spring:url value="/api/provincias" var="opcProvUrl"/>
  	<spring:url value="/api/distritos" var="opcDistUrl"/>
  	<spring:url value="/api/corregimientos" var="opcCorregUrl"/>
  	<spring:url value="/api/localidades/" var="localidadesUrl"/>
  	
 	<!-- Mensajes -->

  	<c:set var="seleccionar"><spring:message code="select" /></c:set>
  	<c:set var="ourequerida"><spring:message code="ou.required" /></c:set>
  	<c:set var="casos">Casos notificados</c:set>

	<script>
		jQuery(document).ready(function() {
			var parametros = {casosUrl: "${casosUrl}", opcRegUrl: "${opcRegUrl}",localidadesUrl: "${localidadesUrl}",
					 ourequerida: "${ourequerida}",opcProvUrl: "${opcProvUrl}",opcDistUrl: "${opcDistUrl}",opcCorregUrl: "${opcCorregUrl}",
					seleccionar: "${seleccionar}",dataTablesLang: "${dataTablesLang}",casos: "${casos}"};
			ProcessExport.init(parametros);
			
			if ($('html').attr('dir') === 'rtl') {
				$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
			}
			$('.navbar-filtros-mega-dropdown').each(function() {
				new MegaDropdown(this);
			});

	    	$("li.export").addClass("active");
	    });
	</script>
</body>
</html>
