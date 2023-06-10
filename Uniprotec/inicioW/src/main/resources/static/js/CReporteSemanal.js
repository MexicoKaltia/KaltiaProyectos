
var totalCobranza = 0;
var totalProgramadoSemana = 0;
var totalVencido = 0;
var totalSinFecha = 0;
var totalProgramadoMes1 = 0;
var totalProgramadoMes2 = 0;
var totalProgramadoMes3 = 0;
var mes1, mes2, mes3;
var weekNumber = 0;
var dayInit = new Date();
var dayFinish = new Date();
var currentDate = new Date();
	
$(document).ready(function(){
		
	calculateWeek();
	calculateDaysWeek();
		
	$('.semanaInicio').html(transformaDia(dayInit));
	$('.semanaFinal').html(transformaDia(dayFinish));
	$('.semanaNumero').html(weekNumber);
	
	calculateTotal(dayInit, dayFinish, new Date());
	calculateMes(currentDate);
	
	$('#totalCobranza').val(formatter.format(totalCobranza));
	$('#totalProgramadoSemana').val(formatter.format(totalProgramadoSemana));
	$('#totalVencido').val(formatter.format(totalVencido));
	$('#totalSinFecha').val(formatter.format(totalSinFecha));
	$('#totalProgramadoMes1').val(formatter.format(totalProgramadoMes1));
	$('#totalProgramadoMes2').val(formatter.format(totalProgramadoMes2));
	$('#totalProgramadoMes3').val(formatter.format(totalProgramadoMes3));
	
	$('#labeltotalCobranza').text(formatIVA(totalCobranza));
	$('#labeltotalProgramadoSemana').text(formatIVA(totalProgramadoSemana));
	$('#labeltotalVencido').text(formatIVA(totalVencido));
	$('#labeltotalSinFecha').text(formatIVA(totalSinFecha));
	$('#labeltotalProgramadoMes1').text(formatIVA(totalProgramadoMes1));
	$('#labeltotalProgramadoMes2').text(formatIVA(totalProgramadoMes2));
	$('#labeltotalProgramadoMes3').text(formatIVA(totalProgramadoMes3));
	
	$('.mes1').html(mes1);
	$('.mes2').html(mes2);
	$('.mes3').html(mes3);
	$('#mes1').val(mes1);
	$('#mes2').val(mes2);
	$('#mes3').val(mes3);
	$('#semanaInicio').val(transformaDia(dayInit));
	$('#semanaFinal').val(transformaDia(dayFinish));
	
	$('#semanaReporte').val(weekNumber);
	$('#dayInit').val(dayInit);
	$('#dayFinish').val(dayFinish);
	
	$('#idUsuario').val(idUsuario);

}); //fin jQuery

	//numero de semana presente
	function calculateWeek(){
		
		startDate = new Date(currentDate.getFullYear(), 0, 1);
		var days = Math.floor((currentDate - startDate) /
		    (24 * 60 * 60 * 1000));
		 
		weekNumber = Math.ceil(days / 7);
//		console.log("Week number of " + currentDate + " is :   " + weekNumber);
	}
	
	
	//dias de la semana
	function calculateDaysWeek(){

		const d = new Date();
		let day = d.getDay();
		dayInit.setDate(d.getDate()-day);
		dayFinish.setDate(d.getDate()+(6-day));
		
//		console.log(dayInit);
//		console.log(dayFinish);
	}
	
	//calcula numero de semana
	function calculateWeekCaption(inputAsignaFecha){
		var elementoPicker = $datepicker.pickadate('picker');	
		var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
		var fechaSelect = new Date(asignaFecha);
   		
		startDate = new Date(fechaSelect.getFullYear(), 0, 1);
		var days = Math.floor((fechaSelect - startDate) /
		    (24 * 60 * 60 * 1000));
		 
		weekNumber = Math.ceil(days / 7);
		console.log("Semana : " + weekNumber);
		calculateDaysWeekCaption(asignaFecha);
		
		$('.semanaNumero').html(weekNumber);
		
		$('.semanaInicio').html(transformaDia(dayInit));
		$('.semanaFinal').html(transformaDia(dayFinish));
	}
	
	//dias de la semana
	function calculateDaysWeekCaption(asignaFecha){

		const d = new Date(asignaFecha);
//		console.log(d);
		let day = d.getDay();
		dayInit = new Date(asignaFecha);
		dayFinish = new Date(asignaFecha);

		dayInit.setDate(d.getDate()-day);
		dayFinish.setDate(d.getDate()+(6-day));
//		console.log(dayInit);
//		console.log(dayFinish);
		
		calculateTotal(dayInit, dayFinish, asignaFecha);
	}



	function calculateTotal(dayInit, dayFinish, fechaSelect){
		totalCobranza = 0;
		totalProgramadoSemana = 0;
		totalVencido = 0;
		totalSinFecha = 0;
		totalProgramadoMes1 = 0;
		totalProgramadoMes2 = 0;
		totalProgramadoMes3 = 0;
		for(var a in datosEconomicos){
			var datoEconomico = datosEconomicos[a];
			
			//totalCobranza
			if(datoEconomico.estatusDatoEconomico == "PAGADA"){
				if(calculateTotalCobranza(datoEconomico.fechaCambioEstatus)){
					totalCobranza = totalCobranza + datoEconomico.formAEPrecioVentaReal;  
				}
			}
			// totalProgramadoSemana 
			if(datoEconomico.estatusDatoEconomico == "PENDIENTE"){
				if(calculateTotalProgramadoSemana(datoEconomico.formAEFechaPromesaPagoFormat)){
//					console.log(datoEconomico)
					totalProgramadoSemana = totalProgramadoSemana + datoEconomico.formAEPrecioVentaReal;
				}
			}
			// totalVencido 
			if(datoEconomico.estatusDatoEconomico == "PENDIENTE"){
				if(calculateTotalVencido(datoEconomico.formAEFechaPromesaPagoFormat)){
					totalVencido = totalVencido + datoEconomico.formAEPrecioVentaReal;
				}
			}
			// totalSinFecha 
			if(datoEconomico.estatusDatoEconomico == "VIGENTE"){
				if(calculateTotalSinFecha(fechaSelect)){
					totalSinFecha = totalSinFecha + datoEconomico.formAEPrecioVentaReal;
				}
			}
			// totalProgramadoMes1
			if(datoEconomico.estatusDatoEconomico == "PENDIENTE"){
				if(calculateTotalProgramadoMes1(datoEconomico.formAEFechaPromesaPagoFormat, fechaSelect)){
					totalProgramadoMes1 = totalProgramadoMes1 + datoEconomico.formAEPrecioVentaReal;
				}
				if(calculateTotalProgramadoMes2(datoEconomico.formAEFechaPromesaPagoFormat, fechaSelect)){
					totalProgramadoMes2 = totalProgramadoMes2 + datoEconomico.formAEPrecioVentaReal;
				}
				if(calculateTotalProgramadoMes3(datoEconomico.formAEFechaPromesaPagoFormat, fechaSelect)){
					totalProgramadoMes3 = totalProgramadoMes3 + datoEconomico.formAEPrecioVentaReal;
				}
			}
			
		}//fin for
		
		
		
		$('#totalCobranza').val(formatter.format(totalCobranza));
		$('#totalProgramadoSemana').val(formatter.format(totalProgramadoSemana));
		$('#totalVencido').val(formatter.format(totalVencido));
		$('#totalSinFecha').val(formatter.format(totalSinFecha));
		$('#totalProgramadoMes1').val(formatter.format(totalProgramadoMes1));
		$('#totalProgramadoMes2').val(formatter.format(totalProgramadoMes2));
		$('#totalProgramadoMes3').val(formatter.format(totalProgramadoMes3));
		
		$('#labeltotalCobranza').text(formatIVA(totalCobranza));
		$('#labeltotalProgramadoSemana').text(formatIVA(totalProgramadoSemana));
		$('#labeltotalVencido').text(formatIVA(totalVencido));
		$('#labeltotalSinFecha').text(formatIVA(totalSinFecha));
		$('#labeltotalProgramadoMes1').text(formatIVA(totalProgramadoMes1));
		$('#labeltotalProgramadoMes2').text(formatIVA(totalProgramadoMes2));
		$('#labeltotalProgramadoMes3').text(formatIVA(totalProgramadoMes3));
		
		calculateMes(fechaSelect);
		$('.mes1').html(mes1);
		$('.mes2').html(mes2);
		$('.mes3').html(mes3);
		$('#mes1').val(mes1);
		$('#mes2').val(mes2);
		$('#mes3').val(mes3);
		
		$('#semanaInicio').val(transformaDia(dayInit));
		$('#semanaFinal').val(transformaDia(dayFinish));
		
		$('#semanaReporte').val(weekNumber);
		$('#dayInit').val(dayInit);
		$('#dayFinish').val(dayFinish);
		
		$('#idUsuario').val(idUsuario);
		
	}
	
	function calculateTotalCobranza(fechaCalcular){
		var fechaRegistro = new Date(fechaCalcular);
		if((startDate <= fechaRegistro)  && (fechaRegistro <= dayFinish)){
			return true;
		}
		return false;
	}
	function calculateTotalProgramadoSemana(fechaCalcular){
		var fechaRegistro = new Date(fechaCalcular);
		if((dayInit <= fechaRegistro)  && (fechaRegistro <= dayFinish)){
			return true;
		}
		return false;	
	}
	function calculateTotalVencido(fechaCalcular){
		var fechaRegistro = new Date(fechaCalcular);
		if((startDate <= fechaRegistro)  && (fechaRegistro <= dayFinish)){
			return true;
		}
		return false;
	}
	function calculateTotalSinFecha(fechaCalcular){
		var fechaRegistro = new Date(fechaCalcular);
//		console.log(dayFinish)
		if((startDate <= fechaRegistro)  && (fechaRegistro <= dayFinish)){
			return true;
		}
		return false;
	}
	function calculateTotalProgramadoMes1(fechaCalcular, fechaSelect){
		var fechaRegistro = new Date(fechaCalcular);
		var fechaPeriodo = new Date(fechaSelect);
		var fechaMes1Inicial = new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 1, 1);
		var fechaMes1Final= new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 2, 1);
		if((fechaMes1Inicial <= fechaRegistro)  && (fechaRegistro < fechaMes1Final)){
//			console.log(fechaRegistro);
			return true;
		}
		return false;
	}
	function calculateTotalProgramadoMes2(fechaCalcular, fechaSelect){
		var fechaRegistro = new Date(fechaCalcular);
		var fechaPeriodo = new Date(fechaSelect);
		var fechaMes1Inicial = new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 2, 1);
		var fechaMes1Final= new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 3, 1);
		if((fechaMes1Inicial <= fechaRegistro)  && (fechaRegistro < fechaMes1Final)){
//			console.log(fechaRegistro);
			return true;
		}
		return false;
	}
	function calculateTotalProgramadoMes3(fechaCalcular, fechaSelect){
		var fechaRegistro = new Date(fechaCalcular);
		var fechaPeriodo = new Date(fechaSelect);
		var fechaMes1Inicial = new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 3, 1);
		var fechaMes1Final= new Date(fechaPeriodo.getFullYear(), fechaPeriodo.getMonth() + 4, 1);
		if((fechaMes1Inicial <= fechaRegistro)  && (fechaRegistro < fechaMes1Final)){
//			console.log(fechaRegistro);
			return true;
		}
		return false;
	}
	
	function calculateMes(currentDate){
		var calMes = new Date(currentDate);
		mes1 = getMesNombre(calMes.getMonth() + 1);
		mes2 = getMesNombre(calMes.getMonth() + 2);
		mes3 = getMesNombre(calMes.getMonth() + 3);
		
	}
	

	 //* los dias del mes de la semana dinamica
	function getDaysWeek(semana){
		var diasAnio = semana * 7;
	//	console.log(diasAnio);
		var finishDate = new Date();
		var initDate = new Date();
		finishDate = new Date(finishDate.getFullYear(), 0, 1);
		finishDate.setDate(diasAnio);
		initDate.setDate(finishDate.getDate()-6);
		console.log(initDate);
		console.log(finishDate);

	}
	
	function formatIVA(valor){
		return formatter.format(((valor * 0.16) + valor));
	}

	function transformaDia(fechaSelectString){
		var fechaSelect = new Date(fechaSelectString);
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

	const formatter = new Intl.NumberFormat('es-MX', {
	      style: 'currency',
	      currency: 'MXN',
	      minimumFractionDigits: 0
	    });

	function getMesNombre(numero){
		const month = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
//		console.log(month[numero]);
		return month[numero].toUpperCase();
	}
	
	function getFecha(fechaValor){
		var d = new Date(fechaValor);
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var fecha = anio + '-' + mes + '-' + dia+ 'T';
		return fecha + "00:00";
	}