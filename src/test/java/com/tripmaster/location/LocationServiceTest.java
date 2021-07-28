package com.tripmaster.location;

import com.tripmaster.location.dto.AttractionDto;
import com.tripmaster.location.dto.UserLocationDto;
import com.tripmaster.location.repository.UserDatabase;
import com.tripmaster.location.service.LocationService;
import com.tripmaster.location.user.User;
import com.tripmaster.location.util.LocalizationUtil;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationServiceTest {
    private GpsUtil gpsUtil = new GpsUtil();
    private LocationService locationService = new LocationService();
    private User user = new User(UUID.randomUUID(), "jon1", "000", "jon1@tourGuide.com");

    @Test
    public void trackUser() {
        LocalizationUtil localizationUtil = new LocalizationUtil();
        Locale.setDefault(localizationUtil.getUS_LOCALE());
        VisitedLocation visitedLocation = locationService.trackUser(user);
        assertEquals(visitedLocation.userId, (user.getUserId()));
    }

    @Test
    public void getAllRecentUsersLocations(){
        List<UserLocationDto> userLocationDtoList;
        UserDatabase userDatabase = new UserDatabase();
        UserDatabase.setInternalUserNumber(100);
        userDatabase.initializeUserDatabase();
        List<User> users = userDatabase.getAllUsers();
        userLocationDtoList = locationService.getAllRecentUsersLocations(users);
        assertTrue(userLocationDtoList.size() > 0);
        assertEquals(userLocationDtoList.size(), 100);
    }

    @Test
    public void getNearbyAttractions() {
        LocalizationUtil localizationUtil = new LocalizationUtil();
        Locale.setDefault(localizationUtil.getUS_LOCALE());
        User user = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");
        List<AttractionDto> attractions = locationService.getNearbyAttractions(user);
        assertEquals(5, attractions.size());
    }

    @Test
    public void getDistance() {
        Location loc1 = new Location(0.0, 0.0);
        Location loc2 = new Location(1.0, 1.0);
        double distance = locationService.getDistance(loc1, loc2);
        Assert.assertEquals(distance, 97.64439545235415, 0.5);
    }


}
