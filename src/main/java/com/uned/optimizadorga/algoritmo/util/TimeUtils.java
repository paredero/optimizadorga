package com.uned.optimizadorga.algoritmo.util;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	public static String formatear(long tiempoEjecucion) {
		final long hr = TimeUnit.MILLISECONDS.toHours(tiempoEjecucion);
		final long min = TimeUnit.MILLISECONDS.toMinutes(tiempoEjecucion
				- TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS.toSeconds(tiempoEjecucion
				- TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
		final long ms = TimeUnit.MILLISECONDS.toMillis(tiempoEjecucion
				- TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min)
				- TimeUnit.SECONDS.toMillis(sec));
		return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
	}

}
