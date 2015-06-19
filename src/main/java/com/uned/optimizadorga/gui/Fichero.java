package com.uned.optimizadorga.gui;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialEra;
import com.uned.optimizadorga.elementos.TipoGen;

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
	private Set<TipoGen> parametros;
	private String selector;
	private boolean elitismo;
	private List<ResultadoParcialEra> resultados;
	
	
	
	/**
	 * @return the elitismo
	 */
	public boolean isElitismo() {
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
	public Set<TipoGen> getParametros() {
		return this.parametros;
	}
	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(Set<TipoGen> parametros) {
		this.parametros = parametros;
	}
	/**
	 * @return the selector
	 */
	public String getSelector() {
		return this.selector;
	}
	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public void setResultados(List<ResultadoParcialEra> resultados) {
		this.resultados = resultados;
	}
	public List<ResultadoParcialEra> getResultados() {
		return this.resultados;
	}
	
	
}
