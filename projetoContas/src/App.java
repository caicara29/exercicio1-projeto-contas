import java.io.IOException;
import java.util.Scanner;

public class App {
    
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //cadastrarcliente e ja acresenta na lista de clientes
        //cadastrarconta e ja acresenta na lista de contas
        //cadastrar usando cast cliente e conta
        //verificar duplicidade de cpf e cnpj 
        //um cliente não pode ter duas contas do mesmo tipo

        Banco banco = new Banco();
        banco.inicializarClientes();
        banco.inicializarContas();
        banco.listarClientes();
        banco.listarContas();

        /* 
        Cliente cliente = new PessoaFisica("isa", "123");
        banco.adicionarCliente(cliente);
        Conta conta = new ContaCorrente(cliente, 1000);
        banco.adicionarConta(conta);*/
       
        banco.finalizar();
        scanner.close();
    }
}
