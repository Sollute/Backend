package sollute.estoquecerto.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.empresas.EmpresaLoginRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/criar-empresa")
    public ResponseEntity<ResponseEntity.BodyBuilder> criarEmpresa(@RequestBody @Valid Empresa createEmpresaResponse) {

        String cnpj = createEmpresaResponse.getCnpj();

        if (!empresaRepository.existsByCnpj(cnpj)) {
            empresaRepository.save(createEmpresaResponse);
            return status(HttpStatus.CREATED).build();
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(@RequestBody @Valid EmpresaLoginRequest requisicao) {

        List<Empresa> empresa = empresaRepository.findAll();

        for (Empresa e : empresa) {
            if (e.getEmail().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {

                empresaRepository.atualizarAutenticado(requisicao.getLogin(), true);
                return status(HttpStatus.OK).body(e);

            }
        }

        return status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/listar-empresas")
    public ResponseEntity<List<Empresa>> listarEmpresas() {

        List<Empresa> listaEmpresas = empresaRepository.findAll();

        if (listaEmpresas.isEmpty()) return status(HttpStatus.NO_CONTENT).build();

        return status(HttpStatus.OK).body(listaEmpresas);
    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity<Integer> calcularProdutosVendidos(@PathVariable Integer fkEmpresa) {

        int aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getQtdVendidos();
            }
            return status(HttpStatus.OK).body(aux);

        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity<Double> calcularValorVendidos(@PathVariable Integer fkEmpresa) {

        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getValorVendidos();
            }

            return status(HttpStatus.OK).body(aux);
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-liquido/{fkEmpresa}")
    public ResponseEntity<Double> lucroLiquido(@PathVariable Integer fkEmpresa) {

        Double bruto = calcularValorVendidos(fkEmpresa).getBody();
        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getPrecoCompra() * prod.getQtdVendidos();
            }

            aux = bruto - aux;

            return status(200).body(aux);
        }

        return status(404).build();
    }

}
