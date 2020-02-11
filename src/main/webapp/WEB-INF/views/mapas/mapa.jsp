<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html class="default-style">
<head>
  <jsp:include page="../fragments/headTag.jsp" />
  <spring:url value="/resources/vendor/libs/leaflet/leaflet.css" var="leafletCSS" />
  <link href="${leafletCSS}" rel="stylesheet" type="text/css"/>
  <style>
  	.legend, .temporal-legend {
	padding: 6px 10px;
	font: 12px/14px Arial, Helvetica, sans-serif;
	background: white;
	background: rgba(255,255,255,0.8);
	box-shadow: 0 0 15px rgba(0,0,0,0.2);
	border-radius: 5px;
	}
	#legendTitle {
	 	text-align: center;
	 	margin-bottom: 15px;
		font-variant: small-caps;
	}
	.symbolsContainer {
		float: left;
		margin-left: 60px;
	}
	.legendCircle1 {
		border-radius:50%; 
		border: 1px solid #245277; 
	 	background: rgba(55, 126, 184, .3);
		display: inline-block;
	}
	.legendCircle2 {
		border-radius:50%; 
		border: 1px solid #2f6a2d; 
	 	background: rgba(77, 175, 74, .3);
		display: inline-block;
	}
	.legendCircle3 {
		border-radius:50%; 
		border: 1px solid #5a2e60; 
	 	background: rgba(152, 78, 163, .3);
		display: inline-block;
	}
	.legendCircle4 {
		border-radius:50%; 
		border: 1px solid #a75300; 
	 	background: rgba(255, 127, 0, .3);
		display: inline-block;
	}
	.legendValue {
		position: absolute;
		right: 8px;
	}
   </style>
   <spring:url value="/resources/vendor/libs/bootstrap-datepicker/bootstrap-datepicker.css" var="bsdpcss" />
   <link rel="stylesheet" href="${bsdpcss}">
   <spring:url value="/resources/vendor/libs/bootstrap-daterangepicker/bootstrap-daterangepicker.css" var="bsdrpcss" />
   <link rel="stylesheet" href="${bsdrpcss}">
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
												<div class="form-group col-md-1">
														<button id="daterange-mc" type="button" class="btn btn-default dropdown-toggle md-btn-flat"></button>
												</div>
												<div class="form-group col-md-2">
						                    	<div class="select2-primary">
						                  			<select id="anio" name="anio" class="select2-filtro form-control" style="width: 100%">
						                    			<c:forEach items="${anios}" var="anio">
								                    		<option value="${anio}"><spring:message code="${anio}" /></option>
														</c:forEach>
						                  			</select>
						               			</div>
						                    </div>
											</div>
											
											<div class="form-row">
												<div class="form-group col-md-12">
													<div class="form-row">
														<div class="form-group col-md-2">
															<label id="labelFechaInicio" class="form-label"></label>
															<input type="hidden" id="fechaInicio" name="fechaInicio" class="form-control"></input>
														</div>
														<div class="form-group col-md-8">
															<div id="slider-date"></div>
														</div>
														<div class="form-group col-md-2">
															<label id="labelFechaFin" class="form-label col-sm-12 text-right"></label>
															<input type="hidden" id="fechaFin" name="fechaFin" class="form-control"></input>
														</div>
													</div>
												</div>
											</div>
											
										</form>
									</div>
								</div>
			                </div>
			
			              </div>
			            </div>
			            
			            <div class="card mb-4" id="map-element">
			              <h6 class="card-header with-elements">
			                <div class="card-header-title"><label id="labelMapTitle"></label></div>
			              </h6>
			              <div class="row no-gutters row-bordered">
			                <div class="col-md-12 col-lg-12 col-xl-12">
			                  <div id="mapCard" class="card-body">
			                    <div id="mapid" style="width: 100%; height: 600px;"></div>
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
  	
  	<spring:url value="/resources/vendor/libs/leaflet/leaflet.js" var="leafletJS" />
    <script src="${leafletJS}" type="text/javascript"></script>
    
    <spring:url value="/resources/vendor/libs/moment/moment-with-locales.js" var="momment" />
    <script src="${momment}" type="text/javascript"></script>
    
    <spring:url value="/resources/vendor/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="bsdp" />
    <script src="${bsdp}" type="text/javascript"></script>
    
    <spring:url value="/resources/vendor/libs/bootstrap-daterangepicker/bootstrap-daterangepicker.js" var="bsdrp" />
    <script src="${bsdrp}" type="text/javascript"></script>
    
    <spring:url value="/resources/vendor/libs/bootstrap-daterangepicker/bdrp_{language}.js" var="bdrpLang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
  	<script src="${bdrpLang}"></script>
  	
  	<spring:url value="/resources/vendor/libs/bootstrap-daterangepicker/ranges_{language}.js" var="rangesLang">
  		<spring:param name="language" value="${lenguaje}" />
  	</spring:url>
  	<script src="${rangesLang}"></script>
    
   
    <spring:url value="/resources/maps/regionescentros.geojson" var="RegionesPanama" />
    <script src="${RegionesPanama}" type="text/javascript"></script>
    
    <spring:url value="/resources/maps/distritoscentros.geojson" var="DistritosPanama" />
    <script src="${DistritosPanama}" type="text/javascript"></script>
    
    <spring:url value="/resources/maps/corregimientoscentros.geojson" var="CorregimientosPanama" />
    <script src="${CorregimientosPanama}" type="text/javascript"></script>
    
    
    <spring:url value="/resources/maps/localidadescentros.geojson" var="LocalidadesPanama" />
    <script src="${LocalidadesPanama}" type="text/javascript"></script>
    
    <!-- Custom scripts required by this view -->
  	<spring:url value="/resources/js/views/mapa.js" var="ProcessMapa" />
  	<script src="${ProcessMapa}"></script>
    
    <spring:url value="/map/datosmapa/" var="datosUrl"/>
    
  	
  	<!-- Lenguaje -->
	<c:choose>
	<c:when test="${cookie.esisvigLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.esisvigLang.value}"/>
	</c:otherwise>
	</c:choose>
	
	<c:set var="creg"><spring:message code="casesxreg" /></c:set>
	<c:set var="cdis"><spring:message code="casesxdist" /></c:set>
	<c:set var="ccor"><spring:message code="casesxcorr" /></c:set>
	<c:set var="cloc"><spring:message code="casesxloc" /></c:set>
	<c:set var="cases"><spring:message code="confirmed" /></c:set>
	
	<script>
		jQuery(document).ready(function() {
			var parametros = {datosUrl: "${datosUrl}",creg: "${creg}",cdis: "${cdis}",ccor: "${ccor}",cloc: "${cloc}",cases: "${cases}"};
			ProcessMap.init(parametros);
			
			if ($('html').attr('dir') === 'rtl') {
				$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
			}

	    	$("li.map").addClass("active");
	    });
	</script>
</body>
</html>
