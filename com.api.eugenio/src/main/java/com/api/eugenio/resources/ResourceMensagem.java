package com.api.eugenio.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eugenio.models.Mensagem;
import com.api.eugenio.service.ServiceMensagem;
@RestController
@RequestMapping ("/api/mensagem")
public class ResourceMensagem {

	@Autowired
	private ServiceMensagem serviceM;
	
	@GetMapping("")
	public  ResponseEntity<List<Mensagem>> listAll(){
		return new ResponseEntity<List<Mensagem>>(serviceM.listaTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Mensagem> getMensagem (@PathVariable ("codigo") int codigo){
		Optional<Mensagem> optionalMensagem = serviceM.getMensagem(codigo);
		if(optionalMensagem.isPresent()) {
			return new ResponseEntity<Mensagem>(optionalMensagem.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Mensagem> salvarMensagem (@RequestBody Mensagem mensagem){
		serviceM.salvaMensagem(mensagem);
		
		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/excluir/{codigo}")
	public ResponseEntity<Mensagem> deletarMensagem(@PathVariable ("codigo") int codigo){

		Optional<Mensagem> optionalMensagem = serviceM.getMensagem(codigo);
		if(optionalMensagem.isPresent()) {
			serviceM.excluiMensagem(optionalMensagem.get());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/editar/{codigo}")
	public ResponseEntity<Mensagem> updateMensagem(@PathVariable("codigo") int codigo,@RequestBody Mensagem mensagem ){
		Optional<Mensagem> optionalMensagem = serviceM.getMensagem(codigo);
		if(optionalMensagem.isPresent()) {
			mensagem.setId(codigo);
			serviceM.salvaMensagem(mensagem);
			return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	
}
