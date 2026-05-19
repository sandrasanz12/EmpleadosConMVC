package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Empleado;
import modelo.GestorEmpleados;
import modelo.Jefe;
import vista.DialogoNuevoEmpleado;
import vista.VentanaVisualizarEmpleados;

public class ControladorVisualizarEmpleado {

	private VentanaVisualizarEmpleados miVista;
	private GestorEmpleados miModelo;
	private ArrayList<Empleado> listaFiltrada;
	private int indiceActual;

	public ControladorVisualizarEmpleado(VentanaVisualizarEmpleados miVista, GestorEmpleados miModelo) {
		this.miVista  = miVista;
		this.miModelo = miModelo;

		recargarLista();
		indiceActual = 0;
		mostrarActual();
		registrarEventos();
	}

	// ── Lógica de filtrado ───────────────────────────────────────────────────

	private void recargarLista() {
		listaFiltrada = miModelo.getEmpleadosFiltrados(
				miVista.chkContabilidad.isSelected(),
				miVista.chkRRHH.isSelected(),
				miVista.chkInformatica.isSelected(),
				miVista.chkVentas.isSelected(),
				miVista.chkJefes.isSelected(),
				miVista.chkEmpleados.isSelected());
	}

	private void aplicarFiltros() {
		recargarLista();
		indiceActual = 0;
		mostrarActual();
	}

	// ── Navegación ───────────────────────────────────────────────────────────

	private void mostrarActual() {
		if (listaFiltrada.isEmpty()) {
			miVista.limpiarDatos();
		} else {
			if (indiceActual >= listaFiltrada.size()) indiceActual = listaFiltrada.size() - 1;
			if (indiceActual < 0) indiceActual = 0;
			miVista.mostrarEmpleado(listaFiltrada.get(indiceActual), indiceActual, listaFiltrada.size());
		}
	}

	// ── Gestión CRUD ─────────────────────────────────────────────────────────

	private void anadir() {
		DialogoNuevoEmpleado dialogo = new DialogoNuevoEmpleado(miVista, null);
		dialogo.setVisible(true);
		Empleado nuevo = dialogo.getEmpleado();
		if (nuevo != null) {
			miModelo.addEmpleado(nuevo);
			recargarLista();
			indiceActual = listaFiltrada.indexOf(nuevo);
			if (indiceActual < 0) indiceActual = 0;
			mostrarActual();
		}
	}

	private void borrar() {
		if (listaFiltrada.isEmpty()) return;
		Empleado actual = listaFiltrada.get(indiceActual);
		int resp = JOptionPane.showConfirmDialog(miVista,
				"¿Borrar el empleado \"" + actual.getNombre() + "\"?",
				"Confirmar borrado", JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			int prevIndex = indiceActual;
			miModelo.removeEmpleado(actual);
			recargarLista();
			indiceActual = Math.min(prevIndex, listaFiltrada.size() - 1);
			mostrarActual();
		}
	}

	private void modificar() {
		if (listaFiltrada.isEmpty()) return;
		Empleado actual = listaFiltrada.get(indiceActual);

		try {
			String nombre = miVista.txtNombre.getText().trim();
			String dept   = miVista.txtDepartamento.getText().trim().toUpperCase();

			if (nombre.isEmpty() || dept.isEmpty()) {
				JOptionPane.showMessageDialog(miVista,
						"Nombre y departamento no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			double sueldo = Double.parseDouble(miVista.txtSueldo.getText().trim());
			LocalDate fecha = LocalDate.parse(miVista.txtFechaContrato.getText().trim(), VentanaVisualizarEmpleados.FMT);

			Empleado modificado;
			if (miVista.rbJefe.isSelected()) {
				double incentivo = Double.parseDouble(miVista.txtIncentivo.getText().trim());
				modificado = new Jefe(nombre, sueldo, dept, fecha, incentivo);
			} else {
				modificado = new Empleado(nombre, sueldo, dept, fecha);
			}

			miModelo.updateEmpleado(actual, modificado);
			recargarLista();
			int nuevoIdx = listaFiltrada.indexOf(modificado);
			indiceActual = nuevoIdx >= 0 ? nuevoIdx : Math.min(indiceActual, listaFiltrada.size() - 1);
			mostrarActual();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(miVista,
					"Sueldo e incentivo deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(miVista,
					"Fecha inválida. Usa el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// ── Registro de eventos ──────────────────────────────────────────────────

	private void registrarEventos() {
		// Filtros
		miVista.btnAplicarFiltros.addActionListener(e -> aplicarFiltros());
		miVista.btnLimpiarFiltros.addActionListener(e -> {
			miVista.chkContabilidad.setSelected(true);
			miVista.chkRRHH.setSelected(true);
			miVista.chkInformatica.setSelected(true);
			miVista.chkVentas.setSelected(true);
			miVista.chkJefes.setSelected(true);
			miVista.chkEmpleados.setSelected(true);
			aplicarFiltros();
		});

		// Navegación
		miVista.btnPrimero.addActionListener(e -> { indiceActual = 0; mostrarActual(); });
		miVista.btnAnterior.addActionListener(e -> {
			if (indiceActual > 0) { indiceActual--; mostrarActual(); }
		});
		miVista.btnSiguiente.addActionListener(e -> {
			if (indiceActual < listaFiltrada.size() - 1) { indiceActual++; mostrarActual(); }
		});
		miVista.btnUltimo.addActionListener(e -> {
			if (!listaFiltrada.isEmpty()) { indiceActual = listaFiltrada.size() - 1; mostrarActual(); }
		});

		// Gestión
		miVista.btnAnadir.addActionListener(e -> anadir());
		miVista.btnBorrar.addActionListener(e -> borrar());
		miVista.btnModificar.addActionListener(e -> modificar());

		// Toggle campo incentivo
		miVista.rbJefe.addActionListener(e -> miVista.txtIncentivo.setEnabled(true));
		miVista.rbEmpleado.addActionListener(e -> {
			miVista.txtIncentivo.setEnabled(false);
			miVista.txtIncentivo.setText("0.0");
		});
	}
}
