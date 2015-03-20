/**
 * 
 */
package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoFinal;

/**
 * @author fpb
 * Interfaz observer para visualizar el progreso del algoritmo
 */
public interface AlgoritmoObserver {
	public void updateEra(Era eraProcesada);
	public void updateGeneracion(Generacion generacionProcesada);
	//TODO ¿Resultado final?
	public void updateFin(ResultadoFinal resultadoFinal);
}
