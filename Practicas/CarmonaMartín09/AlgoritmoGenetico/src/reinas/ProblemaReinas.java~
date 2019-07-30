package reinas;

import genetica.AlgoritmoGenetico;
import genetica.Individuo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Clase que soluciona el problema del tablero de ajedrez de NxN con N reinas que no se pueden
 * atacar entre sí.
 */
public class ProblemaReinas extends AlgoritmoGenetico {
	
	/* Máximo número de generaciones. No es elemento de la clase abstracta AlgoritmoGenetico porque
	 * no forzosamente debe ser criterio de terminación del algoritmo, en este caso sí lo es. */
	private int maxGeneraciones;
	
	/* Lista de los mejores individuos de cada generación, tal que está inicialmente vacío y el
	 * primer elemento es de la primera generación. */
	private LinkedList<Tablero> mejoresTableros;
	
	/**
	 * Crea una nueva instancia del problema de las reinas para solucionarlo.
	 * @param poblacion conjunto inicial de instancias de la clase Tablero.
	 * @param probabilidadMutacion probabilidad con la que se mutará un Tablero.
	 * @param maxGeneraciones el algoritmo genético terminará al encontrar un Tablero óptimo o
	 *                        alcanzar este número de generaciones.
	 */
	public ProblemaReinas(Collection<Tablero> poblacion, double probabilidadMutacion, int maxGeneraciones) {
		super(poblacion, probabilidadMutacion);
		this.maxGeneraciones = maxGeneraciones;
		mejoresTableros = new LinkedList<>();
		mejoresTableros.addAll(poblacion);
		Tablero t = mejoresTableros.getFirst();
		mejoresTableros.clear();
		mejoresTableros.add(t);
	}
	
	/**
	 * Selecciona un Tablero aleatorio con la técnica de selección proporcional de aptitud; cada
	 * Tablero tiene asignado un peso, dado por: f/s, donde f es la aptitud de este Tablero y s es
	 * la suma de las aptitudes de todos los Tableros.
	 * @return un individuo de la población.
	 */
	@Override
	public Individuo seleccionarIndividuo() {
		
		double totalAptitudes = 0;
		for (Individuo<Double> individuo: poblacion) {
			totalAptitudes += individuo.getAptitud();
		}
		
		NavigableMap<Double, Individuo> map = new TreeMap<>();
		double totalProbabilidades = 0;
		for (Individuo<Double> individuo: poblacion) {
			double probabilidad = individuo.getAptitud() / totalAptitudes;
			totalProbabilidades += probabilidad;
			map.put(totalProbabilidades, individuo);
		}
		
		return map.higherEntry(totalProbabilidades * (new Random()).nextDouble()).getValue();
		
	}
	
	/**
	 * El resultado del método seleccionarIndividuo() será el único elemento del elitismo.
	 * @return un Collection con un individuo aleatorio como elemento único.
	 */
	@Override
	public Collection<Individuo> eligirElitismo() {
		LinkedList<Individuo> elitismo = new LinkedList<>();
		elitismo.add(seleccionarIndividuo());
		return elitismo;
	}
	
	/**
	 * El algoritmo terminará al encontrar un Tablero óptimo o cuando
	 * numeroGeneraciones == maxGeneraciones.
	 * @return true si se cumplen las condiciones de terminación.
	 */
	@Override
	public boolean esTerminarAlgoritmo() {
		return (mejorIndividuo != null && mejorIndividuo.esOptimo()) ||
				numeroGeneraciones == maxGeneraciones;
	}
	
	/**
	 * Regresa una lista de los mejores Tableros de cada generación.
	 * @return una lista de Tableros.
	 */
	public LinkedList<Tablero> getMejoresTableros() {
		return mejoresTableros;
	}
	
	/**
	 * Mantiene una lista de los mejores Tableros de cada generación.
	 */
	@Override
	public void realizarAccion() {
		mejoresTableros.addLast((Tablero) mejorIndividuo);
	}
	
}
