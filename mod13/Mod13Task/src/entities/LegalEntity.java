package entities;

public class LegalEntity extends Person{
    private String cnpj;
    private String cnae;

    public LegalEntity(){

    }

    public LegalEntity(String cnpj, String name, String address, String cnae) {
        super(name, address);
        this.cnpj = cnpj;
        this.cnae = cnae;
    }

    @Override
    public String toString() {
        return "LegalEntity{" +
                "name='" + super.getName() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", cnae='" + cnae + '\'' +
                ", address='" + super.getName() + '\'' +
                '}';
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }
}
