package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject;
import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.elementos.Configuracion;

public class Algoritmo implements Runnable, AlgoritmoSubject, EraObserver {
	private static final Logger log = Logger.getLogger(Algoritmo.class);
	
	private Configuracion configuracion;
	private List<Era> listaEras; //Almacena las eras que se van calculando
	private List<AlgoritmoObserver> observadores;
	private int eraActual;
	
	public Algoritmo(Configuracion configuracion) {
		this.configuracion = configuracion;
		observadores = new ArrayList<AlgoritmoObserver>();
		listaEras = new ArrayList<Era>(configuracion.getMaxEras());	
	}

	@Override
	public void run() {		
		for (eraActual = 1; eraActual <= configuracion.getMaxEras(); eraActual++) {
			Era era = new Era();
			era.setConfiguracion(configuracion);
			era.registerObserver(this);
			era.ejecutar();
			listaEras.add(era);
			this.notifyEra(era);
		}
		this.notifyFin();
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject#registerObserver
	 * (com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver)
	 */
	@Override
	public void registerObserver(AlgoritmoObserver observer) {
		this.observadores.add(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject#notifyEra
	 * (com.uned.optimizadorga.algoritmo.Era)
	 */
	@Override
	public void notifyEra(Era eraProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateEra(eraProcesada);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject#notifyGeneracion
	 * (com.uned.optimizadorga.algoritmo.Generacion)
	 */
	@Override
	public void notifyGeneracion(Generacion generacionProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateGeneracion(generacionProcesada);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject#notifyFin()
	 */
	@Override
	public void notifyFin() {
//		Al final debe enviar todas las eras, las cuales ya contienen todas las generaciones
		for (AlgoritmoObserver o:this.observadores) {
			o.updateFin(listaEras);
		}
	}

	/**
	 * @return the configuracion
	 */
	public Configuracion getConfiguracion() {
		return this.configuracion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.EraObserver#updateGeneracion
	 * (com.uned.optimizadorga.algoritmo.Generacion)
	 */
	@Override
	public void updateGeneracion(Generacion generacionProcesada) {
		// Cuando se recibe el calculo de una generación lo único que hace el
		// algoritmo es pasar el resultado de este calculo hacia "arriba", esto
		// es, al interfaz
		this.notifyGeneracion(generacionProcesada);
	}
	
	
}
