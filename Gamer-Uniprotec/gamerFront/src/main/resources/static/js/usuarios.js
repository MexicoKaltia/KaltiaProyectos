$(document).ready(function() {
	
	$('#usuarioAudienciaForm').hide();
	$('#usuarioConfigurarInstructorForm').hide();
	
	$('#usuarioAlta').click(function(){
		$('#usuarioInstructor').removeClass("activeItem");
		$('#usuarioAudencia').removeClass("activeItem");
		$('#usuarioConsulta').removeClass("activeItem");
		$(this).addClass("activeItem");
		
		$('#usuarioConfigurarInstructorForm').hide();
		$('#seccionOperacion').empty();
		console.log("usuarioAlta");

		var selectUsuarioTipo = '<form><fieldset><legend>FORMULARIO ALTA USUARIO</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR TIPO DE USUARIO</label><select class="elementoInput form-control" id="selUsuarioTipo"><option value="" selected>Seleciona tipo de Usuario</option><option value="audiencia">AUDIENCIA</option><option value="instructor">INSTRUCTOR</option><option value="administracion">ADMINISTRACION</option></select></div></fieldset></form>';
		
		$('#seccionOperacion').append(selectUsuarioTipo);
		
		$('#selUsuarioTipo').change(function(){
//			var usuarioTipo = $("#selUsuarioTipo option:selected").text();
			var usuarioTipo = $("#selUsuarioTipo").val();
			$('#selUsuarioTipo').attr('disabled', true);
			
			switch (usuarioTipo){
			case 'audiencia':
				$('#usuarioAudienciaForm').show();
				$('#selectAsignaEvento').hide();
				$('#selectModulosCurso').hide();
	 			$('#btnActivarUsuarios').attr('disabled', true);
				break;
			case 'instructor' :
				
				break;
			case 'administrador':
			
				break;
			}
		})
	});
	
	$('#usuarioInstructor').click(function(){
		console.log("configurar Instructor");
		$('#seccionOperacion').empty();
		
		$('#usuarioAlta').removeClass("activeItem");
		$('#usuarioAudencia').removeClass("activeItem");
		$('#usuarioConsulta').removeClass("activeItem");
		$(this).addClass("activeItem");
		$('#usuarioAudienciaForm').hide();
		
		var selectUsuarioTipo = '<form><fieldset><legend>FORMULARIO CONFIGURACION INSTRUCTOR</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR INSTRUCTOR</label><select class="form-control" id="selInstructor"><option value="" selected>Seleciona Instructor</option><option value="audiencia">Ing Felipe Castillo</option><option value="instructor">Ing Rafael Cevada</option><option value="administracion">Ing Jesus Mares</option></select></div></fieldset></form>';
		$('#seccionOperacion').append(selectUsuarioTipo);
		
		$('#selInstructor').change(function(){
			$('#selInstructor').attr('disabled', true);
			$('#usuarioConfigurarInstructorForm').show();
			
		});
	});
	
    // fin de documento
})

	var asignacionSelec = new Array();

$('#selectAsignaEvento').change(function(){
	$('#usuarioAudenciaNombre').attr("disabled", false);
	idAsignacion = $('#selectAsignaEvento').val();
	$('#selectModulosCurso').show();
	$('#selectModulosCurso').val("");
	$('#divNombreInstructor').empty();
	$('#divListaUsuarios').empty();
	$('#btnActivarUsuarios').attr('disabled', true);
	var nombreInstructor ="";
	for(a in asignacionSelec){
		if((idAsignacion*1) === (asignacionSelec[a].idAsignacion*1)){
			nombreInstructor = asignacionSelec[a].instructorAsignacion; 
		} 
	}
//	$('#divNombreInstructor').append(nombreInstructor);
	$('#usuarioAudenciaNombre').val("");
	$('#usuarioAudenciaNombre').val(nombreInstructor);
//	$('#usuarioAudenciaNombre').attr("disabled", true);
});


$('#selectModulosCurso').change(function(){
//	console.log($('#selectModulosCurso').val());
	$('#divListaUsuarios').empty();
	var listaUsuarios = "<ul>" ;
	var idAsignacion = $('#selectAsignaEvento').val();
//	console.log(idAsignacion);
	var cantidadUsuarios=0;
	for(a in asignacionSelec){
		if((idAsignacion*1) === (asignacionSelec[a].idAsignacion*1)){
			cantidadUsuarios = asignacionSelec[a].participantesAsignacion;
//			console.log(cantidadUsuarios );
			for(var i= 0 ; i<cantidadUsuarios; i++){
				listaUsuarios = listaUsuarios + "<li>"+idAsignacion+"−"+(i+1)+"</li>" 
			}
			$('#btnActivarUsuarios').attr('disabled', false);
		} 
	}
	listaUsuarios = listaUsuarios + "</ul>"
	$('#divListaUsuarios').append(listaUsuarios);
	$('#usuarioAudienciaParticipantes').val(cantidadUsuarios);
	$('#usuarioAudienciaIdAsignacion').val(idAsignacion);
});

$('#btnActivarUsuarios').click(function(){
	alert("Usuarios en proceso de Activacion, recuerde que la vigencia es +1 dia despues de la Fecha Actividad.");
})

	function validaFecha(inputAsignaFecha){
		
			var elementoPicker = $datepicker.pickadate('picker');	
			$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
			$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
	   	    $.asignaFechaCalendario = $('#usuarioAudenciaFecha').val();
	   	    $('#selectAsignaEvento').empty();
	   	 
				if($.asignaFechaCalendario === null || $.asignaFechaCalendario === ""){
					alert("Debes de seleccionar Fecha para consultar los eventos disponibles");
		   		}else{

		   			$('#selectAsignaEvento').show();
		   			$('#selectModulosCurso').hide();
		   			$('#usuarioAudenciaNombre').val("");
		   			$('#selectModulosCurso').val("");
		   			$('#divListaUsuarios').empty();
		   			$('#btnActivarUsuarios').attr('disabled', true);
		   			$('#selectAsignaEvento').empty();
		   			
		   			var asignacionSelec = new Array();
		   			asignacionSelec = getAsignaciones($.asignaFecha2);
		   			var optionAsignaciones ='<option value="" selected>Seleccione Curso - Cliente</option>';
		   			for(a in asignacionSelec){
		   				optionAsignaciones = optionAsignaciones +  '<option value="'+asignacionSelec[a].idAsignacion+'">'+asignacionSelec[a].cursoAsignacion+' − '+asignacionSelec[a].clienteAsignacion+'</option>';
		   			}
		   			$('#selectAsignaEvento').append(optionAsignaciones);
		   		}
	}

function getAsignaciones(fechaSeleccion){
	asignacionSelec.length = 0
	var asignacion = new Array();
	asignacion = asignaciones.asignaciones;
	for(var i=0; i < asignacion.length; i++){
		var fechaAsignacion = asignacion[i].fechaAsignacion;
		if(fechaAsignacion === fechaSeleccion){
			asignacionSelec.push(asignacion[i]);
		}
	}
	return asignacionSelec;
}
