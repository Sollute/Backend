package sollute.estoquecerto.responses.produtos;

public class ProdutoResponse {

    private Integer estoque;
    private Integer estoqueMin;
    private Integer estoqueMax;
    private Double precoCompra;
    private Double precoVenda;

    public ProdutoResponse(Integer estoque, Integer estoqueMin, Integer estoqueMax, Double precoCompra, Double precoVenda) {
        this.estoque = estoque;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

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

