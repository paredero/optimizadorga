package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class Generacion {
	private Poblacion poblacionInicial;
	private Poblacion nuevaPoblacion;
	private Configuracion configuracion;

	public Generacion(Poblacion poblacion, Configuracion configuracion) {
		this.poblacionInicial = poblacion;
		this.configuracion = configuracion;
	}

	public void ejecutar() throws Exception {
		nuevaPoblacion = this.seleccionar();
		operadorCruce(nuevaPoblacion);
		operadorMutacion(nuevaPoblacion);
		if (this.configuracion.getElitismo()) {
			operadorElitismo(nuevaPoblacion);
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
		return resultado;
	}

	/**
	 * @param nuevaPoblacion
	 */
	private void operadorCruce(Poblacion poblacion) {
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		for (Cromosoma c:poblacion.getCromosomas()) {
			double random = Math.random();
			if (random < configuracion.getProbabilidadCruce()) {
				cromosomasSeleccionados.add(c);
			}
		}
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
		try {
			poblacion.calcularCostesPoblacion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param nuevaPoblacion
	 */
	
	private void cruzar(Cromosoma cPar, Cromosoma cImpar) {
		Random random = new Random();
		int max = cPar.getGenes().size();
		int posCruce = random.nextInt(max);
		int i = posCruce;
		
		while (i < max) {
			Gen genPar = cPar.getGenes().get(i);
			Gen genImpar = cImpar.getGenes().get(i); 
			Collections.replaceAll(cPar.getGenes(), genPar, genImpar);
			Collections.replaceAll(cImpar.getGenes(), genImpar, genPar);
			i++;
		}
//		log.debug("Resultado del cruce " + cPar + cImpar);
	}

	/**
	 * @param nuevaPoblacion
	 * @throws Exception 
	 */	
	private void operadorMutacion(Poblacion poblacion) throws Exception {
		for (Cromosoma c:poblacion.getCromosomas()) {
			for (Gen g:c.getGenes()) {
				double random = Math.random();
				if (random < configuracion.getProbabilidadMutacion()) {
					g.generarValorAleatorio();
				}
			}
			c.calcularCoste(configuracion.getFuncionCoste());
		}
	}

	/**
	 * @param nuevaPoblacion
	 */
	private void operadorElitismo(Poblacion nuevaPoblacion) {
		Cromosoma nuevoMejor = nuevaPoblacion.obtenerMejor();		
		Cromosoma mejorPoblacionInicial = poblacionInicial.obtenerMejor();
		if (nuevoMejor.getCoste() < mejorPoblacionInicial.getCoste()) {
			Cromosoma peor = nuevaPoblacion.obtenerPeor();
			nuevaPoblacion.sustituirCromosoma(peor, mejorPoblacionInicial);
		}
	}
}
