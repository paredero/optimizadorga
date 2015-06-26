package com.uned.optimizadorga.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;

public class ProgressDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289334842313773479L;
	private JProgressBar progressBar;
	private AlgoritmoWorker algoritmoWorker;
	private JTextPane panelResultadoEra;
	private JTextPane panelResultadoGeneracion;


	/**
	 * Create the dialog.
	 */
	public ProgressDialog(JFrame parent, String titulo, boolean modal) {
		super(parent, titulo, modal);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(200, 10, 600, 695);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				algoritmoWorker.cancel(true);
			}
		});
		
		panelResultadoEra = new JTextPane();
		panelResultadoGeneracion = new JTextPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
								.addComponent(panelResultadoGeneracion, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
								.addComponent(panelResultadoEra, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(230)
							.addComponent(botonCancelar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(92)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(botonCancelar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(panelResultadoEra, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panelResultadoGeneracion, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}


	/**
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setAlgoritmoWorker(AlgoritmoWorker worker) {
		this.algoritmoWorker = worker;
	}


	/**
	 * @return the panelResultadoEra
	 */
	public JTextPane getPanelResultadoEra() {
		return this.panelResultadoEra;
	}


	/**
	 * @return the panelResultadoGeneracion
	 */
	public JTextPane getPanelResultadoGeneracion() {
		return this.panelResultadoGeneracion;
	}
}
