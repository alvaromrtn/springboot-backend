package springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignatura_excel")
public class AsignaturaExcel {

	@Id
	private long id;

	@Column(name = "codigo_asignatura", nullable = false)
	private int codigo_asignatura;

	@Column(name = "nombre_asignatura", nullable = false)
	private String nombre_asignatura;

	@Column(name = "nombre_titulacion", nullable = false)
	private String nombre_titulacion;

	@Column(name = "curso_asignatura", nullable = false)
	private String curso_asignatura;

	@Column(name = "periodo_asignatura", nullable = false)
	private String periodo_asignatura;

	@Column(name = "caracter_asignatura", nullable = false)
	private String caracter_asignatura;

	@Column(name = "creditos_teoria", nullable = false)
	private String creditos_teoria;

	@Column(name = "creditos_practica", nullable = false)
	private String creditos_practica;
	
	@Column(name = "horas_totales", nullable = false)
	private float horas_totales;
	
	@Column(name = "horas_cuat1", nullable = false)
	private float horas_cuat1;

	public AsignaturaExcel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCodigoAsignatura() {
		return codigo_asignatura;
	}

	public void setCodigoAsignatura(int codigo_asignatura) {
		this.codigo_asignatura = codigo_asignatura;
	}

	public String getNombreAsignatura() {
		return nombre_asignatura;
	}

	public void setNombreAsignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}

	public String getNombreTitulacion() {
		return nombre_titulacion;
	}

	public void setNombreTitulacion(String nombre_titulacion) {
		this.nombre_titulacion = nombre_titulacion;
	}

	public String getCursoAsignatura() {
		return curso_asignatura;
	}

	public void setCursoAsignatura(String curso_asignatura) {
		this.curso_asignatura = curso_asignatura;
	}

	public String getPeriodoAsignatura() {
		return periodo_asignatura;
	}

	public void setPeriodoAsignatura(String periodo_asignatura) {
		this.periodo_asignatura = periodo_asignatura;
	}

	public String getCaracterAsignatura() {
		return caracter_asignatura;
	}

	public void setCaracterAsignatura(String caracter_asignatura) {
		this.caracter_asignatura = caracter_asignatura;
	}

	public String getCreditosTeoria() {
		return creditos_teoria;
	}

	public void setCreditosTeoria(String creditos_teoria) {
		this.creditos_teoria = creditos_teoria;
	}

	public String getCreditosPractica() {
		return creditos_practica;
	}

	public void setCreditosPractica(String creditos_practica) {
		this.creditos_practica = creditos_practica;
	}
	
	public float getHorasTotales() {
		return horas_totales;
	}

	public void setHorasTotales(float horas_totales) {
		this.horas_totales = horas_totales;
	}
	
	public float getHorasCuat1() {
		return horas_cuat1;
	}

	public void setHorasCuat1(float horas_cuat1) {
		this.horas_cuat1 = horas_cuat1;
	}

}
