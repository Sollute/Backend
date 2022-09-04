package sollute.estoquecerto.request.fornecedores;

public class NovoFornecedorRequest {

    private String nomeFornecedor;
    private String telefoneFornecedor;
    private String nomeProduto;
    private Integer qtdFornecida;

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getQtdFornecida() {
        return qtdFornecida;
    }

}
