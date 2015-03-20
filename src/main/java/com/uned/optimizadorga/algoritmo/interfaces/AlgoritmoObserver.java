/**
 * 
 */
package com.uned.optimizadorga.algoritmo.interfaces;

import java.util.List;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * @author fpb
 * Interfaz observer para visualizar el progreso del algoritmo
 */
public interface AlgoritmoObserver {
	public void updateEra(Era eraProcesada);
	public void updateGeneracion(Generacion generacionProcesada);
	public void updateFin(List<Era> listaEras);
}
