package optimizadorga.test.algoritmo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algorithm.Algorithm;
import com.uned.optimizadorga.algorithm.Era;
import com.uned.optimizadorga.algorithm.Generation;
import com.uned.optimizadorga.algorithm.observerinterfaces.AlgorithmObserver;
import com.uned.optimizadorga.algorithm.selectors.SelectorType;
import com.uned.optimizadorga.model.Configuration;
import com.uned.optimizadorga.model.FitnessFunction;
import com.uned.optimizadorga.model.GeneType;

public class AlgoritmoTest implements AlgorithmObserver {

	private FitnessFunction funcionCoste;
	private Configuration c;
	private Algorithm a;
	private int notificacionesFin;
	private int notificacionesGeneracion;
	private int notificacionesEra;

	@Before
	public void setUp() throws Exception {
		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		
		Map<String, GeneType> genesParametro = new HashMap<String, GeneType>();
		genesParametro.put("x1", new GeneType("x1", -3.0, 12.1, 1));
		genesParametro.put("x2", new GeneType("x2", 4.1, 5.8, 1));
		funcionCoste = new FitnessFunction(expresion,genesParametro);
		c = Configuration.createConfiguration(3, 4,
				funcionCoste, genesParametro, 3, 0.5, 0.5, false, SelectorType.ROULETTE);

		a = new Algorithm(c);
	}


	@Test
	public void testRun() {
		a.registerObserver(this);
		a.run();
		assertEquals("Faltan notificaciones por eras", notificacionesEra,3);
		assertEquals("Faltan notificaciones por Generaciones", notificacionesGeneracion,12);
		assertEquals("Faltan notificaciones por fin", notificacionesFin,1);
	}

	@Test
	public void testRegisterObserver() {
//		fail("Not yet implemented");
	}

	@Test
	public void testNotifyEra() {
//		fail("Not yet implemented");
	}

	@Test
	public void testNotifyGeneracion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testNotifyFin() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetConfiguracion() {
//		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGeneracion() {
//		fail("Not yet implemented");
	}


	@Override
	public void updateEndEraExecution(Era resultadoParcial) {
		notificacionesEra++;
	}


	@Override
	public void updateEndGenerationExecution(Generation resultadoParcial) {
		notificacionesGeneracion++;
	}


	@Override
	public void updateEnd() {
		notificacionesFin++;
	}


	@Override
	public void updateError(Exception e) {
		
	}

}
