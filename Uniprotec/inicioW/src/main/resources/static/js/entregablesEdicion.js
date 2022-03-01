function operateFormatterUpdate(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalParticipantes">',
      '<i class="fa fa-2x fa-user-edit"></i>',
      '</a>'
    ].join('')
  }

var $idEntregable ;
var $idEntregableLogico ;
$(document).ready(function() {
	
	console.log(asignacionItem);
//	console.log(asignacionCliente);
//	console.log(entregables);
//	console.log(entregables.length);
//	console.log(participantes);
//	console.log(participantes.length);
	var e=0;
	var $participantes;
	var $entregable;
	var $participantesLength = 0;
	var $estatusEntregable ="";
//	var $idEntregable ;
	
	for(var a in entregables){
		e++;
	}
	
	
	$("#idAsignacionEncabezado").html(asignacionItem.idAsignacion);
	$('#modalEntregables').modal();
	
	$("#encabezadoCliente").text(asignacionItem.clienteAsignacion);
	$("#encabezadoCurso").html(asignacionItem.cursoAsignacion);
	$("#encabezadoInstructor").html(asignacionItem.instructorAsignacion);
	
	$('#btnOmitir').click(function(){
		console.log("btnOmitir")
		$('#statusAsignacion').val("Omitir Entregable");
		
		$('#idAsignacion').val(asignacionItem.idAsignacion);
		$('#idAsignacionLogica').val(asignacionItem.idAsignacionLogica);
		$('#fechaAsignacion').val(asignacionItem.fechaAsignacion);
		$('#idClienteAsignacion').val(asignacionItem.idClienteAsignacion);
		$('#clienteAsignacion').val(asignacionItem.clienteAsignacion);
		$('#idCursoAsignacion').val(asignacionItem.idCursoAsignacion);
		$('#cursoAsignacion').val(asignacionItem.cursoAsignacion);
		$('#idInstructorAsignacion').val(asignacionItem.idInstructorAsignacion);
		$('#instructorAsignacion').val(asignacionItem.instructorAsignacion);
		$('#horarioAsignacion').val(asignacionItem.horarioAsignacion);
		$('#participantesAsignacion').val(asignacionItem.participantesAsignacion);
		$('#nivelAsignacion').val(asignacionItem.nivelAsignacion);
		$('#archivosAsignacion').val(asignacionItem.archivosAsignacion);
		$('#archivosAsignacionTexto').val(asignacionItem.archivosAsignacionTexto);
		$('#observacionesAsignacion').val(asignacionItem.observacionesAsignacion);
		$('#idRegionAsignacion').val(asignacionItem.idRegionAsignacion);
		$('#nombreRegionAsignacion').val(asignacionItem.nombreRegionAsignacion);
		$('#tipoCursoAsignacion').val(asignacionItem.tipoCursoAsignacion);
		$('#verificarEntregable').val(asignacionItem.verificarEntregable);
		$('#userCreateAsignacion').val(asignacionItem.userCreateAsignacion);
		$('#userCreateAsignacionTexto').val(asignacionItem.userCreateAsignacionTexto);
		$('#guiaEntregable').val(asignacionItem.guiaEntregable);
		$('#archivoParticipantesTexto').val(asignacionItem.archivoParticipantesTexto);
		$('#fechaPago').val(asignacionItem.fechaPago);
		$('#numeroFactura').val(asignacionItem.numeroFactura);
		$('#costoHotel').val(asignacionItem.costoHotel);
		$('#errorProceso').val(asignacionItem.errorProceso);
		
		$('#formEntregables').submit();
	});
	
	if(e > 0){
		console.log("entregable existente");
		var listEntregables = '<div class="card-body"><h5 class="card-title">cuenta con los siguientes expedientes de Documentacion:</h5>\
									<ul class="list-group list-group-flush" id="listEntregables">\
									</ul>\
								</div>';
		$('#tituloModalEntregables').after(listEntregables);
		var e =0;
		for(var a in entregables){
			var entregable = entregables[a]; 
			var entregableItem = '<a id="'+entregable.idEntregable+'" href="" data-dismiss="modal" class="entregable list-group-item">'+entregable.idEntregable+'-'+entregable.formACurso+'</a>';
			$('#listEntregables').append(entregableItem);
			e++;
		}
		
		console.log("entregable edicion");
		$("#formARazonSocial").val(asignacionCliente.nombreCompletoCliente);
		$("#formARFC").val(asignacionCliente.rfcCliente);
		$("#formACurso").val(asignacionItem.cursoAsignacion);
		$("#formADuracion").val(duracion(asignacionItem.horarioAsignacion));
		$("#formAInstructor").val(asignacionItem.instructorAsignacion);
		$("#formARepresentanteEmpresa").val(asignacionCliente.representanteEmpresaCliente);
		$("#formARepresentanteTrabajador").val(asignacionCliente.representanteTrabajadorCliente);
		
		$('#formCRazonSocial').val($("#formARazonSocial").val());
		$('#formCCurso').val($("#formACurso").val());
		$('#formCDuracion').val($("#formADuracion").val());
		$('#formCInstructor').val($("#formAInstructor").val());
		$('#formCSede').val(asignacionCliente.domicilioCliente);
		
		$('.entregable').click(function(){
			var valorEntregable = $(this).attr("id");
			var i=0;
			for(var a in entregables){
				var entregable = entregables[a];
				if((entregable.idEntregable*1) === (valorEntregable*1) ){
					$entregable = entregable;
					$estatusEntregable = "edicion";
					$idEntregable = i;
					$idEntregableLogico = asignacionItem.idAsignacion+"_"+$idEntregable;
					
					console.log($entregable);
					console.log($idEntregableLogico);
					$("#formARazonSocial").val(entregable.formARazonSocial);
					$("#formARFC").val(entregable.formARFC);
					$("#formACurso").val(entregable.formACurso);
					$("#formADuracion").val(entregable.formADuracion);
					
					$("#checkFormAFechaInicioDC3").attr('checked', true);
					$("#checkFormAFechaDiploma").attr('checked', true);
					$("#checkFormAEquipoCredencial").attr('checked', true);
					$('#formAFechaInicioDC3').val(entregable.formAFechaInicioDC3);
					$('#formAFechaFinDC3').val(entregable.formAFechaFinDC3);
					$('#formAFechaDiploma').val(entregable.formAFechaDiploma);
					$('#formAEquipoCredencial').val(entregable.formAEquipoCredencial);
					$('#formAFechaInicioCredenciales').val(entregable.formAFechaInicioCredenciales);
					$('#formAFechaFinalCredenciales').val(entregable.formAFechaFinalCredenciales);
					
					$('#formAFechaInicioDC3').attr("disabled", true);
					$('#formAFechaFinDC3').attr("disabled", true);
					$('#formAFechaDiploma').attr("disabled", true);
					$('#formAEquipoCredencial').attr("disabled", true);
					$('#formAFechaInicioCredenciales').attr("disabled", true);
					$('#formAFechaFinalCredenciales').attr("disabled", true);
					
					$("#formAInstructor").val(entregable.formAInstructor);
					$("#formARepresentanteEmpresa").val(entregable.formARepresentanteEmpresa);
					$("#formARepresentanteTrabajador").val(entregable.formARepresentanteTrabajador);
					
					$('#imagenLogoClienteEdicion').attr('src', "/uploadsEntregables/"+asignacionCliente.rfcCliente+"/"+$idEntregableLogico+"/imageLogo/"+entregable.formALogo);
				    $('#imagenLogoClienteEdicion').attr('alt', asignacionCliente.imagenLogoCliente);
					
					var arrayParticipantes = new Array();
					for(var e in participantes){
						var participantesEntregable = participantes[e];
						for(var i in participantesEntregable){
							var participante = participantesEntregable[i];
							if((participante.idEntregable*1) === (valorEntregable*1)){
								
								arrayParticipantes.push(participante);
							}
							$participantesLength++;
						}
					}
					
					$participantes = arrayParticipantes;
					console.log($participantes);
					$('#participantesTable').bootstrapTable('load', $participantes);
					$('#participantesTable').bootstrapTable({data : $participantes});
					
					$('#formCRazonSocial').val(entregable.formCRazonSocial);
					$('#formCCurso').val(entregable.formCCurso);
					$('#formCDuracion').val(entregable.formCDuracion);
					$('#formCInstructor').val(entregable.formCInstructor);
					
					$("#checkFormCFechas").attr('checked', true);
					$('#formCFechaInicio').val(entregable.formCFechaInicio);
					$('#formCFechaFinal').val(entregable.formCFechaFinal);
					$('#formCFechaInicio').attr("disabled", true);
					$('#formCFechaFinal').attr("disabled", true);
					
					
					$('#formCSede').val(entregable.formCSede);
//					$('#formCComentariosGrupo').val(entregable.formCComentariosGrupo);
					$('#formCComentariosGrupoE').html(entregable.formCComentariosGrupo);
					$('#formCProcesoAprendizajeE').html(entregable.formCProcesoAprendizaje);
					$('#formCTeoriaE').html(entregable.formCTeoria);
					$('#formCPracticaE').html(entregable.formCPractica);
					
					
					if(entregable.formCEvidenciasFotograficas){
						$('#evidenciasFotograficas').empty();
						var arrayImgEvidencias = entregable.formCEvidenciasFotograficas.split(","); 
						for(var a in arrayImgEvidencias){
							var urlImgEvidencia = "/uploadsEntregables/"+asignacionCliente.rfcCliente+"/"+$idEntregableLogico+"/imagenesEvidencias/"+arrayImgEvidencias[a];
							var imgEvidencia =  '<img id="imgEvidencia" src="'+urlImgEvidencia+'" class="img-fluid rounded float-left" alt="">';
							$('#evidenciasFotograficas').append(imgEvidencia);
						}
//						console.log(entregable.formCEvidenciasFotograficas);
//						console.log(arrayImgEvidencias);
						$('#formCEvidenciasFotograficasB').val(arrayImgEvidencias);
					}
					
					if(entregable.formCEvidenciaDocto){
						$('#linkDocto').empty();
						var arrayEvidenciasDocto = entregable.formCEvidenciaDocto.split(","); 
						for(var a in arrayEvidenciasDocto){
							var urlEvidenciaDocto = "/uploadsEntregables/"+asignacionCliente.rfcCliente+"/"+$idEntregableLogico+"/file/"+arrayEvidenciasDocto[a];
							var evidenciaDocto =  "<div class='alert alert-success' role='alert'><a href='"+urlEvidenciaDocto+"'>"+arrayEvidenciasDocto[a]+"</div>";
							$('#linkDocto').append(evidenciaDocto);
						}
//						console.log(entregable.formCEvidenciasFotograficas);
//						console.log(arrayImgEvidencias);
						$('#formCEvidenciaDoctoB').val(arrayEvidenciasDocto);
					}
					
					$('#formCRecomendacionesE').html(entregable.formCRecomendaciones);
					$('#formCNivelCumplimientoE').html(entregable.formCNivelCumplimiento);
					$('#formCContingenciasE').html(entregable.formCContingencias);
					$('#formCAvancesLogradosE').html(entregable.formCAvancesLogrados);
					$('#formCObservacionesE').html(entregable.formCObservaciones);
//					$('#formCEvidenciaDocto').val(entregable.formCEvidenciaDocto);
					
				}				
			i++;
			}
		});
		
	}else{
		console.log("entregable nuevo");
		$estatusEntregable = "nuevo";
		$idEntregable = 0;
		$idEntregableLogico = asignacionItem.idAsignacion+"_"+$idEntregable;
		
		$('#tituloModalEntregables').after('<h5 class="card-title">no cuenta con expediente de Documentacion</h5>');
		
		$("#formARazonSocial").val(asignacionCliente.nombreCompletoCliente);
		$("#formARFC").val(asignacionCliente.rfcCliente);
		$("#formACurso").val(asignacionItem.cursoAsignacion);
		$("#formADuracion").val(duracion(asignacionItem.horarioAsignacion));
		$("#formAInstructor").val(asignacionItem.instructorAsignacion);
		$("#formARepresentanteEmpresa").val(asignacionCliente.representanteEmpresaCliente);
		$("#formARepresentanteTrabajador").val(asignacionCliente.representanteTrabajadorCliente);
		
		$('#imagenLogoClienteEdicion').attr('src', "/uploads/img/"+asignacionCliente.rfcCliente+"/"+asignacionCliente.imagenLogoCliente);
	    $('#imagenLogoClienteEdicion').attr('alt', asignacionCliente.imagenLogoCliente);
		
		$('#formCRazonSocial').val($("#formARazonSocial").val());
		$('#formCCurso').val($("#formACurso").val());
		$('#formCDuracion').val($("#formADuracion").val());
		$('#formCInstructor').val($("#formAInstructor").val());
		$('#formCSede').val(asignacionCliente.domicilioCliente);
	}
	
	$('#btnAgregarNuevo').click(function(){
		console.log("entregable nuevo expediente");
		$estatusEntregable = "nuevo";
		$idEntregable = e++;
		$idEntregableLogico = asignacionItem.idAsignacion+"_"+$idEntregable;
		$('#tituloModalEntregables').after('<h5 class="card-title">no cuenta con expediente de Documentacion</h5>');
		
		$("#formARazonSocial").val(asignacionCliente.nombreCompletoCliente);
		$("#formARFC").val(asignacionCliente.rfcCliente);
		$("#formACurso").val(asignacionItem.cursoAsignacion);
		$("#formADuracion").val(duracion(asignacionItem.horarioAsignacion));
		$("#formAInstructor").val(asignacionItem.instructorAsignacion);
		$("#formARepresentanteEmpresa").val(asignacionCliente.representanteEmpresaCliente);
		$("#formARepresentanteTrabajador").val(asignacionCliente.representanteTrabajadorCliente);
		
		$('#imagenLogoClienteEdicion').attr('src', "/uploads/img/"+asignacionCliente.rfcCliente+"/"+asignacionCliente.imagenLogoCliente);
	    $('#imagenLogoClienteEdicion').attr('alt', asignacionCliente.imagenLogoCliente);
		
		$('#formCRazonSocial').val($("#formARazonSocial").val());
		$('#formCCurso').val($("#formACurso").val());
		$('#formCDuracion').val($("#formADuracion").val());
		$('#formCInstructor').val($("#formAInstructor").val());
		$('#formCSede').val(asignacionCliente.domicilioCliente);
	})
	
	$('#imagenLogoClientePrev').attr('src', "/uploads/img/"+asignacionCliente.rfcCliente+"/"+asignacionCliente.imagenLogoCliente);
    $('#imagenLogoClientePrev').attr('alt', asignacionCliente.imagenLogoCliente);
    
	$("#checkFormARazonSocial").attr('checked', true);
	$("#checkFormARFC").attr('checked', true);
	$("#checkFormACurso").attr('checked', true);
	$("#checkFormADuracion").attr('checked', true);
	$("#checkFormAInstructor").attr('checked', true);
	$("#checkFormARepresentanteEmpresa").attr('checked', true);
	$("#checkFormARepresentanteTrabajador").attr('checked', true);
	
	$("#formARazonSocial").attr("disabled", true);
	$("#formARFC").attr("disabled", true);
	$("#formACurso").attr("disabled", true);
	$("#formADuracion").attr("disabled", true);
	$("#formAInstructor").attr("disabled", true);
	$("#formARepresentanteEmpresa").attr("disabled", true);
	$("#formARepresentanteTrabajador").attr("disabled", true);
	
	//Form B
	
	// Form C
	$("#checkFormCRazonSocial").attr('checked', true);
	$("#checkFormCCurso").attr('checked', true);
	$("#checkFormCDuracion").attr('checked', true);
	$("#checkFormCInstructor").attr('checked', true);
	$("#checkFormCSede").attr('checked', true);
	
	$('#formCRazonSocial').attr("disabled", true);
	$('#formCCurso').attr("disabled", true);
	$('#formCDuracion').attr("disabled", true);
	$('#formCInstructor').attr("disabled", true);
	$('#formCSede').attr("disabled", true);
	
	// RFC Original Asignacion
	$('#rfcOriginalAsignacion').val(asignacionCliente.rfcCliente);

	$('#btnGeneraDocto').hide();

	$(".check").click(function(){
		var idInput = $(this).attr("id");
		idInput = idInput.replace("checkForm", "form");
		if(!$(this).is(':checked')) {
//			console.log(idInput);
			$("#"+idInput).attr("disabled",false);
		}else{
			$("#"+idInput).attr("disabled",true);
		}
		
		if(idInput === "formAFechaInicioDC3"){
			if($(this).is(':checked')) {
				if($("#formAFechaFinDC3").val() === "" || $("#formAFechaFinDC3").val() === null || $("#formAFechaInicioDC3").val() === "" || $("#formAFechaInicioDC3").val() === null){
					$("#formAFechaInicioDC3").val("");
					$("#formAFechaFinDC3").val("");
				}
				$("#formAFechaFinDC3").attr("disabled", true);
			}else{
				$("#formAFechaFinDC3").attr("disabled", false);
			}
		}
		
		
		if(idInput === "formAEquipoCredencial"){
			if($(this).is(':checked')) {
				if(($("#formAEquipoCredencial").val() ==="" && $("#formAFechaInicioCredenciales").val() === "" && $("#formAFechaFinalCredenciales").val() === "" ) || ($("#formAEquipoCredencial").val() !=="" && $("#formAFechaInicioCredenciales").val() !== "" && $("#formAFechaFinalCredenciales").val() !== "" ) ){
//					console.log($("#formAEquipoCredencial").val());
					$("#formAFechaInicioCredenciales").attr("disabled", true);
					$("#formAFechaFinalCredenciales").attr("disabled", true);
				}else {
					alert("Debes Capturar Equipo Credencial y Fechas Credenciales");
					$(this).prop('checked', false);
					$("#formAEquipoCredencial").attr("disabled", false);
					$("#formAFechaInicioCredenciales").attr("disabled", false);
					$("#formAFechaFinalCredenciales").attr("disabled", false);
				}
			}else{
				$("#formAFechaInicioCredenciales").attr("disabled", false);
				$("#formAFechaInicioCredenciales").val("");
				$("#formAFechaFinalCredenciales").attr("disabled", false);
				$("#formAFechaFinalCredenciales").val("");
			}
		}
		
		if(idInput === "formCFechas"){
			if($(this).is(':checked')) {
				if($("#formCFechaInicio").val() === "" || $("#formCFechaFinal").val() === null || $("#formCFechaInicio").val() === "" || $("#formCFechaFinal").val() === null){
					$("#formCFechaInicio").val("");
					$("#formCFechaFinal").val("");
				}
				$("#formCFechaInicio").attr("disabled", true);
				$("#formCFechaFinal").attr("disabled", true);
			}else{
				$("#formCFechaInicio").attr("disabled", false);
				$("#formCFechaFinal").attr("disabled", false);
			}
		}
		
		// VALIDAR TODOS LOS CHECKBOX
		var arrayFlags = new Array();
		if($("#checkFormAFechaInicioDC3").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormARazonSocial").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormACurso").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormARFC").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormADuracion").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormAFechaInicioDC3").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormAFechaDiploma").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormAEquipoCredencial").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormAInstructor").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormARepresentanteEmpresa").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormARepresentanteTrabajador").is(':checked')){arrayFlags.push(true)};
		
		$participantes = $('#participantesTable').bootstrapTable('getData');
		
		if($("#checkFormCRazonSocial").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormCCurso").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormCDuracion").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormCInstructor").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormCFechas").is(':checked')){arrayFlags.push(true)};
		if($("#checkFormCSede").is(':checked')){arrayFlags.push(true)};
		
		var e = 0;
		for(a in arrayFlags){if(a){e++;}}
		
		$('#btnAltaEntregable').attr('disabled', true);
		$('#divGeneraDocto').empty();
		$('#btnGeneraDocto').hide();
		if(e === 17 ){
			var flagFoto = false;
			$('#btnAltaEntregable').attr('disabled', false);
			if($participantes.length > 0){
				for(var a in $participantes){
					var participante = $participantes[a];
					if(participante.participanteFoto === ""){
						flagFoto = true;
						alert("Revisa a los participantes que contenga su Foto, parece que no está completo el expediente de participantes");
						break;
					}
				}
				if(!flagFoto){
					$('#btnGeneraDocto').show();
				}
				
//				$('#divGeneraDocto').append('<button type="button" id="btnGeneraDocto" class="mb-2 mr-2 btn btn-success btn-lg btn-block">Generar Documentación</button>');
			}
		}
		
	});
	
	$(".prom").focusout(function(){
//		console.log("prom")
		  var eti = $('#participanteETI').val();
		  var etf = $('#participanteETF').val();
		  var ep = $('#participanteEP').val();
		  var prom = $('#participanteP').val();
//		  console.log(isNumber(eti));
		  
		  if(isNumber(eti)){
			  if((eti*1) > 10){
			  $('#participanteETI').val(0);
		  }}
		  if(isNumber(etf)){
			  if((etf*1)>10){
			  $('#participanteETF').val(0);
			  $('#participanteP').val(0);
		  }}
		  if(isNumber(ep)){
			  if((ep*1)>10){
			  $('#participanteEP').val(0);
			  $('#participanteP').val(0);
		  }}
		  if(isNumber(prom)){
			  if((eti*1)>10){
			  $('#participanteP').val(0);
		  }}
	});
	
	
	

	
	
	
	
	/*
	 * TABLE PARTICIPANTES
	 */
	
	
	var $idParticipante = "";
	
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		    	console.log(JSON.stringify(row));
		    	$('#idParticipante').val(row.idParticipante);
		    	$('#participanteNombre').val(row.participanteNombre);
				$('#participanteCURP').val(row.participanteCURP);
				$('#participantePuesto').val(row.participantePuesto);
				$('#participanteOcupacion').val(row.participanteOcupacion);
				$('#formBFotoParticipante').val("");
				$('#imagenParticipantePrev').attr('src', "/uploadsEntregables/"+asignacionCliente.rfcCliente+"/"+$idEntregableLogico+"/imagenesParticipantes/"+row.participanteFoto);
				$('#participanteETI').val(row.participanteExamenTeoricoInicial);
				$('#participanteETF').val(row.participanteExamenTeoricoFinal);
				$('#participanteEP').val(row.participanteExamenPractico);
				$('#participanteP').val(row.participantePromedio);
				$('#participanteAprovechamiento').val(row.participanteAprovechamiento);
				
				
				$('#btnAgregarParticipante').hide();
				$('#btnEditarParticipante').show();
				$idParticipante = row.idParticipante;
