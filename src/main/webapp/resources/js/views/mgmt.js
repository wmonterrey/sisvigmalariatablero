var ProcessDashboardMgmt = function () {
	
	
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
			}, false);
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
		  tratamientos();
		  tratamientocompleto();
		  diasdx();
	  }
	});

  var chart1 = new Chart(document.getElementById('trat-chart').getContext("2d"), {});
  tratamientos();
  
  var chart2 = new Chart(document.getElementById('tratcomp-chart').getContext("2d"), {});
  tratamientocompleto();
  
  var chart3 = new Chart(document.getElementById('diasdx-chart').getContext("2d"), {});
  diasdx();
  
  
  
  //Grafico de tratamientos
  function tratamientos (){
	  $('#trat-element').block({
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
	  
	  $.getJSON(parametros.tratUrl, $('#filters-form').serialize(), function(data) {
		  var contrat = 0;
		  var sintrat = 0;
		  var total = 0;
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart1Title').html(parametros.trat);
		  }
		  else{
			  $('#labelChart1Title').html(parametros.trat + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  if(data[row][0]==1){
				  contrat = contrat + data[row][1]
			  }
			  total = total + data[row][1]
		  }
		  
		  sintrat = total - contrat;

		  
		  chart1.destroy();
		  chart1 = new Chart(document.getElementById('trat-chart').getContext("2d"), {
		    type: 'doughnut',
		    data: {
				datasets: [{
					data: [
						contrat,
						sintrat
					],
					backgroundColor: [
						
						'#23916e',
						'#eb4034',
					],
					label: parametros.trat
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
		  $('#trat-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#trat-element').unblock();
	  });
  }
 
  
//Grafico de tratamiento completo
  function tratamientocompleto (){
	  $('#tratcomp-element').block({
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
	  
	  $.getJSON(parametros.tratcompUrl, $('#filters-form').serialize(), function(data) {
		  var tratcomps = 0;
		  var tratcompn = 0;
		  var tratcompnull = 0;
		  var total = 0;
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart2Title').html(parametros.tratcomp);
		  }
		  else{
			  $('#labelChart2Title').html(parametros.tratcomp + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  if(data[row][0]==1){
				  tratcomps = tratcomps + data[row][1]
			  }
			  else if(data[row][0]==0){
				  tratcompn = tratcompn + data[row][1]
			  }
			  total = total + data[row][1]
		  }
		  
		  tratcompnull = total - tratcomps - tratcompn;

		  
		  chart2.destroy();
		  chart2 = new Chart(document.getElementById('tratcomp-chart').getContext("2d"), {
		    type: 'doughnut',
		    data: {
				datasets: [{
					data: [
						tratcomps,
						tratcompn,
						tratcompnull
					],
					backgroundColor: [
						
						'#23916e',
						'#eb4034',
						'##36bdf7'
					],
					label: parametros.tratcomp
				}],
				labels: [
					parametros.yes,
					parametros.no,
					'null'
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
		            fontColor: ['white','white','white'],
		            precision: 2
		          }
		        }
			}
		  });
		  chart2.resize();
		  $('#tratcomp-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#tratcomp-element').unblock();
	  });
  }
  
  
//Grafico de tratamiento completo
  function diasdx (){
	  $('#diasdx-element').block({
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
	  
	  $.getJSON(parametros.diasdxUrl, $('#filters-form').serialize(), function(data) {
		  var cats = [];
		  var datos = [];
		  
		  
		  if($('#oulevel').val()=="ALL"){
			  $('#labelChart3Title').html(parametros.diasdx);
		  }
		  else{
			  $('#labelChart3Title').html(parametros.diasdx + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  cats.push([data[row][0]]);
			  datos.push([data[row][1]]);
			  
		  }

		  
		  chart3.destroy();
		  chart3 = new Chart(document.getElementById('diasdx-chart').getContext("2d"), {
			    type: 'bar',
			    data: {
			      labels: cats,
			      datasets: [{
			        label: parametros.diasdx,
			        data: datos,
			        borderWidth: 1,
			        backgroundColor: 'rgba(28,180,255,.05)',
			        borderColor: 'rgba(28,180,255,1)'
			      }
			      ],
			    },
			    options: {
			      scales: {
			        xAxes: [{
			          gridLines: {
			            display: false
			          },
			          ticks: {
			            fontColor: '#aaa'
			          },
			          stacked: true
			        }],
			        yAxes: [{
			          gridLines: {
			            display: false
			          },
			          ticks: {
			            fontColor: '#aaa'
			          },
			          stacked: true
			        }]
			      },
			
			      responsive: true,
			      maintainAspectRatio: false,
					plugins: {
				          labels: {
				            render: 'percentage',
				            fontColor: ['white','white','white'],
				            precision: 2
				          }
				        }
			    }
			  });
			  chart3.resize();
		  $('#diasdx-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#diasdx-element').unblock();
	  });
  }
 


 // Resizing charts

 function resizeCharts() {
	 chart1.resize();
	 chart2.resize();
	 chart3.resize();
 }

 // For performance reasons resize charts on delayed resize event
 window.layoutHelpers.on('resize.mgmt', resizeCharts);
    
 }
 
};

}();
