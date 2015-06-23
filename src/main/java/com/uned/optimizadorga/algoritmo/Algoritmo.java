package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject;
import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.elementos.Configuracion;

public class Algoritmo implements Runnable, AlgoritmoSubject, EraObserver {
//	private static final Logger log = Logger.getLogger(Algoritmo.class);
	
	private Configuracion configuracion;
//	private List<Era> listaEras; //Almacena las eras que se van calculando
	private List<AlgoritmoObserver> observadores;
	private int eraActual;
	
	public Algoritmo(Configuracion configuracion) {
		this.configuracion = configuracion;
		observadores = new ArrayList<AlgoritmoObserver>();
//		listaEras = new ArrayList<Era>(configuracion.getMaxEras());	
	}

	@Override
	public void run() {	
		try {
			for (eraActual = 1; eraActual <= configuracion.getMaxEras(); eraActual++) {
				Era era = new Era();
				era.setConfiguracion(configuracion);
				//			El algoritmo observara la era cuando se ejecute para poder informar de su progreso
				era.registerObserver(this);
				era.ejecutar();
//				listaEras.add(era);
				this.notifyFinCalculoEra(era);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.notifyError(e);
		}
		this.notifyFinEjecucion();
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
	public void notifyFinCalculoEra(Era eraProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateFinCalculoEra(eraProcesada);
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
	public void notifyFinCalculoGeneracion(Generacion generacionProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateFinCalculoGeneracion(generacionProcesada);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject#notifyFin()
	 */
	@Override
	public void notifyFinEjecucion() {
//		Al final debe enviar todas las eras, las cuales ya contienen todas las generaciones
		for (AlgoritmoObserver o:this.observadores) {
			o.updateFin();
		}
	}
	
	@Override
	public void notifyError(Exception e) {
//		Al final debe enviar todas las eras, las cuales ya contienen todas las generaciones
		for (AlgoritmoObserver o:this.observadores) {
			o.updateError(e);
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
		this.notifyFinCalculoGeneracion(generacionProcesada);
	}
	
	
}
