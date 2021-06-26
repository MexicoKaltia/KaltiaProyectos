/*
*	funciones table
*/

function operateFormatterUpdateInstructor(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar"  data-toggle="modal" data-target="#modalInstructor">',
      '<i class="fa fa-2x fa-user"></i>',
      '</a>'
    ].join('')
  }

function operateFormatterUpdateAudiencia(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar"  data-toggle="modal" data-target="#modalAudiencia">',
      '<i class="fa fa-2x fa-user"></i>',
      '</a>'
    ].join('')
  }

function operateFormatterUpdateModulo(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar"  data-toggle="modal" data-target="#modalModulo">',
      '<i class="fa fa-2x fa-microchip"></i>',
      '</a>'
    ].join('')
  }


  function operateFormatterDelete(value, row, index) {
	    return [
	      '<a class="remove" href="javascript:void(0)" title="Eliminar">',
	      '<i class="fa fa-2x fa-address-book">',
	      '</a>'
	    ].join('')
	  }

  
  function getDataInstructores(usuarios){
	  var usuariosInstructores = new Array();
	  for(i in usuarios){
		  var usuario = usuarios[i];
		  if(usuario.usuarioInstructor){
//			  // -  console.log(usuario)
			  usuariosInstructores.push(usuario);
		  }
	  }
	  return usuariosInstructores;
  }
  
  function getDataAudiencia(usuarios){
	  var usuariosAudencia = new Array();
	  for(i in usuarios){
		  var usuario = usuarios[i];
//		  // -  console.log(usuario)
		  if(usuario.usuarioAudiencia){
//			  // -  console.log(usuario)
			  usuariosAudencia.push(usuario);
		  }
	  }
	  return usuariosAudencia;
  }
  
  function getDataAudienciaRem(usuarios){
	  var usuariosAudencia = new Array();
	  for(i in usuarios){
		  var usuario = usuarios[i];
		  if(usuario.usuarioAudiencia){
			  var usuarioA = usuario.usuarioAudiencia;
			  var usuarioAE = usuarioA.usuarioAudienciaEvento;
			  usuarioAE = {
					  "id": usuarioA.username,
					  "status":usuarioA.status
			  }
			  
			  usuariosAudencia.push(usuario);
		  }
	  }
	  return usuariosAudencia;
  }
  
  
  
  $(document).ready(function() {
	  
	  /*
		 * funciones table
		 */
		
//		$dataInstructores = getDataInstructores(usuarios.usuarios);
//		$dataAudiencia = getDataAudiencia(usuarios.usuarios);
//		$dataAudienciaRem = getDataAudienciaRem(usuarios.usuarios);
		$dataModulos = modulos;
		
//		console.lcog(instructores.instructores);
		
		window.operateEventsUpdateInstructor = {
			    'click .like': function (e, value, row, index) {
//			      // -  console.log(JSON.stringify(row));
			    	$('#nombreInstructor').empty();
			    	$('#usernameInstructor').empty();
     		      $('#nombreInstructor').append("<h3 class='nombre'>"+row.nombre+"</h3");
			      $('#usernameInstructor').append("<h3 class='nombre'>"+row.username+"</h3");
			      $('#nombreInstructor').addClass("nombre");
			      $('#usernameInstructor').addClass("nombre");
			      var instructoresA = instructores.instructores;	      
			      var idCursosInstructor = new Array();
			      for(i in instructoresA){
			    	  var instructor = instructoresA[i];
			    	  if(instructor.usuarioInstructor.usernameUsuario  === row.username){
			    		  idCursosInstructor = getArraytoString(instructor.cursosInstructor);
			    	  }
			      }
//	    		  // -  console.log(idCursosInstructor);
			      var nombreCursosInstructor = new Array();
			      var cursosA = cursos.cursos;
			      $('#cursosInstructor').empty();
			      for(i in cursosA){
			    	  var curso = cursosA[i];
//			    	  // -  console.log(curso.idCurso+":"+curso.nombreCurso);
//			    	  // -  console.log(curso.instructores);
			    	  for(e in idCursosInstructor){
			    		  var idCursoA = idCursosInstructor[e]; 
//			    		  // -  console.log("idInstructor:"+idCursoA.idInstructor);
			    		  if((curso.idCurso*1) === (idCursoA*1)){
//			    			  // -  console.log(curso.idCurso+":"+curso.nombreCurso);
			    			  nombreCursosInstructor.push(curso.nombreCurso);
			    			  $('#cursosInstructor').append("<li>"+curso.nombreCurso+"</li>");
			    		  }
			    	  }
			      }
//			      // -  console.log(row.status);
			      $('#btnStatusInstructor').html(row.status);
			      $idUsuarioInstructor = row.id;
//			      // -  console.log(row);
//		      // -  console.log($idUsuarioInstructor);
			    }
			   }
		
		window.operateEventsUpdateAudiencia = {
			    'click .like': function (e, value, row, index) {
//			      // -  console.log('You click like action, row: ' + JSON.stringify(row));//+row.instructores.idInstructor+'  selected:'+row.instructores.nombreInstructor);
		    	$('#usernameAudiencia').empty();
		    	$('#cursosAudiencia').empty();
		    	$('#fechaAudiencia').empty();
		    	$('#modulosInstructor').empty();
		    	
 		      $('#fechaAudiencia').append("<h3 class='nombre'>"+row.usuarioAudiencia.usuarioAudienciaFecha+"</h3");
 		      $('#cursosAudiencia').append("<h3 class='nombre'>"+row.usuarioAudiencia.usuarioAudienciaNombreEvento+"</h3");
		      $('#usernameAudiencia').append("<h3 class='nombre'>"+row.username+"</h3");
		      
		      $('#cursosAudiencia').addClass("nombre");
		      $('#fechaAudiencia').addClass("nombre");
		      $('#usernameAudiencia').addClass("nombre");
		      
		      var modulos = new Array()
		      modulos = row.usuarioAudiencia.usuarioAudienciaModulos.split(",");
		      for(i in modulos){
		    	  $('#modulosInstructor').append("<li>"+modulos[i]+"</li>");
		      }
		      
//		      // -  console.log(nombreCursosInstructor);
		      $('#btnStatusAudiencia').html(row.status);
		      $idUsuarioAudiencia = row.id;
			    }
			   }
		
		window.operateEventsUpdateModulo = {
			    'click .like': function (e, value, row, index) {
			      // -  console.log('You click like action, row: ' + JSON.stringify(row));//+row.instructores.idInstructor+'  selected:'+row.instructores.nombreInstructor);
		    	
			    }
			   }
		
		window.operateEventsDelete = {
		    'click .remove': function (e, value, row, index) {
		    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
		    	$('#clientesTable').bootstrapTable('remove', {
		        field: 'idCliente',
		        values: [row.idCliente]
		      });
		      // -  console.log(row);
		      deleteUserEmpresa(row.idUserEmpresa);
		    }
		  }
		
		
//		$('#instructoresTable').bootstrapTable({data : $dataInstructores})
//		$('#audienciaTable').bootstrapTable({data : $dataAudiencia})
		$('#modulosTable').bootstrapTable({data : $dataModulos})

		 
		  function getArraytoString(cadena){
//			  // -  console.log(cadena)
			  var replace1 = cadena.replace('"','').replace('"','').replace(' ','');
			  replace1 = replace1.replace('[','');
			  replace1 = replace1.replace(']','');
			  replace1 = replace1.replace(' ','');
			  var newArray = new Array();
			  newArray = replace1.split(',');
			  return newArray;
		  }
	    // fin de documento
		
  });
  
 