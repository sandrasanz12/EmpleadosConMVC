package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CrearFichero {
	private Path ruta;

	public CrearFichero(Path ruta) {
		
		this.ruta = ruta;
		
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
		
		escribirFichero(empleados);
		
		for (Empleado empleado : leerFichero(ruta)) {
			System.out.println(empleado.toString());
		}
		
	}
	
	public void escribirFichero(ArrayList<Empleado> empleados) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()))){						// se pone dentro del try para que se encarge de manejar los flujos
			// Escribimos OBJETOS en el fichero 
			
			for(Empleado empleado : empleados) {
				oos.writeObject(empleado);
			}
			
			System.out.println("Escrito");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	
	}
	
	public ArrayList<Empleado> leerFichero(Path ruta) {
		
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

