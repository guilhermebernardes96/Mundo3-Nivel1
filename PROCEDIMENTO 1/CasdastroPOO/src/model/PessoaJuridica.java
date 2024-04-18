package model;
import java.io.Serializable;
/**
 * @author guilhermebernardes
 */
public class PessoaJuridica extends Pessoa  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cnpj;
    
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("CNPJ: " + cnpj);
    }
}