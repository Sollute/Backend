package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;
import sollute.estoquecerto.responses.produtos.ListaProdutosResponse;
import sollute.estoquecerto.responses.produtos.ProdutoResponse;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findProdutoByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    boolean existsByNome(String nome);

    List<Produto> findByFkEmpresaIdEmpresa(Integer idEmpresa);

    List<Produto> findByFkEmpresaIdEmpresaOrderByEstoqueDesc(Integer idEmpresa);

    List<Produto> findFirst5ByFkEmpresaIdEmpresaOrderByQtdVendidosDesc(Integer idEmpresa);

    @Transactional
    void deleteProdutoByCodigoAndFkEmpresaIdEmpresa(String codigo, Integer idEmpresa);

    @Transactional
    void deleteProdutoByNomeAndFkEmpresaIdEmpresa(String nome, Integer idEmpresa);

    @Transactional
    @Modifying
    @Query("update Produto p " +
            "set p.qtdVendidos = ?1, p.valorVendidos = ?2, p.estoque = ?3 " +
            "where p.idProduto = ?4 and p.fkEmpresa.idEmpresa = ?5 ")
    void venderProduto(Integer qtdVendidos,
                       Double valorVendidos,
                       Integer estoque,
                       Integer idProduto,
                       Integer idEmpresa
    );

    @Transactional
    @Modifying
    @Query("update Produto p " +
            "set p.estoque = ?1, p.estoqueMin = ?2, p.estoqueMax = ?3, p.precoCompra = ?4, p.precoVenda = ?5 " +
            "where p.codigo = ?6 and p.fkEmpresa.idEmpresa = ?7 ")
    void atualizarProduto(Integer estoque,
                          Integer estoqueMin,
                          Integer estoqueMax,
                          Double precoCompra,
                          Double precoVenda,
                          String codigo,
                          Integer fkEmpresa
    );

    @Transactional
    @Modifying
    @Query("update Produto p " +
            "set p.estoque = ?1, p.estoqueMin = ?2, p.estoqueMax = ?3, p.precoCompra = ?4, p.precoVenda = ?5 " +
            "where p.nome = ?6 and p.fkEmpresa.idEmpresa = ?7 ")
    void atualizarProdutoPorNome(Integer estoque,
                          Integer estoqueMin,
                          Integer estoqueMax,
                          Double precoCompra,
                          Double precoVenda,
                          String nome,
                          Integer fkEmpresa
    );

    @Query("select new sollute.estoquecerto.responses.produtos.ListaProdutosResponse(" +
            "p.nome, p.precoVenda, p.estoque) " +
            "from Produto p where p.fkEmpresa.idEmpresa like ?1")
    List<ListaProdutosResponse> listarProdutosAndroid(Integer idEmpresa);

    @Query("select new sollute.estoquecerto.responses.produtos.ProdutoResponse(" +
            "p.estoque, p.estoqueMin, p.estoqueMax, p.precoCompra, p.precoVenda) " +
            "from Produto p where p.nome like ?1 and p.fkEmpresa.idEmpresa like ?2")
    ProdutoResponse getInfoByNameAndFkEmpresa(String nome, Integer fkEmpresa);
}
