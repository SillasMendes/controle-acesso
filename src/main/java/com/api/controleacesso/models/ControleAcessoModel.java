package com.api.controleacesso.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="TB_CONTROLE_ACESSO") /*NOME DA TABELA*/
public class ControleAcessoModel implements Serializable {
    private static final long serialVersionUID = 1L ;

    @Id /*IDENTIFICADOR*/
    @GeneratedValue(strategy = GenerationType.AUTO) /*GERADOR DE ID AUTOMATICO - NÃO PRECISA DE IMPLEMENTAÇÃO*/
    private UUID id; /*TIPO DE IDENTIFICADOR (UUID)*/
    @Column(nullable = false, unique = true, length = 10) /*CAMPO Ñ PODE SER NULO, CAMPO UNICO (Ñ PODE TER 2 CADASTRO, MAX 10 CARACTERES*/
    private String numeroDaVaga; /*Nº DA VAGA DO MORADOR*/
    @Column(nullable = false, unique = true, length = 10) /*CAMPO Ñ PODE SER NULO, CAMPO UNICO (Ñ PODE TER PLACAS REPTIDAS, MAX 10 CARACTERES*/
    private String numeroDaPlaca; /*Nº DA PLACA*/
    @Column(nullable = false, length = 70) /*CAMPO Ñ PODE SER NULO, MAX 70 CARACTERES*/
    private String brandCar; /*MARCA DO CARRO*/
    @Column(nullable = false, length = 70) /*CAMPO Ñ PODE SER NULO, MAX 70 CARACTERES*/
    private String modelCar; /*MODELO DO CARRO*/
    @Column(nullable = false, length = 70) /*CAMPO Ñ PODE SER NULO, MAX 70 CARACTERES*/
    private String colorCar; /*COR DO CARRO*/
    @Column(nullable = false) /*CAMPO Ñ PODE SER NULO*/
    private LocalDateTime registrationDate; /*DATA DO REGISTRO*/
    @Column(nullable = false, length = 130) /*CAMPO Ñ PODE SER NULO, MAX 130 CARACTERES*/
    private String responsibleName; /*NOME DO RESPONSAVEL PELO VEICULO - PROPRIETARIO/INQUILINO */
    @Column(nullable = false, length = 30) /*CAMPO Ñ PODE SER NULO, MAX 30 CARACTERES*/
    private String apartment; /*Nº DO APARTAMENTO*/
    @Column(nullable = false, length = 30) /*CAMPO Ñ PODE SER NULO, MAX 30 CARACTERES*/
    private String block; /*NOME DO BLOCO*/

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroDaVaga() {
        return numeroDaVaga;
    }

    public void setNumeroDaVaga(String numeroDaVaga) {
        this.numeroDaVaga = numeroDaVaga;
    }

    public String getNumeroDaPlaca() {
        return numeroDaPlaca;
    }

    public void setNumeroDaPlaca(String numeroDaPlaca) {
        this.numeroDaPlaca = numeroDaPlaca;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
