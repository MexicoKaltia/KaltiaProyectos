/*
 * funciones de table Genericas
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

 function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalRegistro">',
	      '<i class="fa fa-2x fa-user-edit"></i>',
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
	  

	  function alerta(){
//	  	alert("prueba js invocada desde html");
	  }
	 
	  
	
$(document).ready(function(){
		
	/*
	 * Carga la Tabla inicial
	 */
	
	console.log($data);
	
	$.statusInstructor="";
	$.statusCurso="";
	$.listFechas= new Array();
	
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		    	//console.log(JSON.stringify(row));
//		      alert('You click like action, row: ' + JSON.stringify(row.regionCliente.nombreRegion) + ' ' + JSON.stringify(row.regionCliente.idRegion));
		      $('#idCliente').val(row.idCliente);
		      $('#nombreCortoCliente').val(row.nombreCortoCliente);
		      $('#nombreCompletoCliente').val(row.nombreCompletoCliente);
		      $('#emailCliente').val(row.emailCliente);
		      $('#telefonoCliente').val(row.telefonoCliente);
		      $('#rfcCliente').val(row.rfcCliente);
		      $('#idRegionCliente').append('<option value="'+row.regionCliente.idRegion+'" selected >'+row.regionCliente.nombreRegion+'</option>');
		      $('#idVendedorCliente').append('<option value="'+row.vendedorCliente.idVendedor+'" selected >'+row.vendedorCliente.nombreVendedor+'</option>');
		      $('#domicilioCliente').val(row.domicilioCliente);      
		      $('#nombreContactoRecibeCliente').val(row.nombreContactoRecibeCliente);
		      $('#googleMapsCliente').val(row.googleMapsCliente);
		      
		      $('#seccionPautaOperativa').html(row.pautaOperativaCliente);
		      $('#seccionPautaEntregable').html(row.pautaEntregableCliente);
		      
		      $('#reglasAccesoCliente').val(row.reglasAccesoCliente);
		      $('#documentosAccesoCliente').val(row.documentosAccesoCliente);
		      $('#materialDidacticoCliente').val(row.materialDidacticoCliente);
		      $('#pautaGeneralCliente').val(row.pautaGeneralCliente);
		      $('#representanteEmpresaCliente').val(row.representanteEmpresaCliente);
		      $('#representanteTrabajadorCliente').val(row.representanteTrabajadorCliente);
		      $('#informacionPaqueteriaCliente').val(row.informacionPaqueteriaCliente);
		      $('#imagenLogoClienteH').val(row.imagenLogoCliente);
		      $('#imagenLogoClientePrev').attr('src', "/uploads/img/"+row.rfcCliente+"/"+row.imagenLogoCliente);
		      $('#imagenLogoClientePrev').attr('alt', row.imagenLogoCliente);
		      $('#archivosClienteH').val(row.archivosCliente);
		      $('#archivosClientePrev').attr('src', "/uploads/file/"+row.rfcCliente+"/"+row.archivosCliente);
		      $('#archivosClientePrev').attr('alt', row.archivosCliente);

		      $("#linkFile").attr('href', '/uploads/file/'+row.rfcCliente+'/'+row.archivosCliente)
		      $("#linkFile").html('<b>'+row.archivosCliente+'</b>');
		      
		      $("#linkImg").attr('href', '/uploads/img/'+row.rfcCliente+'/'+row.imagenLogoCliente)
		      $("#linkImg").html('<b>'+row.imagenLogoCliente+'</b>');
	      
		    }
		   }
	
	window.operateEventsUpdateCurso = {
		    'click .like': function (e, value, row, index) {
		    	
		      $('#idCurso').val(row.idCurso);
		      $('#nombreCurso').val(row.nombreCurso);
		      $('#listInstructores').multiSelect({
				  selectableHeader: "<div class='custom-header'>Instructores</div>",
				  selectionHeader: "<div class='custom-header'>Instructores Participantes</div>"
				  });
		      $('#listInstructores').multiSelect('deselect_all');
		      const $instructores = $instructoresTotal;
		      var $cursoInstructor = row.instructores;
		      var $instructoresParticipantes =[];
		      
		      $($cursoInstructor).each(function(index, element){
		    	  $($instructores).each(function(index2, element2){
	    			  $('#listInstructores').multiSelect('addOption', {
	    				  value: element2.idInstructor,
	    				  text: element2.nombreInstructor
	    			  });

		    		  if(element === element2.idInstructor){
		    			  $instructoresParticipantes.push(element2.idInstructor.toString());
		    		  }
		    	  })

		      });
			  $('#listInstructores').multiSelect('select', $instructoresParticipantes);
			  
		      $('#notaCurso').val(row.notaCurso);
		      
		    }
		   }

	
	window.operateEventsUpdateInstructor = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' +JSON.stringify(row));
