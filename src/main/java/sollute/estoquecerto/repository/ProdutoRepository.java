package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findProdutoByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    List<Produto> findByFkEmpresaIdEmpresa(Integer idEmpresa);

    List<Produto> findByFkEmpresaIdEmpresaOrderByEstoqueDesc(Integer idEmpresa);

    List<Produto> findFirst5ByFkEmpresaIdEmpresaOrderByQtdVendidosDesc(Integer idEmpresa);

    @Transactional
    void deleteProdutoByCodigoAndFkEmpresaIdEmpresa(String codigo, Integer idEmpresa);

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

}
