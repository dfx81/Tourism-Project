/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderia.launcher;

/**
 *
 * @author user
 */
public class SOPAirplane {
    String name, temperature, numberOfSeat,typeOfAirplane, mask ;
    

    
    SOPAirplane(String nm, String temp, String ms, String ns, String ta) {
        this.name = nm;
        this.temperature = temp;
        this.mask = ms;
        this.numberOfSeat = ns;
        this.typeOfAirplane = ta;
        
    }

    SOPAirplane(String nm, String[] temperature, String[] numberOfSeat, String[] typeOfAirplane, String[] mask) {
        this.name=nm;

  

  
}
}
