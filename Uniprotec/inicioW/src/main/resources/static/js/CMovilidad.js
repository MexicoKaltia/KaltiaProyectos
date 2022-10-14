/*
 * funciones de table Genericas
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

// function operateFormatterExpediente(value, row, index) {
//	    return [
//	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalExpediente">',
//	      '<i class="fa fa-2x fa-address-book"></i>',
//	      '</a>'
//	    ].join('')
//	  }
// function operateFormatterAsignaciones(value, row, index) {
//	    return [
//	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalAsignaciones">',
//	      '<i class="fa fa-2x fa-history"></i>',
//	      '</a>'
//	    ].join('')
//	  }
// function operateFormatterExpedienteAsignacion(value, row, index) {
//	    return [
//	      '<a class="like" href="javascript:void(0)" title="Consultar"  data-toggle="modal" data-target="#modalAsignacion">',
//	      '<i class="fa fa-2x fa-indent"></i>',
//	      '</a>'
//	    ].join('')
//	  }
//	  
//	  function operateFormatterDelete(value, row, index) {
//		    return [
//		      '<a class="remove" href="javascript:void(0)" title="Eliminar">',
//		      '<i class="fa fa-2x fa-history">',
//		      '</a>'
//		    ].join('')
//		  }
	  

	
	
$(document).ready(function(){
		
	/*
	 * Carga la Tabla inicial
	 */
	
//	console.log($regiones);
	var jsonZonaBase = JSON.parse($zonaBase);
//	console.log(jsonZonaBase);

	// tabla regiones
	var regiones ="";
	for(a in $regiones){
		var region = $regiones[a];
		var claseZona = getColorZonaBase(region.idRegion);
		regiones = regiones + "<tr><td  class="+claseZona+">"+region.idRegion+"</td><td>"+region.nombreRegion+"</td></tr>";
	}
	$('#tbodyZona').append(regiones);
	
	//carga tabla inicial
	createTable(jsonZonaBase);
	
	$(".btnEdicion").click(function(){
		console.log("boton Edicion");
		$('#cardHeadModalEdicionMovilidad').empty();
		$('#cardBodyModalEdicionMovilidad').empty();
		$('#tablaConsulta').empty();
		
		var id = $(this).attr("id");
		var idRow = id.substring(0,1);
		console.log(id);
		var columna = "";
		var arrayRow = new Array();
		for(var i in jsonZonaBase){
			var tmp1 = jsonZonaBase[i];
			var tmp2 = i;
			if(tmp2.substring(0,1) == idRow){
				arrayRow.push(tmp1);
			}
		}
		
		// tabla Edicion Row Region
		var columna = '<tr><td data-width="10" data-width-unit="%" data-halign="center" data-align="center" ><b>ZONA</b></td>';
		for(var a in $regiones){
			var zona = $regiones[a];
			var idRegion = zona.idRegion;
			var nombreRegion = zona.nombreRegion;
			var claseZona = getColorZonaBase(idRegion);
			columna = columna + '<td data-halign="center" data-align="center" class="'+claseZona+'"><b>'+idRegion+'</b></td>';
		}
		columna = columna + '</tr>';
		$('#cardHeadModalEdicionMovilidad').append(columna);
//		console.log(arrayRow);
		
		var clase = getColorZonaBase(idRow*1);
		var cellStatus = '<tr><td data-halign="center" data-align="center" class="'+clase+'"><b>'+idRow+'</b></td>';
		for(var e in arrayRow){
			var cell = arrayRow[e]
			var claseZona = getColorZonaBase(id);
			if(cell == true){
				cellStatus = cellStatus + '<td data-halign="center" data-align="center"><label class="switch"><input type="checkbox" id="'+e+'-cell" checked/><span class="slider round"></span></label></td>';
			}else{
				cellStatus = cellStatus + '<td data-halign="center" data-align="center"><label class="switch"><input type="checkbox" id="'+e+'-cell"/><span class="slider round"></span></label></td>';
			}
		}
		cellStatus = cellStatus + '</tr>';
		$('#cardBodyModalEdicionMovilidad').append(cellStatus);
		
		// tabla consulta
		var regiones = "";
		for(a in $regiones){
			var region = $regiones[a];
			var claseZona = getColorZonaBase(region.idRegion);
			regiones = regiones + "<tr><td  WIDTH='50' data-halign='center' data-align='center' class='"+claseZona+"'>  "+region.idRegion+"  </td><td>"+region.nombreRegion+"</td></tr>";
		}
		$('#tablaConsulta').append(regiones);
	});
	
	$("#btnEdicionMovilidad").click(function(){
		var idRow = "";
		var arrayRow = new Array();
		$('#cardBodyModalEdicionMovilidad > tr > td').each(function(index, td){
			if(index === 0){
				idRow = $(td).text();
			}
		});
		
		$('#cardBodyModalEdicionMovilidad > tr > td > label > input').each(function(index, aa){
			var value = $('#'+index+'-cell').is(":checked");
			var element = {index:value};
			arrayRow.push(value);	
		});
		var e = 0;
		for(var i in jsonZonaBase){
			var tmp1 = jsonZonaBase[i];
			var tmp2 = i;
			if(tmp2.substring(0,1) == idRow){
				jsonZonaBase[i] = arrayRow[e];
				e++;
			}
		}
//		console.log(jsonZonaBase);
		var myJSON = JSON.stringify(jsonZonaBase);
		$("#dataZonabase").val(myJSON);
		createTable(jsonZonaBase);
		$("#BMovilidad").submit();
	});	

		

	
			
}); //fin jqry

