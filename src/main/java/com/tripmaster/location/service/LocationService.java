package com.tripmaster.location.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LocationService {
    private GpsUtil gpsUtil;

    public LocationService() {
        this.gpsUtil = new GpsUtil();
    }

    public GpsUtil getGpsUtil() {
        return gpsUtil;
    }

    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

}
