package com.example.rest.webflux.demowebflux.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class HolaRepository {

    public Mono<String> saludo(){
        return Mono.just("hola con rest, webflux").delayElement(Duration.ofSeconds(5));
    }
}
