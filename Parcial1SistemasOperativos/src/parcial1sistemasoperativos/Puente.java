/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1sistemasoperativos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author distribuidos
 */
public class Puente {
    
    int cantidadCoches;
    Semaphore espaciosDisponibles;
    Semaphore cochesDisponibles;
    Semaphore mutex;
    Semaphore semaforoVia;

    public Puente() {
        cantidadCoches = 0;
        espaciosDisponibles = new Semaphore(10, true);
        cochesDisponibles = new Semaphore(0, true);
        mutex = new Semaphore(1, true);
        semaforoVia = new Semaphore(1,true);
    }

    public void colocarVehiculoIzqEnPuente() throws InterruptedException {
        espaciosDisponibles.acquire();
        mutex.acquire();
        cantidadCoches = cantidadCoches + 1;
        mutex.release();
        cochesDisponibles.release();
    }
    
    public void colocarVehiculoDerEnPuente() throws InterruptedException {
        espaciosDisponibles.acquire();
        mutex.acquire();
        cantidadCoches = cantidadCoches + 1;
        cantidadCoches = cantidadCoches + 1;
        mutex.release();
        cochesDisponibles.release();       
    }

    public void tomarVehiculoEnPuente() throws InterruptedException {
        cochesDisponibles.acquire();
        mutex.acquire();
        cantidadCoches = cantidadCoches - 1;
        mutex.release();
        espaciosDisponibles.release();
    }
    
}
