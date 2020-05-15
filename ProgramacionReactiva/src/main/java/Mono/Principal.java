package Mono;

import reactor.core.publisher.Mono;

public class Principal {
    public static void main(String[] args) {


        /**
         * mono es un tipo de obervable pero, en este caso
         * es un observanle de framwork
         */
        Mono<String>saludo=Mono.just("hola  con mono");
        saludo.subscribe(System.out::println);
    }
    /**
     * <!-- https://mvnrepository.com/artifact/io.projectreactor/reactor-core -->
     * <dependency>
     *     <groupId>io.projectreactor</groupId>
     *     <artifactId>reactor-core</artifactId>
     *     <version>3.3.5.RELEASE</version>
     * </dependency>
     */

}
