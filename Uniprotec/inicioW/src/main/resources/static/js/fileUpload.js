$(document).ready(function(){

	
	$("#archivosCliente").fileinput({
        browseClass: "btn btn-info",
        browseLabel: "Seleccionar Archivo",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        removeClass: "btn btn-warning",
        removeLabel: "Remover",
 	    showCaption: false, 
 	    dropZoneEnabled: false
 	  });
    
	$("#imagenLogoCliente").fileinput({
        browseClass: "btn btn-success",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        browseLabel: "Seleccionar Archivo",
        removeClass: "btn btn-warning",
        removeLabel: "Remover",
 	    showCaption: false, 
 	    dropZoneEnabled: false
 	  });

	 $("#asignaArchivos").fileinput({
	        browseClass: "btn btn-info",
	        browseLabel: "Seleccionar Archivo",
	        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
	        removeClass: "btn btn-warning",
	        removeLabel: "Remover",
	 	   showCaption: false, 
	 	   dropZoneEnabled: false
	 	  });
	 
	 $("#archivoParticipantes").fileinput({
	        browseClass: "btn btn-warning",
	        browseLabel: "Seleccionar Archivo Participantes",
	        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
	        removeClass: "btn btn-danger",
	        removeLabel: "Remover",
	 	   showCaption: false, 
	 	   dropZoneEnabled: false
	 	  });
})

    