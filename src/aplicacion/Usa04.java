package aplicacion;

import java.nio.file.Path;
import java.nio.file.Paths;

import modelo.GestorEmpleados;
import vista.VentanaVisualizarEmpleados;
import controlador.ControladorVisualizarEmpleado;

public class Usa04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path pFichero = Paths.get("C:","EmpleadosConMVC","Empleados.txt");
		
		GestorEmpleados ge = new GestorEmpleados(pFichero);
		
		VentanaVisualizarEmpleados ve = new VentanaVisualizarEmpleados();
		
		new ControladorVisualizarEmpleado(ve,ge);
		
		ve.setVisible(true);
	}

}
