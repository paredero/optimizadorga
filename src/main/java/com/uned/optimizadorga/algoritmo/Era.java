package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.algoritmo.interfaces.EraSubject;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

public class Era implements EraSubject {
	private static final Logger log = Logger.getLogger(Era.class);
	private Configuracion configuracion;
	private Poblacion poblacion;
	private List<EraObserver> observadores;
	
	

	
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
			this.evaluar();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.ejecutarBucle();
		}
	}
	
	public void inicializarPoblacion() {
		this.poblacion = Poblacion.generarPoblacionInicializada(configuracion.getTamanioPoblacion(), configuracion.getParametros());
	}
	
	public void evaluar() {
		log.debug("Se evalua la poblacion " + this.poblacion);
		this.poblacion.setFuncionCoste(configuracion.getFuncionCoste());
		this.poblacion.calcularCostesPoblacion();
		log.debug("Nuevo coste " + this.poblacion);
	}
	


	public void ejecutarBucle() {
		int generacionActual = 0;
		while (!Thread.currentThread().isInterrupted() && generacionActual < configuracion.getMaxGens()) {
			Generacion generacion = new Generacion(poblacion, configuracion);
			generacionActual++;
			generacion.ejecutar();
			log.debug("******************************************** Generacion numero: " + (generacionActual));
			ResultadoParcial resultadoGeneracion = this.crearResultadoGeneracion(generacion, generacionActual);
			this.notifyGeneracion(resultadoGeneracion);
		}
	}
	
	private ResultadoParcial crearResultadoGeneracion(Generacion generacion,
			int generacionActual) {
		log.debug("Procesa el resultado de la generacion " + generacion);
		ResultadoParcial r = new ResultadoParcial();
		r.setGeneracionActual(generacionActual);
		r.setMejorCromosoma(poblacion.obtenerMejor());
		r.setMediaCoste(this.calcularMediaCoste(poblacion));
		return r;
	}
	
	public double calcularMediaCoste(Poblacion poblacion) {
		double coste = 0;
		for(Cromosoma c:poblacion.getCromosomas()) {
			coste += c.getCoste();
		}
		return coste/poblacion.getCromosomas().size();
	}

	public Cromosoma obtenerMejor() {
		Cromosoma mejorIndividuo = poblacion.obtenerMejor();	
//		TODO Should i? return new Cromosoma(mejorIndividuo);
		return mejorIndividuo;
	}

	@Override
	public void registerObserver(EraObserver observer) {
		observadores.add(observer);
	}

	@Override
	public void notifyGeneracion(ResultadoParcial resultadoGeneracion) {
		for (EraObserver o:this.observadores) {
			o.updateGeneracion(resultadoGeneracion);
		}
	}
}
