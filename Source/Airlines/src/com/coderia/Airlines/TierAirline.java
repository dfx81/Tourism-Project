package com.coderia.Airlines;

import java.util.Arrays;

public class TierAirline extends Airline {
    private int tier = 0;
    private int[] passengerPerTier = {50, 25, 10};
    
    public TierAirline(String n, int r, int s, int p, boolean d) {
        super(n, r, 1, s, p, d);
    }
    
    public TierAirline(String n, int r, int m, int s, int p, double dist,
            boolean d) {
        super(n, r, 1, m, s, p, dist, d);
    }
    
    public TierAirline(String n, int r, int m, int s, int p, double dist,
            boolean d, int t, int[] max) {
        super(n, r, 1, m, s, p, dist, d);
        tier = t;
        passengerPerTier = max;
    }
    
    @Override
    public int getPassenger() {
        return passengerPerTier[tier];
    }
    
    @Override
    public void setPassenger(int p) {
        passengerPerTier[tier] = p;
    }
    
    @Override
    public boolean checkValid() {
        int p = 0;
        
        for (int i : passengerPerTier) {
            p += i;
        }
        
        boolean peopleOnBoard = p + getStaff() <= getMaxOnBoard();
        boolean socialDistancing = getDistance() >= getMinDistance();
        
        return peopleOnBoard && socialDistancing;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int[] getPassengerPerTier() {
        return passengerPerTier;
    }

    public void setPassengerPerTier(int[] passengerPerTier) {
        this.passengerPerTier = passengerPerTier;
    }
    
    @Override
    public String toString() {
        return getName() + " | " + getRating() + "/5 | Type:" + getType() + " | "
                + "Max:" + getMax() + " | Staff:" + getStaff() + " | "
                + "Passenger:" + Arrays.toString(getPassengerPerTier()) + " | "
                + "Distance:" + getDistance() + " | "
                + "Domestic Only?:" + isDomestic() + " | "
                + "Tier:" + tier + " | "
                + "SOP VALID:" + checkValid() + "\n";
    }
}
