package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {

    Caixa findCaixaByFkEmpresaIdEmpresa(Integer idEmpresa);

    @Transactional
    @Modifying
    @Query("update Caixa c " +
            "set c.valor = ?1 " +
            "where c.idCaixa = ?2 and c.fkEmpresa.idEmpresa = ?3")
    void atualizarValor(Double valor,
                        Integer idCaixa,
                        Integer idEmpresa
    );

}
