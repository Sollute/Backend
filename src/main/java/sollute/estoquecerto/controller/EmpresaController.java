package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;
import sollute.estoquecerto.request.empresas.EmpresaLoginRequest;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity criarEmpresa(
            @RequestBody @Valid Empresa createEmpresaResponse
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        String cnpj = createEmpresaResponse.getCnpj();

        if (!empresaRepository.existsByCnpj(cnpj)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando a empresa...", timeFormated);

                empresaRepository.save(createEmpresaResponse);

                System.out.printf("\n[ LOG ] - [%s] --- Empresa criada com sucesso.", timeFormated);
                return status(HttpStatus.CREATED).build();

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar a empresa.", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }
        }

        System.out.printf("\n[ LOG ] - [%s] --- Já existe uma empresa com esse CNPJ cadastrado.", timeFormated);
        return status(HttpStatus.BAD_REQUEST).body(
                ("Já existe uma empresa com esse CNPJ cadastrado.")
        );
    }

    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticado(
            @RequestBody @Valid EmpresaLoginRequest requisicao
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        List<Empresa> empresa = empresaRepository.findAll();

        for (Empresa e : empresa) {
            System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando autenticação...", timeFormated);

            if (e.getEmail().equals(requisicao.getLogin()) && e.getSenha().equals(requisicao.getSenha())) {

                System.out.printf("\n\n[ LOG ] - [%s] --- Autenticando usuário...", timeFormated);
                empresaRepository.atualizarAutenticado(requisicao.getLogin(), true);

                System.out.printf("\n[ LOG ] - [%s] --- Usuário autenticado com sucesso.", timeFormated);
                return status(HttpStatus.OK).body(e);

            } else {
                System.out.printf("\n[ LOG ] - [%s] --- Credenciais incorretas.", timeFormated);
                return status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Não há empresas cadastradas", timeFormated);
        return status(HttpStatus.NO_CONTENT).body("Não há empresas cadastradas");
    }

    @GetMapping("/listar-empresas")
    public ResponseEntity<List<Empresa>> listarEmpresas() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem das empresas...", timeFormated);
        List<Empresa> listaEmpresas = empresaRepository.findAll();

        if (listaEmpresas.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há empresas cadastradas.", timeFormated);
            return status(HttpStatus.NO_CONTENT).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando empresas.", timeFormated);
        return status(HttpStatus.OK).body(listaEmpresas);
    }

    @GetMapping("/deletar-empresa/{fkEmpresa}")
    public ResponseEntity deletarEmpresa(
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(fkEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluido a empresa do banco de dados", timeFormated);

                empresaRepository.deleteById(fkEmpresa);

                System.out.printf("\n[ LOG ] - [%s] --- Empresa excluida com sucesso", timeFormated);
                return status(HttpStatus.OK).build();

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir a empresa", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }
        }

        System.out.printf("\n[ LOG ] - [%s] --- Não existe empresa com esse ID", timeFormated);
        return status(HttpStatus.BAD_REQUEST).body(
                ("Não existe empresa com esse ID")
        );

    }

    @GetMapping("/calcular-produtos-vendidos/{fkEmpresa}")
    public ResponseEntity<Integer> calcularProdutosVendidos(
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        int aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando a contagem de todos os produtos vendidos...", timeFormated);
            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getQtdVendidos();
            }

            System.out.printf("\n[ LOG ] - [%s] --- Retornando a quantidade total de todos os produtos vendidos.", timeFormated);
            return status(HttpStatus.OK).body(aux);

        }

        System.out.printf("\n[ LOG ] - [%s] --- Não há empresas cadastradas.", timeFormated);
        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-valor-vendidos/{fkEmpresa}")
    public ResponseEntity<Double> calcularValorVendidos(
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando o calculo de todos os produtos vendidos...", timeFormated);
            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getValorVendidos();
            }

            System.out.printf("\n[ LOG ] - [%s] --- Retornando o valor total de todos os produtos vendidos.", timeFormated);
            return status(HttpStatus.OK).body(aux);
        }

        System.out.printf("\n[ LOG ] - [%s] --- Não há empresas cadastradas.", timeFormated);
        return status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/calcular-liquido/{fkEmpresa}")
    public ResponseEntity<Double> lucroLiquido(
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        Double bruto = calcularValorVendidos(fkEmpresa).getBody();
        double aux = 0;

        if (empresaRepository.existsById(fkEmpresa)) {

            System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando o calculo do lucro liquido da empresa", timeFormated);
            for (Produto prod : produtoRepository.findAll()) {
                aux += prod.getPrecoCompra() * prod.getQtdVendidos();
            }
            aux = bruto - aux;

            System.out.printf("\n[ LOG ] - [%s] --- Retornando o lucro liquido da empresa", timeFormated);
            return status(200).body(aux);
        }

        System.out.printf("\n[ LOG ] - [%s] --- Não há empresas cadastradas.", timeFormated);
        return status(HttpStatus.NOT_FOUND).build();
    }

}
