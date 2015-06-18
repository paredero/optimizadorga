package com.uned.optimizadorga.algoritmo.selectores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * Implementacion del operador selector mediante una seleccion por torneo determinística
 * @author fjgarcia
 *
 */
public class SelectorTorneoDeterminista implements Selector {
	private static final Logger log = Logger.getLogger(SelectorTorneoDeterminista.class);
	private int numElemSeleccionados = 2;
	private Random random = new Random();
	@Override
	public Poblacion seleccionar(Poblacion poblacionInicial) {
		// 1 Inicialmente crea una poblacion vacia con la misma configuración
		// sobre la que se devuelven los nuevos elementos
		Poblacion poblacionSeleccionados = Poblacion.copiarPoblacionVacia(poblacionInicial);
		List<Cromosoma> cromosomasSeleccionados = new ArrayList<Cromosoma>();
		
		Poblacion poblacionMuestra = Poblacion.copiarPoblacionVacia(poblacionInicial);
		poblacionMuestra.setTamanio(numElemSeleccionados);
		
		//TODO Traza
		boolean seleccionado = false;
		Cromosoma mejor = poblacionInicial.obtenerMejor();
		// Genera grupos aleatorios y toma el mejor de cada grupo para la nueva poblacion
		while (cromosomasSeleccionados.size()<=poblacionSeleccionados.getTamanio()) {
			List<Cromosoma> muestra = this.seleccionarAlAzar(poblacionInicial, numElemSeleccionados);
			if (muestra.contains(mejor)) {
				seleccionado = true;
			}
			poblacionMuestra.setCromosomas(muestra);
			cromosomasSeleccionados.add(poblacionMuestra.obtenerMejor());
		}
		if (!seleccionado) {
			log.info("No se ha seleccionado el mejor cromosoma " + mejor);
		}
		poblacionSeleccionados.setCromosomas(cromosomasSeleccionados);
		return poblacionSeleccionados;
	}
	
	
	/**
	 * Devuelve una lista con numeroElementos Cromosomas seleccionados al azar
	 * de la poblacion inicial
	 * 
	 * @param poblacionInicial
	 * @param numeroElementos
	 * @return
	 */
	private List<Cromosoma> seleccionarAlAzar(Poblacion poblacionInicial,
			int numeroElementos) {
		List<Cromosoma> listaCromosomas = new ArrayList<Cromosoma>();
		while (listaCromosomas.size() < numeroElementos) {
			int posicion = random.nextInt(poblacionInicial.getTamanio());
			listaCromosomas.add(poblacionInicial.getCromosomas().get(posicion));
		}
		return listaCromosomas;
	}


	/**
	 * @param numElemSeleccionados the numElemSeleccionados to set
	 */
	public void setNumElemSeleccionados(int numElemSeleccionados) {
		this.numElemSeleccionados = numElemSeleccionados;
	}

	
}
