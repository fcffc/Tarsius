package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.jus.tjmt.tarsius.enumeracao.StatusGeral;

@SuppressWarnings("serial")
@Entity
public class EvidenciaSistema extends GenericDomain {
	@Column(length = 100, nullable = false) // Não pode ser nulo
	private String funcionalidade;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Sistema aplicacao;
	@ManyToOne
	private CompetenciaInspecao competencia;
	@Enumerated(EnumType.STRING)
	private StatusGeral status;

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Sistema getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Sistema aplicacao) {
		this.aplicacao = aplicacao;
	}

	public CompetenciaInspecao getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaInspecao competencia) {
		this.competencia = competencia;
	}

	public StatusGeral getStatus() {
		return status;
	}

	public void setStatus(StatusGeral status) {
		this.status = status;
	}
}
