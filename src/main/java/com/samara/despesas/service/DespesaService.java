package com.samara.despesas.service;

import com.samara.despesas.model.Despesa;
import com.samara.despesas.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DespesaService {

        private final DespesaRepository despesaRepository;

        public DespesaService(DespesaRepository despesaRepository) {
            this.despesaRepository = despesaRepository;
        }

        public List<Despesa> listarTodas() {
            return despesaRepository.findAll();
        }

        public Optional<Despesa> buscarPorId(Long id) {
            return despesaRepository.findById(id);
        }

        public Despesa salvar(Despesa despesa) {
            return despesaRepository.save(despesa);
        }

        public void deletar(Long id) {
            despesaRepository.deleteById(id);
        }
}
