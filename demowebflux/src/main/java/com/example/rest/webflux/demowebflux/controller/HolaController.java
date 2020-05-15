package com.example.rest.webflux.demowebflux.controller;

import com.example.rest.webflux.demowebflux.services.HolaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hola")
public class HolaController {

    @Autowired
    HolaServices holaServices;

    @GetMapping
    public Flux<String> mensaje(){
        return holaServices.saludo();
    }
}
