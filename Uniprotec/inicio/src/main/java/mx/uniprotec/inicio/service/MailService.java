package mx.uniprotec.inicio.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.inicio.entity.MailVO;
import mx.uniprotec.inicio.entity.StatusVO;

@Service
public class MailService implements IMailService{
	
	private static Logger log = LoggerFactory.getLogger(MailService.class);

	public MailService() {	}
	
	
	@Override
	public StatusVO mailServicePreCorreo(AsignacionModelo asignacion) {
		
		MailVO mailVO = new MailVO();
		String staffDestino = "kaltiaservicios@gmail.com";
		String referencia = "www.control-uniprotec.com";
		String nombreBoton = " Revisar expediente cliente y confimar asignación. ";
		
		mailVO.setAsuntoMail("Resumen de Asignacion : "+asignacion.getIdAsignacionLogica());
		mailVO.setBodyMail(body(asignacion, staffDestino, referencia, nombreBoton));
		mailVO.setMensajeMail("\\uniprotec\\templates\\PlantillaCorreo.html");
		mailVO.setDestinatarioMail(staffDestino);
		mailVO.setAsignacionMail(asignacion);
		
		StatusVO statusVO = mailServiceGeneraCorreo(mailVO);
		
		
		return statusVO;

	}

	private String body(AsignacionModelo asignacion, String staffDestino, String referencia, String nombreBoton) {
		
		String body = 			
				"<div class='col-md-12'>" + 
				"							<h3 class='text-left'>" + 
				"								Buen Dia "+ staffDestino + " </h3>" + 
				"							<p class='text-left text-primary lead'>" + 
				"								El presente correo tiene la finalidad de notificar: <strong>El número de asignación : <strong>"+ asignacion.getIdAsignacionLogica() +".</strong>" + 
				"							</p>" + 
				"							<p class='text-left text-primary lead'>" + 
				"								Con los siguientes datos: " + 
				"							</p>" + 
				"							<div class='card text-white bg-info'>" + 
				"								<h5 class='card-header'>" + 
				"									Asignación "+asignacion.getIdAsignacionLogica()+"" + 
				"								</h5>" + 
				"								<div class='card-body'>" + 
				"									<h5 class='card-title'>Resumen de asignación</h5>" + 
				"                                               <ul class='list-group'>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Cliente : "+ asignacion.getClienteAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Curso : "+ asignacion.getCursoAsignacion()+"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Instructor : "+ asignacion.getInstructorAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Fecha : "+ asignacion.getFechaAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Horario : "+ horario(asignacion.getHorarioAsignacion()) +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Participantes : "+ asignacion.getParticipantesAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Nivel : "+ asignacion.getNivelAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Observaciones : "+ asignacion.getObservacionesAsignacion() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Status :  "+ status(asignacion.getStatusAsignacion()) +"</li>" +
				"                                                   <li class='list-group-item list-group-item-info'>Documentos Evento: "+ documentos(asignacion.getArchivosAsignacion()) +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Mapa de Ubicacion : "+ mapaGoogle(asignacion.getIdClienteAsignacion()) +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'>Nombre de Ventas : "+ asignacion.getUserCreateAsignacionTexto() +"</li>" + 
				"                                                   <li class='list-group-item list-group-item-info'><a href='"+ referencia +"' class='btn btn-info' role='button'> " + nombreBoton + "</a></li>" + 
				"                                               </ul>" + 
				"								</div>" + 
				"								<div class='card-footer'>" + 
				"									" + 
				"								</div>" + 
				"							</div>" + 
				"						</div>"+
				"</div>\r\n" + 
				"					<br>\r\n" + 
				"					<br>\r\n" + 
				"					<div class='row'>\r\n" + 
				"						<div class='col-md-12'>		 \r\n" + 
				"							<address>\r\n" + 
				"								 <strong>Uniprotec.</strong><br /> Domicilio <br /> Santiago de Queretaro<br /> <abbr title='Phone'>P:</abbr> (123) 456-7890\r\n" + 
				"							</address>\r\n" + 
				"						</div>\r\n" + 
				"						<p class='text-left text-primary lead'><em> <small>Conficencial datos de correo.</small></em></p>\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='col-md-2'>\r\n" + 
				"				           <a href='vendedor.html'><button type='button' class='btn center btn-primary btn-sm'>Visitar Plantilla Vendedor</button></a>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"<!--\r\n" + 
				"<script scr='js/jquery.min.js'></script>\r\n" + 
				"<script scr='js/bootstrap.min.js'></script>\r\n" + 
				"-->\r\n" + 
				"<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>\r\n" + 
				"<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>\r\n" + 
				"<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		return body;
	}
	
			

