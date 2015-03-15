/**
 * 
 */
package com.uned.optimizadorga.algoritmo.comparadores;

import java.util.Comparator;

import com.uned.optimizadorga.elementos.Cromosoma;

/**
 * @author fpb
 *
 */
public class ComparadorMejorCoste implements Comparator<Cromosoma> {

	@Override
	public int compare(Cromosoma o1, Cromosoma o2) {
		if (o1.getCoste() > o2.getCoste()) {
			return 11;
		} else if (o1.getCoste() < o2.getCoste()) {
			return -1;
		} else {
			return 0;
		}
	}

}
