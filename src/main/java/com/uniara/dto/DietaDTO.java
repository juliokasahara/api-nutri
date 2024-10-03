package com.uniara.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DietaDTO {

    @NotBlank(message = "O nome da dieta não pode estar vazio")
    private String nome;
    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;
    @NotNull(message = "O valor das calorias deve ser informado")
    private BigDecimal caloria;
}