//					     console.log($idParticipante); 
				
				$('#checkAprovechamiento').bootstrapToggle('on');
				if(row.participantePromedio < 8){
					$('#checkAprovechamiento').bootstrapToggle('off');
				}
		    }
		   }
	
//	$('#participantesTable').bootstrapTable({data : $participantes});
	
	
	$('#addUser').click(function(){
		$('#btnEditarParticipante').hide();
		$('#btnAgregarParticipante').show();
		$('#participanteNombre').val("");
		$('#participanteCURP').val("");
		$('#participantePuesto').val("");
		$('#participanteOcupacion').val("");
		$('#formBFotoParticipante').val("");
		$('#participanteETI').val("");
		$('#participanteETF').val("");
		$('#participanteEP').val("");
		$('#participanteP').val("");
		$('#participanteAprovechamiento').val("");
		console.log($participantesLength);
		if($participantesLength > 0){
			$participantesLength = $participantesLength + 1;
		}else{
			$participantesLength = 1;
		}
	});
	
	var arrayParticipantes
	$('#btnAgregarParticipante').click(function(){
//		console.log("btnAgregarParticipante");
		var jsonParticipante = {
				idParticipante: asignacionItem.idAsignacion +"-"+ $participantesLength,
				participanteNombre: $('#participanteNombre').val(),
				participanteCURP: $('#participanteCURP').val().toUpperCase(),
				participantePuesto: $('#participantePuesto').val(),
				participanteOcupacion: $('#participanteOcupacion').val(),
				participanteFoto: editaNombreImagen($('#formBFotoParticipante').val()),
				participanteExamenTeoricoInicial: $('#participanteETI').val(),
				participanteExamenTeoricoFinal: $('#participanteETF').val(),
				participanteExamenPractico: $('#participanteEP').val(),
				participantePromedio: $('#participanteP').val(),
				participanteAprovechamiento: $('#participanteAprovechamiento').val(),
				participanteAprobado : $('#checkAprovechamiento').is(':checked')
		};
		$('#participantesTable').bootstrapTable('append', jsonParticipante)
		$participantes = $('#participantesTable').bootstrapTable('getData');
	});
	
	$('#btnEditarParticipante').click(function(){
		console.log("btnEditarParticipante");
		var checkAprobado = $('#checkAprovechamiento').is(':checked');
		var jsonParticipante = {
				idParticipante: $('#idParticipante').val(),
				participanteNombre: $('#participanteNombre').val(),
				participanteCURP: $('#participanteCURP').val().toUpperCase(),
				participantePuesto: $('#participantePuesto').val(),
				participanteOcupacion: $('#participanteOcupacion').val(),
				participanteFoto: editaNombreImagen($('#formBFotoParticipante').val()),
				participanteExamenTeoricoInicial: $('#participanteETI').val(),
				participanteExamenTeoricoFinal: $('#participanteETF').val(),
				participanteExamenPractico: $('#participanteEP').val(),
				participantePromedio: $('#participanteP').val(),
				participanteAprovechamiento: $('#participanteAprovechamiento').val(),
				participanteAprobado : checkAprobado
		};
		
		var tmp = new Array();
		for(var a in $participantes){
			var participante = $participantes[a];
			if(participante.idParticipante !== $idParticipante){
				tmp.push(participante);
			}
		}
		$participantes = tmp;
		$('#participantesTable').bootstrapTable('load', $participantes);
		$('#participantesTable').bootstrapTable('append', jsonParticipante);
		$participantes = $('#participantesTable').bootstrapTable('getData');
		
	});

	/**
	 *  ALTA ENTREGABLE y ALTA DOCUMENTACION
	 */
	$('#btnAltaEntregable').click(function(){
		console.log("Alta Formulario Entregable");
		$participantes = $('#participantesTable').bootstrapTable('getData');
		var arrayParticipantes = new Array();
		var flagParticipantes = false;
		var flagDiploma = false;
		var flagCredencial = false;
		var flagDC3 = false;
		
		for(var a in $participantes){
			arrayParticipantes.push(JSON.stringify($participantes[a]));
			flagParticipantes = true;
		}
		
		if(!flagParticipantes){
			alert("Parece que no has registrado Participantes para DC3, Diploma o Credenciales.");
		}
		
		$("#idAsignacion").val(asignacionItem.idAsignacion);
		$("#idInstructorAsignacion").val(asignacionItem.idInstructorAsignacion);
		$("#nombreFirmaInstructorAsignacion").val(asignacionItem.nombreFirmaInstructor);
		
		if($entregable){
			$("#idEntregable").val($entregable.idEntregable);
//			console.log($entregable.idEntregable);
		}
			
		$("#formARazonSocialA").val($("#formARazonSocial").val());
		$("#formACursoA").val($("#formACurso").val());
		$("#formARFCA").val($("#formARFC").val());
		$("#formADuracionA").val($("#formADuracion").val());
		$("#formAFechaInicioDC3A").val($("#formAFechaInicioDC3").val());
		$("#formAFechaFinDC3A").val($("#formAFechaFinDC3").val());
		$("#formAFechaDiplomaA").val($("#formAFechaDiploma").val());
		$("#formAEquipoCredencialA").val($("#formAEquipoCredencial").val());
		$("#formAFechaInicioCredencialesA").val($("#formAFechaInicioCredenciales").val());
		$("#formAFechaFinalCredencialesA").val($("#formAFechaFinalCredenciales").val());
		$("#formAInstructorA").val($("#formAInstructor").val());
		$("#formARepresentanteEmpresaA").val($("#formARepresentanteEmpresa").val());
		$("#formARepresentanteTrabajadorA").val($("#formARepresentanteTrabajador").val());	
		
		var formALogo = $("#formALogo").val();
		if(formALogo.toString() === "" && $estatusEntregable ==="nuevo"){
			$("#estatusEntregable").val($estatusEntregable);
			$("#formALogoA").val(asignacionCliente.imagenLogoCliente);
		}
		if(formALogo.toString() === "" && $estatusEntregable ==="edicion"){
			$("#estatusEntregable").val($estatusEntregable);
			$("#formALogoA").val($entregable.formALogo);
		}


		$("#formBParticipantesA").val(arrayParticipantes);

		$("#formCRazonSocialA").val($("#formCRazonSocial").val());
		$("#formCCursoA").val($("#formCCurso").val());
		$("#formCDuracionA").val($("#formCDuracion").val());
		$("#formCInstructorA").val($("#formCInstructor").val());
		$("#formCFechaInicioA").val($("#formCFechaInicio").val());
		$("#formCFechaFinalA").val($("#formCFechaFinal").val());
		$("#formCSedeA").val($("#formCSede").val());
		formCTextArea($entregable);
				
		$("#idEntregableLogico").val($idEntregableLogico);
		
		if($("#entregableEdicion").valid()){
			$('#myModalProcess').modal();
		}
	});
	
	$('#btnGeneraDocto').click(function(){
		console.log("Alta Documentacion Entregable");
		console.log("boton alta documentacion");
		$participantes = $('#participantesTable').bootstrapTable('getData');
		var arrayParticipantes = new Array();
		
		
		$("#idAsignacion").val(asignacionItem.idAsignacion);
		$("#idInstructorAsignacion").val(asignacionItem.idInstructorAsignacion);
		$("#nombreFirmaInstructorAsignacion").val(asignacionItem.nombreFirmaInstructor);
		
		if($entregable){
			$("#idEntregable").val($entregable.idEntregable);
		}
		for(var a in $participantes){
			arrayParticipantes.push(JSON.stringify($participantes[a]));
		}
			
		$("#formARazonSocialA").val($("#formARazonSocial").val());
		$("#formACursoA").val($("#formACurso").val());
		$("#formARFCA").val($("#formARFC").val());
		$("#formADuracionA").val($("#formADuracion").val());
		$("#formAFechaInicioDC3A").val($("#formAFechaInicioDC3").val());
		$("#formAFechaFinDC3A").val($("#formAFechaFinDC3").val());
		$("#formAFechaDiplomaA").val($("#formAFechaDiploma").val());
		$("#formAEquipoCredencialA").val($("#formAEquipoCredencial").val());
		$("#formAFechaInicioCredencialesA").val($("#formAFechaInicioCredenciales").val());
		$("#formAFechaFinalCredencialesA").val($("#formAFechaFinalCredenciales").val());
		$("#formAInstructorA").val($("#formAInstructor").val());
		$("#formARepresentanteEmpresaA").val($("#formARepresentanteEmpresa").val());
		$("#formARepresentanteTrabajadorA").val($("#formARepresentanteTrabajador").val());
		
		var formALogo = $("#formALogo").val();
		if(formALogo.toString() === "" && $estatusEntregable ==="nuevo"){
			$("#estatusEntregable").val($estatusEntregable);
			$("#formALogoA").val(asignacionCliente.imagenLogoCliente);
		}
		if(formALogo.toString() === "" && $estatusEntregable ==="edicion"){
			$("#estatusEntregable").val($estatusEntregable);
			$("#formALogoA").val($entregable.formALogo);
		}

		$("#formBParticipantesA").val(arrayParticipantes);

		$("#formCRazonSocialA").val($("#formCRazonSocial").val());
		$("#formCCursoA").val($("#formCCurso").val());
		$("#formCDuracionA").val($("#formCDuracion").val());
		$("#formCInstructorA").val($("#formCInstructor").val());
		$("#formCFechaInicioA").val($("#formCFechaInicio").val());
		$("#formCFechaFinalA").val($("#formCFechaFinal").val());
		$("#formCSedeA").val($("#formCSede").val());
		formCTextArea($entregable);
		
		$("#altaDocto").val(true);
		$("#idEntregableLogico").val($idEntregableLogico);
				
		
		if(asignacionItem.nombreFirmaInstructor !== "" ){
			if($("#entregableEdicion").valid()){
				$('#myModalProcess').modal();
			}
			$('#entregableEdicion').submit();
		}else{
			$('#btnGeneraDocto').hide();
			alert("No se puede generar documentación digital, el instructor asignado "+asignacionItem.instructorAsignacion+", no tiene capturada la firma digital")
			return false;
		}
		
	});


		
			
});  // Fin JQRY

