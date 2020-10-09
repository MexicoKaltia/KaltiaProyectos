package mx.uniprotec.inicio.service;


import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Address;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.UserCorreo;
import mx.uniprotec.inicio.entity.MailVO;
import mx.uniprotec.inicio.entity.StatusVO;

@Service
public class MailService implements IMailService{
	
	
	private static Logger log = LoggerFactory.getLogger(MailService.class);
	private final String PLANTILLA_CORREO = "/uniprotec/templates/PlantillaCorreo.html"; //"\\uniprotec\\templates\\PlantillaCorreo.html";
	
	@Autowired
	IAplicacionService aplicacionService;

	public MailService() {	}
	
	
	@Override
	public StatusVO mailServicePreCorreo(AsignacionModelo asignacion, String token) {
		
		
		  List<String> INSTRUCTOR_PRE = new ArrayList<String>();
//		  INSTRUCTOR_PRE.add("kaltiaservicios@gmail.com");
//		  INSTRUCTOR_PRE.add("hugo.rivas@kaltiaservicios.tech");
//		  INSTRUCTOR_PRE.add("sanchez.olivier@hotmail.com");
		
		 List<String> STAFF_PRE = new ArrayList<String>();
//		STAFF_PRE.add("kaltiaservicios@gmail.com");
		STAFF_PRE.add("olivier.sanchez@uniprotec.net");
//		STAFF_PRE.add("olivier.sanchez201184@gmail.com");
//		STAFF_PRE.add("gasparinho@hotmail.fr");

		
//		log.info(asignacion.toString());
		
		String staffDestino ;
		String referenciaBase = "https://control-uniprotec.com/CAsignacionIC/"+asignacion.getIdAsignacion()+"/";
		String referencia;
		String nombreBoton ;
		String subTitulo;
		String[] envioCorreos = {"Instructor", "Staff"};
		StatusVO statusVO = new StatusVO();
		List<UserCorreo> usersCorreo = aplicacionService.usersCorreo(asignacion.getIdInstructorAsignacion(), asignacion.getUserCreateAsignacion(), token);
//		log.info(usersCorreo.toString());
		
		String correoGmailInstructor="";
		List<String> correoStaff = new ArrayList<String>();
		List<String> correoInstructor = new ArrayList<String>();
		correoStaff.clear();
		for(UserCorreo uc : usersCorreo) {
//			log.info(uc.toString());
			if(uc.getPerfil().equals("Instructor")) {
//				correoInstructor.add("uniprotec@kaltiaservicios.tech");
				correoInstructor.add(uc.getEmailUniprotec());
				correoInstructor.add(uc.getEmailGmail());
				correoGmailInstructor = uc.getEmailGmail();
			}else {
				correoStaff.add(uc.getEmailUniprotec());
			}
		}
		
		
		int i = 0;
		while (i < envioCorreos.length) {
//			log.info(envioCorreos[i]);
			if(envioCorreos[i].equals("Instructor")) {
				MailVO mailVO = new MailVO();		
				staffDestino = asignacion.getInstructorAsignacion();
				
				nombreBoton = " Revisar expediente cliente y confimar asignaci&oacute;n. ";
				subTitulo = "El presente correo tiene la finalidad de notificarle su nueva asignaci&oacute;n";
				referencia = referenciaBase.concat(asignacion.getIdInstructorAsignacion().toString());
				mailVO.setAsuntoMail("Resumen de Asignacion : "+asignacion.getIdAsignacionLogica());
				mailVO.setBodyMail(body(asignacion, staffDestino, referencia, nombreBoton, subTitulo));
				mailVO.setMensajeMail(PLANTILLA_CORREO );
				mailVO.setDestinatarioMailList(correoInstructor);
				log.info("Instructor : "+ mailVO.getDestinatarioMailList().toString());
//				mailVO.setDestinatarioMailList(INSTRUCTOR_PRE);
//				log.info("Instructor : "+ mailVO.getDestinatarioMailList().toString());
				
				mailVO.setAsignacionMail(asignacion);
				statusVO = mailServiceGeneraCorreo(mailVO);		
				
			}else if(envioCorreos[i].equals("Staff")) {
				MailVO mailVO = new MailVO();
				staffDestino = "Equipo Staff Uniprotec";
				referencia = referenciaBase.concat("0");
				nombreBoton = " Revisar expediente cliente";
				subTitulo = "El presente correo tiene la finalidad de notificar la nueva asignaci&oacute;n";
				
				mailVO.setAsuntoMail("Resumen de Asignacion : "+asignacion.getIdAsignacionLogica());
				
				mailVO.setBodyMail(body(asignacion, staffDestino, referencia, nombreBoton, subTitulo));
				
				mailVO.setMensajeMail(PLANTILLA_CORREO );
//				mailVO.setDestinatarioMailList(correoStaff);
//				log.info("Staff : "+ mailVO.getDestinatarioMailList().toString());
				mailVO.setDestinatarioMailList(STAFF_PRE);
				log.info("Staff : "+ mailVO.getDestinatarioMailList().toString());
				
				mailVO.setAsignacionMail(asignacion);
				statusVO = mailServiceGeneraCorreo(mailVO);
			}
			i++;
		}
		
		
		return statusVO;

	}
	
