/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.List;
import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

/**
 * @author fpb
 *
 */
public class Funcion {
	private static final String VARIABLE_NO_ASIGNADA = "The setVariable 'x' has not been set";
	String expresion;
	private Expression e;
	
	public Funcion(String expresion, Map<String, TipoGen> parametros) throws Exception {
		super();
		this.expresion = expresion;
		ExpressionBuilder eb = new ExpressionBuilder(expresion);
		eb.variables("pi");
		eb.variables("e");
		
		eb.variables(parametros.keySet());
		
		e = eb.build();
		e.setVariable("pi", Math.PI);
		e.setVariable("e", Math.E);
		ValidationResult resultadoValidacion = e.validate();
		if (!resultadoValidacion.isValid()) {
			if (resultadoValidacion.getErrors().size() == 1 
					&& resultadoValidacion.getErrors().get(0).equals(VARIABLE_NO_ASIGNADA)) {
				// OK, ya sé que no he asignado valor a la variable aún
			} else {
				throw new Exception();
			}
		}
	}



	public double evaluate(List<Gen> listaParametros) {		
		for (Gen parametro:listaParametros) {
			e.setVariable(parametro.getNombre(), parametro.getValor());
		}
		return e.evaluate();
	}
}
