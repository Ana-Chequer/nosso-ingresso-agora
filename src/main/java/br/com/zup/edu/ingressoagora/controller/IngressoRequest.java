package br.com.zup.edu.ingressoagora.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.ingressoagora.model.EstadoIngresso;
import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.model.Ingresso;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IngressoRequest {
	
	@NotNull
	private EstadoIngresso estado;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime compradoEm = LocalDateTime.now();
	

	public IngressoRequest(EstadoIngresso estado, LocalDateTime compradoEm) {
		this.estado = estado;
		this.compradoEm = compradoEm;
	}
	
	public Ingresso toIngressoModel(Evento evento) {
		return new Ingresso(estado, compradoEm, evento);		
	}
}
