/**
 * 
 */
package com.uned.optimizadorga.algoritmo.selectores;


import org.apache.log4j.Logger;

import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * @author fpb Implementación de un operador de selección basado en el método de
 *         la ruleta
 */
public class SelectorRuleta implements Selector {

	private static final Logger log = Logger.getLogger(SelectorRuleta.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.selectores.Selector#evaluar(com.uned
	 * .optimizadorga.elementos.Poblacion)
	 */
	@Override
	public Poblacion seleccionar(Poblacion poblacionInicial) {
		// Aplica el operador de selección mediante el método de la ruleta
		Poblacion poblacionSeleccionados = Poblacion.copiarPoblacionVacia(poblacionInicial);
		

		// 1.- Calcula la suma total de los valores de la funcion de coste para
		// todos los cromosomas de la población
		double sumaCoste = 0;
		for (Cromosoma c : poblacionInicial.getCromosomas()) {
			sumaCoste += c.getCoste();
		}

		// 2. Calcula la probabilidad de selección para cada cromosoma
		// 3. Calcula la probabilidad acumulada
		double[] probabilidadesAcumuladas = new double[poblacionInicial
				.getTamanio()];
		double sumaProbabilidades = 0;
		int i = 0;
		for (Cromosoma c : poblacionInicial.getCromosomas()) {
			double probabilidadElemento = c.getCoste() / sumaCoste;
			sumaProbabilidades += probabilidadElemento;
			probabilidadesAcumuladas[i] = sumaProbabilidades;
			log.debug("Prob Accum "+c.hashCode()+": " + probabilidadesAcumuladas[i]);
			i++;
		}
		

		// 4. Se gira la ruleta pop_size veces
		for (int j = 0; j < poblacionInicial.getTamanio(); j++) {
			giroRuleta(poblacionInicial, poblacionSeleccionados,
					probabilidadesAcumuladas);
		}
		return poblacionSeleccionados;
	}

	/**
	 * Realiza un giro de la ruleta y añade el elemento de poblacion
	 * seleccionado a poblacionSeleccionados
	 * 
	 * @param poblacion
	 * @param poblacionSeleccionados
	 * @param probabilidadesAcumuladas
	 */
	private void giroRuleta(Poblacion poblacion,
			Poblacion poblacionSeleccionados, double[] probabilidadesAcumuladas) {
		// 4a. Se genera un numero aleatorio
		double numAleatorio = Math.random();
		log.debug("Giro de la ruleta:......." + numAleatorio);
		// 4b. Selecciona el cromosoma en base al numero aleatorio
		if (numAleatorio < probabilidadesAcumuladas[0]) {
			log.debug("Seleccionado "+poblacion.getCromosomas().get(0));
			poblacionSeleccionados.getCromosomas().add(
					poblacion.getCromosomas().get(0));
		} else {
			int k = 1;
			try {
				boolean parar = false;
				while (!parar && k < probabilidadesAcumuladas.length) {
					double probab = probabilidadesAcumuladas[k];
					if (numAleatorio <= probab) {
						parar = true;
					} else {
						k++;
					}
				}
				poblacionSeleccionados.getCromosomas().add(
						poblacion.getCromosomas().get(k));
				log.debug("Seleccionado "+poblacion.getCromosomas().get(k));
			} catch (RuntimeException e) {
				log.error(e.getStackTrace());
				e.printStackTrace();
				throw e;
			}
			
		}
	}

}
