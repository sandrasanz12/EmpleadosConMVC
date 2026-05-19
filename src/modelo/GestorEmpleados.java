package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;

public class GestorEmpleados {
	private Path ruta;

	public GestorEmpleados(Path ruta) {
		this.ruta = ruta;
	}
	
	public ArrayList<Empleado> leerEmpleados() {
		
		ArrayList<Empleado> leidos = new ArrayList<Empleado>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()))){						// se pone dentro del try para que se encarge de manejar los flujos
			// Leemos OBJETOS en el fichero 
		
			while(true)
				leidos.add((Empleado)ois.readObject());
			
		} 
		
		catch (EOFException e) {

		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return leidos;
		
	}
}
