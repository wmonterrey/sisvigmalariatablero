var ProcessActividadUsuario = function () {
	
	
return {
  //main function to initiate the module
  init: function (parametros) {
	  
	  
	//Swiper
	  
  var swiperWithPagination1 = new Swiper('#swiper-days', {
    pagination: {
      el: '.swiper-pagination',
      clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      }
  });
  
	  
	var startDate = moment().subtract(29, 'days');
	var endDate = moment();
	var isRtl = $('html').attr('dir') === 'rtl';
	var inicio ="";
	var final="";
		

	function cb(startDate, endDate) {
	  $('#daterange-mc').html(startDate.format('YYYY-MM-DD') + ' - ' + endDate.format('YYYY-MM-DD'));
	  inicio=startDate.format('YYYY-MM-DD');
	  final=endDate.format('YYYY-MM-DD');
	  paginasVisitadas(inicio,final);
	  visitasDia(inicio,final);
	  accesoUsuario(inicio,final);
	}
	
	$('#daterange-mc').daterangepicker({
		startDate: startDate,
		endDate: endDate,
		maxDate:moment(),
		locale:bdrp,
		ranges: rangeLocale,
		opens: (isRtl ? 'left' : 'right')
	}, cb);
	
	var chart1 = new Chart(document.getElementById('activity-chart-1').getContext("2d"), {});

	cb(startDate, endDate);
  
  //Paginas visitadas
  function paginasVisitadas (inicio,final){
	  $('#visitsdiv,#pagesdiv').block({
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
	  $.getJSON(parametros.visitadasUrl, {username:parametros.usuario,fechaInicio:inicio, fechaFin:final}, function(data) {
		  var total=0;
		  var table3 = $('#pagestable').DataTable({
			  dom: 'lBfrtip',
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "bFilter": false, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	          "responsive": true,
	          "pageLength": 10,
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
		  for (var row in data) {
			  total = total + data[row][1];
			  table3.row.add([data[row][0], data[row][1]]);
		  }
		  $('#visitslabel').html(total);
		  $('#visitsdiv,#pagesdiv').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#visitsdiv,#pagesdiv').unblock();
	  });
  }
  
  
//Paginas visitadas
  function accesoUsuario (inicio,final){
	  $('#accesosdiv').block({
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
	  $.getJSON(parametros.accesosUrl, {username:parametros.usuario,fechaInicio:inicio, fechaFin:final}, function(data) {
		  var table4 = $('#accesestable').DataTable({
			  dom: 'lBfrtip',
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "bFilter": false, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	          "responsive": true,
	          "pageLength": 10,
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
		  table4.clear().draw();
		  for (var row in data) {
			  var current_datetime = new Date(data[row][1]);
			  formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds()
			  
			  table4.row.add([formatted_date, data[row][2]]);
		  }
		  
		  $('#accesosdiv').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#accesosdiv').unblock();
	  });
  }
  
  //visitas por día
  function visitasDia (inicio,final){
	  $('#days-element,#days-element2,#accessesdiv,#averagediv').block({
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
	  $.getJSON(parametros.porDiaUrl, {username:parametros.usuario,fechaInicio:inicio, fechaFin:final}, function(data) {
		  var table1 = $('#daystable').DataTable({
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
		  var dias = [];
		  var ingresos = [];
		  var i=0;
		  var total=0;
		  var promedio = 0.0;
		  for (var row in data) {
			  i++;
			  total = total + data[row][1];
			  promedio = promedio + data[row][2];
			  dias.push([data[row][0]]);
			  ingresos.push([data[row][1]]);
			  table1.row.add([data[row][0], data[row][1], data[row][2]]);
		  }
		  
		  chart1.destroy();
		  chart1 = new Chart(document.getElementById('activity-chart-1').getContext("2d"), {
		    type: 'bar',
		    data: {
		      labels: dias,
		      datasets: [{
		        label: 'Ingresos',
		        data: ingresos,
		        borderWidth: 1,
		        backgroundColor: 'rgba(28,180,255,.05)',
		        borderColor: 'rgba(28,180,255,1)'
		      }],
		    },
		    options: {
		      scales: {
		        xAxes: [{
		          barPercentage: 1.0,
		          categoryPercentage: 1.0,
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
		  
		  $('#accesseslabel').html(total);
		  $('#averagelabel').html((promedio/i).toFixed(2));
		  
		  $('#days-element,#days-element2,#accessesdiv,#averagediv').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#days-element,#days-element2,#accessesdiv,#averagediv').unblock();
	  });
  }
  
  // Resizing charts

  function resizeCharts() {
    chart1.resize();
  }

  // For performance reasons resize charts on delayed resize event
  window.layoutHelpers.on('resize.portada', resizeCharts);
  
  
 }
 
};

}();