function formCTextArea(entregable){
	if(entregable){
		if($('#formCComentariosGrupo').val() === "" || $('#formCComentariosGrupo').val() === null){
			console.log("formCComentariosGrupo");
			$('#formCComentariosGrupo').val(entregable.formCComentariosGrupo);
		}
		
		console.log($('#formCProcesoAprendizaje').val());
		if($('#formCProcesoAprendizaje').val() === ""){
			$('#formCProcesoAprendizaje').val(entregable.formCProcesoAprendizaje);
		}
		
		if($('#formCTeoria').val() === ""){
			$('#formCTeoria').val(entregable.formCTeoria);
		}
		
		if($('#formCPractica').val() === ""){
			$('#formCPractica').val(entregable.formCPractica);
		}
		
		if($('#formCRecomendaciones').val() === ""){
			$('#formCRecomendaciones').val(entregable.formCRecomendaciones);
		}
		
		if($('#formCNivelCumplimiento').val() === ""){
			$('#formCNivelCumplimiento').val(entregable.formCNivelCumplimiento);
		}
		
		if($('#formCContingencias').val() === ""){
			$('#formCContingencias').val(entregable.formCContingencias);
		}
		
		if($('#formCAvancesLogrados').val() === ""){
			$('#formCAvancesLogrados').val(entregable.formCAvancesLogrados);
		}
		
		if($('#formCObservaciones').val() === ""){
			$('#formCObservaciones').val(entregable.formCObservaciones);
		}
	}
	
}

