package modelo;

import java.time.LocalDate;

public class Jefe extends Empleado {
	private static final long serialVersionUID = 2L;

	private double incentivo;

	public Jefe(String nombre, double sueldo, String departamento, LocalDate fechaContrato, double incentivo) {
		super(nombre, sueldo, departamento, fechaContrato);
		this.incentivo = incentivo;
	}

	public double getIncentivo() { return incentivo; }
	public void setIncentivo(double incentivo) { this.incentivo = incentivo; }

	@Override
	public String toString() {
		return "Jefe [" + super.toString() + ", incentivo=" + incentivo + "]";
	}
}
