package com.api.controleacesso.repositorys;

import com.api.controleacesso.models.ControleAcessoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ControleAcessoRepository extends JpaRepository <ControleAcessoModel, UUID> {

    boolean existsByNumeroDaPlaca(String numeroDaPlaca);
    boolean existsByNumeroDaVaga(String numeroDaVaga);
    boolean existsByApartmentAndBlock(String apartment, String block);

}
