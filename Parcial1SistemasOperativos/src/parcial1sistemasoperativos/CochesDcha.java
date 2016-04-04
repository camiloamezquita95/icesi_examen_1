/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1sistemasoperativos;

/**
 *
 * @author distribuidos
 */
public class CochesDcha extends Thread {
    
    private Puente puente;
    
    
    public CochesDcha(Puente p){
        puente = p;
    }
    
    
    public void run() {

        while (true) {

            int tiempoCruzando = (int) (Math.random() * 10);
            System.out.println("CocheDcha Cruzando en" + tiempoCruzando + " segundos");

            try {
                sleep(tiempoCruzando* 1000);
                System.out.println("Coche Termina de Cruzar");
                puente.colocarVehiculoDerEnPuente();
            } catch (InterruptedException e) {
            }

            
        }
    }
}
