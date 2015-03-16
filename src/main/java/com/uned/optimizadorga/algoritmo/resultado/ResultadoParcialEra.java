package com.uned.optimizadorga.algoritmo.resultado;

import com.uned.optimizadorga.elementos.Gen;

public class ResultadoParcialEra extends ResultadoParcial {

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("\tMejor Cromosoma obtenido hasta el momento: ");
		for (Gen g:this.getMejorCromosoma().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\n\tCoste: ").append(this.getMejorCromosoma().getCoste());
		return sb.toString();
	}

}
