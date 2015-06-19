package com.uned.optimizadorga.algoritmo.resultado;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class ResultadoParcialEra extends ResultadoParcial {

	private List<ResultadoParcialGeneracion> resultadosGeneraciones;
	private Cromosoma mejorCromosomaEra;

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("Mejor Cromosoma de la era: ");
		for (Gen g:this.getMejorCromosomaEra().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaEra().getCoste()).append("\n");
		
		sb.append("Mejor Cromosoma obtenido hasta el momento: ");
		for (Gen g:this.getMejorCromosomaTotal().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaTotal().getCoste()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCoste()).append("\n");
		return sb.toString();
	}


	/**
	 * @param startTime
	 * @param resultadosGeneraciones 
	 * @param era
	 * @param eraActual 
	 * @return
	 */
	public static ResultadoParcialEra crearResultadoEra(long startTime, Era era,
			List<ResultadoParcialEra> resultadosEras, List<ResultadoParcialGeneracion> resultadosGeneraciones, Configuracion configuracion) {
		ResultadoParcialEra resultadoEra = new ResultadoParcialEra();
		resultadoEra.setEraActual(resultadosEras.size()+1);
		resultadoEra.setGeneracionActual(0);
		resultadoEra.setCambioEra(true);
		resultadoEra.setCambioGeneracion(false);
		long timeParcial = System.currentTimeMillis();
		resultadoEra.setTiempoEjecucion((timeParcial - startTime)/1000);
		resultadoEra.setMejorCromosomaEra(era.obtenerMejor());
		resultadoEra.setMejorCromosomaTotal(obtenerMejorEras(resultadosEras, era, configuracion));
		//TODO Quiero la media de los mejores y la media de ???
		resultadoEra.setMediaCoste(obtenerMediaMejores(resultadosEras, era));
		resultadoEra.setProgreso(calcularProgreso(resultadosEras,
				new ArrayList<ResultadoParcialGeneracion>(), configuracion));
		resultadoEra.setResultadosGeneraciones(resultadosGeneraciones);
		return resultadoEra;
	}




	public void setMejorCromosomaEra(Cromosoma mejorCromosomaEra) {
		this.mejorCromosomaEra = mejorCromosomaEra;
	}

	public Cromosoma getMejorCromosomaEra() {
		return this.mejorCromosomaEra;
	}


	private void setResultadosGeneraciones(
			List<ResultadoParcialGeneracion> resultadosGeneraciones) {
		this.resultadosGeneraciones = resultadosGeneraciones;
	}


	/**
	 * @return the resultadosGeneraciones
	 */
	public List<ResultadoParcialGeneracion> getResultadosGeneraciones() {
		return this.resultadosGeneraciones;
	}


	private static double obtenerMediaMejores(List<ResultadoParcialEra> listaEras, Era era) {
		double sumaTotal = 0.0;
		for (ResultadoParcialEra e:listaEras) {
			sumaTotal += e.getMejorCromosomaTotal().getCoste();
		}
		sumaTotal += era.obtenerMejor().getCoste();
		
		return sumaTotal/(listaEras.size()+1);
	}

	/**
	 * Obtiene el mejor cromosoma entre una lista de eras
	 * @param listaEras
	 * @param configuracion
	 * @return
	 */
	private static Cromosoma obtenerMejorEras(List<ResultadoParcialEra> listaEras, Era era, Configuracion configuracion) {
		List<Cromosoma> listaMejoresCromosomas = new ArrayList<Cromosoma>();
		listaMejoresCromosomas.add(era.obtenerMejor());
		for (ResultadoParcialEra e:listaEras) {
			listaMejoresCromosomas.add(e.getMejorCromosomaTotal());
		}
		// Construyo una poblacion con los mejores elementos de cada era y de ahí obtengo su mejor
		Poblacion p = new Poblacion();
		p.setFuncionCoste(configuracion.getFuncionCoste());
		p.setCromosomas(listaMejoresCromosomas);
		p.setTamanio(listaMejoresCromosomas.size());
		
		return p.obtenerMejor();
	}
}
