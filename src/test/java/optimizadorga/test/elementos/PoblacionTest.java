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
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;
import com.uned.optimizadorga.elementos.TipoGen;

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
//		Map<String, TipoGen> genesParametro = new HashMap();
//		genesParametro.put("p1",new TipoGen("p1",0, 100, 3));
//		genesParametro.put("p2",new TipoGen("p2",100, 200, 3));
//		genesParametro.put("p3",new TipoGen("p3",200, 300, 3));
//		Poblacion p = Poblacion
//				.generarPoblacionInicializada(5, genesParametro);
//		
//		assertNotNull("La poblacion no se ha creado", p);
//		assertTrue("El tamaño de la poblacion no se ha copiado " + p.getTamanio(),
//				p.getTamanio() == 5);
//		assertNotNull("La lista de cromosomas no se ha creado", p.getCromosomas());	
//		
//		log.debug(p);
	}
	
	@Test
	public void testConstructorPoblacion() {
//		Map<String, TipoGen> parametros = new HashMap<String, TipoGen>();
//		parametros.put("x1", new TipoGen("x1",-3.0, 12.1, 1));
//		parametros.put("x2", new TipoGen("x2",4.1, 5.8, 1));
//		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
//		Funcion funcionCoste = null;
//		try {
//			funcionCoste = new Funcion(expresion, parametros);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Poblacion p = Poblacion
//				.generarPoblacionInicializada(5, parametros);
//		p.setFuncionCoste(funcionCoste);
//		p.calcularCostesPoblacion();
		
//		Poblacion copia = new Poblacion(p);
//		assertNotNull("La copia no se ha creado", copia);
//		assertTrue("El tamaño de la poblacion no se ha copiado " + copia.getTamanio(),
//				copia.getTamanio() == 5);
//		assertNotNull("La lista de cromosomas no se ha creado", copia.getCromosomas());
//		assertNotNull("No se ha creado la funcion de coste de la poblacion", 
//				copia.getFuncionCoste());
//		
//		assertTrue("El objeto Copia es igual al objeto poblacion", copia != p);
//		assertTrue("La lista de cromosomas es el mismo objeto en ambas poblaciones", copia.getCromosomas() != p.getCromosomas());
//		assertTrue("La funcion de coste es distinta entre ambas poblaciones", copia.getFuncionCoste() == p.getFuncionCoste());
//		
//		// Valida los cromosomas
//		assertTrue("La funcion de coste es distinta entre ambas poblaciones", copia.getCromosomas().size() == p.getCromosomas().size());
//		int i = 0;
//		for (Cromosoma c:p.getCromosomas()) {
//			Cromosoma cCopia = copia.getCromosomas().get(i++);
//			assertTrue("El cromosoma c y el cromosoma cCopia son iguales", 
//					cCopia != c);
//			assertTrue("Costes distintos en cromosomas", c.getCoste() == cCopia.getCoste());
//			assertTrue("Lista de genes del cromosoma es el mismo objeto", c.getGenes() != cCopia.getGenes());
//			assertTrue("Lista de genes del cromosoma distintos tams", c.getGenes().size() == cCopia.getGenes().size());
//			
//			// Valido genes
//			int j = 0;
//			for (Gen g:c.getGenes()) {
//				Gen gCopia = cCopia.getGenes().get(j++);
//				assertTrue("El gen y el gen gCopia son el mismo objeto", 
//						g != gCopia);
//				assertTrue("Maximo distinto en genes", g.getTipoGen().getMaximo() == gCopia.getTipoGen().getMaximo());
//				assertTrue("Minimo distinto en genes", g.getTipoGen().getMinimo() == gCopia.getTipoGen().getMinimo());
//				assertTrue("Nombre distinto en genes", g.getNombre() == gCopia.getNombre());
//				assertTrue("Precision distinto en genes", g.getTipoGen().getPrecision() == gCopia.getTipoGen().getPrecision());
//				assertTrue("Valor distinto en genes", g.getValor() == gCopia.getValor());
//			}
//		}
	}
	

}
