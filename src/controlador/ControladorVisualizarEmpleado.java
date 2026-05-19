package controlador;

import java.util.ArrayList;

import modelo.Empleado;
import modelo.GestorEmpleados;
import vista.VentanaVisualizarEmpleados;

public class ControladorVisualizarEmpleado {
	
	private VentanaVisualizarEmpleados miVista;
	private GestorEmpleados miModelo;
	
	private ArrayList<Empleado> listaCompleta;
	private int indiceActual;

	public ControladorVisualizarEmpleado(VentanaVisualizarEmpleados miVista, GestorEmpleados miModelo) {
		this.miVista = miVista;
		this.miModelo = miModelo;
		
		this.listaCompleta = miModelo.leerEmpleados();
		
		imprimirEmpleado();
	}
	
	public void imprimirEmpleado() {
		
		miVista.escribirEmpleado(listaCompleta.get(0).getNombre());
		
		/*for (Empleado empleado : listaCompleta) {
			System.out.println(empleado.toString());
		}*/
		
	}

	
	
	
}
