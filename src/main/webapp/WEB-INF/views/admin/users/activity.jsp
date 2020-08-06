<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="default-style">
<head>
  <jsp:include page="../../fragments/headTag.jsp" />
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
			<jsp:include page="../../fragments/sideNav.jsp" />
			<!-- Layout container -->
			<div class="layout-container">
				<!-- Layout navbar -->
				<nav class="layout-navbar navbar navbar-expand-lg align-items-lg-center bg-white container-p-x" id="layout-navbar">
					<jsp:include page="../../fragments/navHeader.jsp" />
					
					<jsp:include page="../../fragments/navHeader2.jsp" />
				</nav>
				<!-- / Layout navbar -->
				<!-- Layout content -->
        		<div class="layout-content">
        			<!-- Content -->
         			<div class="container-fluid flex-grow-1 container-p-y">
         				<jsp:useBean id="now" class="java.util.Date"/>
         				<h4 class="d-flex justify-content-between align-items-center py-2 mb-4">
			              <div class="font-weight-bold"><spring:message code="heading" /></div>
			              	<button id="daterange-mc" class="btn btn-default dropdown-toggle md-btn-flat"></button>
			            </h4>
			
			            <hr class="container-m-nx border-light my-0">
			            
			            <!-- Stats block -->
            			<div class="row no-gutters row-bordered container-m-nx">
            				<!-- Counters -->
				           <div class="col-sm-6 col-md-3 col-lg-6 col-xl-3" id="activeusersdiv">
				             <div class="d-flex align-items-center container-p-x py-4">
				               <div class="lnr lnr-users display-3 text-primary"></div>
				               <div class="ml-3">
				                 <div class="text-muted small"><spring:message code="activeusers" /></div>
				                 <div class="text-large"><label id="activeuserslabel"></label></div>
				               </div>
				             </div>
				           </div>
				           <div class="col-sm-6 col-md-3 col-lg-6 col-xl-3" id="accessesdiv">
				             <div class="d-flex align-items-center container-p-x py-4">
				               <div class="lnr lnr-laptop display-3 text-primary"></div>
				               <div class="ml-3">
				                 <div class="text-muted small"><spring:message code="accesses" /></div>
				                 <div class="text-large"><label id="accesseslabel"></label></div>
				               </div>
				             </div>
				           </div>
				           <div class="col-sm-6 col-md-3 col-lg-6 col-xl-3" id="visitsdiv">
				             <div class="d-flex align-items-center container-p-x py-4">
				               <div class="lnr lnr-pointer-up display-3 text-primary"></div>
				               <div class="ml-3">
				                 <div class="text-muted small"><spring:message code="visits" /></div>
				                 <div class="text-large"><label id="visitslabel"></label></div>
				               </div>
				             </div>
				           </div>
				           <div class="col-sm-6 col-md-3 col-lg-6 col-xl-3" id="averagediv">
				             <div class="d-flex align-items-center container-p-x py-4">
				               <div class="lnr lnr-hourglass display-3 text-primary"></div>
				               <div class="ml-3">
				                 <div class="text-muted small"><spring:message code="avgtime" /></div>
				                 <div class="text-large"><label id="averagelabel"></label></div>
				               </div>
				             </div>
				           </div>
				           <!-- / Counters -->
				           
				           <div class="swiper-container" id="swiper-days">
				           	  <div class="swiper-wrapper">
				           	  	<div class="swiper-slide">
						           	<!-- days graph -->
					              	<div class="col-lg-12 col-xl-12 container-p-x py-4" id="days-element">
					                	<h5 class="text-muted font-weight-normal mb-4">Ingresos por día</h5>
					                	<div style="height: 300px;">
					                  		<canvas id="activity-chart-1"></canvas>
					                	</div>
					              	</div>
					              	<!-- / days graph -->
				              	</div>
				              	<div class="swiper-slide">
				              		<div class="col-lg-12 col-xl-12 container-p-x py-4" id="days-element-2">
				              			<h6 class="card-header with-elements">
							            	<div class="card-header-title">Ingresos por día</div>
							            </h6>
							            <div class="datatablediv">
						              	<table id="daystable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th>Fecha</th>
						                      <th>Total ingresos</th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
				              		</div>
				              	</div>
				              </div>
				              <div class="swiper-button-next custom-icon">
				                  <i class="lnr lnr-chevron-right text-primary"></i>
				              </div>
				              <div class="swiper-button-prev custom-icon">
				                  <i class="lnr lnr-chevron-left text-primary"></i>
				              </div>
	  					  	  <div class="swiper-pagination"></div>
			              </div>
			              
			              <div class="swiper-container" id="swiper-users">
				           	  <div class="swiper-wrapper">
				           	  	<div class="swiper-slide">
						           	<!-- users graph -->
					              	<div class="col-lg-12 col-xl-12 container-p-x py-4" id="users-element">
					                	<h5 class="text-muted font-weight-normal mb-4">Ingresos por usuario</h5>
					                	<div style="height: 400px;">
					                  		<canvas id="activity-chart-2"></canvas>
					                	</div>
					              	</div>
					              	<!-- / days graph -->
				              	</div>
				              	<div class="swiper-slide">
				              		<div class="col-lg-12 col-xl-12 container-p-x py-4" id="users-element-2">
				              			<h6 class="card-header with-elements">
							            	<div class="card-header-title">Ingresos por usuario</div>
							            </h6>
							            <div class="datatablediv">
						              	<table id="userstable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th>Usuario</th>
						                      <th>Nombre</th>
						                      <th>Habilitado</th>
						                      <th>Último ingreso</th>
						                      <th>Total ingresos</th>
						                      <th>Promedio (mins)</th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
				              		</div>
				              	</div>
				              </div>
				              <div class="swiper-button-next custom-icon">
				                  <i class="lnr lnr-chevron-right text-primary"></i>
				              </div>
				              <div class="swiper-button-prev custom-icon">
				                  <i class="lnr lnr-chevron-left text-primary"></i>
				              </div>
	  					  	  <div class="swiper-pagination"></div>
			              </div>
			
			              
              
            			</div>
            			<!-- / Stats block -->
            			
            			<hr class="container-m-nx border-light mt-0 mb-4">
            			
            			
            			
            			<div class="row">
            				<div class="col-md-12 col-lg-7 col-xl-7">
            					<div class="card mb-4" id="inactivediv">
            						<h6 class="card-header with-elements">
						            	<div class="card-header-title">Usuarios inactivos</div>
						            </h6>
						            <div class="datatablediv">
						              	<table id="inactivetable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th>Usuario</th>
						                      <th>Nombre</th>
						                      <th>Habilitado</th>
						                      <th>Último ingreso</th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
					              	</div>
            					</div>
            				</div>
            				<div class="col-md-12 col-lg-5 col-xl-5">
            					<div class="card mb-4" id="pagesdiv">
            						<h6 class="card-header with-elements">
						            	<div class="card-header-title">Páginas visitadas</div>
						            </h6>
						            <div class="datatablediv">
						              	<table id="pagestable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th>Página</th>
						                      <th>Visitas</th>
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
         			<!-- / Content -->
         			<jsp:include page="../../fragments/navFooter.jsp" />
        		</div>
        		<!-- Layout content -->
			</div>
			<!-- / Layout container -->
    	</div>
    </div>  	
    <!-- / Layout wrapper -->
	
  	<!-- Bootstrap and necessary plugins -->
  	<jsp:include page="../../fragments/corePlugins.jsp" />
  	<jsp:include page="../../fragments/bodyUtils.jsp" />
  	
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
  	
  	
  	<spring:url value="/resources/js/views/actividad.js" var="Actividad" />
  	<script src="${Actividad}" type="text/javascript"></script>
  	
  	<spring:url value="/resources/help/home.html" var="userActivityUrl"/>
  	
  	<!-- Urls -->
  	<spring:url value="/admin/users/activeusers/" var="activosUrl"/>
  	<spring:url value="/admin/users/pagesvisited/" var="visitadasUrl"/>
  	<spring:url value="/admin/users/activitybyday/" var="porDiaUrl"/>
  	<spring:url value="/admin/users/inactives/" var="inactivosUrl"/>
  	<spring:url value="/admin/users/activity/" var="baseUrl"/>
  	

	<script>
		jQuery(document).ready(function() {
			
			var parametros = {baseUrl: "${baseUrl}", activosUrl: "${activosUrl}",inactivosUrl: "${inactivosUrl}",visitadasUrl: "${visitadasUrl}",porDiaUrl: "${porDiaUrl}",lenguaje: "${lenguaje}",dataTablesLang: "${dataTablesLang}"};
			ProcessActividadUsuario.init(parametros);
			
			if ($('html').attr('dir') === 'rtl') {
				$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
			}
	    	$("li.usuarios").addClass("open");
	    	$("li.usuarios").addClass("active");
	    	$("li.uactiv").addClass("active");
			
	    });
		
		function cargarAyuda(){ 
			window.open("${helpUrl}");
		}
		
	</script>
</body>
</html>
