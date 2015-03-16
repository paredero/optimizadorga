package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;

public interface EraSubject {
	public void registerObserver(EraObserver observer);
	public void notifyGeneracion(ResultadoParcialGeneracion resultadoEra);
}
