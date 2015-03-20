package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;

public interface AlgoritmoSubject {
	public void registerObserver(AlgoritmoObserver observer);
	public void notifyEra(Era eraProcesada);
	public void notifyGeneracion(Generacion generacionProcesada);
	public void notifyFin();
}
