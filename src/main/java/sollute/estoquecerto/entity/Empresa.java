package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    @Column(name = "email")
    @Length(min = 5, max = 45)
    @Email(message = "Insira um e-mail valido")
    private String email;

    @NotBlank
    @Length(min = 5, max = 45, message = "Insira uma senha entre 5 e 45 caracteres")
    private String senha;

    @NotBlank
    @Length(min = 3, max = 45, message = "Insira um nome valido, de 5 a 45 caracteres")
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Length(min = 3, max = 45, message = "Insira uma razao social, de 5 a 45 caracteres")
    @Column(name = "razao_social")
    private String razaoSocial;

    @Length(min = 14, max = 14)
    @CNPJ(message = "Insira um CNPJ valido!")
    private String cnpj;

    @NotBlank
    @Column(name = "qtd_produtos_vendidos")
    private int qtdProdutosVendidos;

    @NotBlank
    @Column(name = "total_produtos_vendidos")
    private double totalProdutosVendidos;

    @NotNull
    private boolean autenticado;

    @NotBlank
    @Length(min = 8, max = 8, message = "Insira um CEP valido de 8 caracteres")
    private String cep;

    @NotBlank
    @Length(min = 2, max = 2, message = "Insira uma estado valido")
    private String uf;

    @NotBlank
    @Length(min = 5, max = 45, message = "Insira uma cidade valida, de 5 a 45 caracteres")
    private String cidade;

    @NotBlank
    @Length(min = 5, max = 45, message = "Insira um logradouro valido, de 5 a 45 caracteres")
    private String logradouro;

    @NotBlank
    @Length(min = 5, max = 45, message = "Insira um ponto de referencia valido, de 5 a 45 caracteres")
    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public void setQtdProdutosVendidos(int qtdProdutosVendidos) {
        this.qtdProdutosVendidos = qtdProdutosVendidos;
    }

    public double getTotalProdutosVendidos() {
        return totalProdutosVendidos;
    }

    public void setTotalProdutosVendidos(double totalProdutosVendidos) {
        this.totalProdutosVendidos = totalProdutosVendidos;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }
}
