package mx.uniprotec.inicio.service;

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

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.PreAsignacionAE;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ReporteSemanalModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.VendedorDEModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class DatosEconomicosService implements IDatosEconomicosService{
	private static Logger log = LoggerFactory.getLogger(DatosEconomicosService.class);

	MonitorEntidades me = new MonitorEntidades();
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IInstructorService instructorService;
	
	public DatosEconomicosService() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public ResultVO altaDatosEconomicos(DatosEconomicosModelo datosEconomicosItem, String accesToken) {
		
		me = ComponenteComun.monitorCampos();
		
		datosEconomicosItem.setCreateAtAsignacion(me.getNowEntidad());
		datosEconomicosItem.setStatus("ALTA");
		if(datosEconomicosItem.getIdDatosEconomicos()!=null) {
			datosEconomicosItem.setStatus("ACTUALIZADO");
		}
		if(datosEconomicosItem.getIdDatosEconomicos()==null && datosEconomicosItem.getIdAsignacion()==null) {
			datosEconomicosItem.setStatus("SIN_ASIGNACION");
		}
		
		List<VendedorDEModelo> vendedores = getVendedores(datosEconomicosItem.getVendedoresStr(),  datosEconomicosItem.getVentaReal());
		datosEconomicosItem.setVendedores(vendedores);
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken, BaseClientRest.URL_CRUD_DATOSECONOMICOS, datosEconomicosItem);

		return resultVO;
	}


	@Override
	public ResultVO consultaDatosEconomicos(String token) {		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_DATOSECONOMICOS);
		return rs;
	}
	
	@Override
	public ResultVO deleteDatosEconomicos(String idAsignacion, String accesToken) {
	ResultVO rs= (ResultVO) baseClientRest.objetoDeleteId(idAsignacion, accesToken , BaseClientRest.URL_CRUD_DATOSECONOMICOS);
		
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonResponseObject = new JSONObject();
			jsonResponseObject.put("mensaje", jsonGeneral.get("mensaje"));
			rs.setJsonResponseObject(jsonResponseObject);
		}
		return rs;
	}
	
	@Override
	public ResultVO actualizaDatosEconomicos(DatosEconomicosModelo datosEconomicos, String accesToken) {
		me = ComponenteComun.monitorCampos();
		datosEconomicos.setCreateAtAsignacion(me.getNowEntidad());
		resultVO = (ResultVO) baseClientRest.objetoPut(
				accesToken,
				BaseClientRest.URL_CRUD_DATOSECONOMICOS,
				datosEconomicos,
				datosEconomicos.getIdDatosEconomicos());
			
		return resultVO;
	}
	
	

	
	@Override
	public ResultVO consultaVendedoresAnalisis(String token) {
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonConsulta = new JSONObject();
			jsonConsulta.put("vendedores", jsonGeneral.get("vendedores"));
			rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
			jsonGeneral = rs.getJsonResponse();
			JSONObject jsonAsignaciones = new JSONObject();
			jsonConsulta.put("asignaciones", jsonGeneral.get("asignaciones"));
			if(rs.getCodigo() == 202) {
				rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_DATOSECONOMICOS);
				jsonGeneral = rs.getJsonResponse();
				JSONObject jsonPreAsignaciones = new JSONObject();
				JSONObject jsonPreAsignacionesAE = new JSONObject();
//				jsonConsulta.put("preAsignaciones", jsonGeneral.get("preAsignaciones"));
				jsonConsulta.put("datosEconomicos", jsonGeneral.get("datosEconomicos"));
				
				rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CLIENTES);
				if(rs.getCodigo() == 202) {
					jsonGeneral = rs.getJsonResponse();
					JSONObject jsonClientes = new JSONObject();
					jsonConsulta.put("clientes", jsonGeneral.get("clientes"));
					rs = consultaVendoresDatosEconomicos(token);
					if(rs.getCodigo() == 202) {
						JSONObject jsonVendedoresDatosEconomicos = rs.getJsonResponse();
						jsonConsulta.put("vendedoresDatosEconomicos", jsonVendedoresDatosEconomicos.get("vendedoresDatosEconomicos"));
					}			
				}
			rs.setJsonResponseObject(jsonConsulta);
			}
		}
		return rs;
	}	


	@Override
	public ResultVO consultaVendoresDatosEconomicos(String token) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORESDATOSECONOMICOS);
		return rs;
	}
	
	
	@Override
	public ResultVO altaReporteSemanal(ReporteSemanalModelo reporteSemanalItem, String accesToken) {
		me = ComponenteComun.monitorCampos();
		
		reporteSemanalItem.setCreateAt(me.getNowEntidad());
		reporteSemanalItem.setStatus("ALTA");
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken, BaseClientRest.URL_CRUD_REPORTESEMANAL, reporteSemanalItem);

		return resultVO;

	}

	
	
	/*
	 * PRIVATE
	 */

	private String fecha(String fechaAsignacion) {
		String[] fechas = fechaAsignacion.split("/");
		return fechas[1]+fechas[0]+fechas[2];
	}

	private List<VendedorDEModelo> getVendedores(String vendedoresStr,  Double ventaReal) {
		
		List<VendedorDEModelo> vendedores = new ArrayList<VendedorDEModelo>();
		String[] tmp = vendedoresStr.split("},");
		log.info("Cantidad de Vendedores : "+String.valueOf(tmp.length));
		Double montoFacturaDivida = ventaReal/tmp.length;
		if(tmp.length > 0) {
			for(String a : tmp) {
				if(!a.contains("}")) {
					a = a.concat("}");
				}
				JSONParser parser = new JSONParser();
				try {
					VendedorDEModelo vendedor = new VendedorDEModelo();
					JSONObject json = (JSONObject) parser.parse(a);
					
					vendedor.setIdVendedorAsignacion(Long.valueOf(json.get("idVendedorAsignacion").toString()));
					if(json.get("idAsignacion")==null) {
						vendedor.setIdAsignacion(null);
					}else {
						vendedor.setIdAsignacion(Long.valueOf(json.get("idAsignacion").toString()));
					}
					vendedor.setIdDatosEconomicos(null);
					vendedor.setNombreVendedor(json.get("nombreVendedor").toString());
					vendedor.setComisionRealVendedor(Double.valueOf(json.get("comisionRealVendedor").toString()));
					vendedor.setPorcentajeComisionVendedor(Double.valueOf(json.get("porcentajeComisionVendedor").toString()));
					vendedor.setMontoFacturaDivida(montoFacturaDivida);
					
					vendedores.add(vendedor);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return vendedores;
	}







}
