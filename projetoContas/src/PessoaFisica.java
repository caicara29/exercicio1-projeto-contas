public class PessoaFisica extends Cliente {
    private final String cpf;


    public PessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String toString() {
        return super.toString() + " | CPF: " + this.cpf;
    }

    public String csvString(){
        return super.csvString() + ";" + this.cpf;
    }
    
    public boolean equals(Object obj) {
        return super.equals(obj) && (obj instanceof PessoaFisica);
    }
}
