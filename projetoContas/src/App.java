import java.io.IOException;
import java.util.Scanner;

public class App {
    
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //cadastrarcliente e ja acresenta na lista de clientes
        //cadastrarconta e ja acresenta na lista de contas
        //cadastrar usando cast de cliente e pessoa fisica ou juridica

        Banco banco = new Banco();

        Cliente cliente1 = new PessoaFisica("João", "123.456.789-00");
        Cliente cliente2 = new PessoaJuridica("Empresa", "12.345.678/0001-99");

        banco.adicionarCliente(cliente1);
        banco.adicionarCliente(cliente2);

        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaCorrente(cliente2);
        Conta conta3 = new ContaPoupança(cliente2);
        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);
        banco.adicionarConta(conta3);

        banco.listarClientes();
        banco.listarContas();

        //banco.removerCliente(cliente2.getId());

        banco.listarClientes();
        banco.listarContas();
        //apagar testes dps

        banco.finalizar();
        scanner.close();
    }
}
