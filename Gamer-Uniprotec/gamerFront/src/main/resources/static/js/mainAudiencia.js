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
				var idModuloDidactico = modulo.idModuloDidactico;
				var usuarioAudienciaidCurso=  usuarioAudiencia.usuarioAudienciaidCurso;
				var elemento = '<div class="col-sm align-items-center elemento">\
				<img src="\\uploads\\img\\'+modulo.moduloDidacticoIdImagen+'\\'+modulo.moduloDidacticoImagen+'" alt=""  style="max-width: 12.0rem; max-height: 12.0rem"/>\
					  <div class="card-body elemento">\
					    <h4 class="text-center nombre2">'+modulo.moduloDidacticoDescripcion+'</h4>\
					    <h6 class="text-center nombre3">'+reformatNombreCurso(usuarioAudiencia.usuarioAudienciaNombreEvento)+'</h6>\
					    <div class="align-items-center">\
					    <a href="/accesoModulo?idModuloCurso='+getModuloCurso(modulo.idModuloDidactico, usuarioAudiencia.usuarioAudienciaidCurso)+'">\
					    	<button type="button" class="btn btn-success" id="btnAccesoModulo'+idModulo+'" >Acceso Modulo</button>\
					    </a>\
					    </div>\
					  </div>\
					</div>';
				$('#accesosDirectos').append(elemento);
			}
		}
	}
//	
//	'+getImage(modulo.moduloDidacticoIdImagen, modulo.moduloDidacticoImagen)+'
//	<form id="formAccesoModulo'+idModulo+'" th:action="@{/accesoModulo}"  th:field="*{idModulocurso}"  method="post">\
//	<input type="hidden" name="idModuloCurso" th:field="*{idModulocurso}" value="'+getModuloCurso(modulo.idModuloDidactico, usuarioAudiencia.usuarioAudienciaidCurso)+'">\
//    	<button type="button" class="btn btn-success" id="btnAccesoModulo'+idModulo+'" onclick="btnSubmit(this)">Acceso Modulo</button>\
//    </form>\
	
    // fin de documento
	
})

//function btnSubmit(elemento){
//	var idElemento = $(elemento).attr("id");
//	idElemento = idElemento.replace("btnAccesoModulo","");
//	console.log(idElemento);
//	$('#formAccesoModulo'+idElemento).submit();
//}

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
//			$('#formAccesoModulo').submit();
			
//			console.log(array1[e].idModuloCurso)
			return array1[e].idModuloCurso;
//			$.ajax({
//			   	  url: "/accesoModulo?idModuloCurso="+array1[e].idModuloCurso,//+ context,//+finalJson.action+"/"+finalJson[1],
//			      dataType: 'json',
//				  type: 'POST',
//				  contentType: "application/json",
////				  data: JSON.stringify(finalJson),
//				  headers: {   'Access-Control-Allow-Methods': 'POST', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
//				  crossDomain: true,
//				  success: 	function(data){
////					  window.location = '/modulos?ejecucionI=true'; //			  window.location.reload();
////					  avisaAlerta(data)
//					},
//				  error: function(){
//					  data = {
//							  codigo : "99",
//							  mensaje : "Error al actualizar Modulo"
//					  }
////					  avisaAlerta(data);
//				  }
//				});
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