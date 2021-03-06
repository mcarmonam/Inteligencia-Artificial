package reinas;

import genetica.Individuo;

import java.util.Random;

/**
 * Clase que representa un tablero de ajedrez con exactamente una reina por columna.
 */
public class Tablero implements Individuo<Double> {
	
	/* La aptitud (fitness) de este Tablero. */
	private Double aptitud;
	
	/* El arreglo de posiciones. */
	private int[] posiciones;
	
	/* Para generar enteros y flotantes pseudo-aleatorios. */
	private Random random;
	
	/**
	 * Crea un nuevo Tablero con el arreglo dado.
	 * @param posiciones la representación del tablero.
	 */
	public Tablero(int[] posiciones) {
		random = new Random();
		this.posiciones = posiciones;
	}
	
	/**
	 * Crea un nuevo Tablero con un arreglo de tamaño n, inicializado con valores aleatorios entre
	 * cero (inclusivo) y n (exclusivo).
	 * @param n tamaño del arreglo.
	 */
	public Tablero(int n) {
		random = new Random();
		posiciones = new int[n];
		for (int i = 0; i < posiciones.length; i++) {
			posiciones[i] = random.nextInt(posiciones.length);
		}
	}
	
	/**
	 * Regresa el arreglo que representa en donde se ubican las reinas en el Tablero.
	 * @return el arreglo de posiciones.
	 */
	public int[] getPosiciones() {
		return posiciones;
	}
	
	/**
	 * La aptitud de un Tablero es inversamente proporcional a Z, el número de posibles ataques entre
	 * reinas. Este método asigna 1/(1+Z) como la aptitud de este individuo.
	 */
	@Override
	public void calcularAptitud() {
		
		int ataquesPosibles = 0;
		
		for (int i = 0; i < posiciones.length; i++) {			
			// Observación: Los posibles ataques por estar en la misma columna son imposibles por diseño.
			// Calcula para la reina i, los posibles ataques por filas y en diagonal
			for (int j = i + 1; j < posiciones.length; j++) {
				int distanciaHorizontal = j - i;
				int distanciaVertical = posiciones[j] - posiciones[i];
				distanciaVertical = (distanciaVertical < 0) ? -distanciaVertical : distanciaVertical;
				if (distanciaVertical == 0 || distanciaVertical == distanciaHorizontal) {
					ataquesPosibles++;
				}
			}
			
		}
		
		aptitud = 1.0 / (1 + ataquesPosibles);
		
	}
	
	/**
	 * Regresa el valor de la aptitud (fitness) de este Tablero, un valor entre cero (exclusivo) y
	 * uno (inclusivo).
	 * @return la aptitud de este Tablero.
	 */
	@Override
	public Double getAptitud() {
		return aptitud;
	}
	
	/**
	 * Indica si este Tablero es óptimo, es decir, si tiene un valor de aptitud de uno.
	 * @return true si este Tablero es óptimo.
	 */
	@Override
	public boolean esOptimo() {
		return aptitud == 1;
	}
	
	/**
	 * Cruza dos padres para obtener el hijo.
	 * @param otro el segundo padre.
	 * @return el individuo que resulta de combinar los padres.
	 */
	@Override
	public Individuo recombinar(Individuo otro) {
		int[] posicionesHijo = new int[posiciones.length];
		int corte = random.nextInt(posiciones.length);
		for (int i = 0; i < posiciones.length; i++) {
			posicionesHijo[i] = (i < corte) ? posiciones[i] : ((Tablero) otro).getPosiciones()[i];
		}
		return new Tablero(posicionesHijo);
	}
	
	/**
	 * Altera el valor de cada posicion descrita en el arreglo con la probabilidad dada. Si se
	 * determina que alguna posición de este Tablero sufrirá la mutación, está garantizado que el
	 * nuevo valor será diferente al primero.
	 * El nuevo valor de algún elemento será un entero entre cero (inclusivo) y posiciones.length
	 * (exclusivo).
	 * @param probabilidadMutacion la probabilidad de que este Tablero sufra una mutación.
	 * @return true si este Tablero sufrió una mutación.
	 */
	@Override
	public boolean mutar(double probabilidadMutacion) {
		if (probabilidadMutacion < 0 || probabilidadMutacion > 1) {
			return false;
		}
		boolean mutado = false;
		for (int i = 0; i < posiciones.length; i++) {
			if (random.nextDouble() < probabilidadMutacion) {
				int nuevoValor;
				while ((nuevoValor = random.nextInt(posiciones.length)) == posiciones[i]);
				posiciones[i] = nuevoValor;
				mutado = true;
			}
		}
		return mutado;
	}
	
}
