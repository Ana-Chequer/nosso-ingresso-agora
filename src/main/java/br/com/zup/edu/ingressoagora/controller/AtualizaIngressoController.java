package br.com.zup.edu.ingressoagora.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.ingressoagora.model.EstadoIngresso;
import br.com.zup.edu.ingressoagora.model.Ingresso;
import br.com.zup.edu.ingressoagora.repository.IngressoRepository;

@RestController
public class AtualizaIngressoController {

	private final IngressoRepository ingressoRepository;

	public AtualizaIngressoController(IngressoRepository ingressoRepository) {
		this.ingressoRepository = ingressoRepository;

	}

	@Transactional
	@PutMapping("ingressos/{id}")
	public ResponseEntity<?> atualizarIngresso(@PathVariable Long id) {

		Ingresso ingresso = ingressoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		long eventoData = ChronoUnit.DAYS.between(LocalDate.now(), ingresso.getEvento().getData());
		
		if ((ingresso.getEstado().equals(EstadoIngresso.NAOCONSUMIDO)) && (eventoData >= 1)) {

			ingresso.setEstado(EstadoIngresso.CANCELADO);
			
			ingressoRepository.save(ingresso);

		} else {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return ResponseEntity.noContent().build();

	}

}
