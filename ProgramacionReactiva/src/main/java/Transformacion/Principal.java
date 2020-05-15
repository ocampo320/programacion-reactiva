package Transformacion;

import io.reactivex.Observable;

public class Principal {
    public static void main(String[] args) {

        Observable<String> lista=Observable.just("hola","que","tal");
        lista.map(String::toUpperCase).subscribe(e->System.out.println(e));

    }
}
