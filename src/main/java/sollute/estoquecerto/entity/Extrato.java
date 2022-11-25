package sollute.estoquecerto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "extrato")
public class Extrato {

    @Id
    @Column(name = "id_extrato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExtrato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_empresa")
    private Empresa fkEmpresa;

    @NotBlank
    @Length(min = 5, max = 45)
    @Column(name = "extract_name")
    private String extractName;

    @Column(name = "extract_time")
    private String extractTime;

    @Column(name = "extract_amount")
    private Double extractAmount;

    @Column(name = "extract_type")
    private Integer extract_type;

    public Integer getExtract_type() {
        return extract_type;
    }

    public void setExtract_type(Integer type) {
        this.extract_type = type;
    }

    public Integer getIdExtrato() {
        return idExtrato;
    }

    public void setIdExtrato(Integer idExtrato) {
        this.idExtrato = idExtrato;
    }

    public Empresa getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Empresa fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getExtractName() {
        return extractName;
    }

    public void setExtractName(String extractName) {
        this.extractName = extractName;
    }

    public String getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(String extractTime) {
        this.extractTime = extractTime;
    }

    public Double getExtractAmount() {
        return extractAmount;
    }

    public void setExtractAmount(Double extractAmount) {
        this.extractAmount = extractAmount;
    }
}
