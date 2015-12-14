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
import com.uned.optimizadorga.model.Chromosome;

/**
 * Ventana con el detalle de los resultados de una era
 * @author Francisco Javier Garc�a Paredero
 *
 */
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
		XYSeries serie = new XYSeries("Mejor individuo de la poblaci�n");
		XYSeries media = new XYSeries("Media poblaci�n");
		XYSeries dt = new XYSeries("Desviaci�n t�pica");
		int generacionActual = 0;
		for (ResultadoGeneracion g : resultadoParcialEra
				.getResultadosGeneraciones()) {
			generacionActual++;
			Chromosome mejor = g.getMejorCromosomaGeneracion();
			serie.add(generacionActual, mejor.getFitness());
			media.add(generacionActual, g.getMediaCostePoblacion());
			dt.add(generacionActual, g.getDesviacionTipica());
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);
		dataset.addSeries(media);
		dataset.addSeries(dt);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Evoluci�n del c�lculo", "Generaci�n", "Coste", dataset);
		chart.setAntiAlias(true);
		Color defaultColor = this.getBackground();
		chart.setBackgroundPaint(defaultColor);
		chart.getPlot().setBackgroundPaint(Color.WHITE);;
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.validate();
	}

}
