package com.samara.despesas.controller;

import com.samara.despesas.model.Despesa;
import com.samara.despesas.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping
    public List<Despesa> listar() {
        return despesaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Long id) {
        return despesaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Despesa criar(@RequestBody Despesa despesa) {
        return despesaService.salvar(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa despesa) {
        return despesaService.buscarPorId(id)
                .map(d -> {
                    d.setDescricao(despesa.getDescricao());
                    d.setValor(despesa.getValor());
                    d.setData(despesa.getData());
                    d.setCategoria(despesa.getCategoria());
                    Despesa atualizada = despesaService.salvar(d);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (despesaService.buscarPorId(id).isPresent()) {
            despesaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }}
