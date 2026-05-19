package vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Empleado;

public class VentanaVisualizarEmpleados extends JFrame {
	
	JPanel panelDatos;
	JLabel empleado;
	
	public VentanaVisualizarEmpleados() {
		
		setSize(500,500);											// por defecto nacen con un tamaño inutil
		setTitle("Sandra - EmpleadosConMVC");						// ponemos titulo a la ventana 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// para definir como se cierra 
		setLayout(new GridLayout(2,1));								// JFrame sin administrador de diseño, por defecto tiene BorderLayout
		setResizable(false);										// para que no se pueda redimensionar 
		
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		
		panelDatos = new JPanel();
		// por defecto el setLayout del JPanel es el FlowLayout
		panelDatos.setBounds(10,10,300,300);
		panelDatos.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.BLACK), "DATOS"));
		
	}
	
	public void escribirEmpleado(String nombre) {
	
		empleado = new JLabel(nombre);
		
		panelDatos.add(empleado);
		
		add(panelDatos);
		
	}
	
	
	
}
