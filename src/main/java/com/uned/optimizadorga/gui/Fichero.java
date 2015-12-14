package com.uned.optimizadorga.gui;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.uned.optimizadorga.algorithm.selectors.SelectorType;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoEra;
import com.uned.optimizadorga.model.GeneType;
/**
 * Almacena los datos de la aplicacion que se guardaran en fichero
 * @author Francisco Javier Garc�a Paredero
 *
 */
public class Fichero implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2641409126062194832L;
	private int numeroEras;
	private int numeroGeneraciones;
	private String funcionCoste;
	private int tamanioPoblacion;
	private double probabilidadCruce;
	private double probabilidadMutacion;
	private Set<GeneType> parametros;
	private SelectorType selector;
	private boolean elitismo;
	private List<ResultadoEra> resultados;
	
	
	
	/**
	 * @return the elitismo
	 */
	public boolean getElitismo() {
		return this.elitismo;
	}
	/**
	 * @param elitismo the elitismo to set
	 */
	public void setElitismo(boolean elitismo) {
		this.elitismo = elitismo;
	}
	/**
	 * @return the numeroEras
	 */
	public int getNumeroEras() {
		return this.numeroEras;
	}
	/**
	 * @param numeroEras the numeroEras to set
	 */
	public void setNumeroEras(int numeroEras) {
		this.numeroEras = numeroEras;
	}
	/**
	 * @return the numeroGeneraciones
	 */
	public int getNumeroGeneraciones() {
		return this.numeroGeneraciones;
	}
	/**
	 * @param numeroGeneraciones the numeroGeneraciones to set
	 */
	public void setNumeroGeneraciones(int numeroGeneraciones) {
		this.numeroGeneraciones = numeroGeneraciones;
	}

	
	/**
	 * @return the funcionCoste
	 */
	public String getFuncionCoste() {
		return this.funcionCoste;
	}
	/**
	 * @param funcionCoste the funcionCoste to set
	 */
	public void setFuncionCoste(String funcionCoste) {
		this.funcionCoste = funcionCoste;
	}
	/**
	 * @return the tamanioPoblacion
	 */
	public int getTamanioPoblacion() {
		return this.tamanioPoblacion;
	}
	/**
	 * @param tamanioPoblacion the tamanioPoblacion to set
	 */
	public void setTamanioPoblacion(int tamanioPoblacion) {
		this.tamanioPoblacion = tamanioPoblacion;
	}
	/**
	 * @return the probabilidadCruce
	 */
	public double getProbabilidadCruce() {
		return this.probabilidadCruce;
	}
	/**
	 * @param probabilidadCruce the probabilidadCruce to set
	 */
	public void setProbabilidadCruce(double probabilidadCruce) {
		this.probabilidadCruce = probabilidadCruce;
	}
	/**
	 * @return the probabilidadMutacion
	 */
	public double getProbabilidadMutacion() {
		return this.probabilidadMutacion;
	}
	/**
	 * @param probabilidadMutacion the probabilidadMutacion to set
	 */
	public void setProbabilidadMutacion(double probabilidadMutacion) {
		this.probabilidadMutacion = probabilidadMutacion;
	}
	/**
	 * @return the parametros
	 */
	public Set<GeneType> getParametros() {
		return this.parametros;
	}
	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(Set<GeneType> parametros) {
		this.parametros = parametros;
	}
	/**
	 * @return the selector
	 */
	public SelectorType getSelector() {
		return this.selector;
	}
	/**
	 * @param selectorType the selector to set
	 */
	public void setSelector(SelectorType selectorType) {
		this.selector = selectorType;
	}
	public void setResultados(List<ResultadoEra> resultados) {
		this.resultados = resultados;
	}
	public List<ResultadoEra> getResultados() {
		return this.resultados;
	}
	
	
}
