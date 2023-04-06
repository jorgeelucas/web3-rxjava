package br.com.projetoweb.javastreamsapi;

import java.time.Instant;

public record NotaFiscal(String nome, double valor, Instant data) {
}
