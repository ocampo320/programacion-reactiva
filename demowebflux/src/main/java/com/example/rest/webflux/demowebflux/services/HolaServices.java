package com.example.rest.webflux.demowebflux.services;

import com.example.rest.webflux.demowebflux.repository.HolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HolaServices {

    @Autowired
    HolaRepository holaRepository;

    public Flux<String> saludo(){
        Flux<String>flux=Flux.concat(holaRepository.saludo(),holaRepository.saludo());
        return flux;
    }

}
