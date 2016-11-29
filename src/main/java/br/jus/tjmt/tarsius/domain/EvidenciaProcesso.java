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
	@Column(length = 60, nullable = false)
	private String artefato;
	@OneToOne // 1 p/ 1
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private TipoArtefato tipo;
	@ManyToOne
	private CompetenciaInspecao competencia;
	@Enumerated(EnumType.STRING)
	
	private StatusGeral status;

	public String getArtefato() {
		return artefato;
	}

	public void setArtefato(String artefato) {
		this.artefato = artefato;
	}

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
