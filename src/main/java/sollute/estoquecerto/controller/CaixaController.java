package sollute.estoquecerto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sollute.estoquecerto.repository.CaixaRepository;
import sollute.estoquecerto.repository.EmpresaRepository;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @GetMapping("/pegar-saldo/{idEmpresa}")
    public ResponseEntity<Double> getValor(@PathVariable Integer idEmpresa) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {
            Double saldo = caixaRepository.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();

            return status(HttpStatus.OK).body(saldo);
        }

        return status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/adicionar-valor-caixa/{idEmpresa}/{valor}")
    public ResponseEntity<Double> creditarValor(@PathVariable Integer idEmpresa,
                                                @PathVariable Double valor) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {

            if (valor < 0) return status(HttpStatus.BAD_REQUEST).build();

            double saldo = caixaRepository.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();
            double saldoAtual = saldo + valor;

            caixaRepository.atualizarValor(saldoAtual, 1, idEmpresa);

            return status(HttpStatus.OK).body(saldoAtual);
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/remover-valor-caixa/{idEmpresa}/{valor}")
    public ResponseEntity<Double> debitarValor(@PathVariable Integer idEmpresa,
                                               @PathVariable Double valor) {

        boolean empresa = empresaRepository.existsById(idEmpresa);

        if (empresa) {

            if (valor < 0) return status(HttpStatus.BAD_REQUEST).build();

            double saldo = caixaRepository.findCaixaByFkEmpresaIdEmpresa(idEmpresa).getValor();
            double saldoAtual = saldo - valor;

            if ((saldo - valor) >= 0) {
                caixaRepository.atualizarValor(saldoAtual, 1, idEmpresa);

                return status(HttpStatus.OK).body(saldoAtual);
            }

            return status(HttpStatus.BAD_REQUEST).build();
        }

        return status(HttpStatus.BAD_REQUEST).build();
    }

}
