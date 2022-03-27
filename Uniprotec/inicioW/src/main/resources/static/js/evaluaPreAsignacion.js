	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCURSO
	 */
	var tipoCurso = true;
	var tipoCursoVal = "";
	var arrayInstructores = new Array();
	var instructoresDiaSelect = new Array();
	var instructoresDmin1 = new Array();
	var instructoresDmas1 = new Array();
	var instructoresDiaAyer = new Array();
	var instructoresDiaMan = new Array();
	$.asignaCurso;
	$.idClienteAsignacion;
	$.asignaFecha;
	$.asignaFecha2;
	const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":true,"28":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false}	
	
	function evaluaPreAsignacion(
				fechaAsignacion,
				idClienteAsignacion,
				clienteAsignacion,
				idCursoAsignacion,
				cursoAsignacion,
				tipoCursoAsignacion,
				idRegionAsignacion,
				idInstructorAsignacion,
				instructorAsignacion){
		
		$.asignaCurso = idCursoAsignacion;
		$.idClienteAsignacion = idClienteAsignacion;
		$.asignaFecha=fechaAsignacion;
		$.asignaFecha2=fechaAsignacion;
		
//		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
//		$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
		
//		console.log($.asignaCurso);
//		console.log($.idClienteAsignacion);
//		console.log($.asignaFecha);
//		console.log($.asignaFecha2);
//
//		console.log(idInstructorAsignacion);
		
		tipoCurso = false;
		if(tipoCursoAsignacion === "PRESENCIAL"){
			tipoCurso = true;
		}
		checkTipoCurso(tipoCurso);
		
		var instructores = validaCurso();
		
		var jsonValida = {
				codigo:99,
				mensaje:"Instructor NO Disponible"
		}
//		console.log(instructores);
		for(a in instructores){
			var instructor = instructores[a];
//			console.log(instructor);
			if(idInstructorAsignacion*1 == instructor.idInstructor*1){
//				console.log(instructor);
				var jsonValida = {
						codigo:0,
						mensaje:"Instructor Disponible"
				}
				break;
			}
		}
//		console.log(jsonValida);
		return jsonValida;
	}
	
	function checkTipoCurso(tipoCurso){
		
		if(tipoCurso){
			$('#tipoCurso').html("ON LINE");
			$('#tipoCurso').removeClass("btn-alternate");
			$('#tipoCurso').addClass("btn-warning");
			tipoCurso = false;
			tipoCursoVal = "ON LINE";
		}else{
			$('#tipoCurso').html("PRESENCIAL");
			$('#tipoCurso').removeClass("btn-warning");
			$('#tipoCurso').addClass("btn-alternate");
			tipoCurso = true;
			tipoCursoVal = "PRESENCIAL";
		}
		$('#asignaCurso').attr("disabled", false);
//		validaCurso();
	}
	
	
	var asignacionAsignaciones = new Array();
	 var asignacion;
	 
	function validaCurso(){
		/*
		 * Validacion ValorCampo
		 */
		 arrayInstructores.length = 0; 
		 instructoresDiaSelect.length = 0;
		 instructoresDmin1.length = 0;
		 instructoresDmas1.length = 0;
		 instructoresDiaAyer.length = 0;
		 instructoresDiaMan.length = 0;
		 $.instructores;
//		$.asignaCurso = $('#asignaCurso').val();
//		console.log("asignaCurso:"+ $.asignaCurso);
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		
		if($.asignaCurso === null || $.asignaCurso === ""){
   			alerta="<div class='alert alert-danger' id='alertaCurso' role='alert'>Seleccione Curso</div>";
			alertaFade(alerta);
			$('#btnAsignaCurso').attr("disabled", true);
   		}else{
   			$('#btnAsignaCurso').attr("disabled", false);
   			$('#asignaInstructor').empty();
			$('#asignaInstructor').append('<option value="" selected  >Selecciona Instructor</option>');
   		}
		
		 
		 for(a in asignacionAsignacionesTotal){
			 asignacion = asignacionAsignacionesTotal[a];
			 if(asignacion.statusAsignacion !== "Evento Cancelado"){
				 asignacionAsignaciones.push(asignacion);
			 }
		 }
//		 console.log(asignacionAsignaciones);
		 
		 
		/* 
		 * No VALIDAR Esquemas de movilidad para Perfil Operacion y Direccion
		 */
		 var valorCurso = $.asignaCurso * 1;
		if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
				for (i in asignacionInstructoresOperacion){				
					var arrayCursosInstructor = asignacionInstructoresOperacion[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
					instructor = asignacionInstructoresOperacion[i];
					for( e in arrayCursosInstructor){
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace('[','');
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(']','');
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
						if(arrayCursosInstructor[e] === valorCurso){
							arrayInstructores.push(asignacionInstructores[i])
							$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
						}
					}
				}
		}else{
			/*
			 * Filtra Instructores por Curso
			 */		
//			console.log(asignacionInstructores);
			for (i in asignacionInstructores){				
				var arrayCursosInstructor = asignacionInstructores[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
				for( e in arrayCursosInstructor){
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace('[','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(']','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
					if(arrayCursosInstructor[e] === valorCurso){
						arrayInstructores.push(asignacionInstructores[i])
					}
				}
			}
//			console.log(arrayInstructores);
			/*
			 * Valida dias de Ausencia
			 */
			var instructoresDiaAusencia = new Array();;
			for(a in arrayInstructores){
				var instructorDiaAusencia = arrayInstructores[a]; 
				if(validaDiaAusencia(instructorDiaAusencia)){
					instructoresDiaAusencia.push(instructorDiaAusencia);
				}
			}
//			console.log(arrayInstructores);
			arrayInstructores = instructoresDiaAusencia ;
			
			var regionInstructor;
			var regionCliente;
			var instructor;
			var idInstructor;
	
			
			if(tipoCurso){

				/*
				 * Obtener ZonaCliente 
				 */
				if($.idClienteAsignacion === null || $.idClienteAsignacion === ""){
		   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
					alertaFade(alerta);
					$('#btnAsignaCurso').attr("disabled", true);
				}else{
					var jsonCliente;
					for (o in asignacionClientes){
						var idCliente = asignacionClientes[o].idCliente;
						var cliente = asignacionClientes[o];
						if((idCliente * 1) === ($.idClienteAsignacion * 1)){
							jsonCliente = cliente;
						}
					}
//					console.log(jsonCliente);
					regionCliente = jsonCliente.regionCliente.idRegion;
				}

				/*
				 * Consultar D-1 y D+1 Instructores
				 */
				
				/*
				 * validar dia seleccion
				 */
				for(u in instructoresDiaAusencia){
					 instructor = instructoresDiaAusencia[u];
					 idInstructor = instructor.idInstructor;
					 nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor)){
						instructoresDiaSelect.push(instructor);
					}
				}
//				console.log(instructoresDiaSelect);

				/*
				 * libre ayer
				 */
				for(aa in instructoresDiaSelect){
					instructor = instructoresDiaSelect[aa];
					idInstructor = instructor.idInstructor;
					nombreInstructor = instructor.nombreInstructor;
					if(!validaDiaAyer(idInstructor)){
						idRegionOrigen = getRegionOrigen(idInstructor);
						if(validaZonaBase(idRegionOrigen, regionCliente)){
							instructoresDiaAyer.push(instructor);
						}

					}else{
						if(validaDmin1(regionCliente, idInstructor)){
							instructoresDmin1.push(instructor);
							instructoresDiaAyer.push(instructor);
						}
					}
				}
//				console.log(instructoresDiaAyer);
				/*
				 * libre mañana 
				 */
				for(ae in instructoresDiaAyer){
					instructor = instructoresDiaAyer[ae];
					 idInstructor = instructor.idInstructor
					 nombreInstructor = instructor.nombreInstructor
					if(!validaDiaMan(idInstructor)){					
						instructoresDiaMan.push(instructor);
//						idRegionOrigen = getRegionOrigen(idInstructor);
//						if(validaZonaBase(idRegionOrigen, regionCliente)){
//							instructoresDiaMan.push(instructor);
//						}
					}else{
						if(validaDmas1(regionCliente, idInstructor)){
							instructoresDiaMan.push(instructor);
						}
					}
				}
//				console.log(instructoresDiaMan);
				for(ai in instructoresDiaMan){
					instructor = instructoresDiaMan[ai];
					$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
				}
				$.instructores = instructoresDiaMan;
				
				
			}else{
				//validar dia seleccion
				for(i in arrayInstructores){
					 instructor = arrayInstructores[i];
					 idInstructor = instructor.idInstructor
					 nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor)){
//						$('#asignaInstructor').append('<option value="'+idInstructor+'">'+nombreInstructor+'</option>');
						$.instructores.push(instructor);
					}
				}
			}
		}	
