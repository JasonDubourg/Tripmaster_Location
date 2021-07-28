package com.tripmaster.location;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.tripmaster.location.repository.UserDatabase;
import com.tripmaster.location.thread.ThreadUserService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class TestPerformance {

    @Test
    public void highVolumeTrackLocation(){
        ThreadUserService threadUserService = new ThreadUserService();
        StopWatch stopWatch = new StopWatch();
        UserDatabase.setInternalUserNumber(100);

        // Users should be incremented up to 100,000, and test finishes within 15 minutes
        //  Note : the amount of users tracked depends of the number of users set and the amount of threads used.
        threadUserService.setThreadAmount(10);

        System.out.format("Number of user's locations to track : %d \n", threadUserService.getThreadAmount() * UserDatabase.getInternalUserNumber());

        stopWatch.start();
        threadUserService.trackUsersLocationsThreadPool();
        stopWatch.stop();

        System.out.format("highVolumeTrackLocation: Time Elapsed: %d ms.",
        TimeUnit.MILLISECONDS.toMillis(stopWatch.getTime()));
        assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}

