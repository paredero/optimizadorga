package com.uned.optimizadorga.algoritmo.worker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.comparadores.ComparadorMejorCoste;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoFinal;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;
import com.uned.optimizadorga.algoritmo.util.TimeUtils;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.gui.ProgressDialog;

/**
 * Esta clase es un observa el progreso del algoritmo
 * @author fpb
 *
 */
public class AlgoritmoWorker extends SwingWorker<ResultadoFinal, ResultadoParcial> implements
		AlgoritmoObserver {
	private static final Logger log = Logger.getLogger(AlgoritmoWorker.class);
	private Algoritmo algoritmo;
	private ProgressDialog progressDialog;
	private boolean finEjecucion;
	private ResultadoFinal resultadoFinal;
	private List<ResultadoParcial> resultadosEras;
	private List<ResultadoParcial> resultadosGeneraciones;
	
	public AlgoritmoWorker(Algoritmo algoritmo, ProgressDialog progressDialog) {
		this.algoritmo = algoritmo;
		this.algoritmo.registerObserver(this);
		this.resultadosEras = new ArrayList<ResultadoParcial>();
		this.resultadosGeneraciones = new ArrayList<ResultadoParcial>();
		this.progressDialog = progressDialog;
		
	}




	@Override
	protected ResultadoFinal doInBackground() throws Exception {
//		long startTime = System.currentTimeMillis();
		Thread threadAlgoritmo = new Thread(algoritmo);
		finEjecucion = false;
		threadAlgoritmo.start();
//		double totalGeneraciones = algoritmo.getMaxGens();
//		double progreso = 0;
//		while (progreso <100 && !this.isCancelled()) {
//			try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                System.out.println("interrumpido");
//            }
////			double generacionActual = algoritmo.getGeneracionActual();
////			progreso = (generacionActual / totalGeneraciones) * 100;
////			publish((int)(progreso));
//		}
		
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
//		long endTime = System.currentTimeMillis();
//		long totalTime = (endTime - startTime)/1000;
//		return totalTime;
		return resultadoFinal;
	}

	


	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	@Override
	protected void process(List<ResultadoParcial> chunks) {
		ResultadoParcial r = chunks.get(0);
		String tiempoTranscurrido = TimeUtils.formatear(r.getTiempoEjecucion());
		int progreso = calcularProgreso(r);
		this.progressDialog.getProgressBar().setValue(progreso);
		this.progressDialog.getProgressBar().setString(progreso+"%  "+ tiempoTranscurrido);
		StringBuilder sb = new StringBuilder(this.progressDialog.getPanelResultadoParcial().getText());
		if (r.getMejorCromosomaTotal() != null) {
			// Se trata de un cambio en la era
			sb.append("*************************************************\n");
			sb.append("Era actual: ").append(r.getEraActual()).append("\n");
			sb.append("\t Mejor cromosoma de la era ").append(r.getMejorCromosoma()).append("\n");
			sb.append("\t Mejor cromosoma total: ").append(r.getMejorCromosomaTotal()).append("\n");
			sb.append("\t Media del mejor valor del coste: ").append(r.getMediaMejorValor()).append("\n");
		} else {
			// Cambio de generacion
			sb.append("\tGeneraci�n actual: ").append(r.getGeneracionActual()).append("\n");
			sb.append("\t\t Mejor cromosoma de la generacion ").append(r.getMejorCromosoma()).append("\n");
			sb.append("\t\t Media de la funci�n de coste: ").append(r.getMediaCoste()).append("\n");
//			sb.append("\t\t Desviaci�n est�ndar de la funci�n de coste: ").append(r.getDesviacionEstandar()).append("\n");
//			sb.append("\t\t Mejora del mejor valor del coste: ").append(r.getMejoraCoste()).append("\n");
		}
		this.progressDialog.getPanelResultadoParcial().setText(sb.toString());
	}

	private int calcularProgreso(ResultadoParcial resultadoParcial) {
		double progreso = 0;
		
		double generacionActual = resultadoParcial.getGeneracionActual();
		double eraActual = resultadoParcial.getEraActual();
		
		double totalGeneraciones = algoritmo.getConfiguracion().getMaxGens();
		double totalEras = algoritmo.getConfiguracion().getMaxEras();
		
		progreso = ((eraActual / totalEras) * 100)
				+ ((generacionActual / (totalEras * totalGeneraciones)) * 100); 
		log.debug("Era " + eraActual);
		log.debug("Generacion " + generacionActual);
		log.debug("Actualiza el progreso " + progreso);
		return (int)progreso;
	}




	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		this.progressDialog.setVisible(false);
	}



	@Override
	public void updateEra(ResultadoParcial resultadoParcial) {
		log.debug("Cambio de era: " + resultadoParcial.getEraActual());
		resultadosEras.add(resultadoParcial);
		calcularMediaCoste(resultadosEras);
		calcularMejorCosteTotal(resultadosEras);
		this.resultadosGeneraciones = new ArrayList<ResultadoParcial>();
		publish(resultadoParcial);
	}


	/**
	 * Calcula el valor del mejor coste hasta el momento y lo a�ade al ultimo
	 * resultado parcial
	 * 
	 * @param resultadosEras2
	 */
	private void calcularMejorCosteTotal(List<ResultadoParcial> resultadosEras) {
		ComparadorMejorCoste c = new ComparadorMejorCoste();
		Cromosoma mejorCromosomaHastaAhora = resultadosEras.get(
				resultadosEras.size() - 2).getMejorCromosomaTotal();
		Cromosoma mejorCromosomaUltimaEra = resultadosEras.get(
				resultadosEras.size() - 1).getMejorCromosoma();
		int comparacion = c.compare(mejorCromosomaHastaAhora,
				mejorCromosomaUltimaEra);
		if (comparacion <= 0) {
			resultadosEras.get(resultadosEras.size() - 1)
			.setMejorCromosomaTotal(mejorCromosomaUltimaEra);
		} else {
			resultadosEras.get(resultadosEras.size() - 1)
			.setMejorCromosomaTotal(mejorCromosomaHastaAhora);
		}
	}




	/**
	 * Calcula la media del mejor valor de coste y la a�ade al ultimo elemento
	 * @param resultadosEras
	 */
	private void calcularMediaCoste(List<ResultadoParcial> resultadosEras) {
		double total = 0;
		int count = 0;
		for (ResultadoParcial r:resultadosEras) {
			total +=r.getMejorCromosoma().getCoste();
			count++;
		}
		resultadosEras.get(resultadosEras.size()-1).setMediaMejorValor(total/count);
	}




	@Override
	public void updateGeneracion(ResultadoParcial resultadoParcial) {
		log.debug("Cambio de generacion: " + resultadoParcial.getGeneracionActual());
		resultadosGeneraciones.add(resultadoParcial);
		procesarResultadosGeneraciones(resultadosGeneraciones);
		publish(resultadoParcial);
	}

	private void procesarResultadosGeneraciones(
			List<ResultadoParcial> resultadosGeneraciones2) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void updateFin(ResultadoFinal resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
		finEjecucion = true;
	}
}
