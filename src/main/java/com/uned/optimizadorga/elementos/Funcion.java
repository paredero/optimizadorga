/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.List;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * @author fpb
 *
 */
public class Funcion {
	String expresion;
	
	public Funcion(String expresion) {
		super();
		this.expresion = expresion;
	}



	public double evaluate(List<Gen> listaParametros) {
		ExpressionBuilder eb = new ExpressionBuilder(expresion);
		eb.variables("pi");
		for (Gen parametro:listaParametros) {
			eb.variables(parametro.getNombre());
		}
		Expression e = eb.build();
		for (Gen parametro:listaParametros) {
			e.setVariable(parametro.getNombre(), parametro.getValor());
		}
		e.setVariable("pi", Math.PI);
		return e.evaluate();
	}
}
