package aplicacion;

import java.nio.file.Path;
import java.nio.file.Paths;

import modelo.CrearFichero;

public class Usa2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Path pFichero = Paths.get("C:","EmpleadosConMVC","Empleados.txt");
		
		CrearFichero fEmpleados = new CrearFichero(pFichero);
		
	}

}
