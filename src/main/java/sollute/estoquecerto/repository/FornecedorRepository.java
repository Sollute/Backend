package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Fornecedor;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findByfkEmpresaIdEmpresa(Integer idEmpresa);

    @Transactional
    void deleteByIdFornecedorAndFkEmpresaIdEmpresa(Integer idFornecedor, Integer fkEmpresa);

    @Transactional
    @Modifying
    @Query("update Fornecedor f " +
            "set f.nomeFornecedor = ?1, f.telefoneFornecedor = ?2, f.nomeProduto = ?3, f.qtd = ?4 " +
            "where f.fkEmpresa.idEmpresa = ?5 and f.idFornecedor = ?6")
    void atualizarFornecedor(String nomeFornecedor,
                             String telefoneFornecedor,
                             String nomeProduto,
                             Integer qtd,
                             Integer idEmpresa,
                             Long idFornecedor
    );

}
