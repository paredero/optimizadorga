package com.uned.optimizadorga.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
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
	private List<Era> resultados;
	private JTextPane panelResultados;
	private JScrollPane scrlResultados;
	private JPanel panelConfiguracion;
	private JPanel panelFuncion;
	private JScrollPane scrlParametros;
	private JPanel panelParametros;
	private JPanel panelNuevoParametro;
	private JLabel lbNombreParametro;
	private JTextField nombreParametro;
	private JLabel lbMinimoParametro;
	private JFormattedTextField minimoParametro;
	private JLabel lbMaximoParametro;
	private JTextField maximoParametro;
	private JLabel lbPrecisionParametro;
	private JTextField precision;
	private JButton btnAniadirParametro;
	private JPanel panelNombre;
	private JPanel panelMinimo;
	private JPanel panelMaximo;
	private JPanel panelPrecision;

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
	 * 
	 */
	private void ejecutar() {
		resultados = null;
		ProgressDialog progressDialog = new ProgressDialog(OptimizadorGUI.this, "Calculando", true);				
		
		
		//*********************************************************************
		// Función de prueba caso 1
		List<Gen> parametros = new ArrayList<Gen>();
		Gen x1 = new Gen("x1",-3.0, 12.1, 1);
		Gen x2 = new Gen("x2",4.1, 5.8, 1);
		parametros.add(x1);
		parametros.add(x2);
		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		
//		//*********************************************************************
//		// Función de prueba caso 2
//		List<Gen> parametros = new ArrayList<Gen>();
//		parametros.add(new Gen("x1",-3.0, 5.1, 1));
//		parametros.add(new Gen("x2",2.1, 7.8, 1));
//		parametros.add(new Gen("x3",-10.1, 20.3, 1));
//		parametros.add(new Gen("x4",-3.3, 4.2, 1));
//		parametros.add(new Gen("x5",-15.3, 70.1, 1));
//		parametros.add(new Gen("x6",-0.25, 0.35, 2));
//		String expresion = "100-(x1^2+x2^2+x3^2+x4^2+x5^2+x6^2)";
		
		//*********************************************************************
		// Función de prueba caso 3
//		List<Gen> parametros = new ArrayList<Gen>();
//		parametros.add(new Gen("x1",-5, 5, 1));
//		parametros.add(new Gen("x2",-5, 5, 1));
//		
//		String expresion = "-20*e^(-0.2*sqrt((1/2)*(x1^2+x2^2)))-e^((1/2)*(cos(2*pi*x1)+cos(2*pi*x2)))+20+e";
		
		//*********************************************************************
		// Función de prueba caso 4
//		List<Gen> parametros = new ArrayList<Gen>();
//		parametros.add(new Gen("x1",-100, 100, 1));
//		parametros.add(new Gen("x2",-100, 100, 1));
//
//		String expresion = "100-(((x1^2+x2^2)^0.25)*(sin(50*(x1^2+x2^2)^0.1)^2+1))";

		//*********************************************************************

		Funcion funcionCoste = new Funcion(expresion, parametros);
		Configuracion configuracion = Configuracion
				.crearConfiguracion(
						(Integer) spNumEras.getValue(),
						(Integer) spNumGen.getValue(), 
						funcionCoste,
						parametros,
						(Integer) spTamPoblacion.getValue(),
						(Double) spProbCruce.getValue(),
						(Double) spProbMutacion.getValue());
		
		Algoritmo algoritmo = new Algoritmo(configuracion);
		final AlgoritmoWorker worker = new AlgoritmoWorker(algoritmo, progressDialog);
		
		progressDialog.setAlgoritmoWorker(worker);
		worker.execute();
		progressDialog.setVisible(true);
		
		try {
			resultados = worker.get();
			mostrarResultados(resultados);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void mostrarResultados(List<Era> resultados) {
		StringBuilder sb = new StringBuilder("RESULTADOS DE LA EJECUCIÓN").append("\n");
		int i = 1;
		for (Era e:resultados) {
			sb.append("Era: ").append(i).append("\n");
			Cromosoma mejorCromosomaEra = e.obtenerMejor();
			sb.append("Mejor cromosoma: ");
			for (Gen g:mejorCromosomaEra.getGenes()) {
				sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
			}
			sb.append("\nCoste: ").append(mejorCromosomaEra.getCoste()).append("\n");
			i++;
			sb.append("**********************************************").append("\n");
		}
		panelResultados.setText(sb.toString());
//		scrlResultados.setVisible(true);
		panelResultados.setVisible(true);
		
	}

	/**
	 * Create the frame.
	 */
	public OptimizadorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		panelResultados = new JTextPane();
		scrlResultados = new JScrollPane(panelResultados);
		scrlResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrlResultados.setVisible(true);
		panelResultados.setVisible(false);
		
		panelParametros = new JPanel();
		scrlParametros = new JScrollPane(panelParametros);
		panelParametros.setLayout(new BoxLayout(panelParametros, BoxLayout.X_AXIS));
		
		panelNuevoParametro = new JPanel();
		panelNuevoParametro.setPreferredSize(new Dimension(60, 20));
		panelParametros.add(panelNuevoParametro);
		
		btnAniadirParametro = new JButton("+");
		btnAniadirParametro.setPreferredSize(new Dimension(60, 60));
		
		panelNombre = new JPanel();
		panelNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panelMinimo = new JPanel();
		
		panelMaximo = new JPanel();
		
		panelPrecision = new JPanel();
		GroupLayout gl_panelNuevoParametro = new GroupLayout(panelNuevoParametro);
		gl_panelNuevoParametro.setHorizontalGroup(
			gl_panelNuevoParametro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNuevoParametro.createSequentialGroup()
					.addGap(28)
					.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMaximo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPrecision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAniadirParametro, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(309, Short.MAX_VALUE))
		);
		gl_panelNuevoParametro.setVerticalGroup(
			gl_panelNuevoParametro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNuevoParametro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNuevoParametro.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAniadirParametro, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelMaximo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelPrecision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		lbPrecisionParametro = new JLabel("Precisi\u00F3n:");
		panelPrecision.add(lbPrecisionParametro);
		
		precision = new JTextField();
		panelPrecision.add(precision);
		precision.setPreferredSize(new Dimension(60, 20));
		precision.setColumns(10);
		lbPrecisionParametro.setLabelFor(precision);
		
		lbMaximoParametro = new JLabel("M\u00E1ximo:");
		panelMaximo.add(lbMaximoParametro);
		
		maximoParametro = new JTextField();
		panelMaximo.add(maximoParametro);
		maximoParametro.setPreferredSize(new Dimension(60, 20));
		maximoParametro.setColumns(10);
		lbMaximoParametro.setLabelFor(maximoParametro);
		
		lbMinimoParametro = new JLabel("M\u00EDnimo");
		panelMinimo.add(lbMinimoParametro);
		
		minimoParametro = new JFormattedTextField();
		panelMinimo.add(minimoParametro);
		minimoParametro.setPreferredSize(new Dimension(60, 20));
		lbMinimoParametro.setLabelFor(minimoParametro);
		
		lbNombreParametro = new JLabel("Nombre:");
		panelNombre.add(lbNombreParametro);
		
		nombreParametro = new JTextField();
		panelNombre.add(nombreParametro);
		nombreParametro.setPreferredSize(new Dimension(80, 20));
		nombreParametro.setColumns(10);
		lbNombreParametro.setLabelFor(nombreParametro);
		panelNuevoParametro.setLayout(gl_panelNuevoParametro);
		scrlParametros.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panelConfiguracion = new JPanel();
		panelConfiguracion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelConfiguracion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pNumEras = new JPanel();
		panelConfiguracion.add(pNumEras);
		JLabel lblNumEras = new JLabel("N\u00FAmero de eras:");
		pNumEras.add(lblNumEras);
		lblNumEras.setLabelFor(spNumEras);
		
		spNumEras = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		pNumEras.add(spNumEras);
		
		panelFuncion = new JPanel();
		panelFuncion.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		txtFuncionCoste = new JTextField();
		txtFuncionCoste.setColumns(10);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutar();
				
			}
		});
		
		JLabel lbFunCoste = new JLabel("Funci\u00F3n de coste:");
		GroupLayout gl_panelFuncion = new GroupLayout(panelFuncion);
		gl_panelFuncion.setHorizontalGroup(
			gl_panelFuncion.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFuncion.createSequentialGroup()
					.addGap(176)
					.addComponent(lbFunCoste)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFuncionCoste, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEjecutar)
					.addContainerGap(559, Short.MAX_VALUE))
		);
		gl_panelFuncion.setVerticalGroup(
			gl_panelFuncion.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFuncion.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addGroup(gl_panelFuncion.createParallelGroup(Alignment.LEADING)
						.addComponent(lbFunCoste)
						.addGroup(gl_panelFuncion.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtFuncionCoste, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEjecutar)))
					.addContainerGap())
		);
		gl_panelFuncion.linkSize(SwingConstants.VERTICAL, new Component[] {txtFuncionCoste, btnEjecutar, lbFunCoste});
		panelFuncion.setLayout(gl_panelFuncion);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlResultados, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrlParametros, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(panelConfiguracion, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelFuncion, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelConfiguracion, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelFuncion, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(scrlParametros, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(92)
					.addComponent(scrlResultados, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {scrlResultados, scrlParametros});
		
		JPanel pNumGen = new JPanel();
		panelConfiguracion.add(pNumGen);
		
		JLabel lblNumeroGeneraciones = new JLabel("N\u00FAmero de generaciones:");
		pNumGen.add(lblNumeroGeneraciones);
		
		spNumGen = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		spNumGen.setPreferredSize(new Dimension(53, 20));
		pNumGen.add(spNumGen);
		lblNumeroGeneraciones.setLabelFor(spNumGen);
		
		JPanel pTamPoblacion = new JPanel();
		panelConfiguracion.add(pTamPoblacion);
		
		JLabel lbTamPoblacion = new JLabel("Tama\u00F1o de la poblaci\u00F3n:");
		pTamPoblacion.add(lbTamPoblacion);
		
		spTamPoblacion = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		spTamPoblacion.setPreferredSize(new Dimension(53, 20));
		pTamPoblacion.add(spTamPoblacion);
		lbTamPoblacion.setLabelFor(spTamPoblacion);
		
		JPanel pProbMuta = new JPanel();
		panelConfiguracion.add(pProbMuta);
		JLabel lbProbabilidadMutacion = new JLabel("Probabilidad de mutaci\u00F3n:");
		pProbMuta.add(lbProbabilidadMutacion);
		spProbMutacion = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.001));
		spProbMutacion.setPreferredSize(new Dimension(53, 20));
		pProbMuta.add(spProbMutacion);
		lbProbabilidadMutacion.setLabelFor(spProbMutacion);
		
		JPanel pProbCruce = new JPanel();
		panelConfiguracion.add(pProbCruce);
		
		JLabel lblProbabilidadDeCruce = new JLabel("Probabilidad de cruce:");
		pProbCruce.add(lblProbabilidadDeCruce);
		
		spProbCruce = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.001));
		spProbCruce.setPreferredSize(new Dimension(53, 20));
		pProbCruce.add(spProbCruce);
		lblProbabilidadDeCruce.setLabelFor(spProbCruce);
		contentPane.setLayout(gl_contentPane);
	}
}
