$(document).ready(function() {

	console.log("Modulo Memoria");
	
	$('#divCarrusel').empty();
	var jsonQRD = new Array();// JSON.parse($('#jsonQRDHidden').val());
	$arrayElementosTotal="";
	
	var edicionCategoria = '<br><button type="button" class="btn btn-warning" id="modalEdicionBodyQRD_btnEliminarElemento">Eliminar Elemento</button><button type="button" class="btn btn-info" style="position:absolute; right:16px"id="modalEdicionBodyQRD_btnSumarElemento">Agregar Elemento</button>';
	$('#divEdicionElementoCategoria').append(edicionCategoria);
//	Eliminar Elemento
	 $('#modalEdicionBodyQRD_btnEliminarElemento').click(function(){
		  if(confirm("Confirmar Eliminar Elemento: Los elementos no se presentarán.")){
			  var e = 0;
			  $child = $('#contenedorCarrusel').children("div.active");
			  console.log($child);
			  console.log($arrayElementosTotal);
			  $tmp = $child.children(".activo");
			  $tmp = $tmp.children("div");
			  $tmp = $tmp.children("input");
			  var arrayTmp = new Array();
			  for(a in $arrayElementosTotal){
				  if($arrayElementosTotal[a].titulo !== $tmp.val()){
//					  delete jsonQRD[a];
					  arrayTmp.push($arrayElementosTotal[a]);
				  }
			  }
			  showCarrusel(arrayTmp);
		  }
	  });
//	 Sumar elemento
	 $('#modalEdicionBodyQRD_btnSumarElemento').click(function(){
		 	console.log("btnSumarElemento");
//		 	console.log($idCurso);
//		 	console.log($moduloSel);
//		 	console.log($arrayElementosTotal);
//		 	console.log(jsonQRD);
//		 $('#divCarrusel').empty();
		 	$('#divEdicionElementoCategoria').hide();
		 	$('#modalEdicionBodyQRD_btnEliminarCategoria').hide();
		 	$('#modalEdicionBodyQRD_btnSumarElemento').hide();
		 	$('#modalEdicionBodyQRD_btnSave').hide();
			$('#modalEdicionBodyQRD_btnAgregarElemento').hide();
			$('#divCarrusel').hide();
			$('#divNuevaCategoria').empty();
			$('#divNuevaCategoria').show();
			
			var objetoCarrusel = '<div class="form-group row">\
				<span class="col-3 col-form-label text-right nombre"><i class="fa fa-terminal nombre"></i>Imagen</span>\
				<div class="col-7"><img class="inspace-10 borderedbox" src="" >\
				  <div class="alerta"><hiden class="alerta_in"></hiden></div>\
				  <form id="imagenObjetoQRDForm" class="imagenArrayForm"><hiden class="imagenObjetoQRDForm"></hiden>\
				    <label for="imagenObjetoQRD" class="nombre">Actualiza imagen:</label><input id="imagenObjetoQRD" type="file" name="imagenObjetoQRD" accept="image/jpeg" value="" onchange="imagenElemento(this)" required/></form></div>\
				<small class="form-text bienvenido">Adjuntar imagen formato *.jpg ,  *.png  *.gif  *.bmp  no mayor a un 1MB.</small></div>\
				<div class="form-group row">\
				   <span class="col-3 col-form-label text-right nombre"><i class="fa fa-terminal nombre"></i>Titulo Elemento</span>\
				   <div class="col-7">\
				     <input type="text" class="form-control" id="tituloObjetoQRD" value="" required/></div></div>\
				<div class="form-group row">\
				   <span class="col-3 col-form-label text-right nombre"><i class="fa fa-terminal nombre"></i>Descripcion Elemento</span>\
				   <div class="col-7"><textarea class="form-control" id="descripcionObjetoQRD" required></textarea></div></div>\
				<button type="submit" class="btn btn-primary" id="modalEdicionBodyQRD_btnSaveElemento">Guardar Elemento</button>';
				
			$('#divNuevaCategoria').append(objetoCarrusel);
			
			$(".imagenArrayForm").click(function(){
				var imgArrayForm = $(this).attr('id'); 
				var imgArrayInput =$(this).children("input").attr('id');
//				$('#'+imgArrayInput).on('change', function(){ enviaImagenArray(imgArrayForm, moduloSel.idModuloDidactico)}); 
			});
			$('#modalEdicionBodyQRD_btnSaveElemento').hide();
			$('#tituloObjetoQRD').keyup(function(){
					$('#modalEdicionBodyQRD_btnSaveElemento').show();
			});
			$("#modalEdicionBodyQRD_btnSaveElemento").click(function(){
//				console.log(jsonQRD)
//				var nuevoCategoria = $('#tituloCtegoria').val();
				var nuevoObjeto={
					imagen:formatoImagen($('#imagenObjetoQRD').val()),
					titulo:$('#tituloObjetoQRD').val(),
					descripcion:$('#descripcionObjetoQRD').val()
				}
//				console.log(nuevoObjeto);
				var nuevoArrayObjetos = new Array();
				jsonQRD = $arrayElementosTotal; 
				if(jsonQRD){
					for(i in jsonQRD){
						var elemento = jsonQRD[i];
						nuevoArrayObjetos.push(elemento);
//						console.log(elemento);
					}
				}
				nuevoArrayObjetos.push(nuevoObjeto);
//				var nuevoSeccion = {nuevoCategoria: nuevoArrayObjetos};
				jsonQRD = nuevoArrayObjetos;
				$arrayElementosTotal = jsonQRD;
				showCarrusel(jsonQRD);
		});
	});	
	 
	 
}); // fin de documento


