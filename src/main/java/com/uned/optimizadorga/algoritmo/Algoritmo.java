package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoSubject;
import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;
import com.uned.optimizadorga.elementos.Configuracion;

public class Algoritmo implements Runnable, AlgoritmoSubject, EraObserver {
	private static final Logger log = Logger.getLogger(Algoritmo.class);
	private Configuracion configuracion;

	private List<Era> listaEras;
	private List<AlgoritmoObserver> observadores;
	private long startTime;
	
	public Algoritmo(Configuracion configuracion) {
		this.configuracion = configuracion;
		observadores = new ArrayList<AlgoritmoObserver>();
		listaEras = new ArrayList<Era>();	
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		log.debug("***********************************Comienzo de la ejecucion ");
		
		for (int eraActual = 1; eraActual <= configuracion.getMaxEras(); eraActual++) {
			log.debug("\t ****************************Comienza la era "
					+ eraActual);
			Era era = new Era();
			era.setConfiguracion(configuracion);
			era.registerObserver(this);
			era.ejecutar();
			listaEras.add(era);
			ResultadoParcial resultadoEra = crearResultadoEra(startTime, era);
			this.notifyEra(resultadoEra);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		log.debug("***********************************FIN de la ejecucion "
				+ totalTime + " segundos");
		this.notifyFin();
	}

	/**
	 * @param startTime
	 * @param era
	 * @return
	 */
	private ResultadoParcial crearResultadoEra(long startTime, Era era) {
		ResultadoParcial resultadoEra = new ResultadoParcial();
		resultadoEra.setEraActual(listaEras.size());
		long timeParcial = System.currentTimeMillis();
		resultadoEra.setMejorCromosoma(era.obtenerMejor());
		resultadoEra.setTiempoEjecucion((timeParcial - startTime)/1000);
		return resultadoEra;
	}

	@Override
	public void registerObserver(AlgoritmoObserver observer) {
		this.observadores.add(observer);
	}

	@Override
	public void notifyEra(ResultadoParcial resultadoEra) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateEra(resultadoEra);
		}
	}

	@Override
	public void notifyGeneracion(ResultadoParcial resultadoGeneracion) {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateGeneracion(resultadoGeneracion);
		}
	}

	@Override
	public void notifyFin() {
		for (AlgoritmoObserver o:this.observadores) {
			o.updateFin(null);
		}
	}

	/**
	 * @return the configuracion
	 */
	public Configuracion getConfiguracion() {
		return this.configuracion;
	}

	@Override
	public void updateGeneracion(ResultadoParcial resultadoGeneracion) {
		long timeParcial = System.currentTimeMillis();
		resultadoGeneracion.setTiempoEjecucion((timeParcial - startTime)/1000);
		this.notifyGeneracion(resultadoGeneracion);
	}
	
	
}
