package br.com.zup.edu.ingressoagora.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.model.Ingresso;
import br.com.zup.edu.ingressoagora.repository.EventoRepository;
import br.com.zup.edu.ingressoagora.repository.IngressoRepository;

@RestController
@RequestMapping("eventos/{id}/ingressos")
public class CadastroIngressoController {
	
	private final EventoRepository eventoRepository;
	private final IngressoRepository ingressoRepository;

	public CadastroIngressoController(EventoRepository eventoRepository, IngressoRepository ingressoRepository) {
		this.eventoRepository = eventoRepository;
		this.ingressoRepository = ingressoRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarIngresso(@PathVariable Long id, @RequestBody @Valid IngressoRequest ingressoRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));		
		
		Ingresso novoIngresso = ingressoRequest.toIngressoModel(evento);
		
		ingressoRepository.save(novoIngresso);	
		
		
		URI location = uriComponentsBuilder.path("eventos/{id}/ingressos/{id}" )
				.buildAndExpand(evento.getId(), novoIngresso.getId()).toUri();
		
				return ResponseEntity.created(location).build();			
	}

}

