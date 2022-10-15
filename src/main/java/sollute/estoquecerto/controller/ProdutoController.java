package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Produto;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.repository.ProdutoRepository;
import sollute.estoquecerto.request.produtos.NovoProdutoRequest;
import sollute.estoquecerto.responses.produtos.ListaProdutosResponse;
import sollute.estoquecerto.responses.produtos.ProdutoResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping("/criar-produto/{idEmpresa}")
    public ResponseEntity criarProduto(
            @RequestBody @Valid Produto novoProduto,
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(idEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Criando o produto...", timeFormated);

                novoProduto.setFkEmpresa(empresaRepository.findByIdEmpresa(idEmpresa));
                produtoRepository.save(novoProduto);

                System.out.printf("\n[ LOG ] - [%s] --- Produto criado com sucesso.", timeFormated);
                return status(201).build();

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao criar o produto.", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).body(
                ("Esse produto já existe.")
        );
    }

    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos produtos...", timeFormated);
        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresaOrderByEstoqueDesc(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há produtos cadastrados.", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando produtos.", timeFormated);
        return status(200).body(lista);
    }

    @GetMapping("/listar-produtos-android/{idEmpresa}")
    public ResponseEntity<List<ListaProdutosResponse>> listarProdutosAndroid(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos produtos...", timeFormated);
        List<ListaProdutosResponse> lista = produtoRepository.listarProdutosAndroid(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há produtos cadastrados.", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando produtos.", timeFormated);
        return status(200).body(lista);
    }

    @GetMapping("/listar-produtos-ordem-maior/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutosOrdemMaior(
            @PathVariable Integer idEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando listagem dos produtos...", timeFormated);
        List<Produto> lista = produtoRepository.findFirst5ByFkEmpresaIdEmpresaOrderByQtdVendidosDesc(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há produtos cadastrados.", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Listando produtos.", timeFormated);
        return status(200).body(lista);
    }

    @PutMapping("/editar-produto/{idEmpresa}/{codigo}")
    public ResponseEntity editarProduto(
            @RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
            @PathVariable Integer idEmpresa,
            @PathVariable String codigo
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);
        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) {
            System.out.printf("\n[ LOG ] - [%s] --- Não há produtos cadastrados.", timeFormated);
            return status(400).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando edição do produto...", timeFormated);
        if (produtoRepository.existsByCodigo(codigo)) {

            Integer estoque = novoProdutoRequest.getEstoque();
            Integer estoqueMin = novoProdutoRequest.getEstoqueMin();
            Integer estoqueMax = novoProdutoRequest.getEstoqueMax();
            Double precoCompra = novoProdutoRequest.getPrecoCompra();
            Double precoVenda = novoProdutoRequest.getPrecoVenda();

            System.out.printf("\n\n[ LOG ] - [%s] --- Editando as informações do produto...", timeFormated);
            produtoRepository.atualizarProduto(
                    estoque,
                    estoqueMin,
                    estoqueMax,
                    precoCompra,
                    precoVenda,
                    codigo,
                    idEmpresa);

            System.out.printf("\n\n[ LOG ] - [%s] --- Informações editadas com sucesso.", timeFormated);
            return status(200).build();
        }

        System.out.printf("\n\n[ LOG ] - [%s] --- Produto não existe.", timeFormated);
        return status(HttpStatus.NOT_FOUND).body(
                ("Esse produto não existe.")
        );
    }

    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(
            @PathVariable String codigo,
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        if (empresaRepository.existsById(fkEmpresa)) {
            try {
                System.out.printf("\n\n[ LOG ] - [%s] --- Excluindo o produto do banco de dados", timeFormated);
                if (produtoRepository.existsByCodigo(codigo)) {

                    produtoRepository.deleteProdutoByCodigoAndFkEmpresaIdEmpresa(codigo, fkEmpresa);
                    return status(200).build();
                }

                System.out.printf("\n\n[ LOG ] - [%s] --- Produto não existe.", timeFormated);
                return status(HttpStatus.NOT_FOUND).body(
                        ("Esse produto não existe.")
                );

            } catch (RuntimeException ex) {
                System.out.printf("\n[ LOG ] - [%s] --- Falha ao excluir o produto", timeFormated);
                return status(HttpStatus.BAD_REQUEST).body(ex.toString());
            }

        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/info/{nome}/{fkEmpresa}")
    public ResponseEntity getInfoProduct(
            @PathVariable String nome,
            @PathVariable Integer fkEmpresa
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeFormated = LocalDateTime.now().format(formatter);

        System.out.printf("\n\n[ LOG ] - [%s] --- Iniciando coleta de informações do produto...", timeFormated);
        ProdutoResponse product = produtoRepository.getInfoByNameAndFkEmpresa(nome, fkEmpresa);

        if (product == null) {
            System.out.printf("\n[ LOG ] - [%s] --- Produto não existe", timeFormated);
            return status(204).build();
        }

        System.out.printf("\n[ LOG ] - [%s] --- Retornando informações do produto.", timeFormated);
        return status(200).body(product);
    }
}
