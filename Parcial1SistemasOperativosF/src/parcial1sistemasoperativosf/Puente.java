/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1sistemasoperativosf;

import java.util.concurrent.Semaphore;

/**
 *
 * @author camezquita
 */
public class Puente {
    
    public final static int SENTIDO_IZQ_A_DER = 1;
    public final static int SENTIDO_DER_A_IZQ = 0;
    public final static int CANTIDAD_MAX_VEHICULOS = 10;
    
//  Entero que indica la orientacion del puente, es decir si la via es de izq a der o de der a izq.
    private int orientacionPuente;
    
//  Entero que indica la cantidad de coches que pueden subir al puente.
    private int espaciosDisponibles;
    
//  Entero  que indica cuantos coches hay al lado derecho del puente.
//  Controla la llegada y salida de vehiculos desde el lado derecho.
//  Cuando llega un vehiculo se debe hacer cantidadCochesDer.V()
//  Cuando sale un vehiculo para cruzar se debe hacer cantidadCochesDer.P()
    private int cantidadCochesDer;
    
//  Entero  que indica cuantos coches hay al lado izquierdo del puente.
//  Controla la llegada y salida de vehiculos desde el lado izquierdo.
//  Cuando llega un vehiculo se debe hacer cantidadCochesIzq.V()
//  Cuando sale un vehiculo para cruzar se debe hacer cantidadCochesIzq.P()
    private int cantidadCochesIzq;
    
//  Semaforo que garantiza que las variables sean escritas sincronizadamente.
    private Semaphore mutex;
    
//  Semaforo de los espacios para alojar vehiculos que tiene el puente.
    private Semaphore semaforoEspaciosDisponibles;
    
//  Semaforo de los coches al lado derecho del puente  
    private Semaphore semaforoCochesDisponiblesDerecha;
    
//  Semaforo de los coches al lado izquierdo del puente     
    private Semaphore semaforoCochesDisponiblesIzquierda; 
    
//  Semaforo que garantiza que las variables sean escritas sincronizadamente.    
    private Semaphore mutexCochesDerecha;
    
//  Semaforo que garantiza que las variables sean escritas sincronizadamente.  
    private Semaphore mutexCohesIzquierda;
    
    
    public Puente(){
        orientacionPuente=0;
        espaciosDisponibles=10;                 
        cantidadCochesDer=0;
        cantidadCochesIzq=0;
        mutex = new Semaphore(1, true);
        semaforoEspaciosDisponibles= new Semaphore(10);
        semaforoCochesDisponiblesDerecha= new Semaphore(0, true);
        semaforoCochesDisponiblesIzquierda= new Semaphore(0, true);
        mutexCochesDerecha= new Semaphore(1);
        mutexCohesIzquierda= new Semaphore(1);
       
    }
    
    public void colocarVehiculosDerEnPuente() throws InterruptedException{
        mutexCochesDerecha.acquire();
        cantidadCochesDer= cantidadCochesDer + 1;
        mutexCochesDerecha.release();    
        semaforoCochesDisponiblesDerecha.release();
    }
    
    public void colocarVehiculosIzqEnPuente() throws InterruptedException{
        mutexCohesIzquierda.acquire();
        cantidadCochesIzq= cantidadCochesIzq + 1;
        mutexCohesIzquierda.release(); 
        semaforoCochesDisponiblesIzquierda.release();
    }
    public void darPaso() throws InterruptedException{
         
        if(orientacionPuente==SENTIDO_DER_A_IZQ){
           
            if(cantidadCochesDer<CANTIDAD_MAX_VEHICULOS && cantidadCochesDer>0){
                vaciarPuente();
                semaforoCochesDisponiblesDerecha.acquire(cantidadCochesDer);
                semaforoEspaciosDisponibles.acquire(cantidadCochesDer);
                mutex.acquire();
                mutexCochesDerecha.acquire();
                System.out.println("Salieron " + cantidadCochesDer +" vehiculos a la derecha");
                espaciosDisponibles=espaciosDisponibles-cantidadCochesDer;                
                cantidadCochesDer= cantidadCochesDer - cantidadCochesDer;
                mutexCochesDerecha.release();
                mutex.release();
                orientacionPuente=SENTIDO_IZQ_A_DER;  
            }
            else{
                vaciarPuente();
                semaforoCochesDisponiblesDerecha.acquire(10);
                semaforoEspaciosDisponibles.acquire(10);
                mutex.acquire();
                mutexCochesDerecha.acquire();
                espaciosDisponibles=espaciosDisponibles-10;                
                System.out.println("Salieron " + 10 +" vehiculos a la derecha");
                cantidadCochesDer=cantidadCochesDer - 10;
                mutexCochesDerecha.release();
                mutex.release();
                orientacionPuente=SENTIDO_IZQ_A_DER;  
            }
        
        }
        
        else{
            
            if(cantidadCochesIzq<CANTIDAD_MAX_VEHICULOS && cantidadCochesIzq>0){
                vaciarPuente();
                semaforoCochesDisponiblesIzquierda.acquire(cantidadCochesIzq);
                semaforoEspaciosDisponibles.acquire(cantidadCochesIzq);
                mutex.acquire();
                mutexCohesIzquierda.acquire();
                System.out.println("Salieron " + cantidadCochesIzq +" vehiculos a la izquierda");
                espaciosDisponibles=espaciosDisponibles-cantidadCochesIzq;                
                cantidadCochesIzq=cantidadCochesIzq - cantidadCochesIzq;
                mutexCohesIzquierda.release();
                mutex.release();
                orientacionPuente=SENTIDO_DER_A_IZQ;  
            }
            else{                
                vaciarPuente();
                semaforoCochesDisponiblesIzquierda.acquire(10);
                semaforoEspaciosDisponibles.acquire(10);        
                mutex.acquire();
                mutexCohesIzquierda.acquire();
                espaciosDisponibles=espaciosDisponibles-10;               
                System.out.println("Salieron " + 10 +" vehiculos a la izquierda");
                cantidadCochesIzq=cantidadCochesIzq - 10;
                mutexCohesIzquierda.release();
                mutex.release();
                orientacionPuente=SENTIDO_DER_A_IZQ;              
            }
            
        }
    }
    
    public void vaciarPuente() throws InterruptedException{
            mutex.acquire();
            semaforoEspaciosDisponibles.release(CANTIDAD_MAX_VEHICULOS-espaciosDisponibles);
            if(orientacionPuente == SENTIDO_DER_A_IZQ){
                System.out.println("Han pasado: "+ espaciosDisponibles + "Vehiculos de Derecha a Izquierda");
            }else{
                System.out.println("Han pasado: "+ espaciosDisponibles + "Vehiculos de Izquierda a Derecha");
            }            
            espaciosDisponibles = CANTIDAD_MAX_VEHICULOS;
            mutex.release();
    }
    
}
