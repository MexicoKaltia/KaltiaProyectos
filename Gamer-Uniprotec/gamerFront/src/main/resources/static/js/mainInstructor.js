$(document).ready(function() {
	
	console.log(1);
//	console.log(cursos);
//	console.log(modulos);
//	console.log(moduloCurso);
//	console.log(cursosControl);
	
	$('#divModulosActivos').empty();
	var modulosActivos = new Array();
	cursos = stringToArray(cursos);
	
	for(a in cursos){
		var curso = cursos[a]; //console.log(curso);
		for(e in moduloCurso){
			var idCurso = (moduloCurso[e].idCurso*1); //console.log(idCurso); 
			if(idCurso === (curso*1)){
				for(i in modulos){ //console.log(modulos[i].idModuloDidactico*1);
					if((modulos[i].idModuloDidactico*1)===(moduloCurso[e].idModuloDidactico*1)){
						var moduloImagen = '\\uploads\\img\\'+modulos[i].moduloDidacticoIdImagen+'\\'+modulos[i].moduloDidacticoImagen;
						var moduloNombre = modulos[i].moduloDidacticoNombre;
//						console.log(moduloNombre);
						var cursoNombre = findCursoNombre(idCurso);
						var elemento = '<div class="col-sm-5 modulo "><div class="card-body center-block ">\
							<img src="'+moduloImagen+'" alt="" class="img-responsive" style="max-width: 12.0rem; max-height: 12.0rem"/>\
						      <h4 class="text-center nombre5">'+moduloNombre+'</h4>\
						      <h6 class="text-center nombre3">'+cursoNombre+'</h6>\
						      <div class="align-items-center">\
							     <button type="button" class="btn btn-info">Acceso </button>\
							  </div>\
							</div></div>';
						modulosActivos.push(elemento);
						$('#divModulosActivos').append(elemento);
//						console.log(elemento);
					}
				}
			}
		}
	}
	
	
    // fin de documento
})

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
//			console.log(cursosControl[a].nombreCurso)
			return cursosControl[a].nombreCurso;
		}
	}
}