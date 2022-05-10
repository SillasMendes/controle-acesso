package com.api.controleacesso.controllers;

import com.api.controleacesso.dtos.ControleAcessoDTO;
import com.api.controleacesso.models.ControleAcessoModel;
import com.api.controleacesso.services.ControleAcessoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "x", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ControleAcessoController {

    final ControleAcessoService controleAcessoService;

    public ControleAcessoController(ControleAcessoService controleAcessoService) {
        this.controleAcessoService = controleAcessoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveControleAcesso(@RequestBody @Valid ControleAcessoDTO controleAcessoDTO){
        if (controleAcessoService.existsByNumeroDaPlaca(controleAcessoDTO.getNumeroDaPlaca())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Carro de matrícula já está em uso!");
        }
        if (controleAcessoService.existsByNumeroDaVaga(controleAcessoDTO.getNumeroDaVaga())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: A vaga de estacionamento já está em uso!");
        }
        if (controleAcessoService.existsByApartmentAndBlock(controleAcessoDTO.getApartment(),controleAcessoDTO.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de Estacionamento já cadastrada para este apartamento/bloco!");
        }
        var controleAcessoModel = new ControleAcessoModel();
        BeanUtils.copyProperties(controleAcessoDTO, controleAcessoModel);
        controleAcessoModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(controleAcessoService.save(controleAcessoModel));
    }

    @GetMapping
    public ResponseEntity<Page<ControleAcessoModel>> getAllControledeAcesso(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(controleAcessoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneControledeAcesso(@PathVariable(value = "id") UUID id){
        Optional<ControleAcessoModel> parkingSpotModelOptional = controleAcessoService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de estacionamento não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ControleAcessoModel> parkingSpotModelOptional = controleAcessoService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de estacionamento não encontrada.");
        }
        controleAcessoService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga de estacionamento excluída com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ControleAcessoDTO controleAcessoDTO){
        Optional<ControleAcessoModel> parkingSpotModelOptional = controleAcessoService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de estacionamento não encontrada.");
        }
        var parkingSpotModel = new ControleAcessoModel();
        BeanUtils.copyProperties(controleAcessoDTO, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(controleAcessoService.save(parkingSpotModel));
    }
}

