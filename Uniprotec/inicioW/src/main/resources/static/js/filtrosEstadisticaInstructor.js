/**
 * Archivo de control JS para Modulo Calendario 
 */


$(document).ready(function() {
	
	$.asignaciones = new Array();
	const identificadorUsuario = idUsuario;
	var filtroVendedores = new Array();
	var filtroVendedoresVendedor = new Array();
	var asignacionesFiltroV = new Array();
	
	$.asignaciones = asignacionesHistorico;

	$('#instructorAsignacionesTable').bootstrapTable({data : $.asignaciones})

	// FECHA
	$('#btnFiltroFechasActualizar').click(function (){
		 $('#fechaFinal').val("");
		 $('#fechaInicial').val("");
	});
	
	//Instructores
	//check box filtro Instructores
	var check = true;
	$('#btnFiltroInstructor').click(function(){
		$('#todosInstructores').prop( "checked", check );
		$('.checkboxFiltro').prop( "checked", check );
	});

	$( '#todosInstructores' ).on( 'click', function() {
		check = $('#todosInstructores').prop( "checked");
		$('.checkboxFiltro').prop( "checked", check );
		
	});

	//Vendedores
	//check box filtro Vendedores
	var check = true;
	$('#btnFiltroVendedor').click(function(){
		$('#todosVendedores').prop( "checked", check );
		$('.checkboxFiltroV').prop( "checked", check );
	});

	$( '#todosVendedores' ).on( 'click', function() {
		check = $('#todosVendedores').prop( "checked");
		$('.checkboxFiltroV').prop( "checked", check );
		
	});

	//zona
	//check box filtro Zona
	var check = true;
	$('#btnFiltroZona').click(function(){
		$('#todosZona').prop( "checked", check );
		$('.checkboxZona').prop( "checked", check );
	});

	$( '#todosZona' ).on( 'click', function() {
		check = $('#todosZona').prop( "checked");
		$('.checkboxZona').prop( "checked", check );
		
	});

	//Cursos
	//check box filtro Cursos
	var checkFalse = false;
	$('#btnFiltroCursos').click(function(){
		$('#todosCursos').prop( "checked", checkFalse );
		$('.checkboxCursos').prop( "checked", checkFalse );
	});

	$( '#todosCursos' ).on( 'click', function() {
		check = $('#todosCursos').prop( "checked");
		$('.checkboxCursos').prop( "checked", checkFalse );
		
	});

	//Clientes
	//check box filtro Clientes
	var check = false;
	$('#btnFiltroClientes').click(function(){
		$('#todosClientes').prop( "checked", checkFalse );
		$('.checkboxClientes').prop( "checked", checkFalse );
	});

	$( '#todosClientes' ).on( 'click', function() {
		check = $('#todosClientes').prop( "checked");
		$('.checkboxClientes').prop( "checked", checkFalse );
		
	});

	//Estatus
	//check box filtro Estatus
	var check = true;
	$('#btnFiltroEstatus').click(function(){
		$('#todosEstatus').prop( "checked", check );
		$('.checkboxEstatus').prop( "checked", check );
	});

	$( '#todosEstatus' ).on( 'click', function() {
		check = $('#todosEstatus').prop( "checked");
		$('.checkboxEstatus').prop( "checked", check );
		
	});
	

		
	
				
}); // Fin Jquery

//limpiar filtros
$('#btnFiltroLimpiar').click(function(){
	$('#divFiltroActivo').empty();
	asignaData($.asignacionesOriginal);
});



function asignaData(dataTable){
	$('#instructorAsignacionesTable').bootstrapTable('load', dataTable);
	$('#instructorAsignacionesTable').bootstrapTable({data : dataTable});
	$.asignaciones = dataTable;
	
	var arrayAsignaciones = new Array();
//  	var lstArrayAsignaciones = new Array();
	for(var a in $.asignaciones){
		arrayAsignaciones.push(JSON.stringify($.asignaciones[a]));
	}
    $('#strAsignacionesDescargas').val(arrayAsignaciones);
	
}

