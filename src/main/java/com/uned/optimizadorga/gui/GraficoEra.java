package com.uned.optimizadorga.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

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
		setBounds(200, 100, 800, 800);
		this.setLayout(new BorderLayout());
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		int generacionActual = 0;
		for (Poblacion g:era.getEvolucionPoblaciones()) {
			generacionActual++;
			Cromosoma mejor = g.obtenerMejor();			
			dataSet.addValue(mejor.getCoste(), "Coste", generacionActual+"");
		}
		JFreeChart chart = ChartFactory.createLineChart("Evolución del calculo", "Generación", "Coste", dataSet);
		chart.setBackgroundPaint(Color.GRAY);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
		this.validate();
	}

}
