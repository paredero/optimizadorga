/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.ArrayList;
import java.util.List;

import net.objecthunter.exp4j.Expression;

/**
 * @author fpb
 *
 */
public class Cromosoma {
	private double coste;
	private List<Gen> genes;

	public static Cromosoma generarCromosomaAleatorio(List<Gen> genesParametro) {
		Cromosoma cromosoma = new Cromosoma();
		for (Gen genParametro : genesParametro) {
			Gen genResultado = new Gen(genParametro.getNombre(),
					genParametro.getMaximo(), genParametro.getMinimo(),
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
		return this.hashCode() +" [coste=" + coste + ", genes=" + genes + "] \n";
	}

	public Cromosoma() {
		super();
		this.genes = new ArrayList<Gen>();
	}

	// TODO A sustituir
	public void calcularCoste(Expression funcionCoste) {
		System.out
				.println("WARNING Metodo Cromosoma.calcularCoste feo, sustituir por el que recibe una Funcion");
		for (Gen gen : genes) {
			funcionCoste.setVariable(gen.getNombre(), gen.getValor());
		}
		funcionCoste.setVariable("pi", Math.PI);
		this.coste = funcionCoste.evaluate();
	}

	public void calcularCoste(Funcion funcionCoste) {
		this.coste = funcionCoste.evaluate(genes);
	}

}