//FILTRO FECHAS
function formatoFechaInicial(){
	var fechaFinal = $('#fechaFinal').val();
	var fechaInicial = $('#fechaInicial').val();
	if(isEmpty(fechaInicial) && isEmpty(fechaFinal)){
		formatoFechaFinal();
	}
}

function formatoFechaFinal(){
	if(isEmpty(fechaInicial) && isEmpty(fechaFinal)){
		$('#btnFiltroFechasActualizar').attr("disabled", false);
	}	
}


function actualizaFechas(){
	var dataFechas = new Array();
	var fechaFinal = $('#fechaFinal').val();
	var fechaInicial = $('#fechaInicial').val();
	
	$('#labelInicial').text(fechaInicial);
	var elementoPicker1 = $datepicker4.pickadate('picker');	
	var asignaFecha1 = elementoPicker1.get('select', 'mm/dd/yyyy');
	$('#fechaInicialFormat').val(asignaFecha1);
	
	$('#labelFechaFinal').text(fechaFinal);
	var elementoPicker2 = $datepicker5.pickadate('picker');	
	var asignaFecha2 = elementoPicker2.get('select', 'mm/dd/yyyy');
	$('#fechaFinalFormat').val(asignaFecha2);
	
	var fechaInicialA  = new Date(asignaFecha1);
	var fechaFinalA = new Date(asignaFecha2);
	console.log(fechaInicialA);console.log(fechaFinalA);
	for(var a in $.asignaciones ){
	  var asignacion = $.asignaciones[a];
	  var fechaRegistro = new Date(asignacion.fechaAsignacion)
	  if((fechaInicialA <= fechaRegistro)  && (fechaRegistro <= fechaFinalA)){
		  dataFechas.push(asignacion);
		}
	}
    
	asignaData(dataFechas);
	
	var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Fechas : </b> '+fechaInicial+ '- '+ fechaFinal+'</u></div>';
	$('#divFiltroActivo').append(notificacion);
}


//FIN FILTRO FECHAS
//---------------------------------------------------------------------------------
//FILTRO INSTRUCTOR

function actualizaInstructor(){
	var filtroInstructores = new Array();
	var dataInstructores = new Array();

	$('.checkboxFiltro:checked').each(function(){
		if($(this).attr('checked',true)){
			idInstructor = $(this).attr('id')
			filtroInstructores.push(idInstructor);
		}
	});
//	console.log(filtroInstructores);
	var nombreInstructor = "";
	for(e in $.asignaciones){
		var asignacionA  = $.asignaciones[e];
		for(a in filtroInstructores){
			var instructorFiltro = filtroInstructores[a];
			if((asignacionA.idInstructorAsignacion * 1) === (instructorFiltro * 1)){
				dataInstructores.push(asignacionA);
				nombreInstructor = asignacionA.instructorAsignacion;
			}
		}
	}
	var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Instructor : </b> '+nombreInstructor +'</u></div>';
	$('#divFiltroActivo').append(notificacion);
	asignaData(dataInstructores);
	

}


//FIN FILTRO INSTRUCTOR
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//FILTRO VENDEDOR

function actualizaVendedor(){
	var filtroVendedores = new Array();
	var dataVendedores = new Array();

	$('.checkboxFiltroV:checked').each(function(){
		if($(this).attr('checked',true)){
			idVendedor = $(this).attr('id')
			filtroVendedores.push(idVendedor);
		}
	});
	
	var filtroVendedor = new Array();
	for(e in vendedores){
		var vendedor  = vendedores[e];
		for(a in filtroVendedores){
			var filtrovendedor  = filtroVendedores[a];
			if(vendedor.idVendedor*1 == filtrovendedor*1){
				filtroVendedor.push(vendedor);
			}
		}
	}
	
	var nombreVendedor = "";
	for(e in $.asignaciones){
		var asignacionA  = $.asignaciones[e];
		for(e in filtroVendedor){
			var vendedor = filtroVendedor[e];
			if((asignacionA.userCreateAsignacion * 1) === (vendedor.usuarioVendedor.idUsuario * 1)){
				dataVendedores.push(asignacionA);
				nombreVendedor = vendedor.nombreVendedor;
			}
		}
	}
	var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Vendedor : </b> '+nombreVendedor +'</u></div>';
	$('#divFiltroActivo').append(notificacion);
	asignaData(dataVendedores);
	

}


