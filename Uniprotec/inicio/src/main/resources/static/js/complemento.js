const URL = "http://31.220.63.183:8014/";
const CONT = 'oauth/token';
const CRUD = 'crud/';
const USERNAME = 'angularapp';
const PASSWORD = '12345';

	$('#loginButon').click(function(){
		console.log($('#username').val());
	     
        registroJson = { 
            "username": $('#username').val(),
			"password": $('#password').val()
        }
        console.log(registroJson);

    $.ajax({
          'url': URL + CONT,
          dataType: 'json',
          type: 'POST',
          contentType: "application/json",
          data: JSON.stringify(registroJson),
          headers: {  'Access-Control-Allow-Origin': URL, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER',
          "Content-Type": "application/x-www-form-urlencoded",
          "Authorization": "Basic " + btoa(USERNAME + ":" + PASSWORD)
          },
          crossDomain: false,
          success: 	function(data){					  
                //   avisaAlerta(data);
                  console.log(data);
            },
          error: function(){
            console.log(data);
          }
        });
		
	});

$('#CursoAlta').click(function(){
    // limpiaAlerta();
    valoresRegistro = $('#validationCustom01').val();
     
        registroJson = { 
            "name": valoresRegistro
        }
        console.log(registroJson);

    $.ajax({
          'url': URL + CRUD + 'curso',
          dataType: 'json',
          type: 'POST',
          contentType: "application/json",
          data: JSON.stringify(registroJson),
          headers: {  'Access-Control-Allow-Origin': URL, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER',
          "Content-Type": "application/x-www-form-urlencoded",
          "Authorization": "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2RlIjoyMDAsInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTU4Mzg4NjgyNiwibWVzc2FnZSI6IkxvZ2luIENvcnJlY3RvIiwiZmllbGRzIjp7InVzZXIiOnsibmFtZSI6IkFkbWluIiwiaWQiOjIsInVzZXIiOiJVbm8iLCJlbWFpbCI6IkFkbWluQHVuby5jb20iLCJtb2R1bGVzIjpbeyJpZCI6MSwibmFtZSI6IkFETUlOSVNUUkFDSU9OIiwic3VibW9kdWxlcyI6W3siaWQiOjQsIm5hbWUiOiJDbGllbnRlcyJ9LHsiaWQiOjUsIm5hbWUiOiJVc3VhcmlvcyJ9LHsiaWQiOjYsIm5hbWUiOiJJbnN0cnVjdG9yZXMifV19LHsiaWQiOjIsIm5hbWUiOiJBR0VOREEiLCJzdWJtb2R1bGVzIjpbeyJpZCI6MSwibmFtZSI6IkFzaWduYWNpw7NuIn0seyJpZCI6MiwibmFtZSI6Ik1vZGlmaWNhY2nDs24ifV19XX19LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImp0aSI6ImYwNWFmYmIyLTgyMzktNGEyYS1iMDI2LWQ5NjJlNzk1NjA2OSIsImNsaWVudF9pZCI6ImFuZ3VsYXJhcHAiLCJzdGF0dXMiOjB9.DEuZQVB1LO_rWqWau8YiExUY1x1WCqkeYhIkJO5QLEQslHYB0Vr5eGmJi4k46gmdy-2TEtQrwe94Sjh79_xSNtvsz3jn6eEUf6noilHBADjpOzL_81veVEranMrJV7iT3tpfUDxeDgpjYMZ6PI2Mo5vyk741pG9oU1MbdHhiMZu-4QCbeMDZY6ZTRTTPp_GByIuribaX6ALGKhXAQVjldf4D7qO9ppzzGrO8eFZ1TuBeYU0aHxz8PoYf7yFGMjnH5Scr6bxexBOFEVZwLQxE0QSEV0CQMN-eXGA22NrjPbaKujNtjcvU_QNQ3vong8OlT6-sV9iFa8EmFMB1yNZKTQ"
       },
          crossDomain: false,
          success: 	function(data){					  
                //   avisaAlerta(data);
                  console.log(data);
            },
          error: function(){
            console.log(data);
          }
        });
});
