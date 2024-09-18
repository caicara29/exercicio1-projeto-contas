import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


public class Banco{
    //verificar duplicidade de cpf e cnpj (um cliente não pode ter duas contas do mesmo tipo)

    private final static String banco = "Exemplo Bank";

    public String getBanco() {
        return banco;
    }

    private static Map<String, Cliente> clientes = new HashMap<>();
    private static Map<String, PessoaFisica> clientesPF = new HashMap<>();
    private static Map<String, PessoaJuridica> clientesPJ = new HashMap<>();

    private static Map<String, Conta> contas = new HashMap<>();
    private static Map<String, Conta> contasPoupanca = new HashMap<>();
    private static Map<String, Conta> contasCorrente = new HashMap<>();
    private static Map<String, Conta> contasInvestimento = new HashMap<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
        if (cliente instanceof PessoaFisica pf) clientesPF.put(pf.getCpf(), pf);
        if (cliente instanceof PessoaJuridica pj) clientesPJ.put(pj.getCnpj(), pj);
    }
  
    public void adicionarConta(Conta conta) {
        contas.put(conta.getId(), conta);
        if (conta instanceof ContaPoupanca cp) contasPoupanca.put(cp.getId(), cp);
        if (conta instanceof ContaCorrente cc) contasCorrente.put(cc.getId(), cc);
        if (conta instanceof ContaInvestimento ci) contasInvestimento.put(ci.getId(), ci);
    }

    public void listarClientes() {
        clientes.values().forEach(cliente -> System.out.println(cliente.getNome()+ ", " + cliente.getClass()));
    }

    public void listarContas() {
        contas.values().forEach(conta -> System.out.println(conta.getCliente().getNome() + ", saldo: " + conta.getSaldo() 
        + ", " + conta.getClass()));
    }

    public void listarClientesPF() {
        clientesPF.values().forEach(cliente -> System.out.println(cliente.getNome() + ", " + cliente.getCpf()));
    }

    public void listarClientesPJ() {
        clientesPJ.values().forEach(cliente -> System.out.println(cliente.getNome() + ", " + cliente.getCnpj()));
    }

    public Cliente buscarCliente(String id) {
        return clientes.get(id);
    }

    public PessoaFisica buscarClientePF(String cpf) {
        return clientesPF.get(cpf);
    }

    public PessoaJuridica buscarClientePJ(String cnpj) {
        return clientesPJ.get(cnpj);
    }

    public void removerCliente(String id) {
        Cliente cliente = clientes.get(id);
        if (cliente instanceof PessoaFisica pf) clientesPF.remove(pf.getCpf());
        if (cliente instanceof PessoaJuridica pf) clientesPJ.remove(pf.getCnpj());

        contas.values().removeIf(conta -> conta.getCliente().equals(cliente));
        clientes.remove(id);
    }

    public void removerClienteByCpf(String cpf){
        PessoaFisica cliente = clientesPF.get(cpf);
        removerCliente(cliente.getId());
    }
    public void removerClienteByCnpj(String cnpj){
        PessoaJuridica cliente = clientesPJ.get(cnpj);
        removerCliente(cliente.getId());
    }

    public void removerConta(String id) {
        contas.remove(id);
    }

    public void inicializarClientes(){
        String diretorioAtual = new File("").getAbsolutePath();
        String caminhoArquivoClientesPF = diretorioAtual + File.separator + "clientesPF.txt";
        String caminhoArquivoClientesPJ = diretorioAtual + File.separator + "clientesPJ.txt";
        
        if (new File(caminhoArquivoClientesPF).exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientesPF))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.replace("\n", ";");
                    String[] dados = linha.split(";");
                    Cliente cliente = new PessoaFisica(dados[0], dados[1],LocalDate.parse((dados[2])), dados[3]);
                    adicionarCliente(cliente);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados do arquivo de usuários: " + e.getMessage());
            }
        }
        if (new File(caminhoArquivoClientesPJ).exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientesPJ))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.replace("\n", ";");
                    String[] dados = linha.split(";");
                    Cliente cliente = new PessoaJuridica(dados[0], dados[1],LocalDate.parse((dados[2])), dados[3]);
                    adicionarCliente(cliente);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados do arquivo de usuários: " + e.getMessage());
            }
        }
        
    }

    public void inicializarContas(){
        String diretorioAtual = new File("").getAbsolutePath();
        String caminhoArquivoContasPoupanca = diretorioAtual + File.separator + "contasPoupanca.txt";
        String caminhoArquivoContasCorrente = diretorioAtual + File.separator + "contasCorrente.txt";
        String caminhoArquivoContasInvestimento = diretorioAtual + File.separator + "contasInvestimento.txt";

        
        if (new File(caminhoArquivoContasPoupanca).exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoContasPoupanca))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.replace("\n", ";");
                    String[] dados = linha.split(";");
                    Conta conta = new ContaPoupanca(dados[0], Double.parseDouble(dados[1]), buscarCliente(dados[2]));
                    adicionarConta(conta);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados do arquivo de contas: " + e.getMessage());
            }
        }
        if(new File(caminhoArquivoContasCorrente).exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoContasCorrente))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.replace("\n", ";");
                    String[] dados = linha.split(";");
                    Conta conta = new ContaCorrente(dados[0], Double.parseDouble(dados[1]), buscarCliente(dados[2]), Double.parseDouble(dados[6]));
                    adicionarConta(conta);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados do arquivo de contas: " + e.getMessage());
            }
        }
        if(new File(caminhoArquivoContasInvestimento).exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoContasInvestimento))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.replace("\n", ";");
                    String[] dados = linha.split(";");
                    Conta conta = new ContaInvestimento(dados[0], Double.parseDouble(dados[1]), buscarCliente(dados[2]));
                    adicionarConta(conta);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados do arquivo de contas: " + e.getMessage());
            }
        }
    }
        
    public void finalizar() throws IOException{
        String nome = "clientes";
        FileWriter arquivo = new FileWriter(nome + ".txt");
        for (Cliente c : clientes.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "clientesPF";
        arquivo = new FileWriter(nome + ".txt");
        for (PessoaFisica c : clientesPF.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "clientesPJ";
        arquivo = new FileWriter(nome + ".txt");
        for (PessoaJuridica c : clientesPJ.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "contas";
        arquivo = new FileWriter(nome + ".txt");
        for (Conta c : contas.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "contasPoupanca";
        arquivo = new FileWriter(nome + ".txt");
        for (Conta c : contasPoupanca.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "contasCorrente";
        arquivo = new FileWriter(nome + ".txt");
        for (Conta c : contasCorrente.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();

        nome = "contasInvestimento";
        arquivo = new FileWriter(nome + ".txt");
        for (Conta c : contasInvestimento.values()) {
            arquivo.write(c + "\n");
        }
        arquivo.close();
    }
}