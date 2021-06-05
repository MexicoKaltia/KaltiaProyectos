$idUsuarioInstructor = "";
$idUsuarioAudiencia = "";

$(document).ready(function() {
	
	
	
//	console.log(instructores.instructores);
//	console.log(usuarios.usuarios);
	console.log(asignaciones);
//	console.log(usuarios.usuariosInstructor);
//	console.log(usuariosControl);
	console.log(modulos);
	var idCurosAsignacion="";	
	$('#usuarioAudienciaForm').hide();
	$('#usuarioInstructorForm').hide();
	$('#usuarioAdministradorForm').hide();
	$('#configuracionInstructor').hide();
	$('#configuracionAudiencia').hide();
	
	$('#usuarioAlta').click(function(){
		$('#usuarioInstructor').removeClass("activeItem");
		$('#usuarioAudencia').removeClass("activeItem");
		$('#usuarioConsulta').removeClass("activeItem");
		$(this).addClass("activeItem");

		$('#seccionOperacion').show();
		$('#usuarioAudienciaForm').hide();
		$('#usuarioInstructorForm').hide();
		$('#usuarioAdministradorForm').hide();
		$('#seccionOperacion').empty();
		$('#configuracionInstructor').hide();
		$('#configuracionAudiencia').hide();

		var selectUsuarioTipo = '<form><fieldset><legend class="bienvenido">FORMULARIO ALTA USUARIO</legend><div class="form-group"><label for="exampleSelect1" class="nombre">SELECCIONAR TIPO DE USUARIO</label><select class="elementoInput form-control" id="selUsuarioTipo"><option value="" selected>Seleciona tipo de Usuario</option><option value="audiencia">AUDIENCIA</option><option value="instructor">INSTRUCTOR</option><option value="administrador">ADMINISTRADOR</option></select></div></fieldset></form>';
		
		$('#seccionOperacion').append(selectUsuarioTipo);
		
		$('#selUsuarioTipo').change(function(){
//			var usuarioTipo = $("#selUsuarioTipo option:selected").text();
			var usuarioTipo = $("#selUsuarioTipo").val();
			$('#selUsuarioTipo').attr('disabled', true);
			
			switch (usuarioTipo){
			case 'audiencia':
				$('#usuarioAudienciaForm').show();
				$('#usuarioAdministradorForm').hide();
				$('#usuarioInstructorForm').hide();
				$('#selectAsignaEvento').hide();
				$('#selectModulosCurso').hide();
	 			$('#btnActivarUsuarios').attr('disabled', true);
	 			
				break;
			case 'instructor' :
				$('#usuarioInstructorForm').show();
//				$('#seccionOperacion').empty();
				$('#usuarioAudienciaForm').hide();
				$('#usuarioAdministradorForm').hide();
				$('#instructorActivo').hide()
				$('#instructorNoActivo').hide()
				
				formInstructor();
				break;
			case 'administrador':
				$('#usuarioAdministradorForm').show();
				$('#usuarioAudienciaForm').hide();
				$('#usuarioInstructorForm').hide();
				$('#administradorActivo').hide()
				$('#administradorNoActivo').hide()
				console.log("tipo de Alta Administrador");
				formAdministrador();
				break;
			}
		})
	});
	
	
	
	$('#usuarioInstructor').click(function(){
		console.log("configurar Instructor");
		$('#usuarioAlta').removeClass("activeItem");
		$('#usuarioAudencia').removeClass("activeItem");
		$('#usuarioConsulta').removeClass("activeItem");
		$('#seccionOperacion').hide();
		$(this).addClass("activeItem");		
		
		$('#configuracionInstructor').show();
		$('#configuracionAudiencia').hide();
		$('#usuarioAudenciaNombre').val("");
		$('#usuarioAudienciaForm').hide();
		$('#usuarioInstructorForm').hide();
		$('#usuarioAdministradorForm').hide();
	});
	
	$('#usuarioAudencia').click(function(){
		console.log("configurar Audiencia");
		$('#usuarioAlta').removeClass("activeItem");
		$('#usuarioInstructor').removeClass("activeItem");
		$('#usuarioConsulta').removeClass("activeItem");
		$('#seccionOperacion').hide();
		$(this).addClass("activeItem");		
		
		$('#configuracionAudiencia').show();
		$('#configuracionInstructor').hide();
		$('#usuarioAudenciaNombre').val("");
		$('#usuarioAudienciaForm').hide();
		$('#usuarioInstructorForm').hide();
		$('#usuarioAdministradorForm').hide();
	});
	
	

	
}) // fin de documento jquery

