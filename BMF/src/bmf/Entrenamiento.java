/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmf;

import static bmf.Interfaz.DIM;
import static bmf.Interfaz.celdas;
import static bmf.Interfaz.iteraciones;
import static bmf.Interfaz.matrizTerreno;
import static bmf.Interfaz.personaje;
import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Francisco Javier Rubio Ibañez
 */
public class Entrenamiento {

    private static int fila, columna;
    private static ArrayList<Recorrido> grafo = new ArrayList<>();
    private static ArrayList<Recorrido> grafoAux = new ArrayList<>();
    private static double total = 0;
    private static int ponderacion = 1050200300;

    //private Interfaz in= new Interfaz();
    public void entrenar() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                try {
                    //To change body of generated methods, choose Tools | Templates.
                    moverEnElMapa();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Entrenamiento.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    private void moverEnElMapa() throws InterruptedException {

        ponderacion = 1050200300;
        total = 0;
        grafo.clear();
        grafoAux.clear();
       

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (matrizTerreno[i][j] == 31) {
                    fila = i;
                    columna = j;
                    break;
                }
            }
        }

        for (int iter = 0; iter < iteraciones; iter++) {

            moverPersonaje(); //mueve el personaje aleatoriamente
            celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
            Thread.sleep(10);
            pintarMapa(fila, columna); //ayuda a la animacion del personaje

        }
        
