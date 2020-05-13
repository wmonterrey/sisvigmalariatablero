var ProcessDashboardPortada = function () {
	
	
return {
  //main function to initiate the module
  init: function (parametros) {
	  
  var clickChart = false;	 
  var label = "";

  // Select2
  $(function() {
    $('.select2-filtro').each(function() {
      $(this)
        .wrap('<div class="position-relative"></div>')
        .select2({
          placeholder: parametros.seleccionar,
          dropdownParent: $(this).parent(),
          language:parametros.lenguaje
        });
    })
  });
  
  $('#divouname').hide();
  
  $('#oulevel').change(
		  function() {
			$('#divouname').hide();  
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
    				if(clickChart){
    					$('#ouname').val($("#ouname option:contains('"+label+"')").val()).change();
    					$("#actualizar").click();
    					clickChart = false;
    				}
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
    				if(clickChart){
    					$('#ouname').val($("#ouname option:contains('"+label+"')").val()).change();
    					$("#actualizar").click();
    					clickChart = false;
    				}
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
    				if(clickChart){
    					$('#ouname').val($("#ouname option:contains('"+label+"')").val()).change();
    					$("#actualizar").click();
    					clickChart = false;
    				}
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
  
  
  //Swiper
  
  var swiperWithPagination1 = new Swiper('#swiper-with-pagination-cases', {
    pagination: {
      el: '.swiper-pagination',
      clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      }
  });
  
  var swiperWithPagination2 = new Swiper('#swiper-with-pagination-samples', {
    pagination: {
      el: '.swiper-pagination',
      clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      }
  });
  
  var swiperWithPagination3 = new Swiper('#swiper-with-pagination-regions', {
    pagination: {
      el: '.swiper-pagination',
      clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      }
  });
  
  $("#actualizar").on("click", function(evt) {
	  evt.preventDefault();
	  if($('#oulevel option:selected').val() != "ALL" && $('#ouname').select2('data')[0]===undefined){
		  alert(parametros.ourequerida);
	  }else{
		  casosxSemana ();
		  muestrasxSemana ();
		  casosxRegion();
		  locBusq();  
	  }
	});

  
  var chart1 = new Chart(document.getElementById('confirmed-cases-chart').getContext("2d"), {});
  casosxSemana();
  var chart2 = new Chart(document.getElementById('samples-chart').getContext("2d"), {});
  muestrasxSemana();
  var chart3 = new Chart(document.getElementById('regions-chart').getContext("2d"), {});
  casosxRegion();
  locBusq();
	  
  //Grafico de casos confirmados por semana
  function casosxSemana (){
	  $('#confirmed-element,#confirmed-number').block({
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
	  $.getJSON(parametros.casosUrl, $('#filters-form').serialize(), function(data) {
		  var table1 = $('#casestable').DataTable({
			  dom: 'lBfrtip',
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "bFilter": false, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	          "responsive": true,
	          "pageLength": 4,
	          "bLengthChange": false,
	          "responsive": true,
	          "columns": [
	        	    { "title": $('#timeview').select2('data')[0].text },
	        	    null,
	        	    null,
	        	    null
	        	  ],
	          "buttons": [
	              {
	                  extend: 'excel'
	              },
	              {
	                  extend: 'pdfHtml5',
	                  orientation: 'portrait',
	                  pageSize: 'LETTER'
	              }
	          ]
	      });
		  table1.clear().draw();
		  var semanas = [];
		  var casos = [];
		  var totalCasos = 0;
		  var region = "";
		  
		  $('#labelConfirmed').html(parametros.casos + ' ' + $('#anio').val());
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text;
			  $('#labelChart1Title').html(parametros.casos + ' ' + $('#anio').val());
			  $('#labelTable1Title').html(parametros.casos + ' ' + $('#anio').val());
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelChart1Title').html(parametros.casos + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable1Title').html(parametros.casos + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  
		  for (var row in data) {
			  semanas.push([data[row].periodo]);
			  casos.push([data[row].dato1]);
			  totalCasos = totalCasos + data[row].dato1;
			  table1.row.add([data[row].periodo, $('#anio').val(), data[row].dato1, region]);
		  }
		  
		  $('#confirmed').html(totalCasos);
		  chart1.destroy();
		  chart1 = new Chart(document.getElementById('confirmed-cases-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: semanas,
		      datasets: [{
		        label: parametros.casos,
		        data: casos,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      }],
		    },
		    options: {
		      scales: {
		        xAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa'
		          }
		        }],
		        yAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa',
		            beginAtZero: true,
		            precision:0
		          }
		        }]
		      },
		
		      responsive: false,
		      maintainAspectRatio: false
		    }
		  });
		  chart1.resize();
		  $('#confirmed-element,#confirmed-number').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#confirmed-element,#confirmed-number').unblock();
	  });
  }
  
  
