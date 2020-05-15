package observable;

import io.reactivex.Observable;

public class Principal {
    public static void main(String[] args) {
        /**
         *  <dependency>
         *             <groupId>io.reactivex.rxjava2</groupId>
         *             <artifactId>rxjava</artifactId>
         *             <version>2.2.19</version>
         *         </dependency>
         */


        //este es el observable el que publica.
        Observable<String> lista=Observable.just("hola","mundo","con","rxjava");


        //este es el suscriptor el que implementa el observable

        lista.subscribe(e->System.out.println(e));
    }
}
