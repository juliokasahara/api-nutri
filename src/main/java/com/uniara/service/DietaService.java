package com.uniara.service;

import com.uniara.dto.DietaDTO;
import com.uniara.model.Dieta;

import java.util.List;

public interface DietaService {
    Dieta save(Dieta dieta);
    List<Dieta> listarDietasDeUsuario(Long idUsuario);
    void cadastrarDietaUsuario(Long idUsuario, DietaDTO dietaDTO);

}
