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
	private Poblacion poblacionInicial;
	private Poblacion nuevaPoblacion;
	private Configuracion configuracion;

	public Generacion(Poblacion poblacion, Configuracion configuracion) {
		this.poblacionInicial = poblacion;
		this.configuracion = configuracion;
	}

	public void ejecutar() throws Exception {
		log.info("INICIO BUCLE GENERACION");
		nuevaPoblacion = this.seleccionar();
//		log.debug("Poblacion seleccionada " + nuevaPoblacion);
		operadorCruce(nuevaPoblacion);
		log.info("TRAS CRUCE");
		log.info("Mejor resultado de la poblacion inicial " + poblacionInicial.obtenerMejor());
		log.info("Mejor resultado de la poblacion cruzada" + nuevaPoblacion.obtenerMejor());
		
		operadorMutacion(nuevaPoblacion);
		log.info("TRAS MUTACION ");
		log.info("Mejor resultado de la poblacion inicial " + poblacionInicial.obtenerMejor());
		log.info("Mejor resultado de la poblacion mutada" + nuevaPoblacion.obtenerMejor());
		if (this.configuracion.getElitismo()) {
			operadorElitismo(nuevaPoblacion);
			log.info("TRAS ELITISMO ");
			log.info("Mejor resultado de la poblacion inicial " + poblacionInicial.obtenerMejor());
			log.info("Mejor resultado de la poblacion evolucionada" + nuevaPoblacion.obtenerMejor());
		}
		
	}
	
	
	
	/**
	 * @return the nuevaPoblacion
	 */
	public Poblacion getNuevaPoblacion() {
		return this.nuevaPoblacion;
	}

	
	/**
	 * @return the poblacionInical
	 */
	public Poblacion getPoblacionInicial() {
		return poblacionInicial;
	}

	/**
	 * @return the configuracion
	 */
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	/**
	 * Dada una poblacion inicial, genera una nueva poblacion aplicando el selector
	 * @return
	 */
	private Poblacion seleccionar() {
		Poblacion resultado = configuracion.getSelector().seleccionar(poblacionInicial);
		resultado = Poblacion.copiarPoblacion(resultado);
		log.info("Mejor resultado de la poblacion inicial " + poblacionInicial.obtenerMejor());
		log.info("Mejor resultado de la seleccion: " + resultado.obtenerMejor());		
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
//		log.debug("Ejecuta operador de cruce PC " + configuracion.getProbabilidadCruce());
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		//TODO Traza
		Cromosoma mejor = poblacion.obtenerMejor();
		for (Cromosoma c:poblacion.getCromosomas()) {
			double random = Math.random();
//			log.debug("Gira la ruleta......"+random);
			if (random < configuracion.getProbabilidadCruce()) {
				cromosomasSeleccionados.add(c);
				if (c == mejor) {
					log.info("El mejor cromosoma seleccionado para cruce");
				}
//				log.debug(".......TOCO!  " + c);
			}
		}
//		log.debug("Cromosomas seleccionados para el cruce: " + cromosomasSeleccionados);
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
			if (cImpar == mejor) {
				log.info("El mejor cromosoma tras cruzar sin evaluar " + mejor);
			} else if (cPar == mejor) {
				log.info("El mejor cromosoma tras cruzar sin evaluar " + mejor);
			}
		}
		try {
			poblacion.calcularCostesPoblacion();
			log.info("El mejor cromosoma tras cruzar y evaluar " +mejor);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		log.debug("Poblacion tras el cruce " + poblacion);
	}
	
	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	
	private void cruzar(Cromosoma cPar, Cromosoma cImpar) {
//		log.debug("Cruce entre el cromosoma " + cPar + " y el cromosoma " + cImpar);
		Random random = new Random();
		int max = cPar.getGenes().size();
		int posCruce = random.nextInt(max);
//		log.debug("A partir de la posicion " + posCruce);
		int i = posCruce;
		
		while (i < max) {
			Gen genPar = cPar.getGenes().get(i);
			Gen genImpar = cImpar.getGenes().get(i);
//			log.debug("En " + cPar.hashCode() + " sustituye " + genPar + "Por " + genImpar); 
			Collections.replaceAll(cPar.getGenes(), genPar, genImpar);
//			log.debug(cPar);
			Collections.replaceAll(cImpar.getGenes(), genImpar, genPar);
//			log.debug("En " + cImpar.hashCode() + " sustituye " + genImpar + "Por " + genPar);
//			log.debug(cImpar);
			i++;
		}
//		log.debug("Resultado del cruce " + cPar + cImpar);
	}

	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 * @throws Exception 
	 */
	
	private void operadorMutacion(Poblacion poblacion) throws Exception {
		if (configuracion.getProbabilidadMutacion() == 0.0) {
			log.warn("Probabilidad de Mutacion = 0 Posiblemente no inicializado");
		}
		log.info("Mejor cromosoma antes de mutar " + poblacion.obtenerMejor());
		for (Cromosoma c:poblacion.getCromosomas()) {
//			log.debug("Operador mutacion con probabilidad " + configuracion.getProbabilidadMutacion());
//			log.debug("El cromosoma ANTES DE MUTAR" + c);
			boolean mejorCromosoma  =false;
			if (c.getCoste() == poblacion.obtenerMejor().getCoste()) {
				mejorCromosoma = true;
			}
			
			for (Gen g:c.getGenes()) {
				double random = Math.random();
				if (random < configuracion.getProbabilidadMutacion()) {
					if (mejorCromosoma) {
						log.info("El mejor cromosoma va a mutar ");
					}
//					log.debug("TOCÓ para " + g);
					g.generarValorAleatorio();
					
//					log.debug("Pasa a valer" + g);
				} else {
//					log.debug("NO TOCA para " + g);
					if (mejorCromosoma) {
						log.info("El mejor cromosoma no muta");
					}
				}
			}
			c.calcularCoste(configuracion.getFuncionCoste());
			if (mejorCromosoma) {
				log.info("El mejor cromosoma tras mutar " + c);
			}
//			log.debug("El cromosoma DESPUES DE MUTAR" + c);
		}
	}

	/**
	 * TODO refactor with Strategy pattern
	 * @param nuevaPoblacion
	 */
	private void operadorElitismo(Poblacion nuevaPoblacion) {
		Cromosoma nuevoMejor = nuevaPoblacion.obtenerMejor();
//		log.debug("El mejor de la nueva generacion " + nuevoMejor);		
		Cromosoma mejorPoblacionInicial = poblacionInicial.obtenerMejor();
//		log.debug("El peor de la Generacion inicial " + mejorPoblacionInicial);
		/*
		if (nuevoMejor.getCoste() >= mejorPoblacionInicial.getCoste()) {
			log.debug("El nuevo mejor MEJORA");
			mejorPoblacionInicial = nuevoMejor;
		} else {
			log.debug("Sustituye el mejor anterior por el peor");
			nuevaPoblacion.sustituirCromosoma(peor, mejorPoblacionInicial);
		}
		*/
		if (nuevoMejor.getCoste() < mejorPoblacionInicial.getCoste()) {
//			log.debug("Sustituye el mejor anterior por el peor");
			Cromosoma peor = nuevaPoblacion.obtenerPeor();
//			log.debug("El peor de la nueva Generacion" + peor);
			nuevaPoblacion.sustituirCromosoma(peor, mejorPoblacionInicial);
			log.error("SI SUSTITUYE "+mejorPoblacionInicial + " POR " + nuevoMejor);
		} else {
			log.error("NO SUSTITUYE "+mejorPoblacionInicial + " POR " + nuevoMejor);
		}
	}
}
