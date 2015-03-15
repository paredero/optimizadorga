package com.uned.optimizadorga.resultados;

import com.uned.optimizadorga.elementos.Cromosoma;

public class ResultadoEra {

	private int generacionActual;
	private Cromosoma mejorCromosoma;

	public void setGeneracionActual(int i) {
		this.generacionActual = i;
	}

	public void setMejorCromosoma(Cromosoma mejorCromosoma) {
		this.mejorCromosoma = mejorCromosoma;
	}

}
