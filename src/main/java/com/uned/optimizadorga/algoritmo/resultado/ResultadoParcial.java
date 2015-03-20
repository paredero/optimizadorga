package com.uned.optimizadorga.algoritmo.resultado;

import java.util.List;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;

public abstract class ResultadoParcial {
	/**************************************************************************
	 * VALORES COMUNES
	 *************************************************************************/
	long tiempoEjecucion;
	private int progreso;
	boolean cambioEra; //TODO Do I really need this??
	boolean cambioGeneracion; //TODO Do I really need this??
	int eraActual;
	// El numero de generacion actual
	int generacionActual;
	private Cromosoma mejorCromosoma;
	private double mediaCoste;

	public abstract String printResultado();

	protected static int calcularProgreso(List<Era> listaEras,
			List<Generacion> listaGeneraciones, Configuracion configuracion) {
//		log.debug("****************PROGRESO********************************");
		double progreso = 0;
		
		double numGeneracion = listaGeneraciones.size();
		double numEra = listaEras.size();
		
		double totalGeneraciones = configuracion.getMaxGens();
		double totalEras = configuracion.getMaxEras();
		
		progreso = (((numEra) / totalEras) * 100)
					+ ((numGeneracion / (totalEras * totalGeneraciones)) * 100);
		
		// TODO Delete once tested
//		if (resultadoParcial.isCambioEra()) {
//			progreso = (((eraActual) / totalEras) * 100)
//					+ ((generacionActual / (totalEras * totalGeneraciones)) * 100);
//		} else if (resultadoParcial.isCambioGeneracion()) {
//			progreso = (((eraActual-1) / totalEras) * 100)
//				+ ((generacionActual / (totalEras * totalGeneraciones)) * 100); 
//		}
		return (int)progreso;
	}

	/**
	 * @return the mejorCromosomaGeneracion
	 */
	public Cromosoma getMejorCromosoma() {
		return this.mejorCromosoma;
	}


	/**
	 * @param mejorCromosoma the mejorCromosoma to set
	 */
	public void setMejorCromosoma(Cromosoma mejorCromosoma) {
		this.mejorCromosoma = mejorCromosoma;
	}
	
	
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
	

	/**
	 * @param mediaCoste the mediaCoste to set
	 */
	public void setMediaCoste(double mediaCoste) {
		this.mediaCoste = mediaCoste;
	}


	/**
	 * @return the mediaCoste
	 */
	public double getMediaCoste() {
		return this.mediaCoste;
	}
	/**
	 * @return the progreso
	 */
	public int getProgreso() {
		return progreso;
	}
	/**
	 * @param progreso the progreso to set
	 */
	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}


	
}
