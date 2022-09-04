package sollute.estoquecerto.request.produtos;

public class NovoProdutoRequest {

    private Integer estoque;
    private Integer estoqueMin;
    private Integer estoqueMax;
    private Double precoCompra;
    private Double precoVenda;

    public Integer getEstoque() {
        return estoque;
    }

    public Integer getEstoqueMin() {
        return estoqueMin;
    }

    public Integer getEstoqueMax() {
        return estoqueMax;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

}
