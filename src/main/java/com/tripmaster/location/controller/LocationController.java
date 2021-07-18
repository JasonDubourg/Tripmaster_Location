package com.tripmaster.location.controller;

import com.jsoniter.output.JsonStream;
import com.tripmaster.location.dto.AttractionDto;
import com.tripmaster.location.dto.UserLocationDto;
import com.tripmaster.location.repository.UserDatabase;
import com.tripmaster.location.service.LocationService;
import com.tripmaster.location.user.User;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    UserDatabase userDatabase;

    @GetMapping(value = "/location")
    public String locationService(){
        String test = "Hello Service Location";
        return test;
    }

    @GetMapping(value = "/getLocation")
    public String getUserLocation(@RequestParam("userName") String userName){
        userDatabase.initializeUserDatabase();
        VisitedLocation visitedLocation = locationService.trackUser(userDatabase.getUser(userName));
        return JsonStream.serialize(visitedLocation.location);
    }

    @GetMapping(value = "/getAllCurrentLocations")
    public List<UserLocationDto> getAllRecentUsersLocations(){
        userDatabase.initializeUserDatabase();
        List<User> userList = userDatabase.getAllUsers();
        return locationService.getAllRecentUsersLocations(userList);
    }

    @GetMapping(value = "/getNearbyAttractions")
    public List<AttractionDto> getNearbyAttractions(@RequestParam("userName") String userName){
        userDatabase.initializeUserDatabase();
        User user  = userDatabase.getUser(userName);
        return locationService.getNearbyAttractions(user);
    }
}
