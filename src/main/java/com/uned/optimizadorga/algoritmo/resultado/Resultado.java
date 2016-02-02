package com.uned.optimizadorga.algoritmo.resultado;

import com.uned.optimizadorga.model.Chromosome;

/**
 * Clase para mostrar los resultados de la operacion
 * @author Francisco Javier Garc�a Paredero
 *
 */
public abstract class Resultado {
	/**************************************************************************
	 * VALORES COMUNES
	 *************************************************************************/
	

	long tiempoEjecucion;
	private int progreso;
	boolean cambioEra;
	boolean cambioGeneracion;
	int eraActual;
	// El numero de generacion actual
	int generacionActual;
	private Chromosome mejorCromosomaTotal;

	/**
	 * 
	 * @return Cadena con el resultado que se quier emostrar
	 */
	public abstract String printResultado();



	/**
	 * @return the mejorCromosomaGeneracion
	 */
	public Chromosome getMejorCromosomaTotal() {
		return this.mejorCromosomaTotal;
	}

	/**
	 * @param mejorCromosoma
	 *            the mejorCromosoma to set
	 */
	public void setMejorCromosomaTotal(Chromosome mejorCromosoma) {
		this.mejorCromosomaTotal = mejorCromosoma;
	}

	/**
	 * @return the tiempoEjecucion
	 */
	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	/**
	 * @param tiempoEjecucion
	 *            the tiempoEjecucion to set
	 */
	public void setTiempoEjecucion(long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	/**
	 * @return the eraActual
	 */
	public int getEraActual() {
		return eraActual;
	}

	/**
	 * @param eraActual
	 *            the eraActual to set
	 */
	public void setEraActual(int eraActual) {
		this.eraActual = eraActual;
	}

	/**
	 * @return the generacionActual
	 */
	public int getGeneracionActual() {
		return generacionActual;
	}

	/**
	 * @param generacionActual
	 *            the generacionActual to set
	 */
	public void setGeneracionActual(int generacionActual) {
		this.generacionActual = generacionActual;
	}

	/**
	 * @return the cambioEra
	 */
	public boolean isCambioEra() {
		return cambioEra;
	}

	/**
	 * @param cambioEra
	 *            the cambioEra to set
	 */
	public void setCambioEra(boolean cambioEra) {
		this.cambioEra = cambioEra;
	}

	/**
	 * @return the cambioGeneracion
	 */
	public boolean isCambioGeneracion() {
		return cambioGeneracion;
	}

	/**
	 * @param cambioGeneracion
	 *            the cambioGeneracion to set
	 */
	public void setCambioGeneracion(boolean cambioGeneracion) {
		this.cambioGeneracion = cambioGeneracion;
	}

	/**
	 * @return the progreso
	 */
	public int getProgreso() {
		return progreso;
	}

	/**
	 * @param progreso
	 *            the progreso to set
	 */
	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}

}
