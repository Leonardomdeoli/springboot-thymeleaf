package br.com.facpoli.p3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.facpoli.p3.entity.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
	
	List<Personagem> findByNomeContainingIgnoreCase(String name);	
}
