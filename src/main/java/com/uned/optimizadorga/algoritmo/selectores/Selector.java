package com.uned.optimizadorga.algoritmo.selectores;

import com.uned.optimizadorga.elementos.Poblacion;

/**
 * 
 * @author fpb
 * Interfaz que deben cumplir los distintos operadores de selección
 */
public interface Selector {

	public Poblacion seleccionar(Poblacion poblacion);

}
