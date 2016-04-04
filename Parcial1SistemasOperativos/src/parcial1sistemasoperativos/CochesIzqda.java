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
public class CochesIzqda extends Thread{
    
    
    private Puente puente;
    
    
    public CochesIzqda(Puente p){
        puente = p;
    }
    
    
    public void run() {

        while (true) {

            int tiempoCruzando = (int) (Math.random() * 10);
            System.out.println("CocheIzqda Cruzando en" + tiempoCruzando + " segundos");

            try {
                puente.colocarVehiculoIzqEnPuente();
                sleep(tiempoCruzando* 1000);
                System.out.println("Coche Termina de Cruzar");
                puente.tomarVehiculoEnPuente();
            } catch (InterruptedException e) {
            }

            

        }
    }
    
}
