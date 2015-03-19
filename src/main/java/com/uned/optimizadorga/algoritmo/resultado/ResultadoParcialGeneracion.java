package com.uned.optimizadorga.algoritmo.resultado;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Cromosoma;

public class ResultadoParcialGeneracion extends ResultadoParcial {
	private static final Logger log = Logger.getLogger(ResultadoParcialGeneracion.class);
	
	private double desviacionTipica;
	private double porcentajeMejora;

	public static ResultadoParcialGeneracion crearResultadoGeneracion(Generacion generacion,
			int generacionActual) {
		log.debug("Procesa el resultado de la generacion " + generacion.hashCode());
		ResultadoParcialGeneracion r = new ResultadoParcialGeneracion();
		r.setCambioGeneracion(true);
		r.setCambioEra(false);
		r.setGeneracionActual(generacionActual);
		Cromosoma mejorCromosomaGeneracion = generacion.getNuevaPoblacion().obtenerMejor();
		Cromosoma antiguoMejorCromosoma = generacion.getPoblacionInicial().obtenerMejor();
		r.setMejorCromosoma(mejorCromosomaGeneracion);
		r.setMediaCoste(generacion.getNuevaPoblacion().calcularMediaCoste());
		r.setDesviacionTipica(generacion.getNuevaPoblacion().calcularDesviacionTipica());
		r.setPorcentajeMejora((mejorCromosomaGeneracion.getCoste() / antiguoMejorCromosoma
				.getCoste()) * 100);
		log.debug("El resultado " + r);
		return r;
	}
	

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Generación Actual: ").append(this.generacionActual).append("\n");
		sb.append("\tMejor Cromosoma obtenido hasta el momento: ");
//		for (Gen g:this.getMejorCromosoma().getGenes()) {
//			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
//		}
//		sb.append("\n\tCoste: ").append(this.getMejorCromosoma().getCoste());
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
	}
	

}
