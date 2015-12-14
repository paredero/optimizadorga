/**
 * 
 */
package optimizadorga.test.elementos;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.model.Gene;
import com.uned.optimizadorga.model.GeneType;

/**
 * @author fpb
 *
 */
public class GenTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.uned.optimizadorga.model.Gene#generateRandomValue()}.
	 */
	@Test
	public void testGenerarValorAleatorio() {
		GeneType tipoGen1 = new GeneType("p1",0, 100, 5);
		Gene parametro = new Gene(tipoGen1);
		parametro.generateRandomValue();
		System.out.println(parametro);
		assertTrue("El valor del gen es mayor que el minimo"+parametro, parametro.getValue() >= 0);
		assertTrue("El valor del gen es menor que el maximo"+parametro, parametro.getValue() <= 100);		
		
		GeneType tipoGen2 = new GeneType("p2", 2000, 3000, 10);
		Gene parametro2 = new Gene(tipoGen2);
		parametro2.generateRandomValue();
		System.out.println(parametro2);
		assertTrue("El valor del gen es mayor que el minimo"+parametro2, parametro2.getValue() >= parametro2.getGeneType().getMin());
		assertTrue("El valor del gen es menor que el maximo"+parametro2, parametro2.getValue() <= parametro2.getGeneType().getMax());
	}

}
