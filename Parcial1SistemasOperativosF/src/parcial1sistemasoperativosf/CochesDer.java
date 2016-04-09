/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1sistemasoperativosf;

import static java.lang.Thread.sleep;


/**
 *
 * @author camezquita
 */
public class CochesDer extends Thread{
    private Puente puente;
    
   public CochesDer(Puente puente){
        this.puente=puente;
    }
        
    public void run() {

        while (true) {

            int tiempoPoniendoCoches= (int) (Math.random() * 10);
            System.out.println("Poniendo vehiculo a la derecha en " + tiempoPoniendoCoches + " segundos");

            try {
                sleep(tiempoPoniendoCoches * 1000);
                System.out.println("Llegaron Vehiculos");
                puente.colocarVehiculosDerEnPuente();
            } catch (InterruptedException e) {
            }

        }
    }
    
}
