
   	
$(document).ready(function(){
	
	var f = new Date();
	const MESES = [
	  "Enero",
	  "Febrero",
	  "Marzo",
	  "Abril",
	  "Mayo",
	  "Junio",
	  "Julio",
	  "Agosto",
	  "Septiembre",
	  "Octubre",
	  "Noviembre",
	  "Diciembre",
	];
	const DIA = [
		  "Domingo",
		  "Lunes",
		  "Martes",
		  "Miercoles",
		  "Jueves",
		  "Viernes",
		  "Sabado",
		];
		
	$("#diaControl").html("<i>"+DIA[f.getDay()]+" "+f.getDate()+" de "+ MESES[f.getMonth()]+ " "+ f.getFullYear()+"</i>");
	
	$('#anuncio').click(function(){	
		$('#myModal').modal();
	})
	
	 	
});  // fin de documento JQuery

