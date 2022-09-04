package sollute.estoquecerto.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @Column(name = "id_caixa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCaixa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_empresa")
    private Empresa fkEmpresa;

    @NotNull
    @PositiveOrZero
    private Double valor;

    @NotNull
    @Column(name = "qtd_entradas")
    private int qtdEntradas;

    @NotNull
    @Column(name = "qtd_saidas")
    private int qtdSaidas;

    @NotNull
    @Column(name = "valor_entradas")
    private Double valorEntradas;

    @NotNull
    @Column(name = "valor_saidas")
    private Double valorSaidas;

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQtdEntradas() {
        return qtdEntradas;
    }

    public void setQtdEntradas(int qtdEntradas) {
        this.qtdEntradas = qtdEntradas;
    }

    public int getQtdSaidas() {
        return qtdSaidas;
    }

    public void setQtdSaidas(int qtdSaidas) {
        this.qtdSaidas = qtdSaidas;
    }

    public Double getValorEntradas() {
        return valorEntradas;
    }

    public void setValorEntradas(Double valorEntradas) {
        this.valorEntradas = valorEntradas;
    }

    public Double getValorSaidas() {
        return valorSaidas;
    }

    public void setValorSaidas(Double valorSaidas) {
        this.valorSaidas = valorSaidas;
    }
}
