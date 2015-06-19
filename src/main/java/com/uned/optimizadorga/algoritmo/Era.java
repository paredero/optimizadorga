package com.uned.optimizadorga.algoritmo;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.algoritmo.interfaces.EraSubject;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * Clase que contiene la ejecucion de una era
 * Es observable por el algoritmo, al que informa de su proggreso
 * @author fpb
 *
 */
public class Era implements EraSubject {
//	private static final Logger log = Logger.getLogger(Era.class);
	private Configuracion configuracion;
	private Poblacion poblacionInicial;
	private List<EraObserver> observadores;
	// Aquí conservo las sucesivas poblaciones resultado de la evolucion 
	// en la computacion
	private List<Poblacion> evolucionPoblaciones;
	private Cromosoma mejorIndividuo;
	

	
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

	public void ejecutar() throws Exception {
		if (!Thread.currentThread().isInterrupted()) {
			this.inicializarPoblacion();
		}
		if (!Thread.currentThread().isInterrupted()) {
			this.ejecutarBucle();
		}
	}
	
	private void inicializarPoblacion() throws Exception {
		this.poblacionInicial = Poblacion.generarPoblacionInicializada(configuracion);
		this.evolucionPoblaciones = new ArrayList<Poblacion>(configuracion.getMaxGens());
		this.evolucionPoblaciones.add(poblacionInicial);
	}
	
	private void ejecutarBucle() throws Exception {
		int generacionActual = 0;
		while (!Thread.currentThread().isInterrupted() && generacionActual < configuracion.getMaxGens()) {
			Generacion generacion = new Generacion(poblacionInicial,
					configuracion);
			generacionActual++;
			generacion.ejecutar();
			// Añado el resultado de la generacion que es una nueva poblacion
			// TODO Si puedo me lo cargo
			this.evolucionPoblaciones.add(generacion.getNuevaPoblacion());
			//				log.debug("******************************************** Generacion numero: "
			//						+ (generacionActual));
			// La poblacion inicial de la siguiente generacion sera la obtenida en la ultima iteracion
			this.poblacionInicial = generacion.getNuevaPoblacion();
			this.notifyGeneracion(generacion);
		}
	}

	/**
	 * Devuelve El mejor cromosoma obtenido en la computación hasta el momento
	 * @return
	 */
	public Cromosoma obtenerMejor() {
		mejorIndividuo = this.evolucionPoblaciones.get(
				this.evolucionPoblaciones.size() - 1).obtenerMejor();
		return mejorIndividuo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.interfaces.EraSubject#registerObserver(com.uned.optimizadorga.algoritmo.interfaces.EraObserver)
	 */
	@Override
	public void registerObserver(EraObserver observer) {
		observadores.add(observer);
	}

	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.interfaces.EraSubject#notifyGeneracion(com.uned.optimizadorga.algoritmo.Generacion)
	 */
	@Override
	public void notifyGeneracion(Generacion generacionProcesada) {
		for (EraObserver o:this.observadores) {
			o.updateGeneracion(generacionProcesada);
		}
	}

	/**
	 * Metodo para liberar memoria
	 */
	public void liberarRecursos() {
		this.poblacionInicial = null;
		this.evolucionPoblaciones = null;
	}

	/**
	 * @return the evolucionPoblaciones
	 */
	public List<Poblacion> getEvolucionPoblaciones() {
		return this.evolucionPoblaciones;
	}
}
