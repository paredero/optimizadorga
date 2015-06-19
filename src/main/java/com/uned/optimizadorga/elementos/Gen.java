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
	private double valor;	
	private TipoGen tipoGen;
	
	
	
	public Gen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gen(TipoGen tipoGen) {
		super();
		this.tipoGen = tipoGen;
		
	}
	
	public Gen(Gen g) {
		super();
		this.tipoGen = g.getTipoGen();
		this.valor = g.getValor();
	}


	/**
	 * Inicializa el campo valor con un numero real aleatorio comprendido entre
	 * el máximo y el mínimo con la precision que se ha pasado
	 */
	public void generarValorAleatorio() {
		double numeroAleatorio = tipoGen.getMinimo() + (Math.random() * (tipoGen.getMaximo() - tipoGen.getMinimo()));		
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
		df.setMaximumFractionDigits(tipoGen.getPrecision());
		String valorStr = df.format(valor);
		return Double.parseDouble(valorStr);
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
	 * @return the tipoGen
	 */
	public TipoGen getTipoGen() {
		return tipoGen;
	}

	/**
	 * @param tipoGen the tipoGen to set
	 */
	public void setTipoGen(TipoGen tipoGen) {
		this.tipoGen = tipoGen;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + tipoGen.getNombre() + ", " + valor + "]";
	}

}
