package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.selectores.Selector;
import com.uned.optimizadorga.algoritmo.worker.AlgoritmoWorker;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;
import com.uned.optimizadorga.elementos.TipoGen;

public class OptimizadorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2384122644561114083L;
	private static final Logger log = Logger.getLogger(OptimizadorGUI.class);
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
	private JPanel panelContenido;
	private JPanel panelNuevoParametroResultado;
	private JPanel panelCalculadora;
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
	private JCheckBox chkElitismo;
	private JRadioButton rbSelRuleta;
	private JRadioButton rbSelTorneo;
	private JPanel panelChartResultados;
	private JPanel panelChart;
	private JTextArea textoResultados;
	private JPanel panelBotonesConfiguracion;
	private JPanel panelDatosConfiguracion;

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
//		setBounds(100, 100, 1000, 720);
//		setBounds(0,0,screenSize.width, screenSize.height);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		crearPanelConfiguracion();
		crearPanelContenido();
		contentPane.add(panelConfiguracion, BorderLayout.PAGE_START);
		contentPane.add(panelContenido, BorderLayout.CENTER);
		setContentPane(contentPane);
	}


	private void crearPanelContenido() {
		panelContenido = new JPanel();		
		panelContenido.setLayout(new BorderLayout(0, 0));
		crearPanelDatos();		
		crearPanelNuevoParametroResultado();
		panelContenido.add(panelDatos, BorderLayout.PAGE_START);
		panelContenido.add(panelNuevoParametroResultado, BorderLayout.CENTER);
		
		
	}


	private void crearPanelDatos() {
		panelDatos = new JPanel();
		panelDatos.setPreferredSize(new Dimension(10, 150));		
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDatos.setLayout(new GridLayout(0, 2, 0, 0));
		crearPanelCalculadora();
		crearPanelParametros();
		panelDatos.add(panelCalculadora);		
		panelDatos.add(scrollPane);
	}


	private void crearPanelParametros() {
		panelParametros = new JPanel();
		panelParametros.setLayout(new BoxLayout(panelParametros, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(panelParametros);
	}


	private void crearPanelNuevoParametroResultado() {
		panelNuevoParametroResultado = new JPanel();
		
		panelNuevoParametroResultado.setLayout(new BorderLayout(0, 0));
		
		crearPanelNuevoParametro();	
		crearPanelChartResultados();
		panelNuevoParametroResultado.add(panelNuevoParametro, BorderLayout.PAGE_START);
		panelNuevoParametroResultado.add(panelChartResultados, BorderLayout.CENTER);
	}


	private void crearPanelNuevoParametro() {
		panelNuevoParametro = new JPanel();
		panelNuevoParametro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelNuevoParametro.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Icon addIcon = new ImageIcon(this.getClass().getResource("/icons/plus-icon.png"));
		btnAniadirParametro = new JButton(addIcon);
		btnAniadirParametro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aniadirParametro(nombreParametro.getText(),
						(Double) minimoParametro.getValue(),
						(Double) maximoParametro.getValue(),
						(Integer) precisionParametro.getValue());
			}
		});

		panelPrecision = new JPanel();
		precisionParametro = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
		precisionParametro.setPreferredSize(new Dimension(60, 20));
		lbPrecisionParametro = new JLabel("Precisi\u00F3n:");
		lbPrecisionParametro.setLabelFor(precisionParametro);
		panelPrecision.add(lbPrecisionParametro);
		panelPrecision.add(precisionParametro);
		
		panelMaximo = new JPanel();
		lbMaximoParametro = new JLabel("M\u00E1ximo:");
		maximoParametro = new JFormattedTextField(new Double(0.0));
		maximoParametro.setPreferredSize(new Dimension(60, 20));
		maximoParametro.setColumns(10);
		lbMaximoParametro.setLabelFor(maximoParametro);
		panelMaximo.add(lbMaximoParametro);
		panelMaximo.add(maximoParametro);
		
		panelMinimo = new JPanel();
		lbMinimoParametro = new JLabel("M\u00EDnimo");
		minimoParametro = new JFormattedTextField(new Double(0.0));
		minimoParametro.setPreferredSize(new Dimension(60, 20));
		lbMinimoParametro.setLabelFor(minimoParametro);
		panelMinimo.add(lbMinimoParametro);
		panelMinimo.add(minimoParametro);
		
		panelNombre = new JPanel();	
		lbNombreParametro = new JLabel("Nombre:");
		nombreParametro = new JTextField();
		nombreParametro.setPreferredSize(new Dimension(80, 20));
		nombreParametro.setColumns(10);
		lbNombreParametro.setLabelFor(nombreParametro);		
		panelNombre.add(lbNombreParametro);	
		panelNombre.add(nombreParametro);
		
		panelNuevoParametro.add(panelNombre);
		panelNuevoParametro.add(panelMinimo);
		panelNuevoParametro.add(panelMaximo);
		panelNuevoParametro.add(panelPrecision);
		panelNuevoParametro.add(btnAniadirParametro);
	}

	private void crearPanelChartResultados() {
		panelChartResultados = new JPanel(new GridLayout(0, 2, 0, 0));
		crearPanelChart();
		crearPanelResultados();
		panelChartResultados.add(panelChart);
		panelChartResultados.add(scrlResultados);
//		textoResultados.setText("De momento no hay nada");
//		panelChartResultados.add(textoResultados);
		panelChartResultados.setVisible(false);
	}
	
	private void crearPanelChart() {
		panelChart = new JPanel();		
	}


	private void crearPanelResultados() {
		textoResultados = new JTextArea();
//		scrlResultados = new JScrollPane(panelResultados);
		scrlResultados = new JScrollPane(textoResultados);
		scrlResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrlResultados.setVisible(true);
//		panelResultados.setVisible(false);
	}


	private void crearPanelCalculadora() {
		panelCalculadora = new JPanel();		
		panelCalculadora.setLayout(new BorderLayout(0, 0));		
		
		crearPanelFuncion();		
		crearPanelBotones();
		
		panelCalculadora.add(panelFuncion, BorderLayout.PAGE_START);
		panelCalculadora.add(panelBotones, BorderLayout.CENTER);
	}


	private void crearPanelFuncion() {
		panelFuncion = new JPanel();
		panelFuncion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFuncion.setLayout(new BorderLayout(0, 0));
		
		JLabel lbFunCoste = new JLabel("Funci\u00F3n de coste:");
		
		txtFuncionCoste = new JTextField();
		txtFuncionCoste.setColumns(10);
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutar();			
			}
		});
		
		panelFuncion.add(lbFunCoste, BorderLayout.LINE_START);
		panelFuncion.add(txtFuncionCoste, BorderLayout.CENTER);
		panelFuncion.add(btnEjecutar, BorderLayout.LINE_END);
	}


	/**
	 * 
	 */
	private void crearPanelConfiguracion() {
		panelConfiguracion = new JPanel();
		panelConfiguracion.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panelConfiguracion.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		panelConfiguracion.setLayout(new BorderLayout());
		
		panelBotonesConfiguracion = new JPanel(new FlowLayout());
		crearBotonGuardar();			
		crearBotonAbrir();
		panelConfiguracion.add(panelBotonesConfiguracion, BorderLayout.LINE_START);
		panelDatosConfiguracion = new JPanel(new FlowLayout());
		crearNumeroEras();
		crearNumeroGeneraciones();
		crearTamPoblacion();
		crearProbabilidadMutacion();		
		crearProbabilidadCruce();		
		crearElitismo();
		panelConfiguracion.add(panelDatosConfiguracion, BorderLayout.CENTER);
		crearTipoSeleccion();
	}


	private void crearTipoSeleccion() {
		JPanel pTipoSeleccion = new JPanel();
		rbSelRuleta = new JRadioButton("Seleccion por ruleta");
		rbSelRuleta.setSelected(true);
		rbSelTorneo = new JRadioButton("Seleccion por torneo");
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbSelRuleta);
		grupo.add(rbSelTorneo);
		pTipoSeleccion.setLayout(new BoxLayout(pTipoSeleccion, BoxLayout.PAGE_AXIS));
		pTipoSeleccion.add(rbSelRuleta);
		pTipoSeleccion.add(rbSelTorneo);
		panelConfiguracion.add(pTipoSeleccion, BorderLayout.LINE_END);
	}


	/**
	 * 
	 */
	private void crearElitismo() {
		JPanel pElitismo = new JPanel();		
		chkElitismo = new JCheckBox("Elitismo");
		pElitismo.add(chkElitismo);
		panelDatosConfiguracion.add(pElitismo);
//		panelConfiguracion.add(pElitismo, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearProbabilidadCruce() {
		JPanel pProbCruce = new JPanel();		
		JLabel lblProbabilidadDeCruce = new JLabel("Probabilidad de cruce:");		
		spProbCruce = new JSpinner(new SpinnerNumberModel(0.2, 0.0, 1.0, 0.001));
		spProbCruce.setPreferredSize(new Dimension(53, 20));		
		lblProbabilidadDeCruce.setLabelFor(spProbCruce);
		pProbCruce.add(lblProbabilidadDeCruce);	
		pProbCruce.add(spProbCruce);			
		panelDatosConfiguracion.add(pProbCruce);
//		panelConfiguracion.add(pProbCruce, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearProbabilidadMutacion() {
		JPanel pProbMuta = new JPanel();
		JLabel lbProbabilidadMutacion = new JLabel("Probabilidad de mutaci\u00F3n:");
		spProbMutacion = new JSpinner(new SpinnerNumberModel(0.015, 0.0, 1.0, 0.001));
		spProbMutacion.setPreferredSize(new Dimension(53, 20));
		lbProbabilidadMutacion.setLabelFor(spProbMutacion);
		pProbMuta.add(lbProbabilidadMutacion);
		pProbMuta.add(spProbMutacion);	
		panelDatosConfiguracion.add(pProbMuta);
//		panelConfiguracion.add(pProbMuta, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearTamPoblacion() {
		JPanel pTamPoblacion = new JPanel();
		JLabel lbTamPoblacion = new JLabel("Tama\u00F1o de la poblaci\u00F3n:");
		spTamPoblacion = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
		spTamPoblacion.setPreferredSize(new Dimension(53, 20));
		lbTamPoblacion.setLabelFor(spTamPoblacion);
		pTamPoblacion.add(lbTamPoblacion);
		pTamPoblacion.add(spTamPoblacion);		
		panelDatosConfiguracion.add(pTamPoblacion);
//		panelConfiguracion.add(pTamPoblacion, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearNumeroGeneraciones() {
		JPanel pNumGen = new JPanel();
		JLabel lblNumeroGeneraciones = new JLabel("N\u00FAmero de generaciones:");
		spNumGen = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
		spNumGen.setPreferredSize(new Dimension(53, 20));
		lblNumeroGeneraciones.setLabelFor(spNumGen);
		pNumGen.add(lblNumeroGeneraciones);
		pNumGen.add(spNumGen);
		panelDatosConfiguracion.add(pNumGen);
//		panelConfiguracion.add(pNumGen, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearNumeroEras() {
		JPanel pNumEras = new JPanel();		
		spNumEras = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
		JLabel lblNumEras = new JLabel("N\u00FAmero de eras:");
		lblNumEras.setLabelFor(spNumEras);
		pNumEras.add(lblNumEras);
		pNumEras.add(spNumEras);
		panelDatosConfiguracion.add(pNumEras);
//		panelConfiguracion.add(pNumEras, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void crearBotonAbrir() {
		ImageIcon loadIcon = new ImageIcon(this.getClass().getResource("/icons/folder-icon.png"));
//		Icon loadIcon = new ImageIcon(loadIconBig.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JPanel panelAbrir = new JPanel();
		panelAbrir.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		JButton btnAbrir = new JButton(loadIcon);
		btnAbrir.setPreferredSize(new Dimension(35,35));
		panelAbrir.add(btnAbrir);
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarConfiguracion();			
			}
		});
		panelBotonesConfiguracion.add(panelAbrir);
