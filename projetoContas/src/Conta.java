import java.util.UUID;

public abstract class Conta {
    private final String id;
    protected double saldo;
    private Cliente cliente;

    public Conta() {
        this.id = UUID.randomUUID().toString();
        this.saldo = 0;
    }

    public Conta(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String toString() {
        return cliente.toString() + " | Saldo: " + this.saldo;
    }

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);

    public int hashCode() {
        return id.hashCode();
    }
}
