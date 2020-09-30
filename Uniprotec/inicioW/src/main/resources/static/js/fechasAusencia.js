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
		console.log(strList);
		if(isEmpty(strList) ){
			console.log("historico de Lista de Fechas");
			arrayFechas = stringToList($('#listFechas').val().toString());
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
		$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
		arrayFechas.push(inicioAusenciaMasUno.toString());
		arrayFechas2.push(ausencia);
	}else{
	for(var i = 0 ; i < 365 ; i++){
		if(inicioAusenciaMasUno.toString() === finAusenciaDate.toString()){
			break;
		}else{
			if(i===0){
				ausencia = transformaDia(inicioAusenciaMasUno);
				$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
				arrayFechas.push(inicioAusenciaMasUno.toString());
				arrayFechas2.push(ausencia);
			}else{
				inicioAusenciaMasUno = sumarDias(inicioAusenciaMasUno, 1);
				ausencia = transformaDia(inicioAusenciaMasUno);
				$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
				arrayFechas.push(inicioAusenciaMasUno.toString());
				arrayFechas2.push(ausencia);
			  }
			}
		}
	}
	//	console.log(arrayFechas);
	//	console.log(arrayFechas2);
		agregaFechas(arrayFechas);
	}
function agregaFechas(arrayFechas){
	$('#listFechas').val(arrayFechas);
	console.log(arrayFechas)
	return arrayFechas;
}


function transformaDia(fechaSelect){
	dia = fechaSelect.getDate();
	mes = fechaSelect.getMonth() + 1;
	anio = fechaSelect.getFullYear();
	sem = getDia(fechaSelect.getDay());
	
	return (sem + ", " + dia + "/"+ mes + "/" + anio);
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

