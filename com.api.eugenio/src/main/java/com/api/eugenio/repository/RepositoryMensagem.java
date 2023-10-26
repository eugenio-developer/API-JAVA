package com.api.eugenio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.eugenio.models.Mensagem;

@Repository
public interface RepositoryMensagem extends JpaRepository<Mensagem, Integer>{

	
	
}
