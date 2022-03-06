package springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "titulacion")
public class Titulacion {

	@Id
	private long id;

	@Column(name = "centro", nullable = false)
	private String centro;

	@Column(name = "codigo", nullable = false)
	private String codigo;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "numero_cursos", nullable = false)
	private int numero_cursos;

	public Titulacion() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroCursos() {
		return numero_cursos;
	}

	public void setNumeroCursos(int numero_cursos) {
		this.numero_cursos = numero_cursos;
	}

}
