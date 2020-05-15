package com.example.reactor.reactor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Observable;

@SpringBootTest
class ReactorApplicationTests {

    @Test
    public void testTexto() {
        Mono<String> mensaje = Mono.just("hola con mono");
        mensaje.subscribe(System.out::println);

    }

    @Test
    public void testObjeto() {
        Mono<Persona> mensaje = Mono.just(new Persona("pepe", "perez", 20));
        mensaje.subscribe(System.out::println);

    }

    @Test
	public  void TestAsincronico() throws InterruptedException{
    	Mono<Persona> mensaje=Mono.just(new Persona("PEPE","PEREZ",20)).delayElement(Duration.ofSeconds(5));
    	mensaje.subscribe(System.out::println);
    	Thread.sleep(10000);
	}

	@Test
	public void TestLista()throws InterruptedException {
		Flux<String>mensaje=Flux.just("hola","que","tal","estas").delayElements(Duration.ofSeconds(1));
		mensaje.subscribe(System.out::println);

		Thread.sleep(10000);

	}
	@Test
	public void TestLista2() throws InterruptedException{
    	Flux<Persona>saludo=Flux.just(new Persona("deivi","lopez",29));

		saludo.subscribe(System.out::println);

		Thread.sleep(10000);
	}
	







}
