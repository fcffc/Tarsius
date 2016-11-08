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
public class EvidenciaProcesso extends GenericDomain {
	@OneToOne // 1 p/ 1
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private TipoArtefato tipo;
	@ManyToOne
	private CompetenciaInspecao competencia;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusGeral status;

	public TipoArtefato getTipo() {
		return tipo;
	}

	public void setTipo(TipoArtefato tipo) {
		this.tipo = tipo;
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
