package reinas;

import processing.core.PApplet;

import java.util.LinkedList;

/**
 * Programa de Processing para mostrar una solución al problema de reinas, resuelto mediante
 * algoritmos genéticos.
 */
public class VisualizacionProblemaReinas extends PApplet {
	
	/* Tamaño (NxN) del tablero de ajedrez. */
	private static final int N = 8;
	
	/* Longitud de los cuadrados del tablero. */
	private static final float LONGITUD_DE_CUADRADO = 70;
	
	/* Anchura de la ventana principal. */
	private static final int ANCHO = (int) ((N + 2) * LONGITUD_DE_CUADRADO);
	
	/* Altura de la ventana principal. */
	private static final int ALTO = (int) ((N + 2) * LONGITUD_DE_CUADRADO);
	
	/* Tamaño de la población. */
	private static final int TAMANIO_POBLACION = 50;
	
	/* Probabilidad con la que se mutará un Tablero. */
	private static final double PROBABILIDAD_MUTACION = 0.2;
	
	/* Máximo número de generaciones */
	private static final int MAX_GENERACIONES = 1000;
	
	/* Número de generaciones a visualizar. */
	private static final int NUM_GENERACIONES_VISUALIZAR = 20;
	
	/* La instancia del problema. */
	private ProblemaReinas problemaReinas;
	
	/* El número de generación que se está viendo. */
	private int eleccion;
	
	/**
	 * Ejecuta este programa de Processing.
	 * @param args parámetro no usado.
	 */
	public static void main(String[] args) {
		PApplet.main("reinas.VisualizacionProblemaReinas");
	}
	
	/**
	 * Según la documentación, se debe sobreescribir este método para invocar size(), si es que no
	 * se está usando el PDE.
	 */
	@Override
	public void settings() {
		size(ANCHO, ALTO);
	}
	
	/**
	 * Método invocado una sola vez cuando se inicia el programa.
	 * Inicializa la matriz de Celdas, fija el frame rate y fija el color de fondo.
	 */
	@Override
	public void setup() {
		LinkedList<Tablero> poblacion = new LinkedList<>();
		for (int i = 0; i < TAMANIO_POBLACION; i++) {
			poblacion.add(new Tablero(N));
		}
		problemaReinas = new ProblemaReinas(
				poblacion, PROBABILIDAD_MUTACION, MAX_GENERACIONES);
		problemaReinas.ejecutarAlgoritmo();
		background(255, 255, 255);
		noLoop();
	}
	
	/**
	 * Método invocado inmediatamente después de setup() contínuamente hasta que se detiene el
	 * programa o se invoca noLoop().
	 */
	@Override
	public void draw() {
		
		float mitad = LONGITUD_DE_CUADRADO / 2;
		stroke(255, 255, 255);
		fill(255, 255, 255);
		rect(0, 0, LONGITUD_DE_CUADRADO * N, LONGITUD_DE_CUADRADO);
		fill(0, 0, 0);
		text("Generación " + eleccion + " de " + (problemaReinas.getMejoresTableros().size() - 1),
				LONGITUD_DE_CUADRADO,
				LONGITUD_DE_CUADRADO - mitad);
		
		for (int i = 0; i < N; i++) {
			
			boolean esPar = i % 2 == 0;
			
			for (int j = 0; j < N; j++) {
				
				if (esPar) {
					fill(200, 200, 200);
					esPar = false;
				} else {
					fill(0, 0, 0);
					esPar = true;
				}
				
				float x = LONGITUD_DE_CUADRADO * (j + 1);
				float y = LONGITUD_DE_CUADRADO * (i + 1);
				
				stroke(0, 0, 0);
				rect(x, y, LONGITUD_DE_CUADRADO, LONGITUD_DE_CUADRADO);
				
				if (problemaReinas.getMejoresTableros().get(eleccion).getPosiciones()[j] == i) {
					stroke(200, 0, 0);
					fill(200, 0, 0);
					ellipse(x + mitad, y + mitad, mitad, mitad);
				}
				
			}
		}
		
	}
	
	/**
	 * Indica que se desea avanzar a la siguiente generación.
	 */
	@Override
	public void mouseClicked() {
		eleccion += MAX_GENERACIONES / NUM_GENERACIONES_VISUALIZAR;
		eleccion = (eleccion >= problemaReinas.getMejoresTableros().size()) ?
				problemaReinas.getMejoresTableros().size() - 1 : eleccion;
		redraw();
	}
	
}
