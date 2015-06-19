/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

/**
 * @author fpb
 *
 */
public class Funcion {
	private static final String VARIABLE_NO_ASIGNADA1 = "The setVariable ";
	private static final String VARIABLE_NO_ASIGNADA2 = "has not been set";
//	private String expresion;
	private Expression expresion;
	
	public Funcion(String strExpr, Map<String, TipoGen> parametros) throws Exception {
		super();
		this.validarExpresion(strExpr);
//		this.expresion = expresion;
		ExpressionBuilder eb = new ExpressionBuilder(strExpr);
		eb.variables("pi");
		eb.variables("e");
		
		eb.variables(parametros.keySet());
		
		expresion = eb.build();
		expresion.setVariable("pi", Math.PI);
		expresion.setVariable("e", Math.E);
		
		ValidationResult resultadoValidacion = expresion.validate();
		if (!resultadoValidacion.isValid()) {
			Iterator<String> itErrores = resultadoValidacion.getErrors().iterator();
			while (itErrores.hasNext()) {
				String error = itErrores.next();
				if (!error.contains(VARIABLE_NO_ASIGNADA1) 
						&& !error.contains(VARIABLE_NO_ASIGNADA2)) {
					// El error variable no asignada es aceptable, pues solo asignare al evaluar
					throw new Exception(error);
				}
			}
		}
	}


	/**
	 * Valida inicialmente la expresion
	 * @param strExpr
	 * @throws Exception 
	 */
	private void validarExpresion(String strExpr) throws Exception {
		// 1 Valida los parentesis
		Stack<Character> stack = new Stack<Character>();
		char c;
		for (int i = 0; i<strExpr.length(); i++) {
			c = strExpr.charAt(i);
			if(c == '(') {
	            stack.push(c);
			} else if(c == ')') {
	            if(stack.empty()) {
	            	throw new Exception("parentesis no abierto");
	            } else if(stack.peek() == '('){
	            	stack.pop();
	            }
			}
		}

		if (!stack.empty()) {
			throw new Exception("parentesis no cerrado");
		}
	}



	public double evaluate(List<Gen> listaParametros) throws Exception{		
		for (Gen parametro:listaParametros) {
			expresion.setVariable(parametro.getNombre(), parametro.getValor());
		}
		return expresion.evaluate();
	}



	
}
