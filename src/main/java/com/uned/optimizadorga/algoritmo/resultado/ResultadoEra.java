package com.uned.optimizadorga.algoritmo.resultado;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algorithm.Era;
import com.uned.optimizadorga.model.Chromosome;
import com.uned.optimizadorga.model.Configuration;
import com.uned.optimizadorga.model.Gene;
import com.uned.optimizadorga.model.Population;

/**
 * Clase para mostrar los resultados correspondientes al calculo de una era
 * @author Francisco Javier Garc�a Paredero
 */
public class ResultadoEra extends Resultado {

	private List<ResultadoGeneracion> resultadosGeneraciones;
	private Chromosome mejorCromosomaEra;
	private double mediaCosteEras;

	
	
	public ResultadoEra() {
		super();
	}


	/*
	 * (non-Javadoc)
	 * @see com.uned.optimizadorga.algoritmo.resultado.Resultado#printResultado()
	 */
	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("Mejor Cromosoma de la era: ");
		for (Gene g:this.getMejorCromosomaEra().getGenes()) {
			sb.append("[").append(g.getGeneType().getName()).append(",").append(g.getValue()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaEra().getFitness()).append("\n");
		
		sb.append("Mejor Cromosoma obtenido hasta el momento: ");
		for (Gene g:this.getMejorCromosomaTotal().getGenes()) {
			sb.append("[").append(g.getGeneType().getName()).append(",").append(g.getValue()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaTotal().getFitness()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCosteEras()).append("\n");
		return sb.toString();
	}


	/**
	 * Metodo de factoria est�tica
	 * @return El resultado de una era ya procesada
	 * @throws Exception 
	 */
	public static ResultadoEra crearResultadoEra(long startTime, Era era,
			List<ResultadoEra> resultadosEras, List<ResultadoGeneracion> resultadosGeneraciones, Configuration configuration) throws Exception {
		ResultadoEra resultadoEra = new ResultadoEra();
		resultadoEra.setEraActual(resultadosEras.size()+1);
		resultadoEra.setGeneracionActual(0);
		resultadoEra.setCambioEra(true);
		resultadoEra.setCambioGeneracion(false);
		long timeParcial = System.currentTimeMillis();
		resultadoEra.setTiempoEjecucion((timeParcial - startTime)/1000);
		resultadoEra.setMejorCromosomaEra(era.obtainBest());
		resultadoEra.setMejorCromosomaTotal(obtenerMejorEras(resultadosEras, era, configuration));
		resultadoEra.setMediaCosteEras(obtenerMediaMejores(resultadosEras, era));
		resultadoEra.setProgreso(calcularProgreso(resultadosEras, configuration));
		resultadoEra.setResultadosGeneraciones(resultadosGeneraciones);
		return resultadoEra;
	}

	protected static int calcularProgreso(
			List<ResultadoEra> resultadosEras,
			Configuration Configuration) {
		// log.debug("****************PROGRESO********************************");
		double progreso = 0;
		double numEra = resultadosEras.size() + 1;
		double totalEras = Configuration.getMaxEras();
		progreso = (((numEra) / totalEras) * 100);
		return (int) progreso;
	}



	private static double obtenerMediaMejores(List<ResultadoEra> listaEras, Era era) {
		double sumaTotal = 0.0;
		for (ResultadoEra e:listaEras) {
			sumaTotal += e.getMejorCromosomaEra().getFitness();
		}
		sumaTotal += era.obtainBest().getFitness();
		return sumaTotal/(listaEras.size()+1);
	}

	/**
	 * @param listaEras
	 * @param Configuration
	 * @return el mejor cromosoma entre una lista de eras
	 * @throws Exception 
	 */
	private static Chromosome obtenerMejorEras(List<ResultadoEra> listaEras, Era era, Configuration configuration) throws Exception {
		List<Chromosome> listaMejoresCromosomas = new ArrayList<Chromosome>();
		listaMejoresCromosomas.add(era.obtainBest());
		for (ResultadoEra e:listaEras) {
			listaMejoresCromosomas.add(e.getMejorCromosomaTotal());
		}
		// Construyo una Population con los mejores elementos de cada era y de ah� obtengo su mejor
		Population p = Population.generateInitializedPopulation(configuration);
		p.setFitnessFunction(configuration.getFitnessFunction());
		p.setChromosomes(listaMejoresCromosomas);
		p.setSize(listaMejoresCromosomas.size());
		
		return p.obtainBest();
	}
	
	public void setMediaCosteEras(double mediaCosteEras) {
		this.mediaCosteEras = mediaCosteEras;
	}


	/**
	 * @return the mediaCosteEras
	 */
	public double getMediaCosteEras() {
		return this.mediaCosteEras;
	}


	public void setMejorCromosomaEra(Chromosome mejorCromosomaEra) {
		this.mejorCromosomaEra = mejorCromosomaEra;
	}

	public Chromosome getMejorCromosomaEra() {
		return this.mejorCromosomaEra;
	}


	private void setResultadosGeneraciones(
			List<ResultadoGeneracion> resultadosGeneraciones) {
		this.resultadosGeneraciones = resultadosGeneraciones;
	}


	/**
	 * @return the resultadosGeneraciones
	 */
	public List<ResultadoGeneracion> getResultadosGeneraciones() {
		return this.resultadosGeneraciones;
	}
	
}
