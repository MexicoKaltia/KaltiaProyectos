$(document).ready(function() {
	
//	console.log(usuarioAudiencia);
//	console.log(modulos);
//	console.log(moduloCurso);

	var arrayUsuarioModulos = new Array();
	arrayUsuarioModulos = stringToArrayAudiencia(usuarioAudiencia.usuarioAudienciaModulos);
	for(a in arrayUsuarioModulos){
		var idModulo =  arrayUsuarioModulos[a];
		for(e in modulos){
			if((idModulo*1) === (modulos[e].idModuloDidactico)){
				var modulo = modulos[e];
				var elemento = '<div class="col-sm align-items-center elemento">\
				<img src="\\uploads\\img\\'+modulo.moduloDidacticoIdImagen+'\\'+modulo.moduloDidacticoImagen+'" alt=""  style="max-width: 12.0rem; max-height: 12.0rem"/>\
					  <div class="card-body elemento">\
					    <h4 class="text-center nombre2">'+modulo.moduloDidacticoDescripcion+'</h4>\
					    <h6 class="text-center nombre3">'+reformatNombreCurso(usuarioAudiencia.usuarioAudienciaNombreEvento)+'</h6>\
					    <div class="align-items-center">\
						    <a href="/accesoModulo/'+getModuloCurso(modulo.idModuloDidactico, usuarioAudiencia.usuarioAudienciaidCurso)+'">\
						    	<button type="button" class="btn btn-success">Acceso Modulo</button>\
						    </a>\
					    </div>\
					  </div>\
					</div>';
				$('#accesosDirectos').append(elemento);
			}
		}
	}
	
//	'+getImage(modulo.moduloDidacticoIdImagen, modulo.moduloDidacticoImagen)+'
    // fin de documento
})

function stringToArrayAudiencia(str){
	var array = new Array();
	if(str){
		var tmp = str.split(","); 
		for(i in tmp){
			if(tmp[i] > 0){
				array.push(tmp[i]);
			}
		}
		array = array.sort(function(a, b){return a-b});
//		console.log(array);
	}
	return array;
	
}

function reformatNombreCurso(nombreEvento){
	var tmp = nombreEvento.split("?");
	return tmp[1]+" - "+tmp[2];
}

function getNombreEmpresa(cadena){
	var tmp = cadena.split("?");
	return tmp[2];
}

function getModuloCurso(idModulo, idCurso){
//	console.log(idModulo+"-"+ idCurso);
	var array1 = new Array();
	for(a in moduloCurso){
		if((moduloCurso[a].idModuloDidactico*1) === (idModulo*1)){
			array1.push(moduloCurso[a]);
		}
	}
//	console.log(array1);
	for(e in array1){
		if((array1[e].idCurso*1) === (idCurso*1)){
//			console.log(array1[e].idModuloCurso)
			return array1[e].idModuloCurso;
		}
	}
	return null;
}

function getImage(moduloDidacticoIdImagen, moduloDidacticoImagen){
	$.ajax({
	    type: "GET",
	    url: '/uploads/img/'+moduloDidacticoIdImagen+'/'+moduloDidacticoImagen,
	    datatype: "image",
	    success: function(data) {
	    	console.log("exito")
//	        debugger
//	        $('#CaptchaImg').attr('src', data);
	        return data;
	    },
	 error: function(){
		  data = {
				  codigo : "99",
				  mensaje : "Error al actualizar Modulo"
		  }
		  console.log("error")
	  }
	});
}