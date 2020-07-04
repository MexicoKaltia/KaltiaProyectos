/**
 * Archivo de control JS para Modulo Usuarios 
 */

$(document).ready(function() {
	
	
}); //fin jQuery





function validaUsuario(){
	var perfil = $('#perfilUsuario').val();
	ocultar();
	$('#alertaPerfil').empty();	
	if(perfil === "Vendedor"){
//		console.log("vendedor1");
		alertaVendedor = '<br><div class="alert alert-primary" role="alert">Alta Usuario Perfil Vendedor</div><br>'
		botonVendedor = '<a href="AVendedor"><button type="button" class="btn btn-primary">Agregar Usuario Vendedor</button></a>'
		$('#alertaPerfil').append(alertaVendedor + botonVendedor);
		$('#agregarUsuario').attr("disabled", true);
	}
	else if(perfil === "Instructor"){
//		console.log("Instructor1");
		alertaInstructor = '<br><div class="alert alert-primary" role="alert">Alta Usuario Perfil Instructor</div><br>'
		botonInstructor = '<a href="AInstructor"><button type="button" class="btn btn-primary">Agregar Usuario Instructor</button></a>'
		$('#alertaPerfil').append(alertaInstructor + botonInstructor);
		
		$('#agregarUsuario').attr("disabled", true);
	}else{
		$('#nombreUsuarioDiv').show();
		$('#emailUsuarioDiv').show();
		$('#notaUsuarioDiv').show();
		$('#agregarUsuario').show();
		$('#nombreUsuario').attr("disabled", false);
		$('#emailUsuario').attr("disabled", false);
		$('#notaUsuario').attr("disabled", false);
		$('#agregarUsuario').attr("disabled", false);
	}
}

function ocultar(){
	$('#nombreUsuarioDiv').hide();
	$('#emailUsuarioDiv').hide();
	$('#notaUsuarioDiv').hide();
	$('#agregarUsuario').hide();
}
