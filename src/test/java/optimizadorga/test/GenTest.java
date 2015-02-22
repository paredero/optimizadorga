/**
 * 
 */
package optimizadorga.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.elementos.Gen;

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
	 * Test method for {@link com.uned.optimizadorga.elementos.Gen#generarValorAleatorio()}.
	 */
	@Test
	public void testGenerarValorAleatorio() {
		Gen parametro = new Gen("p1",0, 100, 5);
		parametro.generarValorAleatorio();
		System.out.println(parametro);
		assertTrue("El valor del gen es mayor que el minimo"+parametro, parametro.getValor() >= 0);
		assertTrue("El valor del gen es menor que el maximo"+parametro, parametro.getValor() <= 100);		
		
		Gen parametro2 = new Gen("p2", 2000, 3000, 10);
		parametro2.generarValorAleatorio();
		System.out.println(parametro2);
		assertTrue("El valor del gen es mayor que el minimo"+parametro2, parametro2.getValor() >= 200);
		assertTrue("El valor del gen es menor que el maximo"+parametro2, parametro2.getValor() <= 300);
	}

}