function showCarrusel(arrayElementosTotal){
	console.log(arrayElementosTotal);
	$('#modalEdicionBodyQRD_btnSumarElemento').show();
	$('#divEdicionElementoCategoria').show();
	$('#divNuevaCategoria').hide();
	$('#divCarrusel').empty();
	$('#divCarrusel').show();
//	
	var carrusel = '<div id="carouselEdicionBodyQRD" class="carousel slide" data-interval="false"> <div class="carousel-inner container" id="contenedorCarrusel"><div class="carousel-item col-12 text-center" id="elementoCarrusel"></div> </div><a class="carousel-control-prev" href="#carouselEdicionBodyQRD" role="button" data-slide="prev"><span class="carousel-control-prev-icon" aria-hidden="true"></span><span class="sr-only">Previous</span></a><a class="carousel-control-next" href="#carouselEdicionBodyQRD" role="button" data-slide="next"><span class="carousel-control-next-icon" aria-hidden="true"></span><span class="sr-only">Next</span></a><ol class="carousel-indicators"><li data-target="#carouselEdicionBodyQRD" data-slide-to="0" class="active"></li><li data-target="#carouselEdicionBodyQRD" data-slide-to="1"></li><li data-target="#carouselEdicionBodyQRD" data-slide-to="2"></li></ol></div>'
		$('#divCarrusel').append(carrusel);
		
//		console.log(jsonQRD.hasOwnProperty(categoria));
		var i = 0;
		var objetoArray = arrayElementosTotal; 
		var a = 0;
		$('#contenedorCarrusel #elementoCarrusel').remove();
		for(o in objetoArray){

			var elementoCarrusel='<div class="carousel-item col-12 text-center" id="elementoCarrusel'+a+'"></div>'		
			$('#contenedorCarrusel').append(elementoCarrusel);
			if(a===0){$('#elementoCarrusel'+a).addClass('active');}
//			var imagen = "http://kaltiaservicios.com/store/kaltia/empresa/"+$.action+"/images/"+objetoArray[a].imagen+"?v=1";
			var imagen = '\\uploads\\img\\'+$moduloSel.idModuloDidactico+'-'+$idCurso+'\\'+objetoArray[a].imagen;
//			console.log(imagen);
			var titulo = objetoArray[a].titulo;
			var descripcion = objetoArray[a].descripcion;
			var objetoCarrusel = '<div class="form-group row"><span class="col-3 col-form-label text-right colorLabel nombre">\
				<i class="fa fa-terminal nombre"></i>Imagen</span>\
				<div class="col-7"><img class="inspace-10 borderedbox " src="'+imagen+'" > \
					<form id="imagenObjetoQRDForm'+a+'" class="imagenArrayForm"><hiden class="imagenObjetoQRDForm'+a+'"></hiden><label for="imagenObjetoQRD'+a+'">Actualiza imagen:</label>\
						<input id="imagenObjetoQRD'+a+'" type="file" name="uploadfile" accept="image/jpeg" value="'+objetoArray[a].imagen+'"  disabled/></form></div></div>\
				<div class="form-group row activo"><span class="col-3 col-form-label text-right colorLabel nombre"><i class="fa fa-terminal "></i>Titulo Elemento</span>\
						<div class="col-7"><input type="text" class="form-control " id="tituloObjetoQRD'+a+'" value="'+titulo+'" disabled/></div></div>\
				<div class="form-group row"><span class="col-3 col-form-label text-right colorLabel nombre"><i class="fa fa-terminal nombre"></i>Descripcion Elemento</span>\
						<div class="col-7"><textarea class="form-control" id="descripcionObjetoQRD'+a+'"  disabled>'+descripcion+'</textarea></div></div>';
	       
				$('#elementoCarrusel'+a).append(objetoCarrusel);
			a++;
		}
		$(".imagenArrayForm").click(function(){
			var imgArrayForm = $(this).attr('id'); 
			var imgArrayInput =$(this).children("input").attr('id');
//			console.log(imgArrayForm);
//			console.log(imgArrayInput);
			$('#'+imgArrayInput).on('change', function(){ enviaImagen(imgArrayForm); });
		});
		
	$elementosFinal = arrayElementosTotal;
	$arrayElementosTotal = arrayElementosTotal;	
}

