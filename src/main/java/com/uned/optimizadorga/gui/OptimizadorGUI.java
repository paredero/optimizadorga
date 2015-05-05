package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.TipoGen;

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
	private JPanel panelDatos;
//	private JScrollPane scrlParametros;
//	private JPanel panelParametros;
	private JPanel panelNuevoParametro;
	private JLabel lbNombreParametro;
	private JTextField nombreParametro;
	private JLabel lbMinimoParametro;
	private JFormattedTextField minimoParametro;
	private JLabel lbMaximoParametro;
	private JFormattedTextField maximoParametro;
	private JLabel lbPrecisionParametro;
	private JSpinner precisionParametro;
	private JButton btnAniadirParametro;
	private JPanel panelNombre;
	private JPanel panelMinimo;
	private JPanel panelMaximo;
	private JPanel panelPrecision;
	private Map<String, TipoGen> parametros = new HashMap<String, TipoGen>();
	private JPanel panelParametros;
	private Map<String, JPanel> mapPanelesParametros = new HashMap<String, JPanel>();
	private JPanel panelFuncion;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panelBotones;
	private JButton botonPi;
	private JButton botonE;
	private JButton botonSin;
	private JButton botonCos;
	private JButton botonPotencia;
	private JButton botonSuma;
	private JButton botonResta;
	private JButton botonProducto;
	private JButton botonCociente;
	private JButton botonRaiz;
	private JButton botonLog2;
	private JButton botonLog10;
	private JButton botonLog;
	private JButton botonAbs;
	private JButton botonAtan;
	private JButton botonAcos;
	private JButton botonAsin;
	private JButton botonTan;

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
		if (txtFuncionCoste.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Debe introducirse la función de coste");
		} else {
			Funcion funcionCoste = null;
			try {
				funcionCoste = new Funcion(txtFuncionCoste.getText().trim(),
						parametros);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Formato de función de coste incorrecto");
			}
			if (funcionCoste != null) {
				resultados = null;
				ProgressDialog progressDialog = new ProgressDialog(
						OptimizadorGUI.this, "Calculando", true);

//				TipoGen x1 = new TipoGen("x1", -3.0, 12.1, 1);
//				TipoGen x2 = new TipoGen("x2", 4.1, 5.8, 1);
//				parametros.put("x1", x1);
//				parametros.put("x2", x2);

				String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";

				// //*********************************************************************
				// // Función de prueba caso 2
				// List<Gen> parametros = new ArrayList<Gen>();
				// parametros.add(new Gen("x1",-3.0, 5.1, 1));
				// parametros.add(new Gen("x2",2.1, 7.8, 1));
				// parametros.add(new Gen("x3",-10.1, 20.3, 1));
				// parametros.add(new Gen("x4",-3.3, 4.2, 1));
				// parametros.add(new Gen("x5",-15.3, 70.1, 1));
				// parametros.add(new Gen("x6",-0.25, 0.35, 2));
				// String expresion = "100-(x1^2+x2^2+x3^2+x4^2+x5^2+x6^2)";

				// *********************************************************************
				// Función de prueba caso 3
				// List<Gen> parametros = new ArrayList<Gen>();
				// parametros.add(new Gen("x1",-5, 5, 1));
				// parametros.add(new Gen("x2",-5, 5, 1));
				//
				// String expresion =
				// "-20*e^(-0.2*sqrt((1/2)*(x1^2+x2^2)))-e^((1/2)*(cos(2*pi*x1)+cos(2*pi*x2)))+20+e";

				// *********************************************************************
				// Función de prueba caso 4
				// List<Gen> parametros = new ArrayList<Gen>();
				// parametros.add(new Gen("x1",-100, 100, 1));
				// parametros.add(new Gen("x2",-100, 100, 1));
				//
				// String expresion =
				// "100-(((x1^2+x2^2)^0.25)*(sin(50*(x1^2+x2^2)^0.1)^2+1))";

				// *********************************************************************

				Configuracion configuracion = Configuracion.crearConfiguracion(
						(Integer) spNumEras.getValue(),
						(Integer) spNumGen.getValue(), funcionCoste,
						parametros, (Integer) spTamPoblacion.getValue(),
						(Double) spProbCruce.getValue(),
						(Double) spProbMutacion.getValue());

				Algoritmo algoritmo = new Algoritmo(configuracion);
				final AlgoritmoWorker worker = new AlgoritmoWorker(algoritmo,
						progressDialog);

				progressDialog.setAlgoritmoWorker(worker);
				worker.execute();
				progressDialog.setVisible(true);

				try {
					resultados = worker.get();
					mostrarResultados(resultados);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void mostrarResultados(List<Era> resultados) {
		StringBuilder sb = new StringBuilder("RESULTADOS DE LA EJECUCIÓN")
				.append("\n");
		int i = 1;
		for (Era e : resultados) {
			sb.append("Era: ").append(i).append("\n");
			Cromosoma mejorCromosomaEra = e.obtenerMejor();
			sb.append("Mejor cromosoma: ");
			for (Gen g : mejorCromosomaEra.getGenes()) {
				sb.append("[").append(g.getNombre()).append(",")
						.append(g.getValor()).append("]");
			}
			sb.append("\nCoste: ").append(mejorCromosomaEra.getCoste())
					.append("\n");
			i++;
			sb.append("**********************************************").append(
					"\n");
		}
		panelResultados.setText(sb.toString());
		// scrlResultados.setVisible(true);
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
				
		Icon addIcon = new ImageIcon(this.getClass().getResource("/icons/plus-icon.png"));
		
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
		contentPane.setLayout(new BorderLayout(0, 0));

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
		contentPane.add(panelConfiguracion, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panelDatos = new JPanel();
		panelDatos.setPreferredSize(new Dimension(10, 150));
		panel_1.add(panelDatos, BorderLayout.NORTH);
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDatos.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_3 = new JPanel();
		panelDatos.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panelFuncion = new JPanel();
		panel_3.add(panelFuncion, BorderLayout.NORTH);
		panelFuncion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFuncion.setLayout(new BorderLayout(0, 0));
		
		JLabel lbFunCoste = new JLabel("Funci\u00F3n de coste:");
		panelFuncion.add(lbFunCoste, BorderLayout.WEST);
		
		txtFuncionCoste = new JTextField();
		panelFuncion.add(txtFuncionCoste, BorderLayout.CENTER);
		txtFuncionCoste.setColumns(10);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		panelFuncion.add(btnEjecutar, BorderLayout.EAST);
		
		panelBotones = new JPanel();
		panel_3.add(panelBotones, BorderLayout.CENTER);
		
		inicializarBotonesFuncion();
		
		
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutar();			
			}
		});
		
		panelParametros = new JPanel();
		panelParametros.setLayout(new BoxLayout(panelParametros, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(panelParametros);
		panelDatos.add(scrollPane);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panelNuevoParametro = new JPanel();
		panel_2.add(panelNuevoParametro, BorderLayout.NORTH);
		panelNuevoParametro.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAniadirParametro = new JButton(addIcon);
		btnAniadirParametro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aniadirParametro();
			}
		});
		
		panelNombre = new JPanel();
		panelNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panelMinimo = new JPanel();
		
		panelMaximo = new JPanel();
		
		panelPrecision = new JPanel();
		
		lbPrecisionParametro = new JLabel("Precisi\u00F3n:");
		panelPrecision.add(lbPrecisionParametro);
		
		precisionParametro = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
		
		panelPrecision.add(precisionParametro);
		precisionParametro.setPreferredSize(new Dimension(60, 20));
		
		lbPrecisionParametro.setLabelFor(precisionParametro);
		
		lbMaximoParametro = new JLabel("M\u00E1ximo:");
		panelMaximo.add(lbMaximoParametro);
		
		maximoParametro = new JFormattedTextField(new Double(0.0));
		panelMaximo.add(maximoParametro);
		maximoParametro.setPreferredSize(new Dimension(60, 20));
		maximoParametro.setColumns(10);
		lbMaximoParametro.setLabelFor(maximoParametro);
		
		lbMinimoParametro = new JLabel("M\u00EDnimo");
		panelMinimo.add(lbMinimoParametro);
		
		minimoParametro = new JFormattedTextField(new Double(0.0));
		panelMinimo.add(minimoParametro);
		minimoParametro.setPreferredSize(new Dimension(60, 20));
		lbMinimoParametro.setLabelFor(minimoParametro);
		panelNuevoParametro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbNombreParametro = new JLabel("Nombre:");
		panelNombre.add(lbNombreParametro);
		
		nombreParametro = new JTextField();
		panelNombre.add(nombreParametro);
		nombreParametro.setPreferredSize(new Dimension(80, 20));
		nombreParametro.setColumns(10);
		lbNombreParametro.setLabelFor(nombreParametro);
		panelNuevoParametro.add(panelNombre);
		panelNuevoParametro.add(panelMinimo);
		panelNuevoParametro.add(panelMaximo);
		panelNuevoParametro.add(panelPrecision);
		panelNuevoParametro.add(btnAniadirParametro);
		
		panelResultados = new JTextPane();
		scrlResultados = new JScrollPane(panelResultados);
		panel_2.add(scrlResultados, BorderLayout.CENTER);
		scrlResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//		scrlResultados.setVisible(true);
				panelResultados.setVisible(false);
	}

	private void inicializarBotonesFuncion() {
		botonPi = new JButton("PI");
		panelBotones.add(botonPi);
		inicializarBoton(botonPi, " PI ");
		
		botonE = new JButton("e");
		panelBotones.add(botonE);
		inicializarBoton(botonE, " E ");
		
		botonSuma = new JButton("+");
		panelBotones.add(botonSuma);
		inicializarBoton(botonSuma, " + ");
		
		botonResta = new JButton("-");
		panelBotones.add(botonResta);
		inicializarBoton(botonResta, " - ");
		
		botonProducto = new JButton("*");
		panelBotones.add(botonProducto);
		inicializarBoton(botonProducto, " * ");
		
		botonCociente = new JButton("/");
		panelBotones.add(botonCociente);
		inicializarBoton(botonCociente, " / ");
		
		botonRaiz = new JButton("\u221A");
		panelBotones.add(botonRaiz);
		inicializarBoton(botonRaiz, " sqrt ");
		
		botonPotencia = new JButton("x^y");
		panelBotones.add(botonPotencia);
		inicializarBoton(botonPotencia, " ^ ");
		
		botonSin = new JButton("sin()");
		panelBotones.add(botonSin);
		inicializarBoton(botonSin, " sin() ");
		
		botonCos = new JButton("cos()");
		panelBotones.add(botonCos);
		inicializarBoton(botonCos, " cos() ");
		
		botonTan = new JButton("tan()");
		panelBotones.add(botonTan);
		inicializarBoton(botonTan, " tan() ");
		
		botonAsin = new JButton("asin()");
		panelBotones.add(botonAsin);
		inicializarBoton(botonAsin, " asin() ");
		
		botonAcos= new JButton("acos");
		panelBotones.add(botonAcos);
		inicializarBoton(botonAcos, " acos() ");
		
		botonAtan = new JButton("atan()");
		panelBotones.add(botonAtan);
		inicializarBoton(botonAtan, " atan() ");
		
		botonAbs = new JButton("abs()");
		panelBotones.add(botonAbs);
		inicializarBoton(botonAbs, " abs() ");
		
		botonLog = new JButton("log()");
		panelBotones.add(botonLog);
		inicializarBoton(botonLog, " log() ");
		
		botonLog10 = new JButton("log10()");
		panelBotones.add(botonLog10);
		inicializarBoton(botonLog10, " log10() ");
		
		botonLog2 = new JButton("log2()");
		panelBotones.add(botonLog2);
		inicializarBoton(botonLog2, " log2() ");
	}

	private void inicializarBoton(JButton boton, final String valor) {
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtFuncionCoste.setText(txtFuncionCoste.getText() + valor);
			}

		});
	}

	protected void aniadirParametro() {
		final String nombre = nombreParametro.getText();
		double minimo = (Double) minimoParametro.getValue();
		double maximo = (Double) maximoParametro.getValue();
		int precision = (Integer) precisionParametro.getValue();
		TipoGen tipoGen = new TipoGen(nombre,minimo, maximo, precision);
		
		if (nombre == null || "".equals(nombre)) {
			JOptionPane.showMessageDialog(this, "El nombre no puede ser nulo");
		} else if (mapPanelesParametros.containsKey(nombre)) {
			JOptionPane.showMessageDialog(this, "Ya existe un parámetro con el mismo nombre");
		} else if (maximo < minimo) {
			JOptionPane.showMessageDialog(this, "El valor máximo no puede ser inferior al valor mínimo");
		} else {
			//Añado el parametro a la lista de parametros
			parametros.put(nombre, tipoGen);
			
			// Creo el panel
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			StringBuilder texto = new StringBuilder("Nombre: ").append(nombre)
					.append(" Minimo: ").append(minimo).append(" Maximo: ")
					.append(maximo).append(" Precisión: ").append(precision);
			
			Label label = new Label(texto.toString());
			panel.add(label, BorderLayout.CENTER);
			Icon removeIcon = new ImageIcon(this.getClass().getResource("/icons/delete-icon.png"));
			JButton btnEliminarButton = new JButton(removeIcon);
			btnEliminarButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parametros.remove(nombre);					
					// En segundo lugar elimino el componente
					JPanel panelEliminar = mapPanelesParametros.get(nombre);
					panelEliminar.removeAll();
					panelParametros.remove(panelEliminar);
					mapPanelesParametros.remove(nombre);
					panelParametros.revalidate();
					panelParametros.repaint();
					panelDatos.revalidate();
					panelDatos.repaint();
				}
			});
			panel.add(btnEliminarButton, BorderLayout.EAST);
			mapPanelesParametros.put(nombre, panel);
			
			panelDatos.revalidate();
			panelDatos.repaint();
			
			panelParametros.add(panel);
			panelParametros.revalidate();
			
		}
	}

}
