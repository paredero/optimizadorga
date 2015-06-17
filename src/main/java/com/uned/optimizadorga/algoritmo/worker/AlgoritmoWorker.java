package com.uned.optimizadorga.algoritmo.worker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;
import com.uned.optimizadorga.algoritmo.util.TimeUtils;
import com.uned.optimizadorga.gui.ProgressDialog;

/**
 * Esta clase es un observer del progreso del algoritmo implementa el interfaz
 * AlgoritmoObserver para recibir actualizaciones del progreso del algoritmo y
 * poder mostrar resultados parciales y finales
 * 
 * @author fpb
 *
 */
public class AlgoritmoWorker extends SwingWorker<List<Era>, ResultadoParcial> implements
		AlgoritmoObserver {
	private static final Logger log = Logger.getLogger(AlgoritmoWorker.class);
	private ProgressDialog progressDialog;
	private boolean finEjecucion;
	private long startTime = 0;
	
	private Algoritmo algoritmo;
	private List<Era> erasProcesadas;
	private List<Generacion> generacionesProcesadas;	
	
	public AlgoritmoWorker(Algoritmo algoritmo, ProgressDialog progressDialog) {
		this.algoritmo = algoritmo;
		// Se registra como observador para que sea el algoritmo quien le informe del progreso
		this.algoritmo.registerObserver(this);
		this.erasProcesadas = new ArrayList<Era>(algoritmo.getConfiguracion().getMaxEras());
		this.generacionesProcesadas = new ArrayList<Generacion>(algoritmo.getConfiguracion().getMaxGens());
		this.progressDialog = progressDialog;		
	}




	@Override
	protected List<Era> doInBackground() throws Exception {
		startTime = System.currentTimeMillis();
		Thread threadAlgoritmo = new Thread(algoritmo);
		finEjecucion = false;
		threadAlgoritmo.start();
		
		while (!this.finEjecucion && !this.isCancelled()) {
			// Espera a que termine la ejecucion o sea cancelada
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				log.debug("interrumpido");
			}
		}
		if (this.isCancelled()) {
			threadAlgoritmo.interrupt();
		}
		return erasProcesadas;
	}

	


	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	@Override
	protected void process(List<ResultadoParcial> chunks) {		
		for (ResultadoParcial r:chunks) {
//			log.debug("Era: " + r.getEraActual() +" Gen: " + r.getGeneracionActual() + " progreso: " + r.getProgreso());
			String tiempoTranscurrido = TimeUtils.formatear(r.getTiempoEjecucion());
			this.progressDialog.getProgressBar().setValue(r.getProgreso());
			this.progressDialog.getProgressBar().setString(r.getProgreso()+"%  "+ tiempoTranscurrido);
			if (r.isCambioEra()) {
				this.progressDialog.getPanelResultadoEra().setText(((ResultadoParcialEra)r).printResultado());
				this.progressDialog.getPanelResultadoGeneracion().setText("");
			} else if (r.isCambioGeneracion()) {
				this.progressDialog.getPanelResultadoGeneracion().setText(((ResultadoParcialGeneracion)r).printResultado());
			}
		}
	}


	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		this.progressDialog.setVisible(false);
		this.progressDialog = null;
		this.generacionesProcesadas = null;
		this.algoritmo = null;
	}



	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#updateFinCalculoEra(com.uned.optimizadorga.algoritmo.Era)
	 */
	@Override
	public void updateFinCalculoEra(Era eraProcesada) {
//		log.debug("Cambio de era: " + erasProcesadas.size());
		erasProcesadas.add(eraProcesada);
		ResultadoParcialEra resultadoEra = ResultadoParcialEra
					.crearResultadoEra(startTime, erasProcesadas, algoritmo.getConfiguracion());
//		eraProcesada.liberarRecursos();
		this.generacionesProcesadas = new ArrayList<Generacion>(algoritmo.getConfiguracion().getMaxGens());
		publish(resultadoEra);
	}


	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#updateFinCalculoGeneracion(com.uned.optimizadorga.algoritmo.Generacion)
	 */
	@Override
	public void updateFinCalculoGeneracion(Generacion generacionProcesada) {
//		log.debug("Cambio de generacion: " + generacionesProcesadas.size());
		generacionesProcesadas.add(generacionProcesada);
		ResultadoParcialGeneracion resultadoGeneracion = ResultadoParcialGeneracion
				.crearResultadoGeneracion(startTime, erasProcesadas,
						generacionesProcesadas, algoritmo.getConfiguracion());
		publish(resultadoGeneracion);
	}

	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#updateFin(java.util.List)
	 */
	@Override
	public void updateFin(List<Era> listaEras) {
		this.erasProcesadas = listaEras;		
		finEjecucion = true;
	}




	@Override
	public void updateError() {
		this.cancel(true);
	}
}
