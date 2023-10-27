/**
 * Archivo de control JS para Modulo Calendario 
 */
var datoEconomicoSelec;


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
	var check = true;
	$('#btnFiltroCursos').click(function(){
		$('#todosCursos').prop( "checked", check );
		$('.checkboxCursos').prop( "checked", check );
	});

	$( '#todosCursos' ).on( 'click', function() {
		check = $('#todosCursos').prop( "checked");
		$('.checkboxCursos').prop( "checked", check );
		
	});

	//Clientes
	//check box filtro Clientes
	var check = true;
	$('#btnFiltroClientes').click(function(){
		$('#todosClientes').prop( "checked", check );
		$('.checkboxClientes').prop( "checked", check );
	});

	$( '#todosClientes' ).on( 'click', function() {
		check = $('#todosClientes').prop( "checked");
		$('.checkboxClientes').prop( "checked", check );
		
	});

		
	
				
}); // Fin Jquery

//FILTRO FECHAS
function asignaData(dataTable){
	$('#instructorAsignacionesTable').bootstrapTable('load', dataTable);
	$('#instructorAsignacionesTable').bootstrapTable({data : dataTable});
	$.asignaciones.length = 0;
	$.asignaciones = dataTable;
}


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
	
	var fechaInicial  = new Date(asignaFecha1);
	var fechaFinal = new Date(asignaFecha2);
	console.log(fechaInicial);console.log(fechaFinal);
	for(var a in $.asignaciones ){
	  var asignacion = $.asignaciones[a];
	  var fechaRegistro = new Date(asignacion.fechaAsignacion)
	  if((fechaInicial <= fechaRegistro)  && (fechaRegistro <= fechaFinal)){
		  dataFechas.push(asignacion);
		}
	}
    
	asignaData(dataFechas);
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
	console.log(filtroInstructores);
	
	for(e in $.asignaciones){
		var asignacionA  = $.asignaciones[e];
		for(a in filtroInstructores){
			var instructorFiltro = filtroInstructores[a];
			if((asignacionA.idInstructorAsignacion * 1) === (instructorFiltro * 1)){
				dataInstructores.push(asignacionA);
			}
		}
	}
	asignaData(dataInstructores);
	console.log(filtroInstructores.length);
	console.log(dataInstructores.length);
}
//$('#btnFiltroInstructorActualizar').click(function(){});

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
	console.log(filtroZona);
	
	for(e in $.asignaciones){
		var asignacion  = $.asignaciones[e];
		for(a in filtroZona){
			var zona = filtroZona[a];
			if((asignacion.idRegionAsignacion * 1) === (zona * 1)){
				dataZona.push(asignacion);
			}
		}
	}
	asignaData(dataZona);
	console.log(filtroZona.length);
	console.log(dataZona.length);
}
//FIN FILTRO ZONA
//---------------------------------------------------------------------------------
//FILTRO CURSO

//FIN FILTRO CURSO
//---------------------------------------------------------------------------------
//FILTRO CLIENTE

//FIN FILTRO CLIENTE
//---------------------------------------------------------------------------------
//FILTRO ESTATUS

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