/*
 *  Funciones para dar de alta Usuario Audiencia
 */
	var asignacionSelec = new Array();
	var nombreCurso ="";

$('#selectAsignaEvento').change(function(){
	$('#usuarioAudenciaNombre').attr("disabled", false);
	idAsignacion = $('#selectAsignaEvento').val();
	
	$('#selectModulosCurso').show();
	$('#selectModulosCurso').empty();
	$('#selectModulosCurso').val("");
	$('#divNombreInstructor').empty();
	$('#divListaUsuarios').empty();
	$('#btnActivarUsuarios').attr('disabled', true);
	nombreCurso = $("#selectAsignaEvento option:selected").text();
	idCurosAsignacion=getIdCurso(nombreCurso);
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
	for(a in modulos){
		var modulo = modulos[a];
		if(getCursoContein(modulo, idCurosAsignacion)){
			var option = "<option value='"+modulo.idModuloDidactico+"'>"+modulo.moduloDidacticoNombre+"</option>";
			$('#selectModulosCurso').append(option);
		}
	}
});


$('#selectModulosCurso').change(function(){
//	console.log($('#selectModulosCurso').val());
	$('#divListaUsuarios').empty();
	var listaUsuarios = "<ul>" ;
	var idAsignacion = $('#selectAsignaEvento').val();
	var cantidadUsuarios=0;
	for(a in asignacionSelec){
		if((idAsignacion*1) === (asignacionSelec[a].idAsignacion*1)){
			cantidadUsuarios = asignacionSelec[a].participantesAsignacion;
//			console.log(cantidadUsuarios );
			for(var i= 0 ; i<cantidadUsuarios; i++){
				listaUsuarios = listaUsuarios + "<li style='color:#ffa400'>"+idAsignacion+"−"+(i+1)+"</li>" 
			}
			$('#btnActivarUsuarios').attr('disabled', false);
		} 
	}
	listaUsuarios = listaUsuarios + "</ul>"
	$('#divListaUsuarios').append(listaUsuarios);
	$('#usuarioAudienciaParticipantes').val(cantidadUsuarios);
	$('#usuarioAudienciaIdAsignacion').val(idAsignacion);
	$('#usuarioAudienciaNombreEvento').val(nombreCurso);
	$("#usuarioAudienciaidCurso").val(idCurosAsignacion);
	$('#btnActivarUsuarioAudiencia').attr('disabled', false);
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
		   				var asignacion =asignacionSelec[a];
//		   				console.log(asignacion.cursoAsignacion);
		   				if(getCursosModulo(asignacion.idCursoAsignacion)){
		   					optionAsignaciones = optionAsignaciones +  '<option value="'+asignacion.idAsignacion+'">'+asignacion.idCursoAsignacion+' − '+asignacion.cursoAsignacion+' − '+asignacion.clienteAsignacion+'</option>';
//		   					console.log(optionAsignaciones);
		   				}
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

function getCursosModulo(idCursoAsignacion){
	var flag = false;
//	console.log(idCursoAsignacion);
	for(a in modulos){
		var arrayCursos = getArrayModulos(modulos[a].moduloDidacticoCursos);
//		console.log(arrayCursos);
		for(e in arrayCursos){
			if((arrayCursos[e]*1) === (idCursoAsignacion*1)){
//				console.log(idCursoAsignacion);
				flag = true;
			}
		}
	}
	return flag;
}

function getArrayModulos(cursos){
	var tmp = new Array();
//	console.log(cursos);
	if(cursos.includes(",")){
		tmp = cursos.split(",");
	}else{
		tmp.push(cursos);
	}
	 return tmp;
}

function getCursoContein(modulo, idCurso){
	var arrayCursos = getArrayModulos(modulo.moduloDidacticoCursos);
	for(a in arrayCursos){
		var arrayCurso = arrayCursos[a];
		if((arrayCurso*1) === (idCurso*1)){
			console.log(idCurso);
			return true;
		}
	}
	return false;
}

/*
 *  Funciones para dar de alta Usuario Instructor
 */

function formInstructor(){
	$('#selInstructor').empty();
	var selectUsuarioTipo = '<form><fieldset class="nombre"><legend >FORMULARIO CONFIGURACION INSTRUCTOR</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR INSTRUCTOR</label><select class="form-control" id="selInstructor"><option value="" selected>Seleciona Instructor</option></select></div></fieldset></form>';
//	$('#usuarioInstructorForm').append(selectUsuarioTipo);
	$('#divSelInstructor').empty();
	$('#divSelInstructor').append(selectUsuarioTipo);
	var optionInstructor="";
	var instructoresSel = new Array();
	instructoresSel = instructores.instructores;
	var usernameInstructor="";
	var nombreInstructor="";
	for(a in instructoresSel){
		var instructor = instructoresSel[a];
		 optionInstructor =  "<option value='"+instructor.idInstructor+"'>"+instructor.nombreInstructor+"</option>";
		 $('#selInstructor').append(optionInstructor);
		 usernameInstructor = instructor.usuarioInstructor.usernameUsuario;
		 nombreInstructor = instructor.nombreInstructor;
	}
	
	//Selecciona usuario Instructor
	var instructorSel="";
	var instructorBase="";
	$('#selInstructor').change(function(){
		instructorSel = $('#selInstructor').val();
//		console.log(instructorSel);
		for(e in instructoresSel){
			var instructor = instructoresSel[e];
			if((instructor.idInstructor*1) === (instructorSel*1)){
//				console.log(instructor);
				instructorBase = instructor;
			}
		}
		
		//Valida si esta activo en Gamer Uniprotec
		var activo = false;
//		console.log(instructorBase);
		var usuariosGamer = usuarios.usuarios; 
		
		for(i in usuariosGamer){
			var username = usuariosGamer[i].username;
//			console.log(username);
			if(username === instructorBase.usuarioInstructor.usernameUsuario){
//				 console.log(instructorBase.usuarioInstructor.usernameUsuario);
				activo = true;
			}
		}
		//pinta si esta activo
//		if(instructorBase.idUsuarioInstructor === instructorSel){
		if(activo){
//			console.log("usuarioInstructor Activo:"+instructorBase.usuarioInstructor.usernameUsuario);
			$('#instructorActivo').show();
		}else{
//			console.log("usuarioInstructor NO Activo:"+instructorBase.usuarioInstructor.usernameUsuario);
			$('#instructorNoActivo').show();
		}
			
		$(".usernameInstructor").html("<strong>"+ instructorBase.usuarioInstructor.usernameUsuario +"</strong>")
		$("#usuarioInstructorNombre").val(instructorBase.nombreInstructor);
		$("#usuarioInstructorIdAsignacion").val(instructorBase.idInstructor);//$("#usuarioInstructorIdAsignacion").val(instructorBase.usuarioInstructor.idUsuario);
		$("#usuarioInstructorUserName").val(instructorBase.usuarioInstructor.usernameUsuario);
		$("#usuarioInstructorPassword").val(instructorBase.usuarioInstructor.passwordUsuario);
		
		$('#selInstructor').attr('disabled', true);
		
		
	});

}

function formAdministrador(){
	$('#selAdministrador').empty();
	var selectUsuarioTipo = '<form><fieldset class="nombre"><legend>FORMULARIO CONFIGURACION ADMINISTRADOR</legend><div class="form-group"><label for="exampleSelect1">SELECCIONAR ADMINSITRADOR</label><select class="form-control" id="selAdministrador"><option value="" selected>Seleciona Administrador</option></select></div></fieldset></form>';
	$('#divSelAdministrador').empty();
	$('#divSelAdministrador').append(selectUsuarioTipo);
	var optionAdministrador="";
	var administradoresSel = new Array();
	administradoresSel = getAdministrador(usuariosControl.usuariosControl);
	var usernameAdministrador="";
	var nombreAdministrador="";
	for(a in administradoresSel){
		var administrador = administradoresSel[a];
		 optionAdministrador =  "<option value='"+administrador.idUsuario+"'>"+administrador.nombreUsuario+"</option>";
		 $('#selAdministrador').append(optionAdministrador);
		 usernameAdministrador = administrador.usernameUsuario;
		 nombreAdministrador = administrador.nombreUsuario;
	}
	
		//Selecciona usuario Instructor
		var administradorSel="";
		var administradorBase="";
		$('#selAdministrador').change(function(){
			administradorSel = $('#selAdministrador').val();
	//		console.log(instructorSel);
			for(e in administradoresSel){
				var administrador = administradoresSel[e];
				if((administrador.idUsuario*1) === (administradorSel*1)){
	//				console.log(instructor);
					administradorBase = administrador;
				}
			}
	
		//Valida si esta activo en Gamer Uniprotec
		var activo = false;
	//	console.log(usuarios);
		var usuariosGamer = usuarios.usuarios; 
		
		for(i in usuariosGamer){
			var username = usuariosGamer[i].username;
	//		console.log(username);
			if(username === administradorBase.usernameUsuario){
	//			 console.log(instructorBase.usuarioInstructor.usernameUsuario);
				activo = true;
			}
		}
		//pinta si esta activo
	//	if(instructorBase.idUsuarioInstructor === instructorSel){
		if(activo){
//			console.log("usuario administrador Activo:"+administradorBase.usernameUsuario);
			$('#administradorActivo').show();
		}else{
//			console.log("usuario administrador NO Activo:"+administradorBase.usernameUsuario);
			$('#administradorNoActivo').show();
		}

		$(".usernameAdministrador").html("<strong>"+ administradorBase.usernameUsuario +"</strong>")
		$("#usuarioAdministradorNombre").val(administradorBase.nombreUsuario);
		$("#usuarioAdministradorIdAsignacion").val(administradorBase.idUsuario);
		$("#usuarioAdministradorUserName").val(administradorBase.usernameUsuario)
		$("#usuarioAdministradorPassword").val(administradorBase.passwordUsuario)
					
		$('#selAdministrador').attr('disabled', true);
	});
		
		
}


function getAdministrador(usuarios){
	var usuariosControl = new Array();
	for(i in usuarios){
		if(usuarios[i].perfilUsuario === "Direccion" || usuarios[i].perfilUsuario === "Operacion"){
			usuariosControl.push(usuarios[i]);
		}
	}
//	console.log(usuariosControl);
	return usuariosControl;
}



  

  function alerta(){
//  	alert("prueba js invocada desde html");
  }
  
  function checkStatusInstructor(){
		var status = $("#btnStatusInstructor").html();
		
//		console.log(status);
		if(status === "activo" || status === "ACTIVO"){
			$("#btnStatusInstructor").html("NO ACTIVO");
			$("#btnStatusInstructor").removeClass("btn-success");
			$("#btnStatusInstructor").addClass("btn-warning");
			status = "NO ACTIVO";
			alert("Cambio de Status : NO ACTIVO");
		}else{
			$("#btnStatusInstructor").html("ACTIVO");
			$("#btnStatusInstructor").removeClass("btn-warning");
			$("#btnStatusInstructor").addClass("btn-success");
			status = "ACTIVO";
			alert("Cambio de Status : ACTIVO");
		}
		$('#statusInstructor').val(status);
		$('#idUsuarioInstructor').val($idUsuarioInstructor);
		$('#actualizaInstructor').submit();
	}
  
  function checkStatusAudiencia(){
		var status = $("#btnStatusAudiencia").html();
		
//		console.log(status);
		if(status === "activo" || status === "ACTIVO"){
			$("#btnStatusAudiencia").html("NO ACTIVO");
			$("#btnStatusAudiencia").removeClass("btn-success");
			$("#btnStatusAudiencia").addClass("btn-warning");
			status = "NO ACTIVO";
			alert("Cambio de Status : NO ACTIVO");
		}else{
			$("#btnStatusAudiencia").html("ACTIVO");
			$("#btnStatusAudiencia").removeClass("btn-warning");
			$("#btnStatusAudiencia").addClass("btn-success");
			status = "ACTIVO";
			alert("Cambio de Status : ACTIVO");
		}
		console.log($idUsuarioAudiencia);
		$('#statusAudiencia').val(status);
		$('#idUsuarioAudiencia').val($idUsuarioAudiencia);
		$('#actualizaAudiencia').submit();
	}
  
  function getIdCurso(cadena){
	  var tmp = cadena.split(" − ");
	  var tmp1 = tmp[0];
	  tmp1 = tmp1.replace(" ","");
	  tmp1 = tmp1*1;
	  return tmp1;
  }