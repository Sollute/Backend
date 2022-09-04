package sollute.estoquecerto.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_empresa")
    private Empresa fkEmpresa;

    @NotBlank
    private String codigo;

    @NotBlank
    @Length(min = 2, max = 45)
    private String nome;

    @NotBlank
    @Length(min = 2, max = 45)
    private String marca;

    @NotBlank
    @Length(min = 3, max = 45)
    private String categoria;

    @NotBlank
    @Length(min = 1, max = 2)
    private String tamanho;

    @PositiveOrZero
    private Double peso;

    @PositiveOrZero
    @Column(name = "preco_compra")
    private Double precoCompra;

    @PositiveOrZero
    @Column(name = "preco_venda")
    private Double precoVenda;

    @PositiveOrZero
    private Integer estoque;

    @PositiveOrZero
    @Column(name = "estoque_min")
    private Integer estoqueMin;

    @Positive
    @Column(name = "estoque_max")
    private Integer estoqueMax;

    @PositiveOrZero
    @Column(name = "qtd_vendidos")
    private Integer qtdVendidos;

    @PositiveOrZero
    @Column(name = "valor_vendidos")
    private Double valorVendidos;

    public Produto(Integer idProduto,
                   String codigo,
                   String nome,
                   String marca,
                   String categoria,
                   String tamanho,
                   Double peso,
                   Double precoCompra,
                   Double precoVenda,
                   Integer estoque,
                   Integer estoqueMin,
                   Integer estoqueMax,
                   Integer qtdVendidos,
                   Double valorVendidos) {
        this.idProduto = idProduto;
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.peso = peso;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.estoque = estoque;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
        this.qtdVendidos = qtdVendidos;
        this.valorVendidos = valorVendidos;
    }

    public Produto() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(Integer estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public Integer getEstoqueMax() {
        return estoqueMax;
    }

    public void setEstoqueMax(Integer estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Double getValorVendidos() {
        return valorVendidos;
    }

    public void setValorVendidos(Double valorVendidos) {
        this.valorVendidos = valorVendidos;
    }

}
