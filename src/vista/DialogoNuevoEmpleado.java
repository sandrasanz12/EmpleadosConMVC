package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modelo.Empleado;
import modelo.Jefe;

public class DialogoNuevoEmpleado extends JDialog {

	private JTextField txtNombre, txtSueldo, txtFechaContrato, txtDepartamento, txtIncentivo;
	private JRadioButton rbEmpleado, rbJefe;
	private JButton btnAceptar, btnCancelar;

	private Empleado resultado;

	public DialogoNuevoEmpleado(JFrame parent, Empleado empleado) {
		super(parent, empleado == null ? "Añadir empleado" : "Modificar empleado", true);

		setLayout(new BorderLayout(10, 10));
		setResizable(false);

		add(crearFormulario(), BorderLayout.CENTER);
		add(crearBotones(), BorderLayout.SOUTH);

		if (empleado != null) rellenarCampos(empleado);

		pack();
		setLocationRelativeTo(parent);
	}

	private JPanel crearFormulario() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;

		String[] etiquetas = {"Nombre:", "Sueldo:", "Fecha (dd/MM/yyyy):", "Departamento:"};
		JTextField[] campos = new JTextField[4];

		for (int i = 0; i < etiquetas.length; i++) {
			gbc.gridx = 0; gbc.gridy = i; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
			panel.add(new JLabel(etiquetas[i]), gbc);
			gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
			campos[i] = new JTextField(20);
			panel.add(campos[i], gbc);
		}
		txtNombre        = campos[0];
		txtSueldo        = campos[1];
		txtFechaContrato = campos[2];
		txtDepartamento  = campos[3];

		// Tipo
		gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
		panel.add(new JLabel("Tipo:"), gbc);
		gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		rbEmpleado = new JRadioButton("Empleado", true);
		rbJefe     = new JRadioButton("Jefe");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbEmpleado);
		bg.add(rbJefe);
		panelTipo.add(rbEmpleado);
		panelTipo.add(rbJefe);
		panel.add(panelTipo, gbc);

		// Incentivo
		gbc.gridx = 0; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
		panel.add(new JLabel("Incentivo:"), gbc);
		gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		txtIncentivo = new JTextField("0.0", 20);
		txtIncentivo.setEnabled(false);
		panel.add(txtIncentivo, gbc);

		rbJefe.addActionListener(e -> txtIncentivo.setEnabled(true));
		rbEmpleado.addActionListener(e -> {
			txtIncentivo.setEnabled(false);
			txtIncentivo.setText("0.0");
		});

		return panel;
	}

	private JPanel crearBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAceptar  = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		btnAceptar.addActionListener(e -> aceptar());
		btnCancelar.addActionListener(e -> dispose());
		panel.add(btnAceptar);
		panel.add(btnCancelar);
		return panel;
	}

	private void aceptar() {
		try {
			String nombre = txtNombre.getText().trim();
			String dept   = txtDepartamento.getText().trim().toUpperCase();

			if (nombre.isEmpty() || dept.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Nombre y departamento no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			double sueldo = Double.parseDouble(txtSueldo.getText().trim());
			LocalDate fecha = LocalDate.parse(txtFechaContrato.getText().trim(), VentanaVisualizarEmpleados.FMT);

			if (rbJefe.isSelected()) {
				double incentivo = Double.parseDouble(txtIncentivo.getText().trim());
				resultado = new Jefe(nombre, sueldo, dept, fecha, incentivo);
			} else {
				resultado = new Empleado(nombre, sueldo, dept, fecha);
			}
			dispose();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
					"Sueldo e incentivo deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this,
					"Fecha inválida. Usa el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rellenarCampos(Empleado e) {
		txtNombre.setText(e.getNombre());
		txtSueldo.setText(String.valueOf(e.getSueldo()));
		txtFechaContrato.setText(e.getFechaContrato().format(VentanaVisualizarEmpleados.FMT));
		txtDepartamento.setText(e.getDepartamento());
		if (e instanceof Jefe) {
			rbJefe.setSelected(true);
			txtIncentivo.setText(String.valueOf(((Jefe) e).getIncentivo()));
			txtIncentivo.setEnabled(true);
		}
	}

	public Empleado getEmpleado() {
		return resultado;
	}
}
