package com.alfa.alfabattle.bargaining.services;

import com.alfa.alfabattle.bargaining.model.Branch;

public class DistanceCalculator {
    /**
     * Return distance between branch and point in meters
     */
    public int calc(Branch branch, Double lat, Double lon) {
        int EARTH_RADIUS = 6371000; // in meters
        double fi1 = Math.toRadians(branch.getLat()); //широта 1
        double fi2 = Math.toRadians(lat); //широта 2
        double la1 = Math.toRadians(branch.getLon()); //долгота 1
        double la2 = Math.toRadians(lon); //долгота 2

        double x = Math.sin((fi2-fi1)/2);
        x = x*x;
        double y = Math.sin((la2-la1)/2);
        y = y*y;
        y = y * Math.cos(fi1) * Math.cos(fi1);
        Long distance = Math.round(
                2 * EARTH_RADIUS * Math.asin(
                        Math.sqrt(x+y))
        );
        return distance.intValue();
    }
}
