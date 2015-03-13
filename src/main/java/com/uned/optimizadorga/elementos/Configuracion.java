package com.uned.optimizadorga.elementos;

import java.util.List;

import com.uned.optimizadorga.algoritmo.selectores.Selector;
import com.uned.optimizadorga.algoritmo.selectores.SelectorRuleta;

public class Configuracion {
	private static Configuracion instancia = new Configuracion();
	private int maxEras;
	private int maxGens;
	private Funcion funcionCoste;
	private List<Gen> parametros;
	private int tamanioPoblacion;
	private double probabilidadCruce;
	private double probabilidadMutacion;
	private Selector selector;

	private Configuracion() {
		super();
	}
	
	
	public List<Gen> getParametros() {
		return this.parametros;
	}

	public int getTamanioPoblacion() {
		return this.tamanioPoblacion;
	}

	public Funcion getFuncionCoste() {
		return this.funcionCoste;
	}

	public int getMaxGens() {
		return this.maxGens;
	}

	public Selector getSelector() {
		return this.selector;
	}

	public double getProbabilidadCruce() {
		return this.probabilidadCruce;
	}

	public double getProbabilidadMutacion() {
		return this.probabilidadMutacion;
	}

	public int getMaxEras() {
		return this.maxEras;
	}

	public static Configuracion crearConfiguracionBasica(Integer maxEras,
			Integer maxGens, Funcion funcionCoste, List<Gen> parametros,
			Integer tamanioPoblacion, Double probabilidadCruce, Double probabilidadMutacion) {
		instancia.maxEras = maxEras;
		instancia.maxGens = maxGens;
		instancia.funcionCoste = funcionCoste;
		instancia.parametros = parametros;
		instancia.tamanioPoblacion = tamanioPoblacion;
		instancia.probabilidadCruce = probabilidadCruce;
		instancia.probabilidadMutacion = probabilidadMutacion;
		instancia.selector = new SelectorRuleta();
		return instancia;
	}

}
