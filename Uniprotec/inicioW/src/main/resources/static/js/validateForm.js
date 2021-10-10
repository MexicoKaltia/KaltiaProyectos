$(document).ready(function(){
	
	/*
	 * entregableEdicion
	 */
	
	$("#entregableEdicion").validate({
		rules: {
			formARazonSocial :"required",
			formARFC:"required",
			formACurso:"required",
			formADuracion:"required",
			formAInstructor:"required",
			formARepresentanteEmpresa:"required",
			formARepresentanteTrabajador:"required",
			formCRazonSocial :"required",
			formCCurso:"required",
			formCDuracion:"required",
			formCInstructor:"required",
			formCSede:"required",
			formCFechaInicio:"required",
			formCFechaFinal:"required",
			
			checkFormARazonSocial : { required: true },
			checkFormARFC : { required: true },
			checkFormACurso : { required: true },
			checkFormADuracion : { required: true },
			checkFormAFechaInicioDC3 : { required: true },
			checkFormAFechaDiploma : { required: true },
			checkFormAEquipoCredencial : { required: true },
			checkFormAInstructor : { required: true },
			checkFormARepresentanteEmpresa : { required: true },
			checkFormARepresentanteTrabajador : { required: true },
			checkFormCRazonSocial : { required: true },
			checkFormCCurso : { required: true },
			checkFormCDuracion : { required: true },
			checkFormCInstructor : { required: true },
			checkFormCFechas : { required: true },
			checkFormCSede : { required: true }
		
		},
		messages: {
			formARazonSocial : "Favor capturar RazonSocial ",
			formARFC: "Favor capturar RFC",
			formACurso: "Favor capturar Curso",
			formADuracion: "Favor capturar Duracion",
			formAInstructor: "Favor capturar Instructor",
			formARepresentanteEmpresa: "Favor capturar RepresentanteEmpresa",
			formARepresentanteTrabajador: "Favor capturar RepresentanteTrabajador",
			formCRazonSocial : "Favor capturar RazonSocial ",
			formCCurso: "Favor capturar Curso",
			formCDuracion: "Favor capturar Duracion",
			formCInstructor: "Favor capturar Instructor",
			formCSede: "Favor capturar Sede",
			checkFormARazonSocial : { required:"Favor Validar RazonSocial"},
			checkFormARFC : { required:"Favor Validar RFC"},
			checkFormACurso : { required:"Favor Validar Curso"},
			checkFormADuracion : { required:"Favor Validar Duracion"},
			checkFormAFechaInicioDC3 : { required:"Favor Validar FechaInicioDC3"},
			checkFormAFechaDiploma : { required:"Favor Validar FechaDiploma"},
			checkFormAEquipoCredencial : { required:"Favor Validar EquipoCredencial"},
			checkFormAInstructor : { required:"Favor Validar Instructor"},
			checkFormARepresentanteEmpresa : { required:"Favor Validar RepresentanteEmpresa"},
			checkFormARepresentanteTrabajador : { required:"Favor Validar RepresentanteTrabajador"},
			checkFormCRazonSocial : { required:"Favor Validar RazonSocial"},
			checkFormCCurso : { required:"Favor Validar Curso"},
			checkFormCDuracion : { required:"Favor Validar Duracion"},
			checkFormCInstructor : { required:"Favor Validar Instructor"},
			checkFormCFechas : { required:"Favor Validar Fechas"},
			checkFormCSede : { required:"Favor Validar Sede"}
			
		}
	});
	/*
	 * fin entregableEdicion
	 */

    

	
});



function validaModalFormContacto(){
	var contact_name = $("#contact_name").val() ;
	var contact_message = $("#contact_message").val() ;
	var telefonoRegistro = $("#telefonoRegistro").val() ;
	console.log(contact_name)
	if(contact_message !== "" && contact_name!== "" && telefonoRegistro!== ""){
		if(contact_name.length >= 3 && !contact_message.includes("@")  && telefonoRegistro.length ==10 && isNumber(telefonoRegistro)){
			$("#btnSaveRegistro").show();
		}else{
			$("#btnSaveRegistro").hide();
		}
	}
}

function isNumber(n) { return /^-?[\d.]+(?:e-?\d+)?$/.test(n); } 
