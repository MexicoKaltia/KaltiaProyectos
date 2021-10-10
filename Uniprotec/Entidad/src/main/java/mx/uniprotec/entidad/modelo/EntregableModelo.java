package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.JSONObject;

public class EntregableModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EntregableModelo() {
		// TODO Auto-generated constructor stub
	}
	
	private Long idEntregable;
	private Long idAsignacion;
	private String formARazonSocial;
	private String formARFC;
	private String formACurso;
	private String formADuracion;
	private String formAFechaInicioDC3;
	private String formAFechaFinDC3;
	private String formAFechaDiploma;
	private String formAEquipoCredencial;
	private String formAFechaInicioCredenciales;
	private String formAFechaFinalCredenciales;
	private String formAInstructor;
	private String formARepresentanteEmpresa;
	private String formARepresentanteTrabajador;
	private String formALogo;
	
	private List<ParticipantesModelo> formBParticipantes;
	private String formBParticipantesStr;
	private List<JSONObject> formBParticipantesListJson;
	private JSONObject formBParticipantesJson;
	
	private String formCRazonSocial;
	private String formCCurso;
	private String formCDuracion;
	private String formCInstructor;
	private String formCFechaInicio;
	private String formCFechaFinal;
	private String formCSede;
	private String formCComentariosGrupo;
	private String formCProcesoAprendizaje;
	private String formCTeoria;
	private String formCPractica;
	private List<String> formCEvidenciasFotograficas;
	private List<String> formCEvidenciasFotograficasB;
	private String formCRecomendaciones;
	private String formCNivelCumplimiento;
	private String formCContingencias;
	private String formCAvancesLogrados;
	private String formCObservaciones;
	private String formCEvidenciaDocto;
	
	private String status;
	private Long userCreate;
	private LocalDateTime createAt;
	
	
	
	

	
	public String getFormARazonSocial() {
		return formARazonSocial;
	}
	public void setFormARazonSocial(String formARazonSocial) {
		this.formARazonSocial = formARazonSocial;
	}
	public String getFormARFC() {
		return formARFC;
	}
	public void setFormARFC(String formARFC) {
		this.formARFC = formARFC;
	}
	public String getFormACurso() {
		return formACurso;
	}
	public void setFormACurso(String formACurso) {
		this.formACurso = formACurso;
	}
	public String getFormADuracion() {
		return formADuracion;
	}
	public void setFormADuracion(String formADuracion) {
		this.formADuracion = formADuracion;
	}
	public String getFormAFechaInicioDC3() {
		return formAFechaInicioDC3;
	}
	public void setFormAFechaInicioDC3(String formAFechaInicioDC3) {
		this.formAFechaInicioDC3 = formAFechaInicioDC3;
	}
	public String getFormAFechaFinDC3() {
		return formAFechaFinDC3;
	}
	public void setFormAFechaFinDC3(String formAFechaFinDC3) {
		this.formAFechaFinDC3 = formAFechaFinDC3;
	}
	public String getFormAFechaDiploma() {
		return formAFechaDiploma;
	}
	public void setFormAFechaDiploma(String formAFechaDiploma) {
		this.formAFechaDiploma = formAFechaDiploma;
	}
	public String getFormAEquipoCredencial() {
		return formAEquipoCredencial;
	}
	public void setFormAEquipoCredencial(String formAEquipoCredencial) {
		this.formAEquipoCredencial = formAEquipoCredencial;
	}
	public String getFormAFechaInicioCredenciales() {
		return formAFechaInicioCredenciales;
	}
	public void setFormAFechaInicioCredenciales(String formAFechaInicioCredenciales) {
		this.formAFechaInicioCredenciales = formAFechaInicioCredenciales;
	}
	public String getFormAFechaFinalCredenciales() {
		return formAFechaFinalCredenciales;
	}
	public void setFormAFechaFinalCredenciales(String formAFechaFinalCredenciales) {
		this.formAFechaFinalCredenciales = formAFechaFinalCredenciales;
	}
	public String getFormAInstructor() {
		return formAInstructor;
	}
	public void setFormAInstructor(String formAInstructor) {
		this.formAInstructor = formAInstructor;
	}
	public String getFormARepresentanteEmpresa() {
		return formARepresentanteEmpresa;
	}
	public void setFormARepresentanteEmpresa(String formARepresentanteEmpresa) {
		this.formARepresentanteEmpresa = formARepresentanteEmpresa;
	}
	public String getFormARepresentanteTrabajador() {
		return formARepresentanteTrabajador;
	}
	public void setFormARepresentanteTrabajador(String formARepresentanteTrabajador) {
		this.formARepresentanteTrabajador = formARepresentanteTrabajador;
	}
	public String getFormALogo() {
		return formALogo;
	}
	public void setFormALogo(String formALogo) {
		this.formALogo = formALogo;
	}
	public List<ParticipantesModelo> getFormBParticipantes() {
		return formBParticipantes;
	}
	public void setFormBParticipantes(List<ParticipantesModelo> formBParticipantes) {
		this.formBParticipantes = formBParticipantes;
	}
	public String getFormCRazonSocial() {
		return formCRazonSocial;
	}
	public void setFormCRazonSocial(String formCRazonSocial) {
		this.formCRazonSocial = formCRazonSocial;
	}
	public String getFormCCurso() {
		return formCCurso;
	}
	public void setFormCCurso(String formCCurso) {
		this.formCCurso = formCCurso;
	}
	public String getFormCDuracion() {
		return formCDuracion;
	}
	public void setFormCDuracion(String formCDuracion) {
		this.formCDuracion = formCDuracion;
	}
	public String getFormCInstructor() {
		return formCInstructor;
	}
	public void setFormCInstructor(String formCInstructor) {
		this.formCInstructor = formCInstructor;
	}
	public String getFormCSede() {
		return formCSede;
	}
	public void setFormCSede(String formCSede) {
		this.formCSede = formCSede;
	}
	public String getFormCComentariosGrupo() {
		return formCComentariosGrupo;
	}
	public void setFormCComentariosGrupo(String formCComentariosGrupo) {
		this.formCComentariosGrupo = formCComentariosGrupo;
	}
	public String getFormCProcesoAprendizaje() {
		return formCProcesoAprendizaje;
	}
	public void setFormCProcesoAprendizaje(String formCProcesoAprendizaje) {
		this.formCProcesoAprendizaje = formCProcesoAprendizaje;
	}
	public String getFormCTeoria() {
		return formCTeoria;
	}
	public void setFormCTeoria(String formCTeoria) {
		this.formCTeoria = formCTeoria;
	}
	public String getFormCPractica() {
		return formCPractica;
	}
	public void setFormCPractica(String formCPractica) {
		this.formCPractica = formCPractica;
	}
	public List<String> getFormCEvidenciasFotograficas() {
		return formCEvidenciasFotograficas;
	}
	public void setFormCEvidenciasFotograficas(List<String> formCEvidenciasFotograficas) {
		this.formCEvidenciasFotograficas = formCEvidenciasFotograficas;
	}
	public String getFormCRecomendaciones() {
		return formCRecomendaciones;
	}
	public void setFormCRecomendaciones(String formCRecomendaciones) {
		this.formCRecomendaciones = formCRecomendaciones;
	}
	public String getFormCNivelCumplimiento() {
		return formCNivelCumplimiento;
	}
	public void setFormCNivelCumplimiento(String formCNivelCumplimiento) {
		this.formCNivelCumplimiento = formCNivelCumplimiento;
	}
	public String getFormCContingencias() {
		return formCContingencias;
	}
	public void setFormCContingencias(String formCContingencias) {
		this.formCContingencias = formCContingencias;
	}
	public String getFormCAvancesLogrados() {
		return formCAvancesLogrados;
	}
	public void setFormCAvancesLogrados(String formCAvancesLogrados) {
		this.formCAvancesLogrados = formCAvancesLogrados;
	}
	public String getFormCObservaciones() {
		return formCObservaciones;
	}
	public void setFormCObservaciones(String formCObservaciones) {
		this.formCObservaciones = formCObservaciones;
	}
	public Long getIdEntregable() {
		return idEntregable;
	}
	public void setIdEntregable(Long idEntregable) {
		this.idEntregable = idEntregable;
	}
	public Long getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public String getFormCFechaInicio() {
		return formCFechaInicio;
	}
	public void setFormCFechaInicio(String formCFechaInicio) {
		this.formCFechaInicio = formCFechaInicio;
	}
	public String getFormCFechaFinal() {
		return formCFechaFinal;
	}
	public void setFormCFechaFinal(String formCFechaFinal) {
		this.formCFechaFinal = formCFechaFinal;
	}
	public String getFormCEvidenciaDocto() {
		return formCEvidenciaDocto;
	}
	public void setFormCEvidenciaDocto(String formCEvidenciaDocto) {
		this.formCEvidenciaDocto = formCEvidenciaDocto;
	}
	public String getFormBParticipantesStr() {
		return formBParticipantesStr;
	}
	public void setFormBParticipantesStr(String formBParticipantesStr) {
		this.formBParticipantesStr = formBParticipantesStr;
	}



	public List<JSONObject> getFormBParticipantesListJson() {
		return formBParticipantesListJson;
	}



	public void setFormBParticipantesListJson(List<JSONObject> formBParticipantesListJson) {
		this.formBParticipantesListJson = formBParticipantesListJson;
	}



	public JSONObject getFormBParticipantesJson() {
		return formBParticipantesJson;
	}



	public void setFormBParticipantesJson(JSONObject formBParticipantesJson) {
		this.formBParticipantesJson = formBParticipantesJson;
	}
	@Override
	public String toString() {
		return "EntregableModelo [idEntregable=" + idEntregable + ", idAsignacion=" + idAsignacion
				+ ", formARazonSocial=" + formARazonSocial + ", formARFC=" + formARFC + ", formACurso=" + formACurso
				+ ", formADuracion=" + formADuracion + ", formAFechaInicioDC3=" + formAFechaInicioDC3
				+ ", formAFechaFinDC3=" + formAFechaFinDC3 + ", formAFechaDiploma=" + formAFechaDiploma
				+ ", formAEquipoCredencial=" + formAEquipoCredencial + ", formAFechaInicioCredenciales="
				+ formAFechaInicioCredenciales + ", formAFechaFinalCredenciales=" + formAFechaFinalCredenciales
				+ ", formAInstructor=" + formAInstructor + ", formARepresentanteEmpresa=" + formARepresentanteEmpresa
				+ ", formARepresentanteTrabajador=" + formARepresentanteTrabajador + ", formALogo=" + formALogo
				+ ", formBParticipantes=" + formBParticipantes + ", formBParticipantesStr=" + formBParticipantesStr
				+ ", formBParticipantesListJson=" + formBParticipantesListJson + ", formBParticipantesJson="
				+ formBParticipantesJson + ", formCRazonSocial=" + formCRazonSocial + ", formCCurso=" + formCCurso
				+ ", formCDuracion=" + formCDuracion + ", formCInstructor=" + formCInstructor + ", formCFechaInicio="
				+ formCFechaInicio + ", formCFechaFinal=" + formCFechaFinal + ", formCSede=" + formCSede
				+ ", formCComentariosGrupo=" + formCComentariosGrupo + ", formCProcesoAprendizaje="
				+ formCProcesoAprendizaje + ", formCTeoria=" + formCTeoria + ", formCPractica=" + formCPractica
				+ ", formCEvidenciasFotograficas=" + formCEvidenciasFotograficas + ", formCEvidenciasFotograficasB="
				+ formCEvidenciasFotograficasB + ", formCRecomendaciones=" + formCRecomendaciones
				+ ", formCNivelCumplimiento=" + formCNivelCumplimiento + ", formCContingencias=" + formCContingencias
				+ ", formCAvancesLogrados=" + formCAvancesLogrados + ", formCObservaciones=" + formCObservaciones
				+ ", formCEvidenciaDocto=" + formCEvidenciaDocto + ", status=" + status + ", userCreate=" + userCreate
				+ ", createAt=" + createAt + "]";
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public List<String> getFormCEvidenciasFotograficasB() {
		return formCEvidenciasFotograficasB;
	}
	public void setFormCEvidenciasFotograficasB(List<String> formCEvidenciasFotograficasB) {
		this.formCEvidenciasFotograficasB = formCEvidenciasFotograficasB;
	}

	
	
	
}
