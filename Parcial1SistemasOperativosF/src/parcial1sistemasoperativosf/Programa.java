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

        CochesDcha vehiculosDcha= new CochesDcha(puente);
        CochesIzquda vehiculosIzqda= new CochesIzquda(puente);
        SemaforoPuente semaforo= new SemaforoPuente(puente);
        
        vehiculosDcha.start();
        vehiculosIzqda.start();
        semaforo.start(); 
    }
    
}
