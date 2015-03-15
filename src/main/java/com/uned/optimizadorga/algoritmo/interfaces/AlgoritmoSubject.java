package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;

public interface AlgoritmoSubject {
	public void registerObserver(AlgoritmoObserver observer);
	public void notifyEra(ResultadoParcial resultadoEra);
	public void notifyGeneracion(ResultadoParcial resultadoGeneracion);
	public void notifyFin();
}
