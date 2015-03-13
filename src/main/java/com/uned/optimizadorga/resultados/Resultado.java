package com.uned.optimizadorga.resultados;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

	private Integer eraActual;
	private List<ResultadoEra> resultadosEra;
	private long tiempoEjecucion;
	
	public Resultado() {
		super();
		this.eraActual = 0;
		this.resultadosEra = new ArrayList<ResultadoEra>();
	}

	public Integer getEraActual() {
		return this.eraActual;
	}

	public void incrementarEra() {
		this.eraActual++;
	}

	public void addResultadoEra(ResultadoEra resultadoEra) {
		this.resultadosEra.add(resultadoEra);
	}

	public void setTiempoEjecucion(long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
}
