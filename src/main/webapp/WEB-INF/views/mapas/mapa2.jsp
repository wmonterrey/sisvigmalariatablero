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
		margin-left: 50px;
	}
	.legendCircle {
		border-radius:50%; 
		border: 1px solid #537898; 
	 	background: rgba(113, 133, 152, .6);
		display: inline-block;
	}
	.legendValue {
		position: absolute;
		right: 8px;
	}
   </style>
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
  	
  	<spring:url value="/resources/vendor/libs/leaflet/leaflet.js" var="leafletJS" />
    <script src="${leafletJS}" type="text/javascript"></script>
    
   
    <spring:url value="/resources/maps/regionescentros.geojson" var="RegionesPanama" />
    <script src="${RegionesPanama}" type="text/javascript"></script>
    
    <spring:url value="/view/portada/regionesmapa/" var="regionesUrl"/>
    
  	
  	<!-- Lenguaje -->
	<c:choose>
	<c:when test="${cookie.esisvigLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.esisvigLang.value}"/>
	</c:otherwise>
	</c:choose>
	
	<script>
	jQuery(document).ready(function() {
		var parametros = {casosUrl: "${casosUrl}", muestrasUrl: "${muestrasUrl}", regionesUrl: "${regionesUrl}", locbusqUrl: "${locbusqUrl}",lenguaje: "${lenguaje}",dataTablesLang: "${dataTablesLang}",
				casos: "${casos}", muestras: "${muestras}",seleccionar: "${seleccionar}",opcRegUrl: "${opcRegUrl}",localidadesUrl: "${localidadesUrl}"
					, ourequerida: "${ourequerida}",opcProvUrl: "${opcProvUrl}",opcDistUrl: "${opcDistUrl}",opcCorregUrl: "${opcCorregUrl}",total: "${total}"
						,region: "${region}",distrito: "${distrito}",corregimiento: "${corregimiento}",localidad: "${localidad}"};
		ProcessDashboardPortada.init(parametros);
		
		if ($('html').attr('dir') === 'rtl') {
			$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
		}
		$('.navbar-filtros-mega-dropdown').each(function() {
			new MegaDropdown(this);
		});
    	$("li.tableros").addClass("open");
    	$("li.tableros").addClass("active");
    	$("li.portada").addClass("active");
    });
	
		
	
		
		$("li.maps2").addClass("active");
	  	
	</script>
</body>
</html>
