package com.uned.optimizadorga.algoritmo.interfaces;

import com.uned.optimizadorga.algoritmo.Generacion;

/**
 * Interfaz para implementar el patron observer para registrar el progreso del
 * calculo de una era. Proporciona metodos para que la era informe del progreso de su ejecucion
 * 
 * @author Francisco Javier García Paredero
 *
 */
public interface EraSubject {
	/**
	 * Los elementos que desean recibir actualizaciones sobre el progreso del
	 * calculo de una era deben registrarse como observadores
	 * En este caso se trata del elemento algoritmo
	 * @param observer
	 */
	public void registerObserver(EraObserver observer);
	
	/**
	 * Cuando finaliza una generacion, la era notifica a la clase algoritmo (Que es
	 * el observer) este hecho para que pase la informacion a las clases del GUI
	 * () que desean mostrar el progreso
	 * 
	 * @param resultadoEra
	 */
	public void notifyGeneracion(Generacion resultadoEra);
}
