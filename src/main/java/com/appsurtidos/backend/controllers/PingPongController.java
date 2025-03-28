package com.appsurtidos.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingPongController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
