package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class TipoInspecao extends GenericDomain {
	@Column(length = 60, nullable = false) // Not Null
	private String tipoInspecao;
	@Column(length = 100)
	private String observacao;	

	public String getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(String tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}