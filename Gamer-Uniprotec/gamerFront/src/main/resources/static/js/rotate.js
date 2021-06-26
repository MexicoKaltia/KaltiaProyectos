   $().ready(function(){
        $('[rel="tooltip"]').tooltip();

    });

    function rotateCard(btn){
    	if(!$(btn).hasClass('bloqueo')){
    		var $card = $(btn).closest('.card-container');
//          // -  console.log($card);
          if($card.hasClass('hover')){
              $card.removeClass('hover');
          } else {
              $card.addClass('hover');
          }
    	}
    }