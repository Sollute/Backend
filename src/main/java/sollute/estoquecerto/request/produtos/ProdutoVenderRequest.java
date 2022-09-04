package sollute.estoquecerto.request.produtos;

public class ProdutoVenderRequest {

    private Long idEmpresa;
    private String codigo;
    private Integer qtdVendida;
    private Integer estoqueInicial;

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }

    public Integer getEstoqueInicial() {
        return estoqueInicial;
    }

}
