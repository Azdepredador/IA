/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmf;

/**
 *
 * @author Dell
 */
public class Recorrido {
    
    public  int fila;
    public  int columna;
    public  double ponderacion;
    public  double esfuerzo;
    public int id;

    public Recorrido(int fila, int columna, double ponderacion, double esfuerzo, int id) {
        this.fila = fila;
        this.columna = columna;
        this.ponderacion = ponderacion;
        this.esfuerzo = esfuerzo;
        this.id = id;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public double getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(double esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

   

   
    
    

    
    
    
    
    
    
    
    
    
}
