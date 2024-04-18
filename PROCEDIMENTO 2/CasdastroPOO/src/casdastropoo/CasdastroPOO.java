package casdastropoo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.gerenciadores.PessoaFisicaRepo;
import model.gerenciadores.PessoaJuridicaRepo;
/**
 * @author guilhermebernardes
 */
public class CasdastroPOO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();
        
        try {
            pessoaFisicaRepo.recuperar("pessoaFisica.bin");
            pessoaJuridicaRepo.recuperar("pessoaJuridica.bin");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
        }
        
        while (true) {
            System.out.println("=================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("=================================");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();

            String tipo = "";
            
            switch (opcao) {
                case 1:
                    while (!tipo.equals("F") && !tipo.equals("J")) {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica"); 
                        tipo = scanner.nextLine().toUpperCase();
                        
                        if (!tipo.equals("F") && !tipo.equals("J")) {
                            System.out.println("Opção inválida. Por favor, escolha F ou J.");
                        }
                    }
                    
                    System.out.println("Digite o id da pessoa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("Insira os dados...");
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    
                    if (tipo.equals("F")) {                      
                        System.out.println("CPF: ");
                        String cpf = scanner.nextLine();
                        
                        System.out.println("Idade: ");
                        int idade = scanner.nextInt();
                        
                        pessoaFisicaRepo.inserir(new PessoaFisica(id, nome, cpf, idade));
                        
                        try {
                            pessoaFisicaRepo.persistir("pessoaFisica.bin");
                        } catch (IOException e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                        
                    } else if (tipo.equals("J")) {
                        System.out.println("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        
                        pessoaJuridicaRepo.inserir(new PessoaJuridica(id, nome, cnpj));
                        
                        try {
                            pessoaJuridicaRepo.persistir("pessoaJuridica.bin");
                        } catch (IOException e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                        
                    } else {
                        System.out.println("DADO INVÁLIDO");
                    }
                break;
                
                case 2:
                    while (!tipo.equals("F") && !tipo.equals("J")) {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica"); 
                        tipo = scanner.nextLine().toUpperCase();
                        
                        if (!tipo.equals("F") && !tipo.equals("J")) {
                            System.out.println("Opção inválida. Por favor, escolha F ou J.");
                        }
                    }
                    
                    System.out.println("ID que será alterado: ");
                    int alterarId = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipo.equals("F")) {
                        var pessoaFisica = pessoaFisicaRepo.obter(alterarId);
                        while (pessoaFisica == null) {
                            System.out.println("Nenhum dado encontrado com esse ID. Digite novamente: ");
                            alterarId = scanner.nextInt();
                            scanner.nextLine();
                            pessoaFisica = pessoaFisicaRepo.obter(alterarId);
                        }
                        if (pessoaFisica != null) {
                            System.out.println("Dados atuais: ");
                            System.out.println("Nome: " + pessoaFisica.getNome());
                            System.out.println("CPF: " + pessoaFisica.getCpf());
                            System.out.println("Idade: " + pessoaFisica.getIdade());
                            
                            System.out.println("Novos dados: ");
                            System.out.println("Nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.println("CPF: ");
                            String novoCpf = scanner.nextLine();
                            System.out.println("Idade: ");
                            int novaIdade = scanner.nextInt();
                            scanner.nextLine();
                            
                            pessoaFisica.setNome(novoNome);
                            pessoaFisica.setCpf(novoCpf);
                            pessoaFisica.setIdade(novaIdade);
                            
                            System.out.println("Dados alterados com sucesso.");
                        } else {
                            System.out.println("Nenhum dado encontrado com esse ID.");
                        }
                        
                        try {
                            pessoaFisicaRepo.persistir("pessoaFisica.bin");
                        } catch (IOException e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                        
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obter(alterarId);
                        while (pessoaJuridica == null) {
                            System.out.println("Nenhum dado encontrado com esse ID. Digite novamente: ");
                            alterarId = scanner.nextInt();
                            scanner.nextLine();
                            pessoaJuridica = pessoaJuridicaRepo.obter(alterarId);
                        }
                        if (pessoaJuridica != null) {
                            System.out.println("Dados atuais: ");
                            System.out.println("Nome: " + pessoaJuridica.getNome());
                            System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
                            
                            System.out.println("Novos dados: ");
                            System.out.println("Nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.println("CNPJ: ");
                            String novoCnpj = scanner.nextLine();
                            scanner.nextLine();
                            
                            pessoaJuridica.setNome(novoNome);
                            pessoaJuridica.setCnpj(novoCnpj);
                            
                            System.out.println("Dados alterados com sucesso.");
                        } else {
                            System.out.println("Nenhum dado encontrado com esse ID.");
                        }
                        
                        try {
                            pessoaJuridicaRepo.persistir("pessoaJuridica.bin");
                        } catch (IOException e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    } else {
                        System.out.println("DADO INVÁLIDO");
                    }
                break;
                case 3:
                    while (!tipo.equals("F") && !tipo.equals("J")) {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica"); 
                        tipo = scanner.nextLine().toUpperCase();
                        
                        if (!tipo.equals("F") && !tipo.equals("J")) {
                            System.out.println("Opção inválida. Por favor, escolha F ou J.");
                        }
                    }
                    
                    System.out.println("ID que será excluido: ");
                    int excluirId = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipo.equals("F")) {
                        if (pessoaFisicaRepo.obter(excluirId) != null) {
                            pessoaFisicaRepo.excluir(excluirId);
                            System.out.println("Pessoa Fisica excluída.");
                        } else {
                            System.out.println("Nenhum ID de Pessoa Física encontrado.");
                        }
                    } else if (tipo.equals("J")) {
                        if (pessoaJuridicaRepo.obter(excluirId) != null) {
                            pessoaJuridicaRepo.excluir(excluirId);
                            System.out.println("Pessoa Jurídica excluída.");
                        } else {
                            System.out.println("Nenhum ID de Pessoa Jurídica encontrado.2");
                        }
                    } else {
                        System.out.println("DADO INVÁLIDO");
                    }
                break;
                case 4:
                    while (!tipo.equals("F") && !tipo.equals("J")) {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica"); 
                        tipo = scanner.nextLine().toUpperCase();
                        
                        if (!tipo.equals("F") && !tipo.equals("J")) {
                            System.out.println("Opção inválida. Por favor, escolha F ou J.");
                        }
                    }
                    
                    System.out.println("ID que será visualizado: ");
                    int obterId = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipo.equals("F")) {
                        PessoaFisica pessoaFisica = pessoaFisicaRepo.obter(obterId);
                        if (pessoaFisica != null) {
                            System.out.println("Nome: " + pessoaFisica.getNome());
                            System.out.println("CPF: " + pessoaFisica.getCpf());
                            System.out.println("Idade: " + pessoaFisica.getIdade());
                        } else {
                            System.out.println("Nenhum dado encontrado por esse ID.");
                        }
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obter(obterId);
                        if (pessoaJuridica != null) {
                            System.out.println("Nome: " + pessoaJuridica.getNome());
                            System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
                        } else {
                            System.out.println("Nenhum dado encontrado por esse ID.");
                        }
                    } else {
                        System.out.println("DADO INVÁLIDO");
                    }
                break;
                case 5:
                    while (!tipo.equals("F") && !tipo.equals("J")) {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica"); 
                        tipo = scanner.nextLine().toUpperCase();
                        
                        if (!tipo.equals("F") && !tipo.equals("J")) {
                            System.out.println("Opção inválida. Por favor, escolha F ou J.");
                        }
                    }
                    
                    if (tipo.equals("F")) {
                        List<PessoaFisica> pessoasFisicas = pessoaFisicaRepo.obterTodos();
                        if(!pessoasFisicas.isEmpty()) {
                            System.out.println("PESSOAS FÍSICAS: ");
                            for (PessoaFisica pessoaFisica : pessoaFisicaRepo.obterTodos()) {
                                System.out.println("ID: " + pessoaFisica.getId());
                                System.out.println("Nome: " + pessoaFisica.getNome());
                                System.out.println("CPF: " + pessoaFisica.getCpf());
                                System.out.println("Idade: " + pessoaFisica.getIdade());
                                System.out.println();
                            }
                        } else {
                            System.out.println("Nenhum dado encontrado de pessoas físicas.");
                        }
                    } else if (tipo.equals("J")) {
                        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepo.obterTodos();
                        if (!pessoasJuridicas.isEmpty()) {
                            System.out.println("PESSOAS JURÍDICAS: ");
                            for (PessoaJuridica pessoaJuridica : pessoaJuridicaRepo.obterTodos()) {
                                System.out.println("ID: " + pessoaJuridica.getId());
                                System.out.println("Nome: " + pessoaJuridica.getNome());
                                System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
                                System.out.println();
                            }
                        } else {
                            System.out.println("Nenhum dado encontrado de pessoas jurídicas.");
                        }
                    } else {
                        System.out.println("DADO INVÁLIDO");
                    }
                break;
                case 6:
                    System.out.println("Digite o nome do arquivo a ser salvo: ");
                    String prefixo = scanner.nextLine();
                    
                    try {
                        pessoaFisicaRepo.persistir(prefixo + "fisica.bin");
                        pessoaJuridicaRepo.persistir(prefixo + "juridica.bin");
                        System.out.println("DADOS SALVOS COM SUCESSO");
                    } catch (IOException e) {
                        System.out.println("ERRO: " + e.getMessage());
                    }
                break;
                case 7: 
                    System.out.println("Digite o nome do arquivo a ser recuperado: ");
                    String prefixoRecuperado = scanner.nextLine();
                    
                    try {
                        pessoaFisicaRepo.recuperar(prefixoRecuperado + "fisica.bin");
                        pessoaJuridicaRepo.recuperar(prefixoRecuperado + "juridica.bin");
                        System.out.println("DADOS RECUPERADOS COM SUCESSO");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("ERRO: " + e.getMessage());
                    }
                break;
                case 0:
                    System.out.println("Programa finalizado");
                    return;
                default:
                    System.out.println("Opção Inválida");
            }
        
        }
    }
}