//Grafico de muestras por semana
  function muestrasxSemana (){
	  $('#samples-element,#samples-number').block({
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
	  $.getJSON(parametros.muestrasUrl, $('#filters-form').serialize(), function(data) {
		  var table2 = $('#samplestable').DataTable({
			  dom: 'lBfrtip',
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "bFilter": false, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	          "responsive": true,
	          "pageLength": 4,
	          "bLengthChange": false,
	          "responsive": true,
	          "columns": [
	        	    { "title":$('#timeview').select2('data')[0].text},
	        	    null,
	        	    null,
	        	    null
	        	  ],
	          "buttons": [
	              {
	                  extend: 'excel'
	              },
	              {
	                  extend: 'pdfHtml5',
	                  orientation: 'portrait',
	                  pageSize: 'LETTER'
	              }
	          ]
	      });
		  table2.clear().draw();
		  var semanas = [];
		  var casos = [];
		  var totalCasos = 0;
		  var region = "";
		  
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text;
			  $('#labelChart2Title').html(parametros.muestras + ' ' + $('#anio').val());
			  $('#labelTable2Title').html(parametros.muestras + ' ' + $('#anio').val());
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelChart2Title').html(parametros.muestras + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable2Title').html(parametros.muestras + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  semanas.push([data[row].periodo]);
			  casos.push([data[row].dato1]);
			  totalCasos = totalCasos + data[row].dato1;
			  table2.row.add([data[row].periodo, $('#anio').val(), data[row].dato1, region]);
		  }
		  

		  $('#samples').html(totalCasos);
		  chart2.destroy();
		  chart2 = new Chart(document.getElementById('samples-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: semanas,
		      datasets: [{
		        label: parametros.muestras,
		        data: casos,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      }],
		    },
		    options: {
		      scales: {
		        xAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa'
		          }
		        }],
		        yAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa',
		            beginAtZero: true,
		            precision:0
		          }
		        }]
		      },
		
		      responsive: false,
		      maintainAspectRatio: false
		    }
		  });
		  chart2.resize();
		  $('#samples-element,#samples-number').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#samples-element,#samples-number').unblock();
	  });
  }
  
  
  //Grafico de casos confirmados por region
  function casosxRegion (){
	  $('#regions-element').block({
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
	  $.getJSON(parametros.regionesUrl, $('#filters-form').serialize(), function(data) {
		  var nivelOu = "";
		  if($('#oulevel').val()=="ALL"){
			  nivelOu = parametros.region;
		    }
		    else if ($('#oulevel').val() == "region.samp"){
		    	nivelOu = parametros.distrito;
		    }
		    else if ($('#oulevel').val() == "province.samp"){
		    	nivelOu = parametros.distrito;
		    }
		    else if ($('#oulevel').val() == "district.samp"){
		    	nivelOu =parametros.corregimiento;
		    }
		    else if ($('#oulevel').val() == "correg.samp"){
		    	nivelOu =parametros.localidad;
		    }
		  
		  var table3 = $('#regionstable').DataTable({
			  dom: 'lBfrtip',
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "bFilter": false, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	          "responsive": true,
	          "pageLength": 4,
	          "bLengthChange": false,
	          "responsive": true,
	          "columns": [
	        	    { "title": nivelOu },
	        	    null,
	        	    null,
	        	    null
	        	  ],
	          "buttons": [
	              {
	                  extend: 'excel'
	              },
	              {
	                  extend: 'pdfHtml5',
	                  orientation: 'portrait',
	                  pageSize: 'LETTER'
	              }
	          ]
	      });
		  table3.clear().draw();
		  var regiones = [];
		  var casos = [];
		  var region = "";
		  
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text;
			  $('#labelChart3Title').html(parametros.casos + ' ' + $('#anio').val());
			  $('#labelTable3Title').html(parametros.casos + ' ' + $('#anio').val());
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelChart3Title').html(parametros.casos + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable3Title').html(parametros.casos + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  
		  for (var row in data) {
			  regiones.push([data[row][0]]);
			  casos.push([data[row][1]]);
			  table3.row.add([data[row][0], $('#anio').val(), data[row][1], region]);
		  }
		  
		  chart3.destroy();
		  chart3 = new Chart(document.getElementById('regions-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: regiones,
		      datasets: [{
		        label: parametros.casos,
		        data: casos,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      }],
		    },
		    options: {
		      scales: {
		        xAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa'
		          }
		        }],
		        yAxes: [{
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            fontColor: '#aaa',
		            beginAtZero: true,
		            precision:0
		          }
		        }]
		      },
		
		      responsive: false,
		      maintainAspectRatio: false
		    }
		  });
		  chart3.resize();
		  $('#regions-element').unblock();
	  })
	  .fail(function() {
		  alert( data );
		  $('#regions-element').unblock();
	  });
  }
  
  //Cuando se hace clic en las regiones
  $("#regions-chart").click(function(e) {
	   var activeBars = chart3.getElementAtEvent(e); 
	   
	   if (activeBars.length > 0) {
		    var clickedDatasetIndex = activeBars[0]._datasetIndex;
		    var clickedElementindex = activeBars[0]._index;
		    label = chart3.data.labels[clickedElementindex];
		    clickChart = true;
		    if($('#oulevel').val()=="ALL"){
		    	$('#oulevel').val("region.samp").change();
		    }
		    else if ($('#oulevel').val() == "region.samp"){
		    	$('#oulevel').val("district.samp").change();
		    }
		    else if ($('#oulevel').val() == "province.samp"){
		    	$('#oulevel').val("district.samp").change();
		    }
		    else if ($('#oulevel').val() == "district.samp"){
		    	$('#oulevel').val("correg.samp").change();
		    }
	   }
	   
	});
  
  
  $("#anterior").click(function(e) {	  
	   var actual = parseInt($('#anio').val());
	   actual = actual -1;
	   if($("#anio option:contains('"+actual+"')").val()){
		   $('#anio').val($("#anio option:contains('"+actual+"')").val()).change();
		   $("#actualizar").click();
	   }
	});
  
  $("#siguiente").click(function(e) {
	   var actual = parseInt($('#anio').val());
	   actual = actual + 1;
	   if($("#anio option:contains('"+actual+"')").val()){
		   $('#anio').val($("#anio option:contains('"+actual+"')").val()).change();
		   $("#actualizar").click();
	   }
	});
 
  
  //Total de localidades con busqueda
  function locBusq (){
	  $('#localities-number').block({
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
	  $.getJSON(parametros.locbusqUrl, $('#filters-form').serialize(), function(data) {
		  $('#localities').html(data);
		  $('#localities-number').unblock();
	  })
	  .fail(function() {
		  alert( data );
		  $('#localities-number').unblock();
	  });
	  
  }
  
  // Resizing charts

  function resizeCharts() {
    chart1.resize();
    chart2.resize();
    chart3.resize();
  }

  // For performance reasons resize charts on delayed resize event
  window.layoutHelpers.on('resize.portada', resizeCharts);
  
  
 }
 
};

}();