//FIN FILTRO INSTRUCTOR
//---------------------------------------------------------------------------------

//FILTRO ZONA
function actualizaZona(){
	var filtroZona = new Array();
	var dataZona = new Array();

	$('.checkboxZona:checked').each(function(){
		if($(this).attr('checked',true)){
			idZona = $(this).attr('id')
			filtroZona.push(idZona);
		}
	});
//	console.log(filtroZona);
	
	for(e in $.asignaciones){
		var asignacion  = $.asignaciones[e];
		for(a in filtroZona){
			var zona = filtroZona[a];
			if((asignacion.idRegionAsignacion * 1) === (zona * 1)){
				dataZona.push(asignacion);
				nombreZona = asignacion.nombreRegionAsignacion;
			}
		}
	}
	asignaData(dataZona);
	var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Nombre Región : </b> '+nombreZona +'</u></div>';
	$('#divFiltroActivo').append(notificacion);
//	console.log(filtroZona.length);
//	console.log(dataZona.length);
}
//FIN FILTRO ZONA
//---------------------------------------------------------------------------------
//FILTRO CURSO
 
	 function filtroCurso(element){
	 var tmp = $(element).val();
	 var arrayCursos = new Array();
	 $('#sectionFiltroCurso').empty();
//	 console.log(tmp);
	 for(a in cursos){
		 var curso = cursos[a];
		 if(curso.nombreCurso.includes(tmp)){
			 arrayCursos.push(curso);
		 }
	 }
//	 console.log(arrayCursos);
	 for(e in arrayCursos){
		 var registroCurso = arrayCursos[e];
		 var registro = '<div class="input-group mb-1"><div class="input-group-prepend"><div class="input-group-text"><input type="checkbox" class="checkboxCurso"  id='+registroCurso.idCurso +' ></div></div><span style="padding-left: 10px;">'+registroCurso.nombreCurso +'</span></div>'
		 $('#sectionFiltroCurso').append(registro);
	 }
 }
	 
	 function actualizaCurso(){
		 var filtroCurso = new Array();
			var dataCurso = new Array();

			$('.checkboxCurso:checked').each(function(){
				if($(this).attr('checked',true)){
					idCurso = $(this).attr('id')
					filtroCurso.push(idCurso);
				}
			});
//			console.log(filtroCurso);
			var nombreCurso;
			for(e in $.asignaciones){
				var asignacion  = $.asignaciones[e];
				for(a in filtroCurso){
					var zona = filtroCurso[a];
					if((asignacion.idCursoAsignacion * 1) === (zona * 1)){
						dataCurso.push(asignacion);
						nombreCurso = asignacion.cursoAsignacion;
					}
				}
			}
			asignaData(dataCurso);
			var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Nombre Curso : </b> '+nombreCurso +'</u></div>';
			$('#divFiltroActivo').append(notificacion);

	 }
	 
