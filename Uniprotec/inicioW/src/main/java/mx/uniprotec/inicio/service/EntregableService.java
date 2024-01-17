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
//import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import mx.uniprotec.inicio.controller.ControllerUtil;
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
	@Autowired
	ControllerUtil controllerUtil ;
	
	protected final Log log = LogFactory.getLog(getClass());
	private String pathLogico = "";
	public EntregableService() {
		// TODO Auto-generated constructor stub
	}
	
	MonitorEntidades me = new MonitorEntidades();

	@SuppressWarnings("unchecked")
	@Override
	public ResultVO consultaEntregable(String token, Long idAsignacion) {
		ResultVO rs= (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_ENTREGABLE, null, idAsignacion.toString());
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonEntregables = new JSONObject();
			jsonEntregables.put("entregables", jsonGeneral.get("entregables"));
			jsonEntregables.put("participantes", jsonGeneral.get("participantes"));
//			rs= (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_PARTICIPANTES_IMPORTAR, null, idAsignacion.toString());
//			if(rs.getCodigo() != 500) {
//				jsonGeneral = rs.getJsonResponse();
//				jsonEntregables.put("prtsImportar", jsonGeneral.get("prtsImportar"));
//			}
			rs.setJsonResponseObject(jsonEntregables);
		}
			return rs;
		
	}
	
	@Override
	public ResultVO createEntregable(EntregableModelo entregable, String accesToken, Long idUsuario) {
		log.info(entregable.toString());
		List<ParticipantesModelo> participantes = getParticipantes(entregable, idUsuario);
		this.pathLogico = "/uniprotec/entregables/";
		log.info(participantes.toString());
		
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
				
				String pathImageLogo = this.pathLogico +"/"+ idEmpresa +"/"+ idEntregable+"/imageLogo/";
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

			}
			
			
			
			/*
			 * Genera Documentacion
			 */
			
			if(entregable.isAltaDocto()) {
				entregable.setStatus("Entregable Generado");
				ResultLocal rl = new ResultLocal();
				this.pathLogico = this.pathLogico + idEmpresa+"/"+ idEntregable;
				
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
		        	 * copiar archivo de reporte externo a documentacion
		        	 */
		        
					String directoryExterno = this.pathLogico+"/externo/";
			        File directorioExterno = new File(directoryExterno);				        	
		        	if (directorioExterno.exists()) {
		        		try {
			        		log.info("Copiar Archivo reporte externo");
							copyArchivos();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
		        	
				       	/*
					        * comprimir archivos files a documentacion/evidenciaDocto.zip 
					        */
				       	String directory = this.pathLogico+"/file/";
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
//						FileUtils.deleteDirectory(directorio);
						directorio.delete();
						rl.setCodigo(0);
						rl.setMensaje("Borrar PDF's exitosamente");
//						FileUtils.forceMkdir(directorio);
						directorio.mkdir();
						entregable.setStatus("Entregable Generado");
					} catch (Exception e) {
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
							participante.setIdCliente(entregable.getIdCliente());
							
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
		OutputStream output = new FileOutputStream(new File(this.pathLogico + "/documentacion/diplomas_"+entregable.getIdEntregableLogico()+".pdf"));
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
		String fileName =this.pathLogico + "/documentacion/credenciales_"+entregable.getIdEntregableLogico()+".pdf";
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
		OutputStream output = new FileOutputStream(new File(this.pathLogico + "/documentacion/DC3_"+entregable.getIdEntregableLogico()+".pdf"));
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
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,  new JREmptyDataSource());		
		jasperPrintReporte.add(report);
		
		//Genera Resultados Participantes
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
		OutputStream output = new FileOutputStream(new File(this.pathLogico + "/documentacion/Reporte_"+entregable.getIdEntregableLogico()+".pdf"));
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
	
	
	  private void copyArchivos() throws Exception {
	    	
			Path origenPath = FileSystems.getDefault().getPath( this.pathLogico +"/externo/");
		    Path destinoPath = FileSystems.getDefault().getPath( this.pathLogico +"/documentacion/");
		    File  f = new File(origenPath.toString());
		    String[] paths = f.list();

		    try {
		    	for(String path:paths) {
		    		origenPath = origenPath.resolve(path);
		    		destinoPath = destinoPath.resolve(path);
		    		Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
		    	}
		    	
		    } catch (IOException e) {
		      System.err.println(e);
		    }	    
		}
	
    
    private void compressFile(String idEmpresa, String idEntregable, String fileOutPut, String folderInput) throws Exception {
    	byte[] buffer = new byte[20480];
//        String pathLogico = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable;
        
//          ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("target/file.zip"))));
          FileOutputStream fos = new FileOutputStream(this.pathLogico+fileOutPut);
          ZipOutputStream zos = new ZipOutputStream(fos);
          
          try{  
          
              
              // create new file
        	  String rutaCarpeta = this.pathLogico+folderInput;
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
				cm.setLogoEmpresa(new ByteArrayInputStream(entregableService.getImage(this.pathLogico + "/imageLogo/".concat(entregable.getFormALogoEmpresa()))));
//				cm.setFotoParticipante(new ByteArrayInputStream(entregableService.getImage(this.pathLogico + "/imagenesParticipantes/".concat(pm.getParticipanteFoto()))));
				cm.setFotoParticipante(new ByteArrayInputStream(entregableService.getImage("/uniprotec/entregables/"+entregable.getRfcOriginalAsignacion()+"/imagenesParticipantes/".concat(pm.getParticipanteFoto()))));
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
		map.put("diplomaDuracion",formatHoraDuracion(entregable.getFormADuracion()));
		map.put("diplomaNombreInstructor", entregable.getFormAInstructor());
		map.put("diplomaNombreDirector", "Olivier Sánchez");
				
		EntregableService entregableService = new EntregableService();
		InputStream diplomaFirmaInstructor = new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/"+entregable.getIdInstructorAsignacion()+"/image/"+entregable.getNombreFirmaInstructorAsignacion()));
		map.put("diplomaFirmaInstructor", diplomaFirmaInstructor );
		InputStream diplomaFirmaDirector = new ByteArrayInputStream(entregableService.getImage("/uniprotec/firmaInstructor/director/image/1.jpg"));
		map.put("diplomaFirmaDirector", diplomaFirmaDirector);
		
		return map;
	}
	
	private Object formatHoraDuracion(String formADuracion) {
		if(formADuracion.contains(":")) {
			String[] tmp = formADuracion.split(":");
			return tmp[0];
		}else {
			return formADuracion;
		}
	}

	private Map<String, Object> convertToDC3(ParticipantesModelo pm, EntregableModelo entregable) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(pm.getParticipanteCURP().equals(null) || pm.getParticipanteCURP().equals("")) {
			pm.setParticipanteCURP("                  ");
		}
		 char[] ch = pm.getParticipanteCURP().toCharArray();
		 char[] rfc = entregable.getFormARFC().toCharArray();

		EntregableService entregableService = new EntregableService();
		map.put("dc3EmpresaLogo", new ByteArrayInputStream(entregableService.getImage(this.pathLogico + "/imageLogo/".concat(entregable.getFormALogoEmpresa()))));
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
		map.put("dc3CURP1", ch[0]);
		map.put("dc3CURP2", ch[1]);
		map.put("dc3CURP3", ch[2]);
		map.put("dc3CURP4", ch[3]);
		map.put("dc3CURP5", ch[4]);
		map.put("dc3CURP6", ch[5]);
		map.put("dc3CURP7", ch[6]);
		map.put("dc3CURP8", ch[7]);
		map.put("dc3CURP9", ch[8]);
		map.put("dc3CURP10", ch[9]);
		map.put("dc3CURP11", ch[10]);
		map.put("dc3CURP12", ch[11]);
		map.put("dc3CURP13", ch[12]);
		map.put("dc3CURP14", ch[13]);
		map.put("dc3CURP15", ch[14]);
		map.put("dc3CURP16", ch[15]);
		map.put("dc3CURP17", ch[16]);
		map.put("dc3CURP18", ch[17]);
		map.put("rfc0", rfc[0]);
		map.put("rfc1", rfc[1]);
		map.put("rfc2", rfc[2]);
		map.put("rfc3", rfc[3]);
		map.put("rfc4", rfc[4]);
		map.put("rfc5", rfc[5]);
		map.put("rfc6", rfc[6]);
		map.put("rfc7", rfc[7]);
		map.put("rfc8", rfc[8]);
		map.put("rfc9", rfc[9]);
		map.put("rfc10", rfc[10]);
		map.put("rfc11", rfc[11]);
		map.put("rfc12", ' ');
		if(rfc.length == 13) {
			map.put("rfc12", rfc[12]);
		}
		


		
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
//		map.put("reporteComentarios", entregable.getFormCComentariosGrupo());
//		map.put("reporteAprendizaje", entregable.getFormCProcesoAprendizaje());
//		map.put("reporteTeoria", entregable.getFormCTeoria());
//		map.put("reportePractica", entregable.getFormCPractica());
		map.put("reporteEvidenciasFoto", entregable.getFormCEvidenciasFotograficas());
//		map.put("reporteRecomendaciones", entregable.getFormCRecomendaciones());
//		map.put("reporteCumplimiento", entregable.getFormCNivelCumplimiento());
//		map.put("reporteContingencias", entregable.getFormCContingencias());
//		map.put("reporteAvances", entregable.getFormCAvancesLogrados());
//		map.put("reporteParticipantes", entregable.getFormBParticipantesStr());
//		map.put("reporteResultadosParticipantes", entregable.getFormBParticipantes());
//		map.put("reporteObservaciones", entregable.getFormCObservaciones());
		map.put("reporteEvidenciasDocto", entregable.getFormCEvidenciaDoctoB());
		
//		int i =1;
//		for(String a : entregable.getFormCEvidenciasFotograficasB()) {
//			if(!a.equals("")) {
//				map.put("reporteEFoto"+i, new ByteArrayInputStream(entregableService.getImage(this.pathLogico + "/imagenesEvidencias/".concat(a))));
//			}
//			i++;
//		}
		
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
		String[] espacios = {"   ","   ","   ","    ","   ","    ","   ","    ","    ","   ","    ","   ","   ","   ","   "};
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
		String[] espacios = {"    ","     ","    ","     ","    ","     ","    ","   "};
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
		String[] espacios = {"    ","     ","     ","    ","    ","     ","     ","    "};
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
		tmp = tmp.replaceAll("de ", "");
		tmp = tmp.substring(1, tmp.length());
		String[] tmp2 = tmp.split(" ");
		String mes = convertToMes2(tmp2[1]);
		
		return tmp2[0].concat(" ").concat(mes).concat(" ").concat(tmp2[2]);

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
	
	private String convertToMes2(String string) {
		String[] mes = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
	String r = string.substring(0, 3);
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
