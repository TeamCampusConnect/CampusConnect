package com.campusconnect.CampusConnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String getHealthStatus(){
        return "Status is Ok";
    }

}
