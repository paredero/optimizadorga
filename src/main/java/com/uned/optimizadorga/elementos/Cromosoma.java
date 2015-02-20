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
		for (Gen genParametro:genesParametro) {
			Gen genResultado = new Gen(genParametro.getNombre(),
					genParametro.getMaximo(), genParametro.getMinimo(),
					genParametro.getPrecision());
			genResultado.generarValorAleatorio();
			cromosoma.getGenes().add(genResultado);
		}
		return cromosoma;
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
	 * @return the coste
	 */
	public double getCoste() {
		return coste;
	}
	/**
	 * @param coste the coste to set
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\t Cromosoma [coste=" + coste + ", \n\t genes=" + genes + "]";
	}
	public Cromosoma() {
		super();
		this.genes = new ArrayList<Gen>();
	}
	
	public void calcularCoste(Expression funcionCoste) {
		for (Gen gen:genes) {
			funcionCoste.setVariable(gen.getNombre(), gen.getValor());
		}
		funcionCoste.setVariable("pi", Math.PI);
		this.coste = funcionCoste.evaluate();
	}

}
