package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.jus.tjmt.tarsius.enumeracao.TipoPergunta;

@SuppressWarnings("serial")
@Entity
public class Inspecao extends GenericDomain {
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private CompetenciaInspecao competencia;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPergunta tipo;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Checklist checklist;
	@Column(nullable = false)
	private int totalArtefato;
	@Column(nullable = false)
	private Boolean situacao;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private ItemInspecao itemInspecao;
	@Column(nullable = false)
	private String usuarioLogado;

	public CompetenciaInspecao getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaInspecao competencia) {
		this.competencia = competencia;
	}

	public TipoPergunta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPergunta tipo) {
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

	public ItemInspecao getItemInspecao() {
		return itemInspecao;
	}

	public void setItemInspecao(ItemInspecao itemInspecao) {
		this.itemInspecao = itemInspecao;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
