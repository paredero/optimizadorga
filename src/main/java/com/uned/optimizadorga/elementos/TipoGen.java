package com.uned.optimizadorga.elementos;

public class TipoGen {
	private String nombre;	
	private double minimo;
	private double maximo;
	private int precision;
	
	
	/**
	 * @param nombre
	 * @param minimo
	 * @param maximo
	 * @param precision
	 */
	public TipoGen(String nombre, double minimo, double maximo, int precision) {
		super();
		this.nombre = nombre;
		this.minimo = minimo;
		this.maximo = maximo;
		this.precision = precision;
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
	
	
	
}
