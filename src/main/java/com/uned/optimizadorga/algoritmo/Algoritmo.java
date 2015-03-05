package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.selectores.Selector;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class Algoritmo implements Runnable {
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
	int generacionActual;
	private Cromosoma mejorGeneracion;
	
	public void inicializarPoblacion() {
		this.poblacion = Poblacion.generarPoblacionInicializada(tamanioPoblacion, genes);
	}
	
	public void evaluar() {
		log.debug("Se evalua la poblacion " + this.poblacion);
		this.poblacion.setFuncionCoste(this.funcion);
		this.poblacion.calcularCostesPoblacion();
		log.debug("Nuevo coste " + this.poblacion);
	}
	
	public Cromosoma obtenerMejor() {
		Cromosoma mejorIndividuo = poblacion.obtenerMejor();	
		return new Cromosoma(mejorIndividuo);
	}

	public void ejecutarBucle() {
		while (!Thread.currentThread().isInterrupted() && generacionActual < maxGens) {
			generacionActual++;
			log.debug("******************************************** Generacion numero: " + (generacionActual));
			mejorGeneracion = this.obtenerMejor();
			log.debug("Poblacion inicial: " + this.poblacion);
			log.debug("El mejor individuo: " + this.mejorGeneracion);
			Poblacion nuevaPoblacion = this.seleccionar();
			log.debug("Poblacion seleccionada " + nuevaPoblacion);
			operadorCruce(nuevaPoblacion);
//			nuevaPoblacion.addMejor(mejorGeneracion);
			operadorMutacion(nuevaPoblacion);
			nuevaPoblacion.calcularCostesPoblacion();
			operadorElitismo(nuevaPoblacion);
		}
	}
	
	private Poblacion seleccionar() {
		Poblacion resultado = selector.seleccionar(poblacion);
		return resultado;
	}

	private void operadorElitismo(Poblacion nuevaPoblacion) {
		Cromosoma nuevoMejor = poblacion.obtenerMejor();
		log.debug("El mejor de la nueva generacion " + nuevoMejor);
		Cromosoma peor = poblacion.obtenerPeor();
		log.debug("El peor de la nueva Generacion" + peor);
		if (nuevoMejor.getCoste() >= mejorGeneracion.getCoste()) {
			log.debug("El nuevo mejor MEJORA");
			mejorGeneracion = nuevoMejor;
		} else {
			log.debug("Sustituye el mejor anterior por el peor");
			Collections.replaceAll(nuevaPoblacion.getCromosomas(), peor, mejorGeneracion);
		}
	}

	private void operadorMutacion(Poblacion poblacion) {
		if (probabilidadMutacion == 0.0) {
			log.warn("Probabilidad de Mutacion = 0 Posiblemente no inicializado");
		}
		log.debug("Operador mutacion con probabilidad " + probabilidadMutacion);
		for (Cromosoma c:poblacion.getCromosomas()) {
			log.debug("El cromosoma ANTES DE MUTAR" + c);
			for (Gen g:c.getGenes()) {
				double random = Math.random();
				log.debug("Ruletaa " + random);
				if (random < probabilidadMutacion) {
					log.debug("TOCÓ para " + g.getNombre());
					g.generarValorAleatorio();
				}
			}
			log.debug("El cromosoma DESPUES DE MUTAR" + c);
		}
	}

	private void operadorCruce(Poblacion poblacion) {
		if (probabilidadCruce == 0.0) {
			log.warn("Probabilidad de cruce = 0 Posiblemente no inicializado");
		}
		log.debug("Ejecuta operador de cruce PC " + probabilidadCruce);
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		for (Cromosoma c:poblacion.getCromosomas()) {
			double random = Math.random();
			log.debug("Gira la ruleta......"+random);
			if (random < probabilidadCruce) {
				cromosomasSeleccionados.add(c);
				log.debug(".......TOCO!  " + c);
			}
		}
		log.debug("Cromosomas seleccionados para el cruce: " + cromosomasSeleccionados);
		int i = 0;
		Iterator<Cromosoma> itCromosomas = cromosomasSeleccionados.iterator();
		Cromosoma cPar = null;
		while (itCromosomas.hasNext()) {
			Cromosoma cImpar = itCromosomas.next();
			if (i%2==0) {
//				Si es par
				cPar = cImpar;
			} else {
				cruzar(cPar, cImpar);
			}
			i++;
		}
		log.debug("Poblacion tras el cruce " + poblacion);
	}
	

	private void cruzar(Cromosoma cPar, Cromosoma cImpar) {
		log.debug("Cruce entre el cromosoma " + cPar + " y el cromosoma " + cImpar);
		Random random = new Random();
		int max = cPar.getGenes().size();
		int posCruce = random.nextInt(max);
		log.debug("A partir de la posicion " + posCruce);
		int i = posCruce;
		
		while (i < max) {
			Gen genPar = cPar.getGenes().get(i);
			Gen genImpar = cImpar.getGenes().get(i);
			log.debug("En " + cPar.hashCode() + " sustituye " + genPar + "Por " + genImpar); 
			Collections.replaceAll(cPar.getGenes(), genPar, genImpar);
			log.debug(cPar);
			Collections.replaceAll(cImpar.getGenes(), genImpar, genPar);
			log.debug("En " + cImpar.hashCode() + " sustituye " + genImpar + "Por " + genPar);
			log.debug(cImpar);
			i++;
		}
		log.debug("Resultado del cruce " + cPar + cImpar);
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
	 * @return the probabilidadCruce
	 */
	public double getProbabilidadCruce() {
		return probabilidadCruce;
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
		return probabilidadMutacion;
	}

	/**
	 * @param probabilidadMutacion the probabilidadMutacion to set
	 */
	public void setProbabilidadMutacion(double probabilidadMutacion) {
		this.probabilidadMutacion = probabilidadMutacion;
	}

	
	/**
	 * @return the generacionActual
	 */
	public int getGeneracionActual() {
		return generacionActual;
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		log.debug("***********************************Comienzo de la ejecucion ");
		if (!Thread.currentThread().isInterrupted()) {
			this.inicializarPoblacion();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.evaluar();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.obtenerMejor();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.ejecutarBucle();
		}
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		log.debug("***********************************FIN de la ejecucion "
				+ totalTime + " segundos");
		
	}

	
}
