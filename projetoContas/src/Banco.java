import java.util.HashMap;
import java.util.Map;

public class Banco{
    //verificar duplicidade de cpf e cnpj (um cliente não pode ter duas contas do mesmo tipo)

    private final static String banco = "Exemplo Bank";

    public String getBanco() {
        return banco;
    }

    private Map<String, Cliente> clientes = new HashMap<>();
    private Map<String, PessoaFisica> clientesPF = new HashMap<>();
    private Map<String, PessoaJuridica> clientesPJ = new HashMap<>();

    private Map<String, Conta> contas = new HashMap<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
        if (cliente instanceof PessoaFisica pf) clientesPF.put(pf.getCpf(), pf);
        if (cliente instanceof PessoaJuridica pj) clientesPJ.put(pj.getCnpj(), pj);
    }
  
    public void adicionarConta(Conta conta) {
        contas.put(conta.getId(), conta);
    }

    public void listarClientes() {
        clientes.values().forEach(cliente -> System.out.println(cliente.toString()));
    }

    public void listarContas() {
        contas.values().forEach(conta -> System.out.println(conta.toString()));
    }

    public void listarClientesPF() {
        clientesPF.values().forEach(cliente -> System.out.println(cliente.toString()));
    }

    public void listarClientesPJ() {
        clientesPJ.values().forEach(cliente -> System.out.println(cliente.toString()));
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

        /*for (Conta conta : contas.values()) {
            if (conta.getCliente().equals(cliente)) {
                contas.remove(conta.getId());
            }
        } pq da errado??*/
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
}
