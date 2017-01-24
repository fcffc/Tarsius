package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Inspecao extends GenericDomain {
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private CompetenciaInspecao competencia;
	@ManyToOne
	@JoinColumn(nullable = false) // Para chave estrangeira
	private TipoInspecao tipo;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Checklist checklist;
	private int totalArtefato;
	@Column(nullable = false)
	private Boolean situacao;
	@Column(nullable = true)
	private String usuarioLogado;

	public CompetenciaInspecao getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaInspecao competencia) {
		this.competencia = competencia;
	}

	public TipoInspecao getTipo() {
		return tipo;
	}

	public void setTipo(TipoInspecao tipo) {
		this.tipo = tipo;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public int getTotalArtefato() {
		return totalArtefato;
	}

	public void setTotalArtefato(int totalArtefato) {
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
		String situacaoFormatada = "Inativo";
		if (situacao) {
			situacaoFormatada = "Ativo";
		}
		return situacaoFormatada;
	}
}