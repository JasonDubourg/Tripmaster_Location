package com.tripmaster.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @GetMapping(value = "/location")
    public String locationService(){
        String test = "Hello Service Location";
        return test;
    }
}
