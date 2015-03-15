package optimizadorga.test.algoritmo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.interfaces.AlgoritmoObserver;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoFinal;
import com.uned.optimizadorga.algoritmo.resultado.ResultadoParcial;
import com.uned.optimizadorga.elementos.Configuracion;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;

public class AlgoritmoTest implements AlgoritmoObserver {

	private Funcion funcionCoste;
	private Configuracion c;
	private Algoritmo a;
	private int notificacionesFin;
	private int notificacionesGeneracion;
	private int notificacionesEra;

	@Before
	public void setUp() throws Exception {
		String expresion = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		
		List<Gen> genesParametro = new ArrayList<Gen>();
		Gen x1 = new Gen("x1", -3.0, 12.1, 1);
		Gen x2 = new Gen("x2", 4.1, 5.8, 1);
		genesParametro.add(x1);
		genesParametro.add(x2);
		funcionCoste = new Funcion(expresion,genesParametro);
		c = Configuracion.crearConfiguracionBasica(3, 4,
				funcionCoste, genesParametro, 3, 0.5, 0.5);

		a = new Algoritmo(c);
	}


	@Test
	public void testRun() {
		a.registerObserver(this);
		a.run();
		assertEquals("Faltan notificaciones por eras", notificacionesEra,3);
		assertEquals("Faltan notificaciones por Generaciones", notificacionesGeneracion,12);
		assertEquals("Faltan notificaciones por fin", notificacionesFin,1);
	}

	@Test
	public void testRegisterObserver() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyEra() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyGeneracion() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyFin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConfiguracion() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGeneracion() {
		fail("Not yet implemented");
	}


	@Override
	public void updateEra(ResultadoParcial resultadoParcial) {
		notificacionesEra++;
	}


	@Override
	public void updateGeneracion(ResultadoParcial resultadoParcial) {
		notificacionesGeneracion++;
	}


	@Override
	public void updateFin(ResultadoFinal resultadoFinal) {
		notificacionesFin++;
	}

}
