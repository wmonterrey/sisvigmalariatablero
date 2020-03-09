<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="default-style">
<head>
  <jsp:include page="../fragments/headTag.jsp" />
  <spring:url value="/resources/vendor/libs/leaflet/leaflet.css" var="leafletCSS" />
  <link href="${leafletCSS}" rel="stylesheet" type="text/css"/>
  <style>#map { width: 800px; height: 500px; }
	.info { padding: 6px 8px; font: 14px/16px Arial, Helvetica, sans-serif; background: white; background: rgba(255,255,255,0.8); box-shadow: 0 0 15px rgba(0,0,0,0.2); border-radius: 5px; } .info h4 { margin: 0 0 5px; color: #777; }
	.legend { text-align: left; line-height: 48px; color: #555; } .legend i { width: 20px; height: 20px; float: left; margin-right: 8px; opacity: 0.7; }</style>
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
											<div class="form-group col-md-3">
						                    	<label class="form-label"><spring:message code="year" /></label>
						                    	<div class="select2-primary">
						                  			<select id="anio" name="anio" class="select2-filtro form-control" style="width: 100%">
						                    			<c:forEach items="${anios}" var="anio">
								                    		<option value="${anio}"><spring:message code="${anio}" /></option>
														</c:forEach>
						                  			</select>
						               			</div>
						                    </div>
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
						                  				<option value="foci.samp"><spring:message code="foci.samp" /></option>
						                  			</select>
						               			</div>
						                    </div>
						                    <div id="divouname" class="form-group col-md-4">
						                    	<label class="form-label">&nbsp;</label>
						                    	<div class="select2-primary">
						                  			<select id="ouname" name="ouname" class="select2-filtro form-control" style="width: 100%">s
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
			              
			            <!-- / Conteos -->
			            
			            <!-- Canales pagination -->
			            <div class="swiper-container" id="swiper-with-pagination-canales">
			            	<div class="swiper-wrapper">
			            		<div class="swiper-slide">
			            			<!-- grafico canales -->
			            			<div class="card mb-4" id="canales-element">
						              <h6 class="card-header with-elements">
						                <div class="card-title-elements">
						              		<button id="anterior" type="button" class="btn icon-btn btn-outline-primary">
					                          <span class="ion ion-md-rewind"></span>
					                        </button>
					                        &nbsp;&nbsp;
						              	</div>
						                <div class="card-header-title"><label id="labelChart1Title"></label></div>
						                <div class="card-title-elements ml-md-auto">
				                        	<button id="siguiente" type="button" class="btn icon-btn btn-outline-primary">
				                         		<span class="ion ion-md-fastforward"></span>
				                        	</button>
				                      	</div>
						              </h6>
						              <div class="row no-gutters row-bordered">
						                <div class="col-md-12 col-lg-12 col-xl-12">
						                  <div class="card-body">
						                    <div style="height: 350px;">
						                      <canvas id="canales-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico canales -->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla canales -->
			            			<div class="card mb-4" id="canales-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable1Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="canalestable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th><spring:message code="week.samp" /></th>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totcases" /></th>
						                      <th>Q1</th>
						                      <th>Q2</th>
						                      <th>Q3</th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla canales -->
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
			            <!-- Canales pagination -->
			            
			            
			            <!-- carga pagination-->
			            <div class="swiper-container" id="swiper-with-pagination-carga">
			            	<div class="swiper-wrapper">
			            		<div class="swiper-slide">
			            			<!-- grafico carga tipo busqueda-->
			            			<div class="card mb-4" id="carga-element">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelChart2Title"></label></div>
						              </h6>
						              <div class="row no-gutters row-bordered">
						                <div class="col-md-12 col-lg-12 col-xl-12">
						                  <div class="card-body">
						                    <div style="height: 350px;">
						                      <canvas id="carga-cases-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico carga tipo busqueda-->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla carga tipo busqueda-->
			            			<div class="card mb-4" id="carga-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable2Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="cargacasestable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totcases" /></th>
						                      <th><spring:message code="active" /></th>
						                      <th><spring:message code="pasive" /></th>
						                      <th><spring:message code="reactive" /></th>
						                      <th><spring:message code="survey" /></th>
						                      <th><spring:message code="sdtb" /></th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla carga tipo busqueda-->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- grafico carga plasmodium-->
			            			<div class="card mb-4" id="plasmodium-element">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelChart3Title"></label></div>
						              </h6>
						              <div class="row no-gutters row-bordered">
						                <div class="col-md-12 col-lg-12 col-xl-12">
						                  <div class="card-body">
						                    <div style="height: 350px;">
						                      <canvas id="carga-plasmodium-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico carga plasmodium-->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla carga especie-->
			            			<div class="card mb-4" id="plasmodium-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable3Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="cargaplasmoduimtable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totcases" /></th>
						                      <th><spring:message code="vivax" /></th>
						                      <th><spring:message code="falciparum" /></th>
						                      <th><spring:message code="otro" /></th>
						                      <th><spring:message code="sdtb" /></th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla carga tipo busqueda-->
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
			            <!-- / carga pagination -->
			            
			            
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
    
    <spring:url value="/resources/vendor/libs/leaflet/choropleth.js" var="leafletCloJS" />
    <script src="${leafletCloJS}" type="text/javascript"></script>

 	<spring:url value="/resources/js/views/epid.js" var="Epid" />
  	<script src="${Epid}" type="text/javascript"></script>
  	
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
  	<spring:url value="/view/epid/canal/" var="canalUrl"/>
  	<spring:url value="/view/epid/carga/" var="cargaUrl"/>
  	<spring:url value="/resources/maps/provincias.geojson" var="ProvinciasPanama" />
    <script src="${ProvinciasPanama}" type="text/javascript"></script>
    
    <spring:url value="/resources/maps/distritos.geojson" var="DistritosPanama" />
    <script src="${DistritosPanama}" type="text/javascript"></script>
    
    <spring:url value="/resources/maps/corregimientos.geojson" var="CorregimientosPanama" />
    <script src="${CorregimientosPanama}" type="text/javascript"></script>
    
    <spring:url value="/view/epid/epidmapa/" var="mapaUrl"/>
  	
  	<spring:url value="/api/regiones" var="opcRegUrl"/>
  	<spring:url value="/api/provincias" var="opcProvUrl"/>
  	<spring:url value="/api/distritos" var="opcDistUrl"/>
  	<spring:url value="/api/corregimientos" var="opcCorregUrl"/>
  	<spring:url value="/api/focos" var="opcFocosUrl"/>
  	<spring:url value="/api/localidades/" var="localidadesUrl"/>
  	
  	<spring:url value="/resources/help/epi.html" var="helpUrl"/>
  	
  	<!-- Mensajes -->
  	<c:set var="seleccionar"><spring:message code="select" /></c:set>
  	<c:set var="ourequerida"><spring:message code="ou.required" /></c:set>
  	<c:set var="canal"><spring:message code="canal" /></c:set>
  	<c:set var="total"><spring:message code="total" /></c:set>
  	<c:set var="casos"><spring:message code="confirmed" /></c:set>
  	<c:set var="tipobusq"><spring:message code="tipobusq" /></c:set>
  	<c:set var="active"><spring:message code="active" /></c:set>
  	<c:set var="pasive"><spring:message code="pasive" /></c:set>
  	<c:set var="reactive"><spring:message code="reactive" /></c:set>
  	<c:set var="survey"><spring:message code="survey" /></c:set>
  	<c:set var="sdtb"><spring:message code="sdtb" /></c:set>
  	
  	

	<script>
		jQuery(document).ready(function() {
			var parametros = {canalUrl: "${canalUrl}",cargaUrl: "${cargaUrl}",
					seleccionar: "${seleccionar}",opcRegUrl: "${opcRegUrl}",localidadesUrl: "${localidadesUrl}"
						, ourequerida: "${ourequerida}",opcProvUrl: "${opcProvUrl}",opcDistUrl: "${opcDistUrl}",opcCorregUrl: "${opcCorregUrl}",opcFocosUrl: "${opcFocosUrl}"
							,total: "${total}",canal: "${canal}",dataTablesLang: "${dataTablesLang}",casos: "${casos}",lenguaje: "${lenguaje}"
								,tipobusq: "${tipobusq}",active: "${active}",pasive: "${pasive}",sdtb: "${sdtb}",reactive: "${reactive}",survey: "${survey}",mapaUrl: "${mapaUrl}"};
			ProcessDashboardEpid.init(parametros);
			
			if ($('html').attr('dir') === 'rtl') {
				$('#navbar-filtros .dropdown-menu').addClass('dropdown-menu-right');
			}
			
	    	$("li.tableros").addClass("open");
	    	$("li.tableros").addClass("active");
	    	$("li.epid").addClass("active");
	    });
		function cargarAyuda(){ 
			window.open("${helpUrl}");
		}
	</script>
</body>
</html>
