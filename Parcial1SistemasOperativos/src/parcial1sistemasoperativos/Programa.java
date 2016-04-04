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
public class Programa {

    public static void main(String args[]){

        Puente puente = new Puente();

        CochesIzqda procesoCochesIzqda = new CochesIzqda(puente);
        CochesDcha procesoCochesDcha = new CochesDcha(puente);
        

        procesoCochesIzqda.start();
        procesoCochesDcha.start();

    }
    
}
