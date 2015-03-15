/**
 * 
 */
package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoFinal;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;

/**
 * @author fpb
 * Interfaz observer para visualizar el progreso del algoritmo
 */
public interface AlgoritmoObserver {
	public void updateEra(ResultadoParcial resultadoParcial);
	public void updateGeneracion(ResultadoParcial resultadoParcial);
	//TODO ¿Resultado final?
	public void updateFin(ResultadoFinal resultadoFinal);
}
