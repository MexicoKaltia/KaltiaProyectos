/*
 *  JS Verifica y Edita las fechas de Ausencia Instructores
 */
var elementoPicker = $datepicker.pickadate('picker');
var elementoPicker2 = $datepicker2.pickadate('picker');
var fechas="<div class='alert alert-warning alert-dismissible' id='listFechasAct' role='alert'><button type='button' class='close' data-dismiss='alert'>&times;</button>Fechas de Ausencia:<ul id='listaFechas'></ul></div>";
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
	}
}

function sumaFechas() {
	var inicioAusencia = elementoPicker.get('select', 'mm/dd/yyyy');
	var finAusencia = elementoPicker2.get('select', 'mm/dd/yyyy');	
	var inicioAusenciaDate = new Date(inicioAusencia); 
	var finAusenciaDate = new Date(finAusencia);
	var inicioAusenciaMasUno = inicioAusenciaDate;//.setDate(inicioAusenciaDate.getDate());
	var sem, dia, mes , anio, ausencia;
	var arrayFechas2 = arrayFechas;
	arrayFechas = [];
//	console.log(inicioAusenciaMasUno);
//	console.log(finAusenciaDate);
//	console.log("______________________________");
	$('#listFechasAct').remove();
	$('#listaFechas').empty();
	$(fechas).insertAfter($('#fechas'));
	
	ausencia = transformaDia(inicioAusenciaMasUno);
	$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
	arrayFechas.push(inicioAusenciaMasUno.toString());
	for(var i = 0 ; i < 365 ; i++){
		if(inicioAusenciaMasUno.toString() === finAusenciaDate.toString()){
			break;
		}else{
			inicioAusenciaMasUno = sumarDias(inicioAusenciaMasUno, 1);
			ausencia = transformaDia(inicioAusenciaMasUno);
			$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
			arrayFechas.push(inicioAusenciaMasUno.toString());
		}
	}
	console.log(arrayFechas);
//	console.log(arrayFechas2);
//	var sel ;
//	for(i in arrayFechas){
//		for(o in arrayFechas2){
//			sel = new Date(arrayFechas2[o]);
//			if(sel.toString() === arrayFechas[i].toString()){
//				continue;
//			}else{
//				sel = sumarDias(sel, 1);
//				ausencia = transformaDia(sel);
//				$('#listaFechas').append("<li>Ausencia Fecha : <b>"+ ausencia +"</b></li>");
//				arrayFechas.push(sel.toString());
//			}
//		}
//	}
	$('#listFechas').val(arrayFechas);
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