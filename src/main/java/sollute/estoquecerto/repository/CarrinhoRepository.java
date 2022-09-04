package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Carrinho;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

    List<Carrinho> findByFkEmpresaIdEmpresa(Integer idEmpresa);

    Carrinho findByFkEmpresaCnpj(String cnpj);

    boolean existsByFkEmpresaCnpj(String cnpj);

    @Transactional
    @Modifying
    @Query("update Carrinho c set c.qtdVenda = ?1, c.valorVenda = ?2 " +
            "where c.fkEmpresa.idEmpresa = ?3 and c.fkProduto.codigo = ?4")
    void atualizaCarrinho(Integer qtdVenda,
                          Double valorVenda,
                          Integer idEmpresa,
                          String codigo
    );

}
