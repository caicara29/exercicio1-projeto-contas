import java.util.Date;
import java.util.UUID;

public abstract class Cliente {

    private String nome;
    private final String id;
    private final Date dataCadastro;
    
    public Cliente() {
        this.id = UUID.randomUUID().toString();
        this.dataCadastro = new Date();
    }

    public Cliente(String nome) {
        this();
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Cliente: " + this.nome + " | Data de Cadastro: " + this.dataCadastro;
    }

    public boolean equals(Object obj) {
        return obj != null
            && (obj instanceof Cliente)
            && ((Cliente) obj).id.equals(this.id);
    }

    public int hashCode() {
        return id.hashCode();
    }
}