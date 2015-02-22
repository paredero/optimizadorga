/**
 * 
 */
package optimizadorga.util;

import java.util.List;

import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Gen;

/**
 * @author fpb
 *
 */
public class CreadorObjetos {
	public static Gen crearGenConValor(String nombre, double maximo,
			double minimo, int precision, double valor) {
		Gen g = new Gen(nombre, maximo, minimo, precision);
		g.setValor(valor);
		return g;
	}
	
	public static Cromosoma crearCromosomaConGenesInicializados(List<Gen> genes) {
		Cromosoma c = new Cromosoma();
		c.setGenes(genes);
		return c;
	}
}
