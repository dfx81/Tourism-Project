package com.coderia.airlines;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Airline> airlines = new ArrayList();
        
        airlines.add(new Airline("EirAsia", 4, 15, 85, true));
        airlines.add(new Airline("Malinda Air", 3, 90, 15, 75, 1.5, true));
        airlines.add(new TierAirline("Gerudo Air", 3, 15, 75, true));
        airlines.add(new TierAirline("Tricolore Airline", 5, 15, 65,
                2, true));
        airlines.add(new Airline("Bad Air", 1, 150, 20, 130, 1.5, false));
        airlines.add(new TierAirline("Awful Air", 2, 20, 80, 0.5, true, 0,
                new int[]{40, 20, 15, 5}));
        
        System.out.println(new SOP());
        System.out.println("List of Airlines available:");
        
        for (Airline a : airlines) {
            switch (a.getType()) {
                case 0:
                    System.out.print(a);
                    break;
                case 1:
                    System.out.print((TierAirline)a);
                default:
                    break;
            }
        }
    }
}
