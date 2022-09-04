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
    public ResponseEntity<ResponseEntity.BodyBuilder> adicionarCliente(@RequestBody @Valid Cliente novoCliente,
                                                                       @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            clienteRepository.save(novoCliente);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/listar-clientes/{idEmpresa}")
    public ResponseEntity<List<Cliente>> listarClientes(@PathVariable Integer idEmpresa) {

        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(204).build();

        return status(200).body(lista);

    }

    @PutMapping("/editar-cliente/{idEmpresa}/{idCliente}")
    public ResponseEntity<ResponseEntity.BodyBuilder> editarCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest,
                                                                    @PathVariable Integer idEmpresa,
                                                                    @PathVariable Integer idCliente) {

        List<Cliente> lista = clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(HttpStatus.NOT_FOUND).build();

        if (clienteRepository.existsById(idCliente)) {

            String nome = novoClienteRequest.getNomeCliente();
            String tele = novoClienteRequest.getTelefoneCliente();

            clienteRepository.atualizarCliente(nome, tele, idEmpresa, idCliente);

            return status(HttpStatus.OK).build();

        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/deletar-cliente/{idCliente}/{idEmpresa}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deletarCliente(@PathVariable Integer idCliente,
                                                                    @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {

            if (clienteRepository.existsById(idCliente)) {
                clienteRepository.deleteByIdClienteAndFkEmpresaIdEmpresa(idCliente, idEmpresa);
                return status(HttpStatus.OK).build();
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

}
