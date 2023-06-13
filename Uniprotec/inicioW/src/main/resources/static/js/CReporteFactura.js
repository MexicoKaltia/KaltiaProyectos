

console.log(datosEconomicos);

function operateFormatterUpdate(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalRegistro">',
      '<i class="fa fa-2x fa-user-edit"></i>',
      '</a>'
    ].join('')
  }
  


$(document).ready(function(){

	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
		    	console.log(JSON.stringify(row));
		      	      
		    }
		   }

	$('#facturaTable').bootstrapTable({data : datosEconomicos})
}); //fin jQuery

