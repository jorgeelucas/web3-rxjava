package br.com.projetoweb.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class Principal {
    public static void main(String[] args) {

        // HOT & COLD Observables


        // Observable
        // Single

        Observable<Integer> observable = Observable.range(1, 10);

        observable
                .map(numero -> numero * 3)
                .filter(numero -> numero % 2 == 0)
                .subscribeOn(Schedulers.newThread())
                .subscribe(numero -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    System.out.println(numero);
                });


//        Single<Integer> single = Single.just(1);
//        single.subscribe(numero -> System.out.println(numero));

        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        publishSubject.subscribe(getObserver());
        publishSubject.onNext(2);
        publishSubject.onNext(3);

    }

    private static Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("Se inscreveu!!!");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                System.out.println("Imprimindo valor: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                throw new RuntimeException("erro");
            }

            @Override
            public void onComplete() {
                System.out.println("Completou!!!");
            }
        };
    }
}

