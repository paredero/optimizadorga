/**
 * 
 */
package com.uned.optimizadorga.algoritmo.selectores;

import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * @author fpb
 * Implementaci�n de un operador de selecci�n basado en el m�todo de la ruleta
 */
public class SelectorRuleta implements Selector {

	/* (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.selectores.Selector#evaluar(com.uned.optimizadorga.elementos.Poblacion)
	 */
	public Poblacion seleccionar(Poblacion poblacionInicial) {
		Poblacion poblacionSeleccionados = new Poblacion();
		
//		1.- Calcula la suma total de los valores de la funcion de coste para
//		todos los cromosomas de la poblaci�n
		double sumaCoste = 0;
		for (Cromosoma c:poblacionInicial.getCromosomas()) {
			sumaCoste += c.getCoste();
		}
		
//		2. Calcula la probabilidad de selecci�n para cada cromosoma
//		3. Calcula la probabilidad acumulada
		double[] probabilidadesAcumuladas = new double[poblacionInicial.getTamanio()];
		double sumaProbabilidades = 0;
		int i = 0;
		for (Cromosoma c:poblacionInicial.getCromosomas()) {
			double probabilidadElemento = c.getCoste()/sumaCoste;
			sumaProbabilidades += probabilidadElemento;
			probabilidadesAcumuladas[i] = sumaProbabilidades;
		}
		
//		4. Se gira la ruleta pop_size veces
		for (int j = 0; j <= poblacionInicial.getTamanio(); j++) {
			giroRuleta(poblacionInicial, poblacionSeleccionados, probabilidadesAcumuladas);
		}
		return poblacionSeleccionados;
	}

	/**
	 * @param poblacion
	 * @param poblacionSeleccionados
	 * @param probabilidadesAcumuladas
	 */
	private void giroRuleta(Poblacion poblacion,
			Poblacion poblacionSeleccionados, double[] probabilidadesAcumuladas) {
		// 4a. Se genera un numero aleatorio
		double numAleatorio = Math.random();

		// 4b. Selecciona el cromosoma en base al numero aleatorio
		if (numAleatorio < probabilidadesAcumuladas[0]) {
			poblacionSeleccionados.getCromosomas().add(poblacion.getCromosomas().get(0));
		} else {
			int k = 1;
			boolean parar = false;
			while (!parar && k < probabilidadesAcumuladas.length) {
				double probab = probabilidadesAcumuladas[k];
				if (numAleatorio >= probab) {
					parar = true;
				}
			}
			poblacionSeleccionados.getCromosomas().add(poblacion.getCromosomas().get(k));
		}
	}

}
