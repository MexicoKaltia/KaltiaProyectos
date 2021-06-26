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
		// -  console.log("usuarioAlta");

		var selectUsuarioTipo = '<form><fieldset><legend>FORMULARIO ALTA USUARIO</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR TIPO DE USUARIO</label><select class="form-control" id="selUsuarioTipo"><option value="" selected>Seleciona tipo de Usuario</option><option value="audiencia">AUDIENCIA</option><option value="instructor">INSTRUCTOR</option><option value="administracion">ADMINISTRACION</option></select></div></fieldset></form>';
		
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
//				// -  console.log( $.asignaFechaCalendario);
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
		// -  console.log("configurar Instructor");
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

$('#selectModulosCurso').change(function(){
	// -  console.log($('#selectModulosCurso').val());
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