	@Override
	public void mailServicePreCorreoSustitucion(AsignacionModelo asignacion, String token, Long idInstructor) {
		  List<String> INSTRUCTOR_PRE = new ArrayList<String>();

		  String staffDestino ;
		String referencia;
		String nombreBoton ;
		String subTitulo;
		String[] envioCorreos = {"Instructor"};
		StatusVO statusVO = new StatusVO();
		List<UserCorreo> usersCorreo = aplicacionService.usersCorreo(idInstructor, asignacion.getUserCreateAsignacion(), token);
		
		String correoGmailInstructor="";
		List<String> correoInstructor = new ArrayList<String>();
		for(UserCorreo uc : usersCorreo) {
			if(uc.getPerfil().equals("Instructor")) {
//				correoInstructor.add("uniprotec@kaltiaservicios.tech");
				correoInstructor.add(uc.getEmailUniprotec());
				correoInstructor.add(uc.getEmailGmail());
				correoGmailInstructor = uc.getEmailGmail();
				String correoUniprotec = uc.getEmailUniprotec();
				log.info(correoGmailInstructor +":"+ correoUniprotec);
			}
		}
		
		
		int i = 0;
		while (i < envioCorreos.length) {
//			log.info(envioCorreos[i]);
			if(envioCorreos[i].equals("Instructor")) {
				MailVO mailVO = new MailVO();		
				staffDestino = asignacion.getInstructorAsignacion();
				
				
				subTitulo = "El presente correo tiene la finalidad de notificarle el cambio de Status del Evento : ";
				
				mailVO.setAsuntoMail("Resumen de Asignacion : "+asignacion.getIdAsignacionLogica());
				mailVO.setBodyMail(bodySustitucion(asignacion, staffDestino, subTitulo));
				mailVO.setMensajeMail(PLANTILLA_CORREO );
				mailVO.setDestinatarioMailList(correoInstructor);
				log.info("Instructor : "+ mailVO.getDestinatarioMailList().toString());

				mailVO.setAsignacionMail(asignacion);
				statusVO = mailServiceGeneraCorreo(mailVO);						
			}
			i++;
		}
	}


	

