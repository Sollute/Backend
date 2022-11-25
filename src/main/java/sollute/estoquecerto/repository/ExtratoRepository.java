package sollute.estoquecerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sollute.estoquecerto.entity.Extrato;
import sollute.estoquecerto.responses.extrato.ListaExtratoResponse;

import java.util.List;

public interface ExtratoRepository extends JpaRepository<Extrato, Integer> {

    @Query("select new sollute.estoquecerto.responses.extrato.ListaExtratoResponse(" +
            "p.extractName, p.extractTime, p.extractAmount, p.extract_type)" +
            "from Extrato p where p.fkEmpresa.idEmpresa like ?1")
    List<ListaExtratoResponse> listar(Integer idEMpresa);
}
