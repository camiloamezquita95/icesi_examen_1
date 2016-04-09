/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1sistemasoperativosf;

/**
 *
 * @author camezquita
 */
public class Programa {
    public static void main(String main[]) {

        Puente puente= new Puente();

        CochesDer vehiculosDcha= new CochesDer(puente);
        CochesIzq vehiculosIzqda= new CochesIzq(puente);
        SemaforoPuente semaforo= new SemaforoPuente(puente);
        
        vehiculosDcha.start();
        vehiculosIzqda.start();
        semaforo.start(); 
    }
    
}
