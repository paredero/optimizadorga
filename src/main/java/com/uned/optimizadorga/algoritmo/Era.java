package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.algoritmo.interfaces.EraSubject;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcialGeneracion;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

public class Era implements EraSubject {
	private static final Logger log = Logger.getLogger(Era.class);
	private Configuracion configuracion;
	private Poblacion poblacionInicial;
	private List<EraObserver> observadores;
	// Aquí conservo las sucesivas poblaciones resultado de la evolucion 
	// en la computacion
	private List<Poblacion> evolucionPoblaciones;
	

	
	public Era() {
		super();
		observadores = new ArrayList<EraObserver>();
	}

	/**
	 * @param configuracion the configuracion to set
	 */
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public void ejecutar() {
		if (!Thread.currentThread().isInterrupted()) {
			this.inicializarPoblacion();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.ejecutarBucle();
		}
	}
	
	private void inicializarPoblacion() {
		this.poblacionInicial = Poblacion.generarPoblacionInicializada(configuracion);
		this.evolucionPoblaciones = new ArrayList<Poblacion>();
		this.evolucionPoblaciones.add(poblacionInicial);
	}
	
	private void ejecutarBucle() {
		int generacionActual = 0;
		while (!Thread.currentThread().isInterrupted() && generacionActual < configuracion.getMaxGens()) {
			Generacion generacion = new Generacion(poblacionInicial, configuracion);
			generacionActual++;
			generacion.ejecutar();
			// Añado el resultado de la generacion que es una nueva poblacion
			this.evolucionPoblaciones.add(generacion.getNuevaPoblacion());
			log.debug("******************************************** Generacion numero: " + (generacionActual));
			ResultadoParcialGeneracion resultadoGeneracion = ResultadoParcialGeneracion.crearResultadoGeneracion(generacion, generacionActual);
			this.notifyGeneracion(resultadoGeneracion);
		}
	}

	/*
	 * El mejor cromosoma obtenido en la computación hasta el momento
	 */
	public Cromosoma obtenerMejor() {
		Cromosoma mejorIndividuo = this.evolucionPoblaciones.get(
				this.evolucionPoblaciones.size()).obtenerMejor();
		return mejorIndividuo;
	}

	@Override
	public void registerObserver(EraObserver observer) {
		observadores.add(observer);
	}

	@Override
	public void notifyGeneracion(ResultadoParcialGeneracion resultadoGeneracion) {
		for (EraObserver o:this.observadores) {
			o.updateGeneracion(resultadoGeneracion);
		}
	}
}
