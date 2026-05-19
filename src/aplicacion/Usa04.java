package aplicacion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.swing.SwingUtilities;

import controlador.ControladorVisualizarEmpleado;
import modelo.Empleado;
import modelo.GestorEmpleados;
import modelo.Jefe;
import vista.VentanaVisualizarEmpleados;

public class Usa04 {

	public static void main(String[] args) {
		Path pFichero = Paths.get(System.getProperty("user.home"), "EmpleadosConMVC", "Empleados.dat");

		GestorEmpleados ge = new GestorEmpleados(pFichero);

		if (ge.leerEmpleados().isEmpty()) {
			ge.addEmpleado(new Jefe("Carlos Ruiz",      3300, "CONTABILIDAD",    LocalDate.of(2019, 2, 20),  0));
			ge.addEmpleado(new Empleado("María García",    1500, "CONTABILIDAD",    LocalDate.of(2009, 5, 30)));
			ge.addEmpleado(new Empleado("Laura López",     1500, "CONTABILIDAD",    LocalDate.of(2010, 5, 30)));
			ge.addEmpleado(new Empleado("Fernando Díaz",   1500, "INFORMATICA",     LocalDate.of(2012, 5, 30)));
			ge.addEmpleado(new Empleado("Amancio Torres",  1500, "INFORMATICA",     LocalDate.of(2013, 5, 30)));
			ge.addEmpleado(new Jefe("Florentino Pérez", 2500, "INFORMATICA",     LocalDate.of(2014, 5, 30), 200));
			ge.addEmpleado(new Empleado("Nuria Martín",    1500, "VENTAS",          LocalDate.of(2015, 5, 30)));
			ge.addEmpleado(new Empleado("Raúl Sánchez",    1500, "VENTAS",          LocalDate.of(2016, 5, 30)));
			ge.addEmpleado(new Jefe("Paca Fernández",   2500, "VENTAS",          LocalDate.of(2017, 5, 30), 150));
			ge.addEmpleado(new Empleado("Alexis González", 1500, "RECURSOS_HUMANOS", LocalDate.of(2018, 5, 30)));
			ge.addEmpleado(new Empleado("Ainara Rodríguez",1500, "RECURSOS_HUMANOS", LocalDate.of(2019, 5, 30)));
			ge.addEmpleado(new Jefe("Yibril Hassan",    2500, "RECURSOS_HUMANOS", LocalDate.of(2020, 5, 30), 100));
		}

		SwingUtilities.invokeLater(() -> {
			VentanaVisualizarEmpleados ve = new VentanaVisualizarEmpleados();
			new ControladorVisualizarEmpleado(ve, ge);
			ve.setVisible(true);
		});
	}
}
