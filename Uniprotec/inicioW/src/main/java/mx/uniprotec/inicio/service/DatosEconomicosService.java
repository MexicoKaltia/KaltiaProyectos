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
		
		List<VendedorDEModelo> vendedores = getVendedores(datosEconomicosItem.getVendedoresStr(), datosEconomicosItem.getIdAsignacion(), datosEconomicosItem.getVentaReal());
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
				datosEconomicos.getIdAsignacion());
			
		return resultVO;
	}

	@Override
	public ResultVO consultaVendoresDatosEconomicos(String token) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORESDATOSECONOMICOS);
		return rs;
	}
	
	
	
	
	
	/*
	 * PRIVATE
	 */

	private String fecha(String fechaAsignacion) {
		String[] fechas = fechaAsignacion.split("/");
		return fechas[1]+fechas[0]+fechas[2];
	}

	private List<VendedorDEModelo> getVendedores(String vendedoresStr, Long idAsignacion, Double ventaReal) {
		
		List<VendedorDEModelo> vendedores = new ArrayList<VendedorDEModelo>();
		String[] tmp = vendedoresStr.split("},");
		log.info(String.valueOf(tmp.length));
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
					vendedor.setIdAsignacion(Long.valueOf(json.get("idAsignacion").toString()));
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
