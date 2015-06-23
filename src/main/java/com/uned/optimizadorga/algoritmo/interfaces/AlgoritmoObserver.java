/**
 * 
 */
package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * @author fpb Interfaz observer para visualizar el progreso del algoritmo Este
 *         interfaz deben implementarlo los elementos que desean recibir
 *         actualizaciones sobhre el progreso del algoritmo En este caso se
 *         trata del interfaz grafico que debe implementar este interfaz si
 *         desea que se le envien actualizaciones cuando cambie el estado del
 *         algoritmo para poder mostrar resultados parciales
 */
public interface AlgoritmoObserver {
	/**
	 * Metodo que se emplea para que el observador reciba una actualizacion de
	 * fin del calculo de una era
	 * 
	 * @param eraProcesada
	 */
	public void updateFinCalculoEra(Era eraProcesada);

	/**
	 * Metodo que se emplea para que el observador (El worker) reciba una
	 * actualizacion de fin de calculo de una generacion
	 * 
	 * @param generacionProcesada
	 */
	public void updateFinCalculoGeneracion(Generacion generacionProcesada);

	/**
	 * Metodo que se emplea para que el observador (Worker) reciba una
	 * actualizacion de finalizacion del algoritmo
	 */
	public void updateFin();

	/**
	 * Método que se emplea para que el observador sea avisado cuando se
	 * produzca un error en la ejecución para que vuelva a una situación
	 * controlada
	 */
	public void updateError(Exception e);
}
