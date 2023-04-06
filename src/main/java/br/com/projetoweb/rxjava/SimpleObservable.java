package br.com.projetoweb.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleObservable {

    static AtomicInteger contador = new AtomicInteger(0);

    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 10);

        observable
                .map(x -> {
                    contador.incrementAndGet();
                    return x * 3;
                })
                .map(x -> {
                    if (x == 6) throw new RuntimeException("Erro: valor igual a 6");
                    return x;
                })
//                .filter(x -> x % 2 == 0)
                .onErrorResumeNext(error -> {
                    if (error instanceof RuntimeException) {
                        return Observable.just(-1)
                                .concatWith(observable.skipWhile(x -> x <= contador.get()))
                                .map(x -> x * 3);
                    } else {
                        return Observable.error(error);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Recebido: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Erro: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Concluído");
                    }
                });
    }
}
