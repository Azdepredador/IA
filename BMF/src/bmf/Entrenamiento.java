/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmf;

import Grafo.DijkstraAlgorithm;
import Grafo.Edge;
import Grafo.Graph;
import Grafo.Vertex;
import static bmf.Interfaz.DIM;
import static bmf.Interfaz.celdas;
import static bmf.Interfaz.iteraciones;
import static bmf.Interfaz.matrizTerreno;
import static bmf.Interfaz.personaje;
import static bmf.Interfaz.keyESC;
import static bmf.Interfaz.keya;
import static bmf.Interfaz.keyd;
import static bmf.Interfaz.keye;
import static bmf.Interfaz.keyq;
import static bmf.Interfaz.keys;
import static bmf.Interfaz.keyw;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Francisco Javier Rubio Ibañez
 *
 * Ponderacion Poner etiquetas Crear grafo Conexiones Dkjistra movimiento
 *
 */
public class Entrenamiento {

    private static int fila, columna;
    private static ArrayList<Recorrido> grafo = new ArrayList<>();
    private static ArrayList<Recorrido> grafoAux = new ArrayList<>();

    private List<Vertex> nodes;
    private List<Edge> edges;

    public ArrayList<Integer> nodoInicio = new ArrayList<>();
    public ArrayList<Integer> nodoFin = new ArrayList<>();
    public ArrayList<Integer> nodoAux = new ArrayList<>();
    public ArrayList<Integer> puntos = new ArrayList<>();
    public ArrayList<Double> costos = new ArrayList<>();

    private int nodos = 0;

    private static double total = 0;
    private static double ponderacion = 1050200300;
    private static boolean[][] recorrido = new boolean[DIM][DIM];


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

        nodos = 0;

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (matrizTerreno[i][j] == 31) {
                    fila = i;
                    columna = j;
                    celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
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

        //System.out.println("Numero de nodos "+aux);
        for (int j = 0; j < grafo.size(); j++) {

            grafo.get(j).setId(nodos);
            nodos++;

            if (j == 0) {
                nodoAux.add(grafo.get(j).getId());
            }

            if (grafo.size() - 1 == j) {
                nodoAux.add(grafo.get(j).getId());
            } else {
                nodoAux.add(grafo.get(j).getId());
                nodoAux.add(grafo.get(j).getId());

            }

            costos.add(grafo.get(j).getEsfuerzo());

            /*celdas[grafo.get(j).getFila()][grafo.get(j).getColumna()].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
             Thread.sleep(1000);
             pintarMapa(grafo.get(j).getFila(), grafo.get(j).getColumna());*/
        }

        for (int i = 0; i < nodoAux.size(); i++) {
            if (i % 2 == 0) {
                nodoInicio.add(nodoAux.get(i));
            } else {
                nodoFin.add(nodoAux.get(i));
            }

        }

        /*  System.out.println("Grafos encontrados "+grafo.size());
         System.out.println("Nodo inicio "+nodoInicio.size());
         System.out.println("Nodo fin "+nodoFin.size());
         System.out.println("Costos "+costos.size());*/
        //algoritmodeDijkstra();

        for (int i = 0; i < puntos.size(); i++) {

            System.out.println(puntos.get(i));

            for (int j = 0; j < grafo.size(); j++) {

                /*if(puntos.get(i)==grafo.get(j).getId()){
                 celdas[grafo.get(j).getFila()][grafo.get(j).getColumna()].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + personaje + ".png")));
                 Thread.sleep(1000);
                 pintarMapa(grafo.get(j).getFila(), grafo.get(j).getColumna());
                 break;
                  
                  
                  
                 }*/
            }

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
                //contarPonderaciones();
                break;
            case 1:
                arriba();
                //contarPonderaciones();
                break;
            case 2:

                superiorDerecha();
                //contarPonderaciones();
                break;
            case 3:
                derecha();
                //contarPonderaciones();
                break;
            case 4:
                inferiorDerecha();
                //contarPonderaciones();
                break;
            case 5:
                abajo();
                //contarPonderaciones();
                break;
            case 6:
                inferiorIzquierda();
                //contarPonderaciones();
                break;
            case 7:
                izquierda();
                // contarPonderaciones();
                break;

        }
    }

