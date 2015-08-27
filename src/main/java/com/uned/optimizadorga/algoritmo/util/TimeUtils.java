package com.uned.optimizadorga.algoritmo.util;

import java.util.concurrent.TimeUnit;
/**
 * Clase contenedora de las operaciones de utilidad relacionadas con el tiempo
 * @author Francisco Javier García Paredero
 *
 */
public class TimeUtils {

	/**
	 * @param tiempoEjecucion el tiempo en milisegundos
	 * @return El tiempo formateado en horas, minutos y segundos
	 */
	public static String formatear(long tiempoEjecucion) {
		final long hr = TimeUnit.SECONDS.toHours(tiempoEjecucion);
		final long min = TimeUnit.SECONDS.toMinutes(tiempoEjecucion
				- TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.SECONDS.toSeconds(tiempoEjecucion
				- TimeUnit.HOURS.toSeconds(hr) - TimeUnit.MINUTES.toSeconds(min));
		return String.format("%02d:%02d:%02d", hr, min, sec);
	}

}
