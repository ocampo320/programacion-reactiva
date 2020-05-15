package Combinar;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Combinar {
    public static void main(String[] args) throws InterruptedException {

        Mono<Integer> num1=Mono.just(1).delayElement(Duration.ofSeconds(2));
        Mono<Integer>num2=Mono.just(2).delayElement(Duration.ofSeconds(3));

        //los combino en un flux
      //  Flux<Integer>numeros=Flux.merge(num1,num2);
        //el merge los ejecuta en paralelo, los dos al mimso timpoe
        //el contact ejecuta uno primero y luego el otro, secuencial.  suamndo los tiempo en la operacion

        Flux<Integer>numeros=Flux.concat(num1,num2);
        numeros.subscribe(System.out::println);

        Thread.sleep(10000);

    }
}
