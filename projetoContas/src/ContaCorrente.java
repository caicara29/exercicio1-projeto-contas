public class ContaCorrente extends Conta{
    private double limite = 0;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        this.limite = limite;
    }

    public String toString() {
        return super.toString() + " | Limite: " + this.limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double valor) {
        if (valor < 0) {
            System.err.println("O limite não pode ser negativo");
            return;
        }
        this.limite = valor;
    }
    

    public void sacar(double valor) {
        if (valor > 0 && valor <= (limite + saldo)) {
            this.saldo -= valor;
        }
    }

    public void depositar(double valor) {
        if (valor <= 0) return;
        this.saldo += valor;
    }
}
