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
    
    <spring:url value="/resources/maps/provincias.geojson" var="ProvinciasPanama" />
    <script src="${ProvinciasPanama}" type="text/javascript"></script>
    
    <spring:url value="/view/portada/provinciasmapa/" var="provinciasUrl"/>
    
  	
  	<!-- Lenguaje -->
	<c:choose>
	<c:when test="${cookie.esisvigLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.esisvigLang.value}"/>
	</c:otherwise>
	</c:choose>
	
	<script type="text/javascript">
	
		function getData( callback ){
		 	$.getJSON("${provinciasUrl}","",function(data) {
		 		callback(data);
		  	})
		  	.fail(function() {
				  alert( data );
		  	});
		}
		
		
		getData( function ( datos ) { 
    		for (var row in datos) {
     			ponerValor(datos[row][0],datos[row][1]);
    		}
    		for (var i=0; i<provinciasData.features.length;i++){
	 			if(provinciasData.features[i].properties.cases==null){
					provinciasData.features[i].properties.cases=0;
	 			}
			}
    		var mymap = L.map('mapid').setView([8.398598, -80.108896], 8);
    	 	var theMarker = {};
    	  	var locMarkers = new L.FeatureGroup();
    	  	L.tileLayer('http://a.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    			maxZoom: 18,
    			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
    				'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
    				'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    			id: 'mapbox.streets'
    		}).addTo(mymap);
    	  	
    	  	
    	  	
    	 	// control that shows info on hover
    		var info = L.control();

    		info.onAdd = function (mymap) {
    			this._div = L.DomUtil.create('div', 'info');
    			this.update();
    			return this._div;
    		};

    		info.update = function (props) {
    			this._div.innerHTML = '<h4>Casos confirmados</h4>' +  (props ?
    				'<b>' + props.name + '</b><br />' + props.cases 
    				: '');
    		};
    		
    		info.addTo(mymap);
    		
    		var geojson;
    		
    		geojson = L.choropleth(provinciasData, {
    			valueProperty: 'cases',
    	        scale: ['blue', 'red'],
    	        steps: 4,
    	        mode: 'q',
    	        style: {
    	          color: '#fff',
    	          weight: 2,
    	          fillOpacity: 0.7
    	        },
    			onEachFeature: onEachFeature
    		}).addTo(mymap);
    		
    		var legend = L.control({position: 'bottomright'});

    		legend.onAdd = function (mymap) {

    			var div = L.DomUtil.create('div', 'info legend');
    			var limits = geojson.options.limits;
    			var colors = geojson.options.colors;
    		    var labels = [];
    			
    		    limits.forEach(function (limit, index) {
    		        if (index === 0) {
    		            var to = parseFloat(limits[index]).toFixed(0);
    		            var range_str = "<= " + to;
    		        }
    		        else {
    		            var from = parseFloat(limits[index - 1]).toFixed(0);
    		            var to = parseFloat(limits[index]).toFixed(0);
    		            var range_str = "> "+from + "-" + to;
    		        }

    		        
    		        labels.push(
    						'<i style="background:' + colors[index] + '"></i> ' +
    						range_str);
    		    })
	
	    		div.innerHTML = labels.join('<br>');
    			return div;
    		};

    		legend.addTo(mymap);
    		
    		function ponerValor(prov,valor) {
    	 		for (var i=0; i<provinciasData.features.length;i++){
    	 			if(provinciasData.features[i].properties.id==prov){
    					provinciasData.features[i].properties.cases=valor;
    	 			}
    			}
    		}

    		
    		
    		function highlightFeature(e) {
    			var layer = e.target;

    			layer.setStyle({
    				weight: 5,
    				color: '#666',
    				dashArray: '',
    				fillOpacity: 0.7
    			});

    			if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
    				layer.bringToFront();
    			}

    			info.update(layer.feature.properties);
    		}
    	  	

    		function resetHighlight(e) {
    			geojson.resetStyle(e.target);
    			info.update();
    		}

    		function zoomToFeature(e) {
    			mymap.fitBounds(e.target.getBounds());
    		}
    		
    		function onEachFeature(feature, layer) {
    			layer.on({
    				mouseover: highlightFeature,
    				mouseout: resetHighlight,
    				click: zoomToFeature
    			});
    		}
    	});
		
	 	
	 	
		
		$("li.maps").addClass("active");
	  	
	</script>
</body>
</html>
