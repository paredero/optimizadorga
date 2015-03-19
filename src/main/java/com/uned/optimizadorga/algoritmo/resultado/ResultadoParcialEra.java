package com.uned.optimizadorga.algoritmo.resultado;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

public class ResultadoParcialEra extends ResultadoParcial {

	@Override
	public String printResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append("Era Actual: ").append(this.eraActual).append("\n");
		sb.append("\tMejor Cromosoma obtenido hasta el momento: ");
		for (Gen g:this.getMejorCromosoma().getGenes()) {
			sb.append("[").append(g.getNombre()).append(",").append(g.getValor()).append("]");
		}
		sb.append("\n\tCoste: ").append(this.getMejorCromosoma().getCoste());
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
		resultadoEra.setCambioEra(true);
		resultadoEra.setCambioGeneracion(false);
		long timeParcial = System.currentTimeMillis();
		resultadoEra.setTiempoEjecucion((timeParcial - startTime)/1000);
		resultadoEra.setMejorCromosoma(obtenerMejorEras(listaEras, configuracion));
		resultadoEra.setMediaCoste(obtenerMediaMejores(listaEras));
		return resultadoEra;
	}

	private static double obtenerMediaMejores(List<Era> listaEras) {
		double sumaTotal = 0.0;
		for (Era era:listaEras) {
			sumaTotal += era.obtenerMejor().getCoste();
		}
		
		return sumaTotal/listaEras.size();
	}

	private static Cromosoma obtenerMejorEras(List<Era> listaEras, Configuracion configuracion) {
		List<Cromosoma> listaMejoresCromosomas = new ArrayList<Cromosoma>();
		for (Era era:listaEras) {
			listaMejoresCromosomas.add(era.obtenerMejor());
		}
		
		Poblacion p = new Poblacion();
		p.setFuncionCoste(configuracion.getFuncionCoste());
		p.setCromosomas(listaMejoresCromosomas);
		p.setTamanio(listaMejoresCromosomas.size());
		
		return p.obtenerMejor();
	}
}
