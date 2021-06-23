$().ready(function(){
    $('[rel="tooltip"]').tooltip();

    $ventana_alto = $(window).height();
    $ventana_ancho = $(window).width();
//    alert("ventana:"+$ventana_alto+"-"+$ventana_ancho);
    


});

function rotate(element){

    var $card = $(element).closest('.flip-box-inner'); 
    if(!$(element).hasClass('bloqueo')){
	    if($card.hasClass('rotate')){
	        $card.removeClass('rotate');
	    } else {
	        $card.addClass('rotate');
	    }
    }
}