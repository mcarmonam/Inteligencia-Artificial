/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthwall;

/**
 *
 * @author mcfly
 */
public class LabyrinthWall {
    int height;
    int width;
    Cell[][] matrix;
    
    
    /**
     * Clase para modelar una celda de un laberinto
     * con valores para saber si ya fue visitada en que direciones se puede 
     * pasar a traves de ella
     * @author mcfly
     */
    public class Cell{
        boolean rigth;
        boolean down;
        boolean visitada;
        int x;
        int y;
        
        /** Constructor de una celda que inicializa con todas sus paredes
         * y con un valor de no visitado
         * @param x eje x de la posición de la celda en el laberinto
         * @param y eje y de la posición de la celda en el laberinto
         */
        Cell(int x, int y) {
            rigth = true;
            down = true;        
            visitada = false;
            this.x = x;
            this.y = y;
        }
    }
    /**
     * Constructor de un laberinto que inicializa todas las celdas
     * que lo contienen 
     * @param w ancho del laberinto
     * @param h largo del laberinto
     */
    public LabyrinthWall(int w, int h){
        height = h;
        width = w;
        matrix = new Cell[w][h];
        for(int i = 0; i < width ;i++){
            for(int j = 0; j < height ;j++){
                matrix[i][j] = new Cell(i,j);
            }
        }
    }
}
