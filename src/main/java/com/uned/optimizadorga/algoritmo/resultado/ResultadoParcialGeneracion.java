package com.uned.optimizadorga.algoritmo.resultado;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.algoritmo.comparadores.ComparadorMejorCoste;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;

public class ResultadoParcialGeneracion extends ResultadoParcial {
	private static final Logger log = Logger.getLogger(ResultadoParcialGeneracion.class);
	
	private double desviacionTipica;
	

	private double porcentajeMejora;
	private Cromosoma mejorCromosomaGeneracion;
	private double mediaCostePoblacion;

	public static ResultadoParcialGeneracion crearResultadoGeneracion(Generacion generacion,
			long startTime, List<ResultadoParcialEra> resultadosEras, List<ResultadoParcialGeneracion> resultadosGeneraciones,
			Configuracion configuracion) {
		log.debug("Procesa el resultado de la generacion " + resultadosGeneraciones.size());
		ResultadoParcialGeneracion r = new ResultadoParcialGeneracion();
		long timeParcial = System.currentTimeMillis();
		r.setTiempoEjecucion((timeParcial - startTime)/1000);
		r.setCambioGeneracion(true);
		r.setCambioEra(false);
		r.setGeneracionActual(resultadosGeneraciones.size()+1); //+1 para contarlas como 1, 2, 3...
		r.setEraActual(resultadosEras.size()+1);
		Cromosoma mejorCromosomaGeneracion = generacion.getNuevaPoblacion().obtenerMejor();
		Cromosoma antiguoMejorCromosoma = generacion.getPoblacionInicial().obtenerMejor();
		
		if (new ComparadorMejorCoste().compare(mejorCromosomaGeneracion,
				antiguoMejorCromosoma) >= 0) {
			r.setMejorCromosomaGeneracion(mejorCromosomaGeneracion);
		} else {
			log.warn("SI hay elitismo, por qué el mejor es el antiguo mejor cromosoma???????");
			r.setMejorCromosomaGeneracion(antiguoMejorCromosoma);
		}
		
		r.setMediaCostePoblacion(generacion.getNuevaPoblacion().calcularMediaCoste());
		r.setDesviacionTipica(generacion.getNuevaPoblacion().calcularDesviacionTipica());
		r.setPorcentajeMejora(((mejorCromosomaGeneracion.getCoste() - antiguoMejorCromosoma
				.getCoste()) / antiguoMejorCromosoma.getCoste()) * 100);
		r.setProgreso(calcularProgreso(r.getEraActual(), r.getGeneracionActual(), configuracion));
		log.debug("El resultado " + r);
		return r;
	}


	
	public ResultadoParcialGeneracion() {
		super();
	}



	protected static int calcularProgreso(int eraActual, int generacionActual,
			Configuracion configuracion) {
		// log.debug("****************PROGRESO********************************");
		double progreso = 0;
		int numEra = eraActual-1;
		double numGeneracion = generacionActual;
		double totalGeneraciones = configuracion.getMaxGens();
		double totalEras = configuracion.getMaxEras();

		progreso = (((numEra) / totalEras) * 100) + ((numGeneracion / (totalEras * totalGeneraciones)) * 100);
		return (int) progreso;
	}	

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Generación Actual: ").append(this.generacionActual).append("\n");
		sb.append("Mejor Cromosoma obtenido hasta el momento: ");	
		for (Gen g:this.getMejorCromosomaGeneracion().getGenes()) {
			sb.append("[").append(g.getTipoGen().getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaGeneracion().getCoste()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCostePoblacion()).append("\n");
		sb.append("Desviación típica del coste: ").append(this.getDesviacionTipica()).append("\n");
		sb.append("Porcentaje de mejora: ").append(this.getPorcentajeMejora());
		return sb.toString();
	}

	/**
	 * @return the desviacionTipica
	 */
	public double getDesviacionTipica() {
		return this.desviacionTipica;
	}


	/**
	 * @param desviacionTipica the desviacionTipica to set
	 */
	public void setDesviacionTipica(double desviacionTipica) {
		this.desviacionTipica = desviacionTipica;
	}


	/**
	 * @return the porcentajeMejora
	 */
	public double getPorcentajeMejora() {
		return this.porcentajeMejora;
	}


	/**
	 * @param porcentajeMejora the porcentajeMejora to set
	 */
	public void setPorcentajeMejora(double porcentajeMejora) {
		this.porcentajeMejora = porcentajeMejora;
		if (porcentajeMejora<0) {
			log.error("ERROR, GENERACION DETERIORADA");
		}
	}


	/**
	 * @return the mejorCromosomaGeneracion
	 */
	public Cromosoma getMejorCromosomaGeneracion() {
		return this.mejorCromosomaGeneracion;
	}


	/**
	 * @param mejorCromosomaGeneracion the mejorCromosomaGeneracion to set
	 */
	public void setMejorCromosomaGeneracion(Cromosoma mejorCromosomaGeneracion) {
		this.mejorCromosomaGeneracion = mejorCromosomaGeneracion;
	}
	
	
	public void setMediaCostePoblacion(double mediaCostePoblacion) {
		this.mediaCostePoblacion = mediaCostePoblacion;
	}
	
	

	/**
	 * @return the mediaCostePoblacion
	 */
	public double getMediaCostePoblacion() {
		return this.mediaCostePoblacion;
	}
	
}
