package mx.uniprotec.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import mx.uniprotec.entidad.modelo.ParticipantesModelo;

@Entity
@Table(name="entregables")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idEntregable")
public class EntregableEntity {

	public EntregableEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntregable;
	@Column
	private Long idAsignacion;
	@Column
	private String formARazonSocial;
	@Column
	private String formARFC;
	@Column
	private String formACurso;
	@Column
	private String formADuracion;
	@Column
	private String formAFechaInicioDC3;
	@Column
	private String formAFechaFinDC3;
	@Column
	private String formAFechaDiploma;
	@Column
	private String formAEquipoCredencial;
	@Column
	private String formAFechaInicioCredenciales;
	@Column
	private String formAFechaFinalCredenciales;
	@Column
	private String formAInstructor;
	@Column
	private String formARepresentanteEmpresa;
	@Column
	private String formARepresentanteTrabajador;
	@Column
	private String formALogo;
	
//	@OneToMany(cascade = CascadeType.MERGE,  mappedBy="entregable")
////	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
////	@JoinTable(name="entregable_participante", joinColumns= @JoinColumn(name="entregable_id"),
////	inverseJoinColumns=@JoinColumn(name="participante_id"),
////	uniqueConstraints= {@UniqueConstraint(columnNames= {"entregable_id", "participante_id"})})
////	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private List<ParticipanteEntity> formBParticipantes = new ArrayList<>();
	
	@Column
	private String formCRazonSocial;
	@Column
	private String formCCurso;
	@Column
	private String formCDuracion;
	@Column
	private String formCInstructor;
	@Column
	private String formCFechaInicio;
	@Column
	private String formCFechaFinal;
	@Column
	private String formCSede;
	@Column
	private String formCComentariosGrupo;
	@Column
	private String formCProcesoAprendizaje;
	@Column
	private String formCTeoria;
	@Column
	private String formCPractica;
	@Column
	private String formCEvidenciasFotograficas;
	@Column
	private String formCRecomendaciones;
	@Column
	private String formCNivelCumplimiento;
	@Column
	private String formCContingencias;
	@Column
	private String formCAvancesLogrados;
	@Column
	private String formCObservaciones;
	@Column
	private String formCEvidenciaDocto;
	@Column
	private String RFCOriginalAsignacion;
	@Column
	private String statusEntregable;
	@Column
	private Long userCreateEntregable;
	@Column
	private LocalDateTime createAtEntregable;

	
	
	@Override
	public String toString() {
		return "EntregableEntity [idEntregable=" + idEntregable + ", formARazonSocial=" + formARazonSocial
				+ ", formARFC=" + formARFC + ", formACurso=" + formACurso + ", formADuracion=" + formADuracion
				+ ", formAFechaInicioDC3=" + formAFechaInicioDC3 + ", formAFechaFinDC3=" + formAFechaFinDC3
				+ ", formAFechaDiploma=" + formAFechaDiploma + ", formAEquipoCredencial=" + formAEquipoCredencial
				+ ", formAFechaInicioCredenciales=" + formAFechaInicioCredenciales + ", formAFechaFinalCredenciales="
				+ formAFechaFinalCredenciales + ", formAInstructor=" + formAInstructor + ", formARepresentanteEmpresa="
				+ formARepresentanteEmpresa + ", formARepresentanteTrabajador=" + formARepresentanteTrabajador
				+ ", formALogo=" + formALogo +  ", formCRazonSocial="
				+ formCRazonSocial + ", formCCurso=" + formCCurso + ", formCDuracion=" + formCDuracion
				+ ", formCInstructor=" + formCInstructor + ", formCFechas=" + formCFechaInicio+"-"+ formCFechaFinal + ", formCSede=" + formCSede
				+ ", formCComentariosGrupo=" + formCComentariosGrupo + ", formCProcesoAprendizaje="
				+ formCProcesoAprendizaje + ", formCTeoria=" + formCTeoria + ", formCPractica=" + formCPractica
				+ ", formCEvidenciasFotograficas=" + formCEvidenciasFotograficas + ", formCRecomendaciones="
				+ formCRecomendaciones + ", formCNivelCumplimiento=" + formCNivelCumplimiento + ", formCContingencias="
				+ formCContingencias + ", formCAvancesLogrados=" + formCAvancesLogrados + ", formCObservaciones="
				+ formCObservaciones + ", formCEvidenciaDocto=" + formCEvidenciaDocto + "]";
	}
	public Long getIdEntregable() {
		return idEntregable;
	}
	public void setIdEntregable(Long idEntregable) {
		this.idEntregable = idEntregable;
	}
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
	public String getFormCEvidenciasFotograficas() {
		return formCEvidenciasFotograficas;
	}
	public void setFormCEvidenciasFotograficas(String formCEvidenciasFotograficas) {
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
	public String getFormCEvidenciaDocto() {
		return formCEvidenciaDocto;
	}
	public void setFormCEvidenciaDocto(String formCEvidenciaDocto) {
		this.formCEvidenciaDocto = formCEvidenciaDocto;
	}
	public Long getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
//	public void setFormBParticipantes(List<ParticipanteEntity> formBParticipantes) {
//		this.formBParticipantes = formBParticipantes;
//	}
//	public List<ParticipanteEntity> getFormBParticipantes() {
//		return formBParticipantes;
//	}
	public String getStatusEntregable() {
		return statusEntregable;
	}
	public void setStatusEntregable(String statusEntregable) {
		this.statusEntregable = statusEntregable;
	}
	public Long getUserCreateEntregable() {
		return userCreateEntregable;
	}
	public void setUserCreateEntregable(Long userCreateEntregable) {
		this.userCreateEntregable = userCreateEntregable;
	}
	public LocalDateTime getCreateAtEntregable() {
		return createAtEntregable;
	}
	public void setCreateAtEntregable(LocalDateTime createAtEntregable) {
		this.createAtEntregable = createAtEntregable;
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
	public String getRFCOriginalAsignacion() {
		return RFCOriginalAsignacion;
	}
	public void setRFCOriginalAsignacion(String rFCOriginalAsignacion) {
		RFCOriginalAsignacion = rFCOriginalAsignacion;
	}
	
	

}
