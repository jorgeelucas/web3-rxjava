package br.com.projetoweb.javastreamsapi;

public class EnviadorNFClient {

    public void enviar(NotaFiscal notaFiscal) {
        try {
            System.out.printf("Emitindo nota fiscal para %s\n", notaFiscal.nome());
            Thread.sleep(5000);
            System.out.printf("NF para %s enviada com sucesso.\n", notaFiscal.nome());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
