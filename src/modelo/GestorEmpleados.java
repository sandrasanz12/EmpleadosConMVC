package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GestorEmpleados {
	private Path ruta;
	private ArrayList<Empleado> empleados;

	public GestorEmpleados(Path ruta) {
		this.ruta = ruta;
		this.empleados = cargarEmpleados();
	}

	private ArrayList<Empleado> cargarEmpleados() {
		ArrayList<Empleado> lista = new ArrayList<>();
		if (!Files.exists(ruta)) return lista;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()))) {
			while (true) lista.add((Empleado) ois.readObject());
		} catch (EOFException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void guardarEmpleados() {
		try {
			if (ruta.getParent() != null) Files.createDirectories(ruta.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()))) {
			for (Empleado e : empleados) oos.writeObject(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Empleado> leerEmpleados() {
		return new ArrayList<>(empleados);
	}

	public ArrayList<Empleado> getEmpleadosFiltrados(
			boolean inclContabilidad, boolean inclRRHH,
			boolean inclInformatica, boolean inclVentas,
			boolean inclJefes, boolean inclEmpleados) {

		ArrayList<Empleado> resultado = new ArrayList<>();
		for (Empleado e : empleados) {
			String d = e.getDepartamento().toUpperCase();
			boolean deptOk = (inclContabilidad && d.equals("CONTABILIDAD"))
					|| (inclRRHH && d.equals("RECURSOS_HUMANOS"))
					|| (inclInformatica && d.equals("INFORMATICA"))
					|| (inclVentas && d.equals("VENTAS"));

			boolean tipoOk = (inclJefes && e instanceof Jefe)
					|| (inclEmpleados && !(e instanceof Jefe));

			if (deptOk && tipoOk) resultado.add(e);
		}
		return resultado;
	}

	public void addEmpleado(Empleado e) {
		empleados.add(e);
		guardarEmpleados();
	}

	public void removeEmpleado(Empleado e) {
		empleados.remove(e);
		guardarEmpleados();
	}

	public void updateEmpleado(Empleado viejo, Empleado nuevo) {
		int idx = empleados.indexOf(viejo);
		if (idx >= 0) {
			empleados.set(idx, nuevo);
			guardarEmpleados();
		}
	}
}
