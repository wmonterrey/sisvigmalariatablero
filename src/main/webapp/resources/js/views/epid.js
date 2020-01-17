var ProcessDashboardEpid = function () {
	
	
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
  
  var swiperWithPagination1 = new Swiper('#swiper-with-pagination-canales', {
    pagination: {
      el: '.swiper-pagination',
      clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      }
  });
  
  var swiperWithPagination2 = new Swiper('#swiper-with-pagination-carga', {
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
	  canales ();
	  carga ();
	  }
	});

  
  var chart1 = new Chart(document.getElementById('canales-chart').getContext("2d"), {});
  canales();
  var chart2 = new Chart(document.getElementById('carga-cases-chart').getContext("2d"), {});
  var chart3 = new Chart(document.getElementById('carga-plasmodium-chart').getContext("2d"), {});
  carga ();

	  
  //Grafico de canales
  function canales (){
	  $('#canales-element,#canales-element-2').block({
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
	  $.getJSON(parametros.canalUrl, $('#filters-form').serialize(), function(data) {
		  var table1 = $('#canalestable').DataTable({
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
		  var q1 = [];
		  var q2 = [];
		  var q3 = [];
		  var region = "";
		  
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text;
			  $('#labelChart1Title').html(parametros.canal + ' ' + $('#anio').val());
			  $('#labelTable1Title').html(parametros.canal + ' ' + $('#anio').val());
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelChart1Title').html(parametros.canal + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable1Title').html(parametros.canal + ', ' + $('#anio').val()+ ' ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  semanas.push([data[row].semana]);
			  casos.push([data[row].casos]);
			  q1.push([data[row].q1]);
			  q2.push([data[row].q2]);
			  q3.push([data[row].q3]);
			  table1.row.add([data[row].semana, $('#anio').val(), data[row].casos,data[row].q1,data[row].q2,data[row].q3, region]);
		  }
		  chart1.destroy();
		  chart1 = new Chart(document.getElementById('canales-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: semanas,
		      datasets: [{
		        label: parametros.total,
		        data: casos,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      },
		      {
	    	  	type: 'line',
		        label: 'Q1',
		        data: q1,
		        borderWidth: 1,
		        backgroundColor: 'rgba(0,153,51,0.2)',
		        borderColor: 'rgba(0,153,51,1)',
		        pointRadius: 1
		        
		      },
		      {
	    	  	type: 'line',
		        label: 'Q2',
		        data: q2,
		        borderWidth: 1,
		        backgroundColor: 'rgba(255,255,51,0.2)',
		        borderColor: 'rgba(255,255,51,1)',
		        pointRadius: 1
		        
		      },
		      {
	    	  	type: 'line',
		        label: 'Q3',
		        data: q3,
		        borderWidth: 1,
		        backgroundColor: 'rgba(255,0,0,0.2)',
		        borderColor: 'rgba(255,0,0,1)',
		        pointRadius: 1
		        
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
		            fontColor: '#aaa'
		          }
		        }]
		      },
		
		      responsive: false,
		      maintainAspectRatio: false
		    }
		  });
		  chart1.resize();
		  $('#canales-element,#canales-element-2').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#canales-element,#canales-element-2').unblock();
	  });
  }
  
  //Grafico de carga
  function carga (){
	  $('#carga-element,#carga-element-2').block({
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
	  $.getJSON(parametros.cargaUrl, $('#filters-form').serialize(),  function(data) {
		  var table2 = $('#cargacasestable').DataTable({
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
		  var table3 = $('#cargaplasmoduimtable').DataTable({
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
		  var anios = [];
		  var casos = [];
		  var activas=[];
		  var pasivas=[];
		  var reactivas=[];
		  var encuestas=[];
		  var sdtb=[];
		  var vivax=[];
		  var falciparum=[];
		  var otros=[];
		  var sde=[];
		  var region = "";
		  
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text;
			  $('#labelChart2Title').html(parametros.tipobusq);
			  $('#labelTable2Title').html(parametros.tipobusq);
			  $('#labelChart3Title').html(parametros.tipoesp);
			  $('#labelTable3Title').html(parametros.tipoesp);
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelChart2Title').html(parametros.tipobusq + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable2Title').html(parametros.tipobusq + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelChart3Title').html(parametros.tipobusq + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
			  $('#labelTable3Title').html(parametros.tipobusq + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  anios.push([data[row][0]]);
			  casos.push([data[row][1]]);
			  activas.push([data[row][2]]);
			  pasivas.push([data[row][3]]);
			  reactivas.push([data[row][4]]);
			  encuestas.push([data[row][5]]);
			  sdtb.push([data[row][6]]);
			  vivax.push([data[row][7]+data[row][9]]);
			  falciparum.push([data[row][8]]);
			  otros.push([data[row][10]]);
			  sde.push([data[row][11]]);
			  table2.row.add([data[row][0], data[row][1],data[row][2],data[row][3],data[row][4],data[row][5],data[row][6], region]);
			  table3.row.add([data[row][0], data[row][1],data[row][7]+data[row][9],data[row][8],data[row][10],data[row][11], region]);
		  }
		  
		  chart2.destroy();
		  chart2 = new Chart(document.getElementById('carga-cases-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: anios,
		      datasets: [{
		        label: parametros.active,
		        data: activas,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      },
		      {
		        label: parametros.pasive,
		        data: pasivas,
		        borderWidth: 1,
		        backgroundColor: 'rgba(252,3,227,.05)',
		        borderColor: 'rgba(252,3,227,1)'
		      },
		      {
		        label: parametros.reactive,
		        data: reactivas,
		        borderWidth: 1,
		        backgroundColor: 'rgba(237,183,36,.05)',
		        borderColor: 'rgba(237,183,36,1)'
		      },
		      {
		        label: parametros.survey,
		        data: encuestas,
		        borderWidth: 1,
		        backgroundColor: 'rgba(25,161,57,.05)',
		        borderColor: 'rgba(25,161,57,1)'
		      },
		      {
		        label: parametros.sdtb,
		        data: sdtb,
		        borderWidth: 1,
		        backgroundColor: 'rgba(255,0,0,.05)',
		        borderColor: 'rgba(255,0,0,1)'
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
		      maintainAspectRatio: false
		    }
		  });
		  chart2.resize();
		  chart3.destroy();
		  chart3 = new Chart(document.getElementById('carga-plasmodium-chart').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: anios,
		      datasets: [{
		        label: 'Vivax',
		        data: vivax,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      },
		      {
		        label: 'Falciparum',
		        data: falciparum,
		        borderWidth: 1,
		        backgroundColor: 'rgba(252,3,227,.05)',
		        borderColor: 'rgba(252,3,227,1)'
		      },
		      {
		        label: 'Otros',
		        data: otros,
		        borderWidth: 1,
		        backgroundColor: 'rgba(237,183,36,.05)',
		        borderColor: 'rgba(237,183,36,1)'
		      },
		      {
		        label: parametros.sdtb,
		        data: sde,
		        borderWidth: 1,
		        backgroundColor: 'rgba(255,0,0,.05)',
		        borderColor: 'rgba(255,0,0,1)'
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
		      maintainAspectRatio: false
		    }
		  });
		  chart3.resize();
		  $('#carga-element,#carga-element-2').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#carga-element,#carga-element-2').unblock();
	  });
  }
  
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
 
 // Resizing charts

 function resizeCharts() {
   chart1.resize();
   chart2.resize();
 }

 // For performance reasons resize charts on delayed resize event
 window.layoutHelpers.on('resize.epid', resizeCharts);
    
 }
 
};

}();
