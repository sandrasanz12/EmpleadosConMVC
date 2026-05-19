package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modelo.Empleado;
import modelo.Jefe;

public class VentanaVisualizarEmpleados extends JFrame {

	static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// Filtros
	JCheckBox chkContabilidad, chkRRHH, chkInformatica, chkVentas;
	JCheckBox chkJefes, chkEmpleados;
	JButton btnAplicarFiltros, btnLimpiarFiltros;

	// Datos
	JTextField txtNombre, txtSueldo, txtFechaContrato, txtDepartamento, txtIncentivo;
	JRadioButton rbEmpleado, rbJefe;

	// Gestión
	JButton btnAnadir, btnBorrar, btnModificar;

	// Navegación
	JButton btnPrimero, btnAnterior, btnSiguiente, btnUltimo;
	JLabel lblPosicion;

	public VentanaVisualizarEmpleados() {
		setTitle("Visualizar empleados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout(5, 5));

		iniciarComponentes();

		pack();
		setLocationRelativeTo(null);
	}

	public void iniciarComponentes() {
		add(crearPanelFiltros(), BorderLayout.NORTH);
		add(crearPanelCentral(), BorderLayout.CENTER);
		add(crearPanelNavegacion(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelFiltros() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Filtros"));

		JPanel panelDepts = new JPanel(new GridLayout(2, 2));
		panelDepts.setBorder(BorderFactory.createTitledBorder("Departamento"));
		chkContabilidad = new JCheckBox("Contabilidad", true);
		chkInformatica  = new JCheckBox("Informática", true);
		chkRRHH         = new JCheckBox("RRHH", true);
		chkVentas       = new JCheckBox("Ventas", true);
		panelDepts.add(chkContabilidad);
		panelDepts.add(chkInformatica);
		panelDepts.add(chkRRHH);
		panelDepts.add(chkVentas);

		JPanel panelTipos = new JPanel(new GridLayout(2, 1));
		panelTipos.setBorder(BorderFactory.createTitledBorder("Tipo empleado"));
		chkJefes     = new JCheckBox("Jefes", true);
		chkEmpleados = new JCheckBox("Empleados", true);
		panelTipos.add(chkJefes);
		panelTipos.add(chkEmpleados);

		JPanel panelTop = new JPanel(new GridLayout(1, 2));
		panelTop.add(panelDepts);
		panelTop.add(panelTipos);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAplicarFiltros = new JButton("Aplicar filtros");
		btnLimpiarFiltros = new JButton("Limpiar filtros");
		panelBotones.add(btnAplicarFiltros);
		panelBotones.add(btnLimpiarFiltros);

		panel.add(panelTop, BorderLayout.CENTER);
		panel.add(panelBotones, BorderLayout.SOUTH);
		return panel;
	}

	private JPanel crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		panel.add(crearPanelGestion(), BorderLayout.EAST);
		return panel;
	}

	private JPanel crearPanelDatos() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Datos"));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 8, 4, 8);
		gbc.anchor = GridBagConstraints.WEST;

		String[] etiquetas = {"Nombre:", "Sueldo:", "Fecha contrato:", "Departamento:"};
		JTextField[] campos = new JTextField[4];
		for (int i = 0; i < etiquetas.length; i++) {
			gbc.gridx = 0; gbc.gridy = i; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
			panel.add(new JLabel(etiquetas[i]), gbc);
			gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
			campos[i] = new JTextField(22);
			panel.add(campos[i], gbc);
		}
		txtNombre       = campos[0];
		txtSueldo       = campos[1];
		txtFechaContrato = campos[2];
		txtDepartamento = campos[3];

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
		txtIncentivo = new JTextField(22);
		txtIncentivo.setEnabled(false);
		panel.add(txtIncentivo, gbc);

		// Relleno inferior
		gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weighty = 1.0;
		panel.add(new JLabel(), gbc);

		return panel;
	}

	private JPanel crearPanelGestion() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Gestión"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(120, 0));

		btnAnadir    = new JButton("+ Añadir");
		btnBorrar    = new JButton("|| Borrar");
		btnModificar = new JButton("- Modificar");

		Dimension dim = new Dimension(105, 30);
		for (JButton btn : new JButton[]{btnAnadir, btnBorrar, btnModificar}) {
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setPreferredSize(dim);
			btn.setMaximumSize(dim);
		}

		panel.add(Box.createVerticalGlue());
		panel.add(btnAnadir);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnBorrar);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnModificar);
		panel.add(Box.createVerticalGlue());

		return panel;
	}

	private JPanel crearPanelNavegacion() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Navegación"));

		JPanel inner = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPrimero   = new JButton("|<");
		btnAnterior  = new JButton("<");
		lblPosicion  = new JLabel("0 / 0");
		btnSiguiente = new JButton(">");
		btnUltimo    = new JButton(">|");

		inner.add(btnPrimero);
		inner.add(btnAnterior);
		inner.add(lblPosicion);
		inner.add(btnSiguiente);
		inner.add(btnUltimo);

		panel.add(inner, BorderLayout.CENTER);
		return panel;
	}

	public void mostrarEmpleado(Empleado e, int pos, int total) {
		txtNombre.setText(e.getNombre());
		txtSueldo.setText(String.valueOf(e.getSueldo()));
		txtFechaContrato.setText(e.getFechaContrato().format(FMT));
		txtDepartamento.setText(e.getDepartamento());
		lblPosicion.setText((pos + 1) + " / " + total);

		if (e instanceof Jefe) {
			rbJefe.setSelected(true);
			txtIncentivo.setText(String.valueOf(((Jefe) e).getIncentivo()));
			txtIncentivo.setEnabled(true);
		} else {
			rbEmpleado.setSelected(true);
			txtIncentivo.setText("0.0");
			txtIncentivo.setEnabled(false);
		}
	}

	public void limpiarDatos() {
		txtNombre.setText("");
		txtSueldo.setText("");
		txtFechaContrato.setText("");
		txtDepartamento.setText("");
		txtIncentivo.setText("");
		rbEmpleado.setSelected(true);
		txtIncentivo.setEnabled(false);
		lblPosicion.setText("0 / 0");
	}
}