//		panelConfiguracion.add(panelAbrir, BorderLayout.LINE_START);
	}


	/**
	 * 
	 */
	private void crearBotonGuardar() {
		ImageIcon saveIcon = new ImageIcon(this.getClass().getResource("/icons/save-icon.png"));
//		Icon saveIcon = new ImageIcon(saveIconBig.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JPanel panelGuardar = new JPanel();
		panelGuardar.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		JButton btnGuardar = new JButton(saveIcon);
		btnGuardar.setPreferredSize(new Dimension(35,35));
		panelGuardar.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarConfiguracion();			
			}
		});
		panelBotonesConfiguracion.add(panelGuardar);
//		panelConfiguracion.add(panelGuardar, BorderLayout.LINE_START);
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
			} catch (EmptyStackException e) {
				JOptionPane.showMessageDialog(this,"Formato de función de coste incorrecto");
			} catch (Exception e) {
				if (e.getMessage() == null) {
					JOptionPane.showMessageDialog(this,"Formato de función de coste incorrecto");
				} else if (e.getMessage().contains("Mismatched parentheses")) {
					JOptionPane.showMessageDialog(this,
							"Formato de función de coste incorrecto, por favor, revise los paréntesis");
				} else if (e.getMessage().contains("Too many operators")) {
					JOptionPane.showMessageDialog(this,
							"Formato de función de coste incorrecto, demasiados operadores");
				} else if (e.getMessage().contains("Unable to parse setVariable or function starting at pos")) {
					String mensaje = e.getMessage();
					Pattern p = Pattern.compile("\\d+");
					Matcher m = p.matcher(mensaje);
					m.find();
					String parametro = m.group();
					JOptionPane.showMessageDialog(this,"Formato de función de coste incorrecto, parámetro desconocido: "+txtFuncionCoste.getText().charAt(new Integer(parametro)));
				}
				else {
					JOptionPane.showMessageDialog(this,"Formato de función de coste incorrecto");
				}
						
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
						(Double) spProbMutacion.getValue(), chkElitismo.isSelected(), rbSelRuleta.isSelected(), rbSelTorneo.isSelected());

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
		
