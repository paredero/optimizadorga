package com.uned.optimizadorga.elementos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.comparadores.ComparadorMejorCoste;

public class Poblacion {
	private static final Logger log = Logger.getLogger(Poblacion.class);
	
	private List<Cromosoma> cromosomas;
	private int tamanio;
	private Funcion funcionCoste;

	private Cromosoma mejorCromosoma;
	
	/**
	 * Genera una poblacion con cromosomas aleatorios y calcula su coste
	 * @param configuracion
	 * @return
	 * @throws Exception 
	 */
	public static Poblacion generarPoblacionInicializada(Configuracion configuracion) throws Exception {
		Poblacion poblacion = new Poblacion();
		poblacion.setTamanio(configuracion.getTamanioPoblacion());
		
		for (int i = 0; i < configuracion.getTamanioPoblacion(); i++) {
			poblacion.getCromosomas().add(
					Cromosoma.generarCromosomaAleatorio(configuracion
							.getParametros()));			
		}
		poblacion.setFuncionCoste(configuracion.getFuncionCoste());
		poblacion.calcularCostesPoblacion();
		return poblacion;
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

	/**
	 * Calcula el coste para la cada individuo de la población
	 * @throws Exception 
	 */
	public void calcularCostesPoblacion() throws Exception {
//		log.debug("***********calcula costes de la poblacion " + this.hashCode());
		for (Cromosoma individuo : this.getCromosomas()) {
			individuo.calcularCoste(this.funcionCoste);
		}
//		log.debug("***********Fin del calculo costes de la poblacion " + this);
	}

	/**
	 * Obtiene el mejor individuo de la población
	 * @return
	 */
	public Cromosoma obtenerMejor() {
		mejorCromosoma = Collections
				.max(cromosomas, new ComparadorMejorCoste());

		return mejorCromosoma;
	}

	public Cromosoma obtenerPeor() {
		Cromosoma peorCromosoma = Collections.min(cromosomas,
				new ComparadorMejorCoste());
		return peorCromosoma;
	}
	
	/**
	 * Crea una poblacion sin cromosomas
	 * copia la funcion de coste y el tamaño
	 * @param poblacionInicial
	 * @return
	 */
	public static Poblacion copiarPoblacionVacia(Poblacion poblacion) {
		Poblacion copia = new Poblacion();
		copia.setFuncionCoste(poblacion.getFuncionCoste());
		copia.setTamanio(poblacion.getTamanio());
		return copia;
	}

	public static Poblacion copiarPoblacion(Poblacion origen) {
		Poblacion copia = new Poblacion();
		copia.setFuncionCoste(origen.getFuncionCoste());
		copia.setTamanio(origen.getTamanio());
		List<Cromosoma> listaCromosomas = new ArrayList<Cromosoma>();
		for (Cromosoma c:origen.getCromosomas()) {
			Cromosoma nuevo = new Cromosoma(c);
			listaCromosomas.add(nuevo);
		}
		copia.setCromosomas(listaCromosomas);
		return copia;
	}
	
	/**
	 * Obtiene la media de la funcion de coste para los cromosomas de la poblacion
	 * @return
	 */
	public double calcularMediaCoste() {
		double sumaCostes = 0.0;
		for(Cromosoma c: this.cromosomas) {
			sumaCostes+=c.getCoste();
		}
		return sumaCostes/this.tamanio;
	}

	public double calcularDesviacionTipica() {
		double media = this.calcularMediaCoste();
		double cuadradosAcumulados = 0;
		for(Cromosoma c: this.cromosomas) {
			double diferencia = c.getCoste() - media;
			cuadradosAcumulados += (diferencia*diferencia);
		}
		return Math.sqrt(cuadradosAcumulados/this.tamanio);
	}

	
	
	public void sustituirCromosoma(Cromosoma peor,
			Cromosoma mejorPoblacionInicial) {
//		peor.setCoste(mejorPoblacionInicial.getCoste());
//		peor.setGenes(new ArrayList<Gen>());
//		for (Gen g:mejorPoblacionInicial.getGenes()) {
//			peor.getGenes().add(new Gen(g));
//		}
		Collections.replaceAll(this.getCromosomas(), peor, new Cromosoma(mejorPoblacionInicial));
		mejorCromosoma = null;
	}


	


}
