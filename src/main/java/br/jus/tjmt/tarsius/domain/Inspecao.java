package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Inspecao extends GenericDomain {
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private CompetenciaInspecao competencia;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Checklist checklist;
	private Short totalArtefato;
	@Column(nullable = false)
	private Boolean situacao;
	private String usuarioLogado;

	public CompetenciaInspecao getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaInspecao competencia) {
		this.competencia = competencia;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public Short getTotalArtefato() {
		return totalArtefato;
	}

	public void setTotalArtefato(Short totalArtefato) {
		this.totalArtefato = totalArtefato;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	// Formata True / False para Ativo / Inativo
	@Transient
	public String getSituacaoFormatada() {
		String situacaoFormatada = "Em execução";
		if (situacao) {
			situacaoFormatada = "Concluído";
		}
		return situacaoFormatada;
	}
}