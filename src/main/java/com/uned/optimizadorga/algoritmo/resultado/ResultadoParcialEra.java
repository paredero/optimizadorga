package com.uned.optimizadorga.algoritmo.resultado;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class ResultadoParcialEra extends ResultadoParcial {

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("Mejor Cromosoma obtenido hasta el momento: ").append("\n");
		for (Gen g:this.getMejorCromosoma().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\nCoste: ").append(this.getMejorCromosoma().getCoste()).append("\n");
		sb.append("Valor medio del coste: ").append(this.getMediaCoste()).append("\n");
		return sb.toString();
	}


	/**
	 * @param startTime
	 * @param era
	 * @param eraActual 
	 * @return
	 */
	public static ResultadoParcialEra crearResultadoEra(long startTime,
			List<Era> listaEras, Configuracion configuracion) {
		ResultadoParcialEra resultadoEra = new ResultadoParcialEra();
		resultadoEra.setEraActual(listaEras.size());
		resultadoEra.setGeneracionActual(0);
		resultadoEra.setCambioEra(true);
		resultadoEra.setCambioGeneracion(false);
		long timeParcial = System.currentTimeMillis();
		resultadoEra.setTiempoEjecucion((timeParcial - startTime)/1000);
		resultadoEra.setMejorCromosoma(obtenerMejorEras(listaEras, configuracion));
		resultadoEra.setMediaCoste(obtenerMediaMejores(listaEras));
		resultadoEra.setProgreso(calcularProgreso(listaEras,
				new ArrayList<Generacion>(), configuracion));
		return resultadoEra;
	}

	private static double obtenerMediaMejores(List<Era> listaEras) {
		double sumaTotal = 0.0;
		for (Era era:listaEras) {
			sumaTotal += era.obtenerMejor().getCoste();
		}
		
		return sumaTotal/listaEras.size();
	}

	/**
	 * Obtiene el mejor cromosoma entre una lista de eras
	 * @param listaEras
	 * @param configuracion
	 * @return
	 */
	private static Cromosoma obtenerMejorEras(List<Era> listaEras, Configuracion configuracion) {
		List<Cromosoma> listaMejoresCromosomas = new ArrayList<Cromosoma>();
		for (Era era:listaEras) {
			listaMejoresCromosomas.add(era.obtenerMejor());
		}
		// Construyo una poblacion con los mejores elementos de cada era y de ahí obtengo su mejor
		Poblacion p = new Poblacion();
		p.setFuncionCoste(configuracion.getFuncionCoste());
		p.setCromosomas(listaMejoresCromosomas);
		p.setTamanio(listaMejoresCromosomas.size());
		
		return p.obtenerMejor();
	}
}
