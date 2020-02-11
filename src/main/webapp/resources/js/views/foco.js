var ProcessLocalidad = function () {
	
	
return {
  //main function to initiate the module
  init: function (parametros) {
	  
  $("#addLocalidad").click(function(){ 
		$('#inputAddLocalidadUrl').val($(this).data('whatever'));
		$('#localidadesForm').modal('show');
		
    });
  
  $('.datatable').dataTable({
	  "oLanguage": {
          "sUrl": parametros.dataTablesLang
      }
  });
  
  
  // Select2
  $(function() {
    $('.select2-filtro').each(function() {
      $(this)
        .wrap('<div class="position-relative"></div>')
        .select2({
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
    })
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
 
};

}();