	private String mapaGoogle(Long idClienteAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private String documentos(String archivosAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private String status(String statusAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private String horario(String horarioAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private StatusVO mailServiceGeneraCorreo(MailVO mailVO) {
		
		StatusVO statusVO = new StatusVO();
		 
		 log.info("Correo Destino: "+mailVO.getDestinatarioMail());
	  // El correo gmail de envío
	  String correoEnvia = "notificacion@control-uniprotec.com";
	  String claveCorreo = "Uniprotec2020#";
	 
	  // La configuración para enviar correo
	  Properties properties = new Properties();
//	  properties.put("mail.smtp.host", "smtp.gmail.com");
	  properties.put("mail.smtp.host", "smtp.hostinger.com");
	  properties.put("mail.smtp.starttls.enable", "true");
	  properties.put("mail.smtp.port", "587");
	  properties.put("mail.smtp.auth", "true");
	  properties.put("mail.user", correoEnvia);
	  properties.put("mail.password", claveCorreo);
	 
	  // Obtener la sesion
	  Session session = Session.getInstance(properties, null);
	  session.setDebug(true);
	 
	  try {
	   // Crear el cuerpo del mensaje
	   MimeMessage mimeMessage = new MimeMessage(session);
	 
	   // Agregar quien envía el correo
	   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Uniprotec"));
	 
	   // Los destinatarios
	   InternetAddress[] internetAddresses = { new InternetAddress(mailVO.getDestinatarioMail()) };
	 
	   // Agregar los destinatarios al mensaje
	   mimeMessage.setRecipients(Message.RecipientType.TO,internetAddresses);
	 
	   // Agregar el asunto al correo
	   mimeMessage.setSubject(mailVO.getAsuntoMail());
	 
	   // Crear el multipart para agregar la parte del mensaje anterior
	   Multipart multipart = new MimeMultipart();
	 
	   // Leer la plantilla
//	   InputStream inputStream = getClass().getResourceAsStream(mailVO.getMensajeMail());
//	   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	   BufferedReader bufferedReader = new BufferedReader(new FileReader(mailVO.getMensajeMail()));
	 
	   // Almacenar el contenido de la plantilla en un StringBuffer
	   String strLine;
	   StringBuffer msjHTML = new StringBuffer();
	   while ((strLine = bufferedReader.readLine()) != null) {
	    msjHTML.append(strLine);
	   }
	   msjHTML.append(mailVO.getBodyMail());
	 
	   // Url del directorio donde estan los archivos adjuntos
	   String urlImagenes = "\\uniprotec\\asignacion\\"+mailVO.getAsignacionMail().getIdAsignacionLogica()+"\\";//"C:\\Kaltia\\KaltiaWorkbench\\KaltiaMail\\src\\images\\";
	   File directorio = new File(urlImagenes);
	    
	   // Obtener los nombres de las imagenes en el directorio
	   String[] imagenesDirectorio = directorio.list();
	 
	   // Creo la parte del mensaje HTML
	   MimeBodyPart mimeBodyPart = new MimeBodyPart();
	   mimeBodyPart.setContent(msjHTML.toString(), "text/html");
	 
	   // Validar que el directorio si tenga las imagenes
	   if (imagenesDirectorio != null) {
	    for (int count = 0; count < imagenesDirectorio.length; count++) {
	 
	     MimeBodyPart imagePart = new MimeBodyPart();
	     imagePart.setHeader("Content-ID", "<" + imagenesDirectorio[count].toString() + ">");
	     imagePart.setDisposition(MimeBodyPart.INLINE);
	     imagePart.attachFile(urlImagenes + imagenesDirectorio[count].toString());
	     multipart.addBodyPart(imagePart);
//	     System.out.println("nombre de las imagenes : "+imagenesDirectorio[count].toString());
	    }
	   } else {
	    System.out.println("No hay archivos en el directorio");
	   }
	 
	   // Agregar la parte del mensaje HTML al multiPart
	   multipart.addBodyPart(mimeBodyPart);
	 
	   // Agregar el multipart al cuerpo del mensaje
	   mimeMessage.setContent(multipart);
	 
	   // Enviar el mensaje
	   Transport transport = session.getTransport("smtp");
	   transport.connect(correoEnvia, claveCorreo);
	   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	   transport.close();
	 
	   statusVO.setCodigo(0);
	   statusVO.setMensaje("Correo Enviado");
	   log.info("Correo enviado");
	  } catch (Exception ex) {
		  statusVO.setCodigo(99);
		  statusVO.setMensaje("Correo NO Enviado");
		  ex.printStackTrace();
		  log.info("Correo NO enviado");
	  }
	  
	  return statusVO;
	  
	 }



	
}
