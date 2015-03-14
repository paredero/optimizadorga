/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fpb
 *
 */
public class Cromosoma {
	private double coste;
	private List<Gen> genes;

	public static Cromosoma generarCromosomaAleatorio(List<Gen> parametros) {
		Cromosoma cromosoma = new Cromosoma();
		for (Gen genParametro : parametros) {
			Gen genResultado = new Gen(genParametro.getNombre(),
					genParametro.getMinimo(),genParametro.getMaximo(),
					genParametro.getPrecision());
			genResultado.generarValorAleatorio();
			cromosoma.getGenes().add(genResultado);
		}
		return cromosoma;
	}

	public Cromosoma(Cromosoma cromosomaOrigen) {
		super();
		this.coste = cromosomaOrigen.getCoste();
		genes = new ArrayList<Gen>();
		for (Gen g:cromosomaOrigen.getGenes()) {
			genes.add(new Gen(g));
		}
	}
	/**
	 * @return the genes
	 */
	public List<Gen> getGenes() {
		return genes;
	}

	/**
	 * @param genes
	 *            the genes to set
	 */
	public void setGenes(List<Gen> genes) {
		this.genes = genes;
	}

	/**
	 * @return the coste
	 */
	public double getCoste() {
		return coste;
	}

	/**
	 * @param coste
	 *            the coste to set
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\t\t" + this.hashCode() +" [coste=" + coste + ", genes=" + genes + "]";
	}

	public Cromosoma() {
		super();
		this.genes = new ArrayList<Gen>();
	}

	public void calcularCoste(Funcion funcionCoste) {
		this.coste = funcionCoste.evaluate(genes);
	}

}
