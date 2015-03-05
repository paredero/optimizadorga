package com.uned.optimizadorga.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;

public class ProgressDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289334842313773479L;
	private JProgressBar progressBar;
	private AlgoritmoWorker algoritmoWorker;


	/**
	 * Create the dialog.
	 */
	public ProgressDialog(JFrame parent, String titulo, boolean modal) {
		super(parent, titulo, modal);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				algoritmoWorker.cancel(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(155)
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
					.addContainerGap(67, Short.MAX_VALUE))
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
	
	
}
