package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * Interfaz para implementar el patron observer para registrar el progreso del calculo
 * @author jgarcia
 *
 */
public interface AlgoritmoSubject {
	/**
	 * Los elementos que quieren recibir actualizaciones sobre el progreso del calculo
	 * se registran ante el algoritmo
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
	public void notifyEra(Era eraProcesada);
	
	/**
	 * Cuando se termina el calculo de una generaci�n se actualiza el estado de
	 * los observadores
	 * 
	 * Fundamentalmente los resultados parciales y la barra de progreso en el
	 * interfaz
	 * 
	 * @param generacionProcesada
	 */
	public void notifyGeneracion(Generacion generacionProcesada);
	
	/**
	 * Cuando finaliza el c�lculo del algoritmo se notifica a los observadores
	 */
	public void notifyFin();
}