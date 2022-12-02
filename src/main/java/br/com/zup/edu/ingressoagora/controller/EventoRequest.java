package br.com.zup.edu.ingressoagora.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.ingressoagora.model.Evento;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EventoRequest {
	
	@NotBlank
	private String titulo;
	
	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull
	private BigDecimal preco;

	public EventoRequest(String titulo, LocalDate data, BigDecimal preco) {
		this.titulo = titulo;
		this.data = data;
		this.preco = preco;
	}
	
	public Evento toEventoModel() {
		return new Evento(titulo, data, preco);		
	}

}
