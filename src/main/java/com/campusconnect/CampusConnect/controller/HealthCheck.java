package com.campusconnect.CampusConnect.controller.ControllerImplemntation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class HealthCheck {

    @GetMapping("/")
    public String getHealthStatus(){
        return "Status is Ok";
    }

}
