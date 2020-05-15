package Observer;

import io.reactivex.Observable;

public class Main {
    public static void main(String[] args) {

        Observable<String>lista=Observable.just("hola","con","reactivo");
        lista.subscribe(new Observer());

    }
}
