/**
 * 
 */
package optimizadorga.util;

import java.util.ArrayList;
import java.util.List;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.algoritmo.selectores.SelectorRuleta;
import com.uned.optimizadorga.elementos.Cromosoma;
import com.uned.optimizadorga.elementos.Funcion;
import com.uned.optimizadorga.elementos.Gen;
import com.uned.optimizadorga.elementos.Poblacion;

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
	
	public static Poblacion crearPoblacionInicializada() {
		Gen parametro1 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 2);
		Gen parametro2 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 3);
		Gen parametro3 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 1);
		
		List<Gen> genesParametro1 = new ArrayList<Gen>();
		genesParametro1.add(parametro1);
		genesParametro1.add(parametro2);
		genesParametro1.add(parametro3);
		Cromosoma cromosoma1 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro1);
		
		Gen parametro4 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 6);
		Gen parametro5 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 4);
		Gen parametro6 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 5);
		List<Gen> genesParametro2 = new ArrayList<Gen>();
		genesParametro2.add(parametro4);
		genesParametro2.add(parametro5);
		genesParametro2.add(parametro6);
		Cromosoma cromosoma2 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro2);
		
		Gen parametro7 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 8);
		Gen parametro8 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 7);
		Gen parametro9 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 9);
		List<Gen> genesParametro3 = new ArrayList<Gen>();
		genesParametro3.add(parametro7);
		genesParametro3.add(parametro8);
		genesParametro3.add(parametro9);
		Cromosoma cromosoma3 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro3);
		
		Gen parametro10 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 10);
		Gen parametro11 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 11);
		Gen parametro12 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 12);
		List<Gen> genesParametro4 = new ArrayList<Gen>();
		genesParametro4.add(parametro10);
		genesParametro4.add(parametro11);
		genesParametro4.add(parametro12);
		Cromosoma cromosoma4 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro4);
		
		Gen parametro13 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 15);
		Gen parametro14 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 13);
		Gen parametro15 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 14);
		List<Gen> genesParametro5 = new ArrayList<Gen>();
		genesParametro5.add(parametro13);
		genesParametro5.add(parametro14);
		genesParametro5.add(parametro15);
		Cromosoma cromosoma5 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro5);
		
		List<Cromosoma> listaCromosomas = new ArrayList<Cromosoma>();
		listaCromosomas.add(cromosoma1);
		listaCromosomas.add(cromosoma2);
		listaCromosomas.add(cromosoma3);
		listaCromosomas.add(cromosoma4);
		listaCromosomas.add(cromosoma5);
		
		Poblacion p = new Poblacion();
		p.setTamanio(5);
		String expresion = "x+2y+3z";
		Funcion funcion = new Funcion(expresion);
		p.setFuncionCoste(funcion);
		p.setCromosomas(listaCromosomas);
		return p;
	}

	public static Algoritmo crearAlgoritmoInicializado() {
		Algoritmo a = new Algoritmo();
		String expresion = "x+2y+3z";
		Funcion funcion = new Funcion(expresion);
		a.setFuncion(funcion);
		Gen parametro1 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 2);
		Gen parametro2 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 3);
		Gen parametro3 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 1);
		
		List<Gen> genesParametro1 = new ArrayList<Gen>();
		genesParametro1.add(parametro1);
		genesParametro1.add(parametro2);
		genesParametro1.add(parametro3);
		a.setGenes(genesParametro1);
		a.setTamanioPoblacion(5);
		a.setMaxGens(5);
		a.setSelector(new SelectorRuleta());
		
		Cromosoma cromosoma1 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro1);
		
		Gen parametro4 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 6);
		Gen parametro5 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 4);
		Gen parametro6 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 5);
		List<Gen> genesParametro2 = new ArrayList<Gen>();
		genesParametro2.add(parametro4);
		genesParametro2.add(parametro5);
		genesParametro2.add(parametro6);
		Cromosoma cromosoma2 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro2);
		
		Gen parametro7 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 8);
		Gen parametro8 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 7);
		Gen parametro9 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 9);
		List<Gen> genesParametro3 = new ArrayList<Gen>();
		genesParametro3.add(parametro7);
		genesParametro3.add(parametro8);
		genesParametro3.add(parametro9);
		Cromosoma cromosoma3 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro3);
		
		Gen parametro10 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 10);
		Gen parametro11 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 11);
		Gen parametro12 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 12);
		List<Gen> genesParametro4 = new ArrayList<Gen>();
		genesParametro4.add(parametro10);
		genesParametro4.add(parametro11);
		genesParametro4.add(parametro12);
		Cromosoma cromosoma4 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro4);
		
		Gen parametro13 = CreadorObjetos.crearGenConValor("x",0, 100, 3, 15);
		Gen parametro14 = CreadorObjetos.crearGenConValor("y",100, 200, 3, 13);
		Gen parametro15 = CreadorObjetos.crearGenConValor("z",200, 300, 3, 14);
		List<Gen> genesParametro5 = new ArrayList<Gen>();
		genesParametro5.add(parametro13);
		genesParametro5.add(parametro14);
		genesParametro5.add(parametro15);
		Cromosoma cromosoma5 = CreadorObjetos.crearCromosomaConGenesInicializados(genesParametro5);
		
		List<Cromosoma> listaCromosomas = new ArrayList<Cromosoma>();
		listaCromosomas.add(cromosoma1);
		listaCromosomas.add(cromosoma2);
		listaCromosomas.add(cromosoma3);
		listaCromosomas.add(cromosoma4);
		listaCromosomas.add(cromosoma5);
		
		Poblacion p = new Poblacion();
		p.setTamanio(5);
		
		p.setFuncionCoste(funcion);
		p.setCromosomas(listaCromosomas);
		a.setPoblacion(p);
		return a;
	}
}
