$(document).ready(function(){

	
	$("#archivosCliente").fileinput({
        browseClass: "btn btn-info",
        browseLabel: "Seleccionar Archivos",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        removeClass: "btn btn-warning",
        removeLabel: "Remover",
 	    showCaption: false, 
 	    dropZoneEnabled: false
 	  });
    
	$("#imagenLogoCliente").fileinput({
        browseClass: "btn btn-success",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        browseLabel: "Seleccionar Archivos",
        removeClass: "btn btn-warning",
        removeLabel: "Remover",
 	    showCaption: false, 
 	    dropZoneEnabled: false
 	  });

	 $("#asignaArchivos").fileinput({
	        browseClass: "btn btn-info",
	        browseLabel: "Seleccionar Archivos",
	        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
	        removeClass: "btn btn-warning",
	        removeLabel: "Remover",
	 	   showCaption: false, 
	 	   dropZoneEnabled: false
	 	  });
})

    