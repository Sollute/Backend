package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sollute.estoquecerto.entity.Funcionario;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Boolean existsByCpfFuncionario(String cpfFuncionario);

    List<Funcionario> findByFkEmpresaIdEmpresa(Integer idEmpresa);

    @Transactional
    void deleteByIdFuncionarioAndFkEmpresaIdEmpresa(Integer idFuncionario, Integer fkEmpresa);

    @Transactional
    void deleteByCpfFuncionarioAndFkEmpresaIdEmpresa(String cpfFuncionario, Integer fkEmpresa);

    @Transactional
    @Modifying
    @Query("update Funcionario f " +
            "set f.nomeFuncionario = ?1, f.telefoneFuncionario = ?2, f.cpfFuncionario = ?3, f.salarioFuncionario = ?4 " +
            "where f.fkEmpresa.idEmpresa = ?5 and f.idFuncionario = ?6")
    void atualizarFuncionario(String nomeFuncionario,
                              String telefoneFuncionario,
                              String cpfFuncionario,
                              Double salarioFuncionario,
                              Integer idEmpresa,
                              Integer idFuncionario
    );

    @Transactional
    @Modifying
    @Query("update Funcionario f " +
            "set f.nomeFuncionario = ?1, f.telefoneFuncionario = ?2, f.cpfFuncionario = ?3, f.salarioFuncionario = ?4 " +
            "where f.fkEmpresa.idEmpresa = ?5 and f.cpfFuncionario = ?3")
    void atualizarFuncionarioCpf(String nomeFuncionario,
                                 String telefoneFuncionario,
                                 String cpfFuncionario,
                                 Double salarioFuncionario,
                                 Integer idEmpresa
    );

}
