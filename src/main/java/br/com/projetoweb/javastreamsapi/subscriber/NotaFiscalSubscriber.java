package br.com.projetoweb.javastreamsapi.subscriber;

import br.com.projetoweb.javastreamsapi.EnviadorNFClient;
import br.com.projetoweb.javastreamsapi.NotaFiscal;

import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;

public class NotaFiscalSubscriber implements Subscriber<NotaFiscal> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscriber se inscreveu no publisher");
        subscription.request(1);
        this.subscription = subscription;
    }

    @Override
    public void onNext(NotaFiscal item) {
        EnviadorNFClient client = new EnviadorNFClient();
        client.enviar(item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throw new RuntimeException("erro no processamento da nota");
    }

    @Override
    public void onComplete() {
        System.out.println("Todas as notas foram processadas");
    }
}
