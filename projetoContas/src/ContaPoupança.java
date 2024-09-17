public class ContaPoupança extends Conta implements Rendimento{
    private static double taxa = 0.005;
    
    public ContaPoupança(Cliente cliente) {
        super(cliente);
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            this.saldo -= valor;
        }
    }
    public void depositar(double valor) {
        if (valor <= 0) return;
        this.saldo += valor;
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