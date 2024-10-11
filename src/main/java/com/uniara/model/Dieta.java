package com.uniara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal caloria;

    @JsonIgnore
    @ManyToMany(mappedBy = "dietas")
    private Set<Usuario> usuarios = new HashSet<>();

    public Dieta(String nome, String descricao, BigDecimal caloria) {
        this.nome = nome;
        this.descricao = descricao;
        this.caloria = caloria;
    }

}
