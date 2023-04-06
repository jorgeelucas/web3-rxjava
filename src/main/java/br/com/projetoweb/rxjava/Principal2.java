package br.com.projetoweb.rxjava;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class Principal2 {
    public static void main(String[] args) throws InterruptedException {

        PublishSubject<Integer> publish = PublishSubject.create();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                publish.onNext(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            publish.onComplete();
        }).start();

        Thread.sleep(5000);

        publish.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {}

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("Recebido: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {}

            @Override
            public void onComplete() {
                System.out.println("Completo!!!");
            }
        });

        Thread.sleep(5000);

    }
}
