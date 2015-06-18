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

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

public class GraficoEra extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752915912950953908L;

	public GraficoEra(Era era, JFrame parent, String titulo,
			boolean modal) {
		super(parent, titulo, modal);
//		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(200, 50, 700, 700);
		this.setLayout(new BorderLayout());
		XYSeries serie = new XYSeries("Era");
//		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		int generacionActual = 0;
		for (Poblacion g:era.getEvolucionPoblaciones()) {
			generacionActual++;
			Cromosoma mejor = g.obtenerMejor();		
			serie.add(generacionActual, mejor.getCoste());
//			dataSet.addValue(mejor.getCoste(), "Coste", generacionActual+"");
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);
		JFreeChart chart = ChartFactory.createXYLineChart("Evolución del calculo", "Generación", "Coste", dataset);
		chart.setAntiAlias(true);
		chart.setBackgroundPaint(Color.GRAY);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.validate();
	}

}
