package com.uniara.controller;

import com.uniara.dto.DietaDTO;
import com.uniara.model.Dieta;
import com.uniara.service.DietaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dieta")
@AllArgsConstructor
public class DietaController {

    private final DietaService service;
    @PostMapping
    private Dieta save(@RequestBody Dieta dieta){
        return service.save(dieta);
    }

    @GetMapping()
    private List<Dieta> listarDietas(){
        return service.findAll();
    }

    @GetMapping("/usuario/{id}")
    private List<Dieta> listarDietasDeUsuario(@PathVariable Long id){
        return service.listarDietasDeUsuario(id);
    }

    @PostMapping("/usuario/{id}")
    private void cadastrarDieta(@PathVariable Long id,@Valid @RequestBody DietaDTO dietaDTO){
        service.cadastrarDietaUsuario(id, dietaDTO);
    }

    @PutMapping("/editar")
    private Dieta editarDieta(@Valid @RequestBody DietaDTO dietaDTO){
        return service.editarDietaUsuario(dietaDTO);
    }

}
