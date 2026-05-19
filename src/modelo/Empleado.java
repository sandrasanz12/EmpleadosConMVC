package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Empleado implements Serializable{
	private String nombre;
	private int sueldo;
	private String departamento;
	private LocalDate fechaContrato;
	
	public Empleado(String nombre, int sueldo, String departamento, LocalDate fechaContrato) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.departamento = departamento;
		this.fechaContrato = fechaContrato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public LocalDate getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(LocalDate fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", sueldo=" + sueldo + ", departamento=" + departamento
				+ ", fechaContrato=" + fechaContrato + "]";
	}
	
	
	
}
