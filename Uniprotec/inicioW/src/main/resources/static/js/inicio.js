
   	
$(document).ready(function(){
	
	console.log($preAsignaciones)
	
	if(perfilUsuario !== "Vendedor"){
		$data = $preAsignaciones;
	}else{
		$data = getPREVendedor();
	}

	console.log($data);
	var flag, flag2, flag3 = false;
	
		for(var a in $data){
			var preAsignacion = $data[a];
			if(preAsignacion.idStatusAsignacion == 2){ //= "Alta Análisis Económico"
				flag = true;
			}else if(preAsignacion.idStatusAsignacion == 3){//= "REVISION"
				flag2 = true;
			}else if(preAsignacion.idStatusAsignacion == 4){ //= "APROBACION PREASIGNACION"
				flag3 = true;
			}
	}
		
		console.log(flag);
		console.log(flag2);
		console.log(flag3);
		
	if(flag){
		var notificacionPreAsignacion = '<div class="alert alert-info alert-dismissible" role="alert" id="idNotificacion">\
	    	<button type="button" class="close" data-dismiss="alert">&times;</button>\
           	<h6><i class="metismenu-icon pe-7s-rocket"></i>\
           	<a href="/BSeguimiento">\
			     <span>Bienvenido usuario '+perfilUsuario+' : '+nombreUsuario+', usted tiene +1 notificación de PreAsignación - Análisis Económico, pendientes de revisión / aprobación.</span>\
			</a></h6>\
		</div>';
	}
	
	if(flag2){
		var notificacionRevisionPreAsignacion = '<div class="alert alert-warning alert-dismissible" role="alert" >\
	    	<button type="button" class="close" data-dismiss="alert">&times;</button>\
           	<h6><i class="metismenu-icon pe-7s-rocket"></i>\
           	<a href="/BSeguimiento">\
			     <span>Bienvenido usuario '+perfilUsuario+' : '+nombreUsuario+', usted tiene +1 notificación de PreAsignación - Análisis Económico, pendientes de REVISION.</span>\
			</a></h6>\
		</div>';
	}
	
	if(flag3){
		var notificacionAprobadoPreAsignacion = '<div class="alert alert-success alert-dismissible" role="alert" >\
	    	<button type="button" class="close" data-dismiss="alert">&times;</button>\
           	<h6><i class="metismenu-icon pe-7s-rocket"></i>\
           	<a href="/BSeguimiento">\
			     <span>Bienvenido usuario '+perfilUsuario+' : '+nombreUsuario+', usted tiene +1 notificación de APROBADO PREASIGNACION.</span>\
			</a></h6>\
		</div>';
	}
	
	if(perfilUsuario === "Vendedor"){
		$('#notificacionVendedorPreAsignaciones').append(notificacionRevisionPreAsignacion);
		$('#notificacionVendedorPreAsignaciones').append(notificacionAprobadoPreAsignacion);
	}else{
		$('#notificacionAdministracionPreAsignaciones').append(notificacionPreAsignacion);
	}
	
	
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
		$('#mensajeTexto').text($('#mensaje').val());
		return true
	}
	
	$( "#mensajeform" ).submit(function( event ) {	  
			$('#mensajeTexto').text($('#mensaje').val());
		});
	 	
});  // fin de documento JQuery

function getPREVendedor(){		
	var preAsignaciones = new Array();
	for(var a in $preAsignaciones){
		var preAsignacion = $preAsignaciones[a]
		if(preAsignacion.userCreateAsignacion === idUsuario){
			var fecha = getFecha(preAsignacion.createAtAsignacion);
			preAsignacion.createAtAsignacion = fecha;
			preAsignaciones.push(preAsignacion);
		}
	}
	return preAsignaciones;
}

function getFecha(fechaValor){
	var fechaArray = fechaValor.split('-');
	var dia = fechaArray[2].substring(0,2);
	var mes = fechaArray[1];
	var anio = fechaArray[0];
	
	var fecha = dia + '-' + mes + '-' + anio;	
	return fecha;
}


$('#anuncio').click(function(){	
	
})
