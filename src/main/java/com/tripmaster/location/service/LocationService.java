package com.tripmaster.location.service;

import com.tripmaster.location.dto.AttractionDto;
import com.tripmaster.location.dto.Coordinates;
import com.tripmaster.location.dto.UserLocationDto;
import com.tripmaster.location.repository.UserDatabase;
import com.tripmaster.location.user.User;
import gpsUtil.GpsUtil;
import rewardCentral.RewardCentral;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private GpsUtil gpsUtil;
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
    public LocationService() {
        this.gpsUtil = new GpsUtil();
    }
    public GpsUtil getGpsUtil() {
        return gpsUtil;
    }

    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

    public VisitedLocation trackUser(User user) {
        VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        return visitedLocation;
    }

    public List<UserLocationDto>getAllRecentUsersLocations(List<User> userList){
        List<UserLocationDto> userLocationDtoList = new ArrayList<>();
        for (User user : userList) {
            UserLocationDto userLocationDto = new UserLocationDto();
            Coordinates coordinates = new Coordinates();
            coordinates.setLatitude(user.getLastVisitedLocation().location.latitude);
            coordinates.setLongitude(user.getLastVisitedLocation().location.longitude);
            userLocationDto.setCoordinates(coordinates);
            userLocationDto.setUserId(user.getUserId());
            userLocationDtoList.add(userLocationDto);
        }
        return userLocationDtoList;
    }

    public List<AttractionDto> getNearbyAttractions(User user){
        RewardCentral rewardCentral = new RewardCentral();
        VisitedLocation visitedLocation = this.trackUser(user);
        List<AttractionDto> fiveClosestAttractions = new ArrayList<>();
        List<AttractionDto> nearbyAttractions = new ArrayList<>();
        for (Attraction attraction : gpsUtil.getAttractions()) {
            AttractionDto attractionDto = new AttractionDto();
            attractionDto.setAttractionName(attraction.attractionName);
            attractionDto.setAttractionLatitude(attraction.latitude);
            attractionDto.setAttractionLongitude(attraction.longitude);
            attractionDto.setUserLatitude(visitedLocation.location.latitude);
            attractionDto.setUserLongitude(visitedLocation.location.longitude);
            attractionDto.setDistance(this.getDistance(attraction, visitedLocation.location));
            attractionDto.setRewardPoints(rewardCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId()));
            nearbyAttractions.add(attractionDto);
        }
            fiveClosestAttractions = nearbyAttractions.stream().sorted(Comparator.comparing(AttractionDto::getDistance)).limit(5).collect(Collectors.toList());
        return  fiveClosestAttractions;
    }

    public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math
                .acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }
}