//FIN FILTRO CURSO
//---------------------------------------------------------------------------------
//FILTRO CLIENTE
	 function filtroCliente(element){
		 var tmp = $(element).val();
		 var arrayClientes = new Array();
		 $('#sectionFiltroCliente').empty();
//		 console.log(tmp);
		 for(a in clientes){
			 var cliente = clientes[a];
			 if(cliente.nombreCortoCliente.includes(tmp)){
				 arrayClientes.push(cliente);
			 }
		 }
//		 console.log(arrayClientes);
		 for(e in arrayClientes){
			 var registroCliente = arrayClientes[e];
			 var registro = '<div class="input-group mb-1"><div class="input-group-prepend"><div class="input-group-text"><input type="checkbox" class="checkboxCliente"  id='+registroCliente.idCliente +' ></div></div><span style="padding-left: 10px;">'+registroCliente.nombreCortoCliente +'</span></div>'
			 $('#sectionFiltroCliente').append(registro);
		 }
	 }
		 
		 function actualizaCliente(){
			 var filtroCliente = new Array();
				var dataCliente = new Array();

				$('.checkboxCliente:checked').each(function(){
					if($(this).attr('checked',true)){
						idCliente = $(this).attr('id')
						filtroCliente.push(idCliente);
					}
				});
//				console.log(filtroCliente);
				var nombreCliente;
				for(e in $.asignaciones){
					var asignacion  = $.asignaciones[e];
					for(a in filtroCliente){
						var zona = filtroCliente[a];
						if((asignacion.idClienteAsignacion * 1) === (zona * 1)){
							dataCliente.push(asignacion);
							nombreCliente = asignacion.clienteAsignacion;
						}
					}
				}
				asignaData(dataCliente);
				var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Nombre Cliente : </b> '+nombreCliente +'</u></div>';
				$('#divFiltroActivo').append(notificacion);

		 }

//FIN FILTRO CLIENTE
//---------------------------------------------------------------------------------
//FILTRO ESTATUS
		 function actualizaEstatus(){
			 var filtroEstatus = new Array();
				var dataEstatus = new Array();

				$('.checkboxEstatus:checked').each(function(){
					if($(this).attr('checked',true)){
						idEstatus = $(this).attr('id')
						filtroEstatus.push(idEstatus);
					}
				});
//				console.log(filtroEstatus);
				var nombreEstatus;
				for(e in $.asignaciones){
					var asignacion  = $.asignaciones[e];
					for(a in filtroEstatus){
						var estatus = filtroEstatus[a];
						if(asignacion.statusAsignacion === estatus){
							dataEstatus.push(asignacion);
							nombreEstatus = asignacion.statusAsignacion;
						}
					}
				}
				asignaData(dataEstatus);
				var notificacion = '<div class="alert alert-success" role="alert" ><u><b>Nombre Estatus : </b> '+nombreEstatus +'</u></div>';
				$('#divFiltroActivo').append(notificacion);

		 }

//FIN FILTRO ESTATUS
//---------------------------------------------------------------------------------




function isEmpty(str) {
    return (str || 0 !== str.length);
}






























	

//	function hoy() {
//		var d = new Date();
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		if (dia < 10)
//			dia = "0" + dia.toString();
//		if (mes < 10)
//			mes = "0" + mes.toString();
//		var today = anio + '-' + mes + '-' + dia;
////		//console.log(today);
//		return today;
//	}
//	
//	function hoyCambioEstatus() {
//		var d = new Date();
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		var today = mes + '/' + dia + '/' + anio;
//		return today;
//	}
//	
//	function convierteMX(str){
//		var tmp = str.split("/");
//		return tmp[1]+"/"+tmp[0]+"/"+tmp[2];
//	}
//
//			
//	
//	function fechaPlus(dia){
//		var fecha = new Date();
//		fecha.setDate(fecha.getDate() + dia);		
//		return fecha;
//	}
//	
//			
//	function getFecha(fechaValor){
////		fechaValor = fechaValor.split('/')
//		var d = new Date(fechaValor);
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		if (dia < 10)
//			dia = "0" + dia.toString();
//		if (mes < 10)
//			mes = "0" + mes.toString();
//		var fecha = anio + '-' + mes + '-' + dia+ 'T';
////		//console.log(fecha);
//		return fecha;
//	}
//	
//	
//	
//		
//	function cambiaFormatoFecha(fecha){
//		fecha = fecha.split("/");
//		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
//	}
//	function cambiaFormatoFechaDisponible(fecha){
//		fecha = fecha.split("-");
//		return fecha[1]+"/"+fecha[2].substring(0,2)+"/"+fecha[0];
//	}
//	
	
	
	
	
	
	

	
	// fin de documento