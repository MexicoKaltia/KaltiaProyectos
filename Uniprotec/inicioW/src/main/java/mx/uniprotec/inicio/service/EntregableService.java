package mx.uniprotec.inicio.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
//		log.info(entregable.toString());
		List<ParticipantesModelo> participantes = getParticipantes(entregable, idUsuario);
		
		
		
		if(entregable.getFormCEvidenciasFotograficasB() != null || !(entregable.getFormCEvidenciasFotograficasB().size() == 0)) {
			if(entregable.getFormCEvidenciasFotograficas() == null || entregable.getFormCEvidenciasFotograficas().size() == 0) {
				entregable.setFormCEvidenciasFotograficas(entregable.getFormCEvidenciasFotograficasB());
				log.info(entregable.getFormCEvidenciasFotograficas());
			}
		}
		if(entregable.getFormCEvidenciaDoctoB() != null || !(entregable.getFormCEvidenciaDoctoB().size() == 0)) {
			if(entregable.getFormCEvidenciaDocto() == null || entregable.getFormCEvidenciaDocto().size() == 0) {
				entregable.setFormCEvidenciaDocto(entregable.getFormCEvidenciaDoctoB());
				log.info(entregable.getFormCEvidenciaDocto());
			}
		}
		entregable.setFormBParticipantes(participantes);
		entregable.setUserCreate(idUsuario);
		entregable.setCreateAt(me.getNowEntidad());
		entregable.setStatus("Elaborar Entregable");
		if(entregable.getFormARepresentanteEmpresa().equals("")) {
			entregable.setFormARepresentanteEmpresa("");
		}
		if(entregable.getFormARepresentanteTrabajador().equals("")) {
			entregable.setFormARepresentanteTrabajador("");
		}
		
		/*
		 * Actualiza Entregable
		 */
		ResultVO rs= (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_ENTREGABLE, entregable);
		if(rs.getCodigo() != 500) {
			
			String idEmpresa = entregable.getRfcOriginalAsignacion();
			String idEntregable = entregable.getIdEntregableLogico();
			
			
			/*
			 * Entregable nuevo guardar Imagen logo
			 */
			if(entregable.getEstatusEntregable().equals("nuevo")) {
				
//				JSONObject jsonResponse = rs.getJsonResponse();
//				JSONObject jsonEntregable = (JSONObject) jsonResponse.get("entregable");
//				String idEntregable = (String) jsonEntregable.get("idEntregable");
//				String idAsignacion = Long.valueOf(entregable.getIdAsignacion()).toString();

				String pathImageLogo = pathLogico +"/"+ entregable.getRfcOriginalAsignacion() +"/"+ idEntregable+"/imageLogo/";
				Path origenPath = FileSystems.getDefault().getPath("/uniprotec/"+ entregable.getRfcOriginalAsignacion() +"/image/"+ entregable.getFormALogoEmpresa());
		        Path destinoPath = FileSystems.getDefault().getPath( pathImageLogo + entregable.getFormALogoEmpresa());
//		        "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/"+tipoCarpeta+"/"
		        File directorio = new File(pathImageLogo); 
		        
		        if (!directorio.exists()) {
		            if (directorio.mkdirs()) {
		                try {
							Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
						}
		                log.info("Directorio creado : "+pathImageLogo);
		            } else {
		                System.out.println("Error al crear directorio");
		            }
		        }
//		        try {
//		        	directory.mkdirs();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		        

			}
			
			
			
			/*
			 * Genera Documentacion
			 */
			
			if(entregable.isAltaDocto()) {
				entregable.setStatus("Entregable Generado");
				ResultLocal rl = new ResultLocal();
				pathLogico = "/uniprotec/entregables/";
				pathLogico = pathLogico + idEmpresa+"/"+ idEntregable;
				
				crearDirectoriosDocumentacion( idEmpresa,  idEntregable);
				
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
						rl.setCodigo(0);
				        rl.setMensaje("DC3 PDF generado exitosamente");
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
				
				//Genera Reporte
				if(!entregable.getFormCFechaInicio().equals("")) {
					try {
						rl = generaReporte(entregable);
						rl.setCodigo(0);
				        rl.setMensaje("Reporte PDF generado exitosamente");
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
				
				/*
				 * comprime archivos
				 */
				if(rl.getCodigo() == 0) {
					
					/*
			         * comprimir archivos files a documentacion/evidenciaDocto.zip 
			         */
					 	String directory = pathLogico+"/file/";
					 	String fileOutPut, folderInput= ""; 
					 	
				        File directorio = new File(directory);
				        if (directorio.exists()) {
				        	try {
				        		log.info("Comprime EvidenciaDocumental");
				        		fileOutPut = "/documentacion/evidenciasDocto.zip"; 
				        		folderInput ="/file/";
								compressFile(idEmpresa, idEntregable, fileOutPut, folderInput );
								rl.setCodigo(0);
						        rl.setMensaje("Comprime EvidenciaDocumental exitosamente");
							} catch (Exception e) {
								log.info("exception : "+e.getMessage());
							    rl.setCodigo(99);
							    rl.setMensaje(e.getMessage());
							    return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
							}
				        }
					
			        /*
			         * comprimir archivos zip idEntregable.zip
			         */
					try {
						log.info("Comprime idEntregable.zip");
						fileOutPut = "/zip/"+idEntregable+".zip"; 
		        		folderInput ="/documentacion/";
	//					compress(idEmpresa, idEntregable);
		        		compressFile(idEmpresa, idEntregable, fileOutPut, folderInput );
		        		rl.setCodigo(0);
		        		rl.setMensaje("Comprime idEntregable exitosamente");
					} catch (Exception e) {
						log.info("exception : "+e.getMessage());
					    rl.setCodigo(99);
					    rl.setMensaje(e.getMessage());
					    return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
					}
				}
				
				/**
				 * Borrar PDF's
				 */
				if(rl.getCodigo() == 0) {
					String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/documentacion/";
				    File directorio = new File(directory);
				    try {
						FileUtils.deleteDirectory(directorio);
						rl.setCodigo(0);
						rl.setMensaje("Borrar PDF's exitosamente");
						FileUtils.forceMkdir(directorio);
						entregable.setStatus("Entregable Generado");
					} catch (IOException e) {
						log.info("exception : "+e.getMessage());
					    rl.setCodigo(99);
					    rl.setMensaje(e.getMessage());
					    return new ResultVO(Long.valueOf(rl.getCodigo()), rl.getMensaje());
					}
				}			
			}
		}		
		
		return rs;
		
	}

	

	@Override
	public ResultVO actualizaEntregable(String token, String idAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ResultVO deleteEntregable(String idAsignacion, String accesToken) {
	ResultVO rs= (ResultVO) baseClientRest.objetoDeleteId(idAsignacion, accesToken , BaseClientRest.URL_CRUD_ENTREGABLE_D);

	if(rs.getCodigo() != 500) {
	JSONObject jsonGeneral = rs.getJsonResponse();
	JSONObject jsonResponseObject = new JSONObject();
	jsonResponseObject.put("mensaje", jsonGeneral.get("mensaje"));
	rs.setJsonResponseObject(jsonResponseObject);
	}
	return rs;
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
							participante.setParticipanteExamenTeoricoInicial(json.get("participanteExamenTeoricoInicial").toString());
							participante.setParticipanteExamenTeoricoFinal(json.get("participanteExamenTeoricoFinal").toString());
							participante.setParticipanteExamenPractico(json.get("participanteExamenPractico").toString());
							participante.setParticipantePromedio(json.get("participantePromedio").toString());
							participante.setParticipanteObservaciones("");
							participante.setParticipanteAprovechamiento((String) json.get("participanteAprovechamiento"));
							participante.setParticipanteAprobado((boolean) json.get("participanteAprobado"));
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
	
	private ResultLocal generaDiplomas(EntregableModelo entregable) throws Exception, JRException{
		ResultLocal rl = new ResultLocal();
		long start = System.currentTimeMillis();
		JasperDesign toJasperdesign = null;
		if(entregable.getFormACurso().length() > 30) {
			toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/diploma2.jrxml"));
		}else {
			toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/diploma.jrxml"));
		}
		
		JasperReport compileReport = JasperCompileManager.compileReport(toJasperdesign);
			
		List<JasperPrint> jasperPrintDiploma = new ArrayList<JasperPrint>();
		for(ParticipantesModelo pm : entregable.getFormBParticipantes()) {
				
			Map<String, Object> map = new HashMap<String, Object>();
				map= convertToDiploma(pm, entregable);
//				JasperPrint report = JasperFillManager.fillReport(compileReport, map,  jrBeanCollectionDS);
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
        log.info("Diplomas creation time : " + (System.currentTimeMillis() - start));
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
		long start = System.currentTimeMillis();

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
		JasperExportManager.exportReportToPdfFile(jrJasperPr, pathSave);
		JasperExportManager.exportReportToPdfStream(jrJasperPr, baosPDFSummary);
		lyContent = baosPDFSummary.toByteArray();

		baosPDFSummary.flush();

		baosPDFSummary.close();
        log.info("Credenciales creation time : " + (System.currentTimeMillis() - start));
		return rl;
	}



	

	private ResultLocal generaDC3(EntregableModelo entregable) throws Exception, JRException{
		ResultLocal rl = new ResultLocal();
		long start = System.currentTimeMillis();
		JasperDesign toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/DC3.jrxml"));
		
		JasperReport compileReport = JasperCompileManager.compileReport(toJasperdesign);
			
		List<JasperPrint> jasperPrintDC3 = new ArrayList<JasperPrint>();
		for(ParticipantesModelo pm : entregable.getFormBParticipantes()) {
				
			Map<String, Object> map = new HashMap<String, Object>();
			if(pm.isParticipanteAprobado()) {
				map= convertToDC3(pm, entregable);			
				JasperPrint report = JasperFillManager.fillReport(compileReport, map,  new JREmptyDataSource());
				jasperPrintDC3.add(report);
			}		
		}
			
			// guardar en disco local
//		JasperExportManager.exportToPdfStream(jasperPrintDiploma, "nombreDiploma.pdf");
		OutputStream output = new FileOutputStream(new File(pathLogico + "/documentacion/DC3_"+entregable.getIdEntregableLogico()+".pdf"));
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintDC3));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
        exporter.exportReport();
        output.flush();
        output.close();
        log.info("DC3 creation time : " + (System.currentTimeMillis() - start));
		return rl;

	}

	
	private List<ParticipantesModelo> listaParticipanteTotal = new ArrayList<ParticipantesModelo>();  
	private ResultLocal generaReporte (EntregableModelo entregable) throws Exception, JRException  {
		ResultLocal rl = new ResultLocal();
		long start = System.currentTimeMillis();
		JasperDesign toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/reporte.jrxml"));
		
		JasperReport compileReport = JasperCompileManager.compileReport(toJasperdesign);
			
		List<JasperPrint> jasperPrintReporte = new ArrayList<JasperPrint>();
		Map<String, Object> map = new HashMap<String, Object>();
		map = convertToDReporte(entregable);
//		JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,  new JREmptyDataSource());		
		jasperPrintReporte.add(report);
		
		
		
		//Genera Resultados Participantes
//		JRBeanCollectionDataSource participantes = new JRBeanCollectionDataSource(entregable.getFormBParticipantes());
		
		listaParticipanteTotal = entregable.getFormBParticipantes();
		List<List<ParticipantesModelo>> listas = new ArrayList<List<ParticipantesModelo>>();
		List<ParticipantesModelo> listaParticipanteResto = listaParticipanteTotal;
		listas.add(listaParticipanteTotal);
		listas.add(listaParticipanteResto);
		while(listaParticipanteResto.size() > 0) {
			
//			listaParticipanteTotal = listaParticipantes(listaParticipanteTotal);
			listas = listaParticipantes(listas.get(1));
			listaParticipanteResto = listas.get(1);
//			listaParticipanteTotal = listaParticipanteResto; 
//			JasperPrint resultadosParticipantesJasperPrint = resultadosParticipantes(entregable.getFormBParticipantes());
			JasperPrint resultadosParticipantesJasperPrint = resultadosParticipantes(listas.get(0));
			jasperPrintReporte.add(resultadosParticipantesJasperPrint);
			
		}
			
			// guardar en disco local
//		JasperExportManager.exportToPdfStream(jasperPrintDiploma, "nombreDiploma.pdf");
		OutputStream output = new FileOutputStream(new File(pathLogico + "/documentacion/Reporte_"+entregable.getIdEntregableLogico()+".pdf"));
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintReporte));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
        exporter.exportReport();
        output.flush();
        output.close();
        log.info("Reporte creation time : " + (System.currentTimeMillis() - start));
		return rl;
	}
	
	
	private JasperPrint resultadosParticipantes(List<ParticipantesModelo> formBParticipantes) throws JRException {
		JasperDesign toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/resultadosParticipantes.jrxml"));
		JasperReport compileReport = JasperCompileManager.compileReport(toJasperdesign);
		Map<String, Object> map = new HashMap<String, Object>();
		JRBeanCollectionDataSource participantes = new JRBeanCollectionDataSource(formBParticipantes);
				
		return JasperFillManager.fillReport(compileReport, map,  participantes);
		
	}
	
	private List<List<ParticipantesModelo>> listaParticipantes(List<ParticipantesModelo> formBParticipantes) {
		List<ParticipantesModelo> listaParticipanteNew =  new ArrayList<ParticipantesModelo>();
		List<ParticipantesModelo> listaParticipanteResto =  formBParticipantes;
		List<List<ParticipantesModelo>> listas = new ArrayList<List<ParticipantesModelo>>();
		int capacidad = formBParticipantes.size();
		if(capacidad > 14) {
			for(int count = 0; count < 14 ; count++) {
				ParticipantesModelo participante = formBParticipantes.get(0);
				listaParticipanteNew.add(participante);
				listaParticipanteResto.remove(0);
			}
		}else {
			for(int count = 0; count < capacidad ; count++) {
				ParticipantesModelo participante = formBParticipantes.get(0);
				listaParticipanteNew.add(participante);
				listaParticipanteResto.remove(0);
			}
		}
		listas.add(listaParticipanteNew);
		listas.add(listaParticipanteResto);
		return listas;
	}



