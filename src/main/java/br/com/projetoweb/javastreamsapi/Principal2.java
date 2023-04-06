package br.com.projetoweb.javastreamsapi;

import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class Principal2 {

    public static void main(String[] args) throws InterruptedException {

        // criando o publisher de notas fiscais
        SubmissionPublisher<NotaFiscal> notaFiscalPublisher
                = new SubmissionPublisher<>();

        // ensinando o publisher qual é o seu subscriber
        EnviadorNFClient client = new EnviadorNFClient();
        notaFiscalPublisher.consume(client::enviar);

        boolean keepGoing = true;
        Scanner scanner = new Scanner(System.in);;

        while(keepGoing) {
            scanner = new Scanner(System.in);

            System.out.println("Nova nota");
            System.out.printf("Nome: ");
            var nome = scanner.nextLine();

            System.out.printf("Valor: ");
            var valor = scanner.nextDouble();

            var novaNota = new NotaFiscal(nome, valor, Instant.now());

            notaFiscalPublisher.submit(novaNota);

            System.out.printf("%s, parabéns pela compra, sua nota será enviada por email.\n", nome);
            Thread.sleep(5000);
        }


        if (scanner != null) {
            scanner.close();
        }
        notaFiscalPublisher.close();
    }

}
