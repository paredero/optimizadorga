/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.util.Iterator;
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
	private static final String VARIABLE_NO_ASIGNADA1 = "The setVariable ";
	private static final String VARIABLE_NO_ASIGNADA2 = "has not been set";
//	private String expresion;
	private Expression e;
	
	public Funcion(String expresion, Map<String, TipoGen> parametros) throws Exception {
		super();
//		this.expresion = expresion;
		ExpressionBuilder eb = new ExpressionBuilder(expresion);
		eb.variables("pi");
		eb.variables("e");
		
		eb.variables(parametros.keySet());
		
		e = eb.build();
		e.setVariable("pi", Math.PI);
		e.setVariable("e", Math.E);
		
		ValidationResult resultadoValidacion = e.validate();
		if (!resultadoValidacion.isValid()) {
			Iterator<String> itErrores = resultadoValidacion.getErrors().iterator();
			while (itErrores.hasNext()) {
				String error = itErrores.next();
				if (!error.contains(VARIABLE_NO_ASIGNADA1) 
						&& !error.contains(VARIABLE_NO_ASIGNADA2)) {
					// El error variable no asignada es aceptable, pues solo asignare al evaluar
					throw new Exception();
				}
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
