var ProcessDashboardVig = function () {
	
	
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
		$("#actualizar").click();

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
	  $("#actualizar").click();
  });


  // Create a string representation of the date.
  function formatDate(date) {
	 var yyyy = date.getFullYear().toString();                                    
     var mm = (date.getMonth()+1).toString(); // getMonth() is zero-based         
     var dd  = date.getDate().toString();             
     return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
  }

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
	  $("#actualizar").click();
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
  

 $('#divouname').hide();
  
  $('#oulevel').change(
		  function() {
			$('#ouname').html('');
  			if ($('#oulevel option:selected').val() == "ALL"){
  				$('#divouname').hide();
  			}
  			else if ($('#oulevel option:selected').val() == "province.samp"){
  				$("#ouname").wrap('<div class="position-relative"></div>')
  		        .select2({
  		          placeholder: parametros.seleccionar,
  		          dropdownParent: $(this).parent(),
  		          language:parametros.lenguaje
  		        });
  				$('#divouname').show();
  				$.getJSON(parametros.opcProvUrl, {
    				ajax : 'true'
    			}, function(data) {
    				var html;
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					html += '<option value="' + data[i].ident + '">'+ data[i].name +'</option>';
    				}
    				$('#ouname').html(html);
    			});
  			}
  			else if ($('#oulevel option:selected').val() == "region.samp"){
  				$("#ouname").wrap('<div class="position-relative"></div>')
  		        .select2({
  		          placeholder: parametros.seleccionar,
  		          dropdownParent: $(this).parent(),
  		          language:parametros.lenguaje
  		        });
  				$('#divouname').show();
  				$.getJSON(parametros.opcRegUrl, {
    				ajax : 'true'
    			}, function(data) {
    				var html;
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					html += '<option value="' + data[i].ident + '">'+ data[i].name +'</option>';
    				}
    				$('#ouname').html(html);
    			});
  			}
  			else if ($('#oulevel option:selected').val() == "district.samp"){
  				$("#ouname").wrap('<div class="position-relative"></div>')
  		        .select2({
  		          placeholder: parametros.seleccionar,
  		          dropdownParent: $(this).parent(),
  		          language:parametros.lenguaje
  		        });
  				$('#divouname').show();
  				$.getJSON(parametros.opcDistUrl, {
    				ajax : 'true'
    			}, function(data) {
    				var html;
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					html += '<option value="' + data[i].ident + '">'+ data[i].name + ' - ' + data[i].region.name +'</option>';
    				}
    				$('#ouname').html(html);
    			});
  			}
  			else if ($('#oulevel option:selected').val() == "correg.samp"){
  				$("#ouname").wrap('<div class="position-relative"></div>')
  		        .select2({
  		          placeholder: parametros.seleccionar,
  		          dropdownParent: $(this).parent(),
  		          language:parametros.lenguaje
  		        });
  				$('#divouname').show();
  				$.getJSON(parametros.opcCorregUrl, {
    				ajax : 'true'
    			}, function(data) {
    				var html;
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					html += '<option value="' + data[i].ident + '">'+ data[i].name + ' - ' + data[i].distrito.region.name +'</option>';
    				}
    				$('#ouname').html(html);
    			});
  			}
  			else if ($('#oulevel option:selected').val() == "foci.samp"){
  				$("#ouname").wrap('<div class="position-relative"></div>')
  		        .select2({
  		          placeholder: parametros.seleccionar,
  		          dropdownParent: $(this).parent(),
  		          language:parametros.lenguaje
  		        });
  				$('#divouname').show();
  				$.getJSON(parametros.opcFocosUrl, {
    				ajax : 'true'
    			}, function(data) {
    				var html;
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					html += '<option value="' + data[i].ident + '">'+ data[i].name  +'</option>';
    				}
    				$('#ouname').html(html);
    			});
  			}
  			else if($('#oulevel option:selected').val() == "local.samp"){
  				$('#divouname').show();
  				$("#ouname").wrap('<div class="position-relative"></div>').select2({
  					placeholder: parametros.seleccionar,
  					dropdownParent: $(this).parent(),
  					language:parametros.lenguaje,
  					  ajax: {
  					    url: parametros.localidadesUrl,
  					    dataType: 'json',
  					    delay: 250,
  					    data: function (params) {
  					      return {
  					        filtro: params.term
  					      };
  					    },
  					    processResults: function (data, params) {
  					      return {
  					        results: data.items,
  					        pagination: {
  					          more: (params.page * 30) < data.total
  					        }
  					      };
  					    },
  					    cache: true
  					  },
  					  escapeMarkup: function (markup) { return markup; }, 
  					  minimumInputLength: 3,
  					  templateResult: formatLocalidad,
  					  templateSelection: formatLocalidadSelection
  					});

  					function formatLocalidad (localidad) {
  						if (localidad.loading) {
  						    return localidad.text;
  						}
  					  	
  						var markup = "<div class='clearfix'>" +
  					    	"<div>Localidad     : " + localidad.text + "</div>" +
  					    	"<div>Corregimiento : " + localidad.localidad.corregimiento.name + "</div>" +
  					    	"<div>Distrito      : " + localidad.localidad.corregimiento.distrito.name + "</div>"+
  					    	"<div>Región        : " + localidad.localidad.corregimiento.distrito.region.name + "</div>"+
  					    	"<div>Provincia     : " + localidad.localidad.corregimiento.distrito.provincia.name + "</div>";
  					  	
  					    return markup;
  					}

  					function formatLocalidadSelection (localidad) {
  						if (localidad.localidad) {
  							return localidad.localidad.name + " Corregimiento: " +localidad.localidad.corregimiento.name + " Región: " +localidad.localidad.corregimiento.distrito.region.name;
  						}
  						return localidad.text;
  					}
  			}
          });
    
  $("#actualizar").on("click", function(evt) {
	  evt.preventDefault();
	  if($('#oulevel option:selected').val() != "ALL" && $('#ouname').select2('data')[0]===undefined){
		  alert(parametros.ourequerida);
	  }else{
		  viajes();
		  clasificacion();
		  cpdr();
	  }
	});

  var chart1 = new Chart(document.getElementById('viajes-chart').getContext("2d"), {});
  viajes();
  
  var chart2 = new Chart(document.getElementById('clasificacion-chart').getContext("2d"), {});
  clasificacion();
  
  var chart3 = new Chart(document.getElementById('cpdr-chart').getContext("2d"), {});
  cpdr();
  
  
  //Grafico de viajes
  function viajes (){
	  $('#viajes-element').block({
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
	  
	  $.getJSON(parametros.viajesUrl, $('#filters-form').serialize(), function(data) {
		  var conhistoria = 0;
		  var sinhistoria = 0;
		  var total = 0;
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart1Title').html(parametros.travel);
		  }
		  else{
			  $('#labelChart1Title').html(parametros.travel + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  if(data[row][0]==1||data[row][0]==0){
				  conhistoria = conhistoria + data[row][1]
			  }
			  total = total + data[row][1]
		  }
		  
		  sinhistoria = total - conhistoria;

		  
		  chart1.destroy();
		  chart1 = new Chart(document.getElementById('viajes-chart').getContext("2d"), {
		    type: 'doughnut',
		    data: {
				datasets: [{
					data: [
						conhistoria,
						sinhistoria
					],
					backgroundColor: [
						
						'#23916e',
						'#eb4034',
					],
					label: parametros.travel
				}],
				labels: [
					parametros.yes,
					parametros.no
				]
			},
			options: {
				circumference: Math.PI,
		        rotation : Math.PI,
		        cutoutPercentage : 50,
				responsive: true,
				legend: {
					position: 'top',
				},
				animation: {
					animateScale: true,
					animateRotate: true
				},
				plugins: {
		          labels: {
		            render: 'percentage',
		            fontColor: ['white','white'],
		            precision: 2
		          }
		        }
			}
		  });
		  chart1.resize();
		  $('#viajes-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#viajes-element').unblock();
	  });
  }
 
  
  //Grafico de clasificacion
  function clasificacion (){
	  $('#clasificacion-element').block({
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
	  
	  $.getJSON(parametros.clasUrl, $('#filters-form').serialize(), function(data) {
		  var conclas = 0;
		  var sinclas = 0;
		  var total = 0;
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart2Title').html(parametros.clas);
		  }
		  else{
			  $('#labelChart2Title').html(parametros.clas + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  if(data[row][0]==1){
				  conclas = conclas + data[row][1]
			  }
			  total = total + data[row][1]
		  }
		  
		  sinclas = total - conclas;
		  
		  chart2.destroy();
		  chart2 = new Chart(document.getElementById('clasificacion-chart').getContext("2d"), {
		    type: 'doughnut',
		    data: {
				datasets: [{
					data: [
						conclas,
						sinclas
					],
					backgroundColor: [
						
						'#23916e',
						'#eb4034',
					],
					label: parametros.clas
				}],
				labels: [
					parametros.yes,
					parametros.no
				]
			},
			
			options: {
				circumference: Math.PI,
		        rotation : Math.PI,
		        cutoutPercentage : 50,
				responsive: true,
				legend: {
					position: 'top',
				},
				animation: {
					animateScale: true,
					animateRotate: true
				},
				plugins: {
		          labels: {
		            render: 'percentage',
		            fontColor: ['white','white'],
		            precision: 2
		          }
		        }
			}
		  });
		  chart2.resize();
		  $('#clasificacion-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#clasificacion-element').unblock();
	  });
  }
  
//Grafico de cpdr
  function cpdr (){
	  $('#cpdr-element').block({
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
	  
	  $.getJSON(parametros.cpdrUrl, $('#filters-form').serialize(), function(data) {
		  var cpdrs = 0;
		  var cpdrn = 0;
		  var total = 0;
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart3Title').html(parametros.cpdr);
		  }
		  else{
			  $('#labelChart3Title').html(parametros.cpdr + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  if(data[row][0]==null){
				  cpdrn = cpdrn + data[row][1]
			  }
			  total = total + data[row][1]
		  }
		  
		  cpdrs = total - cpdrn;
		  
		  chart3.destroy();
		  chart3 = new Chart(document.getElementById('cpdr-chart').getContext("2d"), {
		    type: 'doughnut',
		    data: {
				datasets: [{
					data: [
						cpdrs,
						cpdrn
					],
					backgroundColor: [
						
						'#23916e',
						'#eb4034',
					],
					label: parametros.cpdr
				}],
				labels: [
					parametros.yes,
					parametros.no
				]
			},
			options: {
				circumference: Math.PI,
		        rotation : Math.PI,
		        cutoutPercentage : 50,
				responsive: true,
				legend: {
					position: 'top',
				},
				animation: {
					animateScale: true,
					animateRotate: true
				},
				plugins: {
		          labels: {
		            render: 'percentage',
		            fontColor: ['white','white'],
		            precision: 2
		          }
		        }
			}
		  });
		  chart3.resize();
		  $('#cpdr-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#cpdr-element').unblock();
	  });
  }

 // Resizing charts

 function resizeCharts() {
	 chart1.resize();
	 chart2.resize();
	 chart3.resize();
 }

 // For performance reasons resize charts on delayed resize event
 window.layoutHelpers.on('resize.vig', resizeCharts);
    
 }
 
};

}();
