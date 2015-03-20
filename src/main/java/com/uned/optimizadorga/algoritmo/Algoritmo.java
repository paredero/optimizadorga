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

	private List<Era> listaEras;
	private List<AlgoritmoObserver> observadores;
	private long startTime;
	private int eraActual;
	public Algoritmo(Configuracion configuracion) {
		this.configuracion = configuracion;
		observadores = new ArrayList<AlgoritmoObserver>();
		listaEras = new ArrayList<Era>();	
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		log.debug("***********************************Comienzo de la ejecucion ");
		
		for (eraActual = 1; eraActual <= configuracion.getMaxEras(); eraActual++) {
			log.debug("\t ****************************Comienza la era "
					+ eraActual);
			Era era = new Era();
			era.setConfiguracion(configuracion);
			era.registerObserver(this);
			era.ejecutar();
			listaEras.add(era);
			this.notifyEra(era);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		log.debug("***********************************FIN de la ejecucion "
				+ totalTime + " segundos");
		this.notifyFin();
	}

	

	@Override
	public void registerObserver(AlgoritmoObserver observer) {
		this.observadores.add(observer);
	}

	@Override
	public void notifyEra(Era eraProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateEra(eraProcesada);
		}
	}

	@Override
	public void notifyGeneracion(Generacion generacionProcesada) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateGeneracion(generacionProcesada);
		}
	}

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

	@Override
	public void updateGeneracion(Generacion generacionProcesada) {
		this.notifyGeneracion(generacionProcesada);
	}
	
	
}
