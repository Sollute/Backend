package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Empresa;
import sollute.estoquecerto.responses.empresas.EmpresaResponse;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Empresa findByCnpj(String cnpj);

    Empresa findByIdEmpresa(Integer idEmpresa);

    boolean existsByCnpj(String cnpj);

    @Transactional
    @Modifying
    @Query("update Empresa u set u.autenticado = ?2 where u.email = ?1")
    void atualizarAutenticado(String codigo, boolean autenticado);

    @Query("select new sollute.estoquecerto.responses.empresas.EmpresaResponse(e.idEmpresa) " +
            "from Empresa e where e.cnpj like ?1")
    EmpresaResponse getId(String cnpj);
}
