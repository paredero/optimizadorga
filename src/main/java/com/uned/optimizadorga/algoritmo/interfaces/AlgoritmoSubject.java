package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialEra;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;

public interface AlgoritmoSubject {
	public void registerObserver(AlgoritmoObserver observer);
	public void notifyEra(ResultadoParcialEra resultadoEra);
	public void notifyGeneracion(ResultadoParcialGeneracion resultadoGeneracion);
	public void notifyFin();
}
