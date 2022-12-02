package br.com.zup.edu.ingressoagora.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.zup.edu.ingressoagora.model.EstadoIngresso.NAOCONSUMIDO;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class Ingresso {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoIngresso estado = NAOCONSUMIDO;

    @Column(nullable = false)
    private LocalDateTime compradoEm = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Evento evento;

    public Ingresso(EstadoIngresso estado, LocalDateTime compradoEm, Evento evento) {
        this.estado = estado;
        this.compradoEm = compradoEm;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }
    
}
