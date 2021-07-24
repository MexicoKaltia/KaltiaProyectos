$(document).ready(function() {
/*
					 * Error Proceso
					 */

					var contarPrimerNivel = 0 ;
				    var contarSegundoNivel = 0;
				    var contarTercerNivel = 0;
				    
				    $('#seccionErrorProceso').each(function(){
				    	contarPrimerNivel++;
				    	$('#listPrimerNivel'+contarPrimerNivel).each(function(){
				    		contarSegundoNivel++;
				    	})
				    })
				    
				    $('#btnPrimerNivel').click(function(){
				        
				        $('#seccionErrorProceso').append('<div class="alert alert-ligth alert-dismissible fade show" role="alert"><ul id="listPrimerNivel'+contarPrimerNivel+'"></ul><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
				        
				        var primerNivel = $('#txtPrimerNivel').val();
				        $('#listPrimerNivel'+contarPrimerNivel).append('<li style="list-style-type: disc;" ><ul id="itemPrimerNivel'+contarPrimerNivel+'"><b>'+primerNivel+' </b></ul></li>');
				        $('#txtSegundoNivel').attr('disabled', false);
				        $('#btnPrimerNivel').attr('disabled', true);
				        $('#btnSegundoNivel').attr('disabled', false);
				        $('#txtPrimerNivel').val("");
				        $('#txtPrimerNivel').attr('disabled', true);
				        $('#agregarErrorProceso').attr('disabled', false);
				        contarPrimerNivel++;
				        contarSegundoNivel++;
				    })
				    
				    $('#btnSegundoNivel').click(function(){
				        var segundoNivel = $('#txtSegundoNivel').val();		        
				        if(segundoNivel.length > 0){
				            $('#itemPrimerNivel'+(contarPrimerNivel-1)).append('<li style="list-style-type: circle;"  ><ul id="itemSegundoNivel'+contarSegundoNivel+'">'+segundoNivel+' </ul></li>');
				            $('#txtPrimerNivel').attr('disabled', true);
				            $('#txtSegundoNivel').val("");
				            contarSegundoNivel++;
				        }
				    })
				    
				    $('#agregarErrorProceso').click(function(){
//				        //console.log("agregarInstruccion");
				        $('#btnPrimerNivel').attr('disabled', false);
				        $('#txtPrimerNivel').attr('disabled', false);
				        $('#btnSegundoNivel').attr('disabled', true);
				        $('#txtSegundoNivel').attr('disabled', true);
				        $('#agregarErrorProcesoFinal').attr('disabled', false);
				        $('#errorProceso1').val();
				        $('#errorProceso1').val($('#seccionErrorProceso').html());
				    })

				    
}); // fin de jquery