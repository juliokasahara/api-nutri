package com.uniara;

import com.uniara.model.Dieta;
import com.uniara.model.Usuario;
import com.uniara.repository.DietaRepository;
import com.uniara.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, DietaRepository dietaRepository){
		return args -> {
			List<Usuario> usuarioList = usuarioRepository.findAll();

			if(usuarioList.isEmpty()){

				Dieta hiperCalorica = new Dieta();
				hiperCalorica.setNome("Sanduíche Hipercalórico");
				hiperCalorica.setDescricao("Sanduíche recheado com carne, queijo, e molhos.");
				hiperCalorica.setCaloria(new BigDecimal("800"));

				Dieta proteina = new Dieta();
				proteina.setNome("Shake de Ganho de Massa");
				proteina.setDescricao("Shake com proteína, banana e aveia.");
				proteina.setCaloria(new BigDecimal("600"));

				Dieta manter = new Dieta();
				manter.setNome("Salada de Quinoa");
				manter.setDescricao("Salada leve com quinoa, legumes e azeite.");
				manter.setCaloria(new BigDecimal("350"));

				dietaRepository.save(hiperCalorica);
				dietaRepository.save(proteina);
				dietaRepository.save(manter);

				Usuario ana = new Usuario(null,"ana","ana@test.com","123456", new HashSet<>(Arrays.asList(hiperCalorica)));
				Usuario carlos = new Usuario(null,"carlos","carlos@test.com","123456", new HashSet<>(Arrays.asList(proteina)));
				Usuario roger = new Usuario( null,"roger","roger@test.com","123456", new HashSet<>(Arrays.asList(manter)));

				usuarioRepository.save(ana);
				usuarioRepository.save(carlos);
				usuarioRepository.save(roger);

				System.out.println("Usuario criado com sucesso!");
			}
		};
	}
}
