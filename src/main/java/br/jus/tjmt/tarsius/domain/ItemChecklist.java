package br.jus.tjmt.tarsius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.jus.tjmt.tarsius.enumeracao.SecaoProcesso;
import br.jus.tjmt.tarsius.enumeracao.SecaoSistema;
import br.jus.tjmt.tarsius.enumeracao.StatusInspecao;

@SuppressWarnings("serial")
@Entity
public class ItemChecklist extends GenericDomain {
	@Column(length = 200, nullable = false)
	private String pergunta;
	@Column(nullable = false)
	private String descricao;
	@Enumerated(EnumType.STRING)
	private SecaoProcesso secaoProc;
	@Enumerated(EnumType.STRING)
	private SecaoSistema secaoAplic;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Pessoa responsavel;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Gestor gestor;
	@OneToOne
	@JoinColumn(nullable = false) // Para chave estrangeira não nulo
	private Nomenclatura tipoNConformidade;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusInspecao statusInspecao;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SecaoProcesso getSecaoProc() {
		return secaoProc;
	}

	public void setSecaoProc(SecaoProcesso secaoProc) {
		this.secaoProc = secaoProc;
	}

	public SecaoSistema getSecaoAplic() {
		return secaoAplic;
	}

	public void setSecaoAplic(SecaoSistema secaoAplic) {
		this.secaoAplic = secaoAplic;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Nomenclatura getTipoNConformidade() {
		return tipoNConformidade;
	}

	public void setTipoNConformidade(Nomenclatura tipoNConformidade) {
		this.tipoNConformidade = tipoNConformidade;
	}

	public StatusInspecao getStatusInspecao() {
		return statusInspecao;
	}

	public void setStatusInspecao(StatusInspecao statusInspecao) {
		this.statusInspecao = statusInspecao;
	}
}
