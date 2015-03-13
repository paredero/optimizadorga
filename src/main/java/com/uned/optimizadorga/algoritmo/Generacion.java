package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class Generacion {
	private static final Logger log = Logger.getLogger(Generacion.class);
	private Poblacion poblacionInical;
	private Poblacion nuevaPoblacion;
	private Configuracion configuracion;

	public Generacion(Poblacion poblacion, Configuracion configuracion) {
		this.poblacionInical = new Poblacion(poblacion);
		this.configuracion = configuracion;
	}

	public void ejecutar() {
		nuevaPoblacion = this.seleccionar();
		log.debug("Poblacion seleccionada " + nuevaPoblacion);
		operadorCruce(nuevaPoblacion);
		operadorMutacion(nuevaPoblacion);
		nuevaPoblacion.calcularCostesPoblacion();
		operadorElitismo(nuevaPoblacion);
	}
	
	
	
	/**
	 * @return the nuevaPoblacion
	 */
	public Poblacion getNuevaPoblacion() {
		return this.nuevaPoblacion;
	}

	private Poblacion seleccionar() {
		Poblacion resultado = configuracion.getSelector().seleccionar(poblacionInical);
		return resultado;
	}

	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	private void operadorCruce(Poblacion poblacion) {
		if (configuracion.getProbabilidadCruce() == 0.0) {
			log.warn("Probabilidad de cruce = 0 Posiblemente no inicializado");
		}
		log.debug("Ejecuta operador de cruce PC " + configuracion.getProbabilidadCruce());
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		for (Cromosoma c:poblacion.getCromosomas()) {
			double random = Math.random();
			log.debug("Gira la ruleta......"+random);
			if (random < configuracion.getProbabilidadCruce()) {
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
	
	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	
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
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	
	private void operadorMutacion(Poblacion poblacion) {
		if (configuracion.getProbabilidadMutacion() == 0.0) {
			log.warn("Probabilidad de Mutacion = 0 Posiblemente no inicializado");
		}
		log.debug("Operador mutacion con probabilidad " + configuracion.getProbabilidadMutacion());
		for (Cromosoma c:poblacion.getCromosomas()) {
			log.debug("El cromosoma ANTES DE MUTAR" + c);
			for (Gen g:c.getGenes()) {
				double random = Math.random();
				log.debug("Ruletaa " + random);
				if (random < configuracion.getProbabilidadMutacion()) {
					log.debug("TOCÓ para " + g.getNombre());
					g.generarValorAleatorio();
				}
			}
			log.debug("El cromosoma DESPUES DE MUTAR" + c);
		}
	}

	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	private void operadorElitismo(Poblacion nuevaPoblacion) {
		Cromosoma nuevoMejor = nuevaPoblacion.obtenerMejor();
		log.debug("El mejor de la nueva generacion " + nuevoMejor);
		Cromosoma peor = nuevaPoblacion.obtenerPeor();
		log.debug("El peor de la nueva Generacion" + peor);
		Cromosoma mejorPoblacionInicial = poblacionInical.obtenerMejor();
		if (nuevoMejor.getCoste() >= mejorPoblacionInicial.getCoste()) {
			log.debug("El nuevo mejor MEJORA");
			mejorPoblacionInicial = nuevoMejor;
		} else {
			log.debug("Sustituye el mejor anterior por el peor");
			Collections.replaceAll(nuevaPoblacion.getCromosomas(), peor, mejorPoblacionInicial);
		}
	}
}
