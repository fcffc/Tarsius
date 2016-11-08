package br.jus.tjmt.tarsius.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Gestor extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
