package optimizadorga.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.selectores.SelectorRuleta;
import com.uned.optimizadorga.elementos.Gen;

public class AlgoritmoTest {
	Algoritmo algoritmo;

	@Before
	public void setUp() throws Exception {
		algoritmo = new Algoritmo();
		Gen x1 = new Gen("x1",-3.0, 12.1, 1);
		Gen x2 = new Gen("x2",4.1, 5.8, 1);
		List<Gen> genes = new ArrayList<Gen>();
		genes.add(x1);
		genes.add(x2);
		
		String funcionCaso1 = "21.5 + x1 * sin(4 * pi * x1) + x2 * sin(4 * pi * x2)";
		algoritmo.setFuncion(funcionCaso1);
		algoritmo.setGenes(genes);
		algoritmo.setTamanioPoblacion(5);
		algoritmo.setMaxGens(5);
		algoritmo.inicializarPoblacion();
		algoritmo.setSelector(new SelectorRuleta());
		System.out.println(algoritmo.getPoblacion());
	}

	@Test
	public void testEvaluar() {
		algoritmo.evaluar();
		System.out.println(algoritmo.getPoblacion());
	}
	
	@Test
	public void testMantenerMejor() {
		algoritmo.evaluar();
		System.out.println("*************************************************");
		System.out.println("Poblacion Evaluada");
		System.out.println(algoritmo.getPoblacion());
		algoritmo.obtenerMejor();
		System.out.println("*************************************************");
		System.out.println("Poblacion con el mejor a la derecha");
		System.out.println(algoritmo.getPoblacion());
	}
	
	@Test
	public void testBucleEjecucion() {
		algoritmo.evaluar();
		algoritmo.obtenerMejor();
		algoritmo.ejecutarBucle();
	}

}
