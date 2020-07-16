package com.coderia.airlines;

public class SOP {
    private int maxOnBoard = 100;
    private double minDistance = 1;

    public int getMaxOnBoard() {
        return maxOnBoard;
    }

    public void setMaxOnBoard(int maxOnBoard) {
        this.maxOnBoard = maxOnBoard;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }
    
    @Override
    public String toString() {
        return "- Standard Operating Procedure -\n"
                + "Max Passengers: " + maxOnBoard + "\n"
                + "Min Distance per Passenger: " + minDistance + "m\n";
    }
}
