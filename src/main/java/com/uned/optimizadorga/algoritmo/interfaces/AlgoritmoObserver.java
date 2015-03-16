/**
 * 
 */
package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoFinal;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;

/**
 * @author fpb
 * Interfaz observer para visualizar el progreso del algoritmo
 */
public interface AlgoritmoObserver {
	public void updateEra(ResultadoParcialEra resultadoParcial);
	public void updateGeneracion(ResultadoParcialGeneracion resultadoParcial);
	//TODO ¿Resultado final?
	public void updateFin(ResultadoFinal resultadoFinal);
}
