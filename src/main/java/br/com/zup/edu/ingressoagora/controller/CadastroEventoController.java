package br.com.zup.edu.ingressoagora.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.repository.EventoRepository;

@RestController
public class CadastroEventoController {
	
	 private final EventoRepository eventoRepository;

	public CadastroEventoController(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	 
	@PostMapping("/eventos") 
	public ResponseEntity<?> cadastrarEvento(@RequestBody @Valid EventoRequest eventoRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Evento evento = eventoRequest.toEventoModel();
		
		eventoRepository.save(evento);
		
		URI location = uriComponentsBuilder.path("/eventos/{id}")
				.buildAndExpand(evento.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
