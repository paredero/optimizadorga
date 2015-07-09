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

import com.uned.optimizadorga.algoritmo.resultado.ResultadoEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoGeneracion;
import com.uned.optimizadorga.elementos.Cromosoma;

public class GraficoEra extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752915912950953908L;

	public GraficoEra(ResultadoEra resultadoParcialEra, JFrame parent,
			String titulo, boolean modal) {
		super(parent, titulo, modal);
		setBounds(200, 50, 700, 700);
		this.setLayout(new BorderLayout());
		XYSeries serie = new XYSeries("Mejor individuo de la población");
		XYSeries media = new XYSeries("Media población");
		XYSeries dt = new XYSeries("Desviación típica");
		int generacionActual = 0;
		for (ResultadoGeneracion g : resultadoParcialEra
				.getResultadosGeneraciones()) {
			generacionActual++;
			Cromosoma mejor = g.getMejorCromosomaGeneracion();
			serie.add(generacionActual, mejor.getCoste());
			media.add(generacionActual, g.getMediaCostePoblacion());
			dt.add(generacionActual, g.getDesviacionTipica());
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);
		dataset.addSeries(media);
		dataset.addSeries(dt);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Evolución del cálculo", "Generación", "Coste", dataset);
		chart.setAntiAlias(true);
		Color defaultColor = this.getBackground();
		chart.setBackgroundPaint(defaultColor);
		chart.getPlot().setBackgroundPaint(Color.WHITE);;
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.validate();
	}

}
