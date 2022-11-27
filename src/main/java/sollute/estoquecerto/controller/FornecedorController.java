package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Fornecedor;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.repository.FornecedorRepository;
import sollute.estoquecerto.request.fornecedores.NovoFornecedorRequest;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @PostMapping("/criar-fornecedor/{idEmpresa}")
    public ResponseEntity criarFornecedor(
            @RequestBody @Valid Fornecedor novoFornecedor,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        String telefone = novoFornecedor.getTelefoneFornecedor();

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando o fornecedor...", timeFormated);

                if (!fornecedorRepository.existsByTelefoneFornecedor(telefone)) {
                    novoFornecedor.setFkEmpresa(empresaRepository.findByIdEmpresa(idEmpresa));
                    fornecedorRepository.save(novoFornecedor);
                    System.out.printf("\n[ LOG ] - [%s] --- Fornecedor criado com sucesso.", timeFormated);
                    return status(HttpStatus.CREATED).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Fornecedor já existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse fornecedor já existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar o fornecedor.", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).body(
                ("Essa empresa não existe.")
        );
    }

    @GetMapping("/listar-fornecedores/{idEmpresa}")
    public ResponseEntity<List<Fornecedor>> listarFornecedores(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos fornecedores...", timeFormated);
        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há fornecedores cadastrados.", timeFormated);
            return status(HttpStatus.NO_CONTENT).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando fornecedores.", timeFormated);
        return status(200).body(lista);
    }

    @PutMapping("/editar-fornecedor/{idEmpresa}/{idFornecedor}")
    public ResponseEntity editarFornecedor(
            @RequestBody @Valid NovoFornecedorRequest novoFornecedorRequest,
            @PathVariable Integer idEmpresa,
            @PathVariable Long idFornecedor) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há fornecedores cadastrados.", timeFormated);
            return status(HttpStatus.BAD_REQUEST).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando edição do fornecedor...", timeFormated);
        if (fornecedorRepository.existsById(idFornecedor)) {

            String nome = novoFornecedorRequest.getNomeFornecedor();
            String telefone = novoFornecedorRequest.getTelefoneFornecedor();
            String prod = novoFornecedorRequest.getNomeProduto();
            Integer qtd = novoFornecedorRequest.getQtdFornecida();

            System.out.printf("\n\n[ LOG ] - [%s] --- Editando as informações do fornecedor...", timeFormated);
            fornecedorRepository.atualizarFornecedor(nome, telefone, prod, qtd, idEmpresa, idFornecedor);
            System.out.printf("\n\n[ LOG ] - [%s] --- Informações editadas com sucesso.", timeFormated);

            return status(HttpStatus.OK).build();

        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Fornecedor não existe.", timeFormated);
        return status(HttpStatus.NOT_FOUND).body(
                ("Esse Fornecedor não existe.")
        );
    }

    @PutMapping("/editar-fornecedor-telefone/{idEmpresa}/{telefoneFornecedor}")
    public ResponseEntity editarFornecedorNome(
            @RequestBody @Valid NovoFornecedorRequest novoFornecedorRequest,
            @PathVariable Integer idEmpresa,
            @PathVariable String telefoneFornecedor) {

        String timeFormated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        List<Fornecedor> lista = fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há fornecedores cadastrados.", timeFormated);
            return status(HttpStatus.BAD_REQUEST).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando edição do fornecedor por nome...", timeFormated);
        if (fornecedorRepository.existsByTelefoneFornecedor(telefoneFornecedor)) {

            String nome = novoFornecedorRequest.getNomeFornecedor();
            String telefone = novoFornecedorRequest.getTelefoneFornecedor();
            String prod = novoFornecedorRequest.getNomeProduto();
            Integer qtd = novoFornecedorRequest.getQtdFornecida();

            System.out.printf("\n\n[ LOG ] - [%s] --- Editando as informações do fornecedor por nome...", timeFormated);
            fornecedorRepository.atualizarFornecedorPorNome(nome, telefone, prod, qtd, idEmpresa);
            System.out.printf("\n\n[ LOG ] - [%s] --- Informações editadas com sucesso.", timeFormated);

            return status(HttpStatus.OK).build();

        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Fornecedor não existe.", timeFormated);
        return status(HttpStatus.NOT_FOUND).body(
                ("Esse Fornecedor não existe.")
        );
    }

    @DeleteMapping("/deletar-fornecedor/{idFornecedor}/{idEmpresa}")
    public ResponseEntity deletarFornecedor(
            @PathVariable Integer idFornecedor,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluindo o fornecedor do banco de dados", timeFormated);

                if (fornecedorRepository.existsById(idFornecedor.longValue())) {
                    fornecedorRepository.deleteByIdFornecedorAndFkEmpresaIdEmpresa(idFornecedor, idEmpresa);
                    System.out.printf("\n[ LOG ] - [%s] --- Fornecedor excluido com sucesso", timeFormated);
                    return status(HttpStatus.OK).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Fornecedor não existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse fornecedor não existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir o fornecedor", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/deletar-fornecedor-nome/{idEmpresa}/{telefoneFornecedor}")
    public ResponseEntity deletarFornecedorNome(
            @PathVariable String telefoneFornecedor,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluindo o fornecedor do banco de dados", timeFormated);

                if (fornecedorRepository.existsByTelefoneFornecedor(telefoneFornecedor)) {
                    fornecedorRepository.deleteByTelefoneFornecedorAndFkEmpresaIdEmpresa(telefoneFornecedor, idEmpresa);
                    System.out.printf("\n[ LOG ] - [%s] --- Fornecedor excluido com sucesso", timeFormated);
                    return status(HttpStatus.OK).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Fornecedor não existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse fornecedor não existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir o fornecedor", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

}
