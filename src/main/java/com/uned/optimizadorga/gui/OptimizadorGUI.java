package com.uned.optimizadorga.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;

public class OptimizadorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2384122644561114083L;
	private JPanel contentPane;
	private JTextField txtFuncionCoste;
	private JSpinner spNumEras;
	private JSpinner spNumGen;
	private JSpinner spTamPoblacion;
	private JSpinner spProbCruce;
	private JSpinner spProbMutacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OptimizadorGUI frame = new OptimizadorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OptimizadorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelFuncion = new JPanel();
		
		spNumEras = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		JLabel lblNumeroGeneraciones = new JLabel("N\u00FAmero de generaciones:");		
		JLabel lblNumEras = new JLabel("N\u00FAmero de eras:");
		
		spNumGen = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		
		JLabel lbTamPoblacion = new JLabel("Tama\u00F1o de la poblaci\u00F3n:");
		
		spTamPoblacion = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		
		spProbCruce = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.001));		
		spProbMutacion = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.001));
		
		JLabel lblProbabilidadDeCruce = new JLabel("Probabilidad de cruce:");
		JLabel lbProbabilidadMutacion = new JLabel("Probabilidad de mutaci\u00F3n:");
		
		JTextPane textPane = new JTextPane();
		textPane.setVisible(false);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textPane, Alignment.LEADING)
						.addComponent(panelFuncion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNumEras)
									.addGap(61)
									.addComponent(spNumEras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbTamPoblacion)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(spTamPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbProbabilidadMutacion)
									.addGap(18)
									.addComponent(spProbMutacion, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProbabilidadDeCruce)
								.addComponent(lblNumeroGeneraciones))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(spNumGen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spProbCruce, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)))
					.addContainerGap(280, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(panelFuncion, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(spNumEras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spNumGen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumEras)
						.addComponent(lblNumeroGeneraciones))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbTamPoblacion)
						.addComponent(spTamPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbProbabilidadMutacion)
						.addComponent(spProbMutacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProbabilidadDeCruce)
						.addComponent(spProbCruce, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {spNumGen, spProbCruce});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblProbabilidadDeCruce, lblNumeroGeneraciones});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {spTamPoblacion, spProbMutacion, spNumEras});
		
		txtFuncionCoste = new JTextField();
		txtFuncionCoste.setColumns(10);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProgressDialog progressDialog = new ProgressDialog(OptimizadorGUI.this, "Calculando", true);				
				
				List<Gen> parametros = new ArrayList<Gen>();
				Gen x1 = new Gen("x1",-3.0, 12.1, 1);
				Gen x2 = new Gen("x2",4.1, 5.8, 1);
				parametros.add(x1);
				parametros.add(x2);
				String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
				Funcion funcionCoste = new Funcion(expresion, parametros);
				Configuracion configuracion = Configuracion
						.crearConfiguracionBasica(
								(Integer) spNumEras.getValue(),
								(Integer) spNumGen.getValue(), funcionCoste,
								parametros,
								(Integer) spTamPoblacion.getValue(),
								(Double) spProbCruce.getValue(),
								(Double) spProbMutacion.getValue());
				
				Algoritmo algoritmo = new Algoritmo(configuracion);
				AlgoritmoWorker worker = new AlgoritmoWorker(algoritmo, progressDialog);
				worker.execute();
				progressDialog.setAlgoritmoWorker(worker);
				progressDialog.setVisible(true);
			}
		});
		
		JLabel lbFunCoste = new JLabel("Funci\u00F3n de coste:");
		GroupLayout gl_panelFuncion = new GroupLayout(panelFuncion);
		gl_panelFuncion.setHorizontalGroup(
			gl_panelFuncion.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFuncion.createSequentialGroup()
					.addGap(41)
					.addComponent(lbFunCoste)
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(txtFuncionCoste, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEjecutar)
					.addGap(55))
		);
		gl_panelFuncion.setVerticalGroup(
			gl_panelFuncion.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFuncion.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addGroup(gl_panelFuncion.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFuncionCoste, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEjecutar)
						.addComponent(lbFunCoste))
					.addContainerGap())
		);
		gl_panelFuncion.linkSize(SwingConstants.VERTICAL, new Component[] {txtFuncionCoste, btnEjecutar, lbFunCoste});
		panelFuncion.setLayout(gl_panelFuncion);
		contentPane.setLayout(gl_contentPane);
	}
}
