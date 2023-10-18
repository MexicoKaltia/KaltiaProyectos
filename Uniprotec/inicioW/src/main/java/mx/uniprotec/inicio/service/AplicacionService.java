package mx.uniprotec.inicio.service;

import java.security.GeneralSecurityException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.AsignacionModeloDescarga;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ReporteSemanalModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UserCorreo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.entidad.modelo.ZonaBaseModelo;
import mx.uniprotec.inicio.entity.StatusVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;
import mx.uniprotec.inicio.util.IBaseClientRest;


@Service
public class AplicacionService implements IAplicacionService {

	public AplicacionService() {}
	
	private static Logger log = LoggerFactory.getLogger(AplicacionService.class);
	
	ResultVO resultVO = new ResultVO();
	
	@Autowired
	IBaseClientRest baseClientRest ;
	
	@Autowired
	IMailService mailService;
//	@Autowired
	MonitorEntidades me = new MonitorEntidades();


	@Override
	public ResultVO consultaRegiones(String token) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_REGIONES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("regiones", jsonGeneral.get("regiones"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}
	
	@Override
	public ResultVO altaMensaje(MensajeModelo mensaje, String accesToken) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_MENSAJE, mensaje);
		return rs;
	}
	
	@Override
	public ResultVO consultaMovilidad(String accesToken) {
		ResultVO rs = baseClientRest.objetoGetAll(accesToken,BaseClientRest.URL_CRUD_ZONABASE);
		JSONObject jsonZB = new JSONObject();
		jsonZB.putAll((Map) rs.getJsonResponse().get("zonaBase"));		
		JSONObject jsonZonaBase = new JSONObject();
		jsonZonaBase.put("zonaBase", jsonZB.get("dataZonabase"));
		rs.setJsonResponse(jsonZonaBase);
		if(rs.getCodigo() != 500) {
			ResultVO rsRegiones = consultaRegiones(accesToken);
			if(rsRegiones.getCodigo() != 500) {
				rs.setRegiones((List<Region>) rsRegiones.getJsonResponseObject().get("regiones"));
			}
		}
		
		
		
		return rs;
	}
	

	@Override
	public ResultVO updateMovilidad(String accesToken, ZonaBaseModelo zonaBase) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_ZONABASE, zonaBase);
		return rs;
	}





	@Override
	@SuppressWarnings("unchecked")
	public ResultVO consultaData(ResultVO resultVO) {
		
		String token = resultVO.getAccesToken();
		ResultVO rs = new ResultVO();
		ResultVO rsInstructores = new ResultVO();
		ResultVO rsRegiones = new ResultVO();
		ResultVO rsVendedores = new ResultVO();
		ResultVO rsAsignaciones = new ResultVO();
		ResultVO rsZonaBase = new ResultVO();
		
		JSONObject jsonZonaBase = new JSONObject();
		
		ResultVO rsClientes = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CLIENTES);
		
		if(rsClientes .getCodigo() != 500) {
			rs.setClientes((List<ClienteModelo>) rsClientes.getJsonResponse().get("clientes"));
			rsInstructores = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_INSTRUCTORES);
			if(rsInstructores.getCodigo() != 500) {
				rs.setInstructores((List<InstructorModelo>) rsInstructores.getJsonResponse().get("instructores"));
				rsRegiones = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_REGIONES);
				if(rsRegiones.getCodigo() != 500) {
					rs.setRegiones((List<Region>) rsRegiones.getJsonResponse().get("regiones"));
					rsVendedores = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORES);
					if(rsVendedores.getCodigo() != 500) {
						rs.setVendedores((List<VendedorModelo>) rsVendedores.getJsonResponse().get("vendedores"));
						rsAsignaciones = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
						if(rsAsignaciones.getCodigo() != 500) {
							rs.setAsignaciones((List<AsignacionModelo>) rsAsignaciones.getJsonResponse().get("asignaciones"));
							rsZonaBase = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ZONABASE);
							JSONObject jsonZB = new JSONObject();
							jsonZB.putAll((Map) rsZonaBase.getJsonResponse().get("zonaBase"));		
							jsonZonaBase.put("zonaBase", jsonZB.get("dataZonabase"));
						}else {
							log.info("error Asignaciones");
							return rsAsignaciones;
						}
					}else {
						log.info("error Vendedores");
						return rsVendedores;
					}
						
				}else {
					log.info("error Regiones");
					return rsRegiones;
				}
				
			}else {
				log.info("error Instructores");
				return rsInstructores;
			}
			
		}else {
			log.info("error Clientes");
			return rsClientes;
		}
		
			
		ResultVO rsCursos = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CURSOS);
		if(rsCursos.getCodigo() != 500) {
			rs.setCursos((List<CursoModelo>) rsCursos.getJsonResponse().get("cursos"));		
		}else {
			return rsCursos;
		}
		
		rs.setCodigo(rsCursos.getCodigo());
		rs.setMensaje("Exito en las consultas Asignaciones");
		rs.setAccesToken(token);
		rs.setJsonResponse(resultVO.getJsonResponse());
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("clientes", rs.getClientes());
		jsonData.put("instructores", rs.getInstructores());
		jsonData.put("cursos", rs.getCursos());
		jsonData.put("regiones", rs.getRegiones());
		jsonData.put("vendedores", rs.getVendedores());
		jsonData.put("asignaciones", rs.getAsignaciones());
		jsonData.put("zonaBase", jsonZonaBase);
		
		
		rs.setJsonResponseObject(jsonData);

