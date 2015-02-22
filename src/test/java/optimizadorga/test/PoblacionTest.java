/**
 * 
 */
package optimizadorga.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import optimizadorga.util.CreadorObjetos;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * @author fpb
 *
 */
public class PoblacionTest {

	private static final Logger log = Logger.getLogger(PoblacionTest.class);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.uned.optimizadorga.elementos.Poblacion#generarPoblacionInicializada(int, java.util.List)}.
	 */
	@Test
	public void testGenerarPoblacionInicializada() {
		Gen parametro1 = new Gen("p1",0, 100, 3);
		Gen parametro2 = new Gen("p2",100, 200, 3);
		Gen parametro3 = new Gen("p3",200, 300, 3);
		List<Gen> genesParametro = new ArrayList<Gen>();
		genesParametro.add(parametro1);
		genesParametro.add(parametro2);
		genesParametro.add(parametro3);
		Poblacion p = Poblacion
				.generarPoblacionInicializada(5, genesParametro);
		
		assertNotNull("La poblacion no se ha creado", p);
		assertTrue("El tamaño de la poblacion no se ha copiado " + p.getTamanio(),
				p.getTamanio() == 5);
		assertNotNull("La lista de cromosomas no se ha creado", p.getCromosomas());	
		
		log.debug(p);
	}
	
	
	@Test 
	public void testCalcularCostesPoblacion() {
		Poblacion p = CreadorObjetos.crearPoblacionInicializada();
		log.debug("Poblacion antes de calcular" + p);
		p.calcularCostesPoblacion();
		log.debug("Poblacion despues de calcular" + p);
	}
	
	@Test 
	public void testObtenerMejorPeor() {
		Poblacion p = CreadorObjetos.crearPoblacionInicializada();
		log.debug("Poblacion antes de calcular" + p);
		p.calcularCostesPoblacion();
		log.debug("Poblacion despues de calcular" + p);
		log.debug("******************MEJOR ELEMENTO******");
		log.debug(p.obtenerMejor());
		log.debug("******************PEOR ELEMENTO******");
		log.debug(p.obtenerPeor());
	}

}
