package genetica;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Clase que modela un algoritmo genético y lo ejecuta.
 */
public abstract class AlgoritmoGenetico {
	
	/* Población actual de este problema. */
	protected Collection<? extends Individuo> poblacion;
	
	/* Probabilidad con la que se mutará un Individuo. */
	protected double probabilidadMutacion;
	
	/* El individuo de la población con el mayor valor de aptitud. */
	protected Individuo mejorIndividuo;
	
	/* El número de generaciones, o el número de iteraciones que ha hecho este algoritmo. */
	protected long numeroGeneraciones;
	
	/**
	 * Proceso de selección de un algoritmo genético.
	 * @return individuo que después será recombinado con otro.
	 */
	public abstract Individuo seleccionarIndividuo();
	
	/**
	 * Regresa una colección de individuos que forman parte del elitismo de la generación actual.
	 * @return el elitismo de la generación actual.
	 */
	public abstract Collection<Individuo> eligirElitismo();
	
	/**
	 * Indica cuándo terminar el algoritmo.
	 * @return true para terminar el algoritmo.
	 */
	public abstract boolean esTerminarAlgoritmo();
	
	/**
	 * Crea una nueva instancia de este algoritmo con los parámetros dados.
	 * @param poblacion conjunto inicial de instancias de la interfaz Individuo.
	 * @param probabilidadMutacion probabilidad con la que se mutará un Individuo.
	 */
	public AlgoritmoGenetico(Collection<? extends Individuo> poblacion, double probabilidadMutacion) {
		this.poblacion = poblacion;
		this.probabilidadMutacion = probabilidadMutacion;
	}
	
	/**
	 * Regresa el mejor individuo de la población o null si no ha sido asignado, esto último sólo
	 * puede suceder si no se ha invocado el método ejecutarAlgoritmo().
	 * @return el mejor individuo de la población actual.
	 */
	public Individuo getMejorIndividuo() {
		return mejorIndividuo;
	}
	
	/**
	 * Acción opcional a realizarse después de cada iteración del algoritmo. Por default no hace nada.
	 */
	public void realizarAccion() {}
	
	/**
	 * Ejecuta este AlgoritmoGenetico. Puede ser sobreescrito pero en general, todos se resuelven de
	 * esta manera.
	 */
	public void ejecutarAlgoritmo() {
		
		// Se empieza calculando la aptitud (fitness) de cada individuo de la población.
		for (Individuo individuo: poblacion) {
			individuo.calcularAptitud();
		}
		
		// Mientras no se cumplan las condiciones de terminación definidas por el usuario de esta
		// clase.
		while (!esTerminarAlgoritmo()) {
			
			// Se crea la nueva generación y se agrega el elitismo de la generación anterior.
			LinkedList<Individuo> nuevaPoblacion = new LinkedList<>();
			nuevaPoblacion.addAll(eligirElitismo());
			
			// Mientras el tamaño de las generaciones sea distinta.
			int tamanioPoblacion = poblacion.size();
			while (nuevaPoblacion.size() < tamanioPoblacion) {
				
				// Se escogen dos individuos, se combinan, el hijo posiblemente se muta, y el hijo
				// se agrega a la nueva generación.
				Individuo individuo1 = seleccionarIndividuo();
				Individuo individuo2 = seleccionarIndividuo();
				Individuo hijo = individuo1.recombinar(individuo2);
				hijo.mutar(probabilidadMutacion);
				nuevaPoblacion.add(hijo);
				
			}
			
			// Se calcula la aptitud de cada individuo de la nueva generación y se determina el
			// mejor individuo.
			poblacion = nuevaPoblacion;
			mejorIndividuo = nuevaPoblacion.get(0);
			mejorIndividuo.calcularAptitud();
			for (Individuo individuo: poblacion) {
				individuo.calcularAptitud();
				if (individuo.getAptitud().compareTo(mejorIndividuo.getAptitud()) > 0) {
					mejorIndividuo = individuo;
				}
			}
			
			realizarAccion();
			numeroGeneraciones++;
			
		}
		
	}
	
}