function entregable2(element){
	valorEntregable = $(element).attr("id");
	for(var a in entregables){
		var entregable = entregables[a];
		if((entregable.IdEntregable*1) === (valorEntregable*1) ){
			$("#formARazonSocial").val(entregable.formARazonSocial);
			$("#formARFC").val(entregable.formARFC);
			$("#formACurso").val(entregable.formACurso);
			$("#formADuracion").val(entregable.formADuracion);
			$("#formAInstructor").val(entregable.formAInstructor);
			$("#formARepresentanteEmpresa").val(entregable.formARepresentanteEmpresa);
			$("#formARepresentanteTrabajador").val(entregable.formARepresentanteTrabajador);
			
			$('#formCRazonSocial').val(entregable.formCRazonSocial);
			$('#formCCurso').val(entregable.formCCurso);
			$('#formCDuracion').val(entregable.formCDuracion);
			$('#formCInstructor').val(entregable.formCInstructor);
			$('#formCSede').val(entregable.formCSede);
			$('#formCComentariosGrupo').val(entregable.formCComentariosGrupo);
			$('#formCProcesoAprendizaje').val(entregable.formCProcesoAprendizaje);
			$('#formCTeoria').val(entregable.formCTeoria);
			$('#formCPractica').val(entregable.formCPractica);
//			$('#formCEvidenciasFotograficas').val(entregable.formCEvidenciasFotograficas);
			$('#formCRecomendaciones').val(entregable.formCRecomendaciones);
			$('#formCNivelCumplimiento').val(entregable.formCNivelCumplimiento);
			$('#formCContingencias').val(entregable.formCContingencias);
			$('#formCAvancesLogrados').val(entregable.formCAvancesLogrados);
			$('#formCObservaciones').val(entregable.formCObservaciones);
//			$('#formCEvidenciaDocto').val(entregable.formCEvidenciaDocto);
		}				
	}
}


