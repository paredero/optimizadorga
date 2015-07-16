package com.uned.optimizadorga.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AcercaDe(JFrame parent,
			String titulo, boolean modal) {
		super(parent, titulo, modal);
		setBounds(200, 50, 200, 200);
	}

}
