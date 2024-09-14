public class PessoaJuridica extends Cliente {
    private final String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String toString() {
        return super.toString() + " | CNPJ: " + this.cnpj;
    }
    
}
