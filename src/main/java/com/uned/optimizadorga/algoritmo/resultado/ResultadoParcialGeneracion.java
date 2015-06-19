package com.uned.optimizadorga.algoritmo.resultado;

import java.util.List;

import org.apache.log4j.Logger;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.algoritmo.comparadores.ComparadorMejorCoste;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;

public class ResultadoParcialGeneracion extends ResultadoParcial {
	private static final Logger log = Logger.getLogger(ResultadoParcialGeneracion.class);
	
	private double desviacionTipica;
	private double porcentajeMejora;

	public static ResultadoParcialGeneracion crearResultadoGeneracion(Generacion generacion,
			long startTime, List<ResultadoParcialEra> resultadosEras, List<ResultadoParcialGeneracion> resultadosGeneraciones,
			Configuracion configuracion) {
		log.debug("Procesa el resultado de la generacion " + resultadosGeneraciones.size());
		ResultadoParcialGeneracion r = new ResultadoParcialGeneracion();
		long timeParcial = System.currentTimeMillis();
		r.setTiempoEjecucion((timeParcial - startTime)/1000);
		r.setCambioGeneracion(true);
		r.setCambioEra(false);
		r.setGeneracionActual(resultadosGeneraciones.size());
		r.setEraActual(resultadosEras.size());
		Cromosoma mejorCromosomaGeneracion = generacion.getNuevaPoblacion().obtenerMejor();
		Cromosoma antiguoMejorCromosoma = generacion.getPoblacionInicial().obtenerMejor();
		
		if (new ComparadorMejorCoste().compare(mejorCromosomaGeneracion,
				antiguoMejorCromosoma) >= 0) {
			r.setMejorCromosomaTotal(mejorCromosomaGeneracion);
		} else {
			log.warn("SI hay elitismo, por qu� el mejor es el antiguo mejor cromosoma???????");
			r.setMejorCromosomaTotal(antiguoMejorCromosoma);
		}
		
		r.setMediaCoste(generacion.getNuevaPoblacion().calcularMediaCoste());
		r.setDesviacionTipica(generacion.getNuevaPoblacion().calcularDesviacionTipica());
		r.setPorcentajeMejora(((mejorCromosomaGeneracion.getCoste() - antiguoMejorCromosoma
				.getCoste()) / antiguoMejorCromosoma.getCoste()) * 100);
		r.setProgreso(calcularProgreso(resultadosEras,
				resultadosGeneraciones, configuracion));
		log.debug("El resultado " + r);
		return r;
	}
	

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Generaci�n Actual: ").append(this.generacionActual).append("\n");
		sb.append("Mejor Cromosoma obtenido hasta el momento: ");	
		for (Gen g:this.getMejorCromosomaTotal().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaTotal().getCoste()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCoste()).append("\n");
		sb.append("Desviaci�n t�pica del coste: ").append(this.getDesviacionTipica()).append("\n");
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
	

}
