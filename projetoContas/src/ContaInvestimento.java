public class ContaInvestimento extends Conta implements Rendimento{
    private int quantidadeDepositos = 0;
    private static double taxa = 0.02;

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    public int getQuantidadeDepositos() {
        return quantidadeDepositos;
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo && (quantidadeDepositos%3) == 0) {
            this.saldo -= valor;
        }
    }
    public void depositar(double valor) {
        if (valor <= 0) return;
        this.saldo += valor;   
        this.quantidadeDepositos++;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double valor) {
        if (valor < 0) {
            System.err.println("A taxa não pode ser negativa");
            return;
        }
        taxa = valor;
    }

    public void aplicar() {
        this.saldo += saldo * taxa;        
    }


    
}