function duracion(str){
	var tmp = str.split(";");
	return tmp[4];
}
	

	

	

	function imagenCliente(archivosCampo){
	  var $idCliente = asignacionCliente.rfcCliente
//	  $idCliente = $(rfcCliente).val();                
	  var files = archivosCampo.files[0];
//	  console.log(files.size);
	  if((files.size /1024) < 1024){
		  var formALogo = $("#formALogo").val();
		  $("#formALogoA").val("");
		  $("#formALogoA").val(editaNombreImagen(formALogo));
		  enviaImagen(archivosCampo, $idCliente);
	  }
	}
	
	function imagenParticipante(archivosCampo){
		var $idCliente = asignacionCliente.rfcCliente
//		  $idCliente = $(rfcCliente).val();                
		  var files = archivosCampo.files[0];
		  if((files.size /1024) < 1024){
			  enviaImagenParticipante(archivosCampo, $idCliente);
		  }
		}
	
	function imagenEvidencias(archivosCampo){
		var $idCliente = asignacionCliente.rfcCliente
//		  $idCliente = $(rfcCliente).val();                
		  var files = archivosCampo.files;
		  enviaImagenEvidencia(files, $idCliente);
			
		  for (var i = 0; i < files.length; i++) {   
		      var file = files[i];
//		      enviaImagenEvidencia(file, $idCliente);
//		      sleep(1000);
		      
		  }
		}
	
	function archivoCliente(archivosCampo){
		var $idCliente = asignacionCliente.rfcCliente
//		  $idCliente = $(rfcCliente).val();
	      enviaFile(archivosCampo, $idCliente);
	      }

	
