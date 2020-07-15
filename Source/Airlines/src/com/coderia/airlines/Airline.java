package com.coderia.airlines;

public class Airline extends SOP {
    private String name;
    private int rating;
    private int type = 0;
    private int max = 100;
    private int staff = 15;
    private int passenger = 85;
    private double distance = 1;
    private boolean domestic = true;
    
    public Airline(String n, int r, int s, int p, boolean d) {
        name = n;
        rating = r;
        max = s + p;
        staff = s;
        passenger = p;
        distance = super.getMinDistance();
        domestic = d;
    }
    
    public Airline(String n, int r, int t, int s, int p,
            boolean d) {
        name = n;
        rating = r;
        type = t;
        max = s + p;
        staff = s;
        passenger = p;
        distance = super.getMinDistance();
        domestic = d;
    }
    
    public Airline(String n, int r, int s, int p, double dist,
            boolean d) {
        name = n;
        rating = r;
        max = s + p;
        staff = s;
        passenger = p;
        distance = dist;
        domestic = d;
    }
    
    public Airline(String n, int r, int t, int s, int p, double dist,
            boolean d) {
        name = n;
        rating = r;
        type = t;
        max = s + p;
        staff = s;
        passenger = p;
        distance = dist;
        domestic = d;
    }
    
    public boolean checkValid() {
        boolean peopleOnBoard = max <= getMaxOnBoard();
        boolean socialDistancing = distance >= getMinDistance();
        
        return peopleOnBoard && socialDistancing;
    }
    
    @Override
    public String toString() {
        return name + " | " + rating + "/5 | Type:" + type + " | "
                + "Max:" + max + " | Staff:" + staff + " | "
                + "Passenger:" + passenger + " | Distance:" + distance + " | "
                + "Domestic Only?:" + domestic + " | "
                + "SOP VALID:" + checkValid() + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }
}