//		panelResultados.setText(sb.toString());
		textoResultados.setText(sb.toString());
//		textoResultados.validate();
//		textoResultados.repaint();
//		scrlResultados.setVisible(true);
		construirChart(resultados);
//		panelResultados.setVisible(true);
		panelChartResultados.setVisible(true);		
	}

	private void construirChart(List<Era> resultados) {
		panelChart.removeAll();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		int eraActual = 0;
		for (Era e:resultados) {
			eraActual++;
			int generacionActual = 0;
			for (Poblacion g:e.getEvolucionPoblaciones()) {
				generacionActual++;
				Cromosoma mejor = g.obtenerMejor();
				if (generacionActual == 1) {
					dataSet.addValue(mejor.getCoste(), "Coste", ""+eraActual);
				} else {
					dataSet.addValue(mejor.getCoste(), "Coste", "");
				}
			}
		}
		JFreeChart chart = ChartFactory.createLineChart("Evolución del calculo", "Era, Generación", "Coste", dataSet);
		chart.setBackgroundPaint(Color.GRAY);
		ChartPanel chartPanel = new ChartPanel(chart);
		panelChart.add(chartPanel);
		panelChart.validate();
	}


	private void crearPanelBotones() {
		panelBotones = new JPanel();
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

	protected void aniadirParametro(final String nombre, double minimo,
			double maximo, int precision) {
		TipoGen tipoGen = new TipoGen(nombre,minimo, maximo, precision);
		
		if (nombre == null || "".equals(nombre)) {
			JOptionPane.showMessageDialog(this, "El nombre no puede ser nulo");
		} else if (nombre.startsWith("0") || nombre.startsWith("1")
				|| nombre.startsWith("2") || nombre.startsWith("3")
				|| nombre.startsWith("4") || nombre.startsWith("5")
				|| nombre.startsWith("6") || nombre.startsWith("7")
				|| nombre.startsWith("8") || nombre.startsWith("9")) {
			JOptionPane.showMessageDialog(this, "El nombre no puede comenzar por un dígito");
		}
		else if (mapPanelesParametros.containsKey(nombre)) {
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
			
			JPanel panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
			panelLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			JLabel label = new JLabel(texto.toString());
			label.setVerticalAlignment(SwingConstants.TOP);
			panelLabel.add(label);
			
			
			JPanel panelBotones = new JPanel();
			panelBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
			
			Icon editIcon = new ImageIcon(this.getClass().getResource("/icons/pencil-icon.png"));
			JButton btnEditar = new JButton(editIcon);
			btnEditar.setVerticalAlignment(SwingConstants.TOP);
			btnEditar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					editarParametro(nombre);					
				}
			});
			
			
			Icon removeIcon = new ImageIcon(this.getClass().getResource("/icons/delete-icon.png"));
			JButton btnEliminarButton = new JButton(removeIcon);
			btnEliminarButton.setVerticalAlignment(SwingConstants.TOP);
			btnEliminarButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eliminarParametro(nombre);					
				}
			});
			
			panelBotones.add(btnEditar);
			panelBotones.add(btnEliminarButton);
			panel.add(panelLabel, BorderLayout.CENTER);
			panel.add(panelBotones, BorderLayout.LINE_END);
			mapPanelesParametros.put(nombre, panel);
			
			nombreParametro.setText(null);
			minimoParametro.setValue(null);
			maximoParametro.setValue(null);
			precisionParametro.setValue(1);
			
			panelDatos.revalidate();
			panelDatos.repaint();
			
			panelParametros.add(panel);
			panelParametros.revalidate();
			
		}
	}

	private void editarParametro(String nombre) {
		TipoGen parametro = parametros.get(nombre);
		nombreParametro.setText(nombre);
		minimoParametro.setText(String.valueOf(parametro.getMinimo()));
		maximoParametro.setText(String.valueOf(parametro.getMaximo()));
		precisionParametro.setValue(parametro.getPrecision());
		this.eliminarParametro(nombre);
	}
	private void eliminarParametro(String nombre) {
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
	
	protected void guardarConfiguracion() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showSaveDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			Properties prop = new Properties();
			File fichero = fileChooser.getSelectedFile();
			FileOutputStream fo = null;
			try {
				fo = new FileOutputStream(fichero);
				prop.setProperty("nuEras", spNumEras.getValue().toString());
				prop.setProperty("numGens", spNumGen.getValue().toString());
				prop.setProperty("funcionCoste", txtFuncionCoste.getText());
				prop.setProperty("tamPoblacion", spTamPoblacion.getValue()
						.toString());
				prop.setProperty("probCruce", spProbCruce.getValue().toString());
				prop.setProperty("probMutacion", spProbMutacion.getValue()
						.toString());
				if (rbSelRuleta.isSelected()) {
					prop.setProperty("selector", Selector.RULETA);
				} else if (rbSelTorneo.isSelected()) {
					prop.setProperty("selector", Selector.TORNEO);
				}
				if (chkElitismo.isSelected()) {
					prop.setProperty("elitismo", "TRUE");
				} else {
					prop.setProperty("elitismo", "FALSE");
				}
				StringBuilder sb = null;
				for (String key : parametros.keySet()) {
					if (sb == null) {
						sb = new StringBuilder(parametros.get(key)
								.getNombre());
					} else {
						sb.append(",").append(parametros.get(key)
							.getNombre());
					}
					prop.setProperty(key.concat(".minimo"),
							String.valueOf(parametros.get(key).getMinimo()));
					prop.setProperty(key.concat(".maximo"),
							String.valueOf(parametros.get(key).getMaximo()));
					prop.setProperty(key.concat(".precision"),
							String.valueOf(parametros.get(key).getPrecision()));
				}
				if (sb != null) {
					prop.setProperty("parametros.nombres", sb.toString());
				}
				prop.store(fo, null);
			} catch (IOException e) {
				log.error("Error al guardar en fichero ", e);
			} finally {
				if (fo != null) {
					try {
						fo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}
	
	protected void cargarConfiguracion() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showOpenDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			Properties prop = new Properties();
			File fichero = fileChooser.getSelectedFile();
			FileInputStream fi = null;
			try {
				fi = new FileInputStream(fichero);
				prop.load(fi);
				if (prop.containsKey("nuEras")) {
					spNumEras.setValue(Integer.valueOf((String) prop.get("nuEras")));
				}
				if (prop.containsKey("numGens")) {
					spNumGen.setValue(Integer.valueOf((String)prop.get("numGens")));
				}
				if (prop.containsKey("funcionCoste")) {
					 txtFuncionCoste.setText((String) prop.get("funcionCoste"));
				}
				if (prop.containsKey("tamPoblacion")) {
					spTamPoblacion.setValue(Integer.valueOf((String)prop.get("tamPoblacion")));
				}
				if (prop.containsKey("probCruce")) {
					spProbCruce.setValue(Double.valueOf((String)prop.get("probCruce")));
				}
				if (prop.containsKey("probMutacion")) {
					spProbMutacion.setValue(Double.valueOf((String)prop.get("probMutacion")));
				}
				
				if (prop.containsKey("selector")) {
					if (prop.getProperty("selector").equals(Selector.RULETA)) {
						rbSelRuleta.setSelected(true);
						rbSelTorneo.setSelected(false);
					} else if (prop.getProperty("selector").equals(Selector.TORNEO)) {
						rbSelTorneo.setSelected(true);
						rbSelRuleta.setSelected(false);
					}
				}
				
				if (prop.containsKey("elitismo") && "TRUE".equals(prop.getProperty("elitismo"))) {
					chkElitismo.setSelected(true);
				}
				
				
				if (prop.containsKey("parametros.nombres")) {
					String strNombres = prop.getProperty("parametros.nombres");
					String[] arrNombres = strNombres.split(",");
					List<String> listaNombres = Arrays.asList(arrNombres);
					eliminarParametrosExistentes();
					parametros = new HashMap<String, TipoGen>();
					for (String nombre:listaNombres) {
						double minimo = Double.valueOf((String)prop.get(nombre+".minimo"));
						double maximo = Double.valueOf((String)prop.get(nombre+".maximo"));
						int precision = Integer.valueOf((String)prop.get(nombre+".precision"));
						aniadirParametro(nombre, minimo, maximo, precision);
					}
				}
					//TODO Falta la Carga de parametros
				
			} catch (IOException e) {
				log.error("Error al guardar en fichero ", e);
			} finally {
				if (fi != null) {
					try {
						fi.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	private void eliminarParametrosExistentes() {
		try {
			List<String> nombreParam = new ArrayList<String>(parametros.keySet());
			for (String nombre:nombreParam) {
				eliminarParametro(nombre);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