//	function archivoCliente(archivosCampo){
//		  var $idCliente = asignacionCliente.rfcCliente
//	    enviaFile(archivosCampo, $idCliente);
//	    var files = archivosCampo.files;
//	    for (var i = 0; i < files.length; i++) {           
//	        var file = files[i];
//	        }        
//	    }
	
	
	function sleep(milliseconds) {
		  const date = Date.now();
		  let currentDate = null;
		  do {
		    currentDate = Date.now();
		  } while (currentDate - date < milliseconds);
		}

		function editaNombreImagen(str){
			while(str.includes("C:\\fakepath\\") ){
				str = str.replace("C:\\fakepath\\", "")	
			}
			return str;
		}
		
		function promedio(){
			var TF = $('#participanteETF').val();
			var EP = $('#participanteEP').val();

			var promedio = ( TF*1 + EP*1 )/2 ;
			$('#participanteP').val(promedio);
			$('#checkAprovechamiento').bootstrapToggle('on');
			if(promedio < 8){
				$('#checkAprovechamiento').bootstrapToggle('off');
			}
		}

		function isNumber(n) { return /^-?[\d.]+(?:e-?\d+)?$/.test(n); } 

	    
	    
	    /*
	     * REST
	     */

	    
	    	function enviaImagen(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta();
	    			console.log("Comineza envio imagenLogo:"+idImagenForm);
	    			console.log(rfcCliente);
	    			
	    			var alerta="";
      			 var form = $('#entregableEdicion')[0]; //$('#formImagenLogoCliente').attr('files'),
  		        var data = new FormData(form);
  		        //console.log(data);
	    			  $.ajax({
	    				url: "imageUploadEntregable/"+rfcCliente+"/"+$idEntregableLogico,
	    			    type: "POST",
	    			    data: new FormData($("#entregableEdicion")[0]),
	    			    enctype: 'multipart/form-data',
	    			    processData: false,
	    			    contentType: false,
	    			    cache: false,
	    			    success: 	function(data){
	    			    	if(data.codigo===0){
	    			    		var alerta="";
	    			    		if(data.codigo===0){
	    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
	    			  			 $(alerta).insertAfter($('.alertaL'));
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  	    		 $(alerta).insertAfter($('.alerta'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    					  $(alerta).insertAfter($('.alertaL'));
	    			  	//console.log("envio error");
	    			    }
	    			  });
	    	}
	    	
	    	function enviaImagenParticipante(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta();
	    			console.log("Comineza envio imagenParticipante:"+idImagenForm);
//	    			console.log(rfcCliente);
	    			
	    			var alerta="";
      			 var form = $('#formBParticipante')[0];
  		        var data = new FormData(form);
  		        //console.log(data);
	    			  $.ajax({
	    				url: "imageUploadParticipante/"+rfcCliente+"/"+$idEntregableLogico,
	    			    type: "POST",
	    			    data: new FormData($("#formBParticipante")[0]),
	    			    enctype: 'multipart/form-data',
	    			    processData: false,
	    			    contentType: false,
	    			    cache: false,
	    			    success: 	function(data){
	    			    	if(data.codigo===0){
	    			    		var alerta="";
	    			    		if(data.codigo===0){
	    			  			alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
	    			  			$(alerta).insertAfter($('.alertaP'));
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  	    		$(alerta).insertAfter($('.alertaP'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    			    	$(alerta).insertAfter($('.alertaP'));
	    			  	//console.log("envio error");
	    			    }
	    			  });
	    	}
	    	
	    	function enviaImagenEvidencia(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta();
	    			console.log("Comienza envio imagenEvidencia:");
	    			 var form_data = new FormData();

	    			   // Read selected files
	    			   var totalfiles = document.getElementById('formCEvidenciasFotograficas').files.length;
	    			   for (var index = 0; index < totalfiles; index++) {
	    			      form_data.append("entregableEdicion[]", document.getElementById('formCEvidenciasFotograficas').files[index]);
	    			   }

	    			  $.ajax({
	    				url: "imageUploadEvidencia/"+rfcCliente+"/"+$idEntregableLogico,
	    			    type: "POST",
	    			    data: form_data, //new FormData($("#entregableEdicion")[0]),
	    			    enctype: 'multipart/form-data',
	    			    processData: false,
	    			    contentType: false,
	    			    cache: false,
	    			    success: 	function(data){
	    			    	if(data.codigo===0){
	    			    		var alerta="";
	    			    		if(data.codigo===0){
	    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
	    			  			  $(alerta).insertAfter($('.alertaE'));
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  				  $(alerta).insertAfter($('.alertaE'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    					  $(alerta).insertAfter($('.alertaE'));
	    			  	//console.log("envio error");
	    			    }
	    			  });
	    	}

	    	
	    	function enviaFile(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta();

	    			//console.log("Comineza envio idCliente:"+rfcCliente);
	    			var alerta="";
	    			 var form = $('#entregableEdicion')[0]; //$('#formImagenLogoCliente').attr('files'),
			        var data = new FormData(form);
			        //console.log(data);
	    			  $.ajax({
	    				url: "fileUploadEntregables/"+rfcCliente+"/"+$idEntregableLogico,
	    			    type: "POST",
	    			    data: data,
	    			    enctype: 'multipart/form-data',
	    			    processData: false,
	    			    contentType: false,
	    			    cache: false,
	    			    success: 	function(data){
	    			    	if(data.codigo===0){
	    			    		var alerta="";
	    			    		if(data.codigo===0){
	    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
	    			  			$(alerta).insertAfter($('.alertaD'));
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  	    		$(alerta).insertAfter($('.alertaD'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    			    	$(alerta).insertAfter($('.alertaD'));
	    			  	//console.log("envio error");
	    			    }
	    			  });

	    	}

	    	
	    	
	    	
	    	function limpiaAlerta(){
//				$('.alertaL').empty();
//				$('.alertaP').empty();
//				$('.alertaE').empty();
//				$('.alertaD').empty();
			}