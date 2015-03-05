package com.uned.optimizadorga.elementos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poblacion {
	private List<Cromosoma> cromosomas;
	private int tamanio;
	private Funcion funcionCoste;

	public static Poblacion generarPoblacionInicializada(int tamanioPoblacion,
			List<Gen> genes) {
		Poblacion poblacion = new Poblacion();
		poblacion.setTamanio(tamanioPoblacion);

		for (int i = 0; i < tamanioPoblacion; i++) {
			poblacion.getCromosomas().add(
					Cromosoma.generarCromosomaAleatorio(genes));
		}

		return poblacion;
	}

	public Poblacion(Poblacion poblacionInicial) {
		this();
		this.funcionCoste = poblacionInicial.getFuncionCoste();
		this.tamanio = poblacionInicial.getTamanio();
	}

	public Poblacion() {
		super();
		this.cromosomas = new ArrayList<Cromosoma>();
	}

	/**
	 * @return the cromosomas
	 */
	public List<Cromosoma> getCromosomas() {
		return cromosomas;
	}

	/**
	 * @param cromosomas
	 *            the cromosomas to set
	 */
	public void setCromosomas(List<Cromosoma> cromosomas) {
		this.cromosomas = cromosomas;
	}

	/**
	 * @return the tamanio
	 */
	public int getTamanio() {
		return tamanio;
	}

	/**
	 * @param tamanio
	 *            the tamanio to set
	 */
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Poblacion " + this.hashCode() + "(" + tamanio + ")"
				+ cromosomas + "]";
	}

	public void setFuncionCoste(Funcion expresion) {
		this.funcionCoste = expresion;
	}

	/**
	 * @return the funcionCoste
	 */
	public Funcion getFuncionCoste() {
		return funcionCoste;
	}

	public void calcularCostesPoblacion() {
		for (Cromosoma individuo : this.getCromosomas()) {
			individuo.calcularCoste(this.funcionCoste);
		}
	}

	public Cromosoma obtenerMejor() {
		Cromosoma mejorCromosoma = Collections.max(cromosomas,
				new ComparadorMejorCoste());
		return mejorCromosoma;
	}

	public Cromosoma obtenerPeor() {
		Cromosoma peorCromosoma = Collections.min(cromosomas,
				new ComparadorMejorCoste());
		return peorCromosoma;
	}

	/**
	 * Añade el mejor individuo a la posicion max + 1
	 * 
	 * @param mejorIndividuo
	 */
	public void addMejor(Cromosoma mejorIndividuo) {
		cromosomas.add(mejorIndividuo);
	}

}