//	private JasperPrint resultadosParticipantes(List<ParticipantesModelo> formBParticipantes) throws JRException {
//		
//		JasperDesign toJasperdesign = null;
//		JasperReport compileReport  = null;
//		JRBeanCollectionDataSource participantes =null;
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		while(formBParticipantes.size() == 0) {
//			
//			formBParticipantes = listaParticipantes(formBParticipantes);
//			toJasperdesign = JRXmlLoader.load(EntregableService.class.getClassLoader().getResourceAsStream("jasper/resultadosParticipantes.jrxml"));
//			compileReport = JasperCompileManager.compileReport(toJasperdesign);
//			participantes = new JRBeanCollectionDataSource(formBParticipantes);
//			log.info(formBParticipantes.size());
//		}
//				
//		return JasperFillManager.fillReport(compileReport, map,  participantes);
//		
//	}
	
	

	
    
    private void compressFile(String idEmpresa, String idEntregable, String fileOutPut, String folderInput) throws Exception {
    	byte[] buffer = new byte[20480];
        String pathLogico = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable;
        
//          ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("target/file.zip"))));
          FileOutputStream fos = new FileOutputStream(pathLogico+fileOutPut);
          ZipOutputStream zos = new ZipOutputStream(fos);
          
          try{  
          
              
              // create new file
        	  String rutaCarpeta = pathLogico+folderInput;
            File  f = new File(rutaCarpeta);
                                      
              // array of files and directory
            String[] paths = f.list();
                 
              // for each name in the path array
              for(String path:paths) {
              
                 // prints filename and directory name
            	  ZipEntry ze= new ZipEntry(path);
                  zos.putNextEntry(ze);
                  FileInputStream in = new FileInputStream(rutaCarpeta+path);
                  
                  int len;
                  while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                  }
                  
                  in.close();
              }
              
           } catch(Exception e) {
              // if any error occurs
              e.printStackTrace();
           }
          zos.closeEntry();
          zos.close();
         
      }
	/*
	 * Private 2 generacion
	 */


	private List<CredencialModelo> convertToCredenciales(EntregableModelo entregable) {
		EntregableService entregableService = new EntregableService();
		
		List<CredencialModelo> listCM = new ArrayList<CredencialModelo>();
		for(ParticipantesModelo pm : entregable.getFormBParticipantes()) {
			if(pm.isParticipanteAprobado()) {
				CredencialModelo cm = new CredencialModelo();
				cm.setNombreParticipante(pm.getParticipanteNombre());
				cm.setNombreCurso(entregable.getFormAEquipoCredencial());
				cm.setFechaInicio(recortaDia(entregable.getFormAFechaInicioCredenciales()));
				cm.setFechaFinal(recortaDia(entregable.getFormAFechaFinalCredenciales()));
				cm.setInstructor(entregable.getFormAInstructor());
				cm.setLogoEmpresa(new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imageLogo/".concat(entregable.getFormALogoEmpresa()))));
				cm.setFotoParticipante(new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imagenesParticipantes/".concat(pm.getParticipanteFoto()))));
				cm.setFirmaInstructor(new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion())));
//				cm.setFirmaDirector(new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion())));
				 listCM.add(cm);
			}
		}
		return  listCM;
	}

	
	private Map<String, Object> convertToDiploma(ParticipantesModelo pm, EntregableModelo entregable) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("diplomaNombreParticipante", pm.getParticipanteNombre());
		map.put("diplomaNombreCurso", entregable.getFormACurso());
		map.put("diplomaFecha", entregable.getFormAFechaDiploma().toUpperCase());
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
	
	private Map<String, Object> convertToDC3(ParticipantesModelo pm, EntregableModelo entregable) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		EntregableService entregableService = new EntregableService();
		map.put("dc3EmpresaLogo", new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imageLogo/".concat(entregable.getFormALogoEmpresa()))));
		map.put("dc3ParticipanteNombre", pm.getParticipanteNombre());
		map.put("dc3ParticipanteCURP", transformToCURP(pm.getParticipanteCURP()));
		map.put("dc3ParticipanteOcupacion", pm.getParticipanteOcupacion());
		map.put("dc3ParticipantePuesto", pm.getParticipantePuesto());
		map.put("dc3EmpresaRazonSocial", entregable.getFormARazonSocial());
		map.put("dc3EmpresaRFC", transformToRFC(entregable.getFormARFC()));
		map.put("dc3Curso", entregable.getFormACurso());
		map.put("dc3Duracion", entregable.getFormADuracion());
		map.put("dc3FechaInicial", transformToFI(entregable.getFormAFechaInicioDC3()));
		map.put("dc3FechaFinal", transformToFF(entregable.getFormAFechaFinDC3()));
		map.put("dc3Instructor", entregable.getFormAInstructor());
		map.put("dc3RepresentanteEmpresa", entregable.getFormARepresentanteEmpresa());
		map.put("dc3RepresentanteTrabajador", entregable.getFormARepresentanteTrabajador());
		map.put("dc3InstructorFirma", new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion())));
		
		return map;
	}
	
	private Map<String, Object> convertToDReporte( EntregableModelo entregable) {

		Map<String, Object> map = new HashMap<String, Object>();

		EntregableService entregableService = new EntregableService();
		map.put("reporteGeneralesInstructor", entregable.getFormCInstructor());
		map.put("reporteGeneralesCurso", entregable.getFormCCurso());
		map.put("reporteGeneralesFechas", entregable.getFormCFechaInicio().concat(" - ").concat(entregable.getFormCFechaFinal()));
		map.put("reporteGeneralesHorario", entregable.getFormCDuracion());
		map.put("reporteGeneralesCliente", entregable.getFormCRazonSocial());
		map.put("reporteGeneralesSede", entregable.getFormCSede());
		map.put("reporteComentarios", entregable.getFormCComentariosGrupo());
		map.put("reporteAprendizaje", entregable.getFormCProcesoAprendizaje());
		map.put("reporteTeoria", entregable.getFormCTeoria());
		map.put("reportePractica", entregable.getFormCPractica());
//		map.put("reporteEvidenciasFoto", entregable.getFormCEvidenciasFotograficasB());
		map.put("reporteRecomendaciones", entregable.getFormCRecomendaciones());
		map.put("reporteCumplimiento", entregable.getFormCNivelCumplimiento());
		map.put("reporteContingencias", entregable.getFormCContingencias());
		map.put("reporteAvances", entregable.getFormCAvancesLogrados());
//		map.put("reporteParticipantes", entregable.getFormBParticipantesStr());
//		map.put("reporteResultadosParticipantes", entregable.getFormBParticipantes());
		map.put("reporteObservaciones", entregable.getFormCObservaciones());
		map.put("reporteEvidenciasDocto", entregable.getFormCEvidenciaDoctoB());
		
		int i =1;
		for(String a : entregable.getFormCEvidenciasFotograficasB()) {
			if(!a.equals("")) {
				map.put("reporteEFoto"+i, new ByteArrayInputStream(entregableService.getImage(pathLogico + "/imagenesEvidencias/".concat(a))));
			}
			i++;
		}
		
		return map;

		
		
	}
	
	

	
	
	/*
	 * Private 3 generacion
	 */

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
	
	private String transformToCURP(String participanteCURP) {
		char [] palabra = participanteCURP.toCharArray();
		String[] espacios = {"    ","   ","   ","    ","   ","    ","   ","   ","    ","    ","   ","   ","    ","    ","    ","    ","    ","   ","   ","    ","     ","   "};
		String completa="";
		int i = 0;
		for(char a : palabra) {
			completa = completa+a+espacios[i];
			i++;
		}
		return completa;
	}

	private String transformToRFC(String formARFC) {
		char [] palabra = formARFC.toCharArray();
		String[] espacios = {"   ","   ","   ","    ","   ","   ","    ","    ","    ","   ","   ","   ","   ","   ","   "};
		String completa="";
		int i = 0;
		for(char a : palabra) {
			completa = completa+a+espacios[i];
			i++;
		}
		return completa;
	}
	
	private String transformToFI(String formAFechaInicioDC3) {
		String[] tmp = formAFechaInicioDC3.split(" ");
		String dia = convertToDia(tmp[1]);
		String mes = convertToMes(tmp[3]);
		String anio = tmp[5];
		String[] espacios = {"   ","   ","    ","     ","     ","     ","    ","    "};
		String fecha = anio+mes+dia;
		
		char [] palabra = fecha.toCharArray();
		String completa="";
		int i = 0;
		for(char a : palabra) {
			completa = completa+a+espacios[i];
			i++;
		}
		return completa;
	}
	
	
	private String transformToFF(String formAFechaFinDC3) {
		String[] tmp = formAFechaFinDC3.split(" ");
		String dia = convertToDia(tmp[1]);
		String mes = convertToMes(tmp[3]);
		String anio = tmp[5];
		String[] espacios = {"     ","       ","      ","      ","       ","      ","     ","     "};
		String fecha = anio+mes+dia;
		
		char [] palabra = fecha.toCharArray();
		String completa="";
		int i = 0;
		for(char a : palabra) {
			completa = completa+a+espacios[i];
			i++;
		}
		return completa;
	}

	private String recortaDia(String fecha) {
		String tmp = fecha.substring(fecha.indexOf(" "), fecha.length());
		return tmp;
	}

	private String convertToDia(String string) {
		int d = Integer.valueOf(string);
		if(d < 10) {
			return "0"+String.valueOf(d);
		}else {
			return String.valueOf(d);
		}
	}
	
	private String convertToMes(String string) {
		String[] mes = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		int i= 1;
		String r="";
		for( String m : mes) {
			if(m.equals(string)) {
				if(i < 10) {
					r ="0"+String.valueOf(i);
				}else {
					r = String.valueOf(i);
				}
			}
			i++;
		}
		return r;
	}
	
	private void crearDirectoriosDocumentacion(String idEmpresa, String idEntregable) {
		String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/documentacion/";
        File directorio = new File(directory);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Nuevo Directorio creado documentacion");
    		
            } else {
            	log.info("Error al crear directorio documentacion");
            }
        }
        directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/zip/";
        directorio = new File(directory);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Nuevo Directorio creado zip");
    		
            } else {
            	log.info("Error al crear directorio zip");
            }
        }
	}

}
