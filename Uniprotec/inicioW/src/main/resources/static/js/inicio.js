
   	
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
	
	
	function mensajeFuncion(){
		//console.log($('#mensaje').val())
		$('#mensajeTexto').text($('#mensaje').val());
		return true
	}
	
	$( "#mensajeform" ).submit(function( event ) {
		  
//		  //console.log($('#mensaje').val())
//		  alert( "Handler for .submit() called." );
			$('#mensajeTexto').text($('#mensaje').val());
		});
	 	
});  // fin de documento JQuery

$('#anuncio').click(function(){	
//	$('#myModal').modal();
	
})
