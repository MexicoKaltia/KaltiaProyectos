$(document).ready(function() {

	// -  console.log(idModuloCurso);
	// -  console.log(modulosDidacticos);
//	// -  console.log(perfil);
		 
	
	
	 $('#nombreModulo').html(getModuloNombre(idModuloCurso));
	if(perfil !== 'inicioInstructor'){
		 $('#nombreEmpresa').html(getEmpresaNombre(modulosDidacticos.usuarioAudiencia.usuarioAudiencia.usuarioAudienciaNombreEvento));
		 $('#nombreCurso').html(getCursoNombre(modulosDidacticos.usuarioAudiencia.usuarioAudiencia.usuarioAudienciaNombreEvento));
	 }else{
		 $('#nombreCurso').html($moduloCurso.cursoNombre); 
	 }
	 
	 $('#moduloInstrucciones').html(getModuloInstrucciones(idModuloCurso));
	 
	 /*
	  * sonido inicial
	  */
//	 sonidoEfecto = '<audio id="audioInicial" controls><source type="audio/mp3" src="audio/intro.mp3"></audio>';
//	 $('#soundInicial').append(sonidoEfecto);
//	 var audioInicial = document.getElementById("audioInicial");
//	 audioInicial.play();
	 
//	 // -  console.log($moduloCurso);
	 var elementos = new Array();
	 elementos = getElementos($moduloCurso.elementos);
	 
	 
	 $("#tablaElementos").empty();
	 var fila = '<tr></tr>';
	 /*
	  * procesa grid
	  */

	 var filasColumnas = getFilasColumnas(elementos.length);
	 var elementoContat="";
	 var o = 0;
	 for(var e=0; e<filasColumnas[0]; e++){
		 for(var a=0; a<filasColumnas[1]; a++){
			 if(o < elementos.length){
//				 // -  console.log(elementos[o]);
//				 var imagen = '\\uploads\\img\\'+$moduloCurso.idModuloDidactico+'-'+$moduloCurso.idCurso+'\\'+elementos[i-1].imagen;
				 var elemento = '<td><div class="card-container manual-flip conteo" id="'+(e+1)+'-'+(a+1)+'">\
		             <div class="card fondo ">\
		                 <div class="front" onclick="rotateCard(this)">\
		                    <div class="logoGamer"></div>\
		                     <div class="tamano1 text-center fondo">'+(e+1)+'-'+(a+1)+'</div>\
		                 </div> <!-- end front panel -->\
		                 <div class="back" onclick="rotateCard(this)">\
		                     <div class="imagenBack">\
		                         <img src="\\uploads\\img\\'+$moduloCurso.idModuloDidactico+'-'+$moduloCurso.idCurso+'\\'+elementos[o].imagen+'" id="imagen'+(e+1)+'-'+(a+1)+'" class="img-fluid" style="background-position: center center; max-width: 9rem; max-height: 9rem" alt="">\
		                     </div>\
		                     <div class="fondo">\
		                             <h4 class="text-center tamano1" id="titulo'+(e+1)+'-'+(a+1)+'">'+elementos[o].titulo+'</h4>\
		                     </div>\
		                 </div> <!-- end back panel -->\
		             </div> <!-- end card -->\
		         </div> <!-- end card-container -->\
		     </td>'; 
//		         <p class="text-center tamano05" id="descripcion'+(e+1)+'-'+(a+1)+'">'+elementos[o].descripcion+'</p>\
//				 <i class="fa fa-mail-forward"></i>
		//		 $('#').append(elemento);
				 var elementoContat = elementoContat + elemento;
				 o++; 
			 }
		 }
		 elementoContat =  '<tr>'+elementoContat+'</tr>';
	 }
	 $("#tablaElementos").append(elementoContat);
	 
	 var contador = 0;
	 
	 var titulo1 = "";
	 var idSel1="";
	 var conteoEvento=0;
	 $('.conteo').click(function(){
		 
		 var sel = $(this).html();
		 var idSel = $(this).attr("id");
		 var imagen = $('#imagen'+idSel).attr('src');
		 var titulo = $('#titulo'+idSel).html();
		 var descripcion = $('#descripcion'+idSel).html();
		 $('#soundModal').empty();
		 // -  console.log("3");
		 
		 if(contador === 0){
			 contador = 1;		
			 titulo1 = titulo;
			 idSel1 = idSel;
			 $('#seleccion1').html(idSel);
			 $('#seleccion1Titulo').html(titulo);
			 $('#seleccion1Descripcion').html(descripcion);
			 $('#seleccion1Imagen').attr('src',imagen);
			 
		 }else{
			 contador = 0;
			 conteoEvento++;
			 $('.conteo').click(function(){return false;});
			 setTimeout(cerrarModal, 3000);
//			 cerrarModal();
		 }
		 
		 function cerrarModal(){
			 // -  console.log(titulo1+'-'+titulo);
			 
			 if(titulo1.toString() === titulo.toString()){
				 
//				 $('#'+idSel).removeClass('card-container');
//				 $('#'+idSel1).removeClass('card-container');
//				 $('#'+idSel).removeClass('manual-flip');
//				 $('#'+idSel1).removeClass('manual-flip');
				 $('#'+idSel).addClass('bloqueo');
				 $('#'+idSel1).addClass('bloqueo');
				 // -  console.log($('#'+idSel).html());
				 sonidoEfecto = '<audio id="audio" controls><source type="audio/mp3" src="sonido/sonido_correcto.mp3"></audio>';
				 $('#modalCoincidir').modal();
//				 setTimeout(cerrarModal, 18000);
				 
			 }else{
				 // -  console.log("NO coincidir valores1");
//				 setTimeout(enviaAlerta(), 8000);
				 $('#'+idSel).removeClass('hover');
				 $('#'+idSel1).removeClass('hover');
//				 rotateCard(this);
				 sonidoEfecto = '<audio id="audio" controls><source type="audio/mp3" src="sonido/sonido_error.mp3"></audio>';
			 }
			 $('#soundModal').append(sonidoEfecto);
			 $("#btnPuntuaje").html("Movimientos : "+conteoEvento);
			 var audio = document.getElementById("audio");
			 audio.play();
//			 $('.conteo').click(true);
//			 $('#modalCoincidir').modal('hide');
		}
	 });
}); // fin de documento

