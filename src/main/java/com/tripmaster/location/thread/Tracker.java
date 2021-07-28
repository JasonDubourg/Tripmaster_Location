package com.tripmaster.location.thread;

import com.tripmaster.location.repository.UserDatabase;
import com.tripmaster.location.service.LocationService;
import com.tripmaster.location.user.User;
import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Tracker implements Callable<List<VisitedLocation>> {
    private Logger logger = LoggerFactory.getLogger(ThreadUserService.class);
    UserDatabase userDatabase = new UserDatabase();

    @Override
    public List<VisitedLocation> call() {
        List<VisitedLocation> listOfVisitedLocation = new ArrayList<>();
        LocationService locationService = new LocationService();
        userDatabase.initializeUserDatabase();
        List<User> users = userDatabase.getAllUsers();
        logger.debug("Begin Tracker; Tracking {} users...", users.size());
        for (User user : users) {
            listOfVisitedLocation.add(locationService.trackUser(user));
        }
        return listOfVisitedLocation;
    }
}
