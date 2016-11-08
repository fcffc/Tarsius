package br.jus.tjmt.tarsius.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class CompetenciaInspecao extends GenericDomain {
	@Column(length = 80, nullable = false) // Não pode ser nulo
	private String descricao;
	@Temporal(TemporalType.DATE) // Apenas Data
	@Column(nullable = false)
	private Date dataInicio;
	@Temporal(TemporalType.DATE) // Apenas Data
	@Column(nullable = false)
	private Date dataFim;
	@Column(length = 100)
	private String observacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
