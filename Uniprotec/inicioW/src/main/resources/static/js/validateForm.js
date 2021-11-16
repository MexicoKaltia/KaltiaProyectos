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
			formARazonSocial : "Favor capturar Razon Social ",
			formARFC: "Favor capturar RFC",
			formACurso: "Favor capturar Curso",
			formADuracion: "Favor capturar Duracion",
			formAInstructor: "Favor capturar Instructor",
			formARepresentanteEmpresa: "Favor capturar Representante Empresa",
			formARepresentanteTrabajador: "Favor capturar Representante Trabajador",
			formCRazonSocial : "Favor capturar Razon Social ",
			formCCurso: "Favor capturar Curso",
			formCDuracion: "Favor capturar Duracion",
			formCInstructor: "Favor capturar Instructor",
			formCSede: "Favor capturar Sede",
			checkFormARazonSocial : { required:"Favor Validar Razon Social"},
			checkFormARFC : { required:"Favor Validar RFC"},
			checkFormACurso : { required:"Favor Validar Curso"},
			checkFormADuracion : { required:"Favor Validar Duracion"},
			checkFormAFechaInicioDC3 : { required:"Favor Validar Fecha Inicio DC3"},
			checkFormAFechaDiploma : { required:"Favor Validar Fecha Diploma"},
			checkFormAEquipoCredencial : { required:"Favor Validar Equipo Credencial"},
			checkFormAInstructor : { required:"Favor Validar Instructor"},
			checkFormARepresentanteEmpresa : { required:"Favor Validar Representante Empresa"},
			checkFormARepresentanteTrabajador : { required:"Favor Validar Representante Trabajador"},
			checkFormCRazonSocial : { required:"Favor Validar Razon Social"},
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
