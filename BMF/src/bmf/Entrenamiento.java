/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmf;




import static bmf.Interfaz.DIM;
import static bmf.Interfaz.celdas;
import static bmf.Interfaz.matrizTerreno;
import static bmf.Interfaz.personaje;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;



/**
 *
 * @author Francisco Javier Rubio Ibañez
 */
public class Entrenamiento {
    private static int fila,columna;
    //private Interfaz in= new Interfaz();
    
    
    
    public void entrenar(){
        
        Executors.newSingleThreadExecutor().execute(new Runnable(){

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
    
    private void moverEnElMapa() throws InterruptedException{
       
        
        
        for(int i=0; i<DIM; i++){
            for(int j=0; j<DIM; j++){
                    if(matrizTerreno[i][j]==31){
                        fila=i;
                        columna=j;
                        break;
                    }
            }
        }
        
        
        for(int iter=0; iter<200; iter++){
            
            moverPersonaje();
            celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/"+personaje+".png")));
            Thread.sleep(500);
            pintarMapa(fila, columna);
            

            
            
        }
        
    }
    /*
    
    // -- arriba -> abajo ++ fila
    // -- izquieda->derecha ++ columna
    
    */
    
    
    
    private void moverPersonaje(){
        switch(numeroRandom()){
            case 0:
                
                superiorIzquierda();
                
                break;
            case 1:
                arriba();
                break;
            case 2:
                
                superiorDerecha();
                break;
            case 3:
                derecha();
                break;
            case 4:
                inferiorDerecha();
                break;
            case 5:
                abajo();
                break;
            case 6:
                inferiorIzquierda();
                break;
            case 7:
                izquierda();
                break;
            
        }
    }
    
    private void derecha(){
        if(validarDerecha()){
            
            if(validarHayMuro(fila, columna+1)){
                columna++;
            
        }
        
    }
   }
    
    private void izquierda(){
        
        if(validarIzquierda()){
            
            if(validarHayMuro(fila, columna-1)){
                columna--;
            }
            
        }
        
    }
    
    private void arriba(){
        
        if(validarArriba()){
            
            if(validarHayMuro(fila-1, columna)){
                fila--;
            }
            
        }
        
        
    }
    
    private void abajo(){
        
        if(validarAbajo()){
            
            if(validarHayMuro(fila+1, columna)){
                fila++;
            }
            
        }
        
        
    }
    
    private void inferiorIzquierda(){
        
        if(validarAbajo()){
            
            if(validarIzquierda()){
                
                if(validarHayMuro(fila+1, columna-1)){
                    fila++;
                    columna--;
                    
                }
                
            }
            
            
        }
        
        
    }
    
    private void inferiorDerecha(){
        
        if(validarAbajo()){
            
            if(validarDerecha()){
                
                if(validarHayMuro(fila+1, columna+1)){
                    
                    fila++;
                    columna++;
                    
                }
            }
        }
        
    }
    
    private void superiorDerecha(){
        
        if(validarDerecha()){
            
            if(validarArriba()){
                
                if(validarHayMuro(fila-1, columna+1)){
                    fila--;
                    columna++;
                    
                }
                
            }
            
        }
        
        
    }
       
    private void superiorIzquierda(){
        
        
        if(validarIzquierda()){
            
            if(validarArriba()){
                
                if(validarHayMuro(fila-1, columna-1)){
                    
                    fila--;
                    columna--;
                    
                }
                
            }
            
        }
        
        
    }
    
    private boolean validarHayMuro(int f, int c){
        
        if(matrizTerreno[f][c]==4){
            return false;
        }
        return true;
        
    }
    
    
    private boolean validarIzquierda(){
        
        if((columna-1) < 0){
            return false;
        }
        return true;
        
    }
    
  private boolean validarDerecha(){
        
        if((columna+1) >=DIM){
            return false;
        }
        return true;
        
    }
    
    
    private boolean validarArriba(){
        if((fila-1)<0){
            return false;
        }
        return true;
        
    }
    
    
    private boolean validarAbajo(){
        
        if((fila+1)>=DIM){
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
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/"+personaje+".png")));
                break;
                
            case 30:
                celdas[fila][columna].setIcon(new ImageIcon(getClass().getResource("/Imagenes/casa.png")));
                break;
                


        }

    }
    
    private int numeroRandom(){
        
        
        int n=(int) (Math.random()*2);
        int n2=(int) (Math.random()*2);
        int n3=(int) (Math.random()*2);

        String c= Integer.toString(n)+Integer.toString(n2)+Integer.toString(n3);        
        int numero= Integer.parseInt(c,2);
    
        return numero;
        
        
        
        
        
    }
    
    
    
    
}