	private String body(AsignacionModelo asignacion, String staffDestino, String referencia, String nombreBoton, String subTitulo) {
		
		String body =	 "<div class='row'>" + 
				"<div class='col-md-12'>" + 
				"<h3 class='text-left' style='text-align: left;'>Buen D&iacute;a: "+ staffDestino +"</h3>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'>"+ subTitulo +": <b><strong>"+ asignacion.getIdAsignacionLogica() +"</strong></b>.</p>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'>Con los siguientes datos:</p>" + 
				"<div class='col-md-2'>" + 
				"<table style='height: 520px; width: 75%; border-collapse: collapse; border-style: inset; border-color: blue; background-color: #D6FBFC; margin-left: 20; margin-right: auto;' border='3'>" + 
				"<tbody>" + 
				 
				"<tr style='height: 53px;'>" + 
				"<td style='width: 100%; height: 53px;'>" + 
				"<b><h3  style='text-align: left;'>Resumen de asignaci&oacute;n</h3></b>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Cliente : <b>"+ asignacion.getClienteAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Curso : <b>"+ asignacion.getCursoAsignacion()+"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Instructor : <b>"+ asignacion.getInstructorAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Fecha : <b>"+fecha(asignacion.getFechaAsignacion())+"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" +
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Tipo de curso : <b>"+ asignacion.getTipoCursoAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" +
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li><b>"+ horario(asignacion.getHorarioAsignacion()) +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Participantes : <b>"+ asignacion.getParticipantesAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Nivel : <b>"+ asignacion.getNivelAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Observaciones : <b>"+ asignacion.getObservacionesAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul class='list-group'>" + 
				"<li class='list-group-item list-group-item-info' style='text-align: justify;'>Status : <b>"+ asignacion.getStatusAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul class='list-group'>" + 
				"<li class='list-group-item list-group-item-info' style='text-align: justify;'>Vendedor : <b>"+ asignacion.getUserCreateAsignacionTexto() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" +
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul class='list-group'>" + 
				"<li class='list-group-item list-group-item-info' style='text-align: justify;'><b><a href='"+ referencia +"'>"+ nombreBoton +"</a></b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"</tbody>" + 
				"</table><div class='card text-white bg-info'>" + 
				"<div class='card-body'></div>" + 
				"</div>" + 
				"</div>" + 
				"</div>" + 
				"<br /><br />" + 
				"<div class='row'>" + 
				"<div class='col-md-12' style='text-align: left;'><address><strong>Uniprotec.</strong><br/> Ambar 20 Misi&oacute;n Mariana , Corregidora, Quer&eacute;taro, 76903 <br /> Santiago de Quer&eacute;taro<br /><abbr title='Phone'> P:</abbr> + 52 - 4423341671</address></div>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'><em><small>Ha recib&iacute;do este correo electr&oacute;nico porque est&aacute; suscrito como usuario registrado del sistema control-uniprotec.com .</small></em></p>" + 
				"</div>" + 
				"</div>" + 
				"</body></html>";
		
		
		return body;
	}
	
	private String bodySustitucion(AsignacionModelo asignacion, String staffDestino,  String subTitulo) {
		
		String body =	 "<div class='row'>" + 
				"<div class='col-md-12'>" + 
				"<h3 class='text-left' style='text-align: left;'>Buen D&iacute;a: "+ staffDestino +"</h3>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'>"+ subTitulo +": <b><strong>"+ asignacion.getIdAsignacionLogica() +"</strong></b>.</p>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'>Con los siguientes datos:</p>" + 
				"<div class='col-md-2'>" + 
				"<table style='height: 520px; width: 75%; border-collapse: collapse; border-style: inset; border-color: blue; background-color: #D6FBFC; margin-left: 20; margin-right: auto;' border='3'>" + 
				"<tbody>" + 
				 
				"<tr style='height: 53px;'>" + 
				"<td style='width: 100%; height: 53px;'>" + 
				"<b><h3  style='text-align: left;'>Resumen de asignaci&oacute;n</h3></b>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Cliente : <b>"+ asignacion.getClienteAsignacion() +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Curso : <b>"+ asignacion.getCursoAsignacion()+"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul>" + 
				"<li>Fecha : <b>"+fecha(asignacion.getFechaAsignacion())+"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" +
				 
				"<tr style='height: 46px;'>" + 
				"<td style='width: 100%; height: 46px;'>" + 
				"<ul class='list-group'>" + 
				"<li class='list-group-item list-group-item-info' style='text-align: justify;'>Status : <b>"+ colorStatus(asignacion.getStatusAsignacion()) +"</b></li>" + 
				"</ul>" + 
				"</td>" + 
				"</tr>" + 
				 
				"</tbody>" + 
				"</table><div class='card text-white bg-info'>" + 
				"<div class='card-body'></div>" + 
				"</div>" + 
				"</div>" + 
				"</div>" + 
				"<br /><br />" + 
				"<div class='row'>" + 
				"<div class='col-md-12' style='text-align: left;'><address><strong>Uniprotec.</strong><br/> Ambar 20 Misi&oacute;n Mariana , Corregidora, Quer&eacute;taro, 76903 <br /> Santiago de Quer&eacute;taro<br /><abbr title='Phone'> P:</abbr> + 52 - 4423341671</address></div>" + 
				"<p class='text-left text-primary lead' style='text-align: left;'><em><small>Ha recib&iacute;do este correo electr&oacute;nico porque est&aacute; suscrito como usuario registrado del sistema control-uniprotec.com .</small></em></p>" + 
				"</div>" + 
				"</div>" + 
				"</body></html>";
		
		
		return body;
	}
			
	

	private String colorStatus(String statusAsignacion) {
		if(statusAsignacion.equals("Evento Cancelado")) {
			return "<div class='zona' style='background:red; color:white'>"+statusAsignacion+"</div>";
		}else {
			return "<div class='zona' style='background:yellow; color:blue'>Sustitucion de Instructor</div>";
		}
		
	}


