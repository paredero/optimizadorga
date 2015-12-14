/**
 * 
 */
package optimizadorga.test.algoritmo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algorithm.Era;
import com.uned.optimizadorga.algorithm.Generation;
import com.uned.optimizadorga.algorithm.observerinterfaces.EraObserver;
import com.uned.optimizadorga.algorithm.selectors.SelectorType;
import com.uned.optimizadorga.model.Configuration;
import com.uned.optimizadorga.model.FitnessFunction;
import com.uned.optimizadorga.model.Population;
import com.uned.optimizadorga.model.GeneType;

/**
 * @author fpb
 *
 */
public class EraTest implements EraObserver {
	private Generation g;
	private Population p;
	private Era e;
	private Configuration c;
	private FitnessFunction funcionCoste;
	private int numeroActualizaciones;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Map<String, GeneType> genesParametro = new HashMap<String, GeneType>();
		genesParametro.put("x1", new GeneType("x1", -3.0, 12.1, 1));
		genesParametro.put("x2", new GeneType("x2", 4.1, 5.8, 1));
//		p = Poblacion.generarPoblacionInicializada(5, genesParametro);

		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		funcionCoste = new FitnessFunction(expresion, genesParametro);

		c = Configuration.createConfiguration(1, 2,
				funcionCoste, genesParametro, 3, 0.5, 0.5, false, SelectorType.TOURNAMENT);
//		p.setFuncionCoste(funcionCoste);
//		p.calcularCostesPoblacion();
//		g = new Generacion(p, c);
	}


	/**
	 * Test method for {@link com.uned.optimizadorga.algorithm.Era#execute()}.
	 */
	@Test
	public void testEjecutar() {
		Era era = new Era(c);
		era.registerObserver(this);
		try {
			era.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("No se han recibido las notificaciones necesarias",
				c.getMaxGens(), numeroActualizaciones);
	}

	/**
	 * Deben recibirse tantas actualizaciones como generaciones se pasan en configuracion
	 * @param resultadoParcial
	 */
	@Override
	public void updateGeneracion(Generation resultadoParcial) {
		numeroActualizaciones++;
	}

}