//		    	//console.log(row);
		      $('.tmp').remove();
		      $('#idInstructor').val(row.idInstructor);
		      $('#nombreInstructor').val(row.nombreInstructor);
		      $('#regionInstructor').append('<option class="tmp" value="'+row.regionInstructor.idRegion+'" selected >'+row.regionInstructor.nombreRegion+'</option>');
		      $('#emailInstructor').val(row.emailInstructor);
		      $('#emailGmailInstructor').val(row.emailGmailInstructor);
		      $('#listCursoInstructor').multiSelect({
				  selectableHeader: "<div class='custom-header'>Cursos</div>",
				  selectionHeader: "<div class='custom-header'>Cursos Asignados</div>"
				  });
		      $('#listCursoInstructor').multiSelect('deselect_all');
		      const $cursos = $cursosTotal;
		      var $instructorCursos = row.cursosInstructor.replace('[','').replace(']','').replace(' ','').split(',');
//		      $instructorCursos = $instructorCursos.split(',');
//		      //console.log($instructorCursos);
		      var $cursosAsignados =[];
		      
		      $($instructorCursos).each(function(index, element){
		    	  element = element.replace(' ','');
		    	  element = (element * 1);
		    	  $($cursos).each(function(index2, element2){
	    			  $('#listCursoInstructor').multiSelect('addOption', {
	    				  value: element2.idCurso,
	    				  text: element2.nombreCurso
	    			  });

		    		  if(element === element2.idCurso){
		    			  $cursosAsignados.push(element2.idCurso.toString());
		    		  }
		    	  })

		      });
			  $('#listCursoInstructor').multiSelect('select', $cursosAsignados);
			  var nombreOperador ="";
			  for(var a in $operadores){
				  var operador = $operadores[a];
				  if(operador.idUsuario === row.idOperacion){
					  nombreOperador = operador.nombreUsuario;   
				  } 
			  }
		      $('#idOperacion').append('<option class="tmp" value="'+row.idOperacion+'" selected >'+nombreOperador+'</option>');
			  $('#fechas').empty();
			  $('#fechasNuevas').empty();
			  $('#listaFechas').empty();
			  $('#listFechasAct').remove();
				if(row.listFechas ){
					var fechas="<div class='alert alert-warning alert-dismissible' id='listFechasAct' role='alert'>Fechas de Ausencia Actuales:<ul id='listaFechasAct'></ul></div>";
					$(fechas).insertAfter($('#fechas'));
					var arreglo = stringToList(row.listFechas);
					var arregloOrdenado = new Array();
					arregloOrdenado = arreglo.sort(function(a, b){return new Date(a) - new Date(b)});//orderFecha(arreglo);
    				  var a = 0;
					$(arregloOrdenado).each(function(index, element){
					  var elementDate = new Date(element);
					  var ausencia = transformaDia(elementDate);
					  if(ausencia.toString() !== ""){
						  var espacio = sumaEspacio(ausencia.toString());
						  $('#listaFechasAct').append("<li id='li"+a+"'>Ausencia Fecha : <span style='font-family: Lucida Console, monospace; '><b> "+ ausencia.toString() +".</b>"+espacio.toString()+"</span><a id='"+element+"' idCount='"+a+"'  onclick=eliminarFecha(this); class='mb-2 mr-2 badge badge-warning'> Eliminar</a></li>");
						  a++;
						  $.listFechas.push(element);
					  }
				    });
//					$('#listFechas').val(row.listFechas);
					$('#listFechas').val($.listFechas);
				}
				
			  $('#statusInstructor').val("Actualizado");
		      $('#notaInstructor').val(row.notaInstructor);
		      $('#firmaInstructorH').val(row.firmaInstructor);