    private void derecha() {
        if (validarDerecha()) {

            if (validarHayMuro(fila, columna + 1)) {
                columna++;
                contarPonderaciones();

            }

        }
    }

    private void izquierda() {

        if (validarIzquierda()) {

            if (validarHayMuro(fila, columna - 1)) {
                columna--;
                contarPonderaciones();
            }

        }

    }

    private void arriba() {

        if (validarArriba()) {

            if (validarHayMuro(fila - 1, columna)) {
                fila--;
                contarPonderaciones();
            }

        }

    }

    private void abajo() {

        if (validarAbajo()) {

            if (validarHayMuro(fila + 1, columna)) {
                fila++;
                contarPonderaciones();
            }

        }

    }

    private void inferiorIzquierda() {

        if (validarAbajo()) {

            if (validarIzquierda()) {

                if (validarHayMuro(fila + 1, columna - 1)) {
                    fila++;
                    columna--;
                    contarPonderaciones();

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
                    contarPonderaciones();

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
                    contarPonderaciones();

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
                    contarPonderaciones();

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
                    ponderacion += 1.0;
                    agregarGrafoAux(fila, columna, ponderacion, total);

                    break;
                case 1://agua
                    total = 3.0;
                    ponderacion += 3.0;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 1.5;
                    ponderacion += 1.5;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 2.5;
                    ponderacion += 2.5;
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
                    ponderacion += 0.3;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 1://agua

                    total = 1.0;
                    ponderacion += 1.0;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 2.5;
                    ponderacion += 2.5;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 1.5;
                    ponderacion += 1.5;
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
                    ponderacion += 1.5;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 1://agua
                    total = 2.5;
                    ponderacion += 2.5;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 2:
                    //barranco
                    total = 1.0;
                    ponderacion += 1.0;
                    agregarGrafoAux(fila, columna, ponderacion, total);
                    break;
                case 3:
                    //montaña
                    total = 0.3;
                    ponderacion += 0.3;
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

    private void agregarGrafoAux(int fil, int col, double pon, double es) {

        //si ya agrego
        grafoAux.add(new Recorrido(fil, col, pon, es, -1));

    }

    public void algoritmodeDijkstra() {

        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        for (int i = 0; i < nodos; i++) { //nodos
            Vertex location = new Vertex("" + i, "" + i);
            nodes.add(location);

            edges.add(new Edge("Edge_" + Integer.toString(i), null, null, 0));
            //System.out.println(nodes.get(i).getId());

        }

        for (int i = 0; i < nodoInicio.size(); i++) {
            // System.out.println(nodoInicio.get(i)+"Inicio");
            edges.get(i).setSource(nodes.get(nodoInicio.get(i)));
        }

        for (int i = 0; i < nodoFin.size(); i++) {
            //System.out.println(nodoFin.get(i)+"Fin");
            edges.get(i).setDestination(nodes.get(nodoFin.get(i)));
        }

        for (int i = 0; i < costos.size(); i++) {
            edges.get(i).setWeight(costos.get(i).intValue());

        }

        /* addLane("Edge_0", 0, 1, 85);
         addLane("Edge_1", 0, 1, 217);
         addLane("Edge_2", 0, 4, 173);
         addLane("Edge_3", 2, 6, 186);
         addLane("Edge_4", 2, 7, 103);
         addLane("Edge_5", 3, 7, 183);
         addLane("Edge_6", 5, 8, 250);
         addLane("Edge_7", 8, 9, 84);
         addLane("Edge_8", 7, 9, 167);
         addLane("Edge_9", 4, 9, 502);
         addLane("Edge_10", 9, 10, 40);
         addLane("Edge_11", 1, 10, 600);*/
        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(nodos - 1));

 //       assertNotNull(path);
        //      assertTrue(path.size() > 0);
        for (Vertex vertex : path) {
            //System.out.println(vertex);
            puntos.add(Integer.parseInt(vertex.toString()));
        }

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
                    grafoAux.get(i).getColumna(), grafoAux.get(i).getPonderacion(),
                    grafoAux.get(i).getEsfuerzo(),
                    -1));

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