	private StatusVO mailServiceGeneraCorreo(MailVO mailVO) {
		
		StatusVO statusVO = new StatusVO();
		 
		 log.info("Correo Destino: "+mailVO.getDestinatarioMailList().toString());
		 mailVO.setDestinatarioMail(limpia(mailVO.getDestinatarioMailList().toString()));
	  // El correo gmail de envío
	  final String correoEnvia = "notificacion@control-uniprotec.com";
	  final String claveCorreo = "Uniprotec2020#1";
	 
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
	   InternetAddress[] internetAddresses=  InternetAddress.parse(mailVO.getDestinatarioMail()) ;

	 
	   // Agregar los destinatarios al mensaje
	   mimeMessage.addRecipients(Message.RecipientType.TO,internetAddresses);
	 
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
	   MimeBodyPart mimeBodyPart = new MimeBodyPart();
	   if(mailVO.getAsignacionMail().getIdAsignacionLogica() != null ) {
		   String urlImagenes = "\\uniprotec\\asignacion\\"+mailVO.getAsignacionMail().getIdAsignacionLogica()+"\\";
		   File directorio = new File(urlImagenes);
		    
		   // Obtener los nombres de las imagenes en el directorio
		   String[] imagenesDirectorio = directorio.list();
		 
		   // Creo la parte del mensaje HTML
		   mimeBodyPart = new MimeBodyPart();
		   mimeBodyPart.setContent(msjHTML.toString(), "text/html");
		 
		   // Validar que el directorio si tenga las imagenes
		   if (imagenesDirectorio != null) {
		    for (int count = 0; count < imagenesDirectorio.length; count++) {
		 
		     MimeBodyPart imagePart = new MimeBodyPart();
		     imagePart.setHeader("Content-ID", "<" + imagenesDirectorio[count].toString() + ">");
		     imagePart.setDisposition(MimeBodyPart.INLINE);
		     imagePart.attachFile(urlImagenes + imagenesDirectorio[count].toString());
		     multipart.addBodyPart(imagePart);
//		     System.out.println("nombre de las imagenes : "+imagenesDirectorio[count].toString());
		    }
		   } else {
		    System.out.println("No hay archivos en el directorio");
		   }

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

	





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// UTIL
	
	private static String fecha(String fechaAsignacion) {
//        System.out.println(fechaAsignacion);
		Calendar fecha = Calendar.getInstance();
		String[] fechaA = fechaAsignacion.split("/");
		fecha.set(Integer.valueOf(fechaA[2]),Integer.valueOf(fechaA[0])-1,Integer.valueOf(fechaA[1]));
		String[] MESES = {	  "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		String[] DIA = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
		
		return DIA[fecha.get(Calendar.DAY_OF_WEEK)-1] +", "+ fecha.get(Calendar.DATE) + " de "+ MESES[fecha.get(Calendar.MONTH)]+ " "+ fecha.get(Calendar.YEAR);		
	}


	private AsignacionModelo correoStaff(String instructorAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private AsignacionModelo correoIntructor(String instructorAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}


	private String horario(String horarioAsignacion) {
		String[] horario = horarioAsignacion.split(";");
		String hr ="Horario : "+ horario[0].substring(0,2)+":"+horario[0].substring(2,4)+" - "+horario[1].substring(0,2)+":"+horario[1].substring(2,4)+".";
		
//		if( horario[2] != null && !horario[2].equals("")) {
//			if(horario[2].contains("Sede")) {
//				hr = hr +"Receso : Definir en sede el horario de receso. ";
//			}else {
//				hr = hr +"Receso : "+ horario[2].substring(0,2)+":"+horario[2].substring(2,2) +" - "+ horario[3].substring(0,2)+":"+horario[3].substring(2,2);
//			}
//			
//		}
		
		hr = hr +"  Horas Efectivas : "+ horario[4];
		
//		log.info(hr);
		return hr;
	}
	
	private String documentos(String archivosAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String listOTString(List<String> destinatarioMailList) {
		String concat = "";
		for(String a : destinatarioMailList) {
			concat = concat.concat(a + ", ");
		}
//		log.info(concat);
		return concat;
	}
	
	private String limpia(String string) {
		String a = string.replace("[", "");
		a = a.replace("]", "");
		
		return a;
	}





	
}
