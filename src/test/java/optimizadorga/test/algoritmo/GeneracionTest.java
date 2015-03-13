/**
 * 
 */
package optimizadorga.test.algoritmo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import optimizadorga.test.elementos.PoblacionTest;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;
//TODO To be implemented
/**
 * @author fpb
 *
 */
public class GeneracionTest {
	private static final Logger log = Logger.getLogger(GeneracionTest.class);
	private Generacion g;
	private Poblacion p;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<Gen> genesParametro = new ArrayList<Gen>();
		Gen x1 = new Gen("x1",-3.0, 12.1, 1);
		Gen x2 = new Gen("x2",4.1, 5.8, 1);
		genesParametro.add(x1);
		genesParametro.add(x2);
		p = Poblacion
				.generarPoblacionInicializada(5, genesParametro);
		
		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		Funcion funcionCoste = new Funcion(expresion);
		
		Configuracion c = Configuracion.crearConfiguracionBasica(
				1, 
				2, 
				funcionCoste, 
				genesParametro, 
				3, 
				0.5, 
				0.5);
		p.setFuncionCoste(funcionCoste);
		p.calcularCostesPoblacion();
		g = new Generacion(p,c);
	}

	/**
	 * Test method for {@link com.uned.optimizadorga.algoritmo.Generacion#ejecutar()}.
	 */
	@Test
	public void testEjecutar() {
		log.debug("Poblacion Inicial: " + p);
		g.ejecutar();
		Poblacion nuevaPoblacion = g.getNuevaPoblacion();
		log.debug("Poblacion Inicial tras la ejecucion: " + p);
		log.debug("Nueva Poblacion: " + nuevaPoblacion);
		nuevaPoblacion.calcularCostesPoblacion();
		log.debug("Nueva Poblacion tras calcular costes: " + nuevaPoblacion);
	}

}
