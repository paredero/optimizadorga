package optimizadorga.test.algoritmo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Test para calcular valores maximos mediante algoritmo de búsqueda en todo el espacio de soluciones
 * @author fjgarcia
 *
 */
public class Caso1Test {
	class Cromosoma {
		double x1;
		double x2;
		public Cromosoma(double x1, double x2) {
			super();
			this.x1 = x1;
			this.x2 = x2;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(x1);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(x2);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cromosoma other = (Cromosoma) obj;
			if (Double.doubleToLongBits(x1) != Double.doubleToLongBits(other.x1))
				return false;
			if (Double.doubleToLongBits(x2) != Double.doubleToLongBits(other.x2))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Cromosoma [x1=" + x1 + ", x2=" + x2 + "]";
		}
		
	}
	
	@Test
	public void test() {
		
		Date tiempoInicial = new Date();
		double x1 = -3.0;
		double x2 = 4.1;
		double max = -1000.0;
		
		List<Cromosoma> listaValores = new ArrayList<Cromosoma>();
		while (x1 <=5.1) {
			x2 = 4.20;
			while (x2 <= 5.8) {
				double valor = 21.5+x1* Math.sin(4*Math.PI*x1) +x2*Math.sin(4*Math.PI*x2);
				if (valor > max) {
					max = valor;
					listaValores = new ArrayList<Cromosoma>();
					listaValores.add(new Cromosoma(x1,x2));
				} else if (valor == max) {
					listaValores.add(new Cromosoma(x1,x2));
				}
				x2 = x2 + 0.1;						
			}
			x1+=0.1;
		}
		double segundos = (new Date().getTime() - tiempoInicial.getTime())/1000;
		System.out.println("Fin caso 1: " + segundos + " segundos");
		
		System.out.println("Mejores cromosomas por busqueda "+listaValores);
		System.out.println("Mejor valor posible = "+max);
		x1 = 5.1; x2 = 5.6;
		double valor = 21.5+x1* Math.sin(4*Math.PI*x1) +x2*Math.sin(4*Math.PI*x2);
		System.out.println(valor);
	}

	
	@Test
	public void test3() {
		
		Date tiempoInicial = new Date();
		double x1 = -5.0;
		double x2 = -5.0;
		double max = -1000.0;
		
		List<Cromosoma> listaValores = new ArrayList<Cromosoma>();
		while (x1 <=5) {
			x2 = -5;
			while (x2 <= 5) {
				double valor = -20 * Math.pow(Math.E, (-0.2 * Math.sqrt((Math.pow(x1, 2) + Math.pow(x2, 2)) / 2)))
						- Math.pow(Math.E, ((Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2))) / 2) + 20
						+ Math.E;
				if (valor > max) {
					max = valor;
					listaValores = new ArrayList<Cromosoma>();
					listaValores.add(new Cromosoma(x1,x2));
				} else if (valor == max) {
					listaValores.add(new Cromosoma(x1,x2));
				}
				x2 = x2 + 0.1;						
			}
			x1+=0.1;
		}
		double segundos = (new Date().getTime() - tiempoInicial.getTime())/1000;
		System.out.println("Fin caso 2: " + segundos + " segundos");
		
		System.out.println("Mejores cromosomas por busqueda "+listaValores);
		System.out.println("Mejor valor posible = "+max);
		x1 = -4.5;
		x2 = -4.8;
		double valor =  -20 * Math.pow(Math.E, (-0.2 * Math.sqrt((Math.pow(x1, 2) + Math.pow(x2, 2)) / 2)))
				- Math.pow(Math.E, ((Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2))) / 2) + 20
				+ Math.E;
		System.out.println(valor);
	}
}