//		log.info(rs.toString());
		
		return rs;
	}
	
	@Override
	public void enviaMail(AsignacionModelo asignacion, String token) {
		String inicio = hora();
		StatusVO statusVO = mailService.mailServicePreCorreo(asignacion, token);
		
		log.info("inicio:"+inicio+"\t final:"+hora());
		
	//	return statusVO;
		
	}
	
	@Override
	public void enviaMailSustitucion(AsignacionModelo asignacion, String token, Long idInstructor) {
		mailService.mailServicePreCorreoSustitucion(asignacion, token, idInstructor);
		
	}

	
	
	
	@Override
	public List<UserCorreo> usersCorreo(Long idInstructorAsignacion, Long userCreateAsignacion, String token) {
		
		UserCorreo userVendedor = new UserCorreo(userCreateAsignacion, "Ventas");
		UserCorreo userInstructor = new UserCorreo(idInstructorAsignacion, "Instructor");
		List<UserCorreo> usersCorreo = new ArrayList<UserCorreo>();
		usersCorreo.add(userVendedor);
		usersCorreo.add(userInstructor);
		
		usersCorreo =  (List<UserCorreo>) baseClientRest.objetoGetObject(token, BaseClientRest.URL_CRUD_CORREOS, usersCorreo);
		
		return usersCorreo;
		
	}
	
	@Override
	public void actualizaNotificacion(String token, String idAsignacion) {
		baseClientRest.objetoPut(token, BaseClientRest.URL_CRUD_NOTIFICACION, null, Long.valueOf(idAsignacion) );
	}
	
	@Override
	public StatusVO enviaMail(AsignacionModelo asignacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaOperacion(String accesToken) {
		ResultVO rsOperacion = (ResultVO) baseClientRest.objetoGetAll(accesToken, BaseClientRest.URL_CRUD_OPERADORES);
		if(rsOperacion.getCodigo() == 202) {
			JSONObject jsonGeneral = rsOperacion.getJsonResponse();
			JSONObject jsonOperacion = new JSONObject();
			jsonOperacion.put("operadores", jsonGeneral.get("operadores"));
			
			rsOperacion.setJsonResponseObject(jsonOperacion);
	
		}
		return rsOperacion;
	}
	
	@Override
	public ResultVO descargaAsignaciones(AsignacionModeloDescarga asignacionesDescarga) {
		ResultVO rs = new ResultVO();
		try {
			List<AsignacionModelo> asignaciones = converterAsignaciones(asignacionesDescarga);
			ICsvBeanWriter beanWriter = beanWriter(asignaciones);
			rs.setCodigo(200l);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}


	
	
	
	@Override
	public ResultVO getNotificacion(Long idNotificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void citaInstructor(AsignacionModelo asignacion) {
		try {
			GoogleCalendar.enviaCitaInstructor(asignacion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

	/*
	 * privates
	 */
	private String hora() {
		
		java.util.Date date = new java.util.Date();
		DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
											//      dd/MM/yyyy
											//		HH:mm:ss dd/MM/yyyy
		
		return hourFormat.format(date);
	}

	private List<AsignacionModelo> converterAsignaciones(AsignacionModeloDescarga asignacionesDescarga) {
		String[] tmp = null;
		List<AsignacionModelo> asignaciones = new ArrayList<AsignacionModelo>();
		me = ComponenteComun.monitorCampos();
		
			 tmp = asignacionesDescarga.getStrAsignacionesDescargas().split("},");
			 if(tmp.length > 0) {
					for(String a : tmp) {
						if(!a.contains("}")) {
							a = a.concat("}");
						}
						JSONParser parser = new JSONParser();
						try {
							
							AsignacionModelo asignacion = new AsignacionModelo(); 
							JSONObject json = (JSONObject) parser.parse(a);
//							log.info(json.toJSONString());
							
							asignacion.setIdAsignacion((Long) json.get("idAsignacion"));
							asignacion.setIdAsignacionLogica((String) json.get("idAsignacionLogica"));
							asignacion.setFechaAsignacion((String) json.get("fechaAsignacion"));
							asignacion.setClienteAsignacion((String) json.get("clienteAsignacion"));
							asignacion.setCursoAsignacion((String) json.get("cursoAsignacion"));
							asignacion.setTipoCursoAsignacion((String) json.get("tipoCursoAsignacion"));
							asignacion.setInstructorAsignacion((String) json.get("instructorAsignacion"));
							asignacion.setHorarioAsignacion((String) json.get("horarioAsignacion"));
							asignacion.setParticipantesAsignacion((String) json.get("participantesAsignacion"));
							asignacion.setNivelAsignacion((String) json.get("nivelAsignacion"));
//							asignacion.setObservacionesAsignacion((String) json.get("observacionesAsignacion"));
//							asignacion.setArchivosAsignacion(validateEmpty((String) json.get("archivosAsignacionTexto")));
							asignacion.setNombreRegionAsignacion((String) json.get("nombreRegionAsignacion"));
							asignacion.setCreateAtAsignacion(LocalDateTime.parse((String) json.get("createAtAsignacion")));
							asignacion.setUserCreateAsignacionTexto((String) json.get("userCreateAsignacionTexto"));
							asignacion.setStatusAsignacion((String) json.get("statusAsignacion"));
							asignacion.setFechaPago(validateEmpty((String) json.get("fechaPago")));
							asignacion.setGuiaEntregable(validateEmpty((String) json.get("guiaEntregable")));
							asignacion.setNumeroFactura(validateEmpty((String) json.get("numeroFactura")));
//							asignacion.setArchivoParticipantes(validateEmpty((String) json.get("archivoParticipantesTexto")));
							asignacion.setCostoHotel(validateEmpty((String) json.get("costoHotel")));
																				
							asignaciones.add(asignacion);

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			 return asignaciones;
	}

	private String validateEmpty(String string) {
//		log.info(string);
		String dot = ".";
		if(string == null) {
			return dot;
		}
		if(string.equals("") || string.isEmpty()) {
			return dot;
		}else {
			return string;
		}
		
	}

	private static CellProcessor[] getProcessors(){
		
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(), // idAsignacion
				new NotNull(), // idAsignacionLogica
				new NotNull(), // fechaAsignacion
				new NotNull(), // clienteAsignacion
				new NotNull(), // cursoAsignacion
				new NotNull(), // tipoCursoAsignacion
				new NotNull(), // instructorAsignacion
				new NotNull(), // horarioAsignacion
				new NotNull(), // participantesAsignacion
				new NotNull(), // nivelAsignacion
				new Optional(), // observacionesAsignacion
//				new Optional(), // archivosAsignacionTexto
				new NotNull(), // nombreRegionAsignacion
				new NotNull(), // createAtAsignacion
				new NotNull(), // userCreateAsignacionTexto
				new NotNull(), // statusAsignacion
				new Optional(), // fechaPago
				new Optional(), // guiaEntregable
				new Optional(), // numeroFactura
//				new Optional(), // archivoParticipantesTexto
				new Optional(), // costoHotel	
		};
		return processors;
	}
	
	private ICsvBeanWriter beanWriter(List<AsignacionModelo> asignaciones) {
		ICsvBeanWriter beanWriter = null;

		try
		{
			beanWriter = new CsvBeanWriter(new FileWriter("/uniprotec/descargaAsignaciones/descargaAsignaciones.csv"), CsvPreference.STANDARD_PREFERENCE);
			
			final String[] header = new String[] { "idAsignacion","idAsignacionLogica","fechaAsignacion","clienteAsignacion",
					"cursoAsignacion","tipoCursoAsignacion","instructorAsignacion","horarioAsignacion","participantesAsignacion",
					"nivelAsignacion", "observacionesAsignacion", "nombreRegionAsignacion","createAtAsignacion",
					"userCreateAsignacionTexto","statusAsignacion","fechaPago","guiaEntregable","numeroFactura","costoHotel" };

			final CellProcessor[] processors = getProcessors();

			// write the header
			beanWriter.writeHeader(header);

			// write the beans data
			for (AsignacionModelo asignacion : asignaciones) {
				beanWriter.write(asignacion , header, processors);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				beanWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return beanWriter;
	}
	
}
