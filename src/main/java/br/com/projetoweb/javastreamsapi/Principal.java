package br.com.projetoweb.javastreamsapi;

import java.time.Instant;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        // PROGRAMACAO REATIVA

        var notas = List.of(new NotaFiscal("jorge", 399.9, Instant.now()),
                new NotaFiscal("joao", 1200.9, Instant.now()),
                new NotaFiscal("maria", 120.0, Instant.now()),
                new NotaFiscal("alberto", 400.9, Instant.now()));

        EnviadorNFClient client = new EnviadorNFClient();

        notas.stream().forEach(client::enviar);

        System.out.println("Sua nota fiscal será enviada por email");

    }

}
