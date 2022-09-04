package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.entity.Produto;
import sollute.estoquecerto.repository.EmpresaRepository;
import sollute.estoquecerto.repository.ProdutoRepository;
import sollute.estoquecerto.request.produtos.NovoProdutoRequest;

import javax.validation.Valid;
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
    public ResponseEntity criarProduto(@RequestBody @Valid Produto novoProduto,
                                       @PathVariable Integer idEmpresa) {

        if (empresaRepository.existsById(idEmpresa)) {
            produtoRepository.save(novoProduto);
            return status(201).build();
        }

        return status(404).build();
    }

    @GetMapping("/listar-produtos/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Integer idEmpresa) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresaOrderByEstoqueDesc(idEmpresa);

        if (lista.isEmpty()) return status(204).build();

        return status(200).body(lista);

    }

    @GetMapping("/listar-produtos-ordem-maior/{idEmpresa}")
    public ResponseEntity<List<Produto>> listarProdutosOrdemMaior(@PathVariable Integer idEmpresa) {

        List<Produto> lista = produtoRepository.findFirst5ByFkEmpresaIdEmpresaOrderByQtdVendidosDesc(idEmpresa);

        if (lista.isEmpty()) return status(204).build();

        return status(200).body(lista);

    }

    @PutMapping("/editar-produto/{idEmpresa}/{codigo}")
    public ResponseEntity editarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
                                        @PathVariable Integer idEmpresa,
                                        @PathVariable String codigo) {

        List<Produto> lista = produtoRepository.findByFkEmpresaIdEmpresa(idEmpresa);

        if (lista.isEmpty()) return status(400).build();

        if (produtoRepository.existsByCodigo(codigo)) {

            Integer estoque = novoProdutoRequest.getEstoque();
            Integer estoqueMin = novoProdutoRequest.getEstoqueMin();
            Integer estoqueMax = novoProdutoRequest.getEstoqueMax();
            Double precoCompra = novoProdutoRequest.getPrecoCompra();
            Double precoVenda = novoProdutoRequest.getPrecoVenda();

            produtoRepository.atualizarProduto(
                    estoque,
                    estoqueMin,
                    estoqueMax,
                    precoCompra,
                    precoVenda,
                    codigo,
                    idEmpresa);

            return status(200).build();
        }

        return status(404).build();
    }

    @DeleteMapping("/deletar-produto/{codigo}/{fkEmpresa}")
    public ResponseEntity deletarProduto(@PathVariable String codigo,
                                         @PathVariable Integer fkEmpresa) {

        if (empresaRepository.existsById(fkEmpresa)) {
            produtoRepository.deleteProdutoByCodigoAndFkEmpresaIdEmpresa(codigo, fkEmpresa);
            return status(200).build();
        }

        return status(404).build();
    }

}
