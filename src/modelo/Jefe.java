package modelo;

import java.time.LocalDate;

public class Jefe extends Empleado{
	
	private int incentivo;
	
	public Jefe(String nombre, int sueldo, String departamento, LocalDate fechaContrato,
			int incentivo) {
		super(nombre, sueldo, departamento, fechaContrato);
		this.incentivo = incentivo;
	}

	public int getIncentivo() {
		return incentivo;
	}

	public void setIncentivo(int incentivo) {
		this.incentivo = incentivo;
	}

	@Override
	public String toString() {
		return "Jefe [" + super.toString() + "incentivo=" + incentivo + "]";
	}
	
	
	
	
}
