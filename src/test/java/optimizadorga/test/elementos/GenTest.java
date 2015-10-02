/**
 * 
 */
package optimizadorga.test.elementos;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fjgarcia.optimizadorga.elementos.Gen;
import com.fjgarcia.optimizadorga.elementos.TipoGen;

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
		TipoGen tipoGen1 = new TipoGen("p1",0, 100, 5);
		Gen parametro = new Gen(tipoGen1);
		parametro.generarValorAleatorio();
		System.out.println(parametro);
		assertTrue("El valor del gen es mayor que el minimo"+parametro, parametro.getValor() >= 0);
		assertTrue("El valor del gen es menor que el maximo"+parametro, parametro.getValor() <= 100);		
		
		TipoGen tipoGen2 = new TipoGen("p2", 2000, 3000, 10);
		Gen parametro2 = new Gen(tipoGen2);
		parametro2.generarValorAleatorio();
		System.out.println(parametro2);
		assertTrue("El valor del gen es mayor que el minimo"+parametro2, parametro2.getValor() >= parametro2.getTipoGen().getMinimo());
		assertTrue("El valor del gen es menor que el maximo"+parametro2, parametro2.getValor() <= parametro2.getTipoGen().getMaximo());
	}

}