//		      $('#firmaInstructor').val(row.firmaInstructor);
		      $('#firmaInstructorPrev').attr('src', "/firmainstructor/"+row.idInstructor+"/"+row.firmaInstructor);
		      $('#firmaInstructorPrev').attr('alt', row.firmaInstructorH);
		      $('#statusInstructorbtn').html(row.statusInstructor);
		      
		    }
		   }
	
	window.operateEventsUpdateVendedor = {
			
		    'click .like': function (e, value, row, index) {
		    	//console.log(row);
		      $('#idVendedor').val(row.idVendedor);
		      $('#nombreVendedor').val(row.nombreVendedor);
		      $('#emailVendedor').val(row.emailVendedor);
		      $('#emailGmailVendedor').val(row.emailGmailVendedor);
		      $('#statusVendedor').val("Actualizado");
		      $('#notaVendedor').val(row.notaVendedor);
		      $('#porcentajeVendedor').val(row.porcentajeVendedor);
		      $('#statusVendedorbtn').html(row.statusVendedor);
		    }
		   }
	
	window.operateEventsUpdateUsuario = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' +JSON.stringify(row));
		      $('#idUsuario').val(row.idUsuario);
		      $('#usernameUsuario').val(row.usernameUsuario);
		      $('#passwordUsuario').val(row.passwordUsuario);      
		      $('#perfilUsuario').val(row.perfilUsuario);
		      $('#nombreUsuario').val(row.nombreUsuario);
		      $('#emailUsuario').val(row.emailUsuario);
		      $('#notaUsuario').val(row.notaUsuario);
		      $('#statusUsuariobtn').html(row.statusUsuario);
		    }
		   }

	
	window.operateEventsDelete = {
	    'click .remove': function (e, value, row, index) {
	    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
	    	$('#clientesTable').bootstrapTable('remove', {
	        field: 'idCliente',
	        values: [row.idCliente]
	      });
	      //console.log(row);
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }
	
	$('#clientesTable').bootstrapTable({data : $data})
	$('#cursosTable').bootstrapTable({data : $data})
	$('#instructoresTable').bootstrapTable({data : $data})
	$('#vendedoresTable').bootstrapTable({data : $data})
	$('#usuariosTable').bootstrapTable({data : $data})

	
/*
 * 
 * 
 * Rest jquery
 * 
 * 
 */
	
	
	$('#btnSaveRegistro').click(function(){
		limpiaAlerta();
		//console.log($('#idUserEmpresa').val());

	});
	
	$('#btnBorrarAusencia').click(function(){
		arrayFechas = [];
		//console.log("fechas Asusentes");
		//console.log($('#listFechas').val());
		$('#listFechas').val("");
		//console.log($('#listFechas').val());
		$('#Fechas').empty();
		$('#listaFechas').empty();
	    $('#listFechasAct').empty();
	    
//	    $('#listFechas').remove("");
	});
	
	
	
	/*
	 * funciones
	 */
	function eliminaFechas(){
		
	}
	
	

	function stringToList(cadena){
		return cadena.split(";");
	}
		
	$('#btnEliminaListaAusencia').click(function(){
		//console.log("HelloW");
		//console.log(this.val());
	})
}); // Fin documento


function checkStatusUsuario(){
	$('#statusUsuariobtn').html("Usuario Baja");
	$('#statusUsuariobtn').removeClass("btn-info");
	$('#statusUsuariobtn').addClass("btn-danger");
	var r = confirm("Seguro de dar de baja al Usuario, esta operacion no se puede reversar, tendría que dar de Alta Usuario nuevo.")
	if(r){
		$('#statusUsuario').val("Baja");
	}
	
}

function checkStatusVendedor(){
	$('#statusVendedorbtn').html("Vendedor Baja");
	$('#statusVendedorbtn').removeClass("btn-info");
	$('#statusVendedorbtn').addClass("btn-danger");
	var r = confirm("Seguro de dar de baja al Vendedor, esta operacion no se puede reversar, tendría que dar de Alta Vendedor nuevo.")
	if(r){
		$('#statusVendedor').val("Baja");
	}
	
}