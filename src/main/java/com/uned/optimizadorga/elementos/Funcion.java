/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.List;
import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * @author fpb
 *
 */
public class Funcion {
	String expresion;
	private Expression e;
	
	public Funcion(String expresion, Map<String, Gen> parametros) {
		super();
		this.expresion = expresion;
		ExpressionBuilder eb = new ExpressionBuilder(expresion);
		eb.variables("pi");
		eb.variables("e");
		
		eb.variables(parametros.keySet());
		
		e = eb.build();
		e.setVariable("pi", Math.PI);
		e.setVariable("e", Math.E);
	}



	public double evaluate(List<Gen> listaParametros) {		
		for (Gen parametro:listaParametros) {
			e.setVariable(parametro.getNombre(), parametro.getValor());
		}
		return e.evaluate();
	}
}
