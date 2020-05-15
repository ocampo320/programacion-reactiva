package Observer;

import io.reactivex.disposables.Disposable;

public class Observer implements io.reactivex.Observer {
    @Override
    public void onSubscribe(Disposable disposable) {
        System.out.println("en esta parte nos suscribimos");

    }

    @Override
    public void onNext(Object elemento) {
        System.out.println(elemento);

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("en esta parte se genera el error");

    }

    @Override
    public void onComplete() {
        System.out.println("en esta parte se informa del fin del proceso");


    }
}
