
   	
$(document).ready(function(){
	
	 	
});  // fin de documento JQuery

function capturePass(){
	$('#alertPass').empty();
	if($('#passAnterior').val()===null || $('#passAnterior').val()===""){
		$('#passNuevo').attr("disabled", true);
		$('#passConfirm').attr("disabled", true);
	}else{
		$('#passNuevo').attr("disabled", false);
		$('#passConfirm').attr("disabled", false);
	}
}

$('#submitPass').click(function(){
	var nuevo = 	$('#passNuevo').val();
	var confirm =   $('#passConfirm').val();
	if(nuevo === ""){
		return false;
	}
	if(nuevo === confirm){
		$('#nombreUsuario').attr("disabled", false);
		$('#emailUsuario').attr("disabled", false);
		$('#perfilUsuario').attr("disabled", false);
		return true;
	}else{
		//console.log("no coinciden");
		$('#alertPass').append("<div class='alert alert-danger'  role='alert'>Confirmar Password  no coincide con Password Nuevo</div>");
		$('#passAnterior').val("");
		$('#passNuevo').val("");
		$('#passConfirm').val("");
		return false;
	}
		
		
});