function imagenElemento(archivosCampo){
//	$('#imagenObjetoQRD').val(idImagen);
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
	            enviaImagenElemento(archivosCampo, $moduloSel.idModuloDidactico+"-"+$idCurso);
	            }
//	        console.log(archivosCampo.file.name);
//	        console.log($(archivosCampo).val());
	 }

function formatoImagen(imagenTexto){
	  while(imagenTexto.includes("C:\\fakepath\\") ){
		  imagenTexto = imagenTexto.replace("C:\\fakepath\\", "")
	  }
	  while(imagenTexto.includes(" ") ){
		  imagenTexto = imagenTexto.replace(" ", "")
	  }
	  return imagenTexto;
	}

function enviaImagenElemento(idImagenForm, idElemento){
	
	limpiaAlerta(),
//		console.log("Comineza envio imagenBody:"+idImagenForm);
		console.log("Comineza envio idElemento:"+idElemento);
//		rfcCliente ="nuevo";
//		console.log("Comineza envio rfcCliente:"+rfcCliente);
		var alerta="";
		 var form = $('#imagenObjetoQRDForm')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
        console.log(data);
		  $.ajax({
			url: "imageUploadElemento/"+idElemento,
		    type: "POST",
		    data: new FormData($("#imagenObjetoQRDForm")[0]),
//		    data: data,
//		    data: new FormData($("#formImagenLogoCliente")[0]),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
		    success: 	function(data){
		    	if(data.codigo===0){
		    		if(data.codigo===0){
		  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
		  			  $(alerta).insertAfter($('.alerta'));
		  			  console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alerta'));
		  	    		console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
				  $(alerta).insertAfter($('.alerta'));
		  	console.log("envio error");
		    }
		  });
}


function arrayToString(arrayData){
	var cadena="";
	for(a in arrayData){
		var elemento = arrayData[a];
		cadena = cadena + elemento.imagen + "--" + elemento.titulo + "--" + elemento.descripcion + "++";
	}
	cadena = cadena.slice(0, (cadena.length-2));
//	console.log(cadena)
	
	return cadena;
}

function transformaElementosMemoria(elementos){
	var tmpArray = elementos.split("++");
	var elementosArray = new Array();
	for(a in tmpArray){
		var tmpArray2 = tmpArray[a].split("--");
		var elemento = {
				imagen : tmpArray2[0],
				titulo : tmpArray2[1],
				descripcion : tmpArray2[2]
		};
		elementosArray.push(elemento);
	}
	$arrayElementosTotal = elementosArray;
	jsonQRD = $arrayElementosTotal;
	showCarrusel(elementosArray);	
}
