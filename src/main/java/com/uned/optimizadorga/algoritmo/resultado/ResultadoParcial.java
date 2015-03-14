package com.uned.optimizadorga.algoritmo.resultado;

import com.uned.optimizadorga.elementos.Cromosoma;

public class ResultadoParcial {
	long tiempoEjecucion;
	int eraActual;
	int generacionActual;
	
	boolean cambioEra; //TODO Do I really need this??
	boolean cambioGeneracion; //TODO Do I really need this??
	private Cromosoma mejorCromosoma;
	private double mediaMejorValor;
	private Cromosoma mejorCromosomaTotal;
	private double mediaCoste;
	
	
	/**
	 * @return the tiempoEjecucion
	 */
	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}
	/**
	 * @param tiempoEjecucion the tiempoEjecucion to set
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
	 * @param eraActual the eraActual to set
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
	 * @param generacionActual the generacionActual to set
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
	 * @param cambioEra the cambioEra to set
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
	 * @param cambioGeneracion the cambioGeneracion to set
	 */
	public void setCambioGeneracion(boolean cambioGeneracion) {
		this.cambioGeneracion = cambioGeneracion;
	}
	public void setMejorCromosoma(Cromosoma mejorCromosoma) {
		this.mejorCromosoma = mejorCromosoma;
	}
	/**
	 * @return the mejorCromosoma
	 */
	public Cromosoma getMejorCromosoma() {
		return this.mejorCromosoma;
	}
	public void setMediaMejorValor(double mediaMejorValor) {
		this.mediaMejorValor = mediaMejorValor;
	}
	/**
	 * @return the mejorCromosomaTotal
	 */
	public Cromosoma getMejorCromosomaTotal() {
		return this.mejorCromosomaTotal;
	}
	/**
	 * @param mejorCromosomaTotal the mejorCromosomaTotal to set
	 */
	public void setMejorCromosomaTotal(Cromosoma mejorCromosomaTotal) {
		this.mejorCromosomaTotal = mejorCromosomaTotal;
	}
	/**
	 * @return the mediaMejorValor
	 */
	public double getMediaMejorValor() {
		return this.mediaMejorValor;
	}
	public void setMediaCoste(double mediaCoste) {
		this.mediaCoste = mediaCoste;
	}
	/**
	 * @return the mediaCoste
	 */
	public double getMediaCoste() {
		return this.mediaCoste;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResultadoParcial [tiempoEjecucion=" + tiempoEjecucion
				+ ", eraActual=" + eraActual + ", generacionActual="
				+ generacionActual + ", cambioEra=" + cambioEra
				+ ", cambioGeneracion=" + cambioGeneracion
				+ ", mejorCromosoma=" + mejorCromosoma + ", mediaMejorValor="
				+ mediaMejorValor + ", mejorCromosomaTotal="
				+ mejorCromosomaTotal + ", mediaCoste=" + mediaCoste + "]";
	}
	
	
	
	
	
}
