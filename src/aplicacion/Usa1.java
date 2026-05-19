package aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Empleado;
import modelo.Jefe;

public class Usa1 {

	public static void main(String[] args) {
		
		ArrayList<Empleado> empleados = new ArrayList<>();
		
		empleados.add(new Empleado("Maria", 1500, "CONTABILIDAD", LocalDate.of(2009, 5, 30)));
		empleados.add(new Empleado("Laura", 1500, "CONTABILIDAD", LocalDate.of(2010, 5, 30)));
		empleados.add(new Jefe("Rosa", 1500, "CONTABILIDAD", LocalDate.of(2011, 5, 30), 40));

		empleados.add(new Empleado("Fernando", 1500, "INFORMATICA", LocalDate.of(20012, 5, 30)));
		empleados.add(new Empleado("Amancio", 1500, "INFORMATICA", LocalDate.of(2013, 5, 30)));
		empleados.add(new Jefe("Florentino", 1500, "INFORMATICA", LocalDate.of(2014, 5, 30), 40));
		
		empleados.add(new Empleado("Nuria", 1500, "VENTAS", LocalDate.of(2015, 5, 30)));
		empleados.add(new Empleado("Raul", 1500, "VENTAS", LocalDate.of(2016, 5, 30)));
		empleados.add(new Jefe("Paca", 1500, "VENTAS", LocalDate.of(2017, 5, 30), 40));
		
		empleados.add(new Empleado("Alexis", 1500, "RECURSOS_HUMANOS", LocalDate.of(2018, 5, 30)));
		empleados.add(new Empleado("Ainara", 1500, "RECURSOS_HUMANOS", LocalDate.of(2019, 5, 30)));
		empleados.add(new Jefe("Yibril", 1500, "RECURSOS_HUMANOS", LocalDate.of(2020, 5, 30), 40));
		
		for (Empleado empleado : empleados) {
			System.out.println(empleado.toString());
		}
		
	}

}
