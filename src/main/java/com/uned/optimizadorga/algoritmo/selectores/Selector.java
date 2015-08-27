package com.uned.optimizadorga.algoritmo.selectores;

import com.uned.optimizadorga.elementos.Poblacion;

/**
 * 
 * @author Francisco Javier García Paredero
 * Interfaz que deben cumplir los distintos operadores de selección
 */
public interface Selector {

	String RULETA = "RULETA";
	String TORNEO = "TORNEO";

	/**
	 * Dada una población original devuelve una nueva población con los
	 * cromosomas seleccionados como resultado de aplicar el operador de
	 * seleccion
	 * 
	 * @param poblacion
	 * @return  una nueva población con los
	 * cromosomas seleccionados como resultado de aplicar el operador de
	 * seleccion
	 */
	public Poblacion seleccionar(Poblacion poblacion);

	public String getTipoSelector();

}
