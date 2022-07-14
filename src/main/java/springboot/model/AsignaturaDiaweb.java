package springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignatura")
public class AsignaturaDiaweb {

	@Id
	private long id;

	@Column(name = "caracter_asignatura", nullable = false)
	private String caracter_asignatura;

	@Column(name = "codigo_asignatura", nullable = false)
	private int codigo_asignatura;

	@Column(name = "codigo_ocurrencia", nullable = false)
	private int codigo_ocurrencia;

	@Column(name = "creditos_practica", nullable = false)
	private float creditos_practica;

	@Column(name = "creditos_teoria", nullable = false)
	private float creditos_teoria;

	@Column(name = "curso_asignatura", nullable = false)
	private int curso_asignatura;

	@Column(name = "nombre_asignatura", nullable = false)
	private String nombre_asignatura;

	@Column(name = "periodo_asignatura", nullable = false)
	private String periodo_asignatura;

	@Column(name = "responsable_asignatura", nullable = false)
	private String responsable_asignatura;

	@Column(name = "titulacion_asignatura", nullable = false)
	private String titulacion_asignatura;

	public AsignaturaDiaweb() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaracterAsignatura() {
		return caracter_asignatura;
	}

	public void setCaracterAsignatura(String caracter_asignatura) {
		this.caracter_asignatura = caracter_asignatura;
	}

	public int getCodigoAsignatura() {
		return codigo_asignatura;
	}

	public void setCodigoAsignatura(int codigo_asignatura) {
		this.codigo_asignatura = codigo_asignatura;
	}

	public int getCodigoOcurrencia() {
		return codigo_ocurrencia;
	}

	public void setCodigoOcurrencia(int codigo_ocurrencia) {
		this.codigo_ocurrencia = codigo_ocurrencia;
	}

	public float getCreditosPractica() {
		return creditos_practica;
	}

	public void setCreditosPractica(float creditos_practica) {
		this.creditos_practica = creditos_practica;
	}

	public float getCreditosTeoria() {
		return creditos_teoria;
	}

	public void setCreditosTeoria(float creditos_teoria) {
		this.creditos_teoria = creditos_teoria;
	}

	public int getCursoAsignatura() {
		return curso_asignatura;
	}

	public void setCursoAsignatura(int curso_asignatura) {
		this.curso_asignatura = curso_asignatura;
	}

	public String getNombreAsignatura() {
		return nombre_asignatura;
	}

	public void setNombreAsignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}

	public String getPeriodoAsignatura() {
		return periodo_asignatura;
	}

	public void setPeriodoAsignatura(String periodo_asignatura) {
		this.periodo_asignatura = periodo_asignatura;
	}

	public String getResponsableAsignatura() {
		return responsable_asignatura;
	}

	public void setResponsableAsignatura(String responsable_asignatura) {
		this.responsable_asignatura = responsable_asignatura;
	}

	public String getTitulacionAsignatura() {
		return titulacion_asignatura;
	}

	public void setTitulacionAsignatura(String titulacion_asignatura) {
		this.titulacion_asignatura = titulacion_asignatura;
	}

}
