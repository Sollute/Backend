package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Cliente;
import sollute.estoquecerto.repository.ClienteRepository;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.request.clientes.NovoClienteRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/adicionar-cliente/{idEmpresa}")
    public ResponseEntity adicionarCliente(
            @RequestBody @Valid Cliente novoCliente,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
//            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando o cliente...", timeFormated);

                clienteRepository.save(novoCliente);

                System.out.printf("\n[ LOG ] - [%s] --- Cliente criado com sucesso.", timeFormated);
                return status(HttpStatus.CREATED).build();

//            } catch (RuntimeException ex) {
//                System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar o cliente.", timeFormated);
//                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
//            }

        }

        return status(HttpStatus.BAD_REQUEST).body(
                ("Essa empresa não existe.")
        );
    }

    @GetMapping("/listar-clientes/{idEmpresa}")
    public ResponseEntity<List<Cliente>> listarClientes(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos clientes...", timeFormated);
        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há clientes cadastrados.", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando clientes.", timeFormated);
        return status(200).body(lista);
    }

    @PutMapping("/editar-cliente/{idEmpresa}/{idCliente}")
    public ResponseEntity editarCliente(
            @RequestBody @Valid NovoClienteRequest novoClienteRequest,
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há clientes cadastrados.", timeFormated);
            return status(HttpStatus.NOT_FOUND).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando edição do cliente...", timeFormated);
        if (clienteRepository.existsById(idCliente)) {

            String nome = novoClienteRequest.getNomeCliente();
            String tele = novoClienteRequest.getTelefoneCliente();

            System.out.printf("\n\n[ LOG ] - [%s] --- Editando as informações do cliente...", timeFormated);
            clienteRepository.atualizarCliente(nome, tele, idEmpresa, idCliente);

            System.out.printf("\n\n[ LOG ] - [%s] --- Informações editadas com sucesso.", timeFormated);
            return status(HttpStatus.OK).build();

        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Cliente não existe.", timeFormated);
        return status(HttpStatus.NOT_FOUND).body(
                ("Esse cliente não existe.")
        );
    }

    @DeleteMapping("/deletar-cliente/{idCliente}/{idEmpresa}")
    public ResponseEntity deletarCliente(
            @PathVariable Integer idCliente,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluindo o cliente do banco de dados", timeFormated);

                if (clienteRepository.existsById(idCliente)) {
                    clienteRepository.deleteByIdClienteAndFkEmpresaIdEmpresa(idCliente, idEmpresa);
                    System.out.printf("\n[ LOG ] - [%s] --- Cliente excluido com sucesso", timeFormated);
                    return status(HttpStatus.OK).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Cliente não existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse cliente não existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir o cliente", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).build();
    }
}
