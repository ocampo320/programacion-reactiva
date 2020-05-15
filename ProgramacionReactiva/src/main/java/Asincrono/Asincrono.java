package Asincrono;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Asincrono {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> lista=Observable.interval(1, TimeUnit.SECONDS);

        lista.subscribe(System.out::println);
        Thread.sleep(10000);

    }
}
