/**
 * 
 */
package com.uned.optimizadorga.elementos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 * @author fpb
 *
 */
public class Gen {
	private String nombre;
	private double valor;
	private double maximo;
	private double minimo;
	private int precision;
	
	
	public Gen(String nombre, double maximo, double minimo, int precision) {
		super();
		this.nombre = nombre;
		this.maximo = maximo;
		this.minimo = minimo;
		this.precision = precision;
	}
	
	public Gen(Gen g) {
		super();
		this.nombre = g.getNombre();
		this.valor = g.getValor();
		this.maximo = g.getMaximo();
		this.minimo = g.getMinimo();
		this.precision = g.getPrecision();
	}

	/**
	 * Inicializa el campo valor con un numero real aleatorio comprendido entre
	 * el máximo y el mínimo con la precision que se ha pasado
	 */
	public void generarValorAleatorio() {
		double numeroAleatorio = minimo + (Math.random() * (maximo - minimo));		
		this.valor = formatear(numeroAleatorio);
	}
	
	/**
	 * Redondea un valor double a la precisión asignada
	 * @param valor
	 * @return
	 */
	private double formatear(double valor) {
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setGroupingUsed(Boolean.FALSE);
		df.setDecimalFormatSymbols(dfs);
		df.setMaximumFractionDigits(precision);
		String valorStr = df.format(valor);
		return Double.parseDouble(valorStr);
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}


	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}


	/**
	 * @return the maximo
	 */
	public double getMaximo() {
		return maximo;
	}


	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}


	/**
	 * @return the minimo
	 */
	public double getMinimo() {
		return minimo;
	}


	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}
	
	

	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + nombre + ", " + valor + "]";
	}


	
}
