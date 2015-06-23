package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * Interfaz para implementar el patron observer para registrar el progreso del
 * calculo del algoritmo
 * 
 * @author jgarcia
 *
 */
public interface AlgoritmoSubject {
	/**
	 * Los elementos que quieren recibir actualizaciones sobre el progreso del
	 * calculo se registran ante el algoritmo. En este caso se trata de los
	 * elementos del interfaz grafico, que deben implementar el interfaz
	 * AlgoritmoObserver si desean que se les manden actualizaciones
	 * 
	 * @param observer
	 */
	public void registerObserver(AlgoritmoObserver observer);
	
	/**
	 * Cuando se termina el calculo de una era se actualiza el estado de los
	 * observadores
	 * 
	 * Fundamentalmente se trata de la barra de progreso y de los resultados
	 * parciales en el interfaz
	 * 
	 * @param eraProcesada
	 */
	public void notifyFinCalculoEra(Era eraProcesada);
	
	/**
	 * Cuando se termina el calculo de una generación se actualiza el estado de
	 * los observadores
	 * 
	 * Fundamentalmente los resultados parciales y la barra de progreso en el
	 * interfaz
	 * 
	 * @param generacionProcesada
	 */
	public void notifyFinCalculoGeneracion(Generacion generacionProcesada);
	
	/**
	 * Cuando finaliza el cálculo del algoritmo se notifica a los observadores
	 */
	public void notifyFinEjecucion();

	/**
	 * Si se produce un error enla ejecucion se notifica para que los
	 * observadores puedan volver a una situación controlada
	 */
	public void notifyError(Exception e);
}
