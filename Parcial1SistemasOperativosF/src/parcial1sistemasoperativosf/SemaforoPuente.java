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
public class SemaforoPuente extends Thread{
    
    private Puente puente;
    
    public SemaforoPuente(Puente puente){
        this.puente=puente;
    }
    
    public void run() {

        while (true) {
            int tiempoDandoVia= (int) (Math.random() * 10);
            System.out.println("Dando via " + tiempoDandoVia + " segundos");
            try {
                sleep(tiempoDandoVia * 1000);
                puente.darPaso();
            } catch (InterruptedException e) {
            }            
        }
    }
    
}
