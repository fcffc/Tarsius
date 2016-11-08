package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Sistema extends GenericDomain {

	@Column(length = 20, nullable = false) // Não pode ser nulo
	private String sigla;
	@Column(length = 80, nullable = false) // Não pode ser nulo
	private String descricao;
	@Column(length = 100)
	private String observacao;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
