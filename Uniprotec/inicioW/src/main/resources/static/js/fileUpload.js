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
	 
	 /*
	  * entregables
	  */
	 $("#formALogo").fileinput({
			browseClass: "btn btn-success",
			browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
			browseLabel: "Seleccionar Imagen",
			removeClass: "btn btn-warning",
			removeLabel: "Remover",
			maxFileSize: 1024,
			showCaption: false, 
			dropZoneEnabled: false
		});
	 
	 $("#formBFotoParticipante").fileinput({
			browseClass: "btn btn-success",
			browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
			browseLabel: "Seleccionar Imagen",
			removeClass: "btn btn-warning",
			removeLabel: "Remover",
			maxFileSize: 1024,
			showCaption: false, 
			dropZoneEnabled: false,
		});
	 
	
	 $("#formCEvidenciasFotograficas").fileinput({
			browseClass: "btn btn-success",
			browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
			browseLabel: "Cargar Documento",
		    maxFileCount: 9,
		    validateInitialCount: true,
			removeClass: "btn btn-danger",
			removeLabel: "Remover",
			maxFileSize: 102400,
			showCaption: false, 
			dropZoneEnabled: false
		});
	
	 
	 $("#formCEvidenciaDocto").fileinput({
			browseClass: "btn btn-success",
			browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
			browseLabel: "Seleccionar Archivo(s)",
			removeClass: "btn btn-danger",
			removeLabel: "Remover",
			showCaption: false, 
			maxFileSize: 102400,
			dropZoneEnabled: false
		});
})

    