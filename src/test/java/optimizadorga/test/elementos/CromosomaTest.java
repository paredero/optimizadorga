/**
 * 
 */
package optimizadorga.test.elementos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.model.Chromosome;
import com.uned.optimizadorga.model.GeneType;

/**
 * @author fpb
 *
 */
public class CromosomaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.uned.optimizadorga.model.Chromosome#generarAleatorio(java.util.List)}.
	 */
	@Test
	public void testGenerarAleatorio() {
		Map<String, GeneType> genesParametro = new HashMap<String, GeneType>();
		genesParametro.put("p1",new GeneType("p1",0, 100, 3));
		genesParametro.put("p2",new GeneType("p2",100, 200, 3));
		genesParametro.put("p3",new GeneType("p3",200, 300, 3));
		Chromosome c = Chromosome
				.generateRandomChromome(genesParametro);
		
		assertNotNull("El cromosoma no se ha creado", c);
		assertNotNull("La lista de genes no se ha creado", c.getGenes());	
		assertTrue("Los genes no se han incluido dentro de la lista "
				+ c.getGenes().size(), c.getGenes().size() == 3);
		assertTrue("Valor Erroneo en gen p1" + c.getGenes().get(0), c
				.getGenes().get(0).getValue() <= c
						.getGenes().get(0).getGeneType().getMax());
		assertTrue("Valor Erroneo en gen p1" + c.getGenes().get(0), c
				.getGenes().get(0).getValue() >= c
						.getGenes().get(0).getGeneType().getMin());

		assertTrue("Valor Erroneo en gen p2" + c.getGenes().get(1), c
				.getGenes().get(1).getValue() <= c
						.getGenes().get(1).getGeneType().getMax());
		assertTrue("Valor Erroneo en gen p2" + c.getGenes().get(1), c
				.getGenes().get(1).getValue() >= c
						.getGenes().get(1).getGeneType().getMin());

		assertTrue("Valor Erroneo en gen p3" + c.getGenes().get(2), c
				.getGenes().get(2).getValue() <= c
						.getGenes().get(2).getGeneType().getMax());
		assertTrue("Valor Erroneo en gen p3" + c.getGenes().get(2), c
				.getGenes().get(2).getValue() >= c
						.getGenes().get(2).getGeneType().getMin());		
	}

}
