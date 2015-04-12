/**
 * 
 */
package optimizadorga.test.algoritmo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * @author fpb
 * 
 */
public class GeneracionTest {
	private static final Logger log = Logger.getLogger(GeneracionTest.class);
	private Generacion g;
	private Poblacion p;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Map<String, Gen> genesParametro = new HashMap<String, Gen>();
		Gen x1 = new Gen("x1", -3.0, 12.1, 1);
		Gen x2 = new Gen("x2", 4.1, 5.8, 1);
		genesParametro.put("x1", x1);
		genesParametro.put("x2", x2);
		p = Poblacion.generarPoblacionInicializada(5, genesParametro);

		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		Funcion funcionCoste = new Funcion(expresion, genesParametro);

		Configuracion c = Configuracion.crearConfiguracion(1, 2,
				funcionCoste, genesParametro, 3, 0.5, 0.5);
		p.setFuncionCoste(funcionCoste);
		p.calcularCostesPoblacion();
		g = new Generacion(p, c);
	}

	/**
	 * Test method for
	 * {@link com.uned.optimizadorga.algoritmo.Generacion#ejecutar()}.
	 */
	@Test
	public void testEjecutar() {
		log.debug("Poblacion Inicial: " + p);
		g.ejecutar();
		Poblacion nuevaPoblacion = g.getNuevaPoblacion();
		log.debug("Poblacion Inicial tras la ejecucion: " + p);
		log.debug("Nueva Poblacion: " + nuevaPoblacion);
		log.debug("Nueva Poblacion tras calcular costes: " + nuevaPoblacion);
		assertFalse("La nueva poblacion es igual que la poblacion inicial",
				nuevaPoblacion == p);
		assertTrue(
				"La poblacion inicial ha sido modificada dentro del algoritmo",
				g.getPoblacionInicial() == p);
		Iterator<Cromosoma> itCromosomasIniciales = p.getCromosomas()
				.iterator();
		g.getConfiguracion().getFuncionCoste();
		for (Cromosoma c : nuevaPoblacion.getCromosomas()) {
			Cromosoma crIni = itCromosomasIniciales.next();
			assertTrue("El cromosoma se ha copiado literalmente " + c,
					crIni != c);
			Funcion f = g.getConfiguracion().getFuncionCoste();
			double costeCalculado = f.evaluate(c.getGenes());
			assertTrue("El coste del cromosoma " + c
					+ " no se ha calculado correctamente",
					c.getCoste() == costeCalculado);
		}
	}

}
