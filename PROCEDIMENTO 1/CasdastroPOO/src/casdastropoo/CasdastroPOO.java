package casdastropoo;

import java.io.IOException;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.gerenciadores.PessoaFisicaRepo;
import model.gerenciadores.PessoaJuridicaRepo;

/**
 * @author guilhermebernardes
 */

public class CasdastroPOO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        
        PessoaFisica pessoa1 = new PessoaFisica(1, "Ana", "11111111111", 25);
        PessoaFisica pessoa2 = new PessoaFisica(2, "Carlos", "22222222222", 52);
        
        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);
        
        try {
            repo1.persistir("DocPessoaFisica.bin");
            System.out.println("Dados de Pessoas Físicas armazenados.");
        } catch (IOException e) {
            System.out.println("ERRO");
        }
        
        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        
        try {
            System.out.println("Dados de Pessoa Física recuperados.");
            repo2.recuperar("DocPessoaFisica.bin");
            repo2.obterTodos()
                    .forEach(pessoaFisica -> {
                        pessoaFisica.exibirInformacoes();
                        System.out.println();
                    });
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERRO");
        }
        
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(3, "XPTO Sales", "33333333333333");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(4, "XPTO Solutions", "44444444444444");
        
        repo3.inserir(pessoaJuridica1);
        repo3.inserir(pessoaJuridica2);
        
        try {
            repo3.persistir("DocPessoaJuridica.bin");
            System.out.println("Dados de Pessoas Jurídicas armazenados.");
        } catch (IOException e) {
            System.out.println("ERRO");
        }
        
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        
        try {
            System.out.println("Dados das Pessoas Jurídicas recuperados.");
            repo4.recuperar("DocPessoaJuridica.bin");
            repo4.obterTodos()
                    .forEach(pessoaJuridica -> {
                        pessoaJuridica.exibirInformacoes();
                        System.out.println();
                    });
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR");
        }
    }
}