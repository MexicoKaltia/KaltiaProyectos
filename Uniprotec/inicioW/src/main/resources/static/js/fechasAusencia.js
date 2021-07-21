/*
 *  JS Verifica y Edita las fechas de Ausencia Instructores
 */
var elementoPicker = $datepicker.pickadate('picker');
var elementoPicker2 = $datepicker2.pickadate('picker');
var fechas="<div class='alert alert-warning alert-dismissible' id='listFechasAct' role='alert'>Nuevas Fechas de Ausencia:<ul id='listaFechas'></ul></div>";
var arrayFechas = new Array();

function fechasAusencia() {
	
	var inicioAusencia = elementoPicker.get('select', 'mm/dd/yyyy');
	//		  console.log(inicioAusencia);
	elementoPicker2.set('select', new Date(inicioAusencia));
	elementoPicker2.set('min', new Date(inicioAusencia));

	if (inicioAusencia === null || inicioAusencia === "") {
		$('#btnFechasAusencia').attr("disabled", true);
	} else {
		$('#btnFechasAusencia').attr("disabled", false);
		var strList = $('#listFechas').val().toString();
		
		if(isEmpty(strList) ){
			arrayFechas = stringToList(strList);
		}
	}
}

function isEmpty(str) {
    return (str || 0 !== str.length);
}

function sumaFechas() {
	var inicioAusencia = elementoPicker.get('select', 'mm/dd/yyyy');
	var finAusencia = elementoPicker2.get('select', 'mm/dd/yyyy');	
	var inicioAusenciaDate = new Date(inicioAusencia); 
	var finAusenciaDate = new Date(finAusencia);
	var inicioAusenciaMasUno = inicioAusenciaDate;//.setDate(inicioAusenciaDate.getDate());
	var sem, dia, mes , anio, ausencia;
	var arrayFechas2 = new Array();

//	console.log(inicioAusenciaMasUno);
//	console.log(finAusenciaDate);
//	console.log("______________________________");
//	$('#listFechasAct').remove();
//	$('#listaFechas').empty();
	$(fechas).insertAfter($('#fechas'));
	
	if(inicioAusenciaMasUno.toString() === finAusenciaDate.toString()){
		ausencia = transformaDia(inicioAusenciaMasUno);
		$('#listaFechas').append("<li>Ausencia Fecha : style='font-family: Lucida Console, monospace; '><b>"+ ausencia +"</b>"+ sumaEspacio(ausencia.toString())+"</span><a onclick=eliminarFecha(this); class='mb-2 mr-2 badge badge-warning'>Eliminar</a></li>");
		arrayFechas.push(inicioAusenciaMasUno.toString());
		arrayFechas2.push(ausencia);
	}else{
	for(var i = 0 ; i < 365 ; i++){
		if(inicioAusenciaMasUno.toString() === finAusenciaDate.toString()){
			break;
		}else{
			if(i===0){
				ausencia = transformaDia(inicioAusenciaMasUno);
			}else{
				inicioAusenciaMasUno = sumarDias(inicioAusenciaMasUno, 1);
				ausencia = transformaDia(inicioAusenciaMasUno);				
			  }
			arrayFechas.push(inicioAusenciaMasUno.toString());
			arrayFechas2.push(ausencia);
			var espacio = sumaEspacio(ausencia.toString());
			$('#listaFechas').append("<li id='li"+i+"'>Ausencia Fecha : <span style='font-family: Lucida Console, monospace; '><b> "+ ausencia.toString() +".</b>"+espacio.toString()+"</span><a id='"+inicioAusenciaMasUno+"' idCount='"+i+"'  onclick=eliminarFecha(this); class='mb-2 mr-2 badge badge-warning'> Eliminar</a></li>");
			}
		}
	}
		console.log(arrayFechas);
	//	console.log(arrayFechas2);
//		agregaFechas(arrayFechas);
		$.listFechas = arrayFechas;
	$('#listFechas').val($.listFechas);
}


function agregaFechas(arrayFechas){
	$('#listFechas').val(arrayFechas);
	console.log(arrayFechas)
	return arrayFechas;
}


function transformaDia(fechaSelect){
	if(validaHoy(fechaSelect)){
		dia = fechaSelect.getDate();
		mes = fechaSelect.getMonth() + 1;
		anio = fechaSelect.getFullYear();
		sem = getDia(fechaSelect.getDay());
		
		return (sem + ", " + dia + "/"+ mes + "/" + anio);
	}else{
		return "";
	}
}

function getDia(dia){
	var semana= ["Domingo", "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"];
	return semana[dia];
}


function sumarDias(fecha, dias){
	  fecha.setDate(fecha.getDate() + dias);
//	  console.log(fecha);
	  return fecha;
	}

function stringToList(cadena){
	return cadena.split(";");
}

function eliminarFecha(ele){
	 var id = $(ele).attr('id');
	 var idCount = $(ele).attr('idCount');
	 var newArray = new Array();
//	 console.log(idCount);
//	 console.log(id);
	    $('#li'+idCount).remove();
	    for(i in $.listFechas){
	    	var fecha = $.listFechas[i];
	    	if(fecha.toString() !== id.toString()){
	    		newArray.push(fecha);
	    	}
	    }
	    $.listFechas = newArray; 
//	    console.log($.listFechas);
	    $('#listFechas').val($.listFechas);
}

function sumaEspacio(str){
	var largo = str.length;
	var dif = 21 - largo;
	var espacio = "";
	for(i=0;i<dif;i++){
		espacio = espacio + "_";
	}
	return espacio;
}

function validaHoy(fechaAsignacion){
	var hoy = new Date();
	var asignacion = new Date(fechaAsignacion)
	if(asignacion > hoy){
//		console.log(asignacion)
		return true;
	}else
		return false;
}

function orderFecha(array){
	var arrayOrdenada = new Array();
    arrayOrdenada = array.sort(function(a, b){return new Date(a) - new Date(b)});
}