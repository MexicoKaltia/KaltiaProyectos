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

		var selectUsuarioTipo = '<form><fieldset><legend>FORMULARIO ALTA CURSO - MODULO DIDACTICO</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR CURSO</label><select class="form-control" id="selCurso"><option value="" selected>Seleciona Curso</option><option value="audiencia">Montacargas</option><option value="instructor">Liderazgo</option><option value="administracion">Nom-33 Iso 9000</option></select></div></fieldset></form>';
		
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
//				console.log( $.asignaFechaCalendario);
				$('#selectAsignaEvento').show();
				
				$('#selectAsignaEvento').change(function(){
					$('#selectModulosCurso').show();
				});
				
				
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
		
		var selectUsuarioTipo = '<form><fieldset><legend>FORMULARIO CONFIGURACION MODULO DIDACTIVO</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR MODULO DIDACTICO</label><select class="form-control" id="selModulo"><option value="" selected>Seleciona Modulo</option><option value="audiencia">Memoria</option><option value="instructor">Crucigrama</option><option value="administracion">Test</option></select></div></fieldset></form>';
		$('#seccionOperacion').append(selectUsuarioTipo);
		
		$('#selInstructor').change(function(){
			$('#selInstructor').attr('disabled', true);
			$('#usuarioConfigurarInstructorForm').show();
			
		});
	});
	
    // fin de documento
})

$('#selectModulosCurso').change(function(){
	console.log($('#selectModulosCurso').val());
	$('#divListaUsuarios').empty();
	var listaUsuarios = "<ul>" +
			"<li>012015-1</li>" +
			"<li>012015-2</li>" +
			"<li>012015-3</li>" +
			"<li>012015-4</li>" +
			"<li>012015-5</li>" +
			"<li>012015-6</li>" +
			"<li>012015-7</li>";
	$('#divListaUsuarios').append(listaUsuarios);
});

$('#btnActivarUsuarios').click(function(){
	alert("Usuarios en proceso de Activacion, recuerde que la vigencia es +1 dia despues de la Fecha Actividad.");
})

function validaFecha(inputAsignaFecha){
	
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
   	    $.asignaFechaCalendario = $('#usuarioAudenciaFecha').val();
   	    
   	 
			if($.asignaFechaCalendario === null || $.asignaFechaCalendario === ""){
//	   			alert("Debes de seleccionar Fecha para consultar los eventos disponibles");
	   		}else{
	   			
	   		}
		

}