$(document).ready(function() {
	
	// -  console.log(1);
//	// -  console.log(cursos);
//	// -  console.log(modulos);
//	// -  console.log(moduloCurso);
//	// -  console.log(cursosControl);
	
	$('#divModulosActivos').empty();
	var modulosActivos = new Array();
	cursos = stringToArray(cursos);
	
	for(a in cursos){
		var curso = cursos[a]; 
		for(e in moduloCurso){
			var moduloCurs = moduloCurso[e];
			var idCurso = (moduloCurs.idCurso*1); //// -  console.log(idCurso); 
			if(idCurso === (curso*1)){
				for(i in modulos){ 
					var modulo = modulos[i];
					if((modulo.idModuloDidactico*1)===(moduloCurs.idModuloDidactico*1)){
						var moduloImagen = '\\uploads\\img\\'+modulo.moduloDidacticoIdImagen+'\\'+modulo.moduloDidacticoImagen;
						var moduloNombre = modulo.moduloDidacticoNombre;
//						// -  console.log(moduloNombre);
						var cursoNombre = findCursoNombre(idCurso);
						var elemento = '<div class="col-5 modulo flex-containerE">\
							<div class=""><img src="'+moduloImagen+'" alt="" class="img-responsive" style="max-width: 12.0rem; max-height: 12.0rem"/></div>\
							<div class="">\
						      <h4 class="text-center nombre5">'+moduloNombre+'</h4>\
						      <h6 class="text-center nombre3">'+cursoNombre+'</h6>\
						    </div>\
						    <div class="align-items-center">\
							   <a href="/accesoModulo?idModuloCurso='+getModuloCurso(modulo.idModuloDidactico, idCurso)+'">\
							     <button type="button" class="btn btn-success" id="btnAccesoModulo" >Acceso Modulo</button>\
	  						   </a>\
							</div>\
						</div>';
						modulosActivos.push(elemento);
						$('#divModulosActivos').append(elemento);
//						// -  console.log(elemento);
					}
				}
			}
		}
	}
	
	
    // fin de documento
})

function getModuloCurso(idModulo, idCurso){
	var array1 = new Array();
	for(a in moduloCurso){
		if((moduloCurso[a].idModuloDidactico*1) === (idModulo*1)){
			array1.push(moduloCurso[a]);
		}
	}
	for(e in array1){
		if((array1[e].idCurso*1) === (idCurso*1)){
			return array1[e].idModuloCurso;
		}
	}
	return null;
}
	
function stringToArray(cadena){
	var arrayA = cadena.split(",");
	var arrayCursos = new Array();
	for(a in arrayA){
		var e = arrayA[a];
		e = e.replace("[","");
		e = e.replace(" ","");
		e = e.replace("]","");
		arrayCursos.push(e);
	}
	return arrayCursos;
}

function findCursoNombre(idCurso){
	for (a in cursosControl){
		if((idCurso*1) === (cursosControl[a].idCurso*1)){
//			// -  console.log(cursosControl[a].nombreCurso)
			return cursosControl[a].nombreCurso;
		}
	}
}