function createTable(jsonZonaBase){
	$('#theadMovilidad').empty();
	$('#tbodyMovilidad').empty();
	// tabla logica movilidad Encabezado
	var columna = '<tr><td data-width="10" data-width-unit="%" data-halign="center" data-align="center" ><b>ZONA</b></td>';
	for(var a in $regiones){
		var zona = $regiones[a];
		var idRegion = zona.idRegion;
		var nombreRegion = zona.nombreRegion;
		var claseZona = getColorZonaBase(idRegion);
		columna = columna + '<td data-halign="center" data-align="center" class="'+claseZona+'"><b>'+idRegion+'</b></td>';
	}
	columna = columna + '<td data-halign="center" data-align="center" data-field="operateUpdate1" data-formatter="operateFormatterExpediente" data-events="window.operateEventsExpediente" data-cell-style="color:red;"><b>Edicion Registro</b></td></tr>';
	$('#tbodyMovilidad').append(columna);

	// tabla logica movilidad Body
	for(var e in $regiones){
		var count = ((e*1)+1);
		var claseZona = getColorZonaBase(count);
		var renglon = '<tr><td  data-halign="center" data-align="center" class="'+claseZona+'"><b>'+count+'</b></td>';
//		$('#tbodyMovilidad').append(renglon0);
		for(var i in jsonZonaBase){
			var tmp1 = jsonZonaBase[i];
			var tmp2 = i;
			if(tmp2.substring(0,1) == count){
				if(tmp1){
					 renglon = renglon + '<td data-halign="center" data-align="center" >'+tmp1+'</td>';
				}else{
					renglon = renglon + '<td data-halign="center" data-align="center" style="color:red;">'+tmp1+'</td>';
				}
			}
		}
		renglon = renglon + '<td data-halign="center" data-align="center" data-field="operateUpdate1" data-formatter="operateFormatterEdicionMovilidad" data-events="window.operateEventsEdicionMovilidad" data-cell-style="color:red;"><a class="like btnEdicion" id="'+count+'Edicion"  href="javascript:btnEdicion()" title="Edicion Movilidad"  data-toggle="modal" data-target="#modalEdicionMovilidad"><i class="fa fa-2x fa-indent"></i></a></td></tr>';
//		renglon = renglon + '<td><a class="like btnEdicion" id="'+count+'Edicion" title="Edicion Movilidad"  data-toggle="modal" data-target="#modalEdicionMovilidad"><i class="fa fa-2x fa-indent"></i></a></td></tr>';
		$('#tbodyMovilidad').append(renglon);
	}
}



	
	
	
	
	function getColorZonaBase(idRegion){
		var zonaCliente ="";
		switch (idRegion){
		case 1:
			zonaCliente = 'zona1';
			break;
		case 2:
			zonaCliente = 'zona2';
			break;
		case 3:
			zonaCliente = 'zona3';
			break;
		case 4:
			zonaCliente = 'zona4';
			break;
		case 5:
			zonaCliente = 'zona5';
			break;
		case 6:
			zonaCliente = 'zona6';
			break;
		case 7:
			zonaCliente = 'zona7';
			break;
		case 8:
			zonaCliente = 'zona8';
			break;
		case 9:
			zonaCliente = 'zona9';
			break;
		}
		return zonaCliente;
	}
	
	
//	window.operateEventsEdicionMovilidad = {
//		    'click .like': function (e, value, row, index) {
//		    	console.log(row);
//	      
//		    }
//		   }
	

	
	
//	$('#movilidadTable').bootstrapTable({data : $data})

	
	/*
	 * funciones
	 */
	




