package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * Interfaz para implementar el patron observer en la relaci�n entre algoritmo y
 * era El algoritmo crea una era que se ejecuta y va actualizando al algoritmo
 * segun va cambiando su estado Las modificaciones del estado de una era
 * consisten en que se calcule una nueva generaci�n
 * 
 * @author jgarcia
 * 
 */
public interface EraObserver {

	/**
	 * Cuando la era finaliza el calculo de una generaci�n pasa los resultados
	 * al algoritmo para que muestre los resultados parciales
	 * 
	 * @param generacionProcesada
	 */
	public void updateGeneracion(Generacion generacionProcesada);
}
