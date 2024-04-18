package model.gerenciadores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.PessoaFisica;
/**
 * @author guilhermebernardes
 */
public class PessoaFisicaRepo {
    private List<PessoaFisica> pessoasFisicas;
    
    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }
    
    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }
    
    public void alterar(PessoaFisica pessoaFisica, String novoNome, String novoCpf, int novaIdade) {
        pessoaFisica.setNome(novoNome);
        pessoaFisica.setCpf(novoCpf);
        pessoaFisica.setIdade(novaIdade);
    }
    
    public void excluir(int id) {
        pessoasFisicas.removeIf(pessoaFisica -> pessoaFisica.getId() == id);
    }
    
    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoaFisica : pessoasFisicas) {
            if (pessoaFisica.getId() == id) {
                return pessoaFisica;
            }
        }
        return null;
    }
    
    public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas);
    }
    
    public void persistir(String DocPessoaFisica) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DocPessoaFisica))) {
            outputStream.writeObject(pessoasFisicas);
        }
    }
    
    public void recuperar(String DocPessoaFisica) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DocPessoaFisica))) {
            pessoasFisicas = (List<PessoaFisica>) inputStream.readObject();
        }
    }

    public Iterable<PessoaFisica> exibirInformacoes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