function enviaAlerta(){
	// -  console.log("hola");
}

function getCursoNombre(cadena){
	var arrayTmp = new Array();
	arrayTmp = cadena.split("?");
//	// -  console.log(arrayTmp[1]);
	return arrayTmp[1]; 
}

function getEmpresaNombre(cadena){
	var arrayTmp = new Array();
	arrayTmp = cadena.split("?");
//	// -  console.log(arrayTmp[1]);
	return arrayTmp[2]; 
}

function getModuloNombre(id){
	for(a in modulosDidacticos.modulosDidacticos.moduloCurso){
		if((modulosDidacticos.modulosDidacticos.moduloCurso[a].idModuloCurso+0) === (id+0)){
//			// -  console.log(modulosDidacticos.modulosDidacticos.moduloCurso[a].moduloNombre);
			$moduloCurso = modulosDidacticos.modulosDidacticos.moduloCurso[a];
			return modulosDidacticos.modulosDidacticos.moduloCurso[a].moduloNombre; 
		}
	}
}

function getModuloInstrucciones(id){
	var idModuloDidactico=0;
	for(a in modulosDidacticos.modulosDidacticos.moduloCurso){
		if((modulosDidacticos.modulosDidacticos.moduloCurso[a].idModuloCurso*1) === (id*1)){
			idModuloDidactico = modulosDidacticos.modulosDidacticos.moduloCurso[a].idModuloDidactico;
		}
	}
	for(e in modulosDidacticos.modulosDidacticos.modulos){
		if((idModuloDidactico*1) === (modulosDidacticos.modulosDidacticos.modulos[e].idModuloDidactico*1)){
			return modulosDidacticos.modulosDidacticos.modulos[e].moduloDidacticoInstrucciones;
		}
	}
	
}
function getElementos(cadena){
	var arrayA = new Array();
	var arrayE = new Array();
	var arrayI = new Array();
	arrayA = cadena.split("++");
	for(a in arrayA){
		arrayE = arrayA[a].split("--");
		var elemento = { 
				imagen:arrayE[0],
				titulo:arrayE[1],
				descripcion:arrayE[2]
			}
		arrayI.push(elemento);
	}
	return getGrid(arrayI);
}

function getGrid(array){
//	// -  console.log(array);
//	// -  console.log(array.length);
	/*
	 * aqui hay q poner el orden de los elementos
	 */
	var arrayA = array.concat(array);
//	// -  console.log(arrayA);
	return arrayA;
}

function getFilasColumnas(eles){
	var tmp= new Array();
	// -  console.log(eles);
	if(eles < 7){
		tmp = [3,3];
	}else if(7 <= eles && eles < 13){
		tmp = [3,4];
	}else if(13 <= eles && eles < 17){
		tmp = [4,4];
	}else if(17 <= eles && eles < 21){
		tmp = [4,5];
	}else if(21 <= eles && eles < 26){
		tmp = [5,5];
	}else if(26 <= eles && eles < 31){
		tmp = [6,5];
	}else if(31 <= eles && eles < 37){
		tmp = [6,6];
	}else if(37 <= eles && eles < 43){
		tmp = [6,7];
	}else if(43 <= eles && eles < 50){
		tmp = [7,7];
	}
	// -  console.log(tmp);
	return tmp
}