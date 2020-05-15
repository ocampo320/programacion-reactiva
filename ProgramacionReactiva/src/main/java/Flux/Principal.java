package Flux;

import reactor.core.publisher.Flux;

public class Principal {
    public static void main(String[] args) {
        Flux<String>saludo=Flux.just("hola","con","flux");

        saludo.subscribe(System.out::println);
    }
}
