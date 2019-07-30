package genetica;

/**
 * Interfaz que define el comportamiento de un individuo representado en un algoritmo genético.
 * @param <A> tipo de datos de la aptitud (fitness) de este individuo.
 */
public interface Individuo<A extends Comparable> {
	
	/**
	 * Función de aptitud (fitness) de un algoritmo genético.
	 */
	void calcularAptitud();
	
	/**
	 * Regresa el valor de la aptitud (fitness) de este individuo.
	 * Para funcionar bien con la clase AlgoritmoGenetico, se espera que el mejor individuo tenga
	 * una aptitud a tal que a mayor o igual a' para toda a' aptitud de los demás individuos.
	 * @return la aptitud de este individuo.
	 */
	A getAptitud();
	
	/**
	 * Indica si este individuo es el óptimo, el individuo con las características deseadas.
	 * @return true si es óptimo.
	 */
	boolean esOptimo();
	
	/**
	 * Operador de recombinación de un algoritmo genético. El objeto que invoca este método es el
	 * primer padre.
	 * @param otro el segundo padre.
	 * @return el individuo que resulta de combinar los padres.
	 */
	Individuo recombinar(Individuo otro);
	
	/**
	 * Operador de mutación de un algoritmo genético.
	 * @param probabilidadMutacion la probabilidad de que este individuo sufra una mutación.
	 * @return true si este individuo sufrió una mutación.
	 */
	boolean mutar(double probabilidadMutacion);
	
}
