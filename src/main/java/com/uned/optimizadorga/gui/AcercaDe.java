package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

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
		JLabel opti = new JLabel();
		opti.setText("OptimizadorGA");
		panelContenido.add(opti);
		JLabel version = new JLabel();
		version.setText("Version 1.0");
		panelContenido.add(version);
		JLabel autor = new JLabel();
		autor.setText("Autor: Francisco Javier García Paredero");
		panelContenido.add(autor);
		this.add(panelContenido, BorderLayout.CENTER);
		setBounds(200, 50, 300, 200);
	}

}
