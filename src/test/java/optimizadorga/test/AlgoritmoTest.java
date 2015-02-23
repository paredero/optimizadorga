package optimizadorga.test;

import optimizadorga.util.CreadorObjetos;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.elementos.Cromosoma;

public class AlgoritmoTest {
	private static final Logger log = Logger.getLogger(AlgoritmoTest.class);
	
	Algoritmo algoritmo;

	@Before
	public void setUp() throws Exception {
//		Gen x1 = new Gen("x1",-3.0, 12.1, 1);
//		Gen x2 = new Gen("x2",4.1, 5.8, 1);
//		List<Gen> genes = new ArrayList<Gen>();
//		genes.add(x1);
//		genes.add(x2);
		
//		String funcionCaso1 = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
//		Funcion funcion = new Funcion(funcionCaso1);
		algoritmo = CreadorObjetos.crearAlgoritmoInicializado();
		algoritmo.setProbabilidadCruce(0.5);
		algoritmo.setProbabilidadMutacion(0.5);
		log.debug(algoritmo.getPoblacion());
	}

	@Test
	public void testEvaluar() {
		algoritmo.evaluar();
		log.debug(algoritmo.getPoblacion());
	}
	
	@Test
	public void testMantenerMejor() {
		algoritmo.evaluar();
		log.debug("*************************************************");
		log.debug("Poblacion Evaluada");
		log.debug(algoritmo.getPoblacion());
		Cromosoma mejor = algoritmo.obtenerMejor();
		log.debug("*************MEJOR ELEMENTO *******************");
		log.debug(mejor);
		log.debug(algoritmo.getPoblacion());
	}
	
	@Test
	public void testBucleEjecucion() {
		algoritmo.evaluar();
		algoritmo.obtenerMejor();
		algoritmo.ejecutarBucle();
		log.debug(algoritmo.getPoblacion());
		log.debug(algoritmo.obtenerMejor());
	}

}
