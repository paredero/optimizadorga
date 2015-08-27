package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dialogo "Acerca De"
 * @author Francisco Javier García Paredero
 *
 */
public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AcercaDe(JFrame parent,
			String titulo, boolean modal) {
		super(parent, titulo, modal);
		JPanel panelContenido = new JPanel();
		panelContenido.setLayout(new BoxLayout(panelContenido,
				BoxLayout.Y_AXIS));
		JTextField opti = new JTextField();
		opti.setText("OptimizadorGA");
		panelContenido.add(opti);
		JTextField version = new JTextField();
		version.setText("Version 1.0");
		panelContenido.add(version);
		JTextField autor = new JTextField();
		autor.setText("Autor: Francisco Javier García Paredero");
		panelContenido.add(autor);
		this.add(panelContenido, BorderLayout.CENTER);
		setBounds(200, 50, 300, 200);
	}

}
