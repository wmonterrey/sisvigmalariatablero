var ProcessMap = function () {
	
	
return {
  //main function to initiate the module
  init: function (parametros) {

	  // Select2
	  $(function() {
	    $('.select2-filtro').each(function() {
	      $(this)
	        .wrap('<div class="position-relative"></div>')
	        .select2({
	          placeholder: parametros.seleccionar,
	          dropdownParent: $(this).parent()
	        });
	    })
	  });	  
	  
	  $('#anio').change(
		function() {
			var thisYear = $('#anio').val();    
			var startDate =moment(new Date("1/1/" + thisYear));
			var endDate = moment(new Date("12/31/" + thisYear));
			var today = moment();
			  
			endDate > today ? endDate = today: endDate=endDate;
			
			dateSlider.noUiSlider.updateOptions({	
			    range: {
			    	min: timestamp(startDate),
			        max: timestamp(endDate)
			    },
			    start: [timestamp(startDate), timestamp(endDate)],
			},false);
		  bloquearElemento();
		  if(mymap.hasLayer(layerDatos)) layerGroup.removeLayer(layerDatos);
    	  mymap.removeControl(legend);
    	  getData(mymap.getZoom());

	  });	  
	  
	  var isRtl = $('html').attr('dir') === 'rtl';
	  
	  var dateSlider = document.getElementById('slider-date');
	  var firstDate = new Date("12/15/2017");
	  
	  var thisYear = $('#anio').val();    
	  var startDate =moment(new Date("1/1/" + thisYear));
	  var endDate = moment(new Date("12/31/" + thisYear));
	  
	  var today = moment();
	  
	  endDate > today ? endDate = today: endDate=endDate;
	  
	  moment.locale(parametros.lenguaje);
	  
	  //Create a new date from a string, return as a timestamp.
	  function timestamp(str) {
	      return new Date(str).getTime();
	  }

	  noUiSlider.create(dateSlider, {
		  // Create two timestamps to define a range.
	  range: {
	      min: timestamp(startDate),
	      max: timestamp(endDate)
	  },

	  // Steps of one day
	  step: 24 * 60 * 60 * 1000,
	  direction: isRtl ? 'rtl' : 'ltr',

	  // Two more timestamps indicate the handle starting positions.
	  
	  start: [timestamp(startDate), timestamp(endDate)],

	  connect: true,
	  behaviour: 'tap',
	  pips: {
		  mode: 'positions',
		      values: [],
		      stepped: true,
		      density: 2,
		      format: wNumb({
		            decimals: 0
		        })
		    }
	  });
	  
	  var inputStart = document.getElementById('fechaInicio');
	  var labelStart = document.getElementById('labelFechaInicio');
	  var inputEnd = document.getElementById('fechaFin');
	  var labelEnd = document.getElementById('labelFechaFin');

	  dateSlider.noUiSlider.on('update', function (values, handle) {
		inputStart.value = formatDate(new Date(+values[0]));
		labelStart.innerHTML = formatDate(new Date(+values[0]));
		inputEnd.value = formatDate(new Date(+values[1]));
		labelEnd.innerHTML = formatDate(new Date(+values[1]));
	  });

	  dateSlider.noUiSlider.on('set', function () {
		  bloquearElemento();
		  layerGroup.removeLayer(layerDatos);
    	  mymap.removeControl(legend);
    	  getData(mymap.getZoom());
	  });	  
	  
	  // Create a string representation of the date.
	  function formatDate(date) {
		 var yyyy = date.getFullYear().toString();                                    
	     var mm = (date.getMonth()+1).toString(); // getMonth() is zero-based         
	     var dd  = date.getDate().toString();             
	     return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
	  }	  
	  
	  
	  var layerDatos;
	  
	  var mymap = L.map('mapid').setView([8.398598, -80.108896], 8);
	  var layerGroup = new L.LayerGroup();
	  
	  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	  }).addTo(mymap);
	  layerGroup.addTo(mymap);
	  var legend = L.control( { position: 'bottomright' } );
	  
	  	
	  mymap.on('zoomend', function() {
		  actualizarMapa();
	  });
	  
	  
	  function cb(startDate, endDate) {
	      //$('#daterange-mc').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
		  // Set the value, creating a new option if necessary
		  if ($('#anio').find("option[value='" + startDate.year().toString() + "']").length) {
		      $('#anio').val(startDate.year().toString()).trigger('change.select2');
		  } else { 
		      // Create a DOM Option and pre-select by default
		      var newOption = new Option(startDate.year().toString(), startDate.year().toString(), true, true);
		      // Append it to the select
		      $('#anio').append(newOption).trigger('change.select2');
		  }
		  
		  dateSlider.noUiSlider.updateOptions({
			    range: {
			    	min: timestamp(startDate),
			        max: timestamp(endDate)
			    },
			    start: [timestamp(startDate), timestamp(endDate)],
			},false);
		  
		  bloquearElemento();
		  if(mymap.hasLayer(layerDatos)) layerGroup.removeLayer(layerDatos);
		  mymap.removeControl(legend);
		  getData();
	  }

	  $('#daterange-mc').daterangepicker({
	    startDate: startDate,
	    endDate: endDate,
	    minDate:firstDate,
	    maxDate:moment(),
	    locale:bdrp,
	    ranges: rangeLocale,
	   opens: (isRtl ? 'left' : 'right')
	  }, cb);

	  cb(startDate, endDate);

	  
	  
	  function getData(){
	  
		  var promise = $.getJSON(parametros.datosUrl, $('#filters-form').serialize());
		  
		  promise.then(function(data) {
			  var i = 0;
			  for (var i=0; i<regionesData.features.length;i++){
		 		regionesData.features[i].properties.cases=null;
			  }
			  for (var i=0; i<distritosData.features.length;i++){
		 		distritosData.features[i].properties.cases=null;
			  }
			  for (var i=0; i<corregimientosData.features.length;i++){
		 		corregimientosData.features[i].properties.cases=null;
			  }
			  for (var i=0; i<localidadesData.features.length;i++){
		 		localidadesData.features[i].properties.cases=null;
			  }
			  for (var row in data) {
				  i=0;
				  for (i==0;i<data[row].length;i++) {
					  if(row==0){
						  ponerValorRegion(data[row][i][0],data[row][i][1]);
					  }
					  if(row==1){
						  ponerValorDistrito(data[row][i][0],data[row][i][1]);
					  }
					  if(row==2){
						  ponerValorCorregimiento(data[row][i][0],data[row][i][1]);
					  }
					  if(row==3){
						  ponerValorLocalidad(data[row][i][0],data[row][i][1]);
					  }
				  }
			  }
			  cargarMapa(mymap.getZoom());
		  });
	  }
	  
	  
	  function cargarMapa(nivel){
		  var datosVal =[];
		  var dataDatos;
		  var leyendaTitulo;
		  var colorPunto;
		  var bordePunto;
		  if(nivel<=8){
			  dataDatos = regionesData;
			  leyendaTitulo = parametros.creg;
			  colorPunto = "#377eb8";
			  bordePunto = "#245277";
			  cssCirculo = "legendCircle1";
		  }
		  else if(nivel==9){
			  dataDatos = distritosData;
			  leyendaTitulo = parametros.cdis;
			  colorPunto = "#4daf4a";
			  bordePunto = "#2f6a2d";
			  cssCirculo = "legendCircle2";
		  }
		  else if(nivel==10){
			  dataDatos = corregimientosData;
			  leyendaTitulo = parametros.ccor;
			  colorPunto = "#984ea3";
			  bordePunto = "#5a2e60";
			  cssCirculo = "legendCircle3";
		  }
		  else if(nivel>=11){
			  dataDatos = localidadesData;
			  leyendaTitulo = parametros.cloc;
			  colorPunto = "#ff7f00";
			  bordePunto = "#a75300";
			  cssCirculo = "legendCircle4";
		  }
		  else{
			  dataDatos = null;
			  leyendaTitulo = "";
		  }
		  layerDatos = L.geoJson(dataDatos, {		
			  pointToLayer: function(feature, latlng) {	
	    		  if(feature.properties.cases>0){
	    			  return L.circleMarker(latlng, { 
	    	  				fillColor: colorPunto,
	    	  				color: bordePunto,
	    	  				weight: 1, 
	    	  				fillOpacity: 0.3 
	    			  }).on({
	    				  mouseover: function(e) {
	    					  this.openPopup();
	    					  this.setStyle({color: 'yellow'});
	    				  },
	    				  mouseout: function(e) {
	    					  this.closePopup();
	    					  this.setStyle({color: bordePunto});						
	    				  },
	    				  click: function(e){
	    					  mymap.setView(e.target.getLatLng(),mymap.getZoom()+1);
	    				  }
	    			  });
	    		  }
	    	  }
		  });
		  layerGroup.addLayer(layerDatos);
		  layerDatos.eachLayer(function(layer) {
			  var casos = layer.feature.properties.cases;
			  var radius = calcPropRadius(casos);
			  datosVal.push(casos);
			  var popupContent;
			  if(nivel<=8){
				  popupContent = "<b>" + casos + " " + parametros.cases +
					" </b><br>" +
					"<i>" + layer.feature.properties.nombre_region;
			  }
			  else if(nivel==9){
				  popupContent = "<b>" + casos + " " + parametros.cases +
					" </b><br>" +
					"<i>" + layer.feature.properties.nombre_distrito;
			  }
			  else if(nivel==10){
				  popupContent = "<b>" + casos + " " + parametros.cases +
					" </b><br>" +
					"<i>" + layer.feature.properties.nombre_corregimiento;
			  }
			  else if(nivel>=11){
				  popupContent = "<b>" + casos + " " + parametros.cases +
					" </b><br>" +
					"<i>" + layer.feature.properties.LOCALIDAD_v2;
			  }
			  else{
				  dataDatos = null;
				  leyendaTitulo = "";
			  }
			  layer.setRadius(radius);
			  layer.bindPopup(popupContent, { offset: new L.Point(0,-radius) });
		  });
		  legend = L.control( { position: 'bottomright' } );
		  
		  legend.onAdd = function(mymap) {
			  	var radio1 = Math.min.apply(Math,datosVal);
			  	var radio4 = Math.max.apply(Math,datosVal);
				var radio2 = (radio4-radio1)/3;
				var radio3 = ((radio4-radio1)*2)/3;
				var legendContainer = L.DomUtil.create("div", "legend");  
				var symbolsContainer = L.DomUtil.create("div", "symbolsContainer");
				var classes = [radio1,radio2,radio3,radio4]; 
				var legendCircle;  
				var lastRadius = 0;
				var currentRadius;		
				var margin;

				L.DomEvent.addListener(legendContainer, 'mousedown', function(e) { 
					L.DomEvent.stopPropagation(e); 
				});  

				$(legendContainer).append("<h5 id='legendTitle'>"+ leyendaTitulo +"</h5>");

				for (var i = 0; i <= classes.length-1; i++) {  
					legendCircle = L.DomUtil.create("div", cssCirculo);  
					currentRadius = calcPropRadius(classes[i],nivel);
					margin = -currentRadius - lastRadius - 2;
					$(legendCircle).attr("style", "width: " + currentRadius*2 + 
							"px; height: " + currentRadius*2 + 
							"px; margin-left: " + margin + "px" );				
					$(legendCircle).append("<span class='legendValue'>"+roundNumber(classes[i])+"</span>");
					$(symbolsContainer).append(legendCircle);
					lastRadius = currentRadius;
				}

				$(legendContainer).append(symbolsContainer); 

				return legendContainer;
		  }
		  legend.addTo(mymap);
		  $('#map-element').unblock();
	  }
	  
	  function actualizarMapa(){
		  var zoomFinal = mymap.getZoom();
		  if(zoomFinal>=7 && zoomFinal <=12){
			  bloquearElemento();
			  if(mymap.hasLayer(layerDatos)) layerGroup.removeLayer(layerDatos);
	    	  mymap.removeControl(legend);
			  cargarMapa(zoomFinal);
		  }
	  }
	  
	  function ponerValorRegion(region,valor) {
 		for (var i=0; i<regionesData.features.length;i++){
 			if(regionesData.features[i].properties.id_region==region){
 				regionesData.features[i].properties.cases=valor;
 			}
		}
	  }
	  
	  function ponerValorDistrito(distrito,valor) {
 		for (var i=0; i<distritosData.features.length;i++){
 			if(distritosData.features[i].properties.id_distrito==distrito){
 				distritosData.features[i].properties.cases=valor;
 			}
		}
	  }

	  function ponerValorCorregimiento(corregimiento,valor) {
 		for (var i=0; i<corregimientosData.features.length;i++){
 			if(corregimientosData.features[i].properties.id_corregimiento==corregimiento){
 				corregimientosData.features[i].properties.cases=valor;
 			}
		}
	  }
	  
	  function ponerValorLocalidad(localidad,valor) {
 		for (var i=0; i<localidadesData.features.length;i++){
 			if(localidadesData.features[i].properties.ID1==localidad){
 				localidadesData.features[i].properties.cases=valor;
 			}
		}
	  }

	  function roundNumber(inNumber) {
		  return (Math.round(inNumber/10) * 10);  
	  }
	
	  function calcPropRadius(attributeValue, level) {
		  var scaleFactor = 6;
		  var area = attributeValue * scaleFactor;
		  return Math.sqrt(area/Math.PI)*2;
	  }
	  
	  function bloquearElemento(){
		  $('#map-element').block({
		      message: '<div class="sk-wave sk-primary"><div class="sk-rect sk-rect1"></div> <div class="sk-rect sk-rect2"></div> <div class="sk-rect sk-rect3"></div> <div class="sk-rect sk-rect4"></div> <div class="sk-rect sk-rect5"></div></div>',
		      css: {
		        backgroundColor: 'transparent',
		        border: '0'
		      },
		      overlayCSS:  {
		        backgroundColor: '#fff',
		        opacity: 0.8
		      }
		    });
	  }
    
 }
 
};

}();
