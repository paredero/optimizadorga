package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import optimizadorga.test.AlgoritmoTest;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.selectores.Selector;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class Algoritmo {
	private static final Logger log = Logger.getLogger(Algoritmo.class);
//	private List<Cromosoma> cromosomas;
	private List<Gen> genes;
	private Poblacion poblacion;
	private int tamanioPoblacion;
	private double probabilidadCruce;
	private double probabilidadMutacion;
	private Funcion funcion;
	private Selector selector;
	int maxGens;
	private Cromosoma mejorGeneracion;
	
	
	public void inicializarPoblacion() {
		this.poblacion = Poblacion.generarPoblacionInicializada(tamanioPoblacion, genes);
	}
	
	public void evaluar() {
		this.poblacion.setFuncionCoste(funcion);
		this.poblacion.calcularCostesPoblacion();
	}
	
	public Cromosoma obtenerMejor() {
		Cromosoma mejorIndividuo = poblacion.obtenerMejor();
		// TODO Repasar el uso del metodo clone
//		return mejorIndividuo.clone();		
		return mejorIndividuo;
	}

	public void ejecutarBucle() {
		for (int i = 0; i <= maxGens; i++) {
			mejorGeneracion = this.obtenerMejor();
			Poblacion nuevaPoblacion = selector.seleccionar(poblacion);
			operadorCruce(nuevaPoblacion);
//			nuevaPoblacion.addMejor(mejorGeneracion);
			operadorMutacion(nuevaPoblacion);
			nuevaPoblacion.calcularCostesPoblacion();
			operadorElitismo(nuevaPoblacion);
		}
	}
	
	private void operadorElitismo(Poblacion nuevaPoblacion) {
		Cromosoma nuevoMejor = poblacion.obtenerMejor();
		Cromosoma peor = poblacion.obtenerPeor();
		if (nuevoMejor.getCoste() >= mejorGeneracion.getCoste()) {
			mejorGeneracion = nuevoMejor;
		} else {
			Collections.replaceAll(nuevaPoblacion.getCromosomas(), peor, mejorGeneracion);
		}
	}

	private void operadorMutacion(Poblacion poblacion) {
		if (probabilidadCruce == 0.0) {
			log.warn("Probabilidad de Mutacion = 0 Posiblemente no inicializado");
		}
		for (Cromosoma c:poblacion.getCromosomas()) {
			for (Gen g:c.getGenes()) {
				double random = Math.random();
				if (random < probabilidadMutacion) {
					g.generarValorAleatorio();
				}
			}
		}
	}

	private void operadorCruce(Poblacion poblacion) {
		if (probabilidadCruce == 0.0) {
			log.warn("Probabilidad de cruce = 0 Posiblemente no inicializado");
		}
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		for (Cromosoma c:poblacion.getCromosomas()) {
			double random = Math.random();
			if (random < probabilidadCruce) {
				cromosomasSeleccionados.add(c);
			}
		}
		int i = 0;
		Iterator<Cromosoma> itCromosomas = cromosomasSeleccionados.iterator();
		Cromosoma cPar = null;
		while (itCromosomas.hasNext()) {
			Cromosoma cImpar = itCromosomas.next();
			if (i%0==0) {
//				Si es par
				cPar = cImpar;
			} else {
				cruzar(cPar, cImpar);
			}
			i++;
		}
	}
	

	private void cruzar(Cromosoma cPar, Cromosoma cImpar) {
		Random random = new Random();
		int max = cPar.getGenes().size();
		int posCruce = random.nextInt((max) + 1);
		int i = 0;
		
		while (i <= posCruce) {
			Gen genPar = cPar.getGenes().get(i);
			Gen genImpar = cPar.getGenes().get(i);
			Collections.replaceAll(cPar.getGenes(), genPar, genImpar);
			Collections.replaceAll(cImpar.getGenes(), genImpar, genPar);
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

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the funcion
	 */
	public Funcion getFuncion() {
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

	/**
	 * @return the probCruce
	 */
	public double getProbCruce() {
		return probabilidadCruce;
	}

	/**
	 * @param probCruce the probCruce to set
	 */
	public void setProbCruce(double probCruce) {
		this.probabilidadCruce = probCruce;
	}

	
}
