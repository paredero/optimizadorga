/**
 * 
 */
package optimizadorga.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

/**
 * @author fpb
 *
 */
public class PoblacionTest {

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
		
		System.out.println(p);
	}

}
