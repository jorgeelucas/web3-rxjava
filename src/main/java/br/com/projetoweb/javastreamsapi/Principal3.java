package br.com.projetoweb.javastreamsapi;

import br.com.projetoweb.javastreamsapi.subscriber.NotaFiscalSubscriber;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class Principal3 {
    public static void main(String[] args) throws InterruptedException {

        // criando o publisher de notas fiscais
        SubmissionPublisher<NotaFiscal> notaFiscalPublisher
                = new SubmissionPublisher<>();

        // criando o subscriber de notas fiscais
        NotaFiscalSubscriber notaFiscalSubscriber
                = new NotaFiscalSubscriber();

        // se increvendo no publisher
        notaFiscalPublisher.subscribe(notaFiscalSubscriber);

        var notas = List.of(new NotaFiscal("jorge", 399.9, Instant.now()),
                new NotaFiscal("joao", 1200.9, Instant.now()),
                new NotaFiscal("maria", 120.0, Instant.now()),
                new NotaFiscal("alberto", 400.9, Instant.now()));

        notas.forEach(notaFiscalPublisher::submit);

        System.out.println("Sua nota será enviada por email");


//        boolean keepGoing = true;
        Scanner scanner = new Scanner(System.in);

//        while(keepGoing) {
//            scanner = new Scanner(System.in);
//
//            System.out.println("Nova nota");
//            System.out.printf("Nome: ");
//            var nome = scanner.nextLine();
//
//            System.out.printf("Valor: ");
//            var valor = scanner.nextDouble();
//
//            var novaNota = new NotaFiscal(nome, valor, Instant.now());
//
//            notaFiscalPublisher.submit(novaNota);
//
//            System.out.printf("%s, parabéns pela compra, sua nota será enviada por email.\n", nome);
//            Thread.sleep(5000);
//
//        }


//        if (scanner != null) {
//            scanner.close();
//        }
        scanner.nextLine();
        notaFiscalPublisher.close();


    }
}
