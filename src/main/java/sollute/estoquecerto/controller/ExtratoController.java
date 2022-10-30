package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Extrato;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.repository.ExtratoRepository;
import sollute.estoquecerto.responses.extrato.ListaExtratoResponse;
import sollute.estoquecerto.responses.produtos.ListaProdutosResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/extratos")
public class ExtratoController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ExtratoRepository extratoRepository;

    @PostMapping("/criar-extrato/{idEmpresa}")
    public ResponseEntity postExtract(
            @RequestBody @Valid Extrato novoExtrato,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        try {

            if (empresaRepository.existsById(idEmpresa)) {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando o extrato...", timeFormated);

                novoExtrato.setFkEmpresa(empresaRepository.findByIdEmpresa(idEmpresa));
                extratoRepository.save(novoExtrato);
                System.out.printf("\n[ LOG ] - [%s] --- Extrato criado com sucesso.", timeFormated);
                return status(HttpStatus.CREATED).build();
            }
        } catch (RuntimeException ex) {
            System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar o extrato.", timeFormated);
            return status(HttpStatus.BAD_REQUEST).body(ex.toString());
        }

        return status(HttpStatus.BAD_REQUEST).body(("Essa empresa não existe."));
    }

    @GetMapping("/listar-extrato/{idEmpresa}")
    public ResponseEntity<List<ListaExtratoResponse>> listarExtrato(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem do extrato...", timeFormated);
        List<ListaExtratoResponse> lista = extratoRepository.listar(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há histórico de lançamento.", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando o histórico de lançamento.", timeFormated);
        return status(200).body(lista);
    }

}
