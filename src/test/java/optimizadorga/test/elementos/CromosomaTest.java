/**
 * 
 */
package optimizadorga.test.elementos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.TipoGen;

/**
 * @author fpb
 *
 */
public class CromosomaTest {
	private static final Logger log = Logger.getLogger(CromosomaTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.uned.optimizadorga.elementos.Cromosoma#generarAleatorio(java.util.List)}.
	 */
	@Test
	public void testGenerarAleatorio() {
		Map<String, TipoGen> genesParametro = new HashMap<String, TipoGen>();
		genesParametro.put("p1",new TipoGen("p1",0, 100, 3));
		genesParametro.put("p2",new TipoGen("p2",100, 200, 3));
		genesParametro.put("p3",new TipoGen("p3",200, 300, 3));
		Cromosoma c = Cromosoma
				.generarCromosomaAleatorio(genesParametro);
		
		assertNotNull("El cromosoma no se ha creado", c);
		assertNotNull("La lista de genes no se ha creado", c.getGenes());	
		assertTrue("Los genes no se han incluido dentro de la lista "
				+ c.getGenes().size(), c.getGenes().size() == 3);
		assertTrue("Valor Erroneo en gen p1" + c.getGenes().get(0), c
				.getGenes().get(0).getValor() <= 100);
		assertTrue("Valor Erroneo en gen p1" + c.getGenes().get(0), c
				.getGenes().get(0).getValor() >= 0);

		assertTrue("Valor Erroneo en gen p2" + c.getGenes().get(1), c
				.getGenes().get(1).getValor() <= 200);
		assertTrue("Valor Erroneo en gen p2" + c.getGenes().get(1), c
				.getGenes().get(1).getValor() >= 100);

		assertTrue("Valor Erroneo en gen p3" + c.getGenes().get(2), c
				.getGenes().get(2).getValor() <= 300);
		assertTrue("Valor Erroneo en gen p3" + c.getGenes().get(2), c
				.getGenes().get(2).getValor() >= 200);
		
		log.debug(c);
	}

}
