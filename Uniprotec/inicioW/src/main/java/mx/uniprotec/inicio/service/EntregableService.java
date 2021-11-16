package mx.uniprotec.inicio.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.CredencialModelo;
import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;
import mx.uniprotec.entidad.modelo.ResultLocal;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class EntregableService implements IEntregableService {
	
	private static final int WIDTH = 100;

	private static final int HEIGHT =100;

	@Autowired
	BaseClientRest baseClientRest ;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	static String pathLogico = "/uniprotec/entregables/";


	public EntregableService() {
		// TODO Auto-generated constructor stub
	}
	
	MonitorEntidades me = new MonitorEntidades();

//	private byte[] xportReportToPdf;

	@SuppressWarnings("unchecked")
	@Override
	public ResultVO consultaEntregable(String token, Long idAsignacion) {
		ResultVO rs= (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_ENTREGABLE, null, idAsignacion.toString());
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonEntregables = new JSONObject();
			jsonEntregables.put("entregables", jsonGeneral.get("entregables"));
			jsonEntregables.put("participantes", jsonGeneral.get("participantes"));
			rs.setJsonResponseObject(jsonEntregables);
		}
			return rs;
		
	}
	
	@Override
	public ResultVO createEntregable(EntregableModelo entregable, String accesToken, Long idUsuario) {
		log.info(entregable.toString());
		List<ParticipantesModelo> participantes = getParticipantes(entregable, idUsuario);
		
		
		log.info("evidencias");
		if(entregable.getFormCEvidenciasFotograficasB() != null || !(entregable.getFormCEvidenciasFotograficasB().size() == 0)) {
			if(entregable.getFormCEvidenciasFotograficas() == null || entregable.getFormCEvidenciasFotograficas().size() == 0) {
				entregable.setFormCEvidenciasFotograficas(entregable.getFormCEvidenciasFotograficasB());
				log.info(entregable.getFormCEvidenciasFotograficas());
			}
		}
		entregable.setFormBParticipantes(participantes);
		entregable.setUserCreate(idUsuario);
		entregable.setCreateAt(me.getNowEntidad());
		entregable.setStatus("create");
		
		/*
		 * Entregable nuevo guardar Imagen logo
		 */
		if(entregable.getEstatusEntregable().equals("nuevo")) {
			Path origenPath = FileSystems.getDefault().getPath("/uniprotec/"+ entregable.getRfcOriginalAsignacion() +"/image/"+ entregable.getFormALogoEmpresa());
	        Path destinoPath = FileSystems.getDefault().getPath( pathLogico +"/imageLogo/"+ entregable.getFormALogoEmpresa());

	        try {
	            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            System.err.println(e);
	        }
		}
		
		/*
		 * Genera Documentacion
		 */
		if(entregable.isAltaDocto()) {
			entregable.setStatus("Generar Documentacion");
			ResultLocal rl = new ResultLocal();
			pathLogico = "/uniprotec/entregables/";
			pathLogico = pathLogico + entregable.getRfcOriginalAsignacion()+"/"+ entregable.getIdEntregableLogico();
			
			//Genera Diploma
			if(!entregable.getFormAFechaDiploma().equals("")) {
				try {
					rl = generaDiplomas(entregable);
					rl.setCodigo(0);
			        rl.setMensaje("Diploma PDF generado exitosamente");
			        
				} catch (JRException e) {
					e.printStackTrace();
					rl.setCodigo(500);
		            rl.setMensaje(e.getCause().toString());
		            return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
				} catch (Exception e) {
					e.printStackTrace();
					rl.setCodigo(500);
		            rl.setMensaje(e.getMessage().toString());
		            return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
				}
				
			}
			
			//Genera Credenciales
			if(!entregable.getFormAEquipoCredencial().equals("")) {
				try {
					rl = generaCredenciales(entregable);
					rl.setCodigo(0);
			        rl.setMensaje("Credenciales PDF generado exitosamente");
				} catch (JRException e) {
					e.printStackTrace();
					rl.setCodigo(500);
		            rl.setMensaje(e.getCause().toString());
		            return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
				} catch (Exception e) {
					e.printStackTrace();
					rl.setCodigo(500);
		            rl.setMensaje(e.getMessage().toString());
		            return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
				}
			}
			

			
			//Genera DC3
			if(!entregable.getFormAFechaInicioDC3().equals("")) {
				try {
					rl = generaDC3(entregable);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Genera Reporte
			try {
				rl = generaReporte(entregable);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * Actualiza Entregable
		 */
		ResultVO rs= (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_ENTREGABLE, entregable);
		if(rs.getCodigo() != 500) {
			rs.setJsonResponseObject(null);
		}
		
		return rs;
		
	}

	

	@Override
	public ResultVO actualizaEntregable(String token, String idAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO deleteEntregable(String token, String idAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * Privates
	 */
	
	private List<ParticipantesModelo> getParticipantes(EntregableModelo entregable, Long idUsuario) {
//		log.info(entregable.getFormBParticipantesStr());
		String[] tmp = null;
		List<ParticipantesModelo> participantes = new ArrayList<ParticipantesModelo>();
		me = ComponenteComun.monitorCampos();
		
		if(entregable.getFormBParticipantesStr().contains("idParticipante")) {
			 tmp = entregable.getFormBParticipantesStr().split("},");
			 if(tmp.length > 0) {
					for(String a : tmp) {
						if(!a.contains("}")) {
							a = a.concat("}");
						}
						JSONParser parser = new JSONParser();
						try {
							
							ParticipantesModelo participante = new ParticipantesModelo(); 
							JSONObject json = (JSONObject) parser.parse(a);
							
							participante.setIdParticipante(json.get("idParticipante").toString());
							participante.setParticipanteNombre((String) json.get("participanteNombre"));
							participante.setParticipanteCURP((String) json.get("participanteCURP"));
							participante.setParticipantePuesto((String) json.get("participantePuesto"));
							participante.setParticipanteOcupacion((String) json.get("participanteOcupacion"));
							participante.setParticipanteFoto((String) json.get("participanteFoto"));
							participante.setParticipanteExamenTeoricoInicial(Double.valueOf(json.get("participanteExamenTeoricoInicial").toString()));
							participante.setParticipanteExamenTeoricoFinal(Double.valueOf(json.get("participanteExamenTeoricoFinal").toString()));
							participante.setParticipanteExamenPractico(Double.valueOf(json.get("participanteExamenPractico").toString()));
							participante.setParticipantePromedio(Double.valueOf(json.get("participantePromedio").toString()));
							participante.setParticipanteObservaciones("");
							participante.setParticipanteAprovechamiento((String) json.get("participanteAprovechamiento"));
							participante.setUserCreate(idUsuario);
							participante.setCreateAt(me.getNowEntidad());
							participante.setStatus("create");
							
							participantes.add(participante);
//							log.info(participante);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
		}
		
		return participantes;
	}
	
	private ResultLocal generaReporte (EntregableModelo entregable) throws Exception, JRException  {
		
		
		return null;
	}

	private ResultLocal generaDC3(EntregableModelo entregable) throws Exception, JRException{
		// TODO Auto-generated method stub
		return null;
	}

	
	private ResultLocal generaDiplomas(EntregableModelo entregable) throws Exception, JRException{
		ResultLocal rl = new ResultLocal();
		long start = System.currentTimeMillis();
		JasperDesign toJasperdesign = null;
		if(entregable.getFormACurso().length() > 30) {
			toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/diploma2.jrxml"));
			log.info("diploma");
		}else {
			toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/diploma.jrxml"));
			log.info("diploma2");
		}
		
		JasperReport compileReport = JasperCompileManager.compileReport(toJasperdesign);
			
		List<JasperPrint> jasperPrintDiploma = new ArrayList<JasperPrint>();
		for(ParticipantesModelo pm : entregable.getFormBParticipantes()) {
				
			Map<String, Object> map = new HashMap<String, Object>();
			map= convertToDiploma(pm, entregable);
				
//			JasperPrint report = JasperFillManager.fillReport(compileReport, map,  jrBeanCollectionDS);
			JasperPrint report = JasperFillManager.fillReport(compileReport, map,  new JREmptyDataSource());
			jasperPrintDiploma.add(report);
			
		}
			
			// guardar en disco local
//		JasperExportManager.exportToPdfStream(jasperPrintDiploma, "nombreDiploma.pdf");
		OutputStream output = new FileOutputStream(new File(pathLogico + "/documentacion/diplomas_"+entregable.getIdEntregableLogico()+".pdf"));
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintDiploma));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
        exporter.exportReport();
        output.flush();
        output.close();
        log.info("PDF creation time : " + (System.currentTimeMillis() - start));
		return rl;

		// extraer en http get
//		byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(report);
//		HttpHeaders headers = new HttpHeaders ();
//		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=nombreCredencial.pdf");
//		ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportReportToPdf);
		
	}
		 
		
		
		
	

	
	private ResultLocal generaCredenciales(EntregableModelo entregable) throws Exception, JRException{
		ResultLocal rl = new ResultLocal();
		List<CredencialModelo> credenciales = convertToCredenciales(entregable);
		String path ="";
		String fileName =pathLogico + "/documentacion/credenciales_"+entregable.getIdEntregableLogico()+".pdf";
		log.info("createCredencialPDF");

		byte[] lyContent = null;
		
		Map<String, Object> pmParametros = new HashMap<String, Object>();
		String pathSave = path + fileName;

		JasperPrint jrJasperPr = null;
		ByteArrayOutputStream baosPDFSummary = null;

		
		JasperDesign toJasperdesign = null;

//		toJasperdesign = JRXmlLoader.load("jasper/credencial.jrxml");
		toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/credencial.jrxml"));
			

		JasperReport toJaspertRes = JasperCompileManager.compileReport(toJasperdesign);

		baosPDFSummary = new ByteArrayOutputStream();
			
		JRBeanCollectionDataSource dsCredenciales = null;
		dsCredenciales = new JRBeanCollectionDataSource(credenciales);

		pmParametros.put("credenciales", dsCredenciales);

		jrJasperPr = JasperFillManager.fillReport(toJaspertRes, pmParametros, new JREmptyDataSource());
		log.info("Start JasperFillManager.fillReport");
		JasperExportManager.exportReportToPdfFile(jrJasperPr, pathSave);
		JasperExportManager.exportReportToPdfStream(jrJasperPr, baosPDFSummary);
		log.info("End JasperFillManager.fillReport");
		lyContent = baosPDFSummary.toByteArray();

		baosPDFSummary.flush();

		baosPDFSummary.close();
		
		return rl;
	}


	private List<CredencialModelo> convertToCredenciales(EntregableModelo entregable) {
		EntregableService entregableService = new EntregableService();
		
		List<CredencialModelo> listCM = new ArrayList<CredencialModelo>();
		for(ParticipantesModelo pm : entregable.getFormBParticipantes()) {
			CredencialModelo cm = new CredencialModelo();
			cm.setNombreParticipante(pm.getParticipanteNombre());
			cm.setNombreCurso(entregable.getFormAEquipoCredencial());
			cm.setFechaInicio(recortaDia(entregable.getFormAFechaInicioCredenciales()));
			cm.setFechaFinal(recortaDia(entregable.getFormAFechaFinalCredenciales()));
			cm.setInstructor(entregable.getFormAInstructor());
			cm.setLogoEmpresa(new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imageLogo/".concat(entregable.getFormALogoEmpresa()))));
			cm.setFotoParticipante(new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imagenesParticipantes/".concat(pm.getParticipanteFoto()))));
			cm.setFirmaInstructor(new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion())));
//			cm.setFirmaDirector(new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion())));
			 listCM.add(cm);
		}
		return  listCM;
	}

	private String recortaDia(String fecha) {
		String tmp = fecha.substring(fecha.indexOf(" "), fecha.length());
		return tmp;
	}

	private Map<String, Object> convertToDiploma(ParticipantesModelo pm, EntregableModelo entregable) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("diplomaNombreParticipante", pm.getParticipanteNombre());
		map.put("diplomaNombreCurso", entregable.getFormACurso());
		map.put("diplomaFecha", entregable.getFormAFechaDiploma());
		map.put("diplomaDuracion", entregable.getFormADuracion());
		map.put("diplomaNombreInstructor", entregable.getFormAInstructor());
		map.put("diplomaNombreDirector", "Olivier Sánchez");
				
		EntregableService entregableService = new EntregableService();
		InputStream diplomaFirmaInstructor = new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion()));
		map.put("diplomaFirmaInstructor", diplomaFirmaInstructor );
		InputStream diplomaFirmaDirector = new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/director/image/1.jpg"));
		map.put("diplomaFirmaDirector", diplomaFirmaDirector);
		
		return map;
	}
	
	public byte[] getImage(String psthImage) {
		File fi = new File(psthImage);
		byte[] fileContent = null;
		try {
			fileContent = Files.readAllBytes(fi.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileContent;
	}

}
