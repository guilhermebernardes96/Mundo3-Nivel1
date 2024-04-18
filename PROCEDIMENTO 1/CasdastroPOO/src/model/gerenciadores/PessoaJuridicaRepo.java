package model.gerenciadores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.PessoaFisica;
import model.PessoaJuridica;
/**
 * @author guilhermebernardes
 */
public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoasJuridicas;
    
    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }
    
    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }
    
    public void alterar(PessoaJuridica pessoaJuridica, String novoNome, String novoCnpj) {
        pessoaJuridica.setNome(novoNome);
        pessoaJuridica.setCnpj(novoCnpj);
    }
    
    public void excluir(int id) {
        pessoasJuridicas.removeIf(pessoaJuridica -> pessoaJuridica.getId() == id);
    }
    
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
            if (pessoaJuridica.getId() == id) {
                return pessoaJuridica;
            }
        }
        return null;
    }
    
    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas);
    }
    
    public void persistir(String DocPessoaJuridica) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DocPessoaJuridica))) {
            outputStream.writeObject(pessoasJuridicas);
        }
    }
    
    public void recuperar(String DocPessoaJuridica) throws IOException, ClassNotFoundException  {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DocPessoaJuridica))) {
            pessoasJuridicas = (List<PessoaJuridica>) inputStream.readObject();
        }
    }
}
