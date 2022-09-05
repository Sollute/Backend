package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Funcionario;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.repository.FuncionarioRepository;
import sollute.estoquecerto.request.funcionarios.NovoFuncionarioRequest;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/criar-funcionario/{idEmpresa}")
    public ResponseEntity criarFuncionario(
            @RequestBody @Valid Funcionario novoFuncionario,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando o funcionario...", timeFormated);

                funcionarioRepository.save(novoFuncionario);

                System.out.printf("\n[ LOG ] - [%s] --- Funcionario criado com sucesso.", timeFormated);
                return status(HttpStatus.CREATED).build();

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar o funcionario.", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).body(
                ("Essa empresa não existe.")
        );
    }

    @GetMapping("/listar-funcionarios/{idEmpresa}")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos funcionarios...", timeFormated);
        List<Funcionario> lista = funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há funcionarios cadastrados.", timeFormated);
            return status(HttpStatus.NO_CONTENT).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando funcionarios.", timeFormated);
        return status(200).body(lista);
    }

    @PutMapping("/editar-funcionario/{idEmpresa}/{idFuncionario}")
    public ResponseEntity editarFuncionario(
            @RequestBody @Valid NovoFuncionarioRequest novoFuncionarioRequest,
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idFuncionario
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        List<Funcionario> lista = funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há funcionários cadastrados.", timeFormated);
            return status(HttpStatus.BAD_REQUEST).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando edição do funcionário...", timeFormated);
        if (funcionarioRepository.existsById(idFuncionario)) {

            String nome = novoFuncionarioRequest.getNomeFuncionario();
            String tele = novoFuncionarioRequest.getTelefoneFuncionario();
            String cpf = novoFuncionarioRequest.getCpfFuncionario();
            Double salario = novoFuncionarioRequest.getSalario();

            System.out.printf("\n\n[ LOG ] - [%s] --- Editando as informações do funcionário...", timeFormated);
            funcionarioRepository.atualizarFuncionario(nome, tele, cpf, salario, idEmpresa, idFuncionario);

            System.out.printf("\n\n[ LOG ] - [%s] --- Informações editadas com sucesso.", timeFormated);
            return status(HttpStatus.OK).build();

        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Funcionário não existe.", timeFormated);
        return status(HttpStatus.NOT_FOUND).body(
                ("Esse funcionário não existe.")
        );
    }

    @DeleteMapping("/deletar-funcionario/{idFuncionario}/{idEmpresa}")
    public ResponseEntity deletarFuncionario(
            @PathVariable Integer idFuncionario,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluindo o funcionário do banco de dados", timeFormated);

                if (funcionarioRepository.existsById(idFuncionario)) {
                    funcionarioRepository.deleteByIdFuncionarioAndFkEmpresaIdEmpresa(idFuncionario, idEmpresa);
                    System.out.printf("\n[ LOG ] - [%s] --- Funcionário excluido com sucesso", timeFormated);
                    return status(HttpStatus.OK).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Funcionário não existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse funcionário não existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir o funcionário", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).build();
    }
}
