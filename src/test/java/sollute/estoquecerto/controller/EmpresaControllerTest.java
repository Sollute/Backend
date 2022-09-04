package sollute.estoquecerto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EmpresaController.class})
class EmpresaControllerTest {

    @Autowired
    EmpresaController empresaController;

    @MockBean
    private EmpresaRepository empresaRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private CaixaRepository repositoryCaixa;

    @MockBean
    private CarrinhoRepository carrinhoRepository;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @MockBean
    private FornecedorRepository fornecedorRepository;

    // ------------------------------------------------------------------------------------------ //
    // Testes da empresa

    @Test
    @DisplayName("Sem empresas deveria retornar 204 SEM corpo")
    void getListaEmpresaVazia() {

        when(empresaRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Empresa>> resposta = empresaController.listarEmpresas();

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com empresas deveria retornar 200 COM corpo")
    void getListaEmpresaPreenchida() {

        Empresa empresa1 = mock(Empresa.class);
        Empresa empresa2 = mock(Empresa.class);
        List<Empresa> listaMock = List.of(empresa1, empresa2);

        when(empresaRepository.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Empresa>> resposta = empresaController.listarEmpresas();

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }


    // ------------------------------------------------------------------------------------------ //

}