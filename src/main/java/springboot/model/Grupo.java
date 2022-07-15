package springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
public class Grupo {

	@Id
	private long id;

	@Column(name = "codigo_aula", nullable = false)
	private int codigo_aula;

	@Column(name = "codigo_grupo", nullable = false)
	private int codigo_grupo;

	@Column(name = "codigo_profesor", nullable = false)
	private int codigo_profesor;

	@Column(name = "horario_dia_semana", nullable = false)
	private int horario_dia_semana;

	@Column(name = "horario_hora_fin", nullable = false)
	private String horario_hora_fin;

	@Column(name = "horario_hora_inicio", nullable = false)
	private String horario_hora_inicio;

	@Column(name = "horario_quincenal", nullable = false)
	private String horario_quincenal;

	@Column(name = "nombre_asignatura", nullable = false)
	private String nombre_asignatura;

	@Column(name = "nombre_aula", nullable = false)
	private String nombre_aula;

	@Column(name = "nombre_edificio", nullable = false)
	private String nombre_edificio;

	@Column(name = "nombre_grupo", nullable = false)
	private String nombre_grupo;

	@Column(name = "nombre_profesor", nullable = false)
	private String nombre_profesor;

	@Column(name = "numero_alumnos", nullable = false)
	private int numero_alumnos;

	@Column(name = "periodo", nullable = false)
	private String periodo;

	public Grupo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCodigoAula() {
		return codigo_aula;
	}

	public void setCodigoAula(int codigo_aula) {
		this.codigo_aula = codigo_aula;
	}

	public int getCodigoGrupo() {
		return codigo_grupo;
	}

	public void setCodigoGrupo(int codigo_grupo) {
		this.codigo_grupo = codigo_grupo;
	}

	public int getCodigoProfesor() {
		return codigo_profesor;
	}

	public void setCodigoProfesor(int codigo_profesor) {
		this.codigo_profesor = codigo_profesor;
	}

	public int getHorarioDiaSemana() {
		return horario_dia_semana;
	}

	public void setHorarioDiaSemana(int horario_dia_semana) {
		this.horario_dia_semana = horario_dia_semana;
	}

	public String getHorarioHoraFin() {
		return horario_hora_fin;
	}

	public void setHorarioHoraFin(String horario_hora_fin) {
		this.horario_hora_fin = horario_hora_fin;
	}

	public String getHorarioHoraInicio() {
		return horario_hora_inicio;
	}

	public void setHorarioHoraInicio(String horario_hora_inicio) {
		this.horario_hora_inicio = horario_hora_inicio;
	}

	public String getHorarioQuincenal() {
		return horario_quincenal;
	}

	public void setHorarioQuincenal(String horario_quincenal) {
		this.horario_quincenal = horario_quincenal;
	}

	public String getNombreAsignatura() {
		return nombre_asignatura;
	}

	public void setNombreAsignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}

	public String getNombreAula() {
		return nombre_aula;
	}

	public void setNombreAula(String nombre_aula) {
		this.nombre_aula = nombre_aula;
	}

	public String getNombreEdificio() {
		return nombre_edificio;
	}

	public void setNombreEdificio(String nombre_edificio) {
		this.nombre_edificio = nombre_edificio;
	}

	public String getNombreGrupo() {
		return nombre_grupo;
	}

	public void setNombreGrupo(String nombre_grupo) {
		this.nombre_grupo = nombre_grupo;
	}

	public String getNombreProfesor() {
		return nombre_profesor;
	}

	public void setNombreProfesor(String nombre_profesor) {
		this.nombre_profesor = nombre_profesor;
	}

	public int getNumeroAlumnos() {
		return numero_alumnos;
	}

	public void setNumeroAlumnos(int numero_alumnos) {
		this.numero_alumnos = numero_alumnos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
