package com.coderia.Airlines;

public class SOP {
    private int maxOnBoard = 100;
    private int minDistance = 1;

    public int getMaxOnBoard() {
        return maxOnBoard;
    }

    public void setMaxOnBoard(int maxOnBoard) {
        this.maxOnBoard = maxOnBoard;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }
    
    @Override
    public String toString() {
        return "- Standard Operating Procedure -\n"
                + "Max Passengers: " + maxOnBoard + "\n"
                + "Min Distance per Passenger: " + minDistance + "m\n";
    }
}