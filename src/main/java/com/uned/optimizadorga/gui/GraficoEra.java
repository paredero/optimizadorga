package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;
import com.uned.optimizadorga.elementos.Cromosoma;

public class GraficoEra extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752915912950953908L;

	public GraficoEra(ResultadoParcialEra resultadoParcialEra, JFrame parent, String titulo,
			boolean modal) {
		super(parent, titulo, modal);
		setBounds(200, 50, 700, 700);
		this.setLayout(new BorderLayout());
		XYSeries serie = new XYSeries("Era");
		XYSeries media = new XYSeries("Media poblaci�n");
		XYSeries dt = new XYSeries("Desviaci�n t�pica");
		
		int generacionActual = 0;
		for (ResultadoParcialGeneracion g:resultadoParcialEra.getResultadosGeneraciones()) {
			generacionActual++;
			Cromosoma mejor = g.getMejorCromosomaGeneracion();		
			serie.add(generacionActual, mejor.getCoste());
			media.add(generacionActual, g.getMediaCostePoblacion());
			dt.add(generacionActual, g.getDesviacionTipica());
//			dataSet.addValue(mejor.getCoste(), "Coste", generacionActual+"");
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);
		dataset.addSeries(media);
		dataset.addSeries(dt);
		JFreeChart chart = ChartFactory.createXYLineChart("Evoluci�n del calculo", "Generaci�n", "Coste", dataset);
		chart.setAntiAlias(true);
		chart.setBackgroundPaint(Color.GRAY);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.validate();
	}

}
