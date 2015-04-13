/**
 * 
 */
package optimizadorga.test.algoritmo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Era;
import com.uned.optimizadorga.algoritmo.Generacion;
import com.uned.optimizadorga.algoritmo.interfaces.EraObserver;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Poblacion;
import com.uned.optimizadorga.elementos.TipoGen;

/**
 * @author fpb
 *
 */
public class EraTest implements EraObserver {
	private static final Logger log = Logger.getLogger(EraTest.class);
	private Generacion g;
	private Poblacion p;
	private Era e;
	private Configuracion c;
	private Funcion funcionCoste;
	private int numeroActualizaciones;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Map<String, TipoGen> genesParametro = new HashMap<String, TipoGen>();
		genesParametro.put("x1", new TipoGen("x1", -3.0, 12.1, 1));
		genesParametro.put("x2", new TipoGen("x2", 4.1, 5.8, 1));
//		p = Poblacion.generarPoblacionInicializada(5, genesParametro);

		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		funcionCoste = new Funcion(expresion, genesParametro);

		c = Configuracion.crearConfiguracion(1, 2,
				funcionCoste, genesParametro, 3, 0.5, 0.5);
//		p.setFuncionCoste(funcionCoste);
//		p.calcularCostesPoblacion();
//		g = new Generacion(p, c);
	}


	/**
	 * Test method for {@link com.uned.optimizadorga.algoritmo.Era#ejecutar()}.
	 */
	@Test
	public void testEjecutar() {
		Era era = new Era();
		era.setConfiguracion(c);
		era.registerObserver(this);
		era.ejecutar();
		assertEquals("No se han recibido las notificaciones necesarias",
				c.getMaxGens(), numeroActualizaciones);
	}

	/**
	 * Deben recibirse tantas actualizaciones como generaciones se pasan en configuracion
	 * @param resultadoParcial
	 */
	@Override
	public void updateGeneracion(Generacion resultadoParcial) {
		numeroActualizaciones++;
		log.debug("Recibe actualizacion de la generacion " + numeroActualizaciones);
	}

}