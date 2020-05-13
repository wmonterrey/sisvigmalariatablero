<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="default-style">
<head>
  <jsp:include page="fragments/headTag.jsp" />
</head>
<body>
	<div class="page-loader">
    	<div class="bg-primary"></div>
  	</div>
  	
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-2">
		<div class="layout-inner">
			<jsp:include page="fragments/sideNav.jsp" />
			<!-- Layout container -->
			<div class="layout-container">
				<!-- Layout navbar -->
				<nav class="layout-navbar navbar navbar-expand-lg align-items-lg-center bg-white container-p-x" id="layout-navbar">
					<jsp:include page="fragments/navHeader.jsp" />
					<ul class="navbar-nav mr-auto">
						<li class="nav-item mega-dropdown">
							<a class="nav-link dropdown-toggle navbar-filtros-mega-dropdown" href="#" data-toggle="mega-dropdown" data-trigger="hover">
								<i class="fa fa-filter navbar-icon align-middle"><spring:message code="filters" /></i>
								<span class="d-lg-none align-middle"></span>
							</a>
							<div class="dropdown-menu p-4">
								<div class="card mb-4">
									<h6 class="card-header">
										<spring:message code="filters" />
									</h6>
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
						               		<div class="form-row">
											<div class="form-group col-md-3">
						                    	<label class="form-label"><spring:message code="timeview" /></label>
						                    	<div class="select2-primary">
						                  			<select id="timeview" name="timeview" class="select2-filtro form-control" style="width: 100%">
						                    			<option value="week.samp"><spring:message code="week.samp" /></option>
						                    			<option value="month.samp"><spring:message code="month.samp" /></option>
						                    			<option value="day.samp"><spring:message code="day.samp" /></option>
						                  			</select>
						               			</div>
						                    </div>
						                    </div>
										</form>
									</div>
								</div>
							</div>
						</li>
						</ul>
					<jsp:include page="fragments/navHeader2.jsp" />
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
			            <!-- Conteos -->
			            <div class="row">
			              <div class="col-sm-6 col-xl-4">
			
			                <div class="card mb-4" id="confirmed-number">
			                  <div class="card-body">
			                    <div class="d-flex align-items-center">
			                      <div class="lnr lnr-cross-circle display-4 text-danger"></div>
			                      <div class="ml-3">
			                        <div class="text-muted small"><label id="labelConfirmed"><spring:message code="confirmed" /></label></div>
			                        <div class="text-large"><label id="confirmed"></label></div>
			                      </div>
			                    </div>
			                  </div>
			                </div>
			
			              </div>
			              <div class="col-sm-6 col-xl-4">
			
			                <div class="card mb-4" id="samples-number">
			                  <div class="card-body">
			                    <div class="d-flex align-items-center">
			                      <div class="lnr lnr-book display-4 text-info"></div>
			                      <div class="ml-3">
			                        <div class="text-muted small"><spring:message code="samples" /></div>
			                        <div class="text-large"><label id="samples"></label></div>
			                      </div>
			                    </div>
			                  </div>
			                </div>
			
			              </div>
			              <div class="col-sm-6 col-xl-4">
			
			                <div class="card mb-4" id="localities-number">
			                  <div class="card-body">
			                    <div class="d-flex align-items-center">
			                      <div class="lnr lnr-map display-4 text-success"></div>
			                      <div class="ml-3">
			                        <div class="text-muted small"><spring:message code="localities" /></div>
			                        <div class="text-large"><label id="localities"></label></div>
			                      </div>
			                    </div>
			                  </div>
			                </div>
			
			              </div>
			              
			            </div>
			            <!-- / Conteos -->
			            
			            <!-- Navigation 3 -->
						<div class="swiper-container" id="swiper-with-pagination-regions">
			            	<div class="swiper-wrapper">
			            		<div class="swiper-slide">
			            			<!-- grafico regiones -->
			            			<div class="card mb-4" id="regions-element">
						              <h6 class="card-header with-elements">
						              	<div class="card-title-elements">
						              		<button id="anterior" type="button" class="btn icon-btn btn-outline-primary">
					                          <span class="ion ion-md-rewind"></span>
					                        </button>
					                        &nbsp;&nbsp;
						              	</div>
						                <div class="card-header-title"><label id="labelChart3Title"></label></div>
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
						                      <canvas id="regions-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico regiones -->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla regiones -->
			            			<div class="card mb-4" id="regions-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable3Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="regionstable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th></th>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totcases" /></th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla regiones -->
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
			            
			            <!-- Cases pagination -->
			            <div class="swiper-container" id="swiper-with-pagination-cases">
			            	<div class="swiper-wrapper">
			            		<div class="swiper-slide">
			            			<!-- grafico casos confirmados -->
			            			<div class="card mb-4" id="confirmed-element">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelChart1Title"></label></div>
						              </h6>
						              <div class="row no-gutters row-bordered">
						                <div class="col-md-12 col-lg-12 col-xl-12">
						                  <div class="card-body">
						                    <div style="height: 350px;">
						                      <canvas id="confirmed-cases-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico casos confirmados -->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla casos confirmados -->
			            			<div class="card mb-4" id="confirmed-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable1Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="casestable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th></th>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totcases" /></th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla casos confirmados -->
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
						
						<!-- Navigation 2 -->
						<div class="swiper-container" id="swiper-with-pagination-samples">
			            	<div class="swiper-wrapper">
			            		<div class="swiper-slide">
			            			<!-- grafico muestras -->
			            			<div class="card mb-4" id="samples-element">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelChart2Title"></label></div>
						              </h6>
						              <div class="row no-gutters row-bordered">
						                <div class="col-md-12 col-lg-12 col-xl-12">
						                  <div class="card-body">
						                    <div style="height: 350px;">
						                      <canvas id="samples-chart"></canvas>
						                    </div>
						                  </div>
						                </div>
						
						              </div>
						            </div>
						            <!-- / grafico muestras -->
			            		</div>
			            		<div class="swiper-slide">
			            			<!-- tabla muestras -->
			            			<div class="card mb-4" id="samples-element-2">
						              <h6 class="card-header with-elements">
						                <div class="card-header-title"><label id="labelTable2Title"></label></div>
						              </h6>
						              <div class="datatablediv">
						              	<table id="samplestable" class="table table-striped table-bordered">
						              		<thead>
						                    <tr>
						                      <th></th>
						                      <th><spring:message code="year" /></th>
						                      <th><spring:message code="totsamples" /></th>
						                      <th><spring:message code="level" /></th>
						                    </tr>
						                  </thead>
						                  <tbody>
										  </tbody>
						              	</table>
						              </div>
						            </div>
						            <!-- / tabla muestras -->
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
         			<!-- / Content -->
         			<jsp:include page="fragments/navFooter.jsp" />
        		</div>
        		<!-- Layout content -->
			</div>
			<!-- / Layout container -->
    	</div>
    </div>  	
    <!-- / Layout wrapper -->
	
  	<!-- Bootstrap and necessary plugins -->
  	<jsp:include page="fragments/corePlugins.jsp" />
  	<jsp:include page="fragments/bodyUtils.jsp" />

 	<spring:url value="/resources/js/views/portada.js" var="Portada" />
  	<script src="${Portada}" type="text/javascript"></script>
  	
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
  	<spring:url value="/view/portada/casos/" var="casosUrl"/>
  	<spring:url value="/view/portada/muestras/" var="muestrasUrl"/>
  	<spring:url value="/view/portada/regiones/" var="regionesUrl"/>
  	<spring:url value="/view/portada/locbusq/" var="locbusqUrl"/>
  	<spring:url value="/api/regiones" var="opcRegUrl"/>
  	<spring:url value="/api/provincias" var="opcProvUrl"/>
  	<spring:url value="/api/distritos" var="opcDistUrl"/>
  	<spring:url value="/api/corregimientos" var="opcCorregUrl"/>
  	<spring:url value="/api/focos" var="opcFocosUrl"/>
  	<spring:url value="/api/localidades/" var="localidadesUrl"/>
  	
  	<!-- Mensajes -->
  	<c:set var="casos"><spring:message code="confirmed" /></c:set>
  	<c:set var="muestras"><spring:message code="samples" /></c:set>
  	<c:set var="seleccionar"><spring:message code="select" /></c:set>
  	<c:set var="total"><spring:message code="total" /></c:set>
  	<c:set var="ourequerida"><spring:message code="ou.required" /></c:set>
  	
  	<c:set var="region"><spring:message code="region.samp" /></c:set>
  	<c:set var="distrito"><spring:message code="district.samp" /></c:set>
  	<c:set var="corregimiento"><spring:message code="correg.samp" /></c:set>
  	<c:set var="localidad"><spring:message code="local.samp" /></c:set>
  	
  	<spring:url value="/resources/help/home.html" var="helpUrl"/>
  	

	<script>
		jQuery(document).ready(function() {
			var parametros = {casosUrl: "${casosUrl}", muestrasUrl: "${muestrasUrl}", regionesUrl: "${regionesUrl}", locbusqUrl: "${locbusqUrl}",lenguaje: "${lenguaje}",dataTablesLang: "${dataTablesLang}",
					casos: "${casos}", muestras: "${muestras}",seleccionar: "${seleccionar}",opcRegUrl: "${opcRegUrl}",localidadesUrl: "${localidadesUrl}"
						, ourequerida: "${ourequerida}",opcProvUrl: "${opcProvUrl}",opcDistUrl: "${opcDistUrl}",opcCorregUrl: "${opcCorregUrl}",opcFocosUrl: "${opcFocosUrl}",total: "${total}"
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
		function cargarAyuda(){ 
			window.open("${helpUrl}");
		}
	</script>
</body>
</html>
