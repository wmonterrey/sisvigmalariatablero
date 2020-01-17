var ProcessExport = function () {
	
	
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
  

	  
	  //Create a new date from a string, return as a timestamp.
	  function timestamp(str) {
	      return new Date(str).getTime();
	  }
	  
	  
	  var dateSlider = document.getElementById('slider-date');
	  var firstDay = new Date().getFullYear().toString();
	  var isRtl = $('body').attr('dir') === 'rtl' || $('html').attr('dir') === 'rtl';

	  noUiSlider.create(dateSlider, {
		  // Create two timestamps to define a range.
	      range: {
	          min: timestamp('2018-01-01'),
	          max: timestamp(new Date())
	      },

	      // Steps of one day
	      step: 24 * 60 * 60 * 1000,
	      direction: isRtl ? 'rtl' : 'ltr',

	      // Two more timestamps indicate the handle starting positions.
	      
	      start: [timestamp(firstDay), timestamp(new Date())],

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


		// Create a string representation of the date.
		function formatDate(date) {
			 var yyyy = date.getFullYear().toString();                                    
		     var mm = (date.getMonth()+1).toString(); // getMonth() is zero-based         
		     var dd  = date.getDate().toString();             
		     return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
		}
		
  
    
  $("#actualizar").on("click", function(evt) {
	  evt.preventDefault();
	  if($('#oulevel option:selected').val() != "ALL" && $('#ouname').select2('data')[0]===undefined){
		  alert(parametros.ourequerida);
	  }else{
		  casos();
	  }
	});

//casos
  function casos (){
	  $('#export-element').block({
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
		  var table1 = $('#resultados').DataTable({
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
		  table1.clear().draw();
		  
		  var region = "";
		  
		  if($('#oulevel').val()=="ALL"){
			  region = $('#oulevel').select2('data')[0].text
			  $('#labelTableTitle').html(parametros.casos);
		  }
		  else{
			  region = $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text;
			  $('#labelTableTitle').html(parametros.canal + ', ' + $('#oulevel').select2('data')[0].text+ ': ' + $('#ouname').select2('data')[0].text);
		  }
		  
		  for (var row in data) {
			  
			  table1.row.add([data[row][0],data[row][1],data[row][2],data[row][3]
			  				,data[row][4],data[row][5],data[row][6],data[row][7],data[row][8]
			  					,data[row][9],data[row][10],data[row][11],data[row][12],data[row][13]
			  						,data[row][14],data[row][15]]);
		  }
		  $('#export-element').unblock();
	  })
	  .fail(function() {
		  alert( "error" );
		  $('#export-element').unblock();
	  });
  }
    
 }
 
};

}();
