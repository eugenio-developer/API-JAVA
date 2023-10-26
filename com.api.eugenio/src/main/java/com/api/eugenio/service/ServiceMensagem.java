package com.api.eugenio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.eugenio.models.Mensagem;
import com.api.eugenio.repository.RepositoryMensagem;
@Service
public class ServiceMensagem {
	@Autowired
	private RepositoryMensagem repositoryM;
	
	public void salvaMensagem(Mensagem mensagem) {
		repositoryM.save(mensagem);
	}
	public void excluiMensagem(Mensagem mensagem) {
		repositoryM.delete(mensagem);
	}
	public Optional<Mensagem> getMensagem(int codigo) {
		return repositoryM.findById(codigo);
	}
	public List<Mensagem> listaTodos(){
		return repositoryM.findAll();
	}
	
}
