package com.uned.optimizadorga.algoritmo;

import java.util.List;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import com.uned.optimizadorga.algoritmo.selectores.Selector;
import com.uned.optimizadorga.algoritmo.selectores.SelectorRuleta;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class Algoritmo {
//	private List<Cromosoma> cromosomas;
	private List<Gen> genes;
	private Poblacion poblacion;
	private int tamanioPoblacion;
	private String funcion;
	private Selector selector;
	int maxGens;
	
	
	public void inicializarPoblacion() {
		this.poblacion = Poblacion.generarPoblacionInicializada(tamanioPoblacion, genes);
	}
	
	public void evaluar() {
		ExpressionBuilder eb = new ExpressionBuilder(funcion);
		eb.variables("pi");
		for (Gen variables:genes) {
			eb.variables(variables.getNombre());
		}
		Expression e = eb.build();
		this.poblacion.setFuncionCoste(e);
		this.poblacion.calcularCostesPoblacion();
	}
	
	public Cromosoma obtenerMejor() {
		Cromosoma mejorIndividuo = poblacion.obtenerMejor();
		return mejorIndividuo;		
	}

	public void ejecutarBucle() {
		for (int i = 0; i <= maxGens; i++) {
			Cromosoma mejorGeneracion = this.obtenerMejor();
			Poblacion seleccionados = selector.seleccionar(poblacion);
			seleccionados.addMejor(mejorGeneracion);
		}
	}
	
	/**
	 * @return the genes
	 */
	public List<Gen> getGenes() {
		return genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void setGenes(List<Gen> genes) {
		this.genes = genes;
	}

	/**
	 * @return the tamanioPoblacion
	 */
	public int getTamanioPoblacion() {
		return tamanioPoblacion;
	}

	/**
	 * @param tamanioPoblacion the tamanioPoblacion to set
	 */
	public void setTamanioPoblacion(int tamanioPoblacion) {
		this.tamanioPoblacion = tamanioPoblacion;
	}

	/**
	 * @return the poblacion
	 */
	public Poblacion getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	
	/**
	 * @return the maxGens
	 */
	public int getMaxGens() {
		return maxGens;
	}

	/**
	 * @param maxGens the maxGens to set
	 */
	public void setMaxGens(int maxGens) {
		this.maxGens = maxGens;
	}

	/**
	 * @return the selector
	 */
	public Selector getSelector() {
		return selector;
	}

	/**
	 * @param selector the selector to set
	 */
	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	
}
