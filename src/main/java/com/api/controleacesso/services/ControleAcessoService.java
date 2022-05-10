package com.api.controleacesso.services;

import com.api.controleacesso.models.ControleAcessoModel;
import com.api.controleacesso.repositorys.ControleAcessoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ControleAcessoService {

    final
      ControleAcessoRepository controleAcessoRepository;

    public ControleAcessoService(ControleAcessoRepository controleAcessoRepository) {
        this.controleAcessoRepository = controleAcessoRepository;
    }

    @Transactional
    public ControleAcessoModel save(ControleAcessoModel controleAcessoModel) {
        return controleAcessoRepository.save(controleAcessoModel);
    }

    public boolean existsByNumeroDaPlaca(String numeroDaPlaca) {
        return controleAcessoRepository.existsByNumeroDaPlaca(numeroDaPlaca);
    }

    public boolean existsByNumeroDaVaga(String numeroDaVaga) {
        return controleAcessoRepository.existsByNumeroDaVaga(numeroDaVaga);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return controleAcessoRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ControleAcessoModel> findAll(Pageable pageable) {
        return controleAcessoRepository.findAll(pageable);
    }

    public Optional<ControleAcessoModel> findById(UUID id) {
        return controleAcessoRepository.findById(id);
    }

    @Transactional
    public void delete(ControleAcessoModel controleAcessoModel) {
        controleAcessoRepository.delete(controleAcessoModel);
    }
}
