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
	private double mediaCosteEras;

	
	
	public ResultadoParcialEra() {
		super();
	}


	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("Mejor Cromosoma de la era: ");
		for (Gen g:this.getMejorCromosomaEra().getGenes()) {
			sb.append("[").append(g.getTipoGen().getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaEra().getCoste()).append("\n");
		
		sb.append("Mejor Cromosoma obtenido hasta el momento: ");
		for (Gen g:this.getMejorCromosomaTotal().getGenes()) {
			sb.append("[").append(g.getTipoGen().getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosomaTotal().getCoste()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCosteEras()).append("\n");
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
		resultadoEra.setMediaCosteEras(obtenerMediaMejores(resultadosEras, era));
		resultadoEra.setProgreso(calcularProgreso(resultadosEras, configuracion));
		resultadoEra.setResultadosGeneraciones(resultadosGeneraciones);
		return resultadoEra;
	}

	protected static int calcularProgreso(
			List<ResultadoParcialEra> resultadosEras,
			Configuracion configuracion) {
		// log.debug("****************PROGRESO********************************");
		double progreso = 0;
		double numEra = resultadosEras.size() + 1;
		double totalEras = configuracion.getMaxEras();
		progreso = (((numEra) / totalEras) * 100);
		return (int) progreso;
	}



	private static double obtenerMediaMejores(List<ResultadoParcialEra> listaEras, Era era) {
		double sumaTotal = 0.0;
		for (ResultadoParcialEra e:listaEras) {
			sumaTotal += e.getMejorCromosomaEra().getCoste();
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
	
	public void setMediaCosteEras(double mediaCosteEras) {
		this.mediaCosteEras = mediaCosteEras;
	}


	/**
	 * @return the mediaCosteEras
	 */
	public double getMediaCosteEras() {
		return this.mediaCosteEras;
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
	
}