//		$.asignaCursoTexto = $("#asignaCurso option:selected").text();
//		procesoCurso="<li>Prospecto Curso : <b>"+ $.asignaCursoTexto +" : <i><u>"+tipoCursoVal+"</u></i></b></li>";
		$.asignaTipoCurso = tipoCursoVal;
		return $.instructores;
	}  // fin metodo validaCurso
	
	
	/*
	 * privates
	 */
function validaDiaAusencia(instructor){
		
		var fechaDisponible = true;
//		var fechaAusente;
		var fechaSelect = new Date($.asignaFecha2);
		var fechasAusente = new Array();
//		console.log(fechaSelect);
		if(instructor.listFechas){
			fechasAusente = instructor.listFechas.toString().split(";");
//			fechasAusente = stringToList(instructor.listFechas);
			for(e in fechasAusente){
				var fechaAusente = new Date(fechasAusente[e]);
				if(fechaAusente.toString() === fechaSelect.toString()){
					fechaDisponible = false;
					break;
				}
			}			
		}
		return fechaDisponible;
	}
	
	
	function stringToList(cadena){
		return cadena.split(";");
	}
	
	function stringToList(cadena, separador){
		return cadena.split(separador);
	}
	
	
	function validaDiaAyer(idInstructor){
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionOrigen;
		
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dayer = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dayer.setDate(dayer.getDate() - 1);
		var dia = dayer.getDate();
		var mes = (dayer.getMonth()+1);
		var anio =dayer.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dayerTexto = mes +"/"+ dia +"/"+ anio ;
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
			if(asignacionFecha === dayerTexto && (asignacionInstructor === idInstructor)){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					return true;
				}
				
			}
		}
		return false;
	}
	
	function validaDiaMan(idInstructor){
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dman = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dman.setDate(dman.getDate() + 1);
		var dia = dman.getDate();
		var mes = (dman.getMonth()+1);
		var anio =dman.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dManTexto = mes +"/"+ dia +"/"+ anio ;
		
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
//			//console.log(dManTexto+":"+asignacionFecha);
			if((asignacionFecha.toString() === dManTexto.toString()) && (asignacionInstructor.toString() === idInstructor.toString())){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	function validaDiaSelect(idInstructor){
		var fechaDisponible = true;
		var asignacion;
		var asigna;
		var dia;
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asigna = asignacion.fechaAsignacion.toString().split("/");
			dia = asigna[1]+"/"+asigna[0]+"/"+asigna[2];
			if((dia === $.asignaFecha.toString()) && (asignacion.idInstructorAsignacion.toString() === idInstructor.toString())){
				fechaDisponible = false;
				break;
			}
		}
		return fechaDisponible;
	}
	
	function validaDmin1(regionCliente, idInstructor){
//		var flagDiaAnterior;
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		var asignacionesDmin1 = new Array();
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dmin1 = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dmin1.setDate(dmin1.getDate() - 1);
		var dia = dmin1.getDate();
		var mes = (dmin1.getMonth()+1);
		var anio =dmin1.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dmin1Texto = mes +"/"+ dia +"/"+ anio ;
//		//console.log(dmin1Texto);
//		//console.log(asignacionAsignaciones);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
//			//console.log(asignacion.idAsignacion);
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
			if(asignacionFecha === dmin1Texto && (asignacionInstructor === idInstructor)){
				idRegionAsignado = getRegionAsignado(asignacion.idClienteAsignacion);
//				//console.log(idRegionAsignado);
				return validaZonaBase(regionCliente, idRegionAsignado);
			}
		}
		return true;
	}
	
	function validaDmas1(regionCliente, idInstructor){
//		//console.log("regionCliente:"+regionCliente);
//		//console.log("idInstructor:"+idInstructor);
//		var flagDiaAnterior;
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		var asignacionesDmin1 = new Array();
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dmas1 = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dmas1.setDate(dmas1.getDate() + 1);
		var dia = dmas1.getDate();
		var mes = (dmas1.getMonth()+1);
		var anio =dmas1.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dmas1Texto = mes +"/"+ dia +"/"+ anio ;
//		//console.log("dmas1Texto:"+dmas1Texto);
//		//console.log(asignacionAsignaciones);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
//			//console.log(asignacionFecha);
			if((asignacionFecha === dmas1Texto) && (asignacionInstructor === idInstructor)){
				idRegionAsignado = getRegionAsignado(asignacion.idClienteAsignacion);
//				//console.log(idRegionAsignado);
				return validaZonaBase(regionCliente, idRegionAsignado);
			}
		}
		return true;
	}
	
	function validaZonaBase(regionCliente, regionInstructor){
		var claveZB = regionCliente.toString() + regionInstructor.toString();
//		//console.log(claveZB);
//		//console.log(zonabase[claveZB]);
		return zonabase[claveZB];
	}
	
	function getRegionAsignado(idClienteAsignacion){
		for(i in asignacionClientes){
			if(asignacionClientes[i].idCliente === idClienteAsignacion)
				return asignacionClientes[i].regionCliente.idRegion;
		}
		
	}
	
	function getRegionOrigen(idInstructor){
		var instructor;
		for(i in instructoresDiaSelect){
			instructor = instructoresDiaSelect[i];
			if(idInstructor === instructor.idInstructor){
				return instructor.regionInstructor.idRegion.toString();
			}
		}
			
	}
	