       for(int j=0; j<grafo.size(); j++){
           
           celdas[grafo.get(j).getFila()][grafo.get(j).getColumna()].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
           Thread.sleep(400);
           pintarMapa(grafo.get(j).getFila(), grafo.get(j).getColumna());
           
           
       }

    }
    /*
    
     // -- arriba -> abajo ++ fila
     // -- izquieda->derecha ++ columna
    
     */

    private void moverPersonaje() {
        switch (numeroRandom()) {
            case 0:

                superiorIzquierda();
                contarPonderaciones();
                break;
            case 1:
                arriba();
                contarPonderaciones();
                break;
            case 2:

                superiorDerecha();
                contarPonderaciones();
                break;
            case 3:
                derecha();
                contarPonderaciones();
                break;
            case 4:
                inferiorDerecha();
                contarPonderaciones();
                break;
            case 5:
                abajo();
                contarPonderaciones();
                break;
            case 6:
                inferiorIzquierda();
                contarPonderaciones();
                break;
            case 7:
                izquierda();
                contarPonderaciones();
                break;

        }
    }

    private void derecha() {
        if (validarDerecha()) {

            if (validarHayMuro(fila, columna + 1)) {
                columna++;

            }

        }
    }

    private void izquierda() {

        if (validarIzquierda()) {

            if (validarHayMuro(fila, columna - 1)) {
                columna--;
            }

        }

    }

    private void arriba() {

        if (validarArriba()) {

            if (validarHayMuro(fila - 1, columna)) {
                fila--;
            }

        }

    }

    private void abajo() {

        if (validarAbajo()) {

            if (validarHayMuro(fila + 1, columna)) {
                fila++;
            }

        }

    }

    private void inferiorIzquierda() {

        if (validarAbajo()) {

            if (validarIzquierda()) {

                if (validarHayMuro(fila + 1, columna - 1)) {
                    fila++;
                    columna--;

                }

            }

        }

    }

    private void inferiorDerecha() {

        if (validarAbajo()) {

            if (validarDerecha()) {

                if (validarHayMuro(fila + 1, columna + 1)) {

                    fila++;
                    columna++;

                }
            }
        }

    }

    private void superiorDerecha() {

        if (validarDerecha()) {

            if (validarArriba()) {

                if (validarHayMuro(fila - 1, columna + 1)) {
                    fila--;
                    columna++;

                }

            }

        }

    }

    private void superiorIzquierda() {

        if (validarIzquierda()) {

            if (validarArriba()) {

                if (validarHayMuro(fila - 1, columna - 1)) {

                    fila--;
                    columna--;

                }

            }

        }

    }

    private boolean validarHayMuro(int f, int c) {

        if (matrizTerreno[f][c] == 4) {
            return false;
        }
        return true;

    }

    private boolean validarIzquierda() {

        if ((columna - 1) < 0) {
            return false;
        }
        return true;

    }

    private boolean validarDerecha() {

        if ((columna + 1) >= DIM) {
            return false;
        }
        return true;

    }

    private boolean validarArriba() {
        if ((fila - 1) < 0) {
            return false;
        }
        return true;

    }

    private boolean validarAbajo() {

        if ((fila + 1) >= DIM) {
            return false;
        }
        return true;

    }

    private void pintarMapa(int fila, int columna) {

        celdas[fila][columna].setIcon(null);

        switch (matrizTerreno[fila][columna]) {

            case 0:
                celdas[fila][columna].setIcon(null);
                break;

            case 1:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/agua.png")));
                break;
            case 2:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/barranco.png")));
                break;
            case 3:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/montana.png")));
                break;
            case 4:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/muro.png")));
                break;

            case 31:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
                break;

            case 30:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/casa.png")));
                break;

        }

    }

    private int numeroRandom() {

        int n = (int) (Math.random() * 2);
        int n2 = (int) (Math.random() * 2);
        int n3 = (int) (Math.random() * 2);

        String c = Integer.toString(n) + Integer.toString(n2) + Integer.toString(n3);
        int numero = Integer.parseInt(c, 2);

        return numero;

    }

    private void contarPonderaciones() {

        if (personaje.equals("Mombo")) {
            switch (matrizTerreno[fila][columna]) {
                case 0:
                    total = 1.0;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);

                    break;
                case 1://agua
                    total = 3.0;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 1.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 2.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);

                    break;
                case 30:
                    //casa
                     agregarGrafoAux(fila, columna, ponderacion, 0);
                    compararRutas();
                    ponderacion = 1050200300;
                    total = 0;
                    for (int i = 0; i < DIM; i++) {
                        for (int j = 0; j < DIM; j++) {
                            if (matrizTerreno[i][j] == 31) {
                                fila = i;
                                columna = j;
                                break;
                            }
                        }
                    }

                    break;

            }
        } else if (personaje.equals("Lucas")) {
            switch (matrizTerreno[fila][columna]) {
                case 0:
                    total = 0.3;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 1://agua

                    total = 1.0;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 2.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 1.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 30:

                    compararRutas();
                    ponderacion = 1050200300;
                    total = 0;
                    for (int i = 0; i < DIM; i++) {
                        for (int j = 0; j < DIM; j++) {
                            if (matrizTerreno[i][j] == 31) {
                                fila = i;
                                columna = j;
                                break;
                            }
                        }
                    }
                    //casa
                    break;

            }
        } else if (personaje.equals("Pirolo")) {
            switch (matrizTerreno[fila][columna]) {
                case 0:
                    total = 1.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 1://agua
                    total = 2.5;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 1.0;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 0.3;
                    ponderacion++;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 30:
                    //casa
                    compararRutas();

                    ponderacion = 1050200300;
                    total = 0;
                    for (int i = 0; i < DIM; i++) {
                        for (int j = 0; j < DIM; j++) {
                            if (matrizTerreno[i][j] == 31) {
                                fila = i;
                                columna = j;
                                break;
                            }
                        }
                    }

                    break;

            }
        }

    }

    private void agregarGrafoAux(int fil, int col, int pon, double es) {
        
        //si ya agrego
        //if((!visitoNodoPrimero(fil)) && (!visitoNodoSegundo(col))){
            grafoAux.add(new Recorrido(fil, col, pon, es));
       // }
        
        
        
    }
    
    
    private boolean visitoNodoPrimero(int fil){
        
        for(int i=0; i<grafoAux.size(); i++){
            
            if(fil==grafoAux.get(i).getFila()){
                return true;
            }
            
        }
        
        return false;
        
    }
    
    private boolean visitoNodoSegundo(int col){
        for(int i=0; i<grafoAux.size(); i++){
            
            if(col==grafoAux.get(i).getColumna()){
                return true;
            }
            
        }
        
        return false;
        
    }

    private void compararRutas() {

        if (!grafo.isEmpty()) {
           

            if (esfuerzoGrafoAux() < esfuerzoGrafo()) {

                grafo.clear();
                pasarDatosGrafo();
                
            }

            //borrar
        } else {
            
            pasarDatosGrafo();
        }

    }

    private void pasarDatosGrafo() {

        for (int i = 0; i < grafoAux.size(); i++) {

            grafo.add(new Recorrido(grafoAux.get(i).getFila(),
                    grafoAux.get(i).getColumna(), grafoAux.get(i).getPonderacion(), grafoAux.get(i).getEsfuerzo()));

        }

        grafoAux.clear();

    }

    private double esfuerzoGrafo() {
        double c = 0;

        for (int i = 0; i < grafo.size(); i++) {
            c += grafo.get(i).getEsfuerzo();

        }

        return c;

    }

    private double esfuerzoGrafoAux() {
        double c = 0;

        for (int i = 0; i < grafoAux.size(); i++) {

            c += grafoAux.get(i).getEsfuerzo();

        }
        return c;

    }
    


}
