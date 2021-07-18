package com.tripmaster.location.dto;

public class AttractionDto {
    private String attractionName;
    private double AttractionLongitude;
    private double AttractionLatitude;
    private double UserLongitude;
    private double UserLatitude;
    private double distance;
    private int rewardPoints;

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public double getAttractionLongitude() {
        return AttractionLongitude;
    }

    public void setAttractionLongitude(double attractionLongitude) {
        AttractionLongitude = attractionLongitude;
    }

    public double getAttractionLatitude() {
        return AttractionLatitude;
    }

    public void setAttractionLatitude(double attractionLatitude) {
        AttractionLatitude = attractionLatitude;
    }

    public double getUserLongitude() {
        return UserLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        UserLongitude = userLongitude;
    }

    public double getUserLatitude() {
        return UserLatitude;
    }

    public void setUserLatitude(double userLatitude) {
        UserLatitude = userLatitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
