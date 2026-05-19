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
import java.util.ArrayList;

public class CrearFichero {
	private Path ruta;

	public CrearFichero(Path ruta) {
		this.ruta = ruta;

		ArrayList<Empleado> empleados = new ArrayList<>();

		empleados.add(new Jefe("Carlos Ruiz",      3300, "CONTABILIDAD",    LocalDate.of(2019, 2, 20),  0));
		empleados.add(new Empleado("María García",    1500, "CONTABILIDAD",    LocalDate.of(2009, 5, 30)));
		empleados.add(new Empleado("Laura López",     1500, "CONTABILIDAD",    LocalDate.of(2010, 5, 30)));
		empleados.add(new Empleado("Fernando Díaz",   1500, "INFORMATICA",     LocalDate.of(2012, 5, 30)));
		empleados.add(new Empleado("Amancio Torres",  1500, "INFORMATICA",     LocalDate.of(2013, 5, 30)));
		empleados.add(new Jefe("Florentino Pérez", 2500, "INFORMATICA",     LocalDate.of(2014, 5, 30), 200));
		empleados.add(new Empleado("Nuria Martín",    1500, "VENTAS",          LocalDate.of(2015, 5, 30)));
		empleados.add(new Empleado("Raúl Sánchez",    1500, "VENTAS",          LocalDate.of(2016, 5, 30)));
		empleados.add(new Jefe("Paca Fernández",   2500, "VENTAS",          LocalDate.of(2017, 5, 30), 150));
		empleados.add(new Empleado("Alexis González", 1500, "RECURSOS_HUMANOS", LocalDate.of(2018, 5, 30)));
		empleados.add(new Empleado("Ainara Rodríguez",1500, "RECURSOS_HUMANOS", LocalDate.of(2019, 5, 30)));
		empleados.add(new Jefe("Yibril Hassan",    2500, "RECURSOS_HUMANOS", LocalDate.of(2020, 5, 30), 100));

		escribirFichero(empleados);

		for (Empleado empleado : leerFichero(ruta)) {
			System.out.println(empleado.toString());
		}
	}

	public void escribirFichero(ArrayList<Empleado> empleados) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()))) {
			for (Empleado empleado : empleados) oos.writeObject(empleado);
			System.out.println("Escrito");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Empleado> leerFichero(Path ruta) {
		ArrayList<Empleado> leidos = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()))) {
			while (true) leidos.add((Empleado) ois.readObject());
		} catch (EOFException e) {
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return leidos;
	}
}
