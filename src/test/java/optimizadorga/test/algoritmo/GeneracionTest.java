/**
 * 
 */
package optimizadorga.test.algoritmo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.model.GeneType;

/**
 * @author fpb
 * 
 */
public class GeneracionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Map<String, GeneType> genesParametro = new HashMap<String, GeneType>();
		genesParametro.put("x1", new GeneType("x1", -3.0, 12.1, 1));
		genesParametro.put("x2", new GeneType("x2",4.1, 5.8, 1));
//		p = Poblacion.generarPoblacionInicializada(5, genesParametro);
//
//		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
//		Funcion funcionCoste = new Funcion(expresion, genesParametro);
//
//		Configuracion c = Configuracion.crearConfiguracion(1, 2,
//				funcionCoste, genesParametro, 3, 0.5, 0.5);
//		p.setFuncionCoste(funcionCoste);
//		p.calcularCostesPoblacion();
//		g = new Generacion(p, c);
	}

	/**
	 * Test method for
	 * {@link com.uned.optimizadorga.algorithm.Generation#execute()}.
	 */
	@Test
	public void testEjecutar() {
//		log.debug("Poblacion Inicial: " + p);
//		g.ejecutar();
//		Poblacion nuevaPoblacion = g.getNuevaPoblacion();
//		log.debug("Poblacion Inicial tras la ejecucion: " + p);
//		log.debug("Nueva Poblacion: " + nuevaPoblacion);
//		log.debug("Nueva Poblacion tras calcular costes: " + nuevaPoblacion);
//		assertFalse("La nueva poblacion es igual que la poblacion inicial",
//				nuevaPoblacion == p);
//		assertTrue(
//				"La poblacion inicial ha sido modificada dentro del algoritmo",
//				g.getPoblacionInicial() == p);
//		Iterator<Cromosoma> itCromosomasIniciales = p.getCromosomas()
//				.iterator();
//		g.getConfiguracion().getFuncionCoste();
//		for (Cromosoma c : nuevaPoblacion.getCromosomas()) {
//			Cromosoma crIni = itCromosomasIniciales.next();
//			assertTrue("El cromosoma se ha copiado literalmente " + c,
//					crIni != c);
//			Funcion f = g.getConfiguracion().getFuncionCoste();
//			double costeCalculado = f.evaluate(c.getGenes());
//			assertTrue("El coste del cromosoma " + c
//					+ " no se ha calculado correctamente",
//					c.getCoste() == costeCalculado);
//		}
	}

}
