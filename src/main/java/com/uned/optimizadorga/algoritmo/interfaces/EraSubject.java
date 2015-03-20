package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Generacion;

public interface EraSubject {
	public void registerObserver(EraObserver observer);
	public void notifyGeneracion(Generacion resultadoEra);
}
