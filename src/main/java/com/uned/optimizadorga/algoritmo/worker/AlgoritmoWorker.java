package com.uned.optimizadorga.algoritmo.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
<<<<<<< HEAD
import java.util.concurrent.Future;
=======
>>>>>>> 9842eda38fb2fa57fd1aa50ad3a2ec4afaad1765

import javax.swing.SwingWorker;

import org.jfree.util.Log;

import com.uned.optimizadorga.algorithm.Algorithm;
import com.uned.optimizadorga.algorithm.Era;
import com.uned.optimizadorga.algorithm.Generation;
import com.uned.optimizadorga.algorithm.observerinterfaces.AlgorithmObserver;
import com.uned.optimizadorga.algoritmo.resultado.Resultado;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoGeneracion;
import com.uned.optimizadorga.algoritmo.util.TimeUtils;
import com.uned.optimizadorga.gui.ProgressDialog;
import com.uned.optimizadorga.model.Population;

/**
 * Esta clase es un observer del progreso del algoritmo implementa el interfaz
 * AlgoritmoObserver para recibir actualizaciones del progreso del algoritmo y
 * poder mostrar resultados parciales y finales
 * 
 * @author Francisco Javier Garc�a Paredero
 *
 */
public class AlgoritmoWorker extends SwingWorker<List<ResultadoEra>, Resultado>
		implements AlgorithmObserver {
	private ProgressDialog progressDialog;
	private boolean finEjecucion;
	private long startTime = 0;

	private Algorithm algoritmo;

	private List<ResultadoEra> resultadosEras;
	private List<ResultadoGeneracion> resultadosGeneraciones;
	private String error;

	/**
	 * Constructor del worker
	 * 
	 * @param algoritmo
	 *            la instancia del algoritmo a ejecutar
	 * @param progressDialog
	 *            El dialogo que va a recibir las actualizaciones
	 */
	public AlgoritmoWorker(
			Algorithm algoritmo, ProgressDialog progressDialog) {
		this.algoritmo = algoritmo;
		// Se registra como observador para que sea el algoritmo quien le
		// informe del progreso
		this.algoritmo.registerObserver(this);
		this.resultadosEras = new ArrayList<ResultadoEra>(
				algoritmo.getConfiguracion().getMaxEras());
		this.resultadosGeneraciones = new ArrayList<ResultadoGeneracion>(
				algoritmo.getConfiguracion().getMaxGens());
		this.progressDialog = progressDialog;
	}

	@Override
	protected List<ResultadoEra> doInBackground() throws Exception {
		startTime = System.currentTimeMillis();
		ExecutorService pool = Executors.newCachedThreadPool();
		finEjecucion = false;
<<<<<<< HEAD
		Future result = pool.submit(algoritmo);

		while (!this.finEjecucion && !result.isCancelled()) {
=======
		pool.submit(this.algoritmo);
		
		while (!this.finEjecucion && !this.isCancelled()) {
>>>>>>> 9842eda38fb2fa57fd1aa50ad3a2ec4afaad1765
			// Espera a que termine la ejecucion o sea cancelada
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
		}
		if (this.isCancelled()) {
<<<<<<< HEAD
			result.cancel(true);
=======
			pool.shutdownNow();
>>>>>>> 9842eda38fb2fa57fd1aa50ad3a2ec4afaad1765
		}
		return resultadosEras;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	@Override
	protected void process(List<Resultado> chunks) {
		for (Resultado r : chunks) {
			String tiempoTranscurrido = TimeUtils.formatear(r.getTiempoEjecucion());
			this.progressDialog.getProgressBar().setValue(r.getProgreso());
			this.progressDialog.getProgressBar()
					.setString(r.getProgreso() + "%  " + tiempoTranscurrido);
			if (r.isCambioEra()) {
				this.progressDialog.getPanelResultadoEra()
						.setText(((ResultadoEra) r).printResultado());
				this.progressDialog.getPanelResultadoGeneracion().setText("");
			} else if (r.isCambioGeneracion()) {
				this.progressDialog.getPanelResultadoGeneracion()
						.setText(((ResultadoGeneracion) r).printResultado());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		this.progressDialog.setVisible(false);
		this.progressDialog = null;
		this.algoritmo = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#
	 * updateFinCalculoEra(com.uned.optimizadorga.algoritmo.Era)
	 */
	@Override
	public void updateEndEraExecution(Era eraProcesada) {
<<<<<<< HEAD
		ResultadoEra resultadoEra = ResultadoEra.crearResultadoEra(startTime, eraProcesada,
				resultadosEras, resultadosGeneraciones, algoritmo.getConfiguracion());
=======
		ResultadoEra resultadoEra = null;
		try {
			resultadoEra = ResultadoEra
					.crearResultadoEra(startTime, eraProcesada, resultadosEras,
							resultadosGeneraciones, algoritmo.getConfiguracion());
		} catch (Exception e) {
			Log.error("Error while processing ERA results", e);
			this.updateError(e);
		}
>>>>>>> 9842eda38fb2fa57fd1aa50ad3a2ec4afaad1765
		resultadosEras.add(resultadoEra);
		this.resultadosGeneraciones = new ArrayList<ResultadoGeneracion>(
				algoritmo.getConfiguracion().getMaxGens());
		publish(resultadoEra);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#
	 * updateFinCalculoGeneracion(com.uned.optimizadorga.algoritmo.Generacion)
	 */
	@Override
	public void updateEndGenerationExecution(Generation generacionProcesada) {
		ResultadoGeneracion resultadoGeneracion = ResultadoGeneracion.crearResultadoGeneracion(
				generacionProcesada, startTime, resultadosEras, resultadosGeneraciones,
				algoritmo.getConfiguracion());
		resultadosGeneraciones.add(resultadoGeneracion);
		publish(resultadoGeneracion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#updateFin(
	 * java.util.List)
	 */
	@Override
	public void updateEnd() {
		finEjecucion = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver#updateError
	 * (java.lang.Exception)
	 */
	@Override
	public void updateError(Exception e) {
		if (e.getMessage().equals("Division by zero!")) {
			this.error = "Error en la ejecucion: Division por cero";
		}
		this.cancel(true);
	}

	/**
	 * @return the resultadosEras
	 */
	public List<ResultadoEra> getResultados() {
		return this.resultadosEras;
	}

	/**
	 * 
	 * @return Si se ha producido un error devuelve el mensaje
	 */
	public String getError() {
		return this.error;
	}

<<<<<<< HEAD
	@Override
	public void updateEndEraExecution(List<Population> resultEra) {
		// TODO Auto-generated method stub

	}

=======

@Override
public void updateEndEraExecution(List<Population> resultEra) {
	// TODO Auto-generated method stub
	
}
	
	
	
>>>>>>> 9842eda38fb2fa57fd1aa50ad3a2ec4afaad